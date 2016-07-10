/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.controller;

import com.teamnx.model.Comment;
import com.teamnx.model.CommentDaoImpl;
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
 * @author JOHNKYON
 */
@Controller
public class CommentController {
    
    private CommentDaoImpl cdi;
    
    @RequestMapping(value = "/commentList")
    public ModelAndView commentList(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView("comment_board");
        String courseId = request.getParameter("course_id");
        ArrayList<Comment> comments = cdi.findCommentsByCourse(courseId);
        request.setAttribute("course_id", courseId);
        mav.addObject("comments", comments);
        return mav;
    }
    
    @RequestMapping(value = "/addComment")
    public void addComment(HttpSession session,Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String courseId = comment.getCourse_id();
        User user = (User) session.getAttribute("user");
        String commentId = MD5.Md5_16(user.getName() + new Date().getTime());
        comment.setId(commentId);
        comment.setTimestamp(new Timestamp(new Date().getTime()));
        comment.setUser_name(user.getName());
        comment.setCharacter(user.getCharacter());
        cdi.insert(comment);
        response.sendRedirect("commentList.htm?&course_id="+courseId);
    }
    

    /**
     * @param cdi the cdi to set
     */
    public void setCdi(CommentDaoImpl cdi) {
        this.cdi = cdi;
    }
    
}
