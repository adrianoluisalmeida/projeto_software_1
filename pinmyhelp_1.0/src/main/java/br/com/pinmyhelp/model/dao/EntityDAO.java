/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Address;
import br.com.pinmyhelp.model.Entity;
import br.com.pinmyhelp.model.types.GeoLocation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

/**
 *
 * @author isabella
 */
@Component
public class EntityDAO extends AbstractDAO<Entity> {
    
    public EntityDAO() {
        setCreateSql("INSERT INTO entity("
                + " entity_id,"
                + " entity_name,"
                + " cnpj,"
                + " foundation_date,"
                + " entity_first_phone"
                + ") VALUES (?,?,?,?,?) ");
        setUpdateSql("UPDATE entity SET"
                + " entity_name = ?,"
                + " cnpj = ?,"
                + " fundation_date = ?,"
                + " entity_first_phone = ?,"
                + " entity_second_phone = ?,"
                + " logo = ?,"
                + " description = ?,"
                + " entity_score = ?,"
                + " entity_notes = ?,"
                + " e_postal_code = ?,"
                + " e_neighborhood = ?,"
                + " e_street = ?,"
                + " e_number = ?,"
                + " e_complement = ?,"
                + " e_latitude = ?,"
                + " e_longitude = ?"
                + " WHERE entity_id = ?");
        setDeleteSql("DELETE FROM entity WHERE entity_id = ?");
        setFindOneSql("SELECT * FROM entity WHERE entity_id = ?");
        setFindSql("SELECT * FROM entity WHERE cnpj = ?");
        setFindAllSql("SELECT * FROM entity");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Entity e) throws SQLException {
        e.removeMasks();
        ps.setInt(1, e.getId());
        ps.setString(2, e.getName());
        ps.setString(3, e.getCnpj());
        ps.setDate(4, java.sql.Date.valueOf(e.getFoundationDate()));
        ps.setString(5, e.getFirstPhone());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Entity e) throws SQLException {
        e.removeMasks();
        ps.setString(2, e.getName());
        ps.setString(3, e.getCnpj());
        ps.setDate(4, java.sql.Date.valueOf(e.getFoundationDate()));
        ps.setString(5, e.getFirstPhone());
        ps.setString(6, e.getSecondPhone());
        ps.setString(7, e.getLogo());
        ps.setString(8, e.getDescription());
        ps.setDouble(9, e.getScore());
        ps.setString(10, e.getNotes());
        //Adress
        ps.setString(11, e.getAddress().getPostalCode());
        ps.setString(12, e.getAddress().getNeighborhood());
        ps.setString(13, e.getAddress().getStreet());
        ps.setInt(14, e.getAddress().getNumber());
        ps.setString(15, e.getAddress().getComplement());
        ps.setDouble(16, e.getAddress().getLocation().getLatitude());
        ps.setDouble(17, e.getAddress().getLocation().getLongitude());
        //Primary key
        ps.setInt(18, e.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Entity e) throws SQLException {
        ps.setInt(1, e.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, Entity e) throws SQLException {
        ps.setString(1, e.getCnpj());
    }

    @Override
    protected Entity fillRecord(ResultSet rs) throws SQLException {
        Entity p = new Entity();
        p.setId(rs.getInt("entity_id"));
        p.setName(rs.getString("entity_name"));
        p.setCnpj(rs.getString("cnpj"));
        p.setFoundationDate(rs.getDate("fundation_date").toLocalDate());
        p.setFirstPhone(rs.getString("entity_first_phone"));
        p.setSecondPhone(rs.getString("entity_second_phone"));
        p.setLogo(rs.getString("logo"));
        p.setDescription(rs.getString("biography"));
        p.setScore(rs.getDouble("person_score"));
        p.setNotes(rs.getString("person_notes"));
        Address address = new Address();
        address.setPostalCode(rs.getString("e_postal_code"));
        address.setNeighborhood(rs.getString("e_neighborhood"));
        address.setStreet(rs.getString("e_street"));
        address.setNumber(rs.getInt("e_number"));
        address.setComplement(rs.getString("e_complement"));
        address.setLocation(
                new GeoLocation(rs.getDouble("e_latitude"), rs.getDouble("e_longitude")));
        p.setAddress(address);  
        return p;
    }

}
