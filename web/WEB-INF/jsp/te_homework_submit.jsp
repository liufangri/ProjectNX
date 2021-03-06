<%-- 
    Document   : te_homework_submit
    Created on : 2016-7-5, 20:37:13
    Author     : coco
--%>
<%@page import="com.teamnx.model.Homework"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teamnx.model.User"%>
<%@page import="com.teamnx.model.Task"%>
<%@page import="com.teamnx.model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        String courseId = (String) request.getAttribute("course_id");
        User user = (User) session.getAttribute("user");
        Task task = (Task) request.getAttribute("task");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="all" href="<%=path%>/lib/css/daterangepicker-bs3.css" />
        <script type="text/javascript" src="<%=path%>/lib/js/moment.js"></script>
        <script type="text/javascript" src="<%=path%>/lib/js/daterangepicker.js"></script>
        <script type="text/javascript">
            function checkNull() {
                if (nx_check($("#homeworkName"), 45)) {
                    return true;
                } else {
                    $("#homeworkName").popover("toggle");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-10 column">
                        <button class="btn btn-info" type="button" data-target="#myModal5" data-toggle="modal"
                                onclick="javascript:location.href = 'te_homework.htm?course_id=<%=courseId%>'">
                            <i class="glyphicon glyphicon-chevron-left" style="margin-right: 3px" ></i>返回列表</button>
                    </div>
                </div>


                <mvc:form action="addTask.htm" modelAttribute="user" method="post" cssClass="form">
                    <div class="row clearfix" style="padding-top: 20px">
                        <div class="col-md-10 column">
                            <div class="form-group">
                                <label for="name" style="padding-bottom: 5px; padding-left: 3px">作业名称</label>
                                <input type="text" id="homeworkName" class="form-control" placeholder="文本输入" name="name" data-container="body" data-toggle="popover"
                                       data-placement="top" data-content="作业名称长度应在1-45之间" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-10 column">
                            <div class="form-group">
                                <label for="name" style="padding-bottom: 5px; padding-left: 3px">作业介绍</label>
                                <textarea class="form-control" name="description" rows="8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-10 column">
                            <div style="margin-bottom: 0px" class="well">
                                <fieldset>
                                    <div class="control-group">
                                        <label class="control-label" for="reservationtime">选择起止时间</label>
                                        <div class="controls">
                                            <div class="input-prepend input-group">
                                                <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                                <input type="text" style="width: 400px"  name="timeLimit" id="reservationtime" class="form-control span4" value="2016-07-01 12:00 - 2016-07-02 00:00"/>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <a href="stu_index.jsp"></a>
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        $('#reservationtime').daterangepicker({
                                            timePicker: true,
                                            timePickerIncrement: 30,
                                            format: 'YYYY-MM-DD HH:mm'
                                        }, function (start, end, label) {
                                            console.log(start.toISOString(), end.toISOString(), label);
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix" style="padding-top: 5px">
                        <div class="col-md-10 column">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" name="check"/>
                                </span>
                                <label class="form-control" for="name" >是否包括附件</label>
                            </div> 
                            <div style="padding-top: 10px"><input type="submit" onclick="return checkNull();" value="提交" class="btn btn-primary" /></div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-6 column">

                        </div>
                        <div class="col-md-2 column">
                        </div>
                        <input type="text" hidden value="<%= courseId%>" name="courseId"/>
                        <input type="text" hidden value="<%= user.getId()%>" name="teacherId"/>
                        <input type="text" hidden value="<%= user.getName()%>" name="teacherName"/>
                        <div class="col-md-2 column">

                        </div>
                    </div>
                </mvc:form>
            </div>
        </div>
    </body>
</html>