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
public class Semester {
    private int year;
    private int semester;
    private Date start_day;

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the semester
     */
    public int getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * @return the start_day
     */
    public Date getStart_day() {
        return start_day;
    }

    /**
     * @param start_day the start_day to set
     */
    public void setStart_day(Date start_day) {
        this.start_day = start_day;
    }
    
}
