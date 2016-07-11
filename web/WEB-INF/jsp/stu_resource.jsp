<%-- 
    Document   : stu_resource.jsp
    Created on : 2016-7-5, 23:18:57
    Author     : coco
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teamnx.model.Resource"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        Resource currentFolder = (Resource) request.getAttribute("current_folder");
        ArrayList<Resource> resources = (ArrayList<Resource>) request.getAttribute("resources");
        String courseId = (String) request.getAttribute("course_id");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        String fullPath = (String) request.getAttribute("full_path");
        String[] folders = fullPath.split("\\\\");
    %>

    <jsp:include page="header.jsp"/>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=path%>/lib/css/resourceindex.css" rel="stylesheet" />
        <script src="<%=path%>/lib/js/file.js"></script>
        <link href="<%=path%>/lib/css/fileinput.min.css" rel="stylesheet">
        <link href="<%=path%>/lib/css/ace.min.css" rel="stylesheet">
        <script src="<%=path%>/lib/js/fileinput.min.js"></script>
        <script src="<%=path%>/lib/js/fileinput-zh.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#upload").fileinput({
                    language: "zh",
                    fileActionSettings: {showZoom: false},
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="resource"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <section>
                <section>

                    <!--state overview start-->
                    <div class="row state-overview">
                        <input type="hidden" id="path" name="path" value="">
                        <input type="hidden" id="dpath" name="dpath">
                        <input type="hidden" id="sid" name="sid">
                        <input type="hidden" id="delid" name="delid">
                    </div>
                    <!--state overview end-->

                    <div class="row">
                        <div class="col-lg-12">
                            <!--breadcrumbs start -->
                            <ul class="breadcrumb">
                                <% if (currentFolder.getFatherId() != null) {%>
                                <li><a href="resource.htm?course_id=<%= currentFolder.getCourseId()%>&folder_id=<%= currentFolder.getFatherId()%>"><i class="icon-mail-reply"></i> 返回上一级</a></li>
                                    <%}%>
                                <li><a href="resource.htm?course_id=<%= currentFolder.getCourseId()%>">所有资料</a></li>
                                    <%
                                        for (int i = 2; i < folders.length - 2 && i < 8; i++) {
                                            if (!(folders.length == 9 && i == 6)) {
                                    %>

                                <li><%= folders[i]%></li><%}%>
                                    <%if (i == 6) {%>
                                <li>...</li>
                                    <%i++;
                                            }
                                        }%>
                                    <% if (folders.length > 3) {%>
                                <li class="active"><%=folders[folders.length - 2]%></li>
                                    <%}%>
                                    <% if (folders.length >= 3) {%>
                                <li class="active"><%=folders[folders.length - 1]%></li>
                                    <%}%>

                            </ul>
                            <!--breadcrumbs end -->
                        </div>
                        <div class="col-lg-12" id="block" style="display: none;">
                            <ul class="listType pull-left">
                            </ul>
                        </div>
                        <div class="col-lg-12" id="list" >
                            <ul id="listtable" class="listTable pull-left">
                                <li style="list-style-type:none;" id="fileList">
                                    <div class="listTableTop pull-left">
                                        <div class="listTableTopL pull-left">
                                            <div class="name" id="name">名称<div class="seq"></div></div>
                                        </div>
                                        <div class="listTableTopR pull-right">
                                            <div class="publisher" id="publisher">发布人<div class="seq"></div></div>
                                            <div class="updateTime" id="ctime">上传时间<div class="seq"></div></div>

                                        </div>
                                    </div>
                                </li>
                                <!--id为文件标识符-->
                                <% for (Resource r : resources) {%>
                                <li  style="list-style-type:none;" id="li_190">
                                    <div class="listTableIn pull-left">
                                        <div class="listTableInL pull-left">
                                            <div class="name" onclick="location.href = '<%if (r.isFolder()) {%>resource.htm?course_id=<%= r.getCourseId()%>&folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>'">
                                                <i onclick="location.href = '<%if (r.isFolder()) {%>resource.htm?course_id=<%= r.getCourseId()%>&folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>'" style="vertical-align: middle" class="fa fa-2x <%if (r.isFolder()) {%>  fa-folder  <%} else {%>fa-file-o  <%}%>" ></i>
                                                <a style="padding-left:5px" target="_self" id="a_190"   href="<%if (r.isFolder()) {%>resource.htm?course_id=<%= r.getCourseId()%>&folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>">
                                                    <em class="folder"></em>
                                                </a>
                                                <span class="div_pro">
                                                    <a id="sa_190"   href="<%if (r.isFolder()) {%>resource.htm?course_id=<%= r.getCourseId()%>&folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>" ><%= r.getName()%></a>
                                                </span>
                                            </div>

                                        </div>
                                        <div class="listTableInR pull-right">
                                            <div class="publisher"><%= r.getTeacherName()%></div>
                                            <div class="updateTime"><%= sdf.format(new Date(r.getLastChange()))%></div>
                                            <div>
                                                <%
                                                    if (!r.isFolder()) {
                                                %>
                                                <div class="btn-group">
                                                    <a href="download.htm?resourceId=<%=r.getId()%>" ><button type="button" class="btn btn-primary"><i class="fa fa-download"></i></button></a>
                                                </div>
                                                <%}%>
                                            </div>
                                            <div style="display:none;" class="float_box" id="box_190">
                                                <ul class="control">
                                                    <li><a href="<%if (r.isFolder()) {%>resource.htm?folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>">
                                                            <i class="icon-download-alt"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <%}%>
                            </ul>
                        </div>
                    </div>
                    <input type="hidden" id="fileId">
                    <input type="hidden" id="dirId">
                    <input type="hidden" id="order">
                    <input type="hidden" id="by">
                </section>
            </section>
        </div>
    </body>
</html>