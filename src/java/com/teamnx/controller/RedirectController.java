/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Course;
import com.teamnx.model.CourseDaoImpl;
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
 *
 * @author Y400
 */
@Controller
public class RedirectController {

    private CourseDaoImpl cdi;
    private UserDaoImpl udi;

    /**
     * 跳转到不同的usercenter
     *
     * @param request
     * @param response
     * @param session
     * @param user
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

    @RequestMapping(value = "index")
    public ModelAndView toIndex(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("index");
	if ((Boolean) session.getAttribute("is_login") == null) {
	    session.setAttribute("is_login", false);

	}

	String id = request.getParameter("id");
	Course c = cdi.findCourseById(id);
	ArrayList<String> teacherName = udi.findTeachersByCourseId(id);
	String names = "";
	for (String a : teacherName) {
	    names += a + " ";
	}
	c.setTeachers(names);
	mav.addObject("course", c);
	return mav;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

}
