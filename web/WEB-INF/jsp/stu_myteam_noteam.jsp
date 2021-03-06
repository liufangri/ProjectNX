<%-- 
    Document   : stu_homework
    Created on : 2016-7-4, 20:55:52
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        String course_id = (String) request.getAttribute("course_id");
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
        <link href="<%=path%>/lib/css/resourceindex.css" rel="stylesheet" />
        <script type="text/javascript">
            function checkNull() {
                if (nx_check($("#folderName"), 45)) {
                    return true;
                } else {
                    $("#folderName").popover("toggle");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navstudentcolumn.jsp">
                    <jsp:param name="type" value="team"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="alert alert-warning">
                <i class="fa fa-warning fa-lg"></i>
                <strong style="padding-left: 6px">你还没有所属的团队</strong>
            </div>
            <div>
                <button type="button" class="btn btn-default" data-target="#myModal1" data-toggle="modal">组建新的团队</button>
                <a href="studentGroupList.htm?course_id=<%=course_id%>" />
                <button type="button" class="btn btn-default">申请加入团队</button>
                </a>
            </div>
            <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" 
                  id="myModal1" class="modal fade in" style="display: none;">
                <div class="modal-dialog">
                    <mvc:form action="establishGroup.htm" method="post" modelAttribute="group">
                        <div class="modal-content">
                            <div class="modal-header pull-left">
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                <h4 class="modal-title">团队组建</h4>
                            </div>
                            <div class="modal-body pull-left">
                                <div class="clearfix">
                                    <div class="modalTitleSmall pull-left">名称：</div>
                                    <div class="col-lg-10 marginB10 pull-right">
                                        <input class="form-control" data-container="body" data-toggle="popover" data-placement="top" 
                                               data-content="团队名称长度应在1-45之间" name="name" id="folderName" type="text" required="required" placeholder="请输入团队名称">
                                        <input name="courseId" hidden="hidden" value="<%=course_id%>">
                                        <input name="status" hidden="hidden" value="0">
                                        <input name="maxMember" hidden="hidden" value="${max_member}">
                                    </div>
                                </div>
                                <div class="clearfix"><h4>最大人数限制: ${max_member}</h4></div>
                                <div class="clearfix"><h4>负责人：${user_name}</h4></div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success" onclick="return checkNull();">确定</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            </mvc:form> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $('#myModal1').on('hidden.bs.modal', function () {
                $(this).find('input').val("");
                $('#folderName').popover('hide');
            });
        </script>
    </body>
</html>
