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
	Course course = cdi.findCourseById(homework.getCourseId());
	User user = udi.findUserById(homework.getStudentId());
	Task task = tdi.findTaskById(homework.getTaskId());
	if (homework.getId() == null || "".equals(homework.getId())) {
	    //尚未提交作业
	    String id = MD5.Md5_16(homework.getTaskId() + homework.getStudentName() + new Date().getTime());
	    homework.setId(id);
	    if (!course.isCategory()) {
		//是分组
	    } else if (task.isText() || uploadFile.isEmpty()) {
		//是文本作业或者学生没有上传文件
		if (hdi.insert(homework)) {
		    response.sendRedirect("stu_homework.htm?course_id=" + homework.getCourseId());
		} else {
		    //提交作业失败
		}
	    } else {
		//存在文件可以上传
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
		    response.sendRedirect("stu_homework.htm?course_id=" + homework.getCourseId());
		} else {
		    file.delete();
		}
	    }
	} else {

	    //已经有提交了的作业，执行更改作业操作
	    Homework originHomework = hdi.findHomeworkById(homework.getId());
	    if (!course.isCategory()) {
		//是分组
	    } else if (task.isText() || uploadFile.isEmpty()) {
		//是文本作业或者学生没有上传文件
		homework.setFilePath(originHomework.getFilePath());
		if (hdi.update(homework)) {
		    response.sendRedirect("stu_homework.htm?course_id=" + homework.getCourseId());
		} else {
		    //更改作业失败
		}
	    } else {
		//存在文件可以上传
		//学生希望多个文件。暂不考虑
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
		    if (originHomework.getFilePath() != null) {
			File originFile = new File(originHomework.getFilePath());
			if (originFile.exists()) {
			    int x = 0;
			    while (!originFile.delete() && x < 3) {
				try {
				    //删除文件失败
				    Thread.sleep(2000);
				    x++;
				} catch (InterruptedException ex) {
				    Logger.getLogger(HomeworkController.class.getName()).log(Level.SEVERE, null, ex);
				}
			    }
			    if (x >= 3) {
				//删除文件操作失败，撤销数据库操作。
			    }
			}
		    }
		    response.sendRedirect("stu_homework.htm?course_id=" + homework.getCourseId());
		} else {
		    //更新失败
		    file.delete();
		    response.sendRedirect("stu_homework.htm?course_id=" + homework.getCourseId());
		}
	    }
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
