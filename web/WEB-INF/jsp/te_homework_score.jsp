<%-- 
    Document   : te_homework_score
    Created on : 2016-7-5, 19:58:26
    Author     : tmc
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.teamnx.model.Homework"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%
    String path = request.getContextPath();
    String homework_id = (String) request.getAttribute("homework_id");
    String taskId = (String) request.getAttribute("task_id");
    Homework originHomework = (Homework) request.getAttribute("origin_homework");
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
                <button class="btn btn-default" onclick="javascript:location.href = 'te_homework_list.htm?taskId=<%=taskId%>'"> <span class="glyphicon glyphicon-chevron-left"></span>返回列表</button>
            </div>
            <h1>作业评分</h1>

            <div class="form-group">
                <textarea class="form-control" readonly rows="15">abcd</textarea>
            </div>  
            <%
                if (originHomework.getFilePath() != null && originHomework.getFilePath() != "") {

                    File file = new File(originHomework.getFilePath());
            %>
            <div class="clearfix">
                <lable style="margin-right: 20px"><%= file.getName()%></lable><button class="btn btn-info" onclick="javascript:location.href = 'download.htm?homeworkId=<%= originHomework.getId()%>'">附件下载</button>
            </div>
            <%}%>
            <mvc:form action="setHomeworkScore.htm" modelAttribute="homework" method="post" cssClass="form">
                <div class="clearfix">
                    <div class="col-md-8 column">
                        <h3>作业评语：</h3>
                    </div>
                    <div class="col-md-4 column">
                        <h4>作业评分：<input type="number" name="score" min="1" max="100" /></h4>                    
                    </div>
                </div>
                <div class="clearfix">
                    <input type="textarea" name="comment" class="form-control">
                </div>
                <div class="col-md-4 col-md-push-10 column" style="margin: 5px 0 0 0">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div><input type="text" hidden="true" value="<%=taskId%>" name="task_id">
                <input type="text" hidden="true" value="<%=homework_id%>" name="homework_id">
            </mvc:form>
            <button onclick="javascript:location.href = 'te_homework_list.htm?homeworkId=<%=homework_id%>'" class="btn btn-default">返回</button>
        </div>
    </body>
</html>
