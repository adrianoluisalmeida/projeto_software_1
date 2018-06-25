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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView my(HttpSession session){
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("title", "Meus Pedidos");
        mav.addObject("page", "requests/my");
        
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        User u = (User) session.getAttribute("user");
        HelpSolicitation h = new HelpSolicitation();
        Collection<HelpSolicitation> myRequests = new ArrayList<>();
        if(session.getAttribute("type").equals("Claimant")) 
            h.setClaimant((Person) personDAO.findOne(u.getId()));
        //Ainda não testei usando a entidade como requerente
        else if(session.getAttribute("type").equals("Entity"))
            h.setEntity((Entity) entityDAO.findOne(u.getId())); 
        myRequests = helpSolicitationDAO.find(h);
        mav.addObject("myRequests", myRequests);
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();        
        return mav;
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
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        Collection<HelpType> HelpTypes = new ArrayList<>();
        HelpTypes.addAll(Arrays.asList(HelpType.values()));
        mav.addObject("HelpTypes", HelpTypes);
        mav.addObject("title", "Nova solicitação de Ajuda");
        mav.addObject("page", "requests/create");
        return mav;
    }
    
        
    @RequestMapping(value = "/requests/store", method = POST)
    public ModelAndView store(@Valid HelpSolicitation help, GeoLocation location, @RequestParam("idType") int idType, @RequestParam("start-date") String startDate, @RequestParam("end-date") String endDate, BindingResult result, HttpSession session){  
        if (result.hasErrors()) {
           return create(session);
        }
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        User u = (User) session.getAttribute("user");
        if(session.getAttribute("type").equals("Claimant"))
            help.setClaimant((Person) personDAO.findOne(u.getId()));
        //Ainda não testei usando a entidade como requerente (no BD só tem um campo claimant_id)
        //Então no HelpSolicitationDAO só insere em claimant_id o id que pega de claimant
        //Tem que mudar em HelpSolicitationDAO para que insira o id de entidade quando o tipo é entity
        else if(session.getAttribute("type").equals("Entity"))
            help.setEntity((Entity) entityDAO.findOne(u.getId()));
        help.setLocation(location);
        help.setType(HelpType.get(idType));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        help.setStartDate(LocalDate.parse(startDate, formatter));
        help.setEndDate(LocalDate.parse(endDate, formatter));
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