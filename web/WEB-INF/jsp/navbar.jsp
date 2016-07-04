<%-- 
    Document   : navbar
    Created on : 2016-7-3, 12:21:47
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/lib/css/navbar.css" rel="stylesheet">
<script src="<%=path%>/lib/js/nacbar.js"></script>
<script>
    window.onload = function ()
    {
        var myDate = new Date();
        document.getElementById("time").innerHTML = myDate.getFullYear() + "年" + (myDate.getMonth()+1) + "月" + myDate.getDate() + "日";
    }
</script>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="login.htm">课程中心</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">教学周次</a></li>
                <li><a id="time"></a></li>
                <li><div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle btn-xs" 
                                data-toggle="dropdown">
                            2015年春季 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">2015年春季</a></li>
                            <li><a href="#">2015年夏季</a></li>
                            <li><a href="#">2015年秋季</a></li>
                            <li><a href="#">2016年春季</a></li>
                            <li><a href="#">2016年夏季</a></li>
                            <li><a href="#">2016年秋季</a></li>
                            <li><a href="#">2017年春季</a></li>
                            <li><a href="#">2017年夏季</a></li>
                        </ul>
                    </div></a></li>
                <li><a href="#">系统消息</a></li>
                <li><button type="button" id="signout" onclick="signout()">注销</button>></li>
            </ul>
            <%--
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
            --%>
        </div>
    </div>
</nav>

