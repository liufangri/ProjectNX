<%-- 
    Document   : stu_homework_submit
    Created on : 2016-7-5, 11:29:16
    Author     : coco
--%>

<%@page import="com.teamnx.model.Task"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    Task task = (Task) request.getAttribute("task");
%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navbar.jsp">
            <jsp:param name="page" value="usercenter"/>
        </jsp:include>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div>
                <p>作业题目：<b>${task.name}</b></p>   
            </div>
            <div>
                <p>作业开始时间：<%= task.getStartTime().toString()%></p>   
            </div>
            <div>
                <p>作业结束时间：<%= task.getDeadline().toString()%></p>   
            </div>
            <!--            <div>
                            <p>批改老师：<b></b></p>   
                        </div>           -->
            <div>
                <p>作业评分：<b>${homework.score}</b></p>   
            </div>
            <div class="form-group">
                <label>教师评语:</label>
                <textarea class="form-control" readonly rows="9">${homework.comment}</textarea>
            </div>  
            <div class="col-md-4 col-md-push-11 column" style="margin: 5px 0 0 0">
                <button class="btn btn-default" onclick="javascript:location.href = 'stu_homework.htm?course_id=${task.courseId}'">返回</button>
            </div>
        </div>
    </body>
</html>