<%-- 
    Document   : index
    Created on : 2016-7-3, 12:21:47
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <h1 class="page-header">${course.name}</h1>   
            <h1 class="page-header">${course.position}</h1>
            <h1 class="page-header">${course.schedule}</h1>    
            <h1 class="page-header">${course.teachers}</h1>    
        </div>
    </body>
</html>