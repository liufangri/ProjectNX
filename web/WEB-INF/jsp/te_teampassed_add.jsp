<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page import="com.teamnx.model.Course"%>
<%@page import="com.teamnx.model.User"%>
<%@page import="com.teamnx.model.Group"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        Group group = (Group) request.getAttribute("group");
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        Course course = (Course) request.getAttribute("course");
        String managerName = (String) request.getAttribute("manager_name");
    %>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="team"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div>
                <button onClick="location.href='passedGroupInfo.htm?course_id=${course_id}&group_id=<%=group.getId()%>'" type="button" class="btn btn-info">
                    <span class="glyphicon glyphicon-chevron-left"></span>返回</button>
            </div>
            <div>
                <h3>团队名称：<b class="fa-border"><%=group.getName()%></b> <b ></b>负责人： ${managerName}</h3>
            </div>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (User u : users) {
                        %>
                        <tr>
                            <td><%=u.getId()%></td>
                            <td><%=u.getName()%></td>
                            <td><button onClick="location.href='addIntoGroup.htm?course_id=${course_id}&group_id=<%=group.getId()%>&student_id=<%=u.getId()%>'" type="button" class="btn btn-success" onclick="">移进团队</button></td>
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