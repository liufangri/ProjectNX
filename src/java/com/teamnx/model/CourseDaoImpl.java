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
public class CourseDaoImpl implements CourseDao {

    private DbcpBean dbcpBean;

    @Override
    public Course findCourseById(String id) {
	Course course = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM course WHERE id = ?";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		course = new Course();
		course.setCategory(rs.getBoolean("category"));
		course.setDepartment_id(rs.getInt("department_id"));
		course.setEnd_time(rs.getInt("end_time"));
		course.setId(rs.getString("id"));
		course.setName(rs.getString("name"));
		course.setPosition(rs.getString("position"));
		course.setSchedule(rs.getString("schedule"));
                course.setMax_member(rs.getInt("max_member"));
		course.setSemester(rs.getInt("semester"));
		course.setStart_time(rs.getInt("start_time"));
		course.setYear(rs.getInt("year"));
	    }
	} catch (SQLException e) {

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return course;
	}
    }

    @Override
    public ArrayList<Course> findCoursesByName(String name) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Course> findCoursesByStudentId(String id) {
	ArrayList<Course> courses = new ArrayList<Course>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT course.* FROM (\n"
		+ "SELECT * FROM student_course\n"
		+ "WHERE student_id = ?\n"
		+ ")AS course_list INNER JOIN course\n"
		+ "ON course_list.course_id = course.id";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Course course = new Course();
		course.setCategory(rs.getBoolean("category"));
		course.setDepartment_id(rs.getInt("department_id"));
		int x = rs.getInt("end_time");
		course.setEnd_time(rs.getInt("end_time"));
		course.setId(rs.getString("id"));
		course.setName(rs.getString("name"));
		course.setPosition(rs.getString("position"));
		course.setSchedule(rs.getString("schedule"));
		course.setSemester(rs.getInt("semester"));
		course.setStart_time(rs.getInt("start_time"));
		course.setYear(rs.getInt("year"));
		courses.add(course);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return courses;
	}
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public ArrayList<Course> findCoursesByTeacherId(String id) {
	ArrayList<Course> courses = new ArrayList<Course>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT course.* FROM (\n"
		+ "SELECT * FROM teacher_course\n"
		+ "WHERE teacher_id = ?\n"
		+ ")AS course_list INNER JOIN course\n"
		+ "ON course_list.course_id = course.id";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Course course = new Course();
		course.setCategory(rs.getBoolean("category"));
		course.setDepartment_id(rs.getInt("department_id"));
		course.setEnd_time(rs.getInt("end_time"));
		course.setId(rs.getString("id"));
		course.setName(rs.getString("name"));
		course.setPosition(rs.getString("position"));
		course.setSchedule(rs.getString("schedule"));
		course.setSemester(rs.getInt("semester"));
		course.setStart_time(rs.getInt("start_time"));
		course.setYear(rs.getInt("year"));
		courses.add(course);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return courses;
	}
    }

}
