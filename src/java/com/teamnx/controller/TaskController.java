/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.TaskDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Y400
 */
@Controller
public class TaskController {

    private TaskDaoImpl tdi;

    @RequestMapping(value = "/addTask")
    public void addTask() {

    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }
}
