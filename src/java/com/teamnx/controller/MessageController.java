/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Message;
import com.teamnx.model.MessageDaoImpl;
import com.teamnx.model.User;
import com.teamnx.util.MD5;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Y400
 */
@Controller
public class MessageController {

    private MessageDaoImpl mdi;

    public boolean sendMessage(String receiverId, String text) {
	String id = MD5.Md5_16(new Date().getTime() + receiverId + (int) 10 * Math.random());
	Timestamp time = new Timestamp(new Date().getTime());
	Message message = new Message();
	message.setId(id);
	message.setReceiverId(receiverId);
	message.setStatus(false);
	message.setText(text);
	message.setTime(time);
	if (mdi.insert(message)) {
	    return true;
	} else {
	    return false;
	}
    }

    @RequestMapping(value = "/allMessage")
    public ModelAndView toAllMessagePage(HttpServletRequest request, HttpSession session) {
	User user = (User) session.getAttribute("user");
	ArrayList<Message> messageList = mdi.getAllMessage(user.getId());
	request.setAttribute("message_list", messageList);
	ModelAndView mav = new ModelAndView("message_all");
	return mav;
    }

    @RequestMapping(value = "/isread")
    public void readAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String studentId = request.getParameter("user_id");
	mdi.setAllReaded(studentId);
    }

    public void setMdi(MessageDaoImpl mdi) {
	this.mdi = mdi;
    }

}
