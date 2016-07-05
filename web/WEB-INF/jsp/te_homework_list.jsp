<%-- 
    Document   : te_homework_list
    Created on : 2016-7-4, 22:15:55
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
                    <jsp:param name="type" value="homework"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div style="display:table">
                <div style="display:table-cell">
                    <button class="btn btn-default" onclick="javascript:location.href = 'te_homework.htm'"> <span class="glyphicon glyphicon-chevron-left"></span>返回列表</button>
                </div>
                <div style="padding-left: 12px;display:table-cell">
                    <h1> 作业名</h1>
                </div>
            </div>


            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>  
                            <th>提交作业名</th>  
                            <th>分数</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 1; i < 200; i++) {
                        %>
                        <tr>
                            <td>13218888</td>
                            <td>安拉</td>   
                            <td><a href="te_homework_score.htm">阿西吧</td>  
                            <td>998</td>  
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