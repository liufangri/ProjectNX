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
	    dbcpBean.shutDownDataSource();
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
	    dbcpBean.shutDownDataSource();
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
	    homework = new Homework();
	    homework.setId(rs.getString("id"));
	    homework.setStudentId(rs.getString("student_id"));
	    homework.setGroupId(rs.getString("group_id"));
	    homework.setFilePath(rs.getString("file_path"));
	    homework.setScore(rs.getInt("score"));
	    homework.setTaskId(rs.getString("task_id"));
	    homework.setText(rs.getString("text"));
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    dbcpBean.shutDownDataSource();
	    return homework;
	}
    }

    @Override
    public Homework findStudentHomework(String taskId, String studentId) {
	Homework homework = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM homework\n"
		+ "WHERE task_id = ? AND user_id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, studentId);
	    ps.setString(2, taskId);
	    ResultSet rs = ps.executeQuery();
	    homework = new Homework();
	    homework.setId(rs.getString("id"));
	    homework.setStudentId(rs.getString("student_id"));
	    homework.setGroupId(rs.getString("group_id"));
	    homework.setFilePath(rs.getString("file_path"));
	    homework.setScore(rs.getInt("score"));
	    homework.setTaskId(rs.getString("task_id"));
	    homework.setText(rs.getString("text"));
	} catch (SQLException ex) {
	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    dbcpBean.shutDownDataSource();
	    return homework;
	}
    }
    
    @Override
    public boolean setScore(String homeworkId, int score){
        Connection connection = dbcpBean.getConnection();
        String sql = "SET homework score = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ""+score);
            return true;
        }catch (SQLException ex) {
            	    Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    dbcpBean.shutDownDataSource();
                    return false;

        }
    }

    @Override
    public boolean setComment(String homeworkId, String comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
