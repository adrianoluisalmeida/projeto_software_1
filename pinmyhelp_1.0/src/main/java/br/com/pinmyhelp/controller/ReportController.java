/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.HelpType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author roger
 */
@Controller
public class ReportController {
    
    @Autowired
    UserDAO userDAO;

    @Autowired
    PersonDAO personDAO;

    @Autowired
    EntityDAO entityDAO;
    
    @Autowired
    HelpSolicitationDAO helpDAO;
    
    @RequestMapping("/report/test")
    public ModelAndView test(HttpSession session){
        if (!checkUser(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("pages/report/test");
        return mav;
    }

    @RequestMapping("/report/voluntaries")
    public ModelAndView listVoluntaries(HttpSession session){
        if (!checkUser(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("pages/report/persons");
        List<Person> persons = personDAO.find("person_type = ?", "Voluntary");
        mav.addObject("title","Listagem de voluntários");
        mav.addObject("persons", persons);
        return mav;
    }
    
    @RequestMapping("/report/claimants")
    public ModelAndView listClaimants(HttpSession session){
        if (!checkUser(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("pages/report/persons");
        List<Person> persons = personDAO.find("person_type = ?", "Claimant");
        mav.addObject("title","Listagem de requerentes");
        mav.addObject("persons", persons);
        return mav;
    }
    
    @RequestMapping("/report/entities")
    public ModelAndView listEntities(HttpSession session){
        if (!checkUser(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("pages/report/entities");
        List<Entity> entities = entityDAO.findAll();
        mav.addObject("title","Listagem de entidades");
        mav.addObject("persons", entities);
        return mav;
    }
    
    @RequestMapping("/report/solicitations")
    public ModelAndView listSolicitations(HttpServletRequest request, HttpSession session){
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<HelpSolicitation> list = helpDAO.find("start_date >= ? and start_date <= ?", new String[]{startDate, endDate});
        ModelAndView mov = new ModelAndView("pages/report/solicitations");
        mov.addObject("title", "Solicitações de ajuda");
        mov.addObject("solicitations",list);
        mov.addObject("solicitadas",list.stream().filter(s -> s.getStatus() == HelpStatus.SOLICITADA).count());
        mov.addObject("encerradas",list.stream().filter(s -> s.getStatus() == HelpStatus.ENCERRRADA).count());
        mov.addObject("comInteressados",list.stream().filter(s -> s.getStatus() == HelpStatus.INTERESSE).count());
        mov.addObject("canceladas",list.stream().filter(s -> s.getStatus() == HelpStatus.CANCELADA).count());
        return mov;
    };
    
    public boolean checkUser(HttpSession session) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            System.out.println("Checando admin - id: " + user.getId());
            if (user.isAdmin()) {
                return true;
            }
            
        }
        System.err.println("Usuario não pode fazer essa operação!");
        session.invalidate();
        return false;
    }
    
}
