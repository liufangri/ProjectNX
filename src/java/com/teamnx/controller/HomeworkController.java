/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.Homework;
import com.teamnx.model.HomeworkDaoImpl;
import com.teamnx.model.Task;
import com.teamnx.model.TaskDaoImpl;
import com.teamnx.model.UserDaoImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void submitHomework() {

    }

    @RequestMapping(value = "/scoreHomework")
    public ModelAndView scoreHomework(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("te_homework_score");
        String homework_id = request.getParameter("homeworkId");
        Homework homework = hdi.findHomeworkById(homework_id);
        String taskId = homework.getTaskId();
        request.setAttribute("homework_id", homework_id);
        request.setAttribute("task_id", taskId);
        return mav;
    }

    @RequestMapping(value = "/setHomeworkScore")
    public ModelAndView setHomeworkScore(Homework homework, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("te_homework_list");
        hdi.setScore(homework.getId(), homework.getScore());
        if (homework.getComment() != null && homework.getComment() != "") {
            hdi.setComment(homework.getId(), homework.getComment());
        }
        String taskId = request.getParameter("taskId");
        request.setAttribute("task_id", taskId);
        return mav;
    }

    /**
     * @return the cdi
     */
    public CourseDaoImpl getCdi() {
        return cdi;
    }

    /**
     * @param cdi the cdi to set
     */
    public void setCdi(CourseDaoImpl cdi) {
        this.cdi = cdi;
    }

    /**
     * @return the hdi
     */
    public HomeworkDaoImpl getHdi() {
        return hdi;
    }

    /**
     * @return the udi
     */
    public UserDaoImpl getUdi() {
        return udi;
    }

    /**
     * @param udi the udi to set
     */
    public void setUdi(UserDaoImpl udi) {
        this.udi = udi;
    }
}
