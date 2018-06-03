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
import br.com.pinmyhelp.model.Voluntary;
import br.com.pinmyhelp.model.types.OfferStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author isabella
 */
public class HelpOfferDAO extends AbstractDAO<HelpOffer> {
    
    public HelpOfferDAO() {
        setCreateSql("INSERT INTO help_offer(offer_status,solicitation_id,voluntary_id) VALUES(?, ?, ?)");
        setUpdateSql("UPDATE help_offer SET offer_status = ? WHERE offer_id =  ?");
        setDeleteSql("DELETE FROM help_offer WHERE offer_id =  ?");
        setFindOneSql("SELECT * FROM help_offer WHERE offer_id =  ?");
        setFindSql("SELECT * FROM help_offer WHERE voluntary_id = ?");
        setFindAllSql("SELECT * FROM help_offer");
    }

    /*
    offer_id            INT               AUTO_INCREMENT,
    offer_status        INT               NOT NULL, # TODO: change type to ENUM 
    solicitation_id     INT               NOT NULL,
    voluntary_id        INT 
    */
    
    @Override
    protected void fillCreate(PreparedStatement ps, HelpOffer ho) throws SQLException {
        ps.setInt(1, ho.getStatus().getId());
        ps.setInt(2, ho.getHelpSolicitation().getId());
        ps.setInt(3, ho.getVoluntary().getId());
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
        HelpSolicitationDAO hsDAO = new HelpSolicitationDAO();
        HelpSolicitation hs = hsDAO.findOne(rs.getInt("solicitation_id"));
        ho.setHelpSolicitation(hs);
        PersonDAO pDAO = new PersonDAO();
        Voluntary v = (Voluntary)pDAO.findOne(rs.getInt("voluntary_id"));
        ho.setVoluntary(v);
        ho.setId(rs.getInt("offer_id"));
        return ho;
    }

}
