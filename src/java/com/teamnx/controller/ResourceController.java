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
import java.util.ArrayList;
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
		String realPath = session.getServletContext().getRealPath("/WEB-INF") + folder.getPath();
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
	Resource chooseResource = rdi.findResourceById(resourceId);
	if (chooseResource != null) {
	    String realPath = session.getServletContext().getRealPath("/WEB-INF") + chooseResource.getPath();
	    File chooseFile = new File(realPath);
	    if (chooseResource.isFolder()) {
		ArrayList<Resource> resources = rdi.findChildrenByFolderId(resourceId);
		if (resources.size() > 0) {
		    //文件夹不为空,返回错误
		} else if (chooseFile.exists()) {
		    if (rdi.delete(chooseResource)) {
			chooseFile.delete();
		    }
		} else {
		    rdi.delete(chooseResource);
		}
	    } else if (chooseFile.exists()) {
		if (rdi.delete(chooseResource)) {
		    chooseFile.delete();
		}
	    }
	    response.sendRedirect("resource.htm?course_id=" + chooseResource.getCourseId() + "&folder_id=" + chooseResource.getFatherId());
	} else {
	    //所选择的文件已被删除
	    response.sendRedirect("resource.htm?course_id=" + chooseResource.getCourseId() + "&folder_id=" + chooseResource.getFatherId());
	}
    }

    /**
     * 上传文件的请求
     *
     * @param resource
     * @param request
     * @param response
     * @param uploadFile
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile")
    public void uploadFile(Resource resource, HttpServletRequest request, HttpSession session,
	    HttpServletResponse response, MultipartFile uploadFile) throws IOException {
	String id = MD5.Md5_16(new Date().getTime() + resource.getTeacherName());
	resource.setId(id);
	resource.setFolder(false);
	String name = uploadFile.getOriginalFilename();
	resource.setName(name);
	if (name != null && name != "") {
	    Resource fatherFolder = rdi.findResourceById(resource.getFatherId());
	    if (fatherFolder != null) {
		resource.setPath(fatherFolder.getPath() + "\\" + name);
		String realPath = session.getServletContext().getRealPath("/WEB-INF") + fatherFolder.getPath() + "\\" + name;
		File file = new File(realPath);
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

    @RequestMapping(value = "/renameResource")
    public void rename(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	String name = request.getParameter("name");
	String resourceId = request.getParameter("resource_id");
	String courseId = request.getParameter("course_id");
	Resource resource = rdi.findResourceById(resourceId);
	if (resource != null) {
	    if (rdi.findChildren(resource).size() > 0) {
		//文件夹级联更改路径
		response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
	    } else {
		request.setAttribute("course_id", courseId);
		String originName = resource.getName();
		String path = resource.getPath();
		int position = path.lastIndexOf(".");
		if (position != -1) {
		    resource.setName(name + path.substring(position, path.length()));
		    String realPath = session.getServletContext().getRealPath("/WEB-INF") + resource.getPath();
		    File originFile = new File(realPath);
		    String newPath = realPath.substring(0, realPath.length() - originName.length()) + name + path.substring(position, path.length());
		    File newFile = new File(newPath);
		    resource.setPath(path.substring(0, path.length() - originName.length()) + name + path.substring(position, path.length()));
		    if (originFile.renameTo(newFile)) {
			if (rdi.updateName(resource)) {
			    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
			} else {
			    //文件重命名失败
			    newFile.renameTo(new File(realPath));
			    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
			}
		    } else {
			response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
		    }
		} else {
		    resource.setName(name);
		    String realPath = session.getServletContext().getRealPath("/WEB-INF") + resource.getPath();
		    File originFile = new File(realPath);
		    String newPath = realPath.substring(0, realPath.length() - originName.length()) + name;
		    File newFile = new File(newPath);
		    resource.setPath(path.substring(0, path.length() - originName.length()) + name);
		    if (originFile.renameTo(newFile)) {
			if (rdi.updateName(resource)) {
			    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
			} else {
			    //文件重命名失败
			    newFile.renameTo(new File(realPath));
			    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
			}
		    } else {
			response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
		    }
		}
	    }
	} else {
	    //当前的资源不存在
	    response.sendRedirect("resource.htm?course_id=" + courseId);
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
