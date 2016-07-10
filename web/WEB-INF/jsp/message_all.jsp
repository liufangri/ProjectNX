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
        <div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>所有消息</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 1; i < 200; i++) {
                        %>
                        <tr>
                            <td>哈哈哈哈</td>
                            <td><span class="pull-right">2099-03-22</span></td>   
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