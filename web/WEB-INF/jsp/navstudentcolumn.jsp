<%-- 
    Document   : navstudentcolumn
    Created on : 2016-7-3, 15:14:48
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String courseId = (String) request.getAttribute("course_id");
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
            <a href="stu_homework.htm?course_id=<%=courseId%>">
                <i class="fa fa-lg fa-pencil-square-o"></i>
                <span>作业</span>
            </a>
        </li>
        <li>
            <a href="resource.htm?course_id=<%=courseId%>">
                <i class="fa  fa-fw fa-lg fa-folder-open"></i>
                <span>课程资源</span>
            </a>
        </li>
        <li id="team">
            <a href="toMyGroup.htm?course_id=<%=courseId%>">
                <i class="fa  fa-fw fa-lg fa-group"></i>
                <span>团队</span>
            </a>
        </li>
        <li id="comment">
            <a href="commentList.htm?course_id=<%= courseId%>">
                <i class="fa  fa-fw fa-lg fa-comments"></i>
                <span>留言板</span>
            </a>
        </li>
    </ul>
</div>