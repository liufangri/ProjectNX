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
public interface TeacherCourseDao {

    public boolean insert(TeacherCourse teacherCourse);

    public boolean insertMulty(ArrayList<TeacherCourse> teacherCourseList);
}
