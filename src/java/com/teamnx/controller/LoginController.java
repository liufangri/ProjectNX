/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Message;
import com.teamnx.model.MessageDaoImpl;
import com.teamnx.model.SemesterDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.MD5;
import java.io.IOException;
import java.util.ArrayList;
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
public class LoginController {

    private UserDaoImpl udi;
    private MessageDaoImpl mdi;
    private SemesterDaoImpl sdi;

    /**
     * 处理登录操作
     *
     * @author Y400
     * @param user
     * @param session
     */
    @RequestMapping(value = {"/loginAction"})
    public ModelAndView loginAction(User user, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
	ModelAndView mav = new ModelAndView("login");
	User findedUser = udi.findUserById(user.getId());
	if (findedUser != null) {
	    String password = MD5.Md5_16(user.getPassword());
	    if (password.equals(findedUser.getPassword())) {
		session.setAttribute("is_login", Boolean.TRUE);
		ArrayList<Message> unreadMessageList = mdi.getAllUnreadMessage(findedUser.getId());
		session.setAttribute("unread_message_list", unreadMessageList);
		session.setAttribute(password, mav);
		session.setAttribute("user", findedUser);

		switch (findedUser.getCharacter()) {
		    case User.STUDENT:
		    case User.TEACHER:
			response.sendRedirect("usercenter.htm");
			break;
		    case User.ADMIN:
			response.sendRedirect("admin.htm");
			break;
		}
	    } else {
		mav.addObject("error_message", "用户名或密码错误");
	    }

	} else {
	    mav.addObject("error_message", "用户名或密码错误");
	}
	return mav;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView toAdmin(HttpServletRequest request, HttpSession session) {
	ModelAndView mav = new ModelAndView("admin");
	return mav;
    }

    /**
     * 处理跳转到登录页面的请求
     */
    @RequestMapping(value = "login")
    public ModelAndView login() {
	ModelAndView mav = new ModelAndView("login");
	jumpToLoginPage(mav);
	return mav;
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpSession session) {
	ModelAndView mav = new ModelAndView("login");
	session.setAttribute("is_login", Boolean.FALSE);
	session.setAttribute("user", new User());
	return mav;
    }

    /**
     * 跳转到登录页面之前所有的操作
     *
     * @param mav
     */
    public void jumpToLoginPage(ModelAndView mav) {
	User user = new User();
	mav.addObject("user", user);
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

    public void setMdi(MessageDaoImpl mdi) {
	this.mdi = mdi;
    }

    /**
     * @param sdi the sdi to set
     */
    public void setSdi(SemesterDaoImpl sdi) {
        this.sdi = sdi;
    }
}
