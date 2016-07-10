<%-- 
    Document   : stu_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teamnx.model.ShowHomework"%>
<%@page import="com.teamnx.model.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        ArrayList<Task> tasks = (ArrayList<Task>) request.getAttribute("tasks");
        ArrayList<ShowHomework> showHomeworks = (ArrayList<ShowHomework>) request.getAttribute("show_homeworks");
        String courseId = (String) request.getAttribute("course_id");
        boolean inGroup = (Boolean) request.getAttribute("in_group");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
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
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>作业名称</th>
                            <th>起始时间</th>  
                            <th>截止时间</th>  
                            <th>作业状态（是否提交、上传时间）</th>  
                            <th>作业评分</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (ShowHomework sh : showHomeworks) {
                        %>
                        <tr>
                            <td><a href="stu_homework_submit.htm?task_id=<%= sh.getTaskId()%>"><%= sh.getTaskName()%></a></td>
                            <td><%= sdf.format(new Date(Timestamp.valueOf(sh.getStartTime()).getTime()))%></td>   
                            <td><%= sdf.format(new Date(Timestamp.valueOf(sh.getDeadLine()).getTime()))%></td>  
                            <td><% if (sh.getState()) { %>
                                已提交
                                <%  } else {%>
                                未提交<%}%>
                            </td>
                            <td>
                                <% if (sh.getScore() == -1) {%>
                                <label class="primary">暂时没有评分</label>
                                <%} else {%>
                            <a href="stu_homework_score.htm?homework_id=<%= sh.getHomeworkId()%>"><%= sh.getScore()%></a></td> 
                                <%}%>
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
