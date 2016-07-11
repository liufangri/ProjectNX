<%-- 
    Document   : stu_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page import="com.teamnx.model.StudentGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.Group"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        Group group = (Group) request.getAttribute("group");
        String name = group.getName();
        String status = (String) request.getAttribute("status");
        String inGroup = (String) request.getAttribute("in_group");
        String managerId = group.getManagerId();
        ArrayList<StudentGroup> studentGroups = (ArrayList<StudentGroup>) request.getAttribute("studentGroups");
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="team"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div>
                <h3>团队名称：<b><%=group.getName()%></b>  状态：<span class="glyphicon glyphicon-user"></span><i style="color: red">${status}</i></h3>
            </div>

            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>  
                            <th>职务</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (StudentGroup sg : studentGroups) {
                        %>
                        <tr>
                            <td><%=sg.getStudentId()%></td>
                            <td><%=sg.getStudentName()%></td>   
                            <td><%if (sg.getStudentId().equals(managerId)) {%>
                                负责人
                                <%} else {%>
                                组员
                                <%}%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div>
                <button onClick="location.href = 'studentGroupList.htm?course_id=${course_id}'" type="button" class="btn btn-default">返回</button>
            </div>
        </div>
    </body>
</html>
