/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.ArrayList;

/**
 *
 * @author Y400
 */
public interface UserDao {

    public User findUserById(String id);

    public ArrayList<User> findStudentsByCourseId(String courseId);

    public ArrayList<String> findTeachersByCourseId(String courseId);

    public ArrayList<User> findStudentsNotInGroup(String courseId);

    public boolean insertMulty(ArrayList<User> userList);

}
