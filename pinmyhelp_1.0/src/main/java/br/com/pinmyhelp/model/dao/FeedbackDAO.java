/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

/**
 *
 * @author isabella
 */
@Component
public class FeedbackDAO extends AbstractDAO<Feedback> {
    
    public FeedbackDAO() {
        setCreateSql("INSERT INTO feedback(rating, comments, sender_id, solicitation_id) VALUES(?,?,?,?)");
        setUpdateSql("UPDATE feedback SET rating = ?, comments = ? WHERE solicitation_id = ?");
        setDeleteSql("DELETE FROM feedback WHERE feedback_id = ?");
        setFindOneSql("SELECT * FROM feedback WHERE feedback_id = ?");
        setFindSql("SELECT * FROM feedback WHERE solicitation_id = ?");
        setFindAllSql("SELECT * FROM feedback");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Feedback f) throws SQLException {
        ps.setInt(1, f.getRating());
        ps.setString(2, f.getComments());
        ps.setInt(3, f.getSender().getId());
        ps.setInt(4, f.getSolicitation().getId());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Feedback f) throws SQLException {
        ps.setInt(1, f.getRating());
        ps.setString(2, f.getComments());
        ps.setInt(3, f.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Feedback f) throws SQLException {
        ps.setInt(1, f.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, Feedback f) throws SQLException {
        ps.setInt(1, f.getSolicitation().getId());
    }

    @Override
    protected Feedback fillRecord(ResultSet rs) throws SQLException {
        Feedback f = new Feedback();        
        f.setId(rs.getInt("feedback_id"));
        f.setRating(rs.getInt("rating"));
        f.setComments(rs.getString("comments"));
        UserDAO uDao = new UserDAO();
        f.setSender(uDao.findOne(rs.getInt("sender_id")));
        HelpSolicitationDAO hsDao = new HelpSolicitationDAO();
        f.setSolicitation(hsDao.findOne(rs.getInt("solicitation_id")));
        return f;
    }

}
