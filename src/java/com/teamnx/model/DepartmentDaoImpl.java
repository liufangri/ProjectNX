/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import com.teamnx.db.DbcpBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Y400
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(Department department) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertMulty(ArrayList<Department> departmentList) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO department (id, name) VALUES(?,?)";
	try {
	    connection.setAutoCommit(false);
	    PreparedStatement ps = connection.prepareStatement(sql);
	    for (Department department : departmentList) {
		ps.setInt(1, department.getId());
		ps.setString(2, department.getName());
		ps.executeUpdate();

	    }
	    connection.commit();
	    connection.setAutoCommit(true);
	    return true;

	} catch (SQLException ex) {
	    Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    try {
		connection.rollback();
	    } catch (SQLException ex1) {
		Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
	    } finally {
		return false;
	    }
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

}
