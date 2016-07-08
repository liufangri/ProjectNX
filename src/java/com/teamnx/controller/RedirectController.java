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
import com.teamnx.model.Resource;
import com.teamnx.model.ResourceDaoImpl;
import com.teamnx.model.ShowHomework;
import com.teamnx.model.Task;
import com.teamnx.model.TaskDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.MD5;
import java.io.File;
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
    private ResourceDaoImpl rdi;

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
	ArrayList<Course> courses;
	if (user != null) {
	    switch (user.getCharacter()) {
		case User.STUDENT:
		    courses = cdi.findCoursesByStudentId(user.getId());
		    request.setAttribute("courses", courses);
		    break;
		case User.TEACHER:
		    courses = cdi.findCoursesByTeacherId(user.getId());
		    request.setAttribute("courses", courses);
		    break;
		case User.ADMIN:
		    break;
	    }
	} else {
	    response.sendRedirect("login.htm");
	}
	return mav;
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
	String courseId = request.getParameter("course_id");
	if (user != null) {

	    Course c = cdi.findCourseById(courseId);
	    if (c != null) {
		ArrayList<String> teacherName = udi.findTeachersByCourseId(courseId);
		String names = "";
		for (String a : teacherName) {
		    names += a + " ";
		}
		c.setTeachers(names);
		request.setAttribute("current_course", c);
		mav.addObject("course", c);
		switch (user.getCharacter()) {
		    case User.STUDENT:
			request.setAttribute("course_id", courseId);
			mav.setViewName("stu_index");
			break;
		    case User.TEACHER:
			request.setAttribute("course_id", courseId);
			mav.setViewName("te_index");
			break;
		}
	    } else {
		//找不到课程
		mav.setViewName("login");
	    }
	    //用户信息在session中被删除了，返回到登录页面
	} else {
	    mav.setViewName("login");
	}
	return mav;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
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
	String courseId = request.getParameter("course_id");
	ArrayList<Task> tasks;
	tasks = tdi.findTasksByCourseId(courseId);
	request.setAttribute("tasks", tasks);
	Task task = new Task();
	mav.addObject("task", task);
	request.setAttribute("course_id", courseId);
	return mav;
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
	String courseId = request.getParameter("course_id");
	User user = (User) session.getAttribute("user");
	if (user != null) {
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
	} else {
	    mav.setViewName("login");
	}
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
	String courseId = request.getParameter("course_id");
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
	String taskId = request.getParameter("task_id");
	Task task = tdi.findTaskById(taskId);
	mav.addObject("task", task);
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
	String taskId = request.getParameter("task_id");
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
	String taskId = request.getParameter("task_id");
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
	String courseId = task.getCourseId();
	request.setAttribute("origin_homework", homework);
	request.setAttribute("course_id", courseId);
	mav.addObject("homework", homework);
	mav.addObject("task", task);
	return mav;
    }

    /**
     * 跳转到查看选修这门课所有学生的页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/te_studentlist")
    public ModelAndView toStudentListPage(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_studentlist");
	String courseId = request.getParameter("course_id");
	ArrayList<User> students = udi.findStudentsByCourseId(courseId);
	request.setAttribute("course_id", courseId);
	request.setAttribute("students", students);
	return mav;
    }

    /**
     * 跳转到给学生打分页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/scoreHomework")
    public ModelAndView scoreHomework(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_homework_score");
	String homeworkId = request.getParameter("homework_id");
	Homework homework = hdi.findHomeworkById(homeworkId);
	String taskId = homework.getTaskId();
	Task task = (Task) tdi.findTaskById(taskId);
	request.setAttribute("homework_id", homeworkId);
	request.setAttribute("course_id", homework.getCourseId());
	request.setAttribute("task_id", taskId);
	request.setAttribute("origin_homework", homework);
	request.setAttribute("task", task);
	mav.addObject("homework", new Homework());
	return mav;
    }

    /**
     * 跳转到资源页面
     *
     * @param request
     * @param sessionn
     * @return
     */
    @RequestMapping(value = "/resource")
    public ModelAndView toResourcePage(HttpServletRequest request, HttpSession sessionn) {
	String courseId = request.getParameter("course_id");
	User user = (User) sessionn.getAttribute("user");
	String folderId = request.getParameter("folder_id");
	ModelAndView mav = new ModelAndView();
	ArrayList<Resource> resources = null;
	Resource currentFolder = null;
	if (folderId == null) {
	    currentFolder = rdi.findCourseRootFolder(courseId);
	    if (currentFolder == null) {
		currentFolder = getRootResource(courseId, request);
		resources = new ArrayList<Resource>();
	    } else {
		resources = rdi.findChildsByFolderId(currentFolder.getId());
	    }

	} else {
	    currentFolder = rdi.findResourceById(folderId);
	    resources = rdi.findChildsByFolderId(currentFolder.getId());
	}
	request.setAttribute("course_id", courseId);
	request.setAttribute("current_folder", currentFolder);
	request.setAttribute("resources", resources);
	switch (user.getCharacter()) {
	    case User.STUDENT:
		mav.setViewName("stu_resource");
		break;
	    case User.TEACHER:
		mav.setViewName("te_resource");
		mav.addObject("new_resource", new Resource());
		break;
	    case User.ADMIN:
		break;
	}
	return mav;
    }

    /**
     * 首次进入课程资源首页的处理
     *
     * @param courseId
     * @param request
     * @return
     */
    private Resource getRootResource(String courseId, HttpServletRequest request) {
	Resource resource = new Resource();
	String name = courseId + "_root";
	resource.setId(MD5.Md5_16(name));
	resource.setName(name);
	resource.setFolder(true);
	resource.setPath("\\" + name);
	resource.setCourseId(courseId);
	String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF") + "\\courseResources" + resource.getPath();
	File file = new File(realPath);
	if (!file.exists()) {
	    file.mkdirs();
	    resource.setLastChange(file.lastModified());
	    if (!rdi.insert(resource)) {
		//插入数据失败，删除新建的文件夹
		file.delete();
	    }
	} else if (!rdi.insert(resource)) {
	    //插入数据失败，删除新建的文件夹
	    file.delete();
	}
	return resource;

    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }

    public void setHdi(HomeworkDaoImpl hdi) {
	this.hdi = hdi;
    }

    public void setRdi(ResourceDaoImpl rdi) {
	this.rdi = rdi;
    }

}
