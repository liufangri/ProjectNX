/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.Date;

/**
 *
 * @author JOHNKYON
 */
public interface SemesterDao {
    public boolean insert(Semester semester);
    
    public boolean update(Semester semester);
}
