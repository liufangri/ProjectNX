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
public class Task {

    private String id;
    private String courseId;
    private String teacherId;
    private String description;
    private int maxScore;
    private String timeLimit;
    private long startTime;
    private long deadline;
    private boolean text;
    private boolean category;
    private boolean status;

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

    public long getStartTime() {
	return startTime;
    }

    public void setStartTime(long startTime) {
	this.startTime = startTime;
    }

    public long getDeadline() {
	return deadline;
    }

    public void setDeadline(long deadline) {
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
}
