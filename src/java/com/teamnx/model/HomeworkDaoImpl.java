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
public class HomeworkDaoImpl implements HomeworkDao {

    private DbcpBean dbcpBean;

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public Homework findHomeworkById(String homeworkId) {
	Homework homework = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM homework WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, homeworkId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		homework = new Homework();
		homework.setId(rs.getString("id"));
		homework.setStudentId(rs.getString("student_id"));
		homework.setGroupId(rs.getString("group_id"));
		homework.setFilePath(rs.getString("file_path"));
		homework.setScore(rs.getInt("score"));
		homework.setTaskId(rs.getString("task_id"));
		homework.setText(rs.getString("text"));

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return homework;
	}

    }

    @Override
    public ArrayList<Homework> findHomeworksByCourseId(String courseId) {
	ArrayList<Homework> homeworks = new ArrayList<Homework>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM homework WHERE course_id=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, courseId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {

		Homework homework = new Homework();
		homework.setId(rs.getString("id"));
		homework.setStudentId(rs.getString("student_id"));
		homework.setGroupId(rs.getString("group_id"));
		homework.setFilePath(rs.getString("file_path"));
		homework.setScore(rs.getInt("score"));
		homework.setTaskId(rs.getString("task_id"));
		homework.setText(rs.getString("text"));
		homeworks.add(homework);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return homeworks;
	}

    }

    @Override
    public ArrayList<Homework> findHomeworksByTaskId(String taskId) {
	ArrayList<Homework> homeworks = new ArrayList<Homework>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM homework WHERE task_id=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, taskId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {

		Homework homework = new Homework();
		homework.setId(rs.getString("id"));
		homework.setCourseId(rs.getString("course_id"));
		homework.setStudentId(rs.getString("student_id"));
		homework.setGroupId(rs.getString("group_id"));
		homework.setFilePath(rs.getString("file_path"));
		homework.setScore(rs.getInt("score"));
		homework.setTaskId(rs.getString("task_id"));
		homework.setText(rs.getString("text"));
		homework.setComment(rs.getString("comment"));
		homework.setStudentName(rs.getString("student_name"));
		homeworks.add(homework);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return homeworks;
	}

    }

    @Override
    public Homework findGroupHomework(String taskId, String studentId) {
	Homework homework = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT homework.* FROM\n"
		+ "student_group INNER JOIN homework\n"
		+ "ON student_group.group_id = homework.group_id\n"
		+ "WHERE student_group.student_id =? AND homework.task_id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, studentId);
	    ps.setString(2, taskId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		homework = new Homework();
		homework.setId(rs.getString("id"));
		homework.setCourseId(rs.getString("course_id"));
		homework.setStudentId(rs.getString("student_id"));
		homework.setGroupId(rs.getString("group_id"));
		homework.setFilePath(rs.getString("file_path"));
		homework.setScore(rs.getInt("score"));
		homework.setTaskId(rs.getString("task_id"));
		homework.setText(rs.getString("text"));
		homework.setComment(rs.getString("comment"));
		homework.setStudentName(rs.getString("student_name"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return homework;
	}
    }

    @Override
    public Homework findStudentHomework(String taskId, String studentId) {
	Homework homework = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM homework\n"
		+ "WHERE task_id = ? AND student_id = ?;";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, taskId);
	    ps.setString(2, studentId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		homework = new Homework();
		homework.setId(rs.getString("id"));
		homework.setCourseId(rs.getString("course_id"));
		homework.setStudentId(rs.getString("student_id"));
		homework.setGroupId(rs.getString("group_id"));
		homework.setFilePath(rs.getString("file_path"));
		homework.setScore(rs.getInt("score"));
		homework.setTaskId(rs.getString("task_id"));
		homework.setText(rs.getString("text"));
		homework.setComment(rs.getString("comment"));
		homework.setStudentName(rs.getString("student_name"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return homework;
	}
    }

    @Override
    public boolean setScore(String homeworkId, int score) {

	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE homework SET score = ? WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setInt(1, score);
	    ps.setString(2, homeworkId);
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

    @Override
    public boolean insert(Homework homework) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO homework VALUES (?,?,?,?,?,?,?,?,?,?)";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, homework.getId());
	    ps.setString(2, homework.getStudentId());
	    ps.setString(3, homework.getGroupId());
	    ps.setString(4, homework.getCourseId());
	    ps.setString(5, homework.getFilePath());
	    ps.setInt(6, homework.getScore());
	    ps.setString(7, homework.getTaskId());
	    ps.setString(8, homework.getText());
	    ps.setString(9, homework.getComment());
	    ps.setString(10, homework.getStudentName());
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

    @Override
    public boolean setComment(String homeworkId, String comment) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE homework SET comment = ? WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, comment);
	    ps.setString(2, homeworkId);
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    @Override
    public boolean update(Homework homework) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE homework SET file_path = ?,score = 0, text = ? WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, homework.getFilePath());
	    ps.setString(2, homework.getText());
	    ps.setString(3, homework.getId());
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
}
