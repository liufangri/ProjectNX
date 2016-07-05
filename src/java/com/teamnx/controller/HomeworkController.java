/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.HomeworkDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Y400
 */
@Controller
public class HomeworkController {

    private HomeworkDaoImpl hdi;

    public void setHdi(HomeworkDaoImpl hdi) {
	this.hdi = hdi;
    }

    @RequestMapping(value = "/submitHomework")
    public void submitHomework() {

    }
}
