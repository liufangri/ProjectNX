/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Course;
import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.Group;
import com.teamnx.model.GroupDaoImpl;
import com.teamnx.model.ShowGroup;
import com.teamnx.model.StudentGroup;
import com.teamnx.model.StudentGroupDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.MD5;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JOHNKYON
 */
@Controller
public class GroupController {

    private GroupDaoImpl gdi;
    private CourseDaoImpl cdi;
    private StudentGroupDaoImpl sgdi;
    private UserDaoImpl udi;
    private MessageController mc;

//    跳转到我的团队页面
    @RequestMapping(value = "/toMyGroup")
    public ModelAndView toMyGroup(HttpSession session, HttpServletRequest request) {
//      判断用户的组队状态并跳转到相应的我的团队界面
	User user = (User) session.getAttribute("user");
	String student_id = user.getId();
	String course_id = request.getParameter("course_id");
	Group group = gdi.findGroupByStudentId(student_id, course_id);
	Course course = cdi.findCourseById(course_id);
	int max_member = course.getMax_member();
	ModelAndView mav = new ModelAndView();
	request.setAttribute("course_id", course_id);
	if (group != null) {
	    if (group.getManagerId().equals(student_id)) {
		ArrayList<StudentGroup> studentGroups = sgdi.findstudentGroupsByGroupId(group.getId());
		String status = "组建中";
		switch (group.getStatus()) {
		    case 1:
			status = "待审核";
			break;
		    case 2:
			status = "已通过";
			break;
		}
		request.setAttribute("status", status);
		mav.addObject("studentGroups", studentGroups);
		mav.addObject("group", group);
		mav.setViewName("stu_myteam_manager");
	    } else {
		ArrayList<StudentGroup> studentGroups = sgdi.findstudentGroupsByGroupId(group.getId());
		String status = "组建中";
		switch (group.getStatus()) {
		    case 1:
			status = "待审核";
			break;
		    case 2:
			status = "已通过";
			break;
		}
		request.setAttribute("status", status);
		mav.addObject("studentGroups", studentGroups);
		mav.addObject("group", group);
		mav.setViewName("stu_myteam_member");
	    }
	} else {
	    group = new Group();
	    mav.addObject("group", group);
	    mav.addObject("max_member", max_member);
	    mav.addObject("user_name", user.getName());
	    boolean inGroup = sgdi.ifInGroup(student_id, course_id);
	    request.setAttribute("in_group", inGroup);
	    mav.setViewName("stu_myteam_noteam");
	}
	return mav;
    }

//    申请建立团队
    @RequestMapping(value = "/establishGroup")
    public void establishGroup(Group group, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	User user = (User) session.getAttribute("user");
	String courseId = group.getCourseId();
	if (user.getCharacter() == 1) {
	    String groupId = MD5.Md5_16(user.getName() + new Date().getTime());
	    group.setId(groupId);
	    group.setManagerId(user.getId());
	    gdi.insert(group);
//        插入studentGroup数据
	    StudentGroup studentGroup = new StudentGroup();
	    String sgId = MD5.Md5_16(user.getName() + new Date().getTime());
	    studentGroup.setId(sgId);
	    studentGroup.setStudentId(user.getId());
	    studentGroup.setCourseId(courseId);
	    studentGroup.setGroupId(groupId);
	    studentGroup.setStudentName(user.getName());
	    studentGroup.setStatus(true);
	    sgdi.insert(studentGroup);
	    response.sendRedirect("toMyGroup.htm?course_id=" + courseId);
	} else if (user.getCharacter() == 2) {
	    User student = udi.findUserById(group.getManagerId());
	    String groupId = MD5.Md5_16(user.getName() + new Date().getTime());
	    group.setId(groupId);
	    gdi.insert(group);
	    //        插入studentGroup数据
	    StudentGroup studentGroup = new StudentGroup();
	    String sgId = MD5.Md5_16(user.getName() + new Date().getTime());
	    studentGroup.setId(sgId);
	    studentGroup.setStudentId(group.getManagerId());
	    studentGroup.setCourseId(courseId);
	    studentGroup.setGroupId(groupId);
	    studentGroup.setStudentName(student.getName());
	    studentGroup.setStatus(true);
	    sgdi.insert(studentGroup);
	    response.sendRedirect("teacherGroupList.htm?course_id=" + courseId);
	}
    }

//    显示所有团队
    @RequestMapping(value = "/studentGroupList")
    public ModelAndView studentGroupList(HttpSession session, HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("stu_teamlist");
	String courseId = request.getParameter("course_id");
	User user = (User) session.getAttribute("user");
	ArrayList<Group> groups = gdi.findGroupsByCourseId(courseId);
	ArrayList<ShowGroup> showgroups = new ArrayList<ShowGroup>();
	for (Group g : groups) {
	    User manager = udi.findUserById(g.getManagerId());
	    int member = sgdi.countMember(g.getId());
	    ShowGroup showGroup = new ShowGroup();
	    showGroup.setId(g.getId());
	    showGroup.setName(g.getName());
	    showGroup.setManager(manager.getName());
	    showGroup.setGroupId(g.getId());
	    showGroup.setNumber(member);
	    showGroup.setStatus("未知");
	    switch (g.getStatus()) {
		case 0:
		    showGroup.setStatus("组建中");
		    break;
		case 1:
		    showGroup.setStatus("待审核");
		    break;
		case 2:
		    showGroup.setStatus("已通过");
		    break;
	    }
	    showgroups.add(showGroup);
	}
	Course course = cdi.findCourseById(courseId);
	int maxNumber = course.getMax_member();
//        boolean inGroup = sgdi.ifInGroup(user.getId(), courseId);
	String inGroup = "False";
	if (sgdi.ifInGroup(user.getId(), courseId)) {
	    inGroup = "True";
	}
	mav.addObject("in_group", inGroup);
	mav.addObject("max_number", maxNumber);
	mav.addObject("groups", showgroups);
	mav.addObject("course_id", courseId);
	return mav;
    }

//    团队详细信息
    @RequestMapping(value = "/studentGroupInfo")
    public ModelAndView studentGroupInfo(HttpSession session, HttpServletRequest request) {
	User user = (User) session.getAttribute("user");
	ModelAndView mav = new ModelAndView("stu_teaminfo");
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	Group group = gdi.findGroupById(groupId);
	ArrayList<StudentGroup> studentGroups = sgdi.findstudentGroupsByGroupId(groupId);
	String status = "";
	switch (group.getStatus()) {
	    case 0:
		status = "组建中";
		break;
	    case 1:
		status = "待审核";
		break;
	    case 2:
		status = "已通过";
		break;
	}
	String inGroup = "False";
	if (sgdi.ifInGroup(user.getId(), courseId)) {
	    inGroup = "True";
	}
	mav.addObject("in_group", inGroup);
	mav.addObject("status", status);
	mav.addObject("studentGroups", studentGroups);
	mav.addObject("group", group);
	mav.addObject("course_id", courseId);
	return mav;
    }

//    申请进组
    @RequestMapping(value = "/groupApply")
    public void groupApply(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	User user = (User) session.getAttribute("user");
	String groupId = request.getParameter("group_id");
	String courseId = request.getParameter("course_id");
	Group group = gdi.findGroupById(groupId);
	if (group.getMaxMember() > sgdi.countMember(groupId) && !sgdi.ifInGroup(user.getId(), courseId)) {
	    String sgId = MD5.Md5_16(user.getName() + new Date().getTime());
	    StudentGroup studentGroup = new StudentGroup();
	    studentGroup.setId(sgId);
	    studentGroup.setCourseId(courseId);
	    studentGroup.setGroupId(groupId);
	    studentGroup.setStudentId(user.getId());
	    studentGroup.setStudentName(user.getName());
	    studentGroup.setStatus(false);
	    sgdi.insert(studentGroup);
	}
	response.sendRedirect("studentGroupList.htm?course_id=" + courseId);

    }

//    查看申请列表
    @RequestMapping(value = "/applyList")
    public ModelAndView applyList(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("stu_requestlist");
	String groupId = request.getParameter("group_id");
	String courseId = request.getParameter("course_id");
	ArrayList<StudentGroup> studentGroups = sgdi.findApplyList(groupId);
	mav.addObject("studentGroups", studentGroups);
	mav.addObject("course_id", courseId);
	return mav;
    }

//    判断是否同意入队
    @RequestMapping(value = "/ifJoin")
    public void ifJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String studentGroupId = request.getParameter("studentGroup_id");
	StudentGroup sg = sgdi.findById(studentGroupId);
	String groupId = sg.getGroupId();
	String status = request.getParameter("status");
	if (status.equals("1")) {
	    Group group = gdi.findGroupById(groupId);
	    int member = sgdi.countMember(groupId);
	    if (group.getMaxMember() > member) {
//		成功入队
		sgdi.accpet(studentGroupId);
	    }
	} else {
//	    入队失败
	    Course course = cdi.findCourseById(courseId);
	    mc.sendMessage(sg.getStudentId(), course.getName() + "：您的入队申请被拒绝!");
	    sgdi.delete(studentGroupId);
	}
	response.sendRedirect("applyList.htm?course_id=" + courseId + "&?group_id=" + groupId);
    }

//    退出团队
    @RequestMapping(value = "/exitGroup")
    public void exitGroup(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	User user = (User) session.getAttribute("user");
	String groupId = request.getParameter("group_id");
	StudentGroup sg = sgdi.findByStudentGroup(user.getId(), groupId);
	sgdi.delete(sg.getId());
	response.sendRedirect("toMyGroup.htm?course_id=" + courseId);
    }

//    移除团队成员
    @RequestMapping(value = "/removeMember")
    public void removeMember(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String sgId = request.getParameter("sg_id");
	StudentGroup sg = sgdi.findById(sgId);
	String groupId = sg.getGroupId();
	String courseId = request.getParameter("course_id");
	Course course = cdi.findCourseById(courseId);
	Group group = gdi.findGroupById(groupId);
	sgdi.delete(sgId);
	User user = (User) session.getAttribute("user");
	mc.sendMessage(sg.getStudentId(), course.getName() + "：您已被团队\"" + group.getName() + "\"移除！");
	if (user.getCharacter() == 1) {
	    response.sendRedirect("toMyGroup.htm?course_id=" + courseId);
	} else {
	    response.sendRedirect("passedGroupInfo.htm?course_id=" + courseId + "&group_id=" + groupId);
	}
    }

//    解散团队
    @RequestMapping(value = "/dissolution")
    public void dissolution(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String groupId = request.getParameter("group_id");
	User user = (User) session.getAttribute("user");

	//解散团队，对所有人发送消息
	ArrayList<StudentGroup> sgs = sgdi.findstudentGroupsByGroupId(groupId);
	Group group = gdi.findGroupById(groupId);
	Course course = cdi.findCourseById(group.getCourseId());
	for (StudentGroup sg : sgs) {
	    mc.sendMessage(sg.getStudentId(), course.getName() + "：您所在的团队" + group.getName() + "已被解散！");
	}

	String course_id = request.getParameter("course_id");
	sgdi.deleteByGroupId(groupId);
	gdi.delete(groupId);
	if (user.getCharacter() == 1) {
	    response.sendRedirect("toMyGroup.htm?course_id=" + course_id);
	} else {
	    response.sendRedirect("teacherGroupList.htm?course_id=" + course_id);
	}
    }

//    设为负责人
    @RequestMapping(value = "/setManager")
    public void setManager(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	String studentId = request.getParameter("student_id");
	User user = (User) session.getAttribute("user");
	Group group = gdi.findGroupById(groupId);
	Course course = cdi.findCourseById(courseId);
	String oldManagerId = group.getManagerId();
	mc.sendMessage(oldManagerId, course.getName() + "：您已不再是团队“" + group.getName() + "”的负责人");
	gdi.setManager(groupId, studentId);
	if (user.getCharacter() == 1) {
	    response.sendRedirect("toMyGroup.htm?course_id=" + courseId);
	} else {
	    response.sendRedirect("passedGroupInfo.htm?course_id=" + courseId + "&group_id=" + groupId);
	}
    }

//    完成组队，提出申请
    @RequestMapping(value = "/finishForming")
    public void finishForming(HttpServletRequest request, HttpServletResponse response) throws IOException {

	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	if (!sgdi.applyleft(groupId)) {
	    gdi.setStatus(groupId, 1);
	}
	response.sendRedirect("toMyGroup.htm?course_id=" + courseId);
    }

//    取消申请
    @RequestMapping(value = "/cancelForming")
    public void cancelForming(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	gdi.setStatus(groupId, 0);
	response.sendRedirect("toMyGroup.htm?course_id=" + courseId);
    }

//    老师查看团队列表
    @RequestMapping(value = "/teacherGroupList")
    public ModelAndView teacherGroupList(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_teamlist");
	String courseId = request.getParameter("course_id");
	Course course = cdi.findCourseById(courseId);
	ArrayList<Group> passedGroups = gdi.findGroupsByStatus(courseId, 2);
	ArrayList<ShowGroup> passedShowGroups = new ArrayList<ShowGroup>();
	for (Group g : passedGroups) {
	    User manager = udi.findUserById(g.getManagerId());
	    int member = sgdi.countMember(g.getId());
	    ShowGroup showGroup = new ShowGroup();
	    showGroup.setId(g.getId());
	    showGroup.setName(g.getName());
	    showGroup.setManager(manager.getName());
	    showGroup.setGroupId(g.getId());
	    showGroup.setNumber(member);
	    showGroup.setStatus("未知");
	    passedShowGroups.add(showGroup);
	}
	ArrayList<Group> waitingGroups = gdi.findGroupsByStatus(courseId, 1);
	ArrayList<ShowGroup> waitingShowGroups = new ArrayList<ShowGroup>();
	for (Group g : waitingGroups) {
	    User manager = udi.findUserById(g.getManagerId());
	    int member = sgdi.countMember(g.getId());
	    ShowGroup showGroup = new ShowGroup();
	    showGroup.setId(g.getId());
	    showGroup.setName(g.getName());
	    showGroup.setManager(manager.getName());
	    showGroup.setGroupId(g.getId());
	    showGroup.setNumber(member);
	    showGroup.setStatus("未知");
	    waitingShowGroups.add(showGroup);
	}
	ArrayList<Group> formingGroups = gdi.findGroupsByStatus(courseId, 0);
	ArrayList<ShowGroup> formingShowGroups = new ArrayList<ShowGroup>();
	for (Group g : formingGroups) {
	    User manager = udi.findUserById(g.getManagerId());
	    int member = sgdi.countMember(g.getId());
	    ShowGroup showGroup = new ShowGroup();
	    showGroup.setId(g.getId());
	    showGroup.setName(g.getName());
	    showGroup.setManager(manager.getName());
	    showGroup.setGroupId(g.getId());
	    showGroup.setNumber(member);
	    showGroup.setStatus("未知");
	    formingShowGroups.add(showGroup);
	}
	ArrayList<User> users = udi.findStudentsNotInGroup(courseId);
	mav.addObject("users", users);
	mav.addObject("course", course);
	mav.addObject("passedGroups", passedShowGroups);
	mav.addObject("waitingGroups", waitingShowGroups);
	mav.addObject("formingGroups", formingShowGroups);
	mav.addObject("course_id", courseId);
	return mav;
    }

//    老师批准团队申请
    @RequestMapping(value = "/teacherPermitting")
    public void teacherPermitting(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	String statusString = request.getParameter("status");
	int status = Integer.parseInt(statusString);
	gdi.setStatus(groupId, status);
	response.sendRedirect("teacherGroupList.htm?course_id=" + courseId);
    }

//    老师查看已通过团队详情
    @RequestMapping(value = "/passedGroupInfo")
    public ModelAndView passedGroupInfo(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_teampassed");
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	Group group = gdi.findGroupById(groupId);
	int status = group.getStatus();
	String statusStr = "";
	switch (status) {
	    case 0:
		statusStr = "组建中";
		break;
	    case 1:
		statusStr = "待审核";
		break;
	    case 2:
		statusStr = "已通过";
		break;
	}
	ArrayList<StudentGroup> studentGroups = sgdi.findstudentGroupsByGroupId(groupId);
	mav.addObject("status", statusStr);
	mav.addObject("group", group);
	mav.addObject("studentGroups", studentGroups);
	mav.addObject("course_id", courseId);
	return mav;
    }

//    老师添加成员页面
    @RequestMapping(value = "/addMember")
    public ModelAndView addMember(HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("te_teampassed_add");
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	Group group = gdi.findGroupById(groupId);
	String managerName = udi.findUserById(group.getManagerId()).getName();
	ArrayList<User> users = udi.findStudentsNotInGroup(courseId);
	mav.addObject("users", users);
	mav.addObject("group", group);
	mav.addObject("manager_name", managerName);
	mav.addObject("course_id", courseId);
	return mav;
    }

//    老师添加成员
    @RequestMapping(value = "/addIntoGroup")
    public void addIntoGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String groupId = request.getParameter("group_id");
	String studentId = request.getParameter("student_id");
	User student = udi.findUserById(studentId);
	String sgId = MD5.Md5_16(studentId + new Date().getTime());
	StudentGroup sg = new StudentGroup();
	sg.setId(sgId);
	sg.setCourseId(courseId);
	sg.setGroupId(groupId);
	sg.setStudentId(studentId);
	sg.setStudentName(student.getName());
	sg.setStatus(true);
	sgdi.insert(sg);
	response.sendRedirect("addMember.htm?course_id=" + courseId + "&group_id=" + groupId);
    }

    /**
     * @param gdi the gdi to set
     */
    public void setGdi(GroupDaoImpl gdi) {
	this.gdi = gdi;
    }

    /**
     * @param cdi the cdi to set
     */
    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }

    /**
     * @param sgdi the sgdi to set
     */
    public void setSgdi(StudentGroupDaoImpl sgdi) {
	this.sgdi = sgdi;
    }

    /**
     * @param udi the udi to set
     */
    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

    public void setMc(MessageController mc) {
	this.mc = mc;
    }

}
