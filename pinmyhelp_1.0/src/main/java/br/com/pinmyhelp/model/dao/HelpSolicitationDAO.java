/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Address;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.HelpType;
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
public class HelpSolicitationDAO extends AbstractDAO<HelpSolicitation> {
    
    public HelpSolicitationDAO() {
        setCreateSql("INSERT INTO help_solicitation("
                + " solicitation_type,"
                + " solicitation_description,"
                + " start_date,"
                + " end_date,"
                + " s_postal_code,"
                + " s_neighborhood,"
                + " s_street,"
                + " s_number,"
                + " s_latitude,"
                + " s_longitude,"
                + " claimant_id"
                + ") VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        setUpdateSql("UPDATE help_solicitation SET"
                + " solicitation_status = ?,"
                + " solicitation_description = ?,"
                + " solicitation_type = ?,"
                + " start_date = ?,"
                + " end_date = ?,"
                + " s_postal_code = ?,"
                + " s_neighborhood = ?,"
                + " s_street = ?,"
                + " s_number = ?,"
                + " s_latitude = ?,"
                + " s_longitude = ?,"
                + " claimant_id = ? "
                + "WHERE solicitation_id = ?");
        setDeleteSql("DELETE FROM help_solicitation WHERE solicitation_id = ?");
        setFindOneSql("SELECT * FROM help_solicitation WHERE solicitation_id = ?");
        setFindSql("SELECT * FROM help_solicitation WHERE solicitation_id = ?");
        setFindAllSql("SELECT * FROM help_solicitation");
    }
    
    @Override
    protected void fillCreate(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        //ps.setInt(1, h.getStatus().getId());
        h.removeMasks();
        ps.setInt(1, h.getType().getId());
        ps.setString(2, h.getDescription());
        ps.setDate(3, h.getStartDate() != null ? java.sql.Date.valueOf(h.getStartDate()) : null);
        ps.setDate(4, h.getEndDate() != null ? java.sql.Date.valueOf(h.getEndDate()) : null);        
        ps.setString(5, h.getAddress().getPostalCode());
        ps.setString(6, h.getAddress().getNeighborhood());
        ps.setString(7,h.getAddress().getStreet());
        ps.setInt(8, h.getAddress().getNumber());
        ps.setDouble(9, 0.0);
        ps.setDouble(10, 0.0);
        ps.setInt(11, h.getClaimant() != null ? h.getClaimant().getId() : h.getEntity().getId());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        h.removeMasks();
        ps.setInt(1, h.getStatus().getId());
        ps.setString(2, h.getDescription());
        ps.setInt(3, h.getType().getId());
        ps.setDate(4, h.getStartDate() != null ? java.sql.Date.valueOf(h.getStartDate()) : null);
        ps.setDate(5, h.getEndDate() != null ? java.sql.Date.valueOf(h.getEndDate()) : null);    
        ps.setString(6, h.getAddress().getPostalCode());
        ps.setString(7, h.getAddress().getNeighborhood());
        ps.setString(8,h.getAddress().getStreet());
        ps.setInt(9, h.getAddress().getNumber());
        ps.setDouble(10, h.getLocation().getLatitude());
        ps.setDouble(11, h.getLocation().getLongitude());
        ps.setInt(12, h.getClaimant() != null ? h.getClaimant().getId() : h.getEntity().getId());
        ps.setInt(13, h.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        ps.setInt(1, h.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        ps.setInt(1, h.getId());
    }

    @Override
    protected HelpSolicitation fillRecord(ResultSet rs) throws SQLException {
        HelpSolicitation h = new HelpSolicitation();
        h.setId(rs.getInt("solicitation_id"));
        h.setStatus(HelpStatus.get(rs.getInt("solicitation_status")));
        h.setDescription(rs.getString("solicitation_description"));
        h.setType(HelpType.get(rs.getInt("solicitation_type")));
        if(rs.getDate("start_date") != null) h.setStartDate(rs.getDate("start_date").toLocalDate());
        if(rs.getDate("end_date") != null) h.setEndDate(rs.getDate("end_date").toLocalDate());
        Address a = new Address();
        a.setPostalCode(rs.getString("s_postal_code"));
        a.setNeighborhood(rs.getString("s_neighborhood"));
        a.setStreet(rs.getString("s_street"));
        a.setNumber(rs.getInt("s_number"));
        h.setAddress(a);
        /*h.setLocation(
                new GeoLocation(rs.getDouble("s_latitude"), rs.getDouble("s_longitude"))
        );*/
        return h;
    }

    public List<HelpSolicitation> findByClaimantId(Integer id, Integer limit) {
        if (limit == null)    
            return find("claimant_id = ?", new String[]{String.valueOf(id)});
        return find("claimant_id = ? limit " + limit, new String[]{String.valueOf(id)});
    } 
}