<%-- 
    Document   : te_homework_submit
    Created on : 2016-7-5, 20:37:13
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="all" href="<%=path%>/lib/css/daterangepicker-bs3.css" />
        <script type="text/javascript" src="<%=path%>/lib/js/moment.js"></script>
        <script type="text/javascript" src="<%=path%>/lib/js/daterangepicker.js"></script>
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
                        <input type="button" class="btn btn-default" value="返回列表" onclick="javascript:location.href = 'te_homework.htm'">
                    </div>
                </div>
                <mvc:form action="" modelAttribute="user" method="post" cssClass="form">
                    <div class="row clearfix">
                        <div class="col-md-10 column">
                            <div class="form-group">
                                <label for="name">作业名称</label>
                                <input type="text" class="form-control" placeholder="文本输入">
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-10 column">
                            <div class="form-group">
                                <label for="name">作业介绍</label>
                                <textarea class="form-control" rows="9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-10 column">
                            <div style="margin-bottom: 0px" class="well">
                                <form class="form-horizontal">
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="reservationtime">选择起止时间</label>
                                            <div class="controls">
                                                <div class="input-prepend input-group">
                                                    <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                                    <input type="text" style="width: 400px"  name="reservation" id="reservationtime" class="form-control span4" value="08/01/2013 1:00 PM - 08/01/2013 1:30 PM"/>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        $('#reservationtime').daterangepicker({
                                            timePicker: true,
                                            timePickerIncrement: 30,
                                            format: 'MM/DD/YYYY h:mm A'
                                        }, function (start, end, label) {
                                            console.log(start.toISOString(), end.toISOString(), label);
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-10 column">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox">
                                </span>
                                <label class="form-control" for="name">是否包括附件</label>
                            </div> 
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-6 column">
                        </div>
                        <div class="col-md-2 column">

                        </div>
                        <div class="col-md-2 column">
                            <button type="button" class="btn btn-primary">
                                提交
                            </button>
                        </div>
                    </div>
                </mvc:form>
            </div>
        </div>
    </body>
</html>