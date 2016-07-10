<%-- 
    Document   : admin
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
        <script type="text/javascript" src="<%=path%>/lib/js/moment.js"></script>
        <script type="text/javascript" src="<%=path%>/lib/js/daterangepicker.js"></script>
        <title>课程中心 - 登录</title>
    </head>
    <body>
        <div style="background-image:url('<%=path%>/images/login_background.jpg')" class="bg"></div>
        <div class="login">
            <div class="container">
                <div class="login-box" >
                    <div class="login-tab">
                        <div class="hd">
                            <ul><li class="on">设置学期周次</li></ul>
                        </div>
                        <div class="bd">
                            <div class="box-1">
                                <mvc:form action="setSemester.htm" method="post" cssClass="form" onsubmit="return check()">
                                    <div style="margin-top:40px">
                                        <span  class="glyphicon glyphicon-user"></span>本学期是
                                        <div class="row clearfix">
                                            <div class="col-md-6 column">
                                                <div id="DropDownList" class="dropdown-menu">
                                                    <li><input type="text" name="year" value="2011"></li>
                                                    <li><input type="text" name="year" value="2012"></li>
                                                    <li><input type="text" name="year" value="2013"></li>
                                                    <li><input type="text" name="year" value="2014"></li>
                                                    <li><input type="text" name="year" value="2015"></li>
                                                    <li><input type="text" name="year" value="2016"></li>
                                                    <li><input type="text" name="year" value="2017"></li>
                                                    <li><input type="text" name="year" value="2018"></li>
                                                    <li><input type="text" name="year" value="2019"></li>
                                                </div>年
                                            </div>
                                            <div class="col-md-6 column ">
                                                <input type="radio" name="semester" value="春季学期">
                                                <input type="radio" name="semester" value="夏季学期">
                                                <input type="radio" name="semester" value="秋季学期">
                                            </div>
                                        </div>

                                    </div>
                                </div>   
                                <div style="margin-top:40px">
                                    <span  class="glyphicon glyphicon-user"></span>本学期开始时间为

                                    <fieldset>
                                        <div class="control-group">
                                            <div class="controls">
                                                <div class="input-prepend input-group" >
                                                    <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                                    <input type="text"   name="timeLimit" id="reservationtime" class="form-control span4" value="2016-07-01"/>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                    <a href="stu_index.jsp"></a>
                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            $('#reservationtime').daterangepicker({
                                                "singleDatePicker": true,
                                                format: 'YYYY-MM-DD'
                                            }, function (start, end, label) {
                                                console.log(start.toISOString(), end.toISOString(), label);
                                            });
                                        });
                                    </script>

                                </div>
                                <div class="submit-box clearfix">
                                    <input id="type" type="hidden" value="ldap"/>
                                    <button id="submitButton" type="submit">确 定</button>
                                    <img id="processing" style="display: none;" src="<%=path%>/images/processing.gif" />
                                    <a id="errorMessage" style="color:red">${error_message}</a>
                                </div>
                            </mvc:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
