/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.HomeworkDaoImpl;
import com.teamnx.model.Resource;
import com.teamnx.model.ResourceDaoImpl;
import com.teamnx.model.TaskDaoImpl;
import com.teamnx.model.User;
import com.teamnx.model.UserDaoImpl;
import com.teamnx.util.MD5;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Y400
 */
@Controller
public class ResourceController {

    private UserDaoImpl udi;
    private HomeworkDaoImpl hdi;
    private ResourceDaoImpl rdi;
    private TaskDaoImpl tdi;
    private CourseDaoImpl cdi;

    /**
     * 新建文件夹
     *
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/addNewFolder")
    public void addNewFolder(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	String name = request.getParameter("name");
	String courseId = request.getParameter("course_id");
	String fatherId = request.getParameter("current_resource_id");
	User teacher = (User) session.getAttribute("user");
	//检查重名,建议放在前端做。
	if (duplicate()) {

	} else {
	    Resource currentResource = rdi.findResourceById(fatherId);
	    if (currentResource == null) {
		//当前所在文件夹已经被删除

	    } else {
		String fatherName = currentResource.getName();
		String fatherPath = currentResource.getPath();
		//修改此处老师名字
		String teacherName = teacher.getName();
		Resource folder = new Resource();

		folder.setId(MD5.Md5_16(fatherId + name));
		folder.setName(name);
		folder.setFatherId(fatherId);
		folder.setFolder(true);
		folder.setCourseId(courseId);
		folder.setTeacherId(teacher.getId());
		folder.setTeacherName(teacherName);
		folder.setPath(fatherPath + "\\" + name);
		String realPath = session.getServletContext().getRealPath("/WEB-INF") + "\\courseResources" + folder.getPath();
		File file = new File(realPath);
		if (!file.exists()) {
		    file.mkdirs();
		    folder.setLastChange(file.lastModified());
		}

		//部分刷新页面也可以
		if (rdi.insert(folder)) {
		    response.sendRedirect("resource.htm?course_id=" + currentResource.getCourseId()
			    + "&folder_id=" + folder.getFatherId()
		    );
		} else {
		    //插入失败错误处理
		    response.sendRedirect("resource.htm?folder_id=" + fatherId);
		}
	    }
	}
    }

    /**
     * 回到上一级文件夹
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/preFolder")
    public void preFolder(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String currentResourceId = (String) request.getParameter("current_resource_id");
	Resource currentResource = rdi.findResourceById(currentResourceId);
	if (currentResource == null) {
	    //当前文件夹已经被删除，可以写错误提示并跳回首页？
	    response.sendRedirect("resourcepage.htm");
	} else {
	    response.sendRedirect("resourcepage.htm?folder_id=" + currentResource.getFatherId());
	}
    }

    /**
     * 处理单个文件的删除请求
     *
     * @param session
     * @param request
     */
    @RequestMapping(value = "/deleteResource")
    public void deleteResource(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	String resourceId = request.getParameter("resource_id");
	Resource currentResource = rdi.findResourceById(resourceId);
	if (currentResource == null) {
	    //当前所在文件夹不存在，返回资源首页
	    response.sendRedirect("resourcepage.htm");
	} else {
	    Resource resource = rdi.findResourceById(resourceId);
	    if (resource == null) {
		//当前文件不存在，报错
		response.sendRedirect("resourcepage.htm?folder_id=" + currentResource.getId());
	    } else if (rdi.delete(resource)) {
		//数据库删除成功
		String realPath;
		realPath = session.getServletContext().getRealPath("/WEB-INF")
			+ "\\courseResources" + resource.getPath();
		File resourceAtProject = new File(realPath);
		if (resourceAtProject.exists()) {
		    try {
			if (resourceAtProject.delete()) {
			    //成功删除物理存储的文件
			    response.sendRedirect("resourcepage.htm?folder_id=" + currentResource.getId());
			} else {
			    //文件夹不为空，无法删除

			}
		    } catch (SecurityException e) {
			//文件系统拒绝了删除请求

			e.printStackTrace();
		    }
		}
	    } else {
		//数据库删除失败
	    }
	}
    }

    @RequestMapping(value = "/uploadFile")
    public void uploadFile(Resource resource, HttpServletRequest request,
	    HttpServletResponse response, MultipartFile uploadFile) throws IOException {
	String id = MD5.Md5_16(new Date().getTime() + resource.getTeacherName());
	resource.setId(id);
	resource.setFolder(false);
	String name = uploadFile.getOriginalFilename();
	resource.setName(name);
	if (name != null && name != "") {
	    Resource fatherFolder = rdi.findResourceById(resource.getFatherId());
	    if (fatherFolder != null) {
		String path = fatherFolder.getPath() + "\\" + name;
		resource.setPath(path);
		File file = new File(path);
		if (file.exists()) {
		    file.delete();
		}
		file.mkdirs();
		resource.setLastChange(file.lastModified());
		uploadFile.transferTo(file);
		if (rdi.insert(resource)) {
		    response.sendRedirect("resource.htm?course_id=" + resource.getCourseId() + "&folder_id=" + resource.getFatherId());
		} else {
		    file.delete();
		    response.sendRedirect("resource.htm?course_id=" + resource.getCourseId() + "&folder_id=" + resource.getFatherId());
		}
	    } else {
		//父文件夹不存在
		response.sendRedirect("resource.htm?course_id=" + resource.getCourseId());
	    }
	} else {
	    //文件名错误
	    response.sendRedirect("resource.htm?course_id=" + resource.getCourseId());
	}
    }

    /**
     * 检查重名的方法
     *
     * @return
     */
    private boolean duplicate() {
	return false;
    }

    public void setHdi(HomeworkDaoImpl hdi) {
	this.hdi = hdi;
    }

    public void setRdi(ResourceDaoImpl rdi) {
	this.rdi = rdi;
    }

    public void setTdi(TaskDaoImpl tdi) {
	this.tdi = tdi;
    }

    public void setCdi(CourseDaoImpl cdi) {
	this.cdi = cdi;
    }

    public void setUdi(UserDaoImpl udi) {
	this.udi = udi;
    }

}
