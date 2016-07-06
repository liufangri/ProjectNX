<%-- 
    Document   : stu_homework_submit
    Created on : 2016-7-5, 11:29:16
    Author     : coco
--%>

<%@page import="com.teamnx.model.User"%>
<%@page import="com.teamnx.model.Task"%>
<%@page import="com.teamnx.model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%
    String path = request.getContextPath();
    String courseId = (String) request.getAttribute("course_id");
    Task task = (Task) request.getAttribute("task");
    User user = (User) session.getAttribute("user");
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
            <mvc:form action="submitHomework.htm" modelAttribute="homework" method="post" cssClass="form" enctype="multipart/form-data">
                <div style="display:table">
                    <div style="display:table-cell">
                        <input type="button" class="btn btn-default" value="返回列表" onclick="javascript:location.href = 'stu_homework.htm?id=<%= task.getCourseId()%>'">
                    </div>
                    <div style="padding-left: 12px;display:table-cell">
                        <h1><%= task.getName()%></h1>
                    </div>

                </div>
                <div>
                    <h1>作业描述</h1>   
                </div>
                <div class="form-group">
                    <textarea  class="form-control" readonly rows="12" ><%= task.getDescription()%></textarea>
                </div>  

                <div class="form-group">
                    <label for="name">文本作业</label>
                    <input type="textarea" class="form-control" rows="5" name="text"/>
                </div> 
                <div> 
                    <label class="control-label">提交附件</label>
                    <input id="upload" type="file"  class="file-loading" name="uploadFile">
                </div>
                <br/>
                <input type="text" hidden="true" name="courseId" value="<%=courseId%>"/>
                <input type="text" hidden="true" name="taskId" value="<%=task.getId()%>"/>
                <input type="text" hidden="true" name="studentId" value="<%=user.getId()%>"/>
                <input type="text"hidden="true"name="student_name"value="<%=user.getName()%>"/>

                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="提交">
                    <button type="button" class="btn btn-primary" onclick="javascript:location.href = 'stu_homework.htm?id=<%= task.getCourseId()%>'">
                        返回
                    </button>
                </div> 


            </mvc:form>
        </div>
    </body>
</html>