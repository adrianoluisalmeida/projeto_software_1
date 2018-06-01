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
import java.util.Collection;

/**
 *
 * @author isabella
 */
public class FeedbackDAO extends AbstractDAO<Feedback> {
    
    public FeedbackDAO() {
        setCreateSql("");
        setUpdateSql("");
        setDeleteSql("");
        setFindPrimaryKeySql("");
        setFindSql("");
        setFindAllSql("");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Feedback t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Feedback t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Feedback t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fillFind(PreparedStatement ps, Feedback t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Feedback fillRecord(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Collection<Feedback> fillCollection(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
