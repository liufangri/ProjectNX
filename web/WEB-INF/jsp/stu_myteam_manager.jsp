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
                <% switch (group.getStatus()) {
                        case 0:%>
                <a href="finishForming.htm?course_id=${course_id}&group_id=<%=group.getId()%>"><button type="button" class="btn btn-default">完成组建</button></a>
                <% break;
                    case 1:%>
                <a href="cancelForming.htm?course_id=${course_id}&group_id=<%=group.getId()%>"><button type="button" class="btn btn-default">取消申请</button></a>
                <% break;
                    case 2:%>
                <button type="button" disabled class="btn btn-default">审核通过</button>
                <%
                    }
                %>
                <a href="applyList.htm?group_id=<%=group.getId()%>&course_id=${course_id}"><button type="submit" class="btn btn-default">查看申请</button></a>
                <a href="dissolution.htm?group_id=<%=group.getId()%>&course_id=${course_id}"><button type="button" class="btn btn-default">解散团队</button></a>
                <a href="studentGroupList.htm?course_id=${course_id}"><button type="button" class="btn btn-info">团队列表</button></a>
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
                                    <button class="btn btn-danger" onclick="location.href='removeMember.htm?course_id=${course_id}&sg_id=<%=sg.getId()%>'">移除</button>
                                    <button class="btn btn-success" onclick="location.href='setManager.htm?course_id=${course_id}&group_id=<%=sg.getGroupId()%>&student_id=<%=sg.getStudentId()%>'">设为负责人</button>                                    
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
        </div>
    </body>
</html>
