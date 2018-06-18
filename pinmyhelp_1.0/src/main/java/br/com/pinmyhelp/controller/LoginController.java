/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author rhau
 */
@Controller
public class LoginController {
    
    @Autowired
    UserDAO userDAO;
    
    @RequestMapping(value = "sign-in", method = POST)
    public String index(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if ( result.hasErrors() || !userDAO.autenticate(user) )
                return "login";
        session.setAttribute("user", user);
        return "redirect:dashboard";
    }
    
    @RequestMapping(value = "sign-out", method = GET)
    public String index(HttpSession session) {
        session.invalidate();
        return "login";
    }
    
}