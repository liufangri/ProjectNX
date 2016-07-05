<%-- 
    Document   : te_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page import="org.apache.coyote.RequestGroupInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.Task"%>
<%@page import="com.teamnx.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        User user = (User) session.getAttribute("user");
        ArrayList<Task> tasks = (ArrayList<Task>) request.getAttribute("tasks");
        String courseId = (String) request.getAttribute("course_id");
    %>
    <jsp:include page="header.jsp"/>

    <head>
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="all" href="<%=path%>/lib/css/daterangepicker-bs3.css" />
        <script type="text/javascript" src="<%=path%>/lib/js/moment.js"></script>
        <script type="text/javascript" src="<%=path%>/lib/js/daterangepicker.js"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="col-md-offset-10 ">

                <button class="btn btn-default" onclick="javascript:location.href = 'te_homework_add.htm?id=<%= courseId%>'">发布新作业</button>  

            </div>
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>作业名称</th>
                            <th>起始时间</th>  
                            <th>截止时间</th>  
                            <th>发布人</th>  
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Task t : tasks) {
                        %>
                        <tr>
                            <td><a href="te_task_modify.htm?taskId=<%= t.getId()%>"><%= t.getName()%></a></td>
                            <td><%= t.getStartTime().toString()%></td>   
                            <td><%= t.getDeadline().toString()%></td>  
                            <td><%= t.getTeacherName()%></td>  

                            <td><a href="te_homework_list.htm?taskId=<%= t.getId()%>">已提交名单</a></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>  
        </div>
    </body>
</html>