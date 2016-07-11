<%-- 
    Document   : navbarcenter
    Created on : 2016-7-3, 12:21:47
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/lib/css/navbar.css" rel="stylesheet">
<script>
    $(document).ready(function ()
    {
        var myDate = new Date();
        document.getElementById("time").innerHTML = myDate.getFullYear() + "年" + (myDate.getMonth() + 1) + "月" + myDate.getDate() + "日";
    })
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
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="usercenter.htm">课程中心</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a >教学周次</a></li>
                <li><a id="time"></a></li>
                <li>
                    <div  class="btn-group" >
                        <button id="semester" type="button" class="btn dropdown-toggle " 
                                data-toggle="dropdown">
                            2015年春季 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <!--                            TODO-->
                            <li><a href="#">2015年春季</a></li>
                            <li><a href="#">2015年夏季</a></li>
                            <li><a href="#">2015年秋季</a></li>
                            <li><a href="#">2016年春季</a></li>
                            <li><a href="#">2016年夏季</a></li>
                            <li><a href="#">2016年秋季</a></li>
                            <li><a href="#">2017年春季</a></li>
                            <li><a href="#">2017年夏季</a></li>
                        </ul>
                    </div>
                </li>
                <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-success">4</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 4 messages</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <div class="slimScrollDiv" style="position: relative; overflow: auto; width: auto; height: 200px;">
                                <ul class="menu" style="overflow: scroll; width: 100%; height: 200px;">
                                    <li><!-- start message -->
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Support Team
                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <!-- end message -->
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                AdminLTE Design Team
                                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Developers
                                                <small><i class="fa fa-clock-o"></i> Today</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Sales Department
                                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Reviewers
                                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>
                </li>
                <li><a href="logout.htm">注销</a></li>
            </ul>
            <%-- 课程搜索暂时不做
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
            --%>
        </div>
    </div>
    <div style="display: none" id="goTopBtn"><i class="fa fa-fw fa-2x fa-arrow-circle-up"></i></div>  
</nav>
<script type=text/javascript>goTopEx();</script>  

