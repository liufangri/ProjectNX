<%-- 
    Document   : stu_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="team"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div>
                <h3>申请名单：</h3>
            </div>
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>  
                            <th>职务</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < 10; i++) {
                        %>
                        <tr>
                            <td>12311231</td>
                            <td>辛辛那提·穆罕穆德</td>   
                            <td>负责人</td>
                            <td>
                                <button class="btn"><span class="glyphicon glyphicon-ok-sign"></span> 同意</button>
                                <button class="btn"><span class="glyphicon glyphicon-remove-sign"></span> 拒绝</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div>
                <button type="button" class="btn btn-default">返回</button>
            </div>
        </div>
    </body>
</html>
