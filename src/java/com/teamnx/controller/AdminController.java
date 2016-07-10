/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Semester;
import com.teamnx.model.SemesterDaoImpl;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author JOHNKYON
 */
@Controller
public class AdminController {

    private SemesterDaoImpl smdi;

    /**
     * @param smdi the smdi to set
     */
    public void setSmdi(SemesterDaoImpl smdi) {
        this.smdi = smdi;
    }

    @RequestMapping("/setSemester")
    public void setSemester(Semester semester, HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (smdi.ifExit(semester)) {
            case -1:
                response.sendRedirect("usercenter.htm");
                break;
            case 0:
                smdi.insert(semester);
                response.sendRedirect("usercenter.htm");
                break;
            default:
                smdi.update(semester);
                response.sendRedirect("usercenter.htm");
                break;
        }
    }

}
