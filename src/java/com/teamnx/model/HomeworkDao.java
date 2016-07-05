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
public interface HomeworkDao {

    public Homework findHomeworkById(String homeworkId);

    public ArrayList<Homework> findHomeworksByCourseId(String courseId);

    public Homework findGroupHomework(String taskId, String studentId);

    public Homework findStudentHomework(String taskId, String studentId);

    public boolean setScore(String homeworkId, int score);

    public boolean setComment(String homeworkId, String comment);

    public boolean insert(Homework homework);
}
