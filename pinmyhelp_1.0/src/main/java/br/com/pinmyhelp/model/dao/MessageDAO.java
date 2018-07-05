/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Message;
import br.com.pinmyhelp.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author isabella
 */
@Component
public class MessageDAO extends AbstractDAO<Message> {
    
    public MessageDAO(){    
        setCreateSql("INSERT INTO message (user_id, title, content) VALUES (?, ?, ?)");
        setUpdateSql("UPDATE message SET is_readed = TRUE WHERE user_id = ?"); // IMPORTANT
        setDeleteSql("DELETE FROM message WHERE message_id = ?");
        setFindOneSql("SELECT * FROM message WHERE message_id = ?");
        setFindSql("SELECT * FROM message WHERE user_id = ?");
        setFindAllSql("SELECT * FROM message");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Message m) throws SQLException {
        ps.setInt(1, m.getUser().getId());
        ps.setString(2, m.getTitle());
        ps.setString(3, m.getContent());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Message m) throws SQLException {
        ps.setInt(1, m.getUser().getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Message m) throws SQLException {
        ps.setInt(1, m.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, Message m) throws SQLException {
        ps.setInt(1, m.getUser().getId());
    }

    @Override
    protected Message fillRecord(ResultSet rs) throws SQLException {
        Message m = new Message();
        m.setId(rs.getInt("message_id"));
        m.setTitle(rs.getString("title"));
        m.setContent(rs.getString("content"));
        m.setIsReaded(rs.getBoolean("is_readed"));
        m.setCreatedDate((rs.getTimestamp("message_created").toLocalDateTime().toLocalDate()));
        return m;
    }
    
    public List<Message> findMessages(int userId, Boolean isReaded){
        return find("user_id = ? AND is_readed is " + Boolean.toString(isReaded) + " ORDER BY message_created DESC",  
                new String[]{String.valueOf(userId)});
    }
    
    public List<Message> findMessages(User user){
        return find("user_id = ? ORDER BY message_created DESC", user.getId());
    }
    
}