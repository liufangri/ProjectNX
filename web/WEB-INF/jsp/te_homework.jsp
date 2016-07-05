<%-- 
    Document   : te_homework
    Created on : 2016-7-4, 20:55:52
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
            <div class="col-md-offset-10 ">
                <button class="btn btn-default" data-toggle="modal" 
                        data-target="#myModal">发布新作业</button>  
            </div>
            <div class="modal fade" id="myModal" data-backdrop="false" data-keyboard="false" tabindex="-1" role="dialog" 
                 aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <!--在这里写form-->
                    <mvc:form action="addTask.htm" modelAttribute="task" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" 
                                    data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                发布新作业
                            </h4>
                        </div>
                        <div style="margin-bottom: 0px" class="well">

                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label" for="reservationtime">Choose your check-in and check-out times:</label>
                                    <div class="controls">
                                        <div class="input-prepend input-group">
                                            <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                            <input type="text" style="width: 400px"  name="timeLimit" id="reservationtime" class="form-control span4" value="08/01/2013 1:00 PM - 08/01/2013 1:30 PM"/>

                                        </div>
                                    </div>
                                </div>
                            </fieldset>
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
                        <div class="modal-body">

                            <form role="form">
                                <form role="form">
                                    <div class="form-group">
                                        <label for="name">作业名称</label>
                                        <input type="text" class="form-control" placeholder="文本输入">
                                    </div>

                                    <div class="form-group">
                                        <label for="name">作业介绍</label>
                                        <textarea class="form-control" rows="9"></textarea>
                                    </div>
                                </form>

                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox">
                            </span>
                            <label class="form-control" for="name">是否包括附件</label>
                        </div><!-- /input-group -->

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" 
                                    data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-primary">
                                提交更改
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                    <!--关闭form-->
                </mvc:form>
                </div><!-- /.modal -->
            </div>
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>作业名称</th>
                            <th>起始时间</th>  
                            <th>截止时间</th>  
                            <th>发布人</th>  
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 1; i < 200; i++) {
                        %>
                        <tr>
                            <td>加法题</td>
                            <td>2016/08/15</td>   
                            <td>2016/09/11</td>  
                            <td>塔利班</td>  
                            <td><a href="te_homework_list.htm">已提交名单</a></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>  
        </div>
    </body>
</html>
