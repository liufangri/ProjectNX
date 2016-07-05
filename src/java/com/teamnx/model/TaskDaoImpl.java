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
import java.sql.Timestamp;
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
	String sql = "INSERT INTO task values (?,?,?,?,?,?,?,?,?)";
	PreparedStatement ps;
	try {
	    ps = connection.prepareStatement(sql);
	    ps.setString(1, task.getId());
	    ps.setString(2, task.getCourseId());
	    ps.setString(3, task.getTeacherId());
	    ps.setString(4, task.getDescription());
	    ps.setInt(5, task.getMaxScore());
	    ps.setTimestamp(6, new Timestamp(task.getStartTime()));
	    ps.setTimestamp(7, new Timestamp(task.getDeadline()));
	    ps.setBoolean(8, task.isText());
	    ps.setBoolean(9, task.isCategory());
	    ps.setBoolean(10, task.isStatus());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    dbcpBean.shutDownDataSource();
	}
    }

    @Override
    public boolean updateTask(Task task) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> findTasksByCourseId(String courseId) {
	ArrayList<Task> tasks = new ArrayList<Task>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM task WHERE course_id = ? ORDER BY deadline ASC";
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
		task.setStartTime(rs.getTimestamp("start_time").getTime());
		task.setDeadline(rs.getTimestamp("deadline").getTime());
		task.setText(rs.getBoolean("istext"));
		task.setCategory(rs.getBoolean("category"));
		task.setStatus(rs.getBoolean("status"));
		tasks.add(task);

	    }
	} catch (SQLException ex) {

	    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    dbcpBean.shutDownDataSource();
	    return tasks;
	}

    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

}
