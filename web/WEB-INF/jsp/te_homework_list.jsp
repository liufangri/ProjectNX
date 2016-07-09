<%-- 
    Document   : te_homework_list
    Created on : 2016-7-4, 22:15:55
    Author     : coco
--%>

<%@page import="com.teamnx.model.Task"%>
<%@page import="com.teamnx.model.Homework"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        String courseId = (String) request.getAttribute("course_id");
        ArrayList<Homework> homeworks = (ArrayList<Homework>) request.getAttribute("homeworks");
        Task task = (Task) request.getAttribute("task");
    %>
    <jsp:include page="header.jsp"/>
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
            <div style="display:table">
                <div style="display:table-cell">
                    <button class="btn btn-default" onclick="javascript:location.href = 'te_homework.htm?course_id=<%=courseId%>'"> <span class="glyphicon glyphicon-chevron-left"></span>返回列表</button>
                </div>
                <div style="padding-left: 12px;display:table-cell">
                    <h1><%= task.getName()%></h1>
                </div>
            </div>

            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>  
                            <th>分数</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Homework h : homeworks) {
                        %>
                        <tr>
                            <td><a href="scoreHomework.htm?homework_id=<%=h.getId()%>"><%=h.getStudentId()%></a></td>
                            <td><%=h.getStudentName()%></td>   
                            <td><%=h.getScore()%></td>  

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