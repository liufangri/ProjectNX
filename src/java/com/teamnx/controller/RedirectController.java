/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Course;
import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.Task;
import com.teamnx.model.TaskDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理所有页面跳转的请求
 *
 * @author Y400
 */
@Controller
public class RedirectController {

    private CourseDaoImpl cdi;
    private UserDaoImpl udi;
    private TaskDaoImpl tdi;

    /**
     * 跳转到不同的usercenter
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "usercenter")
    public ModelAndView toUserCenter(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	User user = (User) session.getAttribute("user");
	ModelAndView mav = new ModelAndView("usercenter");
	if (user != null) {
	    switch (user.getCharacter()) {
		case User.STUDENT:
		    studentCenter(request, user.getId());
		    break;
		case User.TEACHER:
		    teacherCenter(request, user.getId());
		    break;
		case User.ADMIN:
		    break;
	    }
	} else {
	    response.sendRedirect("login.htm");
	}
	return mav;
    }

    /**
     * 跳转到学生中心
     *
     * @param request
     * @param id
     */
    private void studentCenter(HttpServletRequest request, String id) {
	ArrayList<Course> courses = cdi.findCoursesByStudentId(id);
	request.setAttribute("student_courses", courses);
    }

    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }

    /**
     * 处理到index页的跳转
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView toIndex(HttpServletRequest request, HttpSession session) {
	User user = (User) session.getAttribute("user");
	ModelAndView mav = new ModelAndView();
	if ((Boolean) session.getAttribute("is_login") == null) {
	    session.setAttribute("is_login", false);

	}
	String id = request.getParameter("id");
	if (id != null) {
	    Course c = cdi.findCourseById(id);
	    ArrayList<String> teacherName = udi.findTeachersByCourseId(id);
	    String names = "";
	    for (String a : teacherName) {
		names += a + " ";
	    }
	    c.setTeachers(names);
	    request.setAttribute("current_course", c);
	    mav.addObject("course", c);
	    switch (user.getCharacter()) {
		case User.STUDENT:
		    request.setAttribute("course_id", id);
		    mav.setViewName("stu_index");
		    break;
		case User.TEACHER:
		    request.setAttribute("course_id", id);
		    mav.setViewName("te_index");
		    break;
	    }
	} else {
	    mav.setViewName("login");
	}

	return mav;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

    private void teacherCenter(HttpServletRequest request, String id) {
	ArrayList<Course> courses = cdi.findCoursesByTeacherId(id);
	request.setAttribute("student_courses", courses);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 处理到老师的作业页面的跳转
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/te_homework")
    public ModelAndView teacherHomwork(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_homework");
	toTeacherHomework(mav, request);
	return mav;
    }

    /**
     * 在跳转到作业页面前的操作
     *
     * @param mav
     * @param request
     */
    public void toTeacherHomework(ModelAndView mav, HttpServletRequest request) {
	String courseId = request.getParameter("id");
	ArrayList<Task> tasks;
	tasks = tdi.findTasksByCourseId(courseId);
	request.setAttribute("tasks", tasks);
	Task task = new Task();
	mav.addObject("task", task);
	request.setAttribute("course_id", courseId);
	//到教师作业页面的别的初始条件
    }

    @RequestMapping(value = "/stu_homework")
    public ModelAndView studentHomework(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("stu_homework");
	toStudentHomeWork(mav, request);
	return mav;
    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }

    private void toStudentHomeWork(ModelAndView mav, HttpServletRequest request) {
	String courseId = request.getParameter("id");
	ArrayList<Task> tasks;
	tasks = tdi.findTasksByCourseId(courseId);
	request.setAttribute("tasks", tasks);
	Task task = new Task();
	mav.addObject("task", task);
	request.setAttribute("course_id", courseId);
	//到教师作业页面的别的初始条件
    }

}
