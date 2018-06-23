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
public class OffersController {

    @RequestMapping("/offers")
    public String index(Model model) {
        model.addAttribute("title", "Ofertas");
        model.addAttribute("page", "offers/index");
        return "app";
    }
    
    @RequestMapping("/offers/my")
    public String my(Model model){
        model.addAttribute("title", "Minhas ofertas");
        model.addAttribute("page", "offers/my");
        return "app";
    }
    
    
    @RequestMapping(value = "/offers/create", method = GET)
    public String create(Model model){
        model.addAttribute("title", "Oferecer Ajuda");
        model.addAttribute("page", "offers/help");
        return "app";
    }
    
    @RequestMapping(value = "/offers/store", method = POST)
    public String store(Model model){
        return "";
    }
    
    @RequestMapping(value = "/offers/edit/{idOffer}", method = GET)
    public String edit(Model model){
        return "";
    }
    
    @RequestMapping(value = "/offers/update", method = POST)
    public String update(Model model){
        return "";
    }
    
    @RequestMapping(value = "/offers/delete/{idOffer}", method = GET)
    public String delete(Model model){
        return "";
    }
}