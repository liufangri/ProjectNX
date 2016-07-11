/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Course;
import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.Department;
import com.teamnx.model.DepartmentDaoImpl;
import com.teamnx.model.Semester;
import com.teamnx.model.SemesterDaoImpl;
import com.teamnx.model.StudentCourse;
import com.teamnx.model.StudentCourseDaoImpl;
import com.teamnx.model.TeacherCourse;
import com.teamnx.model.TeacherCourseDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.CSVToDateBase;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JOHNKYON
 */
@Controller
public class AdminController {

    private SemesterDaoImpl smdi;
    private CSVToDateBase csvTool;
    private UserDaoImpl udi;
    private CourseDaoImpl cdi;
    private DepartmentDaoImpl ddi;
    private StudentCourseDaoImpl scdi;
    private TeacherCourseDaoImpl tcdi;
    public static final String[] SEMESTERS = {"", "秋季", "春季", "夏季"};
    public static final String[] YEARS = {"2010年", "2011年", "2012年", "2013年", "2014年",
	"2015年", "2016年", "2017年", "2018年", "2019年", "2020年",
	"2021年", "2022年", "2023年", "2024年", "2025年", "2026年"};

    /**
     * @param smdi the smdi to set
     */
    public void setSmdi(SemesterDaoImpl smdi) {
	this.smdi = smdi;
    }

    @RequestMapping("/setSemester")
    public void setSemester(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
	    String yearStr = request.getParameter("Q1");
	    String semesterStr = request.getParameter("Q2");
	    String startTimeStr = request.getParameter("timeLimit");
//	    startTimeStr = startTimeStr + " 00:00:00";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = sdf.parse(startTimeStr);
	    int year = Integer.parseInt(yearStr.substring(0, yearStr.length() - 1));
	    int semesterNum = 0;
	    for (int i = 0; i < SEMESTERS.length; i++) {
		if (semesterStr.equals(SEMESTERS[i])) {
		    semesterNum = i;
		    break;
		}
	    }
	    Semester semester = new Semester();
	    semester.setYear(year);
	    semester.setSemester(semesterNum);
	    semester.setStart_day(date);

	    switch (smdi.ifExit(semester)) {
		case -1:
		    response.sendRedirect("usercenter.htm");
		    break;
		case 0:
		    smdi.insert(semester);
		    response.sendRedirect("usercenter.htm");
		    break;
		default:
		    smdi.deleteAll();
		    smdi.insert(semester);
		    response.sendRedirect("usercenter.htm");
		    break;
	    }
	} catch (ParseException ex) {
	    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @RequestMapping(value = "/setDataBase")
    public ModelAndView setDataBase(MultipartFile fileUser, MultipartFile fileCourse,
	    MultipartFile fileDepartment, MultipartFile fileStudentCourse,
	    MultipartFile fileTeacherCourse,
	    HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	ModelAndView mav = new ModelAndView("upload_database");
	String pathPre = session.getServletContext().getRealPath("/WEB-INF") + "\\Database\\";
	String userFilePath = pathPre + "user.csv";
	File userCsv = new File(userFilePath);
	if (!userCsv.exists()) {
	    userCsv.mkdirs();
	}
	fileUser.transferTo(userCsv);
	String departmentFilePath = pathPre + "department.csv";
	File departmentCsv = new File(departmentFilePath);
	if (!departmentCsv.exists()) {
	    departmentCsv.mkdirs();
	}
	fileDepartment.transferTo(departmentCsv);
	String courseFilePath = pathPre + "course.csv";
	File courseCsv = new File(courseFilePath);
	if (!courseCsv.exists()) {
	    courseCsv.mkdirs();
	}
	fileCourse.transferTo(courseCsv);
	String studentCourseFilePath = pathPre + "student_course.csv";
	File studentCourseCsv = new File(studentCourseFilePath);
	if (!studentCourseCsv.exists()) {
	    studentCourseCsv.mkdirs();
	}
	fileStudentCourse.transferTo(studentCourseCsv);
	String teacherCourseFilePath = pathPre + "teacher_course.csv";
	File teacherCourseCsv = new File(teacherCourseFilePath);
	if (!teacherCourseCsv.exists()) {
	    teacherCourseCsv.mkdirs();
	}
	fileTeacherCourse.transferTo(teacherCourseCsv);

	/**
	 * 导入数据库的过程：
	 */
	//department:
	ArrayList<Department> departmentList;
	ArrayList<Course> courseList;
	ArrayList<User> userList;
	ArrayList<TeacherCourse> teacherCourseList;
	ArrayList<StudentCourse> studentCourseList;
	csvTool.setFile_header(Department.HEADER);
	departmentList = (ArrayList<Department>) csvTool.readCsvFile(departmentFilePath, CSVToDateBase.DEPARTMENT);
	csvTool.setFile_header(Course.HEADER);
	courseList = (ArrayList<Course>) csvTool.readCsvFile(courseFilePath, CSVToDateBase.COURSE);
	csvTool.setFile_header(User.HEADER);
	userList = (ArrayList<User>) csvTool.readCsvFile(userFilePath, CSVToDateBase.USER);
	csvTool.setFile_header(TeacherCourse.HEADER);
	teacherCourseList = (ArrayList<TeacherCourse>) csvTool.readCsvFile(teacherCourseFilePath, CSVToDateBase.TEACHER_COURSE);
	csvTool.setFile_header(StudentCourse.HEADER);
	studentCourseList = (ArrayList<StudentCourse>) csvTool.readCsvFile(studentCourseFilePath, CSVToDateBase.STUDENT_COURSE);

	mav.addObject("message", "数据库导入失败");
	if (!ddi.insertMulty(departmentList)) {
	    //批量插入学院失败
	    mav.addObject("message", "department导入失败");
	    return mav;
	}
	if (!cdi.insertMulty(courseList)) {
	    //批量插入课程失败
	    mav.addObject("message", "course导入失败");
	    return mav;
	}
	if (!udi.insertMulty(userList)) {
	    //批量插入用户失败
	    mav.addObject("message", "user导入失败");
	    return mav;
	}
	if (!tcdi.insertMulty(teacherCourseList)) {
	    //批量插入教师授课表失败
	    mav.addObject("message", "teacher_course导入失败");
	    return mav;
	}
	if (!scdi.insertMulty(studentCourseList)) {
	    //批量插入学生选课表成功
	    mav.addObject("message", "student_course导入失败");

	    return mav;
	}
	mav.addObject("message", "数据库导入成功");
	mav.setViewName("admin");
	return mav;
    }

    @RequestMapping(value = "/uploadPage")
    public ModelAndView toUploadPage(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("upload_database");
	return mav;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView toAdmin(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("admin");
	return mav;
    }

    public void setCsvTool(CSVToDateBase csvTool) {
	this.csvTool = csvTool;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }

    public void setDdi(DepartmentDaoImpl ddi) {
	this.ddi = ddi;
    }

    public void setScdi(StudentCourseDaoImpl scdi) {
	this.scdi = scdi;
    }

    public void setTcdi(TeacherCourseDaoImpl tcdi) {
	this.tcdi = tcdi;
    }
}
