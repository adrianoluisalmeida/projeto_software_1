package br.csi.controller;


import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author adriano
 */
@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("page", "dashboard");
        return "app";
    }
}
