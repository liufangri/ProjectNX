/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.ArrayList;

/**
 *
 * @author JOHNKYON
 */
public interface CommentDao {
    public boolean insert(Comment comment);
    
    public ArrayList<Comment> findCommentsByCourse(String courseId);
}
