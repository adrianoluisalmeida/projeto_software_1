package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.HelpOfferDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.OfferStatus;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/offers/{solicitation_id}")
    public ModelAndView offersSolicitation(HttpSession session, @PathVariable(value = "solicitation_id") int solicitation_id) {
        ModelAndView mav = new ModelAndView("app");
        if (session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            mav.addObject("title", "Acesso negado");
            mav.addObject("page", "accessDenied");
            return mav;
        }
        User user = (User) session.getAttribute("user");
        mav.addObject("title", "Ofertas");
        mav.addObject("page", "offers/index");
        mav.addObject("offers", helpOfferDAO.find("solicitation_id = ?", solicitation_id));
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
    
    @RequestMapping("/offers/offer/{offer_id}")
    public ModelAndView seeHelp(HttpSession session, @PathVariable(value = "offer_id") int offer_id) {
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("title", "Solicitações - Visualizar oferta ajuda");
        mav.addObject("page", "offers/view_offer");
        mav.addObject("offer", helpOfferDAO.findOne(offer_id));
        return mav;
    }

    @RequestMapping(value = "/offers/store/{solicitation_id}", method = POST)
    public ModelAndView store(@Valid HelpOffer helpOffer, BindingResult result, HttpSession session, @PathVariable(value = "solicitation_id") int solicitation_id, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("msg_error", "Opa, ocorreu algum problema...");
            return new ModelAndView("redirect:/offers/help/" + solicitation_id);
        }
        if (session.getAttribute("type").equals(Person.TYPE_VOLUNTARY)) {
            helpOffer.setVoluntary((Person) session.getAttribute("person"));
        } else if (session.getAttribute("type").equals("Entity")) {
            helpOffer.setEntity((Entity) session.getAttribute("entity"));
        }
        helpOffer.setStatus(OfferStatus.OFERTADA);
        helpOffer.setHelpSolicitation(new HelpSolicitation(solicitation_id));
        if (helpOfferDAO.create(helpOffer) != null) {
            HelpSolicitation helpSolicitation = helpSolicitationDAO.findOne(helpOffer.getHelpSolicitation().getId());
            if (helpSolicitation != null) {
                helpSolicitation.setStatus(HelpStatus.INTERESSE);
                helpSolicitationDAO.update(helpSolicitation);
            }
            redirectAttrs.addFlashAttribute("msg_success", "Oferta realizada com sucesso!");
        }
        return new ModelAndView("redirect:/offers/my");
    }
    
    @RequestMapping(value = "/offers/approve/{offer_id}", method = GET)
    public ModelAndView approve(@PathVariable(value = "offer_id") int offer_id, RedirectAttributes redirectAttrs) {
        HelpOffer helpOffer = helpOfferDAO.findOne(offer_id);
        if (helpOffer != null) {
            HelpSolicitation helpSolicitation = helpSolicitationDAO.findOne(helpOffer.getHelpSolicitation().getId());
            if (helpSolicitation != null) {
                helpOffer.setStatus(OfferStatus.APROVADA);
                helpOfferDAO.update(helpOffer);
                helpSolicitation.setStatus(HelpStatus.ENCERRADA);
                helpSolicitationDAO.update(helpSolicitation);
                redirectAttrs.addFlashAttribute("msg_success", "Oferta aprovada com sucesso!");
                //Se aprova uma oferta, todas outras dessa solicitação devem ser rejeitadas
                Object[] params = {offer_id, helpSolicitation.getId()};
                Collection<HelpOffer> othersOffers = helpOfferDAO.find("offer_id != ? and solicitation_id = ?", params);
                for (HelpOffer offer : othersOffers) {
                    offer.setStatus(OfferStatus.REJEITADA);
                    helpOfferDAO.update(offer); // da pra criar um update só na tbl oferta, filtrado pelo id da solicitacao
                }
            }
        }
        return new ModelAndView("redirect:/offers");
    }

    @RequestMapping(value = "/offers/reject", method = POST)
    public ModelAndView reject(@Valid HelpOffer offer, BindingResult result, @RequestParam("offer_id") int offer_id, RedirectAttributes redirectAttrs) {
        HelpOffer helpOffer = helpOfferDAO.findOne(offer_id);
        if (helpOffer != null) {
            helpOffer.setStatus(OfferStatus.REJEITADA);
            helpOfferDAO.update(helpOffer);
            redirectAttrs.addFlashAttribute("msg_success", "Oferta rejeitada com sucesso!");
        }
        return new ModelAndView("redirect:/offers");
    }

    @RequestMapping(value = "/offers/cancel/{offer_id}", method = GET)
    public ModelAndView delete(@PathVariable(value = "offer_id") int offer_id, RedirectAttributes redirectAttrs) {
        HelpOffer helpOffer = helpOfferDAO.findOne(offer_id);
        helpOffer.setStatus(OfferStatus.CANCELADA);
        helpOfferDAO.update(helpOffer);
        redirectAttrs.addFlashAttribute("msg_success", "Oferta cancelada com sucesso!");      
        return new ModelAndView("redirect:/offers/my");
    }
    
    @RequestMapping(value = "/offers/open/{offer_id}", method = GET)
    public ModelAndView reOpen(@PathVariable(value = "offer_id") int offer_id, RedirectAttributes redirectAttrs) {
        HelpOffer helpOffer = helpOfferDAO.findOne(offer_id);
        helpOffer.setStatus(OfferStatus.OFERTADA);
        helpOfferDAO.update(helpOffer);
        redirectAttrs.addFlashAttribute("msg_success", "Eba! Oferta reaberta com sucesso!");      
        return new ModelAndView("redirect:/offers/my");
    }

}
