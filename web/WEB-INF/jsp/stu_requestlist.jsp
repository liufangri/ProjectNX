<%-- 
    Document   : stu_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.Group"%>
<%@page import="com.teamnx.model.StudentGroup"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        ArrayList<StudentGroup> applyLists = (ArrayList<StudentGroup>) request.getAttribute("studentGroups");
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
                <button class="btn btn-info" type="button"
                        onclick="location.href = 'toMyGroup.htm?course_id=${course_id}'">
                    <i class="glyphicon glyphicon-chevron-left" style="margin-right: 3px" ></i>返回列表</button></div>
            <div>
                <h3>申请名单：</h3>
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
                            for (StudentGroup sg : applyLists) {
                        %>
                        <tr>
                            <td><%=sg.getStudentId()%></td>
                            <td><%=sg.getStudentName()%></td>   
                            <td>
                                <a href="ifJoin.htm?studentGroup_id=<%=sg.getId()%>&course_id=${course_id}&status=1"><button type="button" class="btn"><span class="glyphicon glyphicon-ok-sign"></span> 同意</button></a>
                                <a href="ifJoin.htm?studentGroup_id=<%=sg.getId()%>&course_id=${course_id}&status=0"><button type="button" class="btn"><span class="glyphicon glyphicon-ok-sign"></span> 拒绝</button></a>
                                <input name="studentGroup_id" hidden="hidden" value="<%=sg.getId()%>"/>
                                <input name="course_id" hidden="hidden" value="${course_id}"/>
                                <input name="status" hidden="hidden" value="0"/>
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
