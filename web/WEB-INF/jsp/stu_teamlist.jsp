<%-- 
    Document   : stu_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.ShowGroup"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        ArrayList<ShowGroup> showGroups = (ArrayList<ShowGroup>)request.getAttribute("groups");
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
                <a href="toMyGroup.htm?course_id=${course_id}"><button type="button" class="btn btn-default">我的团队</button></a>
            </div>
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>队名</th>
                            <th>负责人</th>  
                            <th>人数</th>  
                            <th>状态</th>  
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (ShowGroup sg: showGroups) {
                        %>
                        <tr>
                            <td><a href="studentGroupInfo.htm?course_id=${course_id}&group_id=<%=sg.getGroupId()%>"><%=sg.getName()%></a></td>
                            <td><%=sg.getManager()%></td>   
                            <td><%=sg.getNumber()%>/${max_number}</td>  
                            <td><%=sg.getStatus()%></td>
                            <td><%if(sg.getStatus().equals("组建中")){%><mvc:form action="groupApply.htm" method="post"><button type="submit" class="btn btn-default">申请加入</button>
                                    <input name="group_id" hidden="hidden" value="<%=sg.getGroupId()%>">
                                    <input name="course_id" hidden="hidden" value="${course_id}">
                                </mvc:form><%}%></td> 
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
