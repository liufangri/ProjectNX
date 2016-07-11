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
    public ArrayList<Course> findCoursesByStudentId(String id, int year, int semester) {
	ArrayList<Course> courses = new ArrayList<Course>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT course.* FROM (\n"
		+ "SELECT * FROM student_course\n"
		+ "WHERE student_id = ?\n"
		+ ")AS course_list INNER JOIN course\n"
		+ "ON course_list.course_id = course.id "
		+ "WHERE year = ? AND semester = ?";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ps.setInt(2, year);
	    ps.setInt(3, semester);
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
		course.setYear(rs.getInt("year"));
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
    public ArrayList<Course> findCoursesByTeacherId(String id, int year, int semester) {
	ArrayList<Course> courses = new ArrayList<Course>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT course.* FROM (\n"
		+ "SELECT * FROM teacher_course\n"
		+ "WHERE teacher_id = ?\n"
		+ ")AS course_list INNER JOIN course\n"
		+ "ON course_list.course_id = course.id "
		+ "WHERE year = ? AND semester = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ps.setInt(2, year);
	    ps.setInt(3, semester);
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
		course.setYear(rs.getInt("year"));
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

    @Override
    public boolean insertMulty(ArrayList<Course> courseList) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO COURSE "
		+ "(id,name,department_id,"
		+ "start_time,end_time,position,schedule,"
		+ "category,year,semester,max_member,description)"
		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	try {
	    connection.setAutoCommit(false);
	    PreparedStatement ps = connection.prepareStatement(sql);
	    for (Course course : courseList) {
		ps.setString(1, course.getId());
		ps.setString(2, course.getName());
		ps.setInt(3, course.getDepartment_id());
		ps.setInt(4, course.getStart_time());
		ps.setInt(5, course.getEnd_time());
		ps.setString(6, course.getPosition());
		ps.setString(7, course.getSchedule());
		ps.setBoolean(8, course.isCategory());
		ps.setInt(9, course.getYear());
		ps.setInt(10, course.getSemester());
		ps.setInt(11, course.getMax_member());
		ps.setString(12, course.getDescription());
		ps.executeUpdate();
	    }
	    connection.commit();
	    connection.setAutoCommit(true);
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    try {
		connection.rollback();
	    } catch (SQLException ex1) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
	    }
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

}
