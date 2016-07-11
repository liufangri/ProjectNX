/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import com.teamnx.db.DbcpBean;
import java.util.ArrayList;

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
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

}
