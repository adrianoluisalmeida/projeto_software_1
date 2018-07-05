package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.database.ConnectionManager;
import br.com.pinmyhelp.model.Claimant;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Feedback;
import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Message;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.FeedbackDAO;
import br.com.pinmyhelp.model.dao.HelpOfferDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.MessageDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.HelpType;
import br.com.pinmyhelp.model.types.Rating;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    HelpOfferDAO helpOfferDAO;

    @Autowired
    FeedbackDAO feedbackDAO;

    @Autowired
    MessageDAO messageDAO;

    @RequestMapping("/solicitations")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        String type = (String) session.getAttribute("type");
        if (type.equals(Person.TYPE_CLAIMANT)) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        mav.addObject("title", "Solicitações");
        mav.addObject("page", "solicitations/index");
        User u = (User) session.getAttribute("user");
        Collection<HelpSolicitation> solicitations;
        if (type.equals("Entity")) {
            solicitations = helpSolicitationDAO.find("claimant_id != ? ", u.getId());
        } else {
            solicitations = helpSolicitationDAO.find("solicitation_status != ? order by solicitation_created DESC limit 3", HelpStatus.CANCELADA.getId());
            Collection<HelpOffer> offers = helpOfferDAO.find("voluntary_id = ? ", ((User) session.getAttribute("user")).getId());

        }

        //se voluntário/entidade já ofertou ajuda em alguma solicitação, 
        Collection<HelpOffer> offers = helpOfferDAO.find("voluntary_id = ? ", u.getId());
        for (HelpOffer offer : offers) {
            for (HelpSolicitation solicitation : solicitations) {
                if ((offer.getHelpSolicitation().getId()).equals(solicitation.getId())) {
                    solicitation.setHelpOffer(offer);
                }
            }
        }
        Collection<HelpSolicitation> filteredSolicitations = new ArrayList<>();
        for (HelpSolicitation s : solicitations) {
            if (s.getStatus().getId() <= 2 || s.getHelpOffer() != null) {
                filteredSolicitations.add(s);
            }
        }
        mav.addObject("solicitations", filteredSolicitations);

        return mav;
    }

    @RequestMapping("/solicitations/my")
    public ModelAndView my(HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        if (session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        mav.addObject("title", "Meus Pedidos");
        mav.addObject("page", "solicitations/my");
        Collection<HelpSolicitation> helps = helpSolicitationDAO.findByClaimantId(((User) session.getAttribute("user")).getId(), null);
        helps.forEach(helpSolicitationDAO::setOffers);//utiliza o método setOffers do HelpSolicitationDAO em cada elemento da collection
        mav.addObject("solicitations", helps);
        return mav;
    }

    @RequestMapping(value = "/solicitations/create", method = GET)
    public ModelAndView create(HttpSession session) {
        return getModelCreatePage(session);
    }

    @RequestMapping(value = "/solicitations/store", method = POST)
    public ModelAndView store(@Valid HelpSolicitation help, BindingResult result, @ModelAttribute("type_id") String typeId, HttpSession session, RedirectAttributes redirectAttrs) {
        ModelAndView mv = getModelCreatePage(session);
        mv.addObject("address", help.getAddress());
        Boolean datesAreValid = validateDates(help.getStartDate(), help.getEndDate(), result, mv);
        Boolean helpTypeIsValid = validateHelpType(typeId, help, mv);
        if (result.hasErrors() || !datesAreValid || !helpTypeIsValid) {
            return mv;
        }
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        User user = (User) session.getAttribute("user");
        if (((String) session.getAttribute("type")).equals(Person.TYPE_CLAIMANT)) {
            help.setClaimant(new Claimant(user.getId()));
            Person person = (Person) session.getAttribute("person");
            // if claimant doesn't have any address, updt with the address of his first help solicitation
            if (person.getAddress().getPostalCode() == null) { // 
                person.setAddress(help.getAddress());
                personDAO.update(person);
                session.setAttribute("person", person);
            }
        } else {
            help.setEntity(new Entity(user.getId()));
        }
        helpSolicitationDAO.create(help);
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Solicitação criada com sucesso!");
        return new ModelAndView("redirect:/solicitations/my");
    }

    @RequestMapping(value = "/solicitations/edit/{idRequest}", method = GET)
    public ModelAndView edit(@PathVariable(value = "idRequest") int id, HttpSession session) {
        HelpSolicitation solicitation = helpSolicitationDAO.findOne(id);
        User user = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView("app");
        int idClaimant = solicitation.getClaimant() != null ? solicitation.getClaimant().getId() : solicitation.getEntity().getId();
        if (!(user.getId().equals(idClaimant))) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        mav = getModelEditPage(session, id, solicitation);
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
        mv.addObject("address", help.getAddress());
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

        help.setStatus(HelpStatus.SOLICITADA);
        helpSolicitationDAO.update(help);
        redirectAttrs.addFlashAttribute("msg_success", "Solicitação alterada com sucesso!");
        return new ModelAndView("redirect:/solicitations/my");
    }

    @RequestMapping(value = "/solicitations/delete/{idRequest}", method = GET)
    public ModelAndView delete(@PathVariable(value = "idRequest") int id, HttpSession session, RedirectAttributes redirectAttrs) {
        //helpSolicitationDAO.delete(new HelpSolicitation(id));
        HelpSolicitation help = helpSolicitationDAO.findOne(id);
        help.setStatus(HelpStatus.CANCELADA);
        helpSolicitationDAO.update(help);
        redirectAttrs.addFlashAttribute("msg_success", "Solicitação removida com sucesso!");
        return new ModelAndView("redirect:/solicitations/my");
    }

    @RequestMapping(value = "/solicitations/rate/{idRequest}", method = GET)
    public ModelAndView rate(@PathVariable(value = "idRequest") int id, HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        if (session.getAttribute("type") == null || session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        mav.addObject("title", "Avaliar ajuda");
        mav.addObject("page", "solicitations/rate");
        mav.addObject("action", "/solicitations/done");
        mav.addObject("ratings", Rating.getAll());
        mav.addObject("solicitationId", id);
        return mav;
    }

    @RequestMapping(value = "/solicitations/done", method = POST)
    public ModelAndView done(Feedback f, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttrs) {
        if (session.getAttribute("type") == null || session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            ModelAndView mav = new ModelAndView("app");
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        int solicitationId = Integer.valueOf(request.getParameter("solicitationId"));
        HelpSolicitation sol = helpSolicitationDAO.findOne(solicitationId);
        helpSolicitationDAO.setOffers(sol);
        User user = (User) session.getAttribute("user");
        f.setSender(user);
        f.setOffer(sol.getHelpOffer());
        feedbackDAO.create(f);
        sol.setStatus(HelpStatus.CONCLUIDA);
        helpSolicitationDAO.update(sol);
        Message message = new Message();
        String prefix = "?";
        String sender = "?";
        if (sol.getClaimant() != null) {
            prefix = "O requerente: ";
            sender = sol.getClaimant().getName();
        } else if (sol.getEntity() != null) {
            prefix = "A entidade: ";
            sender = sol.getEntity().getName();
        }
        if (f.getOffer() != null) {
            HelpOffer offer = f.getOffer();
            if (offer.getVoluntary() != null) {
                message.setUser(offer.getVoluntary());
            } else if (offer.getEntity() != null) {
                message.setUser(offer.getEntity());
            }
        }
        message.setTitle("Você recebeu uma avaliação!");
        message.setContent(String.format("%s%s avaliou sua ajuda com nota %d", prefix, sender, f.getRating().getValue()));
        messageDAO.create(message);
        redirectAttrs.addFlashAttribute("msg_success", "Feedback enviado!");
        return new ModelAndView("redirect:/dashboard");
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
        if (session.getAttribute("type").equals(Person.TYPE_CLAIMANT)) {
            Person p = (Person) session.getAttribute("person");
            mv.addObject("address", p.getAddress());
        } else {
            Entity e = (Entity) session.getAttribute("entity");
            mv.addObject("address", e.getAddress());
        }
        return mv;
    }

    private ModelAndView getModelEditPage(HttpSession session, Integer id, HelpSolicitation solicitation) {
        ModelAndView mv = new ModelAndView("app");
        mv.addObject("page", "solicitations/edit");
        mv.addObject("id", id);
        mv.addObject("helpTypes", Arrays.asList(HelpType.values()));
        mv.addObject("helpSolicitation", solicitation);
        mv.addObject("address", solicitation.getAddress());
        return mv;
    }

}
