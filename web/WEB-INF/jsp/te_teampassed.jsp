<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page import="com.teamnx.model.Group"%>
<%@page import="com.teamnx.model.StudentGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        ArrayList<StudentGroup> studentGroups = (ArrayList<StudentGroup>) request.getAttribute("studentGroups");
        Group group = (Group) request.getAttribute("group");
        String managerId = group.getManagerId();
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
                <button onClick="location.href='teacherGroupList.htm?course_id=${course_id}'" type="button" class="btn btn-info">
                    <span class="glyphicon glyphicon-chevron-left"></span>返回</button>
            </div>
            <div>
                <h3>团队名称：<b><%=group.getName()%></b>  状态：<span class="glyphicon glyphicon-user"></span><i style="color: red">已通过</i></h3>
            </div>
            <div>                
                <button onClick="location.href='addMember.htm?course_id=${course_id}&group_id=<%=group.getId()%>'" type="button" class="btn btn-default">添加成员</button>
                <button onClick="location.href='dissolution.htm?course_id=${course_id}&group_id=<%=group.getId()%>'" type="button" class="btn btn-default">解散团队</button>
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
                                        <li><a href="removeMember.htm?course_id=${course_id}&sg_id=<%=sg.getId()%>">移除</a></li>
                                        <li><a href="setManager.htm?course_id=${course_id}&group_id=<%=sg.getGroupId()%>&student_id=<%=sg.getStudentId()%>">设为负责人</a></li>
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
            
        </div>
    </body>
</html>