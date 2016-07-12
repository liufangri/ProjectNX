<%-- 
    Document   : navbar
    Created on : 2016-7-3, 12:21:47
    Author     : coco
--%>
<%@page import="java.util.Date"%>
<%@page import="com.teamnx.model.Semester"%>
<%@page import="java.lang.Integer"%>
<%@page import="com.teamnx.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Boolean isread = false;
    String path = request.getContextPath();
    User user = (User) session.getAttribute("user");
    ArrayList<Message> unreadMessageList = (ArrayList<Message>) session.getAttribute("unread_message_list");
    String courseId = (String) request.getParameter("course_id");
    int num = unreadMessageList.size();
    int week = (Integer) session.getAttribute("current_week");
    int year = (Integer) session.getAttribute("year");
    String season = (String) session.getAttribute("season");
    boolean inUsercenter = courseId == null;
%>
<!--如果没有未读 就不用发信息给服务器-->


<link href="<%=path%>/lib/css/navbar.css" rel="stylesheet">
<link href="<%=path%>/lib/css/AdminLTE.min.css" rel="stylesheet"/>
<script>
    $(document).ready(function ()
    {
        var myDate = new Date();
        document.getElementById("time").innerHTML = myDate.getFullYear() + "年" + (myDate.getMonth() + 1) + "月" + myDate.getDate() + "日";
    });
    function isRead()
    {
        var checkInput = document.getElementById("check_for_read");
        if (checkInput === "readed") {
        } else {

            checkInput.value = "readed";
            $.ajax({
                url: 'isread.htm',
                data: {
                    'user_id': ('<%= user.getId()%>'),
                },
                type: 'POST',
                dataType: 'json',
                timeout: 8000,
                success: function (data) {

                }
            });
        }
    }

    function goTopEx() {
        var obj = document.getElementById("goTopBtn");
        function getScrollTop() {
            return document.documentElement.scrollTop + document.body.scrollTop;
        }
        function setScrollTop(value) {
            if (document.documentElement.scrollTop) {
                document.documentElement.scrollTop = value;
            } else {
                document.body.scrollTop = value;
            }
        }
        window.onscroll = function () {
            getScrollTop() > 0 ? obj.style.display = "" : obj.style.display = "none";
        }
        obj.onclick = function () {
            var goTop = setInterval(scrollMove, 10);
            function scrollMove() {
                setScrollTop(getScrollTop() / 1.1);
                if (getScrollTop() < 1)
                    clearInterval(goTop);
            }
        }
    }

</script>
<input type="text" value="" id="check_for_read" hidden>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"  aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="usercenter.htm">课程中心</a>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <li><a id="time"></a></li>
                    <% if (!inUsercenter) { %>
                <li>
                    <a id="backtocenter" href="usercenter.htm">返回课程页面</a>
                </li>
                <%} else {%>
                <li>
                    <div  class="btn-group" >
                        <button id="semester" type="button" class="btn dropdown-toggle " 
                                data-toggle="dropdown">
                            ${year}年${season} <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <!--                            TODO-->
                            <li><a href="changeSemester.htm?&year=2015&semester=spring">2015年春季</a></li>
                            <li><a href="changeSemester.htm?&year=2015&semester=summer">2015年夏季</a></li>
                            <li><a href="changeSemester.htm?&year=2015&semester=fall">2015年秋季</a></li>
                            <li><a href="changeSemester.htm?&year=2016&semester=spring">2016年春季</a></li>
                            <li><a href="changeSemester.htm?&year=2016&semester=summer">2016年夏季</a></li>
                            <li><a href="changeSemester.htm?&year=2016&semester=fall">2016年秋季</a></li>
                            <li><a href="changeSemester.htm?&year=2017&semester=spring">2017年春季</a></li>
                            <li><a href="changeSemester.htm?&year=2017&semester=summer">2017年夏季</a></li>
                        </ul>
                    </div>
                </li>
                <%}%>
                <li><a><%= user.getName()%></a></li>
                        <% if (user.getCharacter() == User.STUDENT) {%>
                <li class="dropdown messages-menu" onclick="isRead();">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">系统消息

                        <i class="fa fa-bell-o"></i>
                        <%if (num != 0) {%><span class="label label-success"><%= num%></span><%}%>
                    </a>
                    <ul class="dropdown-menu">
                        <%if (num > 0) {%>

                        <li class="header" style="text-align: center">您有<%= num%>条新消息</li>

                        <li>
                            <!-- inner menu: contains the actual data -->

                            <ul class="menu" style="overflow-y: auto; width: 100%; height: 200px;">

                                <%for (int i = 0; i <= 5 && i < num; i++) {%>
                                <li>
                                    <a href="#">      
                                        <h4>
                                            <%= unreadMessageList.get(i).getText()%>
                                        </h4>
                                    </a>
                                </li>       
                                <%}%>


                            </ul>

                        </li>


                        <%} else {%>
                        <li class="header" style="text-align: center">没有新消息</li>
                            <%}%>

                        <li class="footer"><a href="allMessage.htm">查看所有消息</a></li>
                    </ul>
                </li>
                <%}%>
                <li><a href="logout.htm">注销</a></li>
            </ul>
        </div>
    </div>
    <div style="display: none" id="goTopBtn"><i class="fa fa-fw fa-2x fa-arrow-circle-up"></i></div>  
</nav>
<script type=text/javascript>
    goTopEx();

</script>  

