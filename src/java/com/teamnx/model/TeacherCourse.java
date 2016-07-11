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
public class TeacherCourse {

    public static final String[] HEADER = {"id", "course_id", "teacher_id"};
    private String id;
    private String courseId;
    private String teacherId;

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

    public String getTeacherId() {
	return teacherId;
    }

    public void setTeacherId(String teacherId) {
	this.teacherId = teacherId;
    }

}
