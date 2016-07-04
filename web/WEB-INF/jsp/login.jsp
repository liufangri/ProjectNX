<%-- 
    Document   : login
    Created on : 2016-7-4, 10:39:44
    Author     : coco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body style="background-color: yellow">
        <div class="login">
            <div class="container">
                <div class="clearfix"><a class="logo" href="/opencourse/" ></a></div>
                <div class="login-box" >
                    <div class="l"><img src="<%=path%>/images/image-16.png"/></div>
                    <div class="r" style=" background-color: white" >
                        <div class="login-tab">
                            <div class="hd">
                                <ul><li class="on">统一认证登录</li></ul>
                            </div>
                            <div class="bd">
                                <div class="box-1">
                                    <dl style="margin-top: 40px;">
                                        <dt><i class="fa fa-user"></i>账号</dt>
                                        <dd><input id="eid" name="eid" type="text" class="input-text" /></dd>
                                    </dl>
                                    <dl style="margin-top: 40px;">
                                        <dt><i class="fa fa-lock"></i>密码</dt>
                                        <dd><input id="pw" name="pw" type="password" class="input-text" /></dd>
                                    </dl>
                                    <div class="submit-box clearfix">
                                        <input id="type" type="hidden" value="ldap"/>
                                        <button id="submitButton" type="button" onclick="doLogin()">登 录</button>
                                        <img id="processing" style="display: none;" src="<%=path%>/images/processing.gif" />
                                        <a id="errorMessage" style="color:red;display: none"></a>
                                    </div>
                                    <p style="padding-top: 5px;color:#ffa88f">注：请使用统一认证的账号密码登录，登录后在首页菜单栏点击“后台”即可进入后台系统</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>   
        </div>
        <script type="text/javascript">
            function doLogin() {
                var eid = $("#eid").val();
                var pw = $("#pw").val();
                var type = $("#type").val();
                if (eid.length < 1 || pw.length < 1) {
                    $("#errorMessage").text("请输入账号或密码！");
                    $("#errorMessage").show();
                    return;
                }
                $("#processing").show();
                $("#errorMessage").hide();
                $("#submitButton").attr("disabled", "disabled");
                $.ajax({
                    url: "/opencourse/login",
                    type: "post",
                    dataType: "json",
                    data: {eid: eid, pw: pw, type: type},
                    success: function (response) {
                        //console.log(response);
                        if (response.isSuccess) {
                            location.href = "/opencourse/";
                        } else {
                            $("#processing").hide();
                            $("#errorMessage").text("账号或密码错误！");
                            $("#errorMessage").show();
                            $("#submitButton").removeAttr("disabled");
                        }
                    }
                });
            }

            $(function () {
                //回车提交事件
                $("input").keydown(function () {
                    //keyCode=13是回车键
                    if (event.keyCode == "13") {
                        doLogin();
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
