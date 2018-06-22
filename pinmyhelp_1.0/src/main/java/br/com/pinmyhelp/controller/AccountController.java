package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.database.ConnectionManager;
import br.com.pinmyhelp.model.Address;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView createPerson(@Valid Person person, BindingResult result, HttpSession session) {
        if (session.getAttribute("user")==null)
            redirectLogin();
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("register");
            return mav;
        }
        User user = new User(person.getEmail(), person.getPassword());
        // create one single connection for two inserts
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        person.setId(userDAO.create(user));
        personDAO.create(person);
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        session.setAttribute("user", user);
        session.setAttribute("type", person.getType());
        return redirectDashboard();
    }

    @RequestMapping(value = "/account/store/entity", method = POST)
    public ModelAndView createEntity(@Valid Entity entity, Address address, BindingResult result, HttpSession session) {
        if (session.getAttribute("user")==null)
            redirectLogin();
        entity.setAddress(address);
        if (result.hasErrors()) 
            return new ModelAndView("register");
        User u = new User(entity.getEmail(), entity.getPassword());
        // create one single connection for two inserts
        entity.setId(userDAO.create(u));
        entityDAO.create(entity);
        ConnectionManager.closeConnection();
        return redirectDashboard();
    }

    @RequestMapping(value = "/account/edit/person/{idPerson}", method = GET)
    public ModelAndView editPerson(@PathVariable(value = "idPerson")int id, HttpSession session) {
        if (!checkUser(id, session))
            return new ModelAndView("redirect:/login");
        Person person = personDAO.findOne(id);
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("page", "account/edit");
        mav.addObject("editPage", "person");
        mav.addObject("person", person);
        return mav;
    }

    @RequestMapping(value = "/account/edit/entity/{idEntity}", method = GET)
    public ModelAndView editEntity(@PathVariable(value = "idEntity")int id, HttpSession session) {
        if (!checkUser(id, session))
            return new ModelAndView("redirect:/login");
        Entity entity = entityDAO.findOne(id);
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("page", "account/edit");
        mav.addObject("editPage", "entity");
        mav.addObject("entity", entity);
        return mav;
    }

    @RequestMapping(value = "/account/update/person", method = POST)
    public ModelAndView updatePerson(@Valid Person person, Address address, BindingResult result, HttpSession session) {
        if (session.getAttribute("user")==null)
            redirectLogin();
        person.setAddress(address);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("app");
            mav.addObject("page", "account/edit");
            mav.addObject("editPage", "person");
            mav.addObject("person", person);
            return mav;
        }
        // create one single connection for two inserts
        ConnectionManager.openConnection();
        if (person.getPassword() != null){
            User user = userDAO.findOne(person.getId());
            user.setPassword(person.getPassword());
            userDAO.update(user);
        }
        personDAO.update(person);
        ConnectionManager.closeConnection();
        return redirectDashboard();
    }

    @RequestMapping(value = "/account/update/entity", method = POST)
    public ModelAndView updateEntity(@Valid Entity entity, Address address, BindingResult result, HttpSession session) {
        if (session.getAttribute("user")==null)
            redirectLogin();
        entity.setAddress(address);
        if (result.hasErrors()){
            ModelAndView mav = new ModelAndView("app");
            mav.addObject("page", "acount/edit");
            mav.addObject("editPage", "entity");
            mav.addObject("entity", entity);
            return mav;
        }
        // single connection for all operations
        ConnectionManager.openConnection();
        if (entity.getPassword() != null){
            User user = userDAO.findOne(entity.getId());
            user.setPassword(entity.getPassword());
            userDAO.update(user);
        }
        entityDAO.update(entity);
        ConnectionManager.closeConnection();
        return redirectDashboard();
    }

    @RequestMapping(value = "/account/delete/person/{idPerson}", method = GET)
    public ModelAndView deletePerson(@PathVariable(value = "idPerson")int id, HttpSession session) {
        if (!checkUser(id, session))
            return redirectLogin();
        ConnectionManager.openConnection();
        Person person = personDAO.findOne(id);
        User user = userDAO.findOne(id);
        //fazer uma trigger no banco pra apagar o resto das referencias antes
        personDAO.delete(person);
        userDAO.delete(user);
        ConnectionManager.closeConnection();
        return redirectLogin();
    }

    @RequestMapping(value = "/account/delete/entity/{idEntity}", method = GET)
    public ModelAndView deleteEntity(@PathVariable(value = "idEntity")int id, HttpSession session) {
        if (!checkUser(id, session))
            return redirectLogin();
        ConnectionManager.openConnection();
        Entity entity = entityDAO.findOne(id);
        User user = userDAO.findOne(id);
        //fazer uma trigger no banco pra apagar o resto das referencias antes
        entityDAO.delete(entity);
        userDAO.delete(user);
        ConnectionManager.closeConnection();
        return redirectLogin();
    }

    @RequestMapping(value = "/account/edit/profile/{id}", method = GET)
    public ModelAndView editProfile(@PathVariable(value="id") int id, HttpSession session){
        if (!checkUser(id, session))
            return redirectLogin();
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
     * @param session
     * @return 
     */
    @RequestMapping(value = "/account/update/profile", method = POST)
    public ModelAndView updateProfile(HttpServletRequest request, HttpSession session){
        if (session.getAttribute("user")==null)
            return redirectLogin();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String fileName = null;
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("picture");
            if(multipartFile.getSize() > 0){
                String fileDir = request.getServletContext().getRealPath("/upload");
                File dir = new File(fileDir);
                String[] sn = multipartFile.getOriginalFilename().split("[.]");
                fileName = String.format("%d.%s",new Date().getTime(),sn[sn.length-1]);
                File file = new File(String.format("%s/%s",dir.getAbsolutePath(),fileName));
                multipartFile.transferTo(file);
                
            }
            ConnectionManager.openConnection();
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
            ConnectionManager.closeConnection();
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return redirectDashboard();
    }
    
    public ModelAndView redirectDashboard(){
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("title","Dashboard");
        mav.addObject("page", "dashboard");
        return mav;
    }
    
    public ModelAndView redirectLogin(){
        return new ModelAndView("redirect:/login");
    }
    
    /**
     * Verifica se o usuário passado como parâmetro é o mesmo que está logado no sistema
     * @param id
     * @param session
     * @return boolean - resultado da verificação
     */
    public boolean checkUser(int id, HttpSession session){
        if (session.getAttribute("user") != null){
            User user = (User)session.getAttribute("user");
            System.out.println("Checando usuário - id: "+user.getId());
            if (user.isAdmin())
                return true;
            if (user.getId() != null && user.getId().compareTo(id) == 0)
                return true;
        }
        System.err.println("Usuario não pode fazer essa operação!");
        session.invalidate();
        return false;
    }
    
}
