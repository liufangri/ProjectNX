/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Semester;
import com.teamnx.model.SemesterDaoImpl;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AdminController {

    private SemesterDaoImpl smdi;

    public static final String[] SEMESTERS = {"", "秋季", "春季", "夏季"};
    public static final String[] YEARS = {"2010年", "2011年", "2012年", "2013年", "2014年",
	"2015年", "2016年", "2017年", "2018年", "2019年", "2020年",
	"2021年", "2022年", "2023年", "2024年", "2025年", "2026年"};

    /**
     * @param smdi the smdi to set
     */
    public void setSmdi(SemesterDaoImpl smdi) {
	this.smdi = smdi;
    }

    @RequestMapping("/setSemester")
    public void setSemester(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
	    String yearStr = request.getParameter("Q1");
	    String semesterStr = request.getParameter("Q2");
	    String startTimeStr = request.getParameter("timeLimit");
//	    startTimeStr = startTimeStr + " 00:00:00";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = sdf.parse(startTimeStr);
	    int year = Integer.parseInt(yearStr.substring(0, yearStr.length() - 1));
	    int semesterNum = 0;
	    for (int i = 0; i < SEMESTERS.length; i++) {
		if (semesterStr.equals(SEMESTERS[i])) {
		    semesterNum = i;
		    break;
		}
	    }
	    Semester semester = new Semester();
	    semester.setYear(year);
	    semester.setSemester(semesterNum);
	    semester.setStart_day(date);

	    switch (smdi.ifExit(semester)) {
		case -1:
		    response.sendRedirect("usercenter.htm");
		    break;
		case 0:
		    smdi.insert(semester);
		    response.sendRedirect("usercenter.htm");
		    break;
		default:
                    smdi.deleteAll();
		    smdi.insert(semester);
		    response.sendRedirect("usercenter.htm");
		    break;
	    }
	} catch (ParseException ex) {
	    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @RequestMapping(value = "/admin")
    public ModelAndView toAdmin(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("admin");
	return mav;
    }
}
