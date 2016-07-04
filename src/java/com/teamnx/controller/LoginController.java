/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.MD5;
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

    /**
     * 处理登录操作
     *
     * @author Y400
     * @param
     */
    @RequestMapping(value = {"/loginAction"})
    public ModelAndView loginAction(User user, HttpSession session) {
	ModelAndView mav = new ModelAndView("login");
	User findedUser = udi.findUserById(user.getId());
	if (findedUser != null) {
	    String password = MD5.Md5_16(user.getPassword());
	    if (password.equals(findedUser.getPassword())) {
		mav.setViewName("usercenter");
		mav.addObject("user", findedUser);
	    } else {
		mav.addObject("error_message", "用户名或密码错误");
	    }

	} else {
	    mav.addObject("error_message", "用户名或密码错误");
	}
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
}
