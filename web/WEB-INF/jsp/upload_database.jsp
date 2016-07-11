<%-- 
    Document   : upload_database
    Created on : 2016-7-10, 11:05:40
    Author     : coco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
    %>

    <jsp:include page="header.jsp"/>
    <jsp:include page="navbar.jsp"/>
    <head>
        <link rel="stylesheet" href="<%=path%>/lib/css/style.css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=path%>/lib/css/daterangepicker-bs3.css" />
        <link href="<%=path%>/lib/css/fileinput.min.css" rel="stylesheet">
        <script type="text/javascript" src="<%=path%>/lib/js/moment.js"></script>
        <script type="text/javascript" src="<%=path%>/lib/js/daterangepicker.js"></script>

        <script src="<%=path%>/lib/js/fileinput.min.js"></script>
        <script src="<%=path%>/lib/js/fileinput-zh.min.js"></script>
        <title>数据库上传</title>
        <script>
            $(document).ready(function () {
                $("#uploaddept").fileinput({
                    language: "zh",
                    showUpload: false,
                    showPreview: false,
                    fileActionSettings: {showZoom: false},
                });
                $("#uploaduser").fileinput({
                    language: "zh",
                    showUpload: false,
                    showPreview: false,
                    fileActionSettings: {showZoom: false},
                });
                $("#uploadcourse").fileinput({
                    language: "zh",
                    showUpload: false,
                    showPreview: false,
                    fileActionSettings: {showZoom: false},
                });
                $("#uploadstu_course").fileinput({
                    language: "zh",
                    showUpload: false,
                    showPreview: false,
                    fileActionSettings: {showZoom: false},
                });
                $("#uploadteacher_course").fileinput({
                    language: "zh",
                    showUpload: false,
                    showPreview: false,
                    fileActionSettings: {showZoom: false},
                });


            });
        </script>
    </head>
    <body>
        <div style="background-image:url('<%=path%>/images/login_background.jpg')" class="bg"></div>
        <div class="login">
            <div class="container">
                <div class="login-box" >
                    <div class="login-tab">
                        <div class="bd">
                            <h1 class="text-info">${message}</h1>
                            <mvc:form action="setDataBase.htm"  method="post" cssClass="form" onsubmit="return check()" enctype="multipart/form-data">
                                <div class="hd">
                                    <ul><li class="on">department表</li></ul>
                                </div>
                                <div style="margin-top:40px">
                                    <input id="uploaddept" type="file"  class="file-loading" data-allowed-file-extensions='["csv"]' name="fileDepartment">
                                </div>
                                <div class="hd">
                                    <ul><li class="on">user表</li></ul>
                                </div>
                                <div style="margin-top:40px">
                                    <input id="uploaduser" type="file"  class="file-loading" data-allowed-file-extensions='["csv"]' name="fileUser">
                                </div>
                                <div class="hd">
                                    <ul><li class="on">courese表</li></ul>
                                </div>
                                <div style="margin-top:40px">
                                    <input id="uploadcourse" type="file"  class="file-loading" data-allowed-file-extensions='["csv"]' name="fileCourse">
                                </div>
                                <div class="hd">
                                    <ul><li class="on">student_course表</li></ul>
                                </div>
                                <div style="margin-top:40px">
                                    <input id="uploadstu_course" type="file"  class="file-loading" data-allowed-file-extensions='["csv"]' name="fileStudentCourse">
                                </div>
                                <div class="hd">
                                    <ul><li class="on">teacher_course表</li></ul>
                                </div>
                                <div style="margin-top:40px">
                                    <input id="uploadteacher_course" type="file"  class="file-loading" data-allowed-file-extensions='["csv"]' name="fileTeacherCourse">
                                </div>
                                <div class="submit-box clearfix">
                                    <input id="type" type="hidden" value="ldap"/>
                                    <button id="submitButton" type="submit">提交</button>
                                </mvc:form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">

            $(function () {
                //回车提交事件
                $("input").keydown(function () {
                    //keyCode=13是回车键
                    if (event.keyCode == "13") {
                        $("submitButton").click();
                    }
                });

                if ($(window).height() > 800) {
                    $(".login").css("height", $(window).height());
                }

                $(window).resize(function () {
                    if ($(window).height() > 800) {
                        $(".login").css("height", $(window).height());
                    }
                });
            });
        </script>
    </body>
</html>
