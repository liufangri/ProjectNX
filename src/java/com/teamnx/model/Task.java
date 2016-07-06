/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.sql.Timestamp;

/**
 *
 * @author Y400
 */
public class Task {

    private String id;
    private String courseId;
    private String teacherId;
    private String description;
    private int maxScore;
    private String timeLimit;
    private Timestamp startTime;
    private Timestamp deadline;
    private boolean text;
    private boolean category;
    private boolean status;
    private String[] check;
    private String name;
    private String teacherName;

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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getMaxScore() {
	return maxScore;
    }

    public void setMaxScore(int maxScore) {
	this.maxScore = maxScore;
    }

    public Timestamp getStartTime() {
	return startTime;
    }

    public void setStartTime(Timestamp startTime) {
	this.startTime = startTime;
    }

    public Timestamp getDeadline() {
	return deadline;
    }

    public void setDeadline(Timestamp deadline) {
	this.deadline = deadline;
    }

    public boolean isText() {
	return text;
    }

    public void setText(boolean text) {
	this.text = text;
    }

    public boolean isCategory() {
	return category;
    }

    public void setCategory(boolean category) {
	this.category = category;
    }

    public boolean isStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }

    public String getTimeLimit() {
	return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
	this.timeLimit = timeLimit;
    }

    public String[] getCheck() {
	return check;
    }

    public void setCheck(String[] check) {
	this.check = check;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getTeacherName() {
	return teacherName;
    }

    public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
    }
}
