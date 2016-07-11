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
public class StudentCourseDaoImpl implements StudentCourseDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(StudentCourse studentCourse) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO student_course(id,student_id,course_id) VALUES (?,?,?)";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, studentCourse.getId());
	    ps.setString(2, studentCourse.getStudentId());
	    ps.setString(3, studentCourse.getCourseId());
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(StudentCourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(StudentCourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public boolean insertMulty(ArrayList<StudentCourse> studentCourseList) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
