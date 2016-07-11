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
    
    public int ifExit(Semester semester);
    
<<<<<<< HEAD
    public Semester getThisSemester();
=======
    public boolean deleteAll();
>>>>>>> 21d92ea569a097c9e8b74f340e5fd4fd2b007537
}
