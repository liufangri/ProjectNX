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
public class CommentDaoImpl implements CommentDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(Comment comment) {
        Connection connection = dbcpBean.getConnection();
        String sql = "INSERT INTO comment"
                + "(id, course_id, user_name, mycharacter, time, comment) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, comment.getId());
            ps.setString(2, comment.getCourse_id());
            ps.setString(3, comment.getUser_name());
            ps.setInt(4, comment.getCharacter());
            ps.setTimestamp(5, comment.getTimestamp());
            ps.setString(6, comment.getComment());
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
    public ArrayList<Comment> findCommentsByCourse(String courseId) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Connection connection = dbcpBean.getConnection();
        String sql = "SELECT * FROM comment "
                + "WHERE course_id = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getString("id"));
                comment.setCourse_id(rs.getString("course_id"));
                comment.setUser_name(rs.getString("user_name"));
                comment.setCharacter(rs.getInt("mycharacter"));
                comment.setTimestamp(rs.getTimestamp("time"));
                comment.setComment(rs.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException ex) {

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return comments;
        }
    }

    /**
     * @param dbcpBean the dbcpBean to set
     */
    public void setDbcpBean(DbcpBean dbcpBean) {
        this.dbcpBean = dbcpBean;
    }

}
