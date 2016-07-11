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
public interface CourseDao {

    public Course findCourseById(String id);

    public ArrayList<Course> findCoursesByName(String name);

    public ArrayList<Course> findCoursesByStudentId(String id, int year, int semester);

    public ArrayList<Course> findCoursesByTeacherId(String id, int year, int semester);
}
