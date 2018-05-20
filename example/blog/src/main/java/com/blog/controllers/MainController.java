/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.controllers;

import com.blog.model.Post;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 
    @RequestMapping("/listaPosts")
    public String lista(Model model){
        /*
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setId(1);
        post.setTitulo("Teste");
        post.setConteudo("Conteúdo teste");
        post.setDataPub(LocalDate.now());
        posts.add(post);
        */
        List<Post> posts = Post.getAll();
        model.addAttribute("posts",posts);
        return "lista";
    }
    
}
