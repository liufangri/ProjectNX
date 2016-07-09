/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Homework;
import com.teamnx.model.HomeworkDaoImpl;
import com.teamnx.model.Resource;
import com.teamnx.model.ResourceDaoImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Y400
 */
@Controller
public class DownloadController {

    private HomeworkDaoImpl hdi;
    private ResourceDaoImpl rdi;

    @RequestMapping("/download")
    public String download(HttpServletRequest request,
	    HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
	String homeworkId = request.getParameter("homeworkId");
	String resourceId = request.getParameter("resourceId");
	String filePath;
	if (homeworkId != null) {
	    Homework homework = hdi.findHomeworkById(homeworkId);
	    filePath = session.getServletContext().getRealPath("/WEB-INF") + homework.getFilePath();
	} else if (resourceId != null) {
	    Resource resource = rdi.findResourceById(resourceId);
	    filePath = session.getServletContext().getRealPath("/WEB-INF") + resource.getPath();
	} else {
	    filePath = "Nothing";
	}
	response.setCharacterEncoding("utf-8");
	response.setContentType("multipart/form-data");
	if (!filePath.equals("Nothing")) {
	    File file = new File(filePath);
	    String fileName = file.getName();
	    response.setHeader("Content-Disposition", "attachment;fileName="
		    + URLEncoder.encode(fileName, "utf-8"));
	    try {
		InputStream inputStream = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		byte[] b;
		b = new byte[(int) file.length()];
		int length;
		while ((length = inputStream.read(b)) > 0) {
		    os.write(b, 0, length);
		}

		// 这里主要关闭。
		os.close();

		inputStream.close();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    //  返回值要注意，要不然就出现下面这句错误！
	    //java+getOutputStream() has already been called for this response
	}
	return null;
    }

    public void setHdi(HomeworkDaoImpl hdi) {
	this.hdi = hdi;
    }

    public void setRdi(ResourceDaoImpl rdi) {
	this.rdi = rdi;
    }
}
