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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2" style="padding-left: 40px; padding-top: 20px">
            <div>
                <div>
                    <button class="btn btn-info" type="button" data-target="#myModal5" data-toggle="modal"
                            onclick="javascript:location.href = 'te_homework.htm?course_id=<%=courseId%>'">
                        <i class="glyphicon glyphicon-chevron-left" style="margin-right: 3px" ></i>返回列表</button>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2" style="padding-left: 40px">
            <h2><span class="fa-border" style="border-radius: 5px "><%= task.getName()%></span></h2>
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
                            <% if (h.getScore() == -1) {%>

                            <td>未批改</td>  
                            <%} else {%>
                            <td><%= h.getScore()%></td>
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