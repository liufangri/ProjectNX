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
public interface GroupDao {
    public boolean insert(Group group);

    public Group findGroupById(String id);

    public Group findGroupByStudentId(String student_id, String course_id);
    
    public ArrayList<Group> findGroupsByCourseId(String course_id);
}
