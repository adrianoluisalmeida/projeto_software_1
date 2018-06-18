package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.database.ConnectionFactory;
import br.com.pinmyhelp.model.Address;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/account/create", method = GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/account/store/person", method = POST)
    public String createPerson(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        User u = new User(person.getEmail(), person.getPassword());
        // create one single connection for two inserts
        ConnectionFactory.openConnection();
        person.setId(userDAO.create(u));
        personDAO.create(person);
        ConnectionFactory.closeConnection();
        return redirectDashboard(model);
    }

    @RequestMapping(value = "/account/store/entity", method = POST)
    public String createEntity(@Valid Entity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        User u = new User(entity.getEmail(), entity.getPassword());
        // create one single connection for two inserts
        ConnectionFactory.openConnection();
        entity.setId(userDAO.create(u));
        entityDAO.create(entity);
        ConnectionFactory.closeConnection();
        return redirectDashboard(model);
    }

    @RequestMapping(value = "/account/edit/person/{idPerson}", method = GET)
    public String editPerson(@PathVariable(value = "idPerson")int id, Model model) {
        Person person = personDAO.findOne(id);
        model.addAttribute("page", "account/edit");
        model.addAttribute("editPage", "person");
        model.addAttribute("person", person);
        return "app";
    }

    @RequestMapping(value = "/account/edit/entity/{idEntity}", method = GET)
    public String editEntity(@PathVariable(value = "idEntity")int id, Model model) {
        Entity entity = entityDAO.findOne(id);
        model.addAttribute("page", "account/edit");
        model.addAttribute("editPage", "entity");
        model.addAttribute("entity", entity);
        return "app";
    }

    @RequestMapping(value = "/account/update/person", method = POST)
    public String updatePerson(@Valid Person person, Address address, BindingResult result, Model model) {
        person.setAddress(address);
        if (result.hasErrors()) {
            model.addAttribute("page", "account/edit");
            model.addAttribute("editPage", "person");
            model.addAttribute("person", person);
            return "app";
        }
        // create one single connection for two inserts
        ConnectionFactory.openConnection();
        if (person.getPassword() != null){
            User user = userDAO.findOne(person.getId());
            user.setPassword(person.getPassword());
            userDAO.update(user);
        }
        personDAO.update(person);
        ConnectionFactory.closeConnection();
        return redirectDashboard(model);
    }

    @RequestMapping(value = "/account/update/entity", method = POST)
    public String updateEntity(@Valid Entity entity, Address address, BindingResult result, Model model) {
        entity.setAddress(address);
        if (result.hasErrors()){
            model.addAttribute("page", "acount/edit");
            model.addAttribute("editPage", "entity");
            model.addAttribute("entity", entity);
            return "app";
        }
        // single connection for all operations
        ConnectionFactory.openConnection();
        if (entity.getPassword() != null){
            User user = userDAO.findOne(entity.getId());
            user.setPassword(entity.getPassword());
            userDAO.update(user);
        }
        entityDAO.update(entity);
        ConnectionFactory.closeConnection();
        return redirectDashboard(model);
    }

    @RequestMapping(value = "/account/delete/person/{idPerson}", method = GET)
    public String deletePerson(@PathVariable(value = "idPerson")int id) {
        ConnectionFactory.openConnection();
        Person person = personDAO.findOne(id);
        User user = userDAO.findOne(id);
        //fazer uma trigger no banco pra apagar o resto das referencias antes
        personDAO.delete(person);
        userDAO.delete(user);
        ConnectionFactory.closeConnection();
        return "login";
    }

    @RequestMapping(value = "/account/delete/entity/{idEntity}", method = GET)
    public String deleteEntity(@PathVariable(value = "idEntity")int id) {
        ConnectionFactory.openConnection();
        Entity entity = entityDAO.findOne(id);
        User user = userDAO.findOne(id);
        //fazer uma trigger no banco pra apagar o resto das referencias antes
        entityDAO.delete(entity);
        userDAO.delete(user);
        ConnectionFactory.closeConnection();
        return "login";
    }

    private String redirectDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("page", "dashboard");
        return "app";
    }
    
    
}
