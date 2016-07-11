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
public class TeacherCourseDaoImpl implements TeacherCourseDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(TeacherCourse teacherCourse) {

	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO student_course(id,teacher_id,course_id) VALUES (?,?,?)";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, teacherCourse.getId());
	    ps.setString(2, teacherCourse.getTeacherId());
	    ps.setString(3, teacherCourse.getCourseId());
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

    @Override
    public boolean insertMulty(ArrayList<TeacherCourse> teacherCourseList) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

}
