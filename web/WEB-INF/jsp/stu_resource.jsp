<%-- 
    Document   : stu_resource.jsp
    Created on : 2016-7-5, 23:18:57
    Author     : coco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
    %>

    <jsp:include page="header.jsp"/>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="iBarn">
        <link rel="shortcut icon" href="img/favicon.png">
        <title>iBarn</title>
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
                        <div class="col-lg-12" id="block" style="display: none;">
                            <ul class="listType pull-left">
                            </ul>
                        </div>
                        <div class="col-lg-12" id="list" >
                            <ul id="listtable" class="listTable pull-left">
                                <li style="list-style-type:none;" id="fileList">
                                    <div class="listTableTop pull-left">
                                        <div class="listTableTopL pull-left">
                                            <div class="cBox"><input id="chkAll" name="chkAll" type="checkbox"></div>
                                            <div class="name" id="name">名称<div class="seq"></div></div>
                                        </div>
                                        <div class="listTableTopR pull-right">
                                            <div class="publisher" id="publisher">发布人<div class="seq"></div></div>
                                            <div class="updateTime" id="ctime">上传时间<div class="seq"></div></div>
                                        </div>
                                    </div>
                                </li>
                                <!--                                id为文件标识符-->
                                <li style="list-style-type:none;" id="li_190">
                                    <div class="listTableIn pull-left">
                                        <div class="listTableInL pull-left">
                                            <div class="cBox"><input name="classLists" id="classLists190" type="checkbox" value="190" class="classLists"></div>
                                            <div class="name">
                                                <img src="<%=path%>/images/folder.jpg"> <a target="_self" id="a_190"   href="index.php?path=a"><em class="folder"></em></a>
                                                <span class="div_pro">
                                                    <a id="sa_190" target="_self"   href="index.php?path=a" >a</a>
                                                </span>
                                            </div>

                                        </div>
                                        <div class="listTableInR pull-right">
                                            <div class="publisher">黄鹤</div>
                                            <div class="updateTime">2016-07-06 00:59:44</div>
                                            <a href="#" onclick="$('#sid').val(190);javascript:downloadResource();" data-toggle="modal"><i class="icon-remove"></i>下载</a>
                                        </div>
                                    </div>
                                </li>

                            </ul>
                        </div>
                    </div>
                    <input type="hidden" id="fileId">
                    <input type="hidden" id="dirId">
                    <input type="hidden" id="order">
                    <input type="hidden" id="by">
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal1" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header pull-left">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">上传文件</h4>
                                </div>
                                <div class="modal-body pull-left">
                                    <input id="upload" type="file"  class="file-loading" name="uploadFile">
                                </div>

                                <div class="modal-footer">
                                    <!--                                测试用-->
                                    <button type="button" class="btn btn-success" onclick="addFile();">测试</button>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal2" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header pull-left">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">编辑</h4>
                                </div>
                                <div class="modal-body pull-left">
                                    <div>
                                        <div class="modalTitleSmall pull-left">名称：</div>
                                        <div class="col-lg-10 marginB10 pull-left">
                                            <input class="form-control" id="newName" type="text" placeholder="请输入名称">
                                            <input type="hidden" id="aname" name="aname">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success" onclick="file.setName();">确定</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal3" class="modal fade in" style="display: none;">
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
                                    <button type="button" class="btn btn-success" onclick="file.trans();">确定</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal4" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header pull-left">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">删除</h4>
                                </div>
                                <div class="modal-body pull-left">
                                    <div class="delText">确定要删除吗？</div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success" onclick="javascript:deleteresouce()">确定</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--新建文件夹模态框 保留-->
                    <div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal5" class="modal fade in" style="display: none;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header pull-left">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">新建文件夹</h4>
                                </div>
                                <div class="modal-body pull-left">
                                    <div>
                                        <div class="modalTitleSmall pull-left">名称：</div>
                                        <div class="col-lg-10 marginB10 pull-left">
                                            <input class="form-control" id="folderName" type="text" placeholder="请输入名称">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success" onclick="addFolder();">确定</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
            <!--main content end-->
            <div id="msg_win">
                <div class="icos"><a id="msg_min" title="最小化" href="javascript:void 0">_</a><a id="msg_close" title="关闭" href="javascript:void 0">×</a></div>
                <div id="msg_title">上传文件</div>
                <div id="msg_content">
                    <ul class="pull-left" id="progress"></ul>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $('#myModal5').on('hidden.bs.modal', function () {
                $(this).find('input').val("");
            });
            $('#myModal1').on('hidden.bs.modal', function () {
                var father = $(this).find(".file-input").parent();
                $(this).find(".file-input").remove();
                father.prepend(" <input id=\"upload\" type=\"file\"  class=\"file-loading\" name=\"uploadFile\">");
                father.prepend("<script> $(\"#upload\").fileinput({language: \"zh\", fileActionSettings: {showZoom: false}, });<\/script>")
            });
            //            $().on('hidden', '.modal', function () {
            //                $(this).removeData('modal');
            //            });
            //            测试用
            function addFolder() {

                var i1 = document.createElement("i");
                i1.className = "icon-download-alt";
                var a1 = document.createElement("a");
                a1.href = "index.php?a=mdown&ids=190";
                var li1 = document.createElement("li1");
                a1.appendChild(i1);
                li1.appendChild(a1);
                var ul1 = document.createElement("ul1");
                ul1.className = "control";
                ul1.appendChild(li1);
                var div1 = document.createElement("div");
                div1.style = "display:none";
                div1.className = "float_box";
                div1.appendChild(ul1);
                var div2 = document.createElement("div");
                div2.className = "updateTime";
                div2.innerHTML = "2016-07-06 02:55:54";
                var div4 = document.createElement("div");
                div4.className = "listTableInR pull-right";
                div4.appendChild(div1);
                div4.appendChild(div2);


                var a2 = document.createElement("a");
                a2.href = "index.php?a=mdown&ids=190";
                a2.targett = "_self";
                a2.innerHTML = document.getElementById("folderName").value;
                var span2 = document.createElement("span2");
                span2.className = "div_pro";
                span2.appendChild(a2);
                var img = document.createElement("img");
                img.src = "<%=path%>/images/folder.jpg";
                var div5 = document.createElement("div");
                div5.className = "name";
                div5.appendChild(img);
                div5.appendChild(span2);

                var check = document.createElement("input");
                check.type = "checkbox";
                check.className = "classLists";
                var div6 = document.createElement("div");
                div6.className = "cBox";
                div6.appendChild(check);
                var div7 = document.createElement("div");
                div7.className = "listTableInL pull-left";
                div7.appendChild(div6);
                div7.appendChild(div5);
                var div8 = document.createElement("div");
                div8.className = "listTableIn pull-left";
                div8.appendChild(div7);
                div8.appendChild(div4);
                var li = document.createElement("li");
                li.appendChild(div8);
                document.getElementById("listtable").appendChild(li);
            }
            function addFile() {

                var i1 = document.createElement("i");
                i1.className = "icon-download-alt";
                var a1 = document.createElement("a");
                a1.href = "index.php?a=mdown&ids=190";
                var li1 = document.createElement("li1");
                a1.appendChild(i1);
                li1.appendChild(a1);
                var ul1 = document.createElement("ul1");
                ul1.className = "control";
                ul1.appendChild(li1);
                var div1 = document.createElement("div");
                div1.style = "display:none";
                div1.className = "float_box";
                div1.appendChild(ul1);
                var div2 = document.createElement("div");
                div2.className = "updateTime";
                div2.innerHTML = "2016-07-06 02:55:54";
                var div4 = document.createElement("div");
                div4.className = "listTableInR pull-right";
                div4.appendChild(div1);
                div4.appendChild(div2);


                var a2 = document.createElement("a");
                a2.href = "index.php?a=mdown&ids=190";
                a2.targett = "_self";
                a2.innerHTML = $(".file-caption-name").attr("title");
                var span2 = document.createElement("span2");
                span2.className = "div_pro";
                span2.appendChild(a2);
                var img = document.createElement("img");
                img.src = "<%=path%>/images/u247.png";
                var div5 = document.createElement("div");
                div5.className = "name";
                div5.appendChild(img);
                div5.appendChild(span2);

                var check = document.createElement("input");
                check.type = "checkbox";
                check.className = "classLists";
                var div6 = document.createElement("div");
                div6.className = "cBox";
                div6.appendChild(check);
                var div7 = document.createElement("div");
                div7.className = "listTableInL pull-left";
                div7.appendChild(div6);
                div7.appendChild(div5);
                var div8 = document.createElement("div");
                div8.className = "listTableIn pull-left";
                div8.appendChild(div7);
                div8.appendChild(div4);
                var li = document.createElement("li");
                li.appendChild(div8);
                document.getElementById("listtable").appendChild(li);
            }

            function deleteresouce()
            {
                $("input[class='classLists']").each(function () {
                    if ($(this).is(':checked'))
                    {
                        $(this).parent().parent().parent().parent().remove();
                    }
                });
            }



            function modalTrans() {
                $('#myModal3').modal('show');
                $.ajax({
                    url: 'index.php?a=getTree',
                    type: 'POST',
                    dataType: 'json',
                    timeout: 8000,
                    success: function (data) {
                        $('#tree').treeview({data: data});
                        $('#tree').on('nodeSelected', function (event, data) {
                            $('#dirId').val(data.mapId);
                            $('#dpath').val(data.path);
                        });
                        $('#tree').on('nodeUnselected', function (event, data) {
                            $('#dirId').val('');
                        });
                    }
                });
            }
            function modalName(id, name) {
                if (!id) {
                    if ($('input[name="squaredCheckbox"]:checked').length > 1) {
                        alert(file.lang('一次只能重命名一个资料'));
                        return false;
                    }
                    $('input[name="squaredCheckbox"]:checked').each(function () {
                        id = $(this).val();
                    });
                    if (!id) {
                        alert(file.lang('请选择要重命名的资料'));
                        return false;
                    }
                    name = $('#aname_' + id).val();
                }
                $('#newName').val(name.replace(/\.\w+$/, ''));
                $('#aname').val(name);
                $('#fileId').val(id);
                $('#myModal2').modal('show');
            }
            function modalDel(name) {
                $('.delText label').text(name);
                $('#myModal4').modal('show');
            }
            function down() {
                if (Cookies.get('show') == 'block') {
                    name = 'squaredCheckbox';
                } else {
                    name = 'classLists';
                }
                var ids = new Array();
                $('input[name="' + name + '"]:checked').each(function () {
                    ids.push($(this).val());
                });
                var idstr = ids.join(',');
                if (!idstr) {
                    alert(file.lang('请选择要下载的文件'));
                    return false;
                }
                href = '';
                if (idstr.indexOf(",") <= 0) {
                    if (name == 'squaredCheckbox') {
                        href = $('#ba_' + idstr).attr('href');
                    } else {
                        href = $('#a_' + idstr).attr('href');
                    }
                    info = href.match(/urlkey=([^&]+)/);
                }
                if (href && info) {
                    window.location.href = 'index.php?a=down&id=' + idstr;
                } else {
                    window.location.href = 'index.php?a=mdown&ids=' + idstr;
                }
            }
            var uploader = new plupload.Uploader({
                runtimes: 'html5,flash,silverlight,html4',
                browse_button: 'pickfiles',
                container: document.getElementById('ucontainer'),
                url: 'index.php?a=upload',
                chunk_size: '1024kb',
                flash_swf_url: 'lib/plupload/js/Moxie.swf',
                silverlight_xap_url: 'lib/plupload/js/Moxie.xap',
                filters: {
                    //          max_file_size : '4096mb',
                    mime_types: []
                },
                multipart_params: {path: $('#path').val()},
                init: {
                    FilesAdded: function (up, files) {
                        plupload.each(files, function (file) {
                            document.getElementById('progress').innerHTML +=
                                    '<li><div class="uploadTitle pull-left">' + file.name + '</div>' +
                                    '<div class="uploadSize pull-left">' + plupload.formatSize(file.size) + '</div>' +
                                    '<div id="' + file.id + '" class="uploadProportion pull-right">' +
                                    '</div></li>';
                            var hash = new hashMe(file.getNative(), function OutputHash(data) {
                                file.hash = data;
                                var data = {
                                    fileName: file.name,
                                    fileSize: file.origSize,
                                    hash: data,
                                    size: up.settings.chunk_size,
                                    maxFileCount: Math.ceil(file.origSize / up.settings.chunk_size)
                                };
                                $.post("index.php?a=uploadCheck", data, function (dy) {
                                    file.loaded = dy.data;
                                    setTimeout(function () {
                                        Message.init();
                                        uploader.start();
                                    }, 10);
                                }, 'json');
                            });
                        });
                    },
                    UploadComplete: function (up, files) {
                        if (Cookies.get('show') == 'block') {
                            type = 1;
                        } else {
                            type = 0;
                        }
                        plupload.each(files, function (f) {
                            var data = {
                                name: f.name,
                                hash: f.hash,
                                path: $('#path').val(),
                                size: f.origSize,
                                mime: f.type,
                                type: type
                            };
                            $.post("index.php?a=putFile", data, function (ret) {
                                if (ret.code == 1) {
                                    document.getElementById(f.id).innerHTML = file.lang('上传成功');
                                    if (!type) {
                                        $('#fileList').after($("<div></div>").html(ret.data).text());
                                        var ps = $(".listTableIn").position();
                                        if (ps) {
                                            $(".float_box").css("position", "absolute");
                                            $(".float_box").css("right", ps.left); //距离左边距
                                            $(".float_box").css("top", +7); //距离上边距
                                        }
                                    } else {
                                        $('.listType').prepend($("<div></div>").html(ret.data).text());
                                    }
                                    setTimeout('Message.close()', 4000);
                                } else {
                                    document.getElementById(f.id).innerHTML = file.lang(ret.data);
                                }
                            }, 'json');
                        });
                        uploader.splice();
                        uploader.refresh();
                    },
                    UploadProgress: function (up, file) {
                        document.getElementById(file.id).innerHTML = '<div class="progress progress-xs"><div style="width: ' + file.percent + '%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="' + file.percent + '" role="progressbar" class="progress-bar progress-bar-info"></div></div></div>';
                    }
                }
            });
            uploader.init();

            $(document).ready(function () {
                var ps = $(".listTableIn").position();
                if (ps) {
                    $(".float_box").css("position", "absolute");
                    $(".float_box").css("right", ps.left); //距离左边距
                    $(".float_box").css("top", +7); //距离上边距
                }
                $(".listTable.pull-left").on('click', 'li', function () {
                    if ($(this).attr('id') != 'fileList') {
                        if ($(this).hasClass("selected")) {
                            $(this).removeClass("selected").find(":checkbox").prop("checked", false);
                        } else {
                            $(this).addClass("selected").find(":checkbox").prop("checked", true);
                        }
                    }
                });
                $(".toggle button").click(function () {
                    if ($(this).hasClass("active")) {
                        return false;
                    } else {
                        $(this).addClass("active");
                        $(this).siblings().removeClass("active");
                        if ($(this).hasClass('listBtn')) {
                            $('#list').show();
                            $('#block').hide();
                            Cookies.set('show', 'list');
                            $('#rename').hide();
                            window.location.reload();
                        } else {
                            $('#list').hide();
                            $('#block').show();
                            Cookies.set('show', 'block');
                            $('#rename').show();
                            window.location.reload();
                        }
                    }
                });
                $('#suser').typeahead({
                    source: function (query, process) {
                        $.ajax({
                            url: 'index.php?m=user&a=getUsersByName',
                            type: 'post',
                            data: {name: query},
                            dataType: 'json',
                            success: function (ret) {
                                return process(ret);
                            }
                        });
                    }
                });
            })
            $("#chkAll").click(function () {
                if (this.checked) {
                    $('input:checkbox[name="classLists"]').prop("checked", true);
                } else {
                    $('input:checkbox[name="classLists"]').prop("checked", false);
                }
            });
            window.onload = function () {
                $('input:checkbox[name="classLists"]').prop("checked", false);
            }
            $('input[name="classLists"]').click(function () {
                $('#chkAll').attr('checked', $('input[name="classLists"]:checked').length == $('input[name="classLists"]').length);
            });

            var i = 0;
            var j = 0;
            $('#editPwd').click(function () {
                i++ % 2 == 0 ? $('#editPwdIpt').show() : $('#editPwdIpt').hide();
            });
            $('#editDate').click(function () {
                j++ % 2 == 0 ? $('#editDateIpt').show() : $('#editDateIpt').hide();
            });

            $("#name, #size, #ctime").click(function () {
                var by;
                if ($(this).children().hasClass("downward")) {
                    $(this).children().removeClass("downward");
                    $(this).children().addClass("descending");
                    by = 'desc';
                } else {
                    if ($(this).children().hasClass("descending")) {
                        $(this).children().removeClass("descending");
                        $(this).children().addClass("downward");
                        by = 'asc';
                    } else {
                        $(this).children().addClass("downward");
                        by = 'asc';
                    }
                }
                $(this).parent().siblings().children().removeClass("downward");
                $(this).parent().siblings().children().removeClass("descending");
                $('#order').val($(this).attr('id'));
                $('#by').val(by);
                $.ajax({
                    url: 'index.php',
                    type: 'POST',
                    data: {
                        path: encodeURIComponent($('#path').val()),
                        order: $(this).attr('id'),
                        by: by,
                        search: encodeURIComponent($('#search').val()),
                        type: $('#type').val(),
                        res: 1,
                        curPage: 1},
                    dataType: 'html',
                    timeout: 8000,
                    success: function (data) {
                        if (data) {
                            $(".listTable.pull-left li").not(":first").remove();
                            $('#fileList').after(data);
                            var ps = $(".listTableIn").position();
                            if (ps) {
                                $(".float_box").css("position", "absolute");
                                $(".float_box").css("right", ps.left); //距离左边距
                                $(".float_box").css("top", +7); //距离上边距
                            }
                        }
                    }
                });
            });
            function show() {
                if ($('#shareType').val() == 1) {
                    $('#showPub').show();
                    $('#showSid').hide();
                } else {
                    $('#showPub').hide();
                    $('#showSid').show();
                }
            }
            function page(type) {
                order = $('#order').val();
                by = $('#by').val();
                if (!order) {
                    order = '';
                }
                if (!by) {
                    by = '';
                }
                if (type == -1) {
                    href = 'index.php?path=&search=&curPage=1&type=0&order=' + order + '&by=' + by;
                } else if (type == 0) {
                    href = 'index.php?path=&search=&curPage=0&type=0&order=' + order + '&by=' + by;
                }
                window.location.href = href;
            }
        </script> 

    </body>
</html>