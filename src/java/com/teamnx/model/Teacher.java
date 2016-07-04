/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

/**
 * 教师的实体类
 *
 * @author Y400
 */
public class Teacher {

    private String id;
    private String name;
    private String password;
    private int department_id;

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

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public int getDepartment_id() {
	return department_id;
    }

    public void setDepartment_id(int department_id) {
	this.department_id = department_id;
    }
}
