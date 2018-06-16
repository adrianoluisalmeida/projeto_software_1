package br.com.pinmyhelp.controller;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    PersonDAO EntityDAO;
        
    @RequestMapping(value = "/account/register", method = GET)
    public String register() {
        return "register";
    }
    
    @RequestMapping(value = "/account/create/person",  method = POST)
    public String createPerson(@Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        User u = new User(person.getEmail(), person.getPassword());
        person.setId(userDAO.create(u));
        personDAO.create(person);
        return "login";
    }
    
    @RequestMapping(value = "/account/create/entity", method = POST)
    public String createEntity() {
        return "";
    }
}
