/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Feedback;
import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.types.Rating;
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
public class FeedbackDAO extends AbstractDAO<Feedback> {
    
    public FeedbackDAO() {
        setCreateSql("INSERT INTO feedback(rating, comments, sender_id, solicitation_id, offer_id) VALUES(?,?,?,?,?)");
        setUpdateSql("UPDATE feedback SET rating = ?, comments = ? WHERE solicitation_id = ?");
        setDeleteSql("DELETE FROM feedback WHERE feedback_id = ?");
        setFindOneSql("SELECT * FROM feedback WHERE feedback_id = ?");
        setFindSql("SELECT * FROM feedback WHERE solicitation_id = ?");
        setFindAllSql("SELECT * FROM feedback");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Feedback f) throws SQLException {
        ps.setInt(1, f.getRating().getValue());
        ps.setString(2, f.getComments());
        ps.setInt(3, f.getSender().getId());
        ps.setObject(4, f.getSolicitation() != null ? f.getSolicitation().getId() : null);
        ps.setObject(5, f.getOffer() != null ? f.getOffer().getId() : null);
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Feedback f) throws SQLException {
        ps.setInt(1, f.getRating().getValue());
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
        return f;
    }
    
    public Feedback findBySolicitation(HelpSolicitation h){
        List<Feedback> list = find("solicitation_id = ?", h.getId());
        if (list.size() == 1)
            return list.get(0);
        return null;
    }
    
    public Feedback findByOffer(HelpOffer o){
        List<Feedback> list = find("offer_id = ?", o.getId());
        if (list.size() == 1)
            return list.get(0);
        return null;
    }
    
    

}
