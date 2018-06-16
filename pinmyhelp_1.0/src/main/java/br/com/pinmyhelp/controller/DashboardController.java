package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.UserDAO;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author adriano
 */
@Controller
public class DashboardController {
    
    @Autowired 
    UserDAO userDAO;

    @RequestMapping("/dashboard")
    public String index(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if ( result.hasErrors() || !userDAO.autenticate(user))
            return "login";
   
        session.setAttribute("user", user);
        model.addAttribute("title", "Dashboard");
        model.addAttribute("page", "dashboard");
        return "app";
    }
    
}