/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Feedback;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.FeedbackDAO;
import br.com.pinmyhelp.model.dao.HelpSolicitationDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.model.types.HelpStatus;
import java.util.List;
import java.util.stream.Collectors;
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
    
    @Autowired
    FeedbackDAO feedbackDAO;
    
    @RequestMapping("/report")
    public ModelAndView index(HttpSession session){
        if (!checkUser(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("page", "report/index");
        return mav;
    }
    
    @RequestMapping("/report/generate")
    public ModelAndView generate(HttpServletRequest request, HttpSession session){
        if (!checkUser(session)){
            return new ModelAndView("redirect:/login");
        }
        int type = Integer.valueOf(request.getParameter("type"));
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        switch (type){
            case 1: return listClaimants(name);
            case 2: return listVoluntaries(name);
            case 3: return listEntities(name);
            case 4: return listSolicitations(name, startDate, endDate);
            case 5: return listFeedbacks(name, startDate, endDate);        
        }
        //TODO change this
        return new ModelAndView("redirect:/dashboard");
    }

    private String getFindString(String string){
        StringBuilder builder = new StringBuilder();
        builder.append('%');
        builder.append(string);
        builder.append('%');
        return builder.toString();
    }
    
    private ModelAndView listVoluntaries(String name){
        ModelAndView mav = new ModelAndView("pages/report/persons");
        List<Person> persons = personDAO.find("person_type = ? AND person_name like ?", new String[]{"Voluntary",getFindString(name)});
        mav.addObject("title","Listagem de voluntários");
        mav.addObject("persons", persons);
        mav.addObject("name", name);
        return mav;
    }
    
    private ModelAndView listClaimants(String name){
        ModelAndView mav = new ModelAndView("pages/report/persons");
        List<Person> persons = personDAO.find("person_type = ? AND person_name like ?", new String[]{"Claimant",getFindString(name)});
        mav.addObject("title","Listagem de requerentes");
        mav.addObject("persons", persons);
        mav.addObject("name", name);
        return mav;
    }
    
    private ModelAndView listEntities(String name){
        ModelAndView mav = new ModelAndView("pages/report/entities");
        List<Entity> entities = entityDAO.find("entity_name like ?", getFindString(name));
        mav.addObject("title","Listagem de entidades");
        mav.addObject("persons", entities);
        mav.addObject("name", name);
        return mav;
    }
    
    private ModelAndView listSolicitations(String name, String startDate, String endDate){
        List<HelpSolicitation> list = helpDAO.find("start_date >= ? and start_date <= ?", new String[]{startDate, endDate});
        if (name.trim().length() > 0){
            //Utiliza streams pra filtrar a pesquisa ao invéz de pesquisar no banco, para não ter que mudar o sql no DAO
            list = list.stream().filter(a -> (a.getClaimant() != null && a.getClaimant().getName().toUpperCase().contains(name.toUpperCase())) ||
                (a.getEntity() != null && a.getEntity().getName().toUpperCase().contains(name.toUpperCase()))).collect(Collectors.toList());
        }
        ModelAndView mav = new ModelAndView("pages/report/solicitations");
        mav.addObject("title", "Solicitações de ajuda");
        mav.addObject("solicitations",list);
        mav.addObject("name", name);
        mav.addObject("solicitadas",list.stream().filter(s -> s.getStatus() == HelpStatus.SOLICITADA).count());
        mav.addObject("encerradas",list.stream().filter(s -> s.getStatus() == HelpStatus.ENCERRADA).count());
        mav.addObject("comInteressados",list.stream().filter(s -> s.getStatus() == HelpStatus.INTERESSE).count());
        mav.addObject("canceladas",list.stream().filter(s -> s.getStatus() == HelpStatus.CANCELADA).count());
        mav.addObject("concluidas",list.stream().filter(s -> s.getStatus() == HelpStatus.CONCLUIDA).count());
        mav.addObject("avaliadas",list.stream().filter(s -> s.getStatus() == HelpStatus.AVALIADA).count());
        return mav;
    };
    
    private ModelAndView listFeedbacks(String name, String startDate, String endDate){
        List<Feedback> list = feedbackDAO.find("feedback_created >= ? and feedback_created <= ?", new String[]{startDate, endDate});
        if (name.trim().length() > 0){
            //Utiliza streams pra filtrar a pesquisa ao invéz de pesquisar no banco, para não ter que mudar o sql no DAO
            list = list.stream().filter(a -> 
            (a.getSender() != null && a.getSender().getEmail().toUpperCase().contains(name.toUpperCase()))
            || (a.getOffer().getVoluntary() != null && a.getOffer().getVoluntary().getName().toUpperCase().contains(name.toUpperCase()))
            || (a.getOffer().getEntity() != null && a.getOffer().getEntity().getName().toUpperCase().contains(name.toUpperCase()))
            || (a.getComments().toUpperCase().contains(name.toUpperCase()))
            ).collect(Collectors.toList());
        }
        ModelAndView mav = new ModelAndView("pages/report/feedbacks");
        mav.addObject("title", "Feedbacks");
        mav.addObject("feedbacks",list);
        mav.addObject("name", name);
        return mav;
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
