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
import com.teamnx.model.ShowHomework;
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
    private HomeworkDaoImpl hdi;

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

    /**
     * 处理到学生作业页面的跳转
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/stu_homework")
    public ModelAndView studentHomework(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("stu_homework");
	String courseId = request.getParameter("id");
	User user = (User) session.getAttribute("user");
	ArrayList<Task> tasks;
	tasks = tdi.findTasksByCourseId(courseId);
	ArrayList<ShowHomework> showHomeworks = new ArrayList<ShowHomework>();
	for (Task t : tasks) {
	    Homework homework = hdi.findStudentHomework(t.getId(), user.getId());
	    ShowHomework sh = new ShowHomework();
	    sh.setDeadLine(t.getDeadline().toString());
	    sh.setStartTime(t.getStartTime().toString());
	    sh.setTaskId(t.getId());
	    sh.setTaskName(t.getName());
	    if (homework == null) {
		sh.setState(false);
	    } else {
		sh.setState(true);
		sh.setHomeworkId(homework.getId());
		sh.setScore(homework.getScore());
	    }
	    showHomeworks.add(sh);
	}
	request.setAttribute("tasks", tasks);
	request.setAttribute("show_homeworks", showHomeworks);
	Task task = new Task();
	mav.addObject("task", task);
	request.setAttribute("course_id", courseId);
	return mav;
    }

    /**
     * 去添加作业页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/te_homework_add")
    public ModelAndView toTeacherTaskAddPage(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_homework_submit");
	Task task = new Task();
	mav.addObject("task", task);
	String courseId = request.getParameter("id");
	request.setAttribute("course_id", courseId);
	return mav;

    }

    /**
     * 去修改作业要求页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/te_task_modify")
    public ModelAndView toTeacherTaskModifyPage(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_homework_change");
	String taskId = request.getParameter("taskId");
	Task task = tdi.findTaskById(taskId);
	request.setAttribute("origin_task", task);
	mav.addObject("task", new Task());
	request.setAttribute("course_id", task.getCourseId());
	return mav;
    }

    /**
     * 去教师的作业提交列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/te_homework_list")
    public ModelAndView toTeacherHomeworkPage(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_homework_list");
	String taskId = request.getParameter("taskId");
	Task task = tdi.findTaskById(taskId);
	request.setAttribute("task", task);
	ArrayList<Homework> homeworks = hdi.findHomeworksByTaskId(taskId);
	request.setAttribute("homeworks", homeworks);
	request.setAttribute("course_id", task.getCourseId());
	return mav;
    }

    /**
     * 跳转到学生提交作业页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/stu_homework_submit")
    public ModelAndView toStudentHomeworkSubmitPage(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("stu_homework_submit");
	User user = (User) session.getAttribute("user");
	String taskId = request.getParameter("taskId");
	Task task = tdi.findTaskById(taskId);
	request.setAttribute("task_id", taskId);
	request.setAttribute("course_id", task.getCourseId());
	request.setAttribute("task", task);
	Homework homework = hdi.findStudentHomework(taskId, user.getId());
	if (homework == null) {
	    homework = new Homework();
	}
	request.setAttribute("origin_homework", homework);
	mav.addObject("homework", homework);
	return mav;
    }

    /**
     * 学生查看详细分数页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/stu_homework_score")
    public ModelAndView toStudentScorePage(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView mav = new ModelAndView("stu_homework_score");
	String homeworkId = request.getParameter("homework_id");

	Homework homework = hdi.findHomeworkById(homeworkId);
	Task task = tdi.findTaskById(homework.getTaskId());

	request.setAttribute("origin_homework", homework);
	mav.addObject("homework", homework);
	mav.addObject("task", task);
	return mav;
    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }

    public void setHdi(HomeworkDaoImpl hdi) {
	this.hdi = hdi;
    }

}
