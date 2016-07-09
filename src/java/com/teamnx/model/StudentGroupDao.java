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
public interface StudentGroupDao {

    public boolean insert(StudentGroup studentGroup);

    public boolean delete(String id);
    
    public StudentGroup findById(String id);
    
    public StudentGroup findByCourseGroup(String courseId, String groupId);
    
    public StudentGroup findByStudentGroup(String studentId, String groupId);

    public ArrayList<StudentGroup> findstudentGroupsByGroupId(String groupId);

    public boolean ifInGroup(String studentId, String courseId);

    public ArrayList<StudentGroup> findApplyList(String groupId);
    
    public boolean deleteByGroupId(String groupId);

    public boolean deleteByStudentId(String studentId, String groupId);

    public int countMember(String groudId);
    
    public boolean accpet(String sgId);
}
