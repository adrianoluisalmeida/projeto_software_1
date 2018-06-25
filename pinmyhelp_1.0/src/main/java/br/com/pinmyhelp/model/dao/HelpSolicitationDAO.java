/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.types.GeoLocation;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.HelpType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

/**
 *
 * @author isabella
 */
@Component
public class HelpSolicitationDAO extends AbstractDAO<HelpSolicitation> {
    
    public HelpSolicitationDAO() {
        setCreateSql("INSERT INTO help_solicitation("
                + " solicitation_type, "
                + " solicitation_description, "
                + " start_date,"
                + " end_date,"
                + " s_latitude,"
                + " s_longitude,"
                + " claimant_id"
                + ") VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)");
        setUpdateSql("UPDATE help_solicitation SET"
                + " solicitation_status = ?,"
                + " solicitation_description = ?,"
                + " solicitation_type = ?,"
                + " start_date = ?,"
                + " end_date = ?,"
                + " s_latitude = ?,"
                + " s_longitude = ?,"
                + " claimant_id = ? "
                + "WHERE solicitation_id = ?");
        setDeleteSql("DELETE FROM help_solicitation WHERE solicitation_id = ?");
        setFindOneSql("SELECT * FROM help_solicitation WHERE solicitation_id = ?" );
        setFindSql("SELECT * FROM help_solicitation WHERE claimant_id = ?");
        setFindAllSql("SELECT * FROM help_solicitation");
    }
    
    @Override
    protected void fillCreate(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        //ps.setInt(1, h.getStatus().getId());
        ps.setInt(1, h.getType().getId());
        ps.setString(2, h.getRequirementDescription());
        if(h.getStartDate() != null)
            ps.setDate(3, java.sql.Date.valueOf(h.getStartDate()));
        else
            ps.setDate(3, null);
        if(h.getEndDate() != null)
            ps.setDate(4, java.sql.Date.valueOf(h.getEndDate()));
        else
            ps.setDate(4, null);
        ps.setDouble(5, h.getLocation().getLatitude());
        ps.setDouble(6, h.getLocation().getLongitude());
        ps.setInt(7, h.getClaimant().getId());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        ps.setInt(1, h.getStatus().getId());
        ps.setString(2, h.getRequirementDescription());
        ps.setInt(3, h.getType().getId());
        if(h.getStartDate() != null)
            ps.setDate(4, java.sql.Date.valueOf(h.getStartDate()));
        else
            ps.setDate(4, null);
        if(h.getEndDate() != null)
            ps.setDate(5, java.sql.Date.valueOf(h.getEndDate()));
        else
            ps.setDate(5, null);
        ps.setDouble(6, h.getLocation().getLatitude());
        ps.setDouble(7, h.getLocation().getLongitude());
        ps.setInt(8, h.getClaimant().getId());
        ps.setInt(9, h.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        ps.setInt(1, h.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        ps.setInt(1, h.getClaimant().getId());
    }

    @Override
    protected HelpSolicitation fillRecord(ResultSet rs) throws SQLException {
        HelpSolicitation h = new HelpSolicitation();
        h.setId(rs.getInt("solicitation_id"));
        h.setStatus(HelpStatus.get(rs.getInt("solicitation_status")));
        h.setRequirementDescription(rs.getString("solicitation_description"));
        h.setType(HelpType.get(rs.getInt("solicitation_type")));
        if(rs.getDate("start_date") != null)
            h.setStartDate(rs.getDate("start_date").toLocalDate());
        if(rs.getDate("end_date") != null)
            h.setEndDate(rs.getDate("end_date").toLocalDate());
        h.setLocation(
                new GeoLocation(rs.getDouble("s_latitude"), rs.getDouble("s_longitude"))
        );
        return h;
    }

}
