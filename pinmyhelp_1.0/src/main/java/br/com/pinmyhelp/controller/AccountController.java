package br.com.pinmyhelp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adriano
 */
@Controller
public class AccountController {

    @RequestMapping("/account/register")
    public String register() {
        return "register";
    }

}
