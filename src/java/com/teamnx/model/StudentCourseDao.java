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
public interface StudentCourseDao {

    public boolean insert(StudentCourse studentCourse);

    public boolean insertMulty(ArrayList<StudentCourse> studentCourseList);
}
