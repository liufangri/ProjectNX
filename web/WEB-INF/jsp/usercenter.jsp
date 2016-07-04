<%-- 
    Document   : classnav
    Created on : 2016-7-3, 16:58:35
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                    <section id="articlebox" class="tiles">
                        <%
                            for (int i = 1; i <= 15; i++) {
                                out.print("<article class=\"style" + (int) (1 + Math.random() * (7 - 1 + 1)) + "\">");
                                out.print("<span class=\"image\">");
                                out.print("<img src=\"" + request.getContextPath() + "/images/pic0" + (int) (1 + Math.random() * (9 - 1 + 1)) + ".jpg\" alt=\"\"/>");
                                out.print("</span>");
                                out.print("<a href=\"te_index.htm\">");
                                out.print("<h2>Magna</h2>");
                                out.print(" <div class=\"content\">");
                                out.print("  <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>");
                                out.print("</div>");
                                out.print(" </a>");
                                out.print("</article>");
                            }
                        %>
                    </section>

                </div>
            </div>
        </div>
    </body>

</html>
