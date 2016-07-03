<%-- 
    Document   : index
    Created on : 2016-7-3, 12:21:47
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
    <script type="text/javascript">
        
    </script>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
               <jsp:include page="navheader.jsp">
               <jsp:param name="type" value="index"/>
               </jsp:include>>
            </div>
        </div>
    </body>
</html>