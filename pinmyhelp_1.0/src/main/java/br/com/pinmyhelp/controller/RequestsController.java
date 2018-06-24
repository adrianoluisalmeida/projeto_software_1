package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.database.ConnectionManager;
import br.com.pinmyhelp.model.Address;
import br.com.pinmyhelp.model.Claimant;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.types.GeoLocation;
import br.com.pinmyhelp.model.types.HelpType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author adriano
 */
@Controller
public class RequestsController {
    
    @Autowired
    PersonDAO personDAO;

    @Autowired
    EntityDAO entityDAO;
    
    @Autowired
    HelpSolicitationDAO helpSolicitationDAO;

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
    public ModelAndView create(HttpSession session){
        ModelAndView mav = new ModelAndView("app");
        ConnectionManager.openConnection();
        User user = (User) session.getAttribute("user");
        Entity entity = null;
        Person person = null;
        if(session.getAttribute("type").equals("Entity")){
           entity = entityDAO.findOne(user.getId());
           mav.addObject("claimant", entity);
        } else {
           person = personDAO.findOne(user.getId());
           mav.addObject("claimant", person);
        }
        Collection<HelpType> HelpTypes = new ArrayList<>();
        HelpTypes.addAll(Arrays.asList(HelpType.values()));
        mav.addObject("HelpTypes", HelpTypes);
        mav.addObject("title", "Nova solicitação de Ajuda");
        mav.addObject("page", "requests/create");
        return mav;
    }
    
        
    @RequestMapping(value = "/requests/store", method = POST)
    public ModelAndView store(@Valid HelpSolicitation help, GeoLocation location, HelpType helpType, Claimant claimant, Entity entity, BindingResult result, HttpSession session){  
        if (result.hasErrors()) {
           return create(session);
        }
        if(claimant != null)
            help.setClaimant(claimant);
        if(entity != null)
            help.setEntity(entity);
        help.setLocation(location);
        help.setType(helpType);
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        helpSolicitationDAO.create(help);
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        return new DashboardController().redirectDashboard(session);
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