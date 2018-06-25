package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.database.ConnectionManager;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/dashboard")
    public ModelAndView index(HttpSession session) {
        System.out.println("redirect");
        User user = (User) session.getAttribute("user");
        if (user == null) // TODO: validation with interceptor
        {
            return new ModelAndView("redirect:/login");
        }
        return redirectDashboard(session);
    }

    public ModelAndView redirectDashboard(HttpSession session) {
        ModelAndView mav = new ModelAndView("app");
        String pageDashboard = "voluntary/dashboard";
        if (session.getAttribute("type").equals("Claimant")) {
            pageDashboard = "claimant/dashboard";
        //Não está funcionando (não funciona o método personDAO.findOne() - SEM MOTIVOS    
  /*        Person p = null;
            ConnectionManager.openConnection();
            ConnectionManager.beginTransaction();
            User u = (User) session.getAttribute("user");
            HelpSolicitation h = new HelpSolicitation();
            Collection<HelpSolicitation> myRequests = new ArrayList<>();
            if (session.getAttribute("type").equals("Claimant")) {
                p = personDAO.findOne(u.getId());
                System.out.println("user id: " + p.getId());
                h.setClaimant(p);
            } else if (session.getAttribute("type").equals("Entity")) {
                h.setEntity((Entity) entityDAO.findOne(u.getId()));
            }
            System.out.println("User help id+ " + h.getClaimant().getId());
            myRequests = helpSolicitationDAO.find(h);
            mav.addObject("myRequests", myRequests);
            ConnectionManager.commitTransaction();
            ConnectionManager.closeConnection();*/
        }
        mav.addObject("title", "Dashboard");
        mav.addObject("page", pageDashboard);
        return mav;
    }

}
