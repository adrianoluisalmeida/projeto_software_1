/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.database;

import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isabella
 * @param <T>
 */
public abstract class AbstractDAO<T extends Record> {
    
    private String createSql;
    private String updateSql;
    private String deleteSql;
    private String findOneSql;
    private String findSql;
    private String findAllSql;

    public String getCreateSql() {
        return createSql;
    }

    public void setCreateSql(String createSql) {
        this.createSql = createSql;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }

    public String getDeleteSql() {
        return deleteSql;
    }

    public void setDeleteSql(String deleteSql) {
        this.deleteSql = deleteSql;
    }

    public String getFindOneSql() {
        return findOneSql;
    }

    public void setFindOneSql(String findPrimaryKeySql) {
        this.findOneSql = findPrimaryKeySql;
    }
    
    public String getFindSql() {
        return findSql;
    }

    public void setFindSql(String findSql) {
        this.findSql = findSql;
    }

    public String getFindAllSql() {
        return findAllSql;
    }

    public void setFindAllSql(String findAllSql) {
        this.findAllSql = findAllSql;
    }
    
    public Integer create(T t) {
        Connection c = ConnectionFactory.openConnection();
        Integer id = null;
        try {
            PreparedStatement ps = c.prepareStatement(getCreateSql(), PreparedStatement.RETURN_GENERATED_KEYS);
            fillCreate(ps, t);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1); // pk
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
        return id;
    }
    
     public void update(T t) {
        Connection c = ConnectionFactory.openConnection();
        try {
            PreparedStatement ps = c.prepareStatement(getUpdateSql());
            fillUpdate(ps, t);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();;
    }

    public void delete(T t) {
        Connection c = ConnectionFactory.openConnection();
        try {
            PreparedStatement ps = c.prepareStatement(getDeleteSql());
            fillDelete(ps, t);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
    }
    
    //Find by PrimaryKey
    @Deprecated
    public T findPrimaryKey(T t) {
        Connection c = ConnectionFactory.openConnection();
        T record = null;
        try {
            PreparedStatement ps = c.prepareStatement(getFindOneSql());
            fillDelete(ps, t);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
                record = fillRecord(rs);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
        return record;
    }
    
    /**
     * New method to find by primary key
     * @param id - primary key
     * @return Record
     */
    public T findOne(Object id) {
        Connection c = ConnectionFactory.openConnection();
        T record = null;
        try {
            PreparedStatement ps = c.prepareStatement(getFindOneSql());
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
                record = fillRecord(rs);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
        return record;
    }
    
    //Find by any atribute
    public List<T> find(T t) {
        Connection c = ConnectionFactory.openConnection();
        List<T> records = null;
        try {
            PreparedStatement ps = c.prepareStatement(getFindSql());
            fillFind(ps, t);
            ResultSet rs = ps.executeQuery();
            records = fillList(rs);
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
        return records;
    }
    
    public List<T> findAll() {
        Connection c = ConnectionFactory.openConnection();
        List<T> records = null;
        try {
            PreparedStatement ps = c.prepareStatement(getFindAllSql());
            ResultSet rs = ps.executeQuery();
            records = fillList(rs);
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
        return records;
    }
    
    /**
     * Finds record(s) with any filter
     * @param filter - sql with number of params equals the lenght of array
     * @param params - array of params
     * @return List with result
     */
    public List<T> find(String filter, Object[] params) {
        Connection c = ConnectionFactory.openConnection();
        List<T> records = null;
        try {
            String sql = String.format("%s WHERE %s", getFindAllSql(), filter);
            PreparedStatement ps = c.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            records = fillList(rs);
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionFactory.closeConnection();
        return records;
    }
    
    /**
     * Finds record(s) with any filter
     * @param filter - sql with single param
     * @param param - parameter
     * @return List with result
     */
    public List<T> find(String filter, Object param) {
        return find(filter, new Object[]{param});
    }
    
    protected abstract void fillCreate(PreparedStatement ps, T t) throws SQLException;

    protected abstract void fillUpdate(PreparedStatement ps, T t) throws SQLException;

    protected abstract void fillDelete(PreparedStatement ps, T t) throws SQLException;

    protected abstract void fillFind(PreparedStatement ps, T t) throws SQLException;

    protected abstract T fillRecord(ResultSet rs) throws SQLException;

    protected List<T> fillList(ResultSet rs) throws SQLException{
        List<T> collection = new ArrayList<>();
        while(rs.next())
            collection.add(fillRecord(rs));
        return collection;
    }
    
}
