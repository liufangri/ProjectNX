<%-- 
    Document   : te_studentlist
    Created on : 2016-7-4, 20:41:36
    Author     : coco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
    <jsp:include page="header.jsp"/>
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
                    <button type="button" class="btn btn-info pull-right"><span class="glyphicon glyphicon-plus-sign"></span> 组建新团队</button>
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
                                    for (int i = 0; i < 10; i++) {
                                %>
                                <tr>
                                    <td><a href="">安拉胡阿克巴</a></td>
                                    <td>辛辛那提·穆罕穆德</td>   
                                    <td>299/300</td>
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
                                    for (int i = 0; i < 10; i++) {
                                %>
                                <tr>
                                    <td>安拉胡阿克巴</td>
                                    <td>辛辛那提·穆罕穆德</td>   
                                    <td>233/300</td>
                                    <td>
                                        <button class="btn btn-success"><span class="glyphicon glyphicon-ok-sign"></span> 同意</button>
                                        <button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign"></span> 拒绝</button>
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
                                    for (int i = 0; i < 10; i++) {
                                %>
                                <tr>
                                    <td>安拉胡阿克巴</td>
                                    <td>辛辛那提·穆罕穆德</td>   
                                    <td>233/300</td>                                    
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
                                    for (int i = 1; i < 200; i++) {
                                %>
                                <tr>
                                    <td>1,001</td>
                                    <td>Lorem</td>   
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>  
                </div>
            </div>
        </div>
    </body>
    <script>
        $(function () {
            $('#mytab a').click(function (e) {
                e.preventDefault();//阻止a链接的跳转行为 
                $(this).tab('show');//显示当前选中的链接及关联的content 
            })
        })
    </script>
</html>