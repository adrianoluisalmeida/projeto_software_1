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
                + " solicitation_status,"
                + " solicitation_type, "
                + " start_date,"
                + " end_date,"
                + " s_latitude,"
                + " s_longitude,"
                + " claimant_id"
                + ") VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)");
        setUpdateSql("UPDATE help_solicitation SET"
                + " solicitation_status = ?,"
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
        ps.setInt(1, h.getStatus().getId());
        ps.setInt(2, h.getType().getId());
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(h.getStartDate()));
        ps.setTimestamp(4, java.sql.Timestamp.valueOf(h.getEndDate()));
        ps.setDouble(5, h.getLocation().getLatitude());
        ps.setDouble(6, h.getLocation().getLongitude());
        ps.setInt(7, h.getClaimant().getId());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, HelpSolicitation h) throws SQLException {
        ps.setInt(1, h.getStatus().getId());
        ps.setInt(2, h.getType().getId());
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(h.getStartDate()));
        ps.setTimestamp(4, java.sql.Timestamp.valueOf(h.getEndDate()));
        ps.setDouble(5, h.getLocation().getLatitude());
        ps.setDouble(6, h.getLocation().getLongitude());
        ps.setInt(7, h.getClaimant().getId());
        ps.setInt(1, h.getId());
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
        h.setType(HelpType.get(rs.getInt("solicitation_type")));
        h.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        h.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
        h.setLocation(
                new GeoLocation(rs.getDouble("s_latitude"), rs.getDouble("s_longitude"))
        );
        return h;
    }

}