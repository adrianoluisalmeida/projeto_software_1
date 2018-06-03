/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author isabella
 */
public class UserDAO extends AbstractDAO<User> {
    
    public UserDAO(){
        setCreateSql("INSERT INTO user (email, password, is_admin) VALUES (?, ?, ?)");
        setUpdateSql("UPDATE user SET email = ?, password = ?, is_admin = ? WHERE user_id = ?");
        setDeleteSql("DELETE FROM user WHERE user_id = ?");
        setFindOneSql("SELECT * FROM user WHERE user_id = ?");
        setFindSql("SELECT * FROM user WHERE email = ? AND password = ?");
        setFindAllSql("SELECT * FROM user");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, User u) throws SQLException {
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getPassword());
        ps.setBoolean(3, u.isAdmin());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, User u) throws SQLException {
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getPassword());
        ps.setBoolean(3, u.isAdmin());
        ps.setInt(4, u.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, User u) throws SQLException {
        ps.setInt(1, u.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, User u) throws SQLException {
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getPassword());
    }

    @Override
    protected User fillRecord(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("user_id"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setAdmin(rs.getBoolean("is_admin"));
        return u;
    }

    @Override
    public Integer create(User u) {
        Integer id = super.create(u);
        u.setId(id);
        return id;
    }
}
