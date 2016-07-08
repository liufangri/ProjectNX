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
public class TaskDaoImpl implements TaskDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean addTask(Task task) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO task values (?,?,?,?,?,?,?,?,?,?,?,?)";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, task.getId());
	    ps.setString(2, task.getCourseId());
	    ps.setString(3, task.getTeacherId());
	    ps.setString(4, task.getDescription());
	    ps.setInt(5, task.getMaxScore());
	    ps.setTimestamp(6, task.getStartTime());
	    ps.setTimestamp(7, task.getDeadline());
	    ps.setBoolean(8, task.isText());
	    ps.setBoolean(9, task.isCategory());
	    ps.setBoolean(10, task.isStatus());
	    ps.setString(11, task.getName());
	    ps.setString(12, task.getTeacherName());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean updateTask(Task task) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE task "
		+ "SET course_id = ?, teacher_id = ?, description = ?,"
		+ "max_score = ?, start_time = ?, deadline = ?,"
		+ "istext = ?, category = ?, status = ?,"
		+ "name = ?, teacher_name = ? WHERE id=?";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, task.getCourseId());
	    ps.setString(2, task.getTeacherId());
	    ps.setString(3, task.getDescription());
	    ps.setInt(4, task.getMaxScore());
	    ps.setTimestamp(5, task.getStartTime());
	    ps.setTimestamp(6, task.getDeadline());
	    ps.setBoolean(7, task.isText());
	    ps.setBoolean(8, task.isCategory());
	    ps.setBoolean(9, task.isStatus());
	    ps.setString(10, task.getName());
	    ps.setString(11, task.getTeacherName());
	    ps.setString(12, task.getId());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public ArrayList<Task> findTasksByCourseId(String courseId) {
	ArrayList<Task> tasks = new ArrayList<Task>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM task WHERE course_id = ? ORDER BY start_time ASC";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, courseId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Task task = new Task();
		task.setId(rs.getString("id"));
		task.setCourseId(rs.getString("course_id"));
		task.setTeacherId(rs.getString("teacher_id"));
		task.setDescription(rs.getString("description"));
		task.setMaxScore(rs.getInt("max_score"));
		task.setStartTime(rs.getTimestamp("start_time"));
		task.setDeadline(rs.getTimestamp("deadline"));
		task.setText(rs.getBoolean("istext"));
		task.setCategory(rs.getBoolean("category"));
		task.setStatus(rs.getBoolean("status"));
		task.setName(rs.getString("name"));
		task.setTeacherName(rs.getString("teacher_name"));
		tasks.add(task);

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return tasks;
	}

    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public Task findTaskById(String taskId) {
	Task task = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM task WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, taskId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		task = new Task();
		task.setId(rs.getString("id"));
		task.setCourseId(rs.getString("course_id"));
		task.setTeacherId(rs.getString("teacher_id"));
		task.setDescription(rs.getString("description"));
		task.setMaxScore(rs.getInt("max_score"));
		task.setStartTime(rs.getTimestamp("start_time"));
		task.setDeadline(rs.getTimestamp("deadline"));
		task.setText(rs.getBoolean("istext"));
		task.setCategory(rs.getBoolean("category"));
		task.setStatus(rs.getBoolean("status"));
		task.setName(rs.getString("name"));
		task.setTeacherName(rs.getString("teacher_name"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return task;
	}
    }

}
