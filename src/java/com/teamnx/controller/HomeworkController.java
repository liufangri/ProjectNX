/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Course;
import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.Homework;
import com.teamnx.model.HomeworkDaoImpl;
import com.teamnx.model.Task;
import com.teamnx.model.TaskDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.MD5;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Y400
 */
@Controller
public class HomeworkController {

    private HomeworkDaoImpl hdi;
    private CourseDaoImpl cdi;
    private UserDaoImpl udi;
    private TaskDaoImpl tdi;

    public void setHdi(HomeworkDaoImpl hdi) {
	this.hdi = hdi;
    }

    @RequestMapping(value = "/submitHomework")
    public void submitHomework(Homework homework, HttpServletRequest request, HttpServletResponse response,
	    MultipartFile uploadFile, HttpSession session) throws IOException {
	Course course = cdi.findCourseById(homework.getCourseId());
	User user = udi.findUserById(homework.getStudentId());
	Task task = tdi.findTaskById(homework.getTaskId());
	if (homework.getId() == null) {
	    String id = MD5.Md5_16(homework.getTaskId() + homework.getStudent_name() + new Date().getTime());
	    homework.setId(id);
	    if (!course.isCategory()) {
		//是分组
	    } else if (task.isText() || uploadFile.isEmpty()) {
		if (hdi.insert(homework)) {
		    response.sendRedirect("stu_homework.htm?id=" + homework.getCourseId());
		} else {

		}
	    } else {

		String realPath = session.getServletContext().getRealPath("/WEB-INF")
			+ "\\homework\\" + homework.getTaskId() + "\\" + uploadFile.getOriginalFilename();
		File file = new File(realPath);
		if (!file.exists()) {
		    file.mkdirs();
		} else {
		    //已存在相同的文件
		}
		uploadFile.transferTo(file);
		homework.setFilePath(realPath);

		if (hdi.insert(homework)) {
		    response.sendRedirect("stu_homework.htm?id=" + homework.getCourseId());
		} else {
		    file.delete();
		}
	    }
	} else if (!course.isCategory()) {
	    //是分组
	} else if (!course.isCategory()) {
	    //是分组
	} else if (task.isText() || uploadFile.isEmpty()) {
	    if (hdi.insert(homework)) {
		response.sendRedirect("stu_homework.htm?id=" + homework.getCourseId());
	    } else {

	    }
	} else {

	    String realPath = session.getServletContext().getRealPath("/WEB-INF")
		    + "\\homework\\" + homework.getTaskId() + "\\" + uploadFile.getOriginalFilename();
	    File file = new File(realPath);
	    if (!file.exists()) {
		file.mkdirs();
	    } else {
		//已存在相同的文件
	    }
	    uploadFile.transferTo(file);
	    homework.setFilePath(realPath);

	    if (hdi.update(homework)) {
		response.sendRedirect("stu_homework.htm?id=" + homework.getCourseId());
	    } else {
		file.delete();
	    }
	}

    }

    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

    @RequestMapping(value = "/scoreHomework")
    public ModelAndView scoreHomework(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_homework_score");
	String homeworkId = request.getParameter("homeworkId");
	Homework homework = hdi.findHomeworkById(homeworkId);
	String taskId = homework.getTaskId();
	request.setAttribute("homework_id", homeworkId);
	request.setAttribute("task_id", taskId);
	request.setAttribute("origin_homework", homework);
	mav.addObject("homework", new Homework());
	return mav;
    }

    @RequestMapping(value = "/setHomeworkScore")
    public void setHomeworkScore(Homework homework, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String homeworkId = request.getParameter("homework_id");
	hdi.setScore(homeworkId, homework.getScore());
	if (homework.getComment() != null && homework.getComment() != "") {
	    hdi.setComment(homeworkId, homework.getComment());
	}
	String taskId = request.getParameter("task_id");
	response.sendRedirect("te_homework_list.htm?taskId=" + taskId);
    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }
}
