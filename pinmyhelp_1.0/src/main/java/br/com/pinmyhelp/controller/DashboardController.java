package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.HelpOfferDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.MessageDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.model.types.HelpStatus;
import com.google.gson.Gson;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author adriano
 */
@Controller
public class DashboardController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PersonDAO personDAO;

    @Autowired
    EntityDAO entityDAO;

    @Autowired
    HelpSolicitationDAO helpSolicitationDAO;

    @Autowired
    HelpOfferDAO helpOfferDAO;
    
    @Autowired
    MessageDAO messageDAO;

    @RequestMapping("/dashboard")
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) { // TODO: validation with interceptor
            session.invalidate();
            return new ModelAndView("redirect:/login");
        }
        return redirectDashboard(session);
    }

    public ModelAndView redirectDashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView("app");
        String type = (String) session.getAttribute("type");
        String pageDashboard = "entity/dashboard";
        Collection<HelpSolicitation> solicitations = helpSolicitationDAO.findByClaimantId(((User) session.getAttribute("user")).getId(), 3);
        solicitations.forEach(helpSolicitationDAO::setOffers);
        if (type.equals(Person.TYPE_CLAIMANT) || type.equals("Entity")) {
            //consulta MINHAS solicitações
            mav.addObject("solicitations", solicitations);
            if (type.equals(Person.TYPE_CLAIMANT)) {
                pageDashboard = "claimant/dashboard";
            } else if (type.equals("Entity")) {
                pageDashboard = "entity/dashboard";
                //Consulta TODAS solicitações
                //converte para json, para utilizar no mapa
                Gson gson = new Gson();
                String userJSONString = gson.toJson(helpSolicitationDAO.find("claimant_id != ? ", ((User) session.getAttribute("user")).getId()));
                mav.addObject("gson", userJSONString);
            }
        } else if (type.equals(Person.TYPE_VOLUNTARY)) {
            pageDashboard = "voluntary/dashboard";
            solicitations = helpSolicitationDAO.find("solicitation_status != ? order by solicitation_created DESC limit 3",  HelpStatus.CANCELADA.getId());
            Collection<HelpOffer> offers = helpOfferDAO.find("voluntary_id = ? ", ((User) session.getAttribute("user")).getId());
            for (HelpOffer offer : offers) {
                for (HelpSolicitation solicitation : solicitations) {
                    if ((offer.getHelpSolicitation().getId()).equals(solicitation.getId())) {
                        solicitation.setHelpOffer(offer);
                    }
                }
            }
            mav.addObject("solicitations", solicitations);

            Gson gson = new Gson();
            String userJSONString = gson.toJson(helpSolicitationDAO.find("solicitation_status != ?",  HelpStatus.CANCELADA.getId()));
            mav.addObject("gson", userJSONString);
        }
        mav.addObject("title", "Dashboard");
        mav.addObject("page", pageDashboard);
        session.setAttribute("new_messages", messageDAO.findMessages(user.getId(), false)); // isReaded = false
        session.setAttribute("old_messages", messageDAO.findMessages(user.getId(), true));   // isReaded = true
        return mav;
    }
}