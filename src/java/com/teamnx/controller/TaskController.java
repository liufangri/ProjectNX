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
import com.teamnx.util.MD5;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Y400
 */
@Controller
public class TaskController {

    private TaskDaoImpl tdi;
    private CourseDaoImpl cdi;

    @RequestMapping(value = "/addTask")
    public void addTask(Task task, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String str = task.getName() + new Date().getTime();
	task.setId(MD5.Md5_16(str));
	String[] strs = task.getTimeLimit().split(" - ");
	task.setStartTime(Timestamp.valueOf(strs[0] + ":00"));
	task.setDeadline(Timestamp.valueOf(strs[1] + ":00"));
	if (task.getCheck() != null) {
	    task.setText(false);
	} else {
	    task.setText(true);
	}

	Course course = cdi.findCourseById(task.getCourseId());
	task.setCategory(course.isCategory());
	task.setStatus(false);
	if (tdi.addTask(task)) {
	    request.setAttribute("course_id", course.getId());
	    response.sendRedirect("te_homework.htm?course_id=" + course.getId());
	} else {

	}
    }

    @RequestMapping(value = "/changeTask")
    public void changeTask(Task task, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String[] strs = task.getTimeLimit().split(" - ");
	task.setStartTime(Timestamp.valueOf(strs[0] + ":00"));
	task.setDeadline(Timestamp.valueOf(strs[1] + ":00"));
	if (task.getCheck() != null) {
	    task.setText(false);
	} else {
	    task.setText(true);
	}

	Course course = cdi.findCourseById(task.getCourseId());
	task.setCategory(course.isCategory());
	task.setStatus(false);
	if (tdi.updateTask(task)) {
	    request.setAttribute("course_id", course.getId());
	    response.sendRedirect("te_homework.htm?course_id=" + course.getId());
	} else {

	}
    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }

    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }
}
