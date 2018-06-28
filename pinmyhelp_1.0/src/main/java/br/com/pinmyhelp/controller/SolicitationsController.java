package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.database.ConnectionManager;
import br.com.pinmyhelp.model.Claimant;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.types.GeoLocation;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.HelpType;
import java.time.LocalDate;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author adriano
 */
@Controller
public class SolicitationsController {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    EntityDAO entityDAO;

    @Autowired
    HelpSolicitationDAO helpSolicitationDAO;

    @RequestMapping("/solicitations")
    public String index(Model model) {
        model.addAttribute("title", "Solicitações");
        model.addAttribute("page", "solicitations/index");
        return "app";
    }

    @RequestMapping("/solicitations/my")
    public ModelAndView my(HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("title", "Meus Pedidos");
        mav.addObject("page", "solicitations/my");
        mav.addObject("mySolicitations", helpSolicitationDAO.findByClaimantId(((User) session.getAttribute("user")).getId(), null));
        return mav;
    }

    @RequestMapping(value = "/solicitations/create", method = GET)
    public ModelAndView create(HttpSession session) {
        return getModelCreatePage(session);
    }

    @RequestMapping(value = "/solicitations/store", method = POST)
    public ModelAndView store(@Valid HelpSolicitation help, BindingResult result, @ModelAttribute("type_id") String typeId, HttpSession session, RedirectAttributes redirectAttrs) {
        ModelAndView mv = getModelCreatePage(session);
        Boolean datesAreValid = validateDates(help.getStartDate(), help.getEndDate(), result, mv);
        Boolean helpTypeIsValid = validateHelpType(typeId, help, mv);
        if (result.hasErrors() || !datesAreValid || !helpTypeIsValid) {
            return mv;
        }
        User user = (User) session.getAttribute("user");
        if (((String) session.getAttribute("type")).equals(Person.TYPE_CLAIMANT)) {
            help.setClaimant(new Claimant(user.getId()));
        } else {
            help.setEntity(new Entity(user.getId()));
        }
        helpSolicitationDAO.create(help);
        //return new DashboardController().redirectDashboard(session);
        redirectAttrs.addFlashAttribute("msg_success", "Solicitação criada com sucesso!");
        return new ModelAndView("redirect:/solicitations/my");
    }

    @RequestMapping(value = "/solicitations/edit/{idRequest}", method = GET)
    public ModelAndView edit(@PathVariable(value = "idRequest") int id, HttpSession session) {

        HelpSolicitation solicitation = helpSolicitationDAO.findOne(id);

        //ModelAndView mav = new ModelAndView("app");
        ModelAndView mav = getModelEditPage(session, id, solicitation);
        return mav;
    }

    /**
     *
     * @param id
     * @param help
     * @param result
     * @param session
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "/solicitations/update/{idRequest}", method = POST)
    public ModelAndView update(@PathVariable(value = "idRequest") int id, @Valid HelpSolicitation help, BindingResult result, @ModelAttribute("type_id") String typeId, HttpSession session, RedirectAttributes redirectAttrs) {

        ModelAndView mv = getModelEditPage(session, id, help);
        Boolean datesAreValid = validateDates(help.getStartDate(), help.getEndDate(), result, mv);
        Boolean helpTypeIsValid = validateHelpType(typeId, help, mv);
        if (result.hasErrors() || !datesAreValid || !helpTypeIsValid) {
            return mv;
        }

        // ConnectionManager.openConnection();
        User user = (User) session.getAttribute("user");
        if (((String) session.getAttribute("type")).equals(Person.TYPE_CLAIMANT)) {
            help.setClaimant(new Claimant(user.getId()));
        } else {
            help.setEntity(new Entity(user.getId()));
        }

        help.setStatus(HelpStatus.S1);
        help.setLocation(new GeoLocation(0, 0));
        
        helpSolicitationDAO.update(help);
        // ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Solicitação Alterada com sucesso!");

        return new ModelAndView("redirect:/solicitations/my");
    }

    @RequestMapping(value = "/solicitations/delete/{idRequest}", method = GET)
    public ModelAndView delete(@PathVariable(value = "idRequest") int id, HttpSession session, RedirectAttributes redirectAttrs) {
        ConnectionManager.openConnection();
        HelpSolicitation solicitation = helpSolicitationDAO.findOne(id);

        helpSolicitationDAO.delete(solicitation);

        ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Solicitação removida com sucesso!");
        return new ModelAndView("redirect:/solicitations/my");
    }

    private Boolean validateDates(LocalDate startDate, LocalDate endDate, BindingResult result, ModelAndView mv) {
        Boolean formatError = false;
        if (startDate == null || result.hasFieldErrors("startDate")) {
            mv.addObject("errorStartDate", "Informe uma data válida");
            formatError = true;
        }
        if (endDate == null || result.hasFieldErrors("endDate")) {
            mv.addObject("errorEndDate", "Informe uma data válida");
            formatError = true;
        }
        if (!formatError) {
            if (!startDate.isEqual(LocalDate.now()) && startDate.isBefore(LocalDate.now())) {
                mv.addObject("errorStartDate", "Data de início não pode ser menor que a data atual");
                formatError = true;
            } else if (!startDate.isEqual(endDate) && startDate.isAfter(endDate)) {
                mv.addObject("errorStartDate", "Data de início não pode ser maior que a data fim");
                formatError = true;
            }
        }
        return !formatError;
    }

    private Boolean validateHelpType(String typeId, HelpSolicitation help, ModelAndView mv) {
        if (typeId == null) {
            mv.addObject("errorHelpType", "Escolha o tipo da ajuda solicitada");
            return false;
        }
        try {
            Integer id = Integer.parseInt(typeId);
            HelpType helpType = HelpType.get(id);
            if (helpType != null) {
                help.setType(helpType);
                return true;
            }
        } catch (NumberFormatException e) {
        }
        mv.addObject("errorHelpType", "Escolha um tipo válido");
        return false;
    }

    private ModelAndView getModelCreatePage(HttpSession session) {
        ModelAndView mv = new ModelAndView("app");
        mv.addObject("title", "Nova solicitação de Ajuda");
        mv.addObject("page", "solicitations/create");
        mv.addObject("helpTypes", Arrays.asList(HelpType.values()));
        return mv;
    }

    private ModelAndView getModelEditPage(HttpSession session, Integer id, HelpSolicitation solicitation) {
        ModelAndView mv = new ModelAndView("app");
        mv.addObject("page", "solicitations/edit");
        mv.addObject("id", id);
        mv.addObject("helpTypes", Arrays.asList(HelpType.values()));
        mv.addObject("helpSolicitation", solicitation);
        return mv;
    }

}
