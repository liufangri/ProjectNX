/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.util;

import com.teamnx.model.Course;
import com.teamnx.model.Department;
import com.teamnx.model.StudentCourse;
import com.teamnx.model.TeacherCourse;
import com.teamnx.model.User;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Y400
 */
public class CSVToDateBase {

    public static final int USER = 1;
    public static final int DEPARTMENT = 2;
    public static final int COURSE = 3;
    public static final int STUDENT_COURSE = 4;
    public static final int TEACHER_COURSE = 5;

    //CSV文件头
    private String[] file_header;

    /**
     * @param fileName
     * @param type
     * @return
     */
    public List readCsvFile(String fileName, int type) {
	FileReader fileReader = null;
	CSVParser csvFileParser = null;
	List list = null;
	//创建CSVFormat（header mapping）
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(file_header);
	try {
	    //初始化FileReader object
	    fileReader = new FileReader(fileName);
	    //初始化 CSVParser object
	    csvFileParser = new CSVParser(fileReader, csvFileFormat);
	    //CSV文件records
	    List<CSVRecord> csvRecords = csvFileParser.getRecords();
	    // CSV

	    switch (type) {
		case USER:
		    List<User> userList = new ArrayList<User>();
		    for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord record = csvRecords.get(i);
			//创建用户对象填入数据
			User user = new User();
			user.setId(record.get("id"));
			user.setName(record.get("name"));
			user.setPassword(record.get("password"));
			user.setDepartment_id(Integer.parseInt(record.get("department_id")));
			user.setCharacter(Integer.parseInt(record.get("character")));
			user.setClass_id(record.get("class_id"));
			user.setDepartment_name(record.get("department_name"));
			userList.add(user);
		    }
		    list = userList;
		    break;
		case DEPARTMENT:
		    List<Department> departmentList = new ArrayList<Department>();
		    for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord record = csvRecords.get(i);
			//创建用户对象填入数据
			Department department = new Department();
			department.setId(Integer.parseInt(record.get("id")));
			department.setName(record.get("name"));
			departmentList.add(department);
		    }
		    list = departmentList;
		    break;
		case COURSE:
		    List<Course> courseList = new ArrayList<Course>();
		    for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord record = csvRecords.get(i);
			//创建用户对象填入数据
			Course course = new Course();
			course.setId(record.get("id"));
			course.setName(record.get("name"));
			course.setDepartment_id(Integer.parseInt(record.get("department_id")));
			course.setStart_time(Integer.parseInt(record.get("start_time")));
			course.setEnd_time(Integer.parseInt(record.get("end_time")));
			course.setPosition(record.get("position"));
			course.setSchedule(record.get("schedule"));
			course.setYear(Integer.parseInt(record.get("year")));
			course.setSemester(Integer.parseInt(record.get("semester")));
			int j = Integer.parseInt(record.get("category"));
			course.setCategory(j == 1 ? true : false);
			course.setMax_member(Integer.parseInt(record.get("max_member")));
			courseList.add(course);
		    }
		    list = courseList;
		    break;
		case STUDENT_COURSE:
		    List<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
		    for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord record = csvRecords.get(i);
			StudentCourse studentCourse = new StudentCourse();
			studentCourse.setId(record.get("id"));
			studentCourse.setCourseId(record.get("course_id"));
			studentCourse.setStudentId(record.get("student_id"));
			studentCourseList.add(studentCourse);
		    }
		    list = studentCourseList;
		    break;
		case TEACHER_COURSE:
		    List<TeacherCourse> teacherCourseList = new ArrayList<TeacherCourse>();
		    for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord record = csvRecords.get(i);
			TeacherCourse teacherCourse = new TeacherCourse();
			teacherCourse.setId(record.get("id"));
			teacherCourse.setTeacherId(record.get("teacher_id"));
			teacherCourse.setCourseId(record.get("course_id"));
			teacherCourseList.add(teacherCourse);
		    }
		    list = teacherCourseList;
		    break;

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		fileReader.close();
		csvFileParser.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    } finally {
		return list;
	    }
	}
    }

    public void setFile_header(String[] file_header) {
	this.file_header = file_header;
    }

}
