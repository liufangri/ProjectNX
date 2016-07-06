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
public class Resource {

    private String name;
    private String fatherId;
    private String id;
    private String path;
    private String teacherId;
    private String teacherName;
    private long lastChange;
    private boolean folder;
    private long size;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getFatherId() {
	return fatherId;
    }

    public void setFatherId(String fatherId) {
	this.fatherId = fatherId;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getTeacherId() {
	return teacherId;
    }

    public void setTeacherId(String teacherId) {
	this.teacherId = teacherId;
    }

    public long getLastChange() {
	return lastChange;
    }

    public void setLastChange(long lastChange) {
	this.lastChange = lastChange;
    }

    public boolean isFolder() {
	return folder;
    }

    public void setFolder(boolean folder) {
	this.folder = folder;
    }

    public long getSize() {
	return size;
    }

    public void setSize(long size) {
	this.size = size;
    }

    public String getTeacherName() {
	return teacherName;
    }

    public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
    }
}
