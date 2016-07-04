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
public class User {

    private String id;
    private String name;
    private String password;
    private int department_id;
    private String department_name;
    private String class_id;
    private int character;

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

    public String getDepartment_name() {
	return department_name;
    }

    public void setDepartment_name(String department_name) {
	this.department_name = department_name;
    }

    public String getClass_id() {
	return class_id;
    }

    public void setClass_id(String class_id) {
	this.class_id = class_id;
    }

    public int getCharacter() {
	return character;
    }

    public void setCharacter(int character) {
	this.character = character;
    }
}
