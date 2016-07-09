<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page import="com.teamnx.model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.teamnx.model.ShowGroup"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
        ArrayList<ShowGroup> passedGroups = (ArrayList<ShowGroup>) request.getAttribute("passedGroups");
        ArrayList<ShowGroup> waitingGroups = (ArrayList<ShowGroup>) request.getAttribute("waitingGroups");
        ArrayList<ShowGroup> formingGroups = (ArrayList<ShowGroup>) request.getAttribute("formingGroups");
        Course course = (Course) request.getAttribute("course");
    %>
    <jsp:include page="header.jsp"/>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=path%>/lib/css/dashboard.css" rel="stylesheet">
        <link href="<%=path%>/lib/css/resourceindex.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navteachercolumn.jsp">
                    <jsp:param name="type" value="team"/>
                </jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div>
                <ul class="nav nav-pills" id="mytab">
                    <li class="active">
                        <a href="#passed">已通过</a>
                    </li>
                    <li><a href="#checking">待审核</a></li>
                    <li><a href="#building">组建中</a></li>
                    <li><a href="#alone">无团队学生</a></li>              
                </ul>
            </div>
            <div class="tab-content"> 
                <div id="passed" class="tab-pane active">
                    <div class="table-responsive">
                        <table class="table table-striped" cellspacing="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>队名</th>
                                    <th>负责人</th>  
                                    <th>人数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (ShowGroup sg : passedGroups) {
                                %>
                                <tr>
                                    <td><a href=""><%=sg.getName()%></a></td>
                                    <td><%=sg.getManager()%></td>   
                                    <td><%=sg.getNumber()%>/<%=course.getMax_member()%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="checking" class="tab-pane">
                    <div class="table-responsive">
                        <table class="table table-striped" cellspacing="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>队名</th>
                                    <th>负责人</th>  
                                    <th>人数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (ShowGroup sg : waitingGroups) {
                                %>
                                <tr>
                                    <td><a href=""><%=sg.getName()%></a></td>
                                    <td><%=sg.getManager()%></td>   
                                    <td><%=sg.getNumber()%>/<%=course.getMax_member()%></td>
                                    <td>
                                        <button onClick="location.href = 'teacherPermitting.htm?course_id=${course_id}&group_id=<%=sg.getGroupId()%>&status=<%=2%>'" class="btn btn-success"><span class="glyphicon glyphicon-ok-sign"></span> 同意</button>
                                        <button onClick="location.href = 'teacherPermitting.htm?course_id=${course_id}&group_id=<%=sg.getGroupId()%>&status=<%=0%>'" class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign"></span> 拒绝</button>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="building" class="tab-pane">
                    <div class="table-responsive">
                        <table class="table table-striped" cellspacing="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>队名</th>
                                    <th>负责人</th>  
                                    <th>人数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (ShowGroup sg : formingGroups) {
                                %>
                                <tr>
                                    <td><%=sg.getName()%></td>
                                    <td><%=sg.getManager()%></td>   
                                    <td><%=sg.getNumber()%>/<%=course.getMax_member()%></td>                                    
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="alone" class="tab-pane"><div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>学号</th>
                                    <th>姓名</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 1; i < 3; i++) {
                                %>
                                <tr>
                                    <td>1,001</td>
                                    <td id="112">Lorem</td>
                                    <td><button type="button" class="btn btn-success" onclick="manager();" data-toggle="modal">新建团队</button></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>  
                </div>
            </div>
            <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal1" class="modal fade in" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header pull-left">
                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                            <h4 class="modal-title">团队组建</h4>
                        </div>
                        <div class="modal-body pull-left">
                            <div class="clearfix">
                                <div class="modalTitleSmall pull-left">名称：</div>
                                <div class="col-lg-10 marginB10 pull-right">
                                    <input class="form-control" id="folderName" type="text" placeholder="请输入团队名称">
                                </div>
                            </div>
                            <div class="clearfix"><h4>最大人数限制: 100</h4></div>
                            <div class="clearfix"><h4>负责人： <span id="teammanager"></span></h4></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="">确定</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(function () {
                $('#mytab a').click(function (e) {
                    e.preventDefault();//阻止a链接的跳转行为 
                    $(this).tab('show');//显示当前选中的链接及关联的content 
                })
            })
            function manager() {
                $('#teammanager').text($('#112').text());
                $('#myModal1').modal("show");
            }
        </script>
    </body>

</html>