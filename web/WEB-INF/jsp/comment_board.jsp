<%-- 
    Document   : message_board
    Created on : 2016-7-10, 15:57:33
    Author     : tmc
--%>

<%@page import="com.teamnx.model.User"%>
<%@page import="com.teamnx.model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        User user = (User) session.getAttribute("user");
        ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
    %>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="comment"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div>
                <h3>留言板</h3>
            </div>

            <div class="dialogs">
                <%
                    for (Comment c : comments) {
                %>
                <div class="itemdiv dialogdiv">
                    <div class="body">
                        <div class="time pull-right">
                            <i class="ace-icon fa fa-clock-o"></i>
                            <span class="green"><%=c.getTimestamp()%></span>
                        </div>
                        <div class="name">
                            <a ><%=c.getUser_name()%></a>
                        </div>
                        <div class="text"><%=c.getComment()%></div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>        
            <mvc:form action="addComment.htm" method="post" modelAttribute="comment">
                <div class="clearfix">
                    <textarea class="form-control" rows="5" name="comment"></textarea>
                    <input name="course_id" hidden="hidden" value="${course_id}"/>
                    <input name="user_name" hidden="hidden" value="<%=user.getName()%>"/>
                    <input name="character" hidden="hidden" value="<%=user.getCharacter()%>"/>
                    <button type="submit" class="btn btn-primary">留言</button>
                </div>
            </mvc:form>
        </div>
    </body>
</html>
