package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.UserDAO;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(Model model, HttpSession session) {
        System.out.println("redirect");
        User user = (User)session.getAttribute("user");
        if ( user == null ) // TODO: validation with interceptor
            return "login";
        model.addAttribute("title", "Dashboard");
        model.addAttribute("page", "dashboard");
        return "app";
    }
    
}