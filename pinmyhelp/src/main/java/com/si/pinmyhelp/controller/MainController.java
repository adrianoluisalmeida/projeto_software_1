/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.si.pinmyhelp.controller;

import com.si.pinmyhelp.model.Admin;
import com.si.pinmyhelp.model.data.Model;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author roger
 */
@Controller
public class MainController {
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("lista")
    public String lista(org.springframework.ui.Model model){
        List<Admin> list = Model.getAll(Admin.class);
        model.addAttribute("admins", list);
        return "lista";
    }
    
}
