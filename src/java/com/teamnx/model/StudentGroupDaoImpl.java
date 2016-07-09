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
public class StudentGroupDaoImpl implements StudentGroupDao {

    private DbcpBean dbcpBean;

    /**
     * @param dbcpBean the dbcpBean to set
     */
    public void setDbcpBean(DbcpBean dbcpBean) {
        this.dbcpBean = dbcpBean;
    }

    @Override
    public boolean insert(StudentGroup studentGroup) {
        Connection connection = dbcpBean.getConnection();
        String sql = "INSERT INTO student_group"
                + "(id, student_id, group_id, course_id, student_name, status) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentGroup.getId());
            ps.setString(2, studentGroup.getStudentId());
            ps.setString(3, studentGroup.getGroupId());
            ps.setString(4, studentGroup.getCourseId());
            ps.setString(5, studentGroup.getStudentName());
            ps.setBoolean(6, studentGroup.isStatus());
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
        String sql = "DELETE FROM student_group WHERE id = ?";
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
    public boolean deleteByGroupId(String groupId) {
        Connection connection = dbcpBean.getConnection();
        boolean flag = false;
        String sql = "DELETE FROM student_group WHERE group_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, groupId);
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
    public boolean deleteByStudentId(String studentId, String groupId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<StudentGroup> findstudentGroupsByGroupId(String groupId) {
        Connection connection = dbcpBean.getConnection();
        ArrayList<StudentGroup> studentGroups = new ArrayList<StudentGroup>();
        String sql = "SELECT * FROM student_group WHERE "
                + "group_id = ? AND status = True";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, groupId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentGroup studentGroup = new StudentGroup();
                studentGroup.setId(rs.getString("id"));
                studentGroup.setStudentId(rs.getString("student_id"));
                studentGroup.setGroupId(rs.getString("group_id"));
                studentGroup.setCourseId(rs.getString("course_id"));
                studentGroup.setStudentName(rs.getString("student_name"));
                studentGroup.setStatus(rs.getBoolean("status"));
                studentGroups.add(studentGroup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return studentGroups;
        }
    }

    @Override
    public int countMember(String groupId) {
        Connection connection = dbcpBean.getConnection();
        int counter = 0;
        String sql = "SELECT COUNT(id) FROM student_group WHERE group_id = ? AND status = True";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, groupId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                counter += rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return counter;
        }
    }

    @Override
    public boolean ifInGroup(String studentId, String courseId) {
        Connection connection = dbcpBean.getConnection();
        boolean flag = false;
        int counter = 0;
        String sql = "SELECT COUNT(id) FROM student_group WHERE student_id = ? AND course_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) != 0) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public ArrayList<StudentGroup> findApplyList(String groupId) {
        Connection connection = dbcpBean.getConnection();
        ArrayList<StudentGroup> studentGroups = new ArrayList<StudentGroup>();
        String sql = "SELECT * FROM student_group WHERE "
                + "group_id = ? AND status = False";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, groupId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentGroup studentGroup = new StudentGroup();
                studentGroup.setId(rs.getString("id"));
                studentGroup.setStudentId(rs.getString("student_id"));
                studentGroup.setGroupId(rs.getString("group_id"));
                studentGroup.setCourseId(rs.getString("course_id"));
                studentGroup.setStudentName(rs.getString("student_name"));
                studentGroup.setStatus(rs.getBoolean("status"));
                studentGroups.add(studentGroup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return studentGroups;
        }
    }

    @Override
    public boolean accpet(String sgId) {
        Connection connection = dbcpBean.getConnection();
        boolean flag = false;
        String sql = "UPDATE student_group SET status = True WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sgId);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public StudentGroup findById(String id) {
        Connection connection = dbcpBean.getConnection();
        StudentGroup sg = null;
        String sql = "SELECT * FROM student_group WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sg = new StudentGroup();
                sg.setId(rs.getString("id"));
                sg.setCourseId(rs.getString("course_id"));
                sg.setGroupId(rs.getString("group_id"));
                sg.setStudentId(rs.getString("student_id"));
                sg.setStudentName(rs.getString("student_name"));
                sg.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return sg;
        }
    }

    @Override
    public StudentGroup findByCourseGroup(String courseId, String groupId) {
        Connection connection = dbcpBean.getConnection();
        StudentGroup sg = null;
        String sql = "SELECT * FROM student_group WHERE "
                + "course_id = ? AND group_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseId);
            ps.setString(2, groupId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sg = new StudentGroup();
                sg.setId(rs.getString("id"));
                sg.setCourseId(rs.getString("course_id"));
                sg.setGroupId(rs.getString("group_id"));
                sg.setStudentId(rs.getString("student_id"));
                sg.setStudentName(rs.getString("student_name"));
                sg.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return sg;
        }
    }

    @Override
    public StudentGroup findByStudentGroup(String studentId, String groupId) {
        Connection connection = dbcpBean.getConnection();
        StudentGroup sg = null;
        String sql = "SELECT * FROM student_group WHERE "
                + "student_id = ? AND group_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, groupId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sg = new StudentGroup();
                sg.setId(rs.getString("id"));
                sg.setCourseId(rs.getString("course_id"));
                sg.setGroupId(rs.getString("group_id"));
                sg.setStudentId(rs.getString("student_id"));
                sg.setStudentName(rs.getString("student_name"));
                sg.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return sg;
        }
    }
    
}
