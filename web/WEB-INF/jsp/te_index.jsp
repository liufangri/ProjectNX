<%-- 
    Document   : tercherindex
    Created on : 2016-7-4, 20:32:53
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
                    <jsp:param name="type" value="index"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">课程名字</h1>   
            <h1 class="page-header">上课地点</h1>     
            <h1 class="page-header">课程时间</h1>    
            <h1 class="page-header">任课老师</h1>    
        </div>
    </body>
</html>