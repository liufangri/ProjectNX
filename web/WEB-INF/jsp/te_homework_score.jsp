<%-- 
    Document   : te_homework_score
    Created on : 2016-7-5, 19:58:26
    Author     : tmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%
    String path = request.getContextPath();
%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navbar.jsp">
            <jsp:param name="page" value="usercenter"/>
        </jsp:include>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="clearfix">
                <button class="btn btn-default" onclick="javascript:location.href = 'te_homework_list.htm'"> <span class="glyphicon glyphicon-chevron-left"></span>返回列表</button>
            </div>
            <h1>作业评分</h1>

            <div class="form-group">
                <textarea class="form-control" readonly rows="15">abcd</textarea>
            </div>  
            <div class="clearfix">
                <button class="btn btn-info">附件下载</button>
            </div>
            <mvc:form action="" modelAttribute="user" method="post" cssClass="form">
                <div class="clearfix">
                    <div class="col-md-8 column">
                        <h3>作业评语：</h3>
                    </div>
                    <div class="col-md-4 column">
                        <h4>作业评分：<input type="number" min="1" max="100" /></h4>                    
                    </div>
                </div>
                <div class="clearfix">
                    <textarea class="form-control" rows="9">123123</textarea>
                </div>
                <div class="col-md-4 col-md-push-10 column" style="margin: 5px 0 0 0">
                    <button class="btn btn-default">返回</button>
                    <button class="btn btn-primary">提交</button>
                </div>
            </mvc:form>
        </div>
    </body>
</html>
