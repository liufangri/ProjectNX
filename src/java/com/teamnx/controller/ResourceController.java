/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.CourseDaoImpl;
import com.teamnx.model.HomeworkDaoImpl;
import com.teamnx.model.JSONNode;
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
import net.sf.json.JSONObject;
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
	if (duplicate(fatherId, name)) {
	    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + fatherId);
	} else {
	    Resource currentResource = rdi.findResourceById(fatherId);
	    if (currentResource == null) {
		//当前所在文件夹已经被删除
		response.sendRedirect("resource.htm?course_id=" + courseId);
	    } else {
		String teacherName = teacher.getName();
		Resource folder = new Resource();
		String folderId = MD5.Md5_16(new Date().getTime() + teacherName);
		folder.setId(folderId);
		folder.setName(name);
		folder.setFatherId(fatherId);
		folder.setFolder(true);
		folder.setCourseId(courseId);
		folder.setTeacherId(teacher.getId());
		folder.setTeacherName(teacherName);
		Resource courseRoot = rdi.findCourseRootFolder(courseId);
		folder.setPath(courseRoot.getPath() + "\\" + name);
		folder.setLastChange(new Date().getTime());

		//部分刷新页面也可以
		if (rdi.insert(folder)) {
		    response.sendRedirect("resource.htm?course_id=" + currentResource.getCourseId()
			    + "&folder_id=" + folder.getFatherId()
		    );
		} else {
		    //插入失败错误处理
		    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + fatherId);
		}
	    }
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
	    //遍历当前目录下所有的文件
	    //查看是否有重名文件
	    if (duplicate(resource.getFatherId(), name)) {
		response.sendRedirect("resource.htm?course_id=" + resource.getCourseId() + "&folder_id=" + resource.getFatherId());
	    } else {
		Resource fatherFolder = rdi.findResourceById(resource.getFatherId());
		if (fatherFolder != null) {
		    Resource courseRoot = rdi.findCourseRootFolder(fatherFolder.getCourseId());
		    resource.setPath(courseRoot.getPath() + "\\" + id);
		    String realPath = session.getServletContext().getRealPath("/WEB-INF") + resource.getPath();
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
	    }
	} else {
	    //文件名错误
	    response.sendRedirect("resource.htm?course_id=" + resource.getCourseId());
	}
    }

    /**
     * 获取当前文件树
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getall")
    public void getAllFolder(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String resourceId = request.getParameter("resource_id");
	ArrayList<Resource> resources = rdi.findCourseResources(courseId);
	Resource rootFolder = rdi.findCourseRootFolder(courseId);
	JSONNode jsonnode = new JSONNode();
	jsonnode.setId(rootFolder.getId());
	jsonnode.setName("所有资料");
	getJson(resources, jsonnode, resourceId);
	JSONObject jsono = JSONObject.fromObject(jsonnode);
	String out = jsono.toString();
	out = "[" + out + "]";
	response.setCharacterEncoding("utf-8");
	response.getWriter().write(out);
	response.getWriter().flush();
    }

    /**
     * 获得文件系统JSON
     *
     * @param resources
     * @param node
     */
    private void getJson(ArrayList<Resource> resources, JSONNode node, String resourceId) {
	if (!resources.isEmpty()) {
	    ArrayList<JSONNode> jsonns = new ArrayList<JSONNode>();
	    for (Resource r : resources) {
		if (r.isFolder() && !r.getId().equals(resourceId)) {
		    JSONNode jsonn = new JSONNode();
		    jsonn.setId(r.getId());
		    jsonn.setName(r.getName());
		    jsonns.add(jsonn);
		    ArrayList<Resource> children = rdi.findChildren(r);
		    getJson(children, jsonn, resourceId);
		}
	    }
	    if (!jsonns.isEmpty()) {

		node.setNode(jsonns);
	    }

	}
    }

    /**
     * 重命名文件夹或文件
     *
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/renameResource")
    public void rename(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
	String name = request.getParameter("name");
	String resourceId = request.getParameter("resource_id");
	String courseId = request.getParameter("course_id");
	Resource resource = rdi.findResourceById(resourceId);
	if (resource != null) {
	    if (!duplicate(resource.getFatherId(), name)) {
		request.setAttribute("course_id", courseId);
		String originName = resource.getName();
		int position = originName.lastIndexOf(".");
		if (position != -1) {
		    resource.setName(name + originName.substring(position, originName.length()));
		} else {
		    resource.setName(name);
		}
		if (rdi.updateName(resource)) {
		    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
		} else {
		    //数据库修改名称失败
		    response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
		}
	    } else {
		//存在重名文件
		response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + resource.getFatherId());
	    }

	} else {
	    //当前的资源不存在
	    response.sendRedirect("resource.htm?course_id=" + courseId);
	}

    }

    /**
     * 移动文件
     *
     * @param request
     */
    @RequestMapping(value = "/transportResource")
    public void transResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String courseId = request.getParameter("course_id");
	String sourceId = request.getParameter("resource_id");
	String folderId = request.getParameter("aim_id");
	request.setAttribute("course_id", courseId);
	Resource resource = rdi.findResourceById(sourceId);
	Resource folder = rdi.findResourceById(folderId);
	if (folder != null && resource != null) {
	    String currentFolderId = resource.getFatherId();
	    resource.setFatherId(folder.getId());
	    if (rdi.updatePath(resource)) {
		response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + currentFolderId);
	    } else {
		response.sendRedirect("resource.htm?course_id=" + courseId + "&folder_id=" + currentFolderId);
	    }
	} else {
	    response.sendRedirect("resource.htm?course_id=" + courseId);
	}

    }

    /**
     * 检查重名的方法
     *
     * @return
     */
    private boolean duplicate(String fatherId, String name) {
	ArrayList<Resource> resources = rdi.findChildrenByFolderId(fatherId);
	if (resources.size() > 0) {
	    for (Resource r : resources) {
		if (name.equals(r.getName())) {
		    return true;
		}
	    }
	    return false;
	} else {

	    return false;
	}
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
