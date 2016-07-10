<%-- 
    Document   : te_resource.jsp
    Created on : 2016-7-5, 23:18:57
    Author     : coco
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.teamnx.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.Resource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>

<!DOCTYPE html>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        Resource currentFolder = (Resource) request.getAttribute("current_folder");
        ArrayList<Resource> resources = (ArrayList<Resource>) request.getAttribute("resources");
        User teacher = (User) session.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        String fullPath = currentFolder.getPath();
        String[] folders = fullPath.split("\\\\");
    %>

    <jsp:include page="header.jsp"/>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="iBarn">
        <link rel="shortcut icon" href="img/favicon.png">
        <link href="<%=path%>/lib/css/resourceindex.css" rel="stylesheet" />
        <script src="<%=path%>/lib/js/file.js"></script>
        <script src="<%=path%>/lib/js/bootstrap-treeview.js"></script>
        <link href="<%=path%>/lib/css/fileinput.min.css" rel="stylesheet">
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
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="resource"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <section>
                <section>
                    <!--state overview start-->
                    <div class="row state-overview">
                        <ul class="buttons pull-left">
                            <li>
                                <div id="ucontainer">
                                    <button class="btn btn-success" type="button" data-target="#myModal1" data-toggle="modal">
                                        <i class="icon-folder-close"></i>
                                        上传资料                                </button>
                                </div>
                            </li>
                            <li>
                                <button class="btn btn-warning" type="button" 
                                        data-target="#myModal5" data-toggle="modal">新建文件夹
                                    <i class="icon-folder-close"></i>
                                </button>
                            </li>
                        </ul>   
                        <input type="hidden" id="path" name="path" value="">
                        <input type="hidden" id="dpath" name="dpath">
                        <input type="hidden" id="sid" name="sid">
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
                                        for (int i = 3; i < folders.length - 1 && i < 10; i++) {
                                    %>
                                <li><%= folders[i]%>
                                    <%if (i == 9) {%>
                                <li>...</li>
                                    <%}
                                        }%>
                                <li class="active"><%=folders.length > 3 ? folders[folders.length - 1] : ""%>
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
                                            <div class="menu" id="menu">操作<div class="seq"></div></div>
                                        </div>
                                    </div>
                                </li>
                                <!--id为文件标识符-->
                                <!--每个资源项展示位置-->
                                <% for (Resource r : resources) {%>
                                <li  style="list-style-type:none;" id="li_190">

                                    <div class="listTableIn pull-left">
                                        <div class="listTableInL pull-left">

                                            <div class="name">
                                                <img src="<%=path%>/images/<%if (r.isFolder()) {%>folder.jpg<%} else {%>u247.png<%}%>"/> 
                                                <a target="_self" id="a_190"   href="<%if (r.isFolder()) {%>resource.htm?course_id=<%= r.getCourseId()%>&folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>">
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
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-primary dropdown-toggle" 
                                                            data-toggle="dropdown">
                                                        菜单 <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li><a href="#" onclick="deleteResource('<%= r.getId()%>');" data-toggle="modal"><i class="icon-remove"></i>删除</a></li>
                                                            <%if (!r.isFolder()) {%>
                                                        <li><a href="download.htm?resourceId=<%=r.getId()%>" ><i class="icon-remove"></i>下载</a></li>
                                                            <%}%>
                                                        <li><a href="#" onclick="renameResource('<%= r.getId()%>');"><i class="icon-remove"></i>重命名</a></li>
                                                        <li><a href="#" onclick="$('#sid').val('<%= r.getId()%>');modalTrans('<%= r.getCourseId()%>', '<%= r.getId()%>');" data-toggle="modal"><i class="icon-remove"></i>移动到</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div style="display:none;" class="float_box" id="box_190">
                                                <ul class="control">
                                                    <li><a href="<%if (r.isFolder()) {%>resource.htm?folder_id=<%=r.getId()%><%} else {%>download.htm?resourceId=<%=r.getId()%><%}%>">
                                                            <i class="icon-download-alt"></i></a></li>
                                                    <!--<li><a href="#" onclick="modalShare(190, '5a');" data-toggle="modal"><i class="icon-share"></i></a></li>
                                                        <li><a href="#" onclick="modalName(190, 'a');" data-toggle="modal"><i class="icon-edit"></i></a></li>
                                                        <li><a href="#" onclick="$('#sid').val(190);modalTrans();" data-toggle="modal"><i class="icon-random"></i></a></li>
                                                        <li><a href="#" onclick="modalDel('a');$('#delid').val(190);" data-toggle="modal"><i class="icon-remove"></i></a></li>-->
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <% }%>
                                <!--展示完成-->
                            </ul>
                        </div>
                    </div>
                    <input type="hidden" id="fileId">
                    <input type="hidden" id="dirId">
                    <input type="hidden" id="order">
                    <input type="hidden" id="by">
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal1" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <mvc:form action="uploadFile.htm" enctype="multipart/form-data" method="post" modelAttribute="new_resource">
                                <div class="modal-content">
                                    <div class="modal-header pull-left">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 class="modal-title">上传文件</h4>
                                    </div>
                                    <div class="modal-body pull-left">
                                        <input id="upload" type="file"  class="file-loading" name="uploadFile">
                                    </div>
                                    <input type="text" name="courseId" value="<%= currentFolder.getCourseId()%>" hidden="hidden">
                                    <input type="text" name="fatherId" value="<%= currentFolder.getId()%>" hidden="hidden">
                                    <input type="text" name="teacherId" value="<%= teacher.getId()%>" hidden="hidden">
                                    <input type="text" name="teacherName" value="<%= teacher.getName()%>" hidden="hidden">
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </mvc:form>
                        </div>
                    </div>
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal2" class="modal fade in" style="display: none;">
                        <form method="post" onsubmit="return check($('#rename'));" action="renameResource.htm" >
                            <div class="modal-dialog">
                                <input type="text" name="course_id" value="<%= currentFolder.getCourseId()%>" hidden="hidden">
                                <input type="text" name="resource_id" value="" id="rename_resource_id" hidden="hidden" >
                                <div class="modal-content">
                                    <div class="modal-header pull-left">
                                        <button aria-hidden="true"  data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 class="modal-title">重命名</h4>
                                    </div>
                                    <div class="modal-body pull-left">
                                        <div>
                                            <div class="modalTitleSmall pull-left">名称：</div>
                                            <div class="col-lg-10 marginB10 pull-left">
                                                <input id="rename" class="form-control" type="text" placeholder="请输入名称" name="name">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success">确定</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal3" class="modal fade in" style="display: none;">
                        <form action="transportResource.htm" method="post" id="trans_resource_form">
                            <input type="text" hidden="hidden" value="" name="resource_id" id="trans_resource_resource_id">
                            <input type="text" hidden="hidden" value="" name="aim_id" id="trans_resource_aim_id">
                            <input type="text" hidden="hidden" value="<%= currentFolder.getCourseId()%>" name="course_id">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header pull-left">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 class="modal-title">移动到</h4>
                                    </div>
                                    <div class="modal-body pull-left" style="height: 412px;overflow-y: scroll;">
                                        <div id="tree"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="submit" class="btn btn-success" onclick="return transResource();" value="确定">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal4" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <form action="deleteResource.htm" method="post" id="delete_resource_form_id">
                                <input id="delete_resource_id" name="resource_id" type="text" value="" hidden="hidden">
                                <div class="modal-content">
                                    <div class="modal-header pull-left">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 class="modal-title">删除</h4>
                                    </div>
                                    <div class="modal-body pull-left">
                                        <div class="delText">确定要删除吗？</div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success">确定</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--新建文件夹模态框 保留-->
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal5" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="addNewFolder.htm" onsubmit="return check($('#folderName'));" method="post">
                                    <div class="modal-header pull-left">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 class="modal-title">新建文件夹</h4>
                                    </div>
                                    <div class="modal-body pull-left">
                                        <div>
                                            <div class="modalTitleSmall pull-left">名称：</div>
                                            <div class="col-lg-10 marginB10 pull-left">
                                                <input class="form-control" id="folderName" type="text" placeholder="请输入名称" name="name">
                                                <input type="text" hidden="hidden" name="current_resource_id" value="<%= currentFolder.getId()%>">
                                                <input type="text" hidden="hidden" name="course_id" value="<%= currentFolder.getCourseId()%>">
                                            </div>
                                        </div>   
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit"  class="btn btn-success">确定</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
        </div>
        <script type="text/javascript">
            $('body').on('hidden', '.modal', function () {
                $(this).removeData('modal');
            });
            $('#myModal5').on('hidden.bs.modal', function () {
                $(this).find('input').val("");
            });
            $('#myModal2').on('hidden.bs.modal', function () {
                $(this).find('input').val("");
            });
            $('#myModal1').on('hidden.bs.modal', function () {
                var father = $(this).find(".file-input").parent();
                $(this).find(".file-input").remove();
                father.prepend(" <input id=\"upload\" type=\"file\"  class=\"file-loading\" name=\"uploadFile\">");
                father.prepend("<script> $(\"#upload\").fileinput({language: \"zh\", fileActionSettings: {showZoom: false}, });<\/script>")
            });
            function check(obj)
            {
                if ($.trim(obj.val()) === '') {
                    var oTimer = null;
                    var i = 0;
                    oTimer = setInterval(function () {
                        i++;
                        i == 5 ? clearInterval(oTimer) : (i % 2 == 0 ? obj.css("background-color", "#ffffff") : obj.css("background-color", "#ffd4d4"));
                    }, 200);
                    return false;
                }
                return true;
            }

            function renameResource(resource_id)
            {
                $('#myModal2').modal('show');
                document.getElementById("rename_resource_id").value = resource_id;
            }
            function deleteResource(resource_id)
            {
                $('#myModal4').modal('show');
                document.getElementById("delete_resource_id").value = resource_id;
            }
            function transResource()
            {
                dir = $('#dirId').val();
                alert(dir);
                if (dir === '') {
                    alert('请选择要转入的目录');
                    return false;
                }
                var sid = $('#sid').val();
                alert(sid);
                document.getElementById("trans_resource_resource_id").value = sid;
                document.getElementById("trans_resource_aim_id").value = dir;
                return true;

            }

            function modalTrans(courseId, resourceId) {
                $('#myModal3').modal('show');
                $.ajax({
                    url: 'getall.htm',
                    data: {
                        'course_id': (courseId),
                        'resource_id': (resourceId),
                    },
                    type: 'POST',
                    dataType: 'json',
                    timeout: 8000,
                    success: function (data) {
                        $('#tree').treeview({data: data});
                        $('#tree').on('nodeSelected', function (event, data) {
                            $('#dirId').val(data.mapId);
// 
                        });
                        $('#tree').on('nodeUnselected', function (event, data) {
                            $('#dirId').val('');
                        });
                    }
                });
            }
        </script> 

    </body>
</html>