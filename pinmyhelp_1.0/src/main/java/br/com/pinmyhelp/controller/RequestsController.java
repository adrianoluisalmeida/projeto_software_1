package br.com.pinmyhelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author adriano
 */
@Controller
public class RequestsController {

    @RequestMapping("/requests")
    public String index(Model model) {
        model.addAttribute("title", "Solicitações");
        model.addAttribute("page", "requests/index");
        return "app";
    }
    
    @RequestMapping("/requests/help")
    public String help(Model model){
        model.addAttribute("title", "Oferecer Ajuda");
        model.addAttribute("page", "requests/help");
        return "app";
    }
    
    @RequestMapping("/requests/my")
    public String my(Model model){
        model.addAttribute("title", "Meus Pedidos");
        model.addAttribute("page", "requests/my");
        return "app";
    }
    
    @RequestMapping("/requests/create")
    public String create(Model model){
        model.addAttribute("title", "Nova solicitação de Ajuda");
        model.addAttribute("page", "requests/create");
        return "app";
    }
    
}