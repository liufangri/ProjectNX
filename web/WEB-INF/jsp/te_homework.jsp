<%-- 
    Document   : te_homework
    Created on : 2016-7-4, 20:55:52
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
            <div class="table-responsive">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>作业名称</th>
                            <th>起始时间</th>  
                            <th>截止时间</th>  
                            <th>作业要求</th>  
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
                            <td><div style="width:200px;white-space: nowrap;overflow: scroll;">据说Chrome34+支持响应式图片，就是直接&lt;img&gt;标签上使用特定属性，就可以实现图片自动的响应式获取，大伙可以试试据说Chrome34+支持响应式图片，就是直接&lt;img&gt;标签上使用特定属性，就可以实现图片自动的响应式获取，大伙可以试试据说Chrome34+支持响应式图片，就是直接&lt;img&gt;标签上使用特定属性，就可以实现图片自动的响应式获取，大伙可以试试</div></td>  
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
