<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="studentlist"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="sub-header">学生名单</h1>
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
                            for (int i = 1; i < 200; i++) {
                        %>
                        <tr>
                            <td>1,001</td>
                            <td>Lorem</td>   
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