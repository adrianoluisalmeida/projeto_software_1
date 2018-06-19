package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.database.ConnectionFactory;
import br.com.pinmyhelp.model.Address;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
    public String createEntity(@Valid Entity entity, Address address, BindingResult result, Model model) {
        entity.setAddress(address);
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

    @RequestMapping(value = "/account/edit/profile/{id}", method = GET)
    public ModelAndView editProfile(@PathVariable(value="id") int id){
        ModelAndView mov = new ModelAndView("app");
        mov.addObject("page", "account/profile");
        Person person = personDAO.findOne(id);
        mov.addObject("person", person);
        return mov;
    }
    
    /**
     * http://www.pablocantero.com/blog/2010/09/29/upload-com-spring-mvc/
     * 
     * @param request
     * @return 
     */
    @RequestMapping(value = "/account/update/profile", method = POST)
    public String updateProfile(HttpServletRequest request){
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String fileName = null;
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("picture");
            if(multipartFile.getSize() > 0){
                String fileDir = request.getServletContext().getRealPath("/upload");
                File dir = new File(fileDir);
                if (!dir.exists()){
                    dir.mkdir();
                }
                String[] sn = multipartFile.getOriginalFilename().split("[.]");
                fileName = String.format("%d.%s",new Date().getTime(),sn[sn.length-1]);
                File file = new File(String.format("%s/%s",dir.getAbsolutePath(),fileName));
                multipartFile.transferTo(file);
                
            }
            ConnectionFactory.openConnection();
            Person person = personDAO.findOne(id);
            if (fileName != null){
                if (person.getProfilePicture() != null){
                    String oldFileName = String.format("%s/%s",request.getServletContext().getRealPath("/upload"),person.getProfilePicture());
                    File file = new File(oldFileName);
                    System.out.println(file.getAbsolutePath());
                    if (file.exists())
                        file.delete();
                }
                person.setProfilePicture(fileName);
            }
            String bio = request.getParameter("bio").trim();
            if (bio.length() > 0)
                person.setBiography(bio);
            else
                person.setBiography(null);
            personDAO.update(person);
            ConnectionFactory.closeConnection();
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login";
    }
    
    private String redirectDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("page", "dashboard");
        return "app";
    }
    
    
}
