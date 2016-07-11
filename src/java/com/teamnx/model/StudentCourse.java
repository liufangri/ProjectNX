/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

/**
 *
 * @author Y400
 */
public class StudentCourse {

    public static final String[] HEADER = {"id", "student_id", "course_id"};
    private String id;
    private String courseId;
    private String studentId;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getCourseId() {
	return courseId;
    }

    public void setCourseId(String courseId) {
	this.courseId = courseId;
    }

    public String getStudentId() {
	return studentId;
    }

    public void setStudentId(String studentId) {
	this.studentId = studentId;
    }

}
