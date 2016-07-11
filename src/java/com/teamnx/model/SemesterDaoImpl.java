/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import com.teamnx.db.DbcpBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHNKYON
 */
public class SemesterDaoImpl implements SemesterDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(Semester semester) {
        Connection connection = dbcpBean.getConnection();
        String sql = "INSERT INTO semester "
                + "(year, semester, start_day) "
                + "VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, semester.getYear());
            ps.setInt(2, semester.getSemester());
            java.sql.Date sqldate;
            sqldate = new java.sql.Date(semester.getStart_day().getTime());
            ps.setDate(3, sqldate);
            ps.executeUpdate();     
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean update(Semester semester) {
        Connection connection = dbcpBean.getConnection();
        String sql = "UPDATE semester SET start_day = ?"
                + "WHERE year=? AND semester=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(2, semester.getYear());
            ps.setInt(3, semester.getSemester());
            java.sql.Date sqldate;
            sqldate = new java.sql.Date(semester.getStart_day().getTime());
            ps.setDate(1, sqldate);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param dbcpBean the dbcpBean to set
     */
    public void setDbcpBean(DbcpBean dbcpBean) {
        this.dbcpBean = dbcpBean;
    }

    @Override
    public int ifExit(Semester semester) {
        Connection connection = dbcpBean.getConnection();
        String sql = "SELECT COUNT(*) FROM semester "
                + "WHERE year=? AND semester=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, semester.getYear());
            ps.setInt(2, semester.getSemester());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            else{
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Semester getThisSemester() {
        Connection connection = dbcpBean.getConnection();
        String sql = "SELECT * FROM semester";
        Semester semester = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                semester = new  Semester();
                semester.setYear(rs.getInt("year"));
                semester.setSemester(rs.getInt("semester"));
                semester.setStart_day(rs.getDate("start_day"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                return semester;
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                return semester;
            }
        }
    }
    
    @Override
    public boolean deleteAll() {
        Connection connection = dbcpBean.getConnection();
        String sql = "DELETE FROM semester";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();     
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
