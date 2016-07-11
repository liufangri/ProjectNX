<%-- 
    Document   : te_homework_score
    Created on : 2016-7-5, 19:58:26
    Author     : tmc
--%>

<%@page import="com.teamnx.model.Group"%>
<%@page import="com.teamnx.model.Task"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.teamnx.model.Homework"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%
    String path = request.getContextPath();
    String homework_id = (String) request.getAttribute("homework_id");
    String taskId = (String) request.getAttribute("task_id");
    Homework originHomework = (Homework) request.getAttribute("origin_homework");
    Task task = (Task) request.getAttribute("task");
    String courseId = (String) request.getAttribute("course_id");
    Group group = (Group) request.getAttribute("group");
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
                <button class="btn btn-info" onclick="javascript:location.href = 'te_homework_list.htm?task_id=<%=taskId%>'"> <span class="glyphicon glyphicon-chevron-left"></span>返回列表</button>
            </div>
            <h3>评分&评语：</h3>
            <% if (group != null) {%>
            <h3>小组：<%= group.getName()%></h3>
            <%}%>
            <div class="form-group">
                <textarea class="form-control" readonly rows="15"><%= originHomework.getText()%></textarea>
            </div>  
            <%
                if (originHomework.getFilePath() != null && originHomework.getFilePath() != "") {

                    File file = new File(originHomework.getFilePath());
            %>
            <div class="clearfix" >
                <label class="control-label">附件下载</label>
            </div>
            <div class="clearfix" style="margin-bottom:20px"> 
                <button class="btn btn-info" onclick="javascript:location.href = 'download.htm?homeworkId=<%= originHomework.getId()%>'"><%= file.getName()%></button>
            </div>
            <%}%>
            <mvc:form action="setHomeworkScore.htm" modelAttribute="homework" method="post" cssClass="form">
                <div class="clearfix">
                    <div class="col-md-8 column">
                        <h4>作业评语：</h4>
                    </div>
                    <div class="col-md-4 column">
                        <h4>作业评分：<input type="number" name="score" min="0" max="100" required="required" /></h4>                    
                    </div>
                </div>
                <div class="clearfix">
                    <textarea  name="comment" class="form-control" rows="5"></textarea>
                </div>
                <div class="clearfix" style="padding-top: 5px">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
                <input type="text" hidden value="<%=taskId%>" name="task_id">
                <input type="text" hidden value="<%=homework_id%>" name="homework_id">
            </mvc:form>
        </div>
    </body>
</html>
