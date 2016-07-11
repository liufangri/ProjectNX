<%-- 
    Document   : index
    Created on : 2016-7-3, 12:21:47
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>


    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="index"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h3 class="page-header">${course.name}</h3>   
            <h1 class="page-header">${course.position}</h3>
            <h3 class="page-header">${course.schedule}</h3>    
            <h3 class="page-header">${course.teachers}</h3>    

        </div>

    </body>
</html>