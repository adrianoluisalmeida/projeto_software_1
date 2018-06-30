package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author adriano
 */
@Controller
public class OffersController {
    
    @Autowired
    HelpSolicitationDAO helpSolicitationDAO;


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
    
    
    @RequestMapping("/offers/help/{solicitation_id}")
    public ModelAndView help(HttpSession session, @PathVariable(value = "solicitation_id") int solicitation_id) {
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("title", "Solicitações - Oferecer ajuda");
        mav.addObject("page", "offers/help");
        mav.addObject("solicitation", helpSolicitationDAO.findOne(solicitation_id));
        return mav;
    }
    
    @RequestMapping(value = "/offers/store/{solicitation_id}", method = POST)
    public ModelAndView store(HttpSession session, @PathVariable(value = "solicitation_id") int solicitation_id) {
        ModelAndView mav = new ModelAndView("app");
        return mav; 
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