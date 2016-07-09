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
            <div>
                <h3>团队名称：<b>安拉胡阿克巴</b>  状态：<span class="glyphicon glyphicon-user"></span><i style="color: red">组建中</i></h3>
            </div>
            <div>                
                <button type="button" class="btn btn-default">添加成员</button>
                <button type="button" class="btn btn-default">解散团队</button>
            </div>
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>  
                            <th>职务</th>   
                            <th>操作</th>
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
                                <div  class="btn-group" >
                                    <button type="button" class="btn dropdown-toggle " 
                                            data-toggle="dropdown">
                                        选择操作 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">移除</a></li>
                                        <li><a href="#">设为负责人</a></li>
                                    </ul>
                                </div>
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