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
public class Homework {

    private String id;
    private String studentId;
    private String groupId;
    private String courseId;
    private String filePath;
    private int score;
    private String taskId;
    private String text;
    private String comment;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getStudentId() {
	return studentId;
    }

    public void setStudentId(String studentId) {
	this.studentId = studentId;
    }

    public String getGroupId() {
	return groupId;
    }

    public void setGroupId(String groupId) {
	this.groupId = groupId;
    }

    public String getCourseId() {
	return courseId;
    }

    public void setCourseId(String courseId) {
	this.courseId = courseId;
    }

    public String getFilePath() {
	return filePath;
    }

    public void setFilePath(String filePath) {
	this.filePath = filePath;
    }

    public int getScore() {
	return score;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public String getTaskId() {
	return taskId;
    }

    public void setTaskId(String taskId) {
	this.taskId = taskId;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
