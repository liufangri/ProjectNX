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
public class Message {

    private String id;
    private String receiverId;
    private String text;
    private Timestamp time;
    private boolean status;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getReceiverId() {
	return receiverId;
    }

    public void setReceiverId(String receiverId) {
	this.receiverId = receiverId;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Timestamp getTime() {
	return time;
    }

    public void setTime(Timestamp time) {
	this.time = time;
    }

    public boolean isStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }
}
