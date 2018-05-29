package br.com.pinmyhelp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adriano
 */
@Controller
public class IndexController {

    @RequestMapping( value = {"", "/", "/home"} )
    public String home() {
        return "index";
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
