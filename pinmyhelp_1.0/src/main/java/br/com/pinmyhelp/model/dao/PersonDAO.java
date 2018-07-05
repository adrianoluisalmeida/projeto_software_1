/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.dao;

import br.com.pinmyhelp.database.AbstractDAO;
import br.com.pinmyhelp.model.Address;
import br.com.pinmyhelp.model.Person;
import br.com.pinmyhelp.model.types.GeoLocation;
import br.com.pinmyhelp.util.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;
/**
 *
 * @author rhau
 */
@Component
public class PersonDAO extends AbstractDAO<Person>{
   
    public PersonDAO(){
        setCreateSql("INSERT INTO person("
                + " person_id,"
                + " person_type,"
                + " person_name,"
                + " cpf,"
                + " rg,"
                + " born_date,"
                + " person_first_phone"
                + ") VALUES (?,?,?,?,?,?,?)");
        setUpdateSql("UPDATE person SET"
                + " person_name = ?,"
                + " cpf = ?,"
                + " rg = ?,"
                + " born_date = ?,"
                + " person_first_phone = ?,"
                + " person_second_phone = ?,"
                + " profile_picture = ?,"
                + " biography = ?,"
                + " person_score = ?,"
                + " person_notes = ?,"
                + " p_postal_code = ?,"
                + " p_uf = ?,"
                + " p_city = ?,"
                + " p_neighborhood = ?,"
                + " p_street = ?,"
                + " p_number = ?,"
                + " p_complement = ?,"
                + " p_latitude = ?,"
                + " p_longitude = ?"
                + " WHERE person_id = ?");
        setDeleteSql("DELETE FROM person WHERE person_id = ?");
        setFindOneSql("SELECT * FROM person JOIN user ON person.person_id = user.user_id WHERE person_id = ?");
        setFindSql("SELECT * FROM person JOIN user ON person.person_id = user.user_id WHERE person_type = ?");
        setFindAllSql("SELECT * FROM person JOIN user ON person.person_id = user.user_id ");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Person p) throws SQLException {
        p.removeMasks();
        ps.setInt(1, p.getId());
        ps.setString(2, p.getType());
        ps.setString(3, p.getName());
        //Removendo mascara de entrada antes de inserir
        ps.setString(4, FormatUtils.unmaskNumber(p.getCpf()));
        ps.setString(5, p.getRg());
        ps.setDate(6, java.sql.Date.valueOf(p.getBornDate()));
        ps.setString(7, FormatUtils.unmaskNumber(p.getFirstPhone()));
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Person p) throws SQLException {
        p.removeMasks();
        ps.setString(1, p.getName());
        ps.setString(2, p.getCpf());
        ps.setString(3, p.getRg());
        ps.setDate(4, java.sql.Date.valueOf(p.getBornDate()));
        ps.setString(5, p.getFirstPhone());
        ps.setString(6, p.getSecondPhone());
        ps.setString(7, p.getProfilePicture());
        ps.setString(8, p.getBiography());
        ps.setDouble(9, p.getScore());
        ps.setString(10, p.getNotes());
        // Address
        ps.setString(11, p.getAddress().getPostalCode());
        ps.setString(12, p.getAddress().getState());
        ps.setString(13, p.getAddress().getCity());
        ps.setString(14, p.getAddress().getNeighborhood());
        ps.setString(15, p.getAddress().getStreet());
        ps.setObject(16, p.getAddress().getNumber());
        ps.setString(17, p.getAddress().getComplement());
        ps.setDouble(18, p.getAddress().getLocation().getLatitude());
        ps.setDouble(19, p.getAddress().getLocation().getLongitude());
        // Primary key
        ps.setInt(20, p.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Person p) throws SQLException {
        ps.setInt(1, p.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, Person p) throws SQLException {
        ps.setString(1, p.getType());
    }

    @Override
    protected Person fillRecord(ResultSet rs) throws SQLException {
        Person p = new Person();
        p.setId(rs.getInt("person_id"));
        p.setType(rs.getString("person_type"));
        p.setName(rs.getString("person_name"));
        p.setCpf(rs.getString("cpf"));
        p.setRg(rs.getString("rg"));
        p.setBornDate(rs.getDate("born_date").toLocalDate());
        p.setCpf(rs.getString("cpf"));
        p.setFirstPhone(rs.getString("person_first_phone"));
        p.setSecondPhone(rs.getString("person_second_phone"));
        p.setProfilePicture(rs.getString("profile_picture"));
        p.setBiography(rs.getString("biography"));
        p.setScore(rs.getDouble("person_score"));
        p.setNotes(rs.getString("person_notes"));
        p.setEmail(rs.getString("email"));
        Address address = new Address();
        address.setPostalCode(rs.getString("p_postal_code"));
        address.setState(rs.getString("p_uf"));
        address.setCity(rs.getString("p_city"));
        address.setNeighborhood(rs.getString("p_neighborhood"));
        address.setStreet(rs.getString("p_street"));
        if ( rs.getObject("p_number") != null ) address.setNumber((Integer) rs.getObject("p_number"));
        address.setComplement(rs.getString("p_complement"));
        address.setLocation( new GeoLocation(rs.getDouble("p_latitude"), rs.getDouble("p_longitude")) );
        p.setAddress(address);  
        return p;
    }

}