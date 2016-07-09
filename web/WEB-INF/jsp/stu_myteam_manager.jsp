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
                <h3>团队名称：<b><%=name%></b>  状态：<span class="glyphicon glyphicon-user"></span><i style="color: red">${status}</i></h3>
            </div>
            <div>
                <button type="button" class="btn btn-default">完成组建</button>
                <a href="applyList.htm?group_id=<%=group.getId()%>&course_id=${course_id}"><button type="submit" class="btn btn-default">查看申请</button></a>
                <button type="button" class="btn btn-default">解散团队</button>
            </div>
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>  
                            <th>职务</th>   
                            <th>操作</th>
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
                            <td>
                                <div  class="btn-group" >
                                    <%if (!sg.getStudentId().equals(managerId)) {%>
                                    <button type="button" class="btn dropdown-toggle " 
                                            data-toggle="dropdown">
                                        选择操作 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">移除</a></li>
                                        <li><a href="#">设为负责人</a></li>
                                    </ul>
                                    <%}%>
                                </div>
                            </td> 
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div>
                <button type="button" class="btn btn-default">返回</button>
            </div>
        </div>
    </body>
</html>
