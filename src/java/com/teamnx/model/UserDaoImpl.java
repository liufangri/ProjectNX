/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import com.teamnx.db.DbcpBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Y400
 */
public class UserDaoImpl implements UserDao {

    private DbcpBean dbcpBean;

    @Override
    public User findUserById(String id) {
	User user = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM user WHERE id=?";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		user = new User();
		user.setId(id);
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setCharacter(rs.getInt("character"));
		user.setClass_id(rs.getString("class_id"));
		user.setDepartment_id(rs.getInt("department_id"));
		user.setDepartment_name(rs.getString("department_name"));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	//测试用
	dbcpBean.shutDownDataSource();
	//正常使用时使用
//	try {
//
//	    connection.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//	}
	return user;

    }

    public ArrayList<String> findTeachersByCourseId(String id) {
	ArrayList<String> teacherNames = new ArrayList<String>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT user.name FROM(\n"
		+ "SELECT * FROM teacher_course\n"
		+ "WHERE course_id = ?\n"
		+ ")AS teacher_list INNER JOIN user ON teacher_list.teacher_id = user.id";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		String name = rs.getString("name");
		teacherNames.add(name);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	//测试用
	dbcpBean.shutDownDataSource();
	//正常使用时使用
//	try {
//
//	    connection.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//	}
	return teacherNames;
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

}
