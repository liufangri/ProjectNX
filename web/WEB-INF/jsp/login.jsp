<%-- 
    Document   : login
    Created on : 2016-7-4, 10:39:44
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
    <head>
        <link rel="stylesheet" href="<%=path%>/lib/css/style.css" />
        <title>课程中心 - 登录</title>
    </head>
    <body>
        <div style="background-image:url('<%=path%>/images/login_background.jpg')" class="bg"></div>
        <div class="login">
            <div class="container">
                <div class="login-box" >
                        <div class="login-tab">
                            <div class="hd">
                                <ul><li class="on">课程中心登录</li></ul>
                            </div>
                            <div class="bd">
                                <div class="box-1">
                                    <mvc:form action="loginAction.htm" modelAttribute="user" method="post" cssClass="form" onsubmit="return check()">
                                        <dl style="margin-top: 40px;">
                                            <dt><span class="glyphicon glyphicon-user"></span>账号</dt>
                                            <dd><input id="eid" name="id" type="text" class="input-text" /></dd>
                                        </dl>
                                        <dl style="margin-top: 40px;">
                                            <dt><span class="glyphicon glyphicon-lock"></span>密码</dt>
                                            <dd><input id="pw" name="password" type="password" class="input-text" /></dd>
                                        </dl>
                                        <div class="submit-box clearfix">
                                            <input id="type" type="hidden" value="ldap"/>
                                            <button id="submitButton" type="submit">登 录</button>
                                            <img id="processing" style="display: none;" src="<%=path%>/images/processing.gif" />
                                            <a id="errorMessage" style="color:red">${error_message}</a>
                                        </div>
                                    </mvc:form>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function check() {
                var eid = $("#eid").val();
                var pw = $("#pw").val();
                if (eid.length < 1 || pw.length < 1) {
                    $("#errorMessage").text("请输入账号或密码！");
                    $("#errorMessage").show();
                    return false;
                }
                $("#processing").show();
                $("#errorMessage").hide();
                $("#submitButton").attr("disabled", "disabled");
                return true;
            }

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
