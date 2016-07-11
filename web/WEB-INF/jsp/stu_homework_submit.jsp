<%-- 
    Document   : stu_homework_submit
    Created on : 2016-7-5, 11:29:16
    Author     : coco
--%>

<%@page import="com.teamnx.model.Group"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.teamnx.model.Homework"%>
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
    Homework origin_homework = (Homework) request.getAttribute("origin_homework");
    Homework homework = (Homework) request.getAttribute("homework");
    Timestamp currentTimestamp = new Timestamp(new Date().getTime());
    Course course = (Course) request.getAttribute("course");
    Group group = (Group) request.getAttribute("group");
    if (group == null) {
        group = new Group();
    }
    boolean inGroup = (Boolean) request.getAttribute("in_group");
    boolean courseHide = course.isCategory() && !inGroup;
    boolean hide = courseHide || currentTimestamp.before(task.getStartTime()) || currentTimestamp.after(task.getDeadline());
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
                    showUpload: false,
                });
            });
            function checkNull() {
                var errorMessage = document.getElementById("errorMessage");
                var formGroup = document.getElementById("text_homework_div_id");
                if (<%= task.isText()%>) {
                    if ($.trim($("#home_input_text").val()) === "") {
                        errorMessage.style.visibility = "visible";
                        formGroup.className = "form-group has-error";
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        </script>
    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>


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
                        <input type="button" class="btn btn-default" value="返回列表" onclick="javascript:location.href = 'stu_homework.htm?course_id=<%= task.getCourseId()%>'">
                    </div>
                    <div style="padding-left: 12px;display:table-cell">
                        <h1><%= task.getName()%></h1>
                    </div>

                </div>
                <div>
                    <h1>作业描述</h1>   
                </div>

                <div class="form-group">

                    <% if (inGroup && origin_homework != null && origin_homework.getStudentName() != null && !origin_homework.getStudentName().equals("")) {%>
                    <label>上次提交者：</label>
                    <label class="text-info" style="margin-right: 20px"><%= homework.getStudentName()%></label>

                    <label>您的队伍：</label><label class="text-info">${group.name}</label>
                    <br/>
                    <%}%>
                    <label class="text-danger" for="name"<%if (!hide) {%>hidden="hidden"<%}%>><% if (!courseHide) { %>不在提交时间范围内<%} else {%>您还没有加入队伍，或者队伍还没有通过审核，无法提交作业<%}%></label>
                </div> 
                <div class="form-group">
                    <textarea rows="8" class="form-control" readonly ><%= task.getDescription()%></textarea>
                </div>  

                <div class="form-group" id="text_homework_div_id">
                    <label for="name">文本作业</label>  
                    <textarea id="home_input_text" class="form-control" rows="5" name="text" <%if (hide) {%>disabled="disabled"<%};%>>${homework.text}</textarea>
                    <label class="form-control" style="visibility: hidden" id="errorMessage">提交作业不能为空</label>
                </div> 

                <%if (!task.isText()) {
                %>
                <div> 
                    <label class="control-label">提交附件</label>
                    <input id="upload" type="file"  class="file-loading" name="uploadFile" <%if (hide) {%>disabled="disabled"<%}%> >
                </div>
                <%
                    }
                %>
                <br/>
                <input type="text" hidden="hidden" name="origin_homework_id" value="${origin_homework.id}"/>
                <input type="text" hidden="hidden" name="courseId" value="<%=courseId%>"/>
                <input type="text" hidden="hidden" name="taskId" value="<%=task.getId()%>"/>
                <input type="text" hidden="hidden" name="studentId" value="<%=user.getId()%>"/>
                <input type="text" hidden="hidden" name="studentName"value="<%=user.getName()%>"/>
                <input type="text" hidden="hidden" name="groupId" value="${group.id}">
                <% if (origin_homework.getFilePath() != null) {
                        File file = new File(origin_homework.getFilePath());
                %>
                <div class="clearfix" >
                    <label class="control-label">附件下载</label>
                </div>
                <div class="clearfix" style="margin-bottom:20px">
                    <a href="download.htm?homeworkId=<%= origin_homework.getId()%>"><button type="button" class="btn btn-info" > <%= file.getName()%></button></a>
                </div>
                <%}%>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" 
                           <%if (hide) {%>disabled="disabled"<%}%>
                           onclick="return checkNull();" value="提交">
                    <button type="button" class="btn btn-primary" onclick="javascript:location.href = 'stu_homework.htm?course_id=<%= task.getCourseId()%>'">
                        返回
                    </button>
                </div> 
            </mvc:form>
        </div>
    </body>
</html>