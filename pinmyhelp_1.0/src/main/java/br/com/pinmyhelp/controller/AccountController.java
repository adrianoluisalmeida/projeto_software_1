package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.EntityDAO;
import br.com.pinmyhelp.model.dao.PersonDAO;
import br.com.pinmyhelp.model.dao.UserDAO;
import br.com.pinmyhelp.database.ConnectionManager;
import br.com.pinmyhelp.model.Message;
import br.com.pinmyhelp.model.dao.MessageDAO;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @Autowired
    MessageDAO messageDAO;

    @RequestMapping(value = "/account/create", method = GET)
    public String register() {
        return "register";
    }

    @RequestMapping("/account/store/person")
    public ModelAndView createPerson(@Valid Person person, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
        ModelAndView mv = new ModelAndView("register");
        if ( !validPerson(person, mv, result, false, session) ) return mv;
        User user = new User(person.getEmail(), person.getPassword());
        // create one single connection for two inserts
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        person.setId(userDAO.create(user));
        user.setId(personDAO.create(person));
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Sua conta foi criada com sucesso!");
        mv.setViewName("redirect:/login");
        session.setAttribute("registered_email", person.getEmail());
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/account/store/entity")
    public ModelAndView createEntity(@Valid Entity entity, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("tab", "tab-entity");
        if ( !validEntity(entity, mv, result, false, session) ) return mv;  
        User user = new User(entity.getEmail(), entity.getPassword());
        // create one single connection for two inserts
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        entity.setId(userDAO.create(user));
        user.setId(entityDAO.create(entity));
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Sua conta foi criada com sucesso!");
        session.setAttribute("registered_email", entity.getEmail());
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/account/edit/person/{idPerson}", method = GET)
    public ModelAndView editPerson(@PathVariable(value = "idPerson") int id, HttpSession session) {
        if ( !checkUser(id, session) ) 
            return new ModelAndView("redirect:/login");
        // Person person = personDAO.findOne(id);
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("page", "account/edit");
        mav.addObject("editPage", "person");
        // mav.addObject("person", person); 
        return mav;
    }

    @RequestMapping(value = "/account/edit/entity/{idEntity}", method = GET)
    public ModelAndView editEntity(@PathVariable(value = "idEntity") int id, HttpSession session) {
        if ( !checkUser(id, session) ) 
            return new ModelAndView("redirect:/login");
        // Entity entity = entityDAO.findOne(id);
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("page", "account/edit");
        mav.addObject("editPage", "entity");
        // mav.addObject("entity", entity);
        return mav;
    }

    @RequestMapping(value = "/account/update/person", method = POST)
    public ModelAndView updatePerson(@Valid Person person, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
        User user = (User) session.getAttribute("user");
        if (user == null) return new ModelAndView("redirect:/login");
        ModelAndView mv = new ModelAndView("app");
        if ( !validPerson(person, mv, result, true, session) ) {        
            mv.addObject("page", "account/edit");
            mv.addObject("editPage", "person");
            return mv;
        }
        // create one single connection for two inserts
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        if (!user.getEmail().equals(person.getEmail()) || !user.getPassword().equals(person.getPassword())) {
            user.setEmail(person.getEmail());
            user.setPassword(person.getPassword());
            userDAO.update(user);
            session.setAttribute("user", user);
        }
        personDAO.update(person);
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        mv.setViewName("redirect:/dashboard");
        session.setAttribute("person", person);
        redirectAttrs.addFlashAttribute("msg_success", "Conta alterada com sucesso!");
        return mv;
    }

    @RequestMapping(value = "/account/update/entity", method = POST)
    public ModelAndView updateEntity(@Valid Entity entity, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
        User user = (User) session.getAttribute("user");
        if (user == null) return new ModelAndView("redirect:/login");
        ModelAndView mv = new ModelAndView("app");
        if ( !validEntity(entity, mv, result, false, session) ) {
            mv.addObject("editPage", "entity");
            mv.addObject("page", "acount/edit");
            return mv;
        }
        // create one single connection for two inserts
        ConnectionManager.openConnection();
        ConnectionManager.beginTransaction();
        if (!user.getEmail().equals(entity.getEmail()) || !user.getPassword().equals(entity.getPassword())) {
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            userDAO.update(user);
            session.setAttribute("user", user);
        }
        entityDAO.update(entity);
        ConnectionManager.commitTransaction();
        ConnectionManager.closeConnection();
        mv.setViewName("redirect:/dashboard");
        session.setAttribute("entity", entity);
        redirectAttrs.addFlashAttribute("msg_success", "Conta alterada com sucesso!");
        return mv;
    }

    @RequestMapping(value = "/account/delete/person/{idPerson}", method = GET)
    public ModelAndView deletePerson(@PathVariable(value = "idPerson") int id, HttpSession session, RedirectAttributes redirectAttrs) {
        if (!checkUser(id, session)) {
            return new LoginController().redirectLogin();
        }
        ConnectionManager.openConnection();
        Person person = personDAO.findOne(id);
        User user = userDAO.findOne(id);
        //fazer uma trigger no banco pra apagar o resto das referencias antes
        personDAO.delete(person);
        userDAO.delete(user);
        ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Conta removida com sucesso!");
        
        return new LoginController().redirectLogin();
    }

    @RequestMapping(value = "/account/delete/entity/{idEntity}", method = GET)
    public ModelAndView deleteEntity(@PathVariable(value = "idEntity") int id, HttpSession session, RedirectAttributes redirectAttrs) {
        if (!checkUser(id, session)) {
            return new LoginController().redirectLogin();
        }
        ConnectionManager.openConnection();
        Entity entity = entityDAO.findOne(id);
        User user = userDAO.findOne(id);
        //fazer uma trigger no banco pra apagar o resto das referencias antes
        entityDAO.delete(entity);
        userDAO.delete(user);
        ConnectionManager.closeConnection();
        redirectAttrs.addFlashAttribute("msg_success", "Conta removida com sucesso!");
        return new LoginController().redirectLogin();
    }

    @RequestMapping(value = "/account/edit/profile/{id}", method = GET)
    public ModelAndView editProfile(@PathVariable(value = "id") int id, HttpSession session) {
        if (!checkUser(id, session)) {
            return new LoginController().redirectLogin();
        }
        ModelAndView mov = new ModelAndView("app");
        mov.addObject("page", "account/profile");
        Person person = personDAO.findOne(id);
        if (person != null){
            mov.addObject("person", person);
        }else{
            Entity entity = entityDAO.findOne(id);
            mov.addObject("entity", entity);
        }
        return mov;
    }

    /**
     * http://www.pablocantero.com/blog/2010/09/29/upload-com-spring-mvc/
     *
     * @param request
     * @param session
     * @param redirectAttrs 
     * @return
     */
    @RequestMapping(value = "/account/update/profile", method = POST)
    public ModelAndView updateProfile(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttrs) {
        if (session.getAttribute("user") == null) return new LoginController().redirectLogin();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String fileName = null;
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("picture");
            if (multipartFile.getSize() > 0) {
                String fileDir = request.getServletContext().getRealPath("/upload");
                File dir = new File(fileDir);
                String[] sn = multipartFile.getOriginalFilename().split("[.]");
                fileName = String.format("%d.%s", new Date().getTime(), sn[sn.length - 1]);
                File file = new File(String.format("%s/%s", dir.getAbsolutePath(), fileName));
                multipartFile.transferTo(file);
            }
            ConnectionManager.openConnection();
            Person person = personDAO.findOne(id);
            if (person != null){
                if (fileName != null) {
                    if (person.getProfilePicture() != null) {
                        String oldFileName = String.format("%s/%s", request.getServletContext().getRealPath("/upload"), person.getProfilePicture());
                        File file = new File(oldFileName);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    person.setProfilePicture(fileName);
                    session.setAttribute("profilePicture", person.getProfilePicture());
                }
                String bio = request.getParameter("bio").trim();
                if (bio.length() > 0) {
                    person.setBiography(bio.trim());
                } else {
                    person.setBiography(null);
                }
                personDAO.update(person);
            }else{
                Entity entity = entityDAO.findOne(id);
                if (fileName != null) {
                    if (entity.getLogo() != null) {
                        String oldFileName = String.format("%s/%s", request.getServletContext().getRealPath("/upload"), entity.getLogo());
                        File file = new File(oldFileName);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    entity.setLogo(fileName);
                    session.setAttribute("profilePicture", entity.getLogo());
                }
                String bio = request.getParameter("bio").trim();
                if (bio.length() > 0) {
                    entity.setNotes(bio.trim());
                } else {
                    entity.setNotes(null);
                }
                entityDAO.update(entity);
            }
            ConnectionManager.closeConnection();
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        redirectAttrs.addFlashAttribute("msg_success", "Perfil alterado com sucesso!");
        return new ModelAndView("redirect:/dashboard");
    }

    @RequestMapping(value = "/account/messages/my", method = GET)
    public ModelAndView getMessages(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.setAttribute("new_messages", messageDAO.findMessages(user.getId(), false)); // isReaded = false
        session.setAttribute("old_messages", messageDAO.findMessages(user.getId(), true));   // isReaded = true
        ModelAndView mav = new ModelAndView("app");
        mav.addObject("page", "account/messages");
        return mav;
    }
    
    @RequestMapping(value = "/account/messages/my/read", method = GET)
    public ModelAndView readMessages(HttpSession session) {
        User user = (User) session.getAttribute("user");
        messageDAO.update(new Message(user)); // important: messageDAO.update set all user messages as TRUE by default
        return getMessages(session);
    }    
    
    /**
     * Verifica se o usuário passado como parâmetro é o mesmo que está logado no
     * sistema
     *
     * @param id
     * @param session
     * @return boolean - resultado da verificação
     */
    public boolean checkUser(int id, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            System.out.println("Checando usuário - id: " + user.getId());
            if (user.isAdmin()) {
                return true;
            }
            if (user.getId() != null && user.getId().compareTo(id) == 0) {
                return true;
            }
        }
        System.err.println("Usuario não pode fazer essa operação!");
        session.invalidate();
        return false;
    }
    
    public boolean validPerson(Person person, ModelAndView mv, BindingResult result, boolean update, HttpSession session) {
        if ( result.hasErrors() ) {
            if ( person.getBornDate() == null || result.hasFieldErrors("bornDate") )
                mv.addObject("errorBornDate", "Informe uma data válida");
            return false;
        }
        if ( person.getPassword().length() < 6 ) {
            mv.addObject("password_error_person", "A senha deve ter no mínimo 6 caracteres");
            return false;           
        }
        if ( person.getBornDate() == null ) { // it's also necessary to check the date here
            mv.addObject("errorBornDate", "Informe uma data válida");
            return false;
        }
        if ( !update ) {
            if ( userDAO.emailAlreadyExists(person.getEmail()) ) {
                mv.addObject("email_error_person", "E-mail já cadastrado");
                return false;
            } 
        } else {
            User user = (User) session.getAttribute("user");
            if ( !person.getEmail().equals(user.getEmail()) && userDAO.emailAlreadyExists(person.getEmail()) ) {
                mv.addObject("email_error_person", "E-mail já cadastrado");
                return false;
            }   
        }
        return true;
    }

    public boolean validEntity(Entity entity, ModelAndView mv, BindingResult result, boolean update, HttpSession session) {
        if (result.hasErrors()) {
            if ( entity.getFoundationDate() == null || result.hasFieldErrors("foundationDate") )
                mv.addObject("errorFoundationDate", "Informe uma data válida");
            return false;
        }
        if ( entity.getPassword().length() < 6 ) {
            mv.addObject("password_error_entity", "A senha deve ter no mínimo 6 caracteres");
            return false;            
        } 
        if ( entity.getFoundationDate() == null) {
            mv.addObject("errorFoundationDate", "Informe uma data válida");
            return false;
        }
        if ( !update ) {
            if ( userDAO.emailAlreadyExists(entity.getEmail()) ) {
                mv.addObject("email_error_entity", "E-mail já cadastrado");
                return false;
            } 
        } else {
            User user = (User) session.getAttribute("user");
            if ( !entity.getEmail().equals(user.getEmail()) && userDAO.emailAlreadyExists(entity.getEmail()) ) {
                mv.addObject("email_error_entity", "E-mail já cadastrado");
                return false;
            }   
        }
        return true;
    }

}
