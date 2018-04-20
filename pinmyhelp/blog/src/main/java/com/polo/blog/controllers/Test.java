/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polo.blog.controllers;

import com.polo.blog.model.Post;
import com.polo.blog.model.data.SessionManager;
import java.util.List;

/**
 *
 * @author roger
 */
public class Test {
    
    public static void main(String[] args) {
        /*
        SessionManager.beginSession(true);
        Tag t = new Tag();
        t.setNome("TESTE 1");
        DB.insert(t);
        Tag t2 = new Tag();
        t2.setNome("TESTE 2");
        DB.insert(t2);
        SessionManager.endSession(true);
        List<Tag> tags = Tag.find("WEEK");
        for(Tag tag : tags){
            System.out.println(tag.getNome());
        }
        String busca = "sentido";
        List<Post> posts = (List<Post>)DB.find(Post.class,
                "titulo like :titulo or conteudo like :conteudo",
                "%"+busca+"%"
                );
        for (Post post : posts){
            System.out.println(post.getTitulo()+" - "+post.getConteudo());
        }
        List<Tag> tags = Tag.find("WEEK");
        tags.forEach(System.out::println);
        Tag tag = tags.get(0);
        tag.setNome("Weekend");
        tag.save();
        tags = Tag.find("Week");
        tags.forEach(System.out::println);
        */
        SessionManager.beginSession(false);
        List<Post> posts = Post.getAll();
        posts.forEach(System.out::println);
        posts.get(0).getArquivos().forEach(System.out::println);
        posts.get(0).getTags().forEach(System.out::println);
        SessionManager.endSession(false);
    }
    
    public static boolean test2(){
        return SessionManager.endSession(false);
    }
    
}
