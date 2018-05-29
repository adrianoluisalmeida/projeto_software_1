package br.csi.controller;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adriano
 */
@Controller
public class IndexController {

    public String index() {
        
        return "redirect:home";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }
    
//
//    @RequestMapping("cadastrar")
//    public String cadatrar() {
//        return "cadastrar";
//    }

    @RequestMapping(value = {"/home", "/"})
    public String homeSite() {
        return "site";
    }

//    @RequestMapping(value = {"/about"})
//    public String about() {
//        return "about";
//    }

//    @RequestMapping(value = "/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:login";
//    }

}
