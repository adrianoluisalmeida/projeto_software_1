/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rhau
 */
@Controller
public class LoginController {
    
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    PersonDAO personDAO;
    
    @Autowired  
    EntityDAO entityDAO;
    
    @RequestMapping(value = "sign-in", method = POST)
    public ModelAndView index(@Valid User user, BindingResult result, HttpSession session) {
        if ( result.hasErrors() || !userDAO.autenticate(user) )
            return redirectLogin();
        User loggedUser = userDAO.findOne(user.getEmail(), user.getPassword());
        session.setAttribute("user", loggedUser);
        Person person = personDAO.findOne(loggedUser.getId());
        if (person != null){
            session.setAttribute("type", person.getType());
            session.setAttribute("picture", person.getProfilePicture());
        }else{
            Entity entity = entityDAO.findOne(loggedUser.getId());
            if (entity != null){
                session.setAttribute("type", "Entity");
                session.setAttribute("picture", entity.getLogo());
            }
        }
        return new DashboardController().redirectDashboard(session);
    }
    
    public ModelAndView redirectLogin(){
        return new ModelAndView("redirect:/login");
    }
    
    @RequestMapping(value = "sign-out", method = GET)
    public String index(HttpSession session) {
        session.invalidate();
        return "login";
    }
    
}