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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	User user = udi.findUserById(homework.getStudentId());
	if (user != null) {
	    Task task = tdi.findTaskById(homework.getTaskId());
	    Course course = cdi.findCourseById(homework.getCourseId());
	    String originHomeworkId = request.getParameter("origin_homework_id");
	    Homework originHomework = hdi.findHomeworkById(originHomeworkId);

	    if (uploadFile != null && !uploadFile.isEmpty()) {
		if (originHomework != null) {
		    File oldFile = new File(session.getServletContext().getRealPath("/WEB-INF") + originHomework.getFilePath());
		    if (oldFile.delete()) {
			//成功删除旧作业附件
		    } else {
			//删除旧作业失败附件
		    }
		}
		String fileName = uploadFile.getOriginalFilename();
		String filePath = "\\homework\\" + task.getId() + "\\" + user.getId() + "\\" + fileName;
		String realPath = session.getServletContext().getRealPath("/WEB-INF") + filePath;
		File newFile = new File(realPath);
		homework.setFilePath(filePath);
		if (!newFile.exists()) {
		    newFile.mkdirs();
		}
		try {
		    uploadFile.transferTo(newFile);
		} catch (IOException ex) {
		    Logger.getLogger(HomeworkController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalStateException ex) {
		    Logger.getLogger(HomeworkController.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	    if (originHomework == null) {
		//尚未提交作业
		String id = MD5.Md5_16(homework.getTaskId() + homework.getStudentName() + new Date().getTime());
		homework.setId(id);

		if (hdi.insert(homework)) {
		    //添加作业成功
		    response.sendRedirect("stu_homework.htm?course_id=" + course.getId());
		} else {
		    //添加作业失败
		    response.sendRedirect("stu_homework.htm?course_id=" + course.getId());
		}
	    } else {
		homework.setId(originHomeworkId);
		if (hdi.update(homework)) {
		    //更新作业成功
		    response.sendRedirect("stu_homework.htm?course_id=" + course.getId());
		} else {
		    //更新作业失败
		    response.sendRedirect("stu_homework.htm?course_id=" + course.getId());
		}
	    }
	} else {
	    response.sendRedirect("login.htm");
	}

    }

    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

    @RequestMapping(value = "/setHomeworkScore")
    public void setHomeworkScore(Homework homework, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String homeworkId = request.getParameter("homework_id");
	hdi.setScore(homeworkId, homework.getScore());
	if (homework.getComment() != null && homework.getComment() != "") {
	    hdi.setComment(homeworkId, homework.getComment());
	}
	String taskId = request.getParameter("task_id");
	request.setAttribute("course_id", homework.getCourseId());
	response.sendRedirect("te_homework_list.htm?task_id=" + taskId);
    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }
}
