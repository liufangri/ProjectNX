/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.sql.Timestamp;

/**
 *
 * @author JOHNKYON
 */
public class Comment {
    private String id;
    private String course_id;
    private String user_name;
    private int character;
    private Timestamp timestamp;
    private String comment;

    /**
     * @return the idString
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the idString to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the course_id
     */
    public String getCourse_id() {
        return course_id;
    }

    /**
     * @param course_id the course_id to set
     */
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the character
     */
    public int getCharacter() {
        return character;
    }

    /**
     * @param character the character to set
     */
    public void setCharacter(int character) {
        this.character = character;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
