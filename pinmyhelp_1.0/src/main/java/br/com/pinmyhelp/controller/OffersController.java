package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.HelpOfferDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.types.OfferStatus;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class OffersController {

    @Autowired
    HelpSolicitationDAO helpSolicitationDAO;

    @Autowired
    HelpOfferDAO helpOfferDAO;

    @RequestMapping("/offers")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        if (session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        User user = (User) session.getAttribute("user");
        mav.addObject("title", "Ofertas");
        mav.addObject("page", "offers/index");
        mav.addObject("offers", helpOfferDAO.findByClaimantId(user.getId(), null));
        return mav;
    }

    @RequestMapping("/offers/my")
    public ModelAndView my(HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        if (session.getAttribute("type").equals(Person.TYPE_CLAIMANT)) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        User u = (User) session.getAttribute("user");
        mav.addObject("title", "Minhas ofertas");
        mav.addObject("offers", helpOfferDAO.find("voluntary_id = ? ", u.getId()));
        mav.addObject("page", "offers/my");
        return mav;
    }

    @RequestMapping("/offers/help/{solicitation_id}")
    public ModelAndView help(HttpSession session, @PathVariable(value = "solicitation_id") int solicitation_id) {
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("title", "Solicitações - Oferecer ajuda");
        mav.addObject("page", "offers/help");
        mav.addObject("solicitation", helpSolicitationDAO.findOne(solicitation_id));
        return mav;
    }

    @RequestMapping(value = "/offers/store/{solicitation_id}", method = POST)
    public ModelAndView store(@Valid HelpOffer helpOffer, BindingResult result, HttpSession session, @PathVariable(value = "solicitation_id") int solicitation_id, RedirectAttributes redirectAttrs) {
        System.out.println(helpOffer);
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("msg_error", "Opa, ocorreu algum problema...");
            return new ModelAndView("redirect:/offers/help/" + solicitation_id);
        }
        if (session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            helpOffer.setVoluntary((Person) session.getAttribute("person"));
        } else if (session.getAttribute("type").equals("Entity")) {
            helpOffer.setEntity((Entity) session.getAttribute("entity"));
        }
        System.out.println("ate aqui tudo bem");
        helpOffer.setStatus(OfferStatus.S1);
        helpOffer.setHelpSolicitation(new HelpSolicitation(solicitation_id));
        if(helpOfferDAO.create(helpOffer) != null)
            redirectAttrs.addFlashAttribute("msg_success", "Oferta realizada com sucesso!");
        return new ModelAndView("redirect:/offers/my");
    }

    @RequestMapping(value = "/offers/edit/{idOffer}", method = GET)
    public String edit(Model model) {
        return "";
    }

    @RequestMapping(value = "/offers/update", method = POST)
    public String update(Model model) {
        return "";
    }

    @RequestMapping(value = "/offers/delete/{idOffer}", method = GET)
    public String delete(Model model) {
        return "";
    }
}
