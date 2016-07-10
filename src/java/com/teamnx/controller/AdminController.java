/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.SemesterDaoImpl;
import org.springframework.stereotype.Controller;

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
    
    
}
