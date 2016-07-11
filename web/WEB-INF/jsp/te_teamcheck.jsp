<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="team"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="clearfix">
                <button type="button" class="btn btn-info">返回</button>
            </div>
            <div>
                <h3>团队名称：<b>安拉胡阿克巴</b>  状态：<span class="glyphicon glyphicon-user"></span><i style="color: red">组建中</i>
                    <button class="btn btn-danger pull-right"><span class="glyphicon glyphicon-remove-sign"></span> 拒绝</button>
                    <button class="btn btn-success pull-right"><span class="glyphicon glyphicon-ok-sign"></span> 同意</button>
                </h3>
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