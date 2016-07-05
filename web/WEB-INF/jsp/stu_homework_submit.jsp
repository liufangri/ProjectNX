<%-- 
    Document   : stu_homework_submit
    Created on : 2016-7-5, 11:29:16
    Author     : coco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%
    String path = request.getContextPath();
%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
    <head>
        <link href="<%=path%>/lib/css/fileinput.min.css" rel="stylesheet">
        <script src="<%=path%>/lib/js/fileinput.min.js"></script>
        <script src="<%=path%>/lib/js/fileinput-zh.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#upload").fileinput({
                    language: "zh",
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div style="display:table">
                <div style="display:table-cell">
                    <input type="button" class="btn btn-default" value="返回列表" onclick="javascript:location.href = 'stu_homework.htm'">
                </div>
                <div style="padding-left: 12px;display:table-cell">
                    <h1>作业名</h1>
                </div>
            </div>
            <div>
                <h1>作业说明</h1>   
            </div>
            <div class="form-group">
                <textarea class="form-control" readonly rows="15">abcd</textarea>
            </div>  
            <mvc:form action="" modelAttribute="user" method="post" cssClass="form">
                <div class="form-group">
                    <label for="name">文本作业</label>
                    <textarea class="form-control" rows="5"></textarea>
                </div> 
                <div> 
                    <label class="control-label">提交附件</label>
                    <input id="upload" type="file"  class="file-loading">
                </div>
            </mvc:form>
        </div>
    </body>
</html>