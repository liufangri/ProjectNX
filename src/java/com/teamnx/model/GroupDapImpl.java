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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHNKYON
 */
public class GroupDapImpl implements GroupDao {

    private DbcpBean dbcpBean;

    /**
     * @param dbcpBean the dbcpBean to set
     */
    public void setDbcpBean(DbcpBean dbcpBean) {
        this.dbcpBean = dbcpBean;
    }

    @Override
    public Group findGroupById(String id) {
        Group group = null;
        Connection connection = dbcpBean.getConnection();
        String sql = "SELECT * FROM mygroup WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                group = new Group();
                group.setId(rs.getString("id"));
                group.setName(rs.getString("name"));
                group.setCourseId(rs.getString("course_id"));
                group.setManagerId("manager_id");
                group.setMaxMember(rs.getInt("max_member"));
                group.setStatus(rs.getInt("status"));
            }
        } catch (SQLException ex) {

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return group;
        }
    }

    @Override
    public Group findGroupByStudentId(String student_id, String course_id) {
        Group group = null;
        Connection connection = dbcpBean.getConnection();
        String sql = "SELECT * FROM mygroup WHERE id = ("
                + "SELECT group_id FROM student_group "
                + "WHERE student_id = ? AND course_id = ? AND status = True)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, student_id);
            ps.setString(2, course_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                group = new Group();
                group.setId(rs.getString("id"));
                group.setName(rs.getString("name"));
                group.setCourseId(rs.getString("course_id"));
                group.setManagerId(rs.getString("manager_id"));
                group.setMaxMember(rs.getInt("max_member"));
                group.setStatus(rs.getInt("status"));
            }
        } catch (SQLException ex) {

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return group;
        }
    }

    @Override
    public ArrayList<Group> findGroupsByCourseId(String course_id) {
        ArrayList<Group> groups = new ArrayList<Group>();
        Connection connection = dbcpBean.getConnection();
        String sql = "SELECT * FROM mygroup "
                + "WHERE course_id = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, course_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getString("id"));
                group.setName(rs.getString("name"));
                group.setCourseId(rs.getString("course_id"));
                group.setManagerId(rs.getString("manager_id"));
                group.setMaxMember(rs.getInt("max_member"));
                group.setStatus(rs.getInt("status"));
                groups.add(group);
            }
        } catch (SQLException ex) {

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return groups;
        }
    }

    @Override
    public boolean insert(Group group) {
        Connection connection = dbcpBean.getConnection();
        String sql = "INSERT INTO mygroup "
                + "(id, name, course_id, status, max_member, manager_id) "
                + "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, group.getId());
            ps.setString(2, group.getName());
            ps.setString(3, group.getCourseId());
            ps.setInt(4, group.getStatus());
            ps.setInt(5, group.getMaxMember());
            ps.setString(6, group.getManagerId());
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
    public boolean delete(String id) {
        Connection connection = dbcpBean.getConnection();
        boolean flag = false;
        String sql = "DELETE FROM mygroup WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return flag;
        }
    }

    @Override
    public boolean setManager(String groupId, String student_id) {
        boolean flag = false;
        Connection connection = dbcpBean.getConnection();
        String sql = "UPDATE mygroup SET manager_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, student_id);
            ps.setString(2, groupId);
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
    public boolean setStatus(String groupId, int status) {
        boolean flag = false;
        Connection connection = dbcpBean.getConnection();
        String sql = "UPDATE mygroup SET status = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, groupId);
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
