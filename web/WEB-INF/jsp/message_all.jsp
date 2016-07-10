<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teamnx.model.Message"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <%
        ArrayList<Message> messageList = (ArrayList<Message>) request.getAttribute("message_list");
        SimpleDateFormat sdf =new SimpleDateFormat("MM-dd HH:mm");
    %>
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
                        <%			    for (Message m : messageList) {
                        %>
                        <tr>
                            <td><%= m.getText() %></td>
                            <td><span class="pull-right"><%= sdf.format(new Date(m.getTime().getTime())) %></span></td>   
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