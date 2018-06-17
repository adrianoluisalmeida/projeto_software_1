package br.com.pinmyhelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
   
    
    @RequestMapping("/requests/my")
    public String my(Model model){
        model.addAttribute("title", "Meus Pedidos");
        model.addAttribute("page", "requests/my");
        return "app";
    }
    
    @RequestMapping(value = "/requests/create", method = GET)
    public String create(Model model){
        model.addAttribute("title", "Nova solicitação de Ajuda");
        model.addAttribute("page", "requests/create");
        return "app";
    }
    
        
    @RequestMapping(value = "/requests/store", method = POST)
    public String store(Model model){
        return "";
    }
    
    @RequestMapping(value = "/requests/edit/{idRequest}", method = GET)
    public String edit(Model model){
        return "";
    }
    
    @RequestMapping(value = "/requests/update", method = POST)
    public String update(Model model){
        return "";
    }
    
    @RequestMapping(value = "/requests/delete/{idRequest}", method = GET)
    public String delete(Model model){
        return "";
    }
    
}