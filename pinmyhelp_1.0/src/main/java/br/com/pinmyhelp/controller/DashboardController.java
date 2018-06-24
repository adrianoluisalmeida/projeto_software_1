package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.UserDAO;
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

    @RequestMapping("/dashboard")
    public ModelAndView index(HttpSession session) {
        System.out.println("redirect");
        User user = (User)session.getAttribute("user");
        if ( user == null ) // TODO: validation with interceptor
            return new ModelAndView("redirect:/login");
        return redirectDashboard(session);
    }
    
    public ModelAndView redirectDashboard(HttpSession session){
        ModelAndView mav = new ModelAndView("app");
        String pageDashboard = "voluntary/dashboard";
        if(session.getAttribute("type").equals("Claimant"))
            pageDashboard = "claimant/dashboard";
        mav.addObject("title","Dashboard");
        mav.addObject("page", pageDashboard);
        return mav;
    }
    

    
}