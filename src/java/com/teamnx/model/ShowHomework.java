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
public class ShowHomework {

    private String homeworkId;
    private String taskId;
    private String taskName;
    private int score;
    private String startTime;
    private String deadLine;
    private boolean state;

    public String getHomeworkId() {
	return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
	this.homeworkId = homeworkId;
    }

    public String getTaskId() {
	return taskId;
    }

    public void setTaskId(String taskId) {
	this.taskId = taskId;
    }

    public String getTaskName() {
	return taskName;
    }

    public void setTaskName(String taskName) {
	this.taskName = taskName;
    }

    public int getScore() {
	return score;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public String getDeadLine() {
	return deadLine;
    }

    public void setDeadLine(String deadLine) {
	this.deadLine = deadLine;
    }

    public boolean getState() {
	return state;
    }

    public void setState(boolean state) {
	this.state = state;
    }

}
