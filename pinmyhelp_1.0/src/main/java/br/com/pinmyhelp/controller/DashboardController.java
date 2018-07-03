package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import com.google.gson.Gson;

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
        ModelAndView mav = new ModelAndView("app");
        String type = (String) session.getAttribute("type");
        String pageDashboard = "entity/dashboard";
        if (type.equals(Person.TYPE_CLAIMANT) || type.equals("Entity")) {
            if(type.equals(Person.TYPE_CLAIMANT))
                pageDashboard = "claimant/dashboard";
            else if ( type.equals("Entity") ) 
                pageDashboard = "entity/dashboard";
            //consulta solicitações
            mav.addObject("solicitations", helpSolicitationDAO.findByClaimantId(((User) session.getAttribute("user")).getId(), 3));
            //converte para json, para utilizar no mapa
            Gson gson = new Gson(); 
            String userJSONString = gson.toJson(helpSolicitationDAO.findByClaimantId(((User) session.getAttribute("user")).getId(), 3)); 
            mav.addObject("gson", userJSONString);
        } else if ( type.equals(Person.TYPE_VOLUNTARY) ) {
            pageDashboard = "voluntary/dashboard";   
            mav.addObject("solicitations", helpSolicitationDAO.findAll());
            
            Gson gson = new Gson(); 
            String userJSONString = gson.toJson(helpSolicitationDAO.findAll()); 
            mav.addObject("gson", userJSONString);
        } 
        mav.addObject("title", "Dashboard");
        mav.addObject("page", pageDashboard);
        return mav;
    }

}