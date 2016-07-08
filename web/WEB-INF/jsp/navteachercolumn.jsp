<%-- 
    Document   : navheader
    Created on : 2016-7-3, 15:14:48
    Author     : coco
--%>
<%@page import="com.teamnx.model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Course course = (Course) request.getAttribute("current_course");
    String courseId = (String) request.getAttribute("course_id");
%>
<script>
    window.onload = function ()
    {
        document.getElementById("${param.type}").className = 'active';
    }
</script>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li id="index"><a href="index.htm?course_id=<%= courseId%>">课程信息 <span class="sr-only">(current)</span></a></li>
        <li id="homework"><a href="te_homework.htm?course_id=<%= courseId%>">作业</a></li>
        <li id="resource"><a href="resource.htm?course_id=<%= courseId%>">课程资源</a></li>
        <li><a href="">团队管理</a></li>
        <li id="studentlist"><a href="te_studentlist.htm?course_id=<%= courseId%>">学生名单</a></li>
        <li><a href="">学生留言</a></li>
    </ul>
</div>