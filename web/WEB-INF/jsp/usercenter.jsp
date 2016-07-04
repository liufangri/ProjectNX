<%-- 
    Document   : classnav
    Created on : 2016-7-3, 16:58:35
    Author     : coco
--%>
<!DOCTYPE HTML>
<!--
        Phantom by HTML5 UP
        html5up.net | @ajlkn
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
    <%
        String path = request.getContextPath();
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <link rel="stylesheet" href="<%=path%>/lib/css/main.css" />
        <script src="<%=path%>/lib/js/skel.min.js"></script>
        <script src="<%=path%>/lib/js/util.js"></script>
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="<%=path%>/lib/js/main.js"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
            <header id="header">

            </header>

            <!-- Main -->
            <div id="main">
                <div class="inner"> 
                    <section class="tiles">
                        <article>
                            <span class="image">
                                <img src="<%=path%>/images/pic01.jpg" alt="" />
                            </span>
                            <a href="index.htm">
                                <h2>Magna</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style2">
                            <span class="image">
                                <img src="<%=path%>/images/pic02.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Lorem</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style3">
                            <span class="image">
                                <img src="<%=path%>/images/pic03.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Feugiat</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style4">
                            <span class="image">
                                <img src="<%=path%>/images/pic04.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Tempus</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style5">
                            <span class="image">
                                <img src="<%=path%>/images/pic05.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Aliquam</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style6">
                            <span class="image">
                                <img src="<%=path%>/images/pic06.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Veroeros</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style2">
                            <span class="image">
                                <img src="<%=path%>/images/pic07.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Ipsum</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style3">
                            <span class="image">
                                <img src="<%=path%>/images/pic08.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Dolor</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style1">
                            <span class="image">
                                <img src="<%=path%>/images/pic09.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Nullam</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style5">
                            <span class="image">
                                <img src="<%=path%>/images/pic10.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Ultricies</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style6">
                            <span class="image">
                                <img src="<%=path%>/images/pic11.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Dictum</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                        <article class="style4">
                            <span class="image">
                                <img src="<%=path%>/images/pic12.jpg" alt="" />
                            </span>
                            <a href="generic.html">
                                <h2>Pretium</h2>
                                <div class="content">
                                    <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                                </div>
                            </a>
                        </article>
                    </section>
                </div>
            </div>



        </div>
    </body>
</html>
