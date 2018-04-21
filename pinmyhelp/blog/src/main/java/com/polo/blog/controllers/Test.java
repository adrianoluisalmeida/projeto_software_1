/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polo.blog.controllers;

import com.polo.blog.model.Post;
import com.polo.blog.model.data.Model;
import com.polo.blog.model.data.SessionManager;
import java.util.List;

/**
 *
 * @author roger
 */
public class Test {
    
    public static void main(String[] args) {
        List<Post> posts = Post.getAll();
        posts.forEach(System.out::println);        
    }
        
}
