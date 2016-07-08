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
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li id="index"><a href="index.htm?course_id=<%=courseId%>">课程信息 <span class="sr-only">(current)</span></a></li>
        <li id="homework"><a href="stu_homework.htm?course_id=<%=courseId%>">作业</a></li>
        <li><a href="#">课程资源</a></li>
        
        <li><a href="#">团队</a></li>
        <li><a href="#">留言板</a></li>
    </ul>
</div>