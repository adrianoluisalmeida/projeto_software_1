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
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
}