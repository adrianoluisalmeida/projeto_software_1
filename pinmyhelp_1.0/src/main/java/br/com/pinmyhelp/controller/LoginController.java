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
        session.setAttribute("registered_email", user.getEmail());
        if ( result.hasErrors() ) return new ModelAndView("login");
        User loggedUser = userDAO.autenticateUser(user);
        if ( loggedUser == null ) return redirectLogin(user);
        session.setAttribute("user", loggedUser);
        Person person = personDAO.findOne(loggedUser.getId());
        if (person != null){
            session.setAttribute("person", person);
            session.setAttribute("type", person.getType());
        } else {
            Entity entity = entityDAO.findOne(loggedUser.getId());
            if (entity != null){
                session.setAttribute("entity", entity);
                session.setAttribute("type", "Entity");
            } else {
                return new ModelAndView("login");
            }
        }
        return new ModelAndView("redirect:/dashboard");
    }
    
    public ModelAndView redirectLogin(User user){
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("login_error", "E-mail ou senha incorretos.");
        mv.addObject("user_email", user.getEmail());
        return mv;
    }
    
    public ModelAndView redirectLogin(){
        return new ModelAndView("redirect:/login");
    }
        
    @RequestMapping(value = "sign-out", method = GET)
    public String signOut(HttpSession session) {
        session.invalidate();
        return "login";
    }
    
}