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
    boolean isTeamCourse = courseId.charAt(courseId.length() - 1) == '1';
%>
<script>
    window.onload = function ()
    {
        document.getElementById("${param.type}").className = 'active';
    }
</script>
<div class="col-sm-3 col-md-2 navbar-vertical-left">
    <ul class="nav navbar-nav">
        <li id="index">
            <a href="index.htm?course_id=<%=courseId%>">
                <i class="fa  fa-fw fa-lg fa-info-circle"></i> 
                <span>课程信息</span>
            </a>
        </li>
        <li id="homework">
            <a href="te_homework.htm?course_id=<%=courseId%>">
                <i class="fa fa-fw fa-lg fa-pencil-square"></i>
                <span>作业</span>
            </a>
        </li>
        <li id="resource">
            <a href="resource.htm?course_id=<%= courseId%>">
                <i class="fa  fa-fw fa-lg fa-folder-open"></i>
                <span>课程资源</span>
            </a>
        </li>
        <% if (isTeamCourse) {%>
        <li id="team">
            <a href="teacherGroupList.htm?course_id=<%= courseId%>">
                <i class="fa  fa-fw fa-lg fa-group"></i>
                <span>团队管理</span>
            </a>
        </li>
        <%}%>
        <li id="studentlist">
            <a href="te_studentlist.htm?course_id=<%= courseId%>">
                <i class="fa  fa-fw fa-lg fa-file"></i>
                <span>学生名单</span>
            </a>
        </li>
        <li id="comment">
            <a href="commentList.htm?course_id=<%= courseId%>">
                <i class="fa  fa-fw fa-lg fa-comments"></i>
                <span>学生留言</span>
            </a>
        </li>
    </ul>
</div>