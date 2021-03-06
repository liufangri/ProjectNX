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
public class Course {

    public static final String[] HEADER = {"id", "name", "department_id", "start_time",
	"end_time", "position", "schedule", "category", "year",
	"semester", "max_member", "description"};
    private String id;
    private String name;
    private int department_id;
    private int start_time;
    private int end_time;
    private String position;
    private String schedule;
    private boolean category;
    private int year;
    private int semester;
    private String teachers;
    private int max_member;
    private String description;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getDepartment_id() {
	return department_id;
    }

    public void setDepartment_id(int department_id) {
	this.department_id = department_id;
    }

    public int getStart_time() {
	return start_time;
    }

    public void setStart_time(int start_time) {
	this.start_time = start_time;
    }

    public int getEnd_time() {
	return end_time;
    }

    public void setEnd_time(int end_time) {
	this.end_time = end_time;
    }

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public String getSchedule() {
	return schedule;
    }

    public void setSchedule(String schedule) {
	this.schedule = schedule;
    }

    public boolean isCategory() {
	return category;
    }

    public void setCategory(boolean category) {
	this.category = category;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public int getSemester() {
	return semester;
    }

    public void setSemester(int semester) {
	this.semester = semester;
    }

    public String getTeachers() {
	return teachers;
    }

    public void setTeachers(String teachers) {
	this.teachers = teachers;
    }

    /**
     * @return the max_member
     */
    public int getMax_member() {
	return max_member;
    }

    /**
     * @param max_member the max_member to set
     */
    public void setMax_member(int max_member) {
	this.max_member = max_member;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
