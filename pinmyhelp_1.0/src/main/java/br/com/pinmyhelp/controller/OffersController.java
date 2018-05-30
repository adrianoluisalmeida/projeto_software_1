package br.com.pinmyhelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author adriano
 */
@Controller
public class OffersController {

    @RequestMapping("/offers")
    public String index(Model model) {
        model.addAttribute("title", "Ofertas");
        model.addAttribute("page", "offers/index");
        return "app";
    }
    
}