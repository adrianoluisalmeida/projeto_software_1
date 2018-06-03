/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.util;

import br.com.pinmyhelp.model.User;
import br.com.pinmyhelp.model.dao.UserDAO;
import java.util.List;

/**
 *
 * @author roger
 */
public class Test {
    
    public static void main(String[] args) {
        /*
        User u = new User();
        u.setAdmin(Boolean.TRUE);
        u.setEmail("rogerecouto@gmail.com");
        u.setPassword("1234");
        UserDAO dao = new UserDAO();
        dao.create(u);
        System.out.println(u.getId());
        UserDAO dao = new UserDAO();
        User user = dao.findOne(1);
        System.out.println(user.getId()+" "+user.toString());
        Collection<User> list = dao.find("email like ?", new Object[]{"%gmail%"});
        */
        UserDAO dao = new UserDAO();
        List<User> list = dao.findAll();
        list.forEach(System.out::println);
    }
    
}
