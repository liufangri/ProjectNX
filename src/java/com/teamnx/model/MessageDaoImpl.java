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
 * @author Y400
 */
public class MessageDaoImpl implements MessageDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(Message message) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO message VALUES(?,?,?,?,?)";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, message.getId());
	    ps.setString(2, message.getReceiverId());
	    ps.setString(3, message.getText());
	    ps.setTimestamp(4, message.getTime());
	    ps.setBoolean(5, message.isStatus());
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    @Override
    public boolean delete(String id) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setReaded(String messageId) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE message SET status = ? WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setBoolean(1, true);
	    ps.setString(2, messageId);
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

    @Override
    public boolean setUnread(String messageId) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE message SET status = ? WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setBoolean(1, false);
	    ps.setString(2, messageId);
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    @Override
    public ArrayList<Message> getAllMessage(String receiverId) {
	ArrayList<Message> messageList = new ArrayList<Message>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM message WHERE receicer_id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, receiverId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Message message = new Message();
		message.setId(rs.getString("id"));
		message.setReceiverId(rs.getString("receiver_id"));
		message.setStatus(rs.getBoolean("status"));
		message.setText(rs.getString("text"));
		message.setTime(rs.getTimestamp("time"));
		messageList.add(message);

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();

	    } catch (SQLException ex) {
		Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return messageList;
	}

    }

    @Override
    public ArrayList<Message> getAllUnreadMessage(String receiverId) {
	ArrayList<Message> messageList = new ArrayList<Message>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM message WHERE receicer_id = ? AND status = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, receiverId);
	    ps.setBoolean(2, false);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Message message = new Message();
		message.setId(rs.getString("id"));
		message.setReceiverId(rs.getString("receiver_id"));
		message.setStatus(rs.getBoolean("status"));
		message.setText(rs.getString("text"));
		message.setTime(rs.getTimestamp("time"));
		messageList.add(message);

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();

	    } catch (SQLException ex) {
		Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return messageList;
	}
    }

    @Override
    public boolean delete(Message message) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public Message findMessageById(String messageId) {
	Message message = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM message WHERE id = ?";
	try {

	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, messageId);
	    ResultSet rs = ps.executeQuery();
	    message = new Message();
	    message.setId(rs.getString("id"));
	    message.setReceiverId(rs.getString("receiver_id"));
	    message.setStatus(rs.getBoolean("status"));
	    message.setText(rs.getString("text"));
	    message.setTime(rs.getTimestamp("time"));

	} catch (SQLException ex) {
	    Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(MessageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return message;
	}
    }

}
