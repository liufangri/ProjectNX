/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

/**
 *
 * @author coco
 */
@Controller
public class GetAllController {

    public void submitHomework(HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	String s = "[{\"text\":\"\\u6240\\u6709\\u8d44\\u6599\",\"mapId\":0,\"nodes\":[{\"mapId\":\"38\",\"path\":\"\\/\",\"text\":\"\\u4fe1\\u606f\"},{\"mapId\":\"44\",\"path\":\"\\/\",\"text\":\"123\",\"nodes\":[{\"mapId\":\"181\",\"path\":\"\\/44\\/\",\"text\":\"as\",\"nodes\":[{\"mapId\":\"72\",\"path\":\"\\/44\\/181\\/\",\"text\":\"aaa\"}]}]},{\"mapId\":\"166\",\"path\":\"\\/\",\"text\":\"aaaa\"},{\"mapId\":\"194\",\"path\":\"\\/\",\"text\":\"asdfadd\"}]}]";
	response.getWriter().write(s);
	response.getWriter().flush();
    }

}
