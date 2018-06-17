package br.com.pinmyhelp.controller;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.util.ConnectionFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Adriano
 */

@Controller
public class AccountController {
    
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    PersonDAO personDAO;
    
    @Autowired
    EntityDAO entityDAO;
    
    @RequestMapping(value = "/account/register", method = GET)
    public String register() {
        return "register";
    }
    
    @RequestMapping(value = "/account/create/person",  method = POST)
    public String createPerson(@Valid Person person, BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            return "register";
        }
        User u = new User(person.getEmail(), person.getPassword());
        //Criar apenas uma conexão para as 2 insersões
        ConnectionFactory.openConnection();
        person.setId(userDAO.create(u));        
        personDAO.create(person);
        ConnectionFactory.closeConnection();
        return redirectDashboard(model);
    }
    

    @RequestMapping(value = "/account/create/entity", method = POST)
    public String createEntity(@Valid Entity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        User u = new User(entity.getEmail(), entity.getPassword());
        ConnectionFactory.openConnection();
        entity.setId(userDAO.create(u));        
        entityDAO.create(entity);
        ConnectionFactory.closeConnection();
        return redirectDashboard(model);
    }

    private String redirectDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("page", "dashboard");
        return "app";
    }
    
}