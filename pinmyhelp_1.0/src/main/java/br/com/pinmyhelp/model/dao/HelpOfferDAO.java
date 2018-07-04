/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.types.OfferStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author isabella
 */
@Component
public class HelpOfferDAO extends AbstractDAO<HelpOffer> {
    
    public HelpOfferDAO() {
        setCreateSql("INSERT INTO help_offer("
                + " offer_status, "
                + " offer_observation, "
                + " solicitation_id,"
                + " voluntary_id"
                + ") VALUES"
                + "(?, ?, ?, ?)");
        setUpdateSql("UPDATE help_offer SET offer_status = ? WHERE offer_id =  ?");
        setDeleteSql("DELETE FROM help_offer WHERE offer_id =  ?");
        setFindOneSql("SELECT * FROM help_offer WHERE offer_id =  ?");
        setFindSql("SELECT * FROM help_offer WHERE solicitation_id = ?");
        setFindAllSql("SELECT * FROM help_offer");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, HelpOffer ho) throws SQLException {
        ps.setInt(1, ho.getStatus().getId());
        ps.setString(2, ho.getObservation());
        ps.setInt(3, ho.getHelpSolicitation().getId());
        ps.setInt(4, ho.getVoluntary() != null ? ho.getVoluntary().getId() : ho.getEntity().getId());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, HelpOffer ho) throws SQLException {
        ps.setInt(1, ho.getStatus().getId());
        ps.setInt(2, ho.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, HelpOffer ho) throws SQLException {
        ps.setInt(1, ho.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, HelpOffer ho) throws SQLException {
        ps.setInt(2, ho.getVoluntary().getId());
    }

    @Override
    protected HelpOffer fillRecord(ResultSet rs) throws SQLException {
        HelpOffer ho = new HelpOffer();
        ho.setId(rs.getInt("offer_id"));
        ho.setStatus(OfferStatus.get(rs.getInt("offer_status")));
        ho.setObservation(rs.getString("offer_observation"));
        HelpSolicitationDAO hsDAO = new HelpSolicitationDAO();
        HelpSolicitation hs = hsDAO.findOne(rs.getInt("solicitation_id"));
        ho.setHelpSolicitation(hs);
        PersonDAO pdao = new PersonDAO();
        EntityDAO edao = new EntityDAO();
        Person p = pdao.findOne(rs.getInt("voluntary_id"));
        if (p != null)
            ho.setVoluntary(p); 
        else {
            Entity e = edao.findOne(rs.getInt("voluntary_id"));
            if (e != null)
                ho.setEntity(e);
        }
        ho.setId(rs.getInt("offer_id"));
        FeedbackDAO fDao = new FeedbackDAO();
        ho.setFeedback(fDao.findByOffer(ho));
        return ho;
    }
    
    public Collection<HelpOffer> findByClaimantId(Integer id, Integer limit) {
        String base = "natural join help_solicitation where claimant_id = ? order by start_date";
        if (limit != null && limit > 0)
            return findJoinable(base + " limit " + limit, new String[]{String.valueOf(id)});
        return findJoinable(base, new String[]{String.valueOf(id)});
    }
    
}