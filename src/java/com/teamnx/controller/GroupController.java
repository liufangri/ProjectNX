/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Course;
import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.Group;
import com.teamnx.model.GroupDapImpl;
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

    private GroupDapImpl gdi;
    private CourseDaoImpl cdi;
    private StudentGroupDaoImpl sgdi;
    private UserDaoImpl udi;

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
            mav.setViewName("stu_myteam_noteam");
        }
        return mav;
    }

    @RequestMapping(value = "/establishGroup")
    public void establishGroup(Group group, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("user");
        String courseId = group.getCourseId();
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
    }

    @RequestMapping(value = "/studentGroupList")
    public ModelAndView studentGroupList(HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("stu_teamlist");
        String courseId = request.getParameter("course_id");
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
        mav.addObject("max_number", maxNumber);
        mav.addObject("groups", showgroups);
        mav.addObject("course_id", courseId);
        return mav;
    }

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
                sgdi.accpet(studentGroupId);
            }
        } else {
            sgdi.delete(studentGroupId);
        }
        response.sendRedirect("applyList.htm?course_id=" + courseId + "&?group_id=" + groupId);
    }
    
    @RequestMapping(value = "/exitGroup")
    public void exitGroup(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String courseId = request.getParameter("course_id");
        User user = (User) session.getAttribute("user");
        String groupId = request.getParameter("group_id");
        StudentGroup sg = sgdi.findByStudentGroup(user.getId(), groupId);
        sgdi.delete(sg.getId());
        response.sendRedirect("toMyGroup.htm?course_id="+courseId);
    }

    /**
     * @param gdi the gdi to set
     */
    public void setGdi(GroupDapImpl gdi) {
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

}
