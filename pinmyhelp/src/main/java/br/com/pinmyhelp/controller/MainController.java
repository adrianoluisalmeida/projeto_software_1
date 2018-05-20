/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.controller;

import br.com.pinmyhelp.model.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author rhau
 */
@Controller
public class MainController {
    
    
    @RequestMapping( value = {"", "/"} )
    public RedirectView index() {
        return new RedirectView("home", true); 
    }
   
    @RequestMapping("home")
    public String home() {

        return "index";
    }
    
    @RequestMapping("testdao")
    public String testeDao() {
        System.out.println(Address.getAll(Address.class));
        return "teste";
    }
    
}
