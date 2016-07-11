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
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return user;
	}
    }

    @Override
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
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return teacherNames;
	}
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public ArrayList<User> findStudentsByCourseId(String courseId) {
	ArrayList<User> users = new ArrayList<User>();
	Connection connection = dbcpBean.getConnection();
	String sql = "select student_course.student_id, user.name \n"
		+ "from student_course,user\n"
		+ " where student_course.course_id = ? \n"
		+ " and student_course.student_id = user.id";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, courseId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		User user = new User();
		user.setId(rs.getString("student_id"));
		user.setName(rs.getString("name"));
		users.add(user);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return users;
	}
    }

    @Override
    public ArrayList<User> findStudentsNotInGroup(String courseId) {
	ArrayList<User> users = new ArrayList<User>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM user \n"
		+ "WHERE user.character = 1 AND id NOT IN (\n"
		+ "SELECT student_id FROM student_group WHERE course_id = ?\n"
		+ ")";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, courseId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setClass_id(rs.getString("class_id"));
		user.setDepartment_id(rs.getInt("department_id"));
		user.setDepartment_name(rs.getString("department_name"));
		user.setName(rs.getString("name"));
		users.add(user);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return users;
	}
    }

    @Override
    public boolean insertMulty(ArrayList<User> userList) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO user "
		+ "(id,name,password,department_id,department_name,"
		+ "class_id,user.character) VALUES (?,?,?,?,?,?,?)";

	try {
	    connection.setAutoCommit(false);
	    PreparedStatement ps = connection.prepareStatement(sql);
	    for (User user : userList) {
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.setInt(4, user.getDepartment_id());
		ps.setString(5, user.getDepartment_name());
		ps.setString(6, user.getClass_id());
		ps.setInt(7, user.getCharacter());
		ps.executeUpdate();
	    }
	    connection.commit();
	    connection.setAutoCommit(true);
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    try {
		connection.rollback();

	    } catch (SQLException ex1) {
		Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
	    } finally {
		return false;
	    }
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
}
