<%-- 
    Document   : admin
    Created on : 2016-7-10, 11:05:40
    Author     : coco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <%
        String path = request.getContextPath();
    %>

    <jsp:include page="header.jsp"/>
    <jsp:include page="navbar.jsp"/>
    <head>
        <link rel="stylesheet" href="<%=path%>/lib/css/style.css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=path%>/lib/css/daterangepicker-bs3.css" />
        <script type="text/javascript" src="<%=path%>/lib/js/moment.js"></script>
        <script type="text/javascript" src="<%=path%>/lib/js/daterangepicker.js"></script>
        <title>课程中心 - 登录</title>
    </head>
    <body>
        <div style="background-image:url('<%=path%>/images/login_background.jpg')" class="bg"></div>
        <div class="login">
            <div class="container">
                <div class="login-box" >
                    <div class="login-tab">
                        <div class="hd">
                            <ul><li class="on">设置学期周次</li></ul>
                        </div>
                        <div class="bd">
                            <mvc:form action="setSemester.htm" method="post" cssClass="form" onsubmit="return check()">
                                <div class="box-1">

                                    <div style="margin-top:40px">
                                        <span  class="glyphicon glyphicon-user"></span>本学期是
                                        <div class="row clearfix">
                                            <div class="col-md-6 column">
                                                <div id="DropDownList" ></div>
                                            </div>
                                            <div class="col-md-6 column ">
                                                <div id="DropDownList2" ></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>   
                                <div style="margin-top:40px">
                                    <span  class="glyphicon glyphicon-user"></span>本学期开始时间为

                                    <fieldset>
                                        <div class="control-group">
                                            <div class="controls">
                                                <div class="input-prepend input-group" >
                                                    <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                                    <input type="text"   name="timeLimit" id="reservationtime" class="form-control span4" value="2016-07-01"/>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                    <a href="stu_index.jsp"></a>
                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            $('#reservationtime').daterangepicker({
                                                "singleDatePicker": true,
                                                format: 'YYYY-MM-DD'
                                            }, function (start, end, label) {
                                                console.log(start.toISOString(), end.toISOString(), label);
                                            });
                                        });
                                    </script>

                                </div>
                                <div class="submit-box clearfix">
                                    <input id="type" type="hidden" value="ldap"/>
                                    <button id="submitButton" type="submit">登 录</button>

                                    <img id="processing" style="display: none;" src="<%=path%>/images/processing.gif" />
                                    <a id="errorMessage" style="color:red">${error_message}</a>
                                </div>
                            </mvc:form>
                            <button id="submitButton2" onclick=" $(function () {
                                        alert($('#Q1').val() + $('#Q2').val());
                                    });">登 123123录</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            function check() {
                var eid = $("#eid").val();
                var pw = $("#pw").val();
                if (eid.length < 1 || pw.length < 1) {
                    $("#errorMessage").text("请输入账号或密码！");
                    $("#errorMessage").show();
                    return false;
                }
                $("#processing").show();
                $("#errorMessage").hide();
                $("#submitButton").attr("disabled", "disabled");
                return true;
            }

            $(function () {
                //回车提交事件
                $("input").keydown(function () {
                    //keyCode=13是回车键
                    if (event.keyCode == "13") {
                        $("submitButton").click();
                    }
                });

                if ($(window).height() > 800) {
                    $(".login").css("height", $(window).height());
                }

                $(window).resize(function () {
                    if ($(window).height() > 800) {
                        $(".login").css("height", $(window).height());
                    }
                });
            });
            (function ($) {
                jQuery.fn.DropDownList = function (options) {

                    //设置插件的默认属性
                    var defaults = {
                        InputName: "Q",
                        ButtonText: "",
                        ReadOnly: true,
                        MaxHeight: -1,
                        onSelect: $.noop(),
                    }
                    var options = $.extend(defaults, options);
                    return this.each(function () {
                        var o = options;
                        var Obj = $(this);

                        var S = "<div class='input-group'>";
                        S = S + "<input type='text' class='form-control' name='" + o.InputName + "' id='" + o.InputName + "'  />";
                        S = S + "<div class='input-group-btn'>";
                        S = S + "<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>" + o.ButtonText + "<span class='caret'></span></button>";
                        S = S + "<ul class='dropdown-menu dropdown-menu-right'  style='width: 50%' role='menu'>";


                        if (o.Sections !== undefined)
                        {
                            $.each(o.Sections, function (n, value) {

                                //从第2节开始，在每节的顶部添加一条分割线
                                if (n > 0) {
                                    S = S + "<li class='divider'></li>";
                                }
                                //如果设置了ItemHeader参数，则给该节添加标题文本
                                if (value.ItemHeader !== undefined) {
                                    S = S + "<li class='dropdown-header'>" + value.ItemHeader + "</li>";
                                }
                                CreateItem(value);
                            });
                        } else
                        {
                            CreateItem(o);
                        }

                        var SelText = "";
                        var SelData = "";
                        function CreateItem(Items)
                        {
                            $.each(Items.Items, function (n, Item) {
                                //如果ItemData参数没有定义，则把ItemText参数传给ItemDate
                                if (Item.ItemData === undefined) {
                                    Item.ItemData = Item.ItemText;
                                }
                                S = S + "<li><a href='#'  ItemData='" + Item.ItemData + "' >" + Item.ItemText + "</a></li>";
                                //如果设置了Selected参数，则获取该条目的ItemDada和ItemText。
                                //如果有多个条目设置该参数，则获取的是满足条件最后一个条目
                                if (Item.Selected == true) {
                                    SelText = Item.ItemText;
                                    SelData = Item.ItemData;
                                }
                            });
                        }


                        S = S + "</ul></div></div>";

                        Obj.html(S);

                        var Input = Obj.find("input");
                        //如果有条目设置Selected参数，则调用设置活动条目的函数
                        if (SelText != "") {
                            SetData(SelText, SelData);
                        }

                        //给所有的条目绑定单击事件，单击后调用设置活动条目的函数
                        Obj.find("a").bind("click", function (e) {
                            SetData($(this).html(), $(this).attr("ItemData"));
                        });


                        if (o.ReadOnly == true)
                        {
                            Input.bind("cut copy paste keydown", function (e) {
                                e.preventDefault();
                            });
                        }


                        if (o.MaxHeight > 0)
                        {
                            var UL = Obj.find("ul");
                            if (UL.height() > o.MaxHeight)
                            {
                                UL.css({'height': o.MaxHeight, 'overflow': 'auto'});
                            }
                        }

                        function SetData(Text, Data)
                        {
                            Input.val(Text);
                            if (o.onSelect) {
                                o.onSelect(o.InputName, Data);
                            }
                        }

                    });
                }
            })(jQuery);
            $("#DropDownList").DropDownList(
                    {
                        InputName: "Q1",
                        ButtonName: "参考",
                        Items: [
                            {ItemText: "2014年", ItemData: "Demo1", Selected: true},
                            {ItemText: "2015年", ItemData: "Demo2"},
                            {ItemText: "2016年", ItemData: "Demo3"}
                        ]
                    });
            $("#DropDownList2").DropDownList(
                    {
                        InputName: "Q2",
                        ButtonName: "参考",
                        Items: [
                            {ItemText: "春季", ItemData: "Demo1", Selected: true},
                            {ItemText: "秋季", ItemData: "Demo2"},
                        ]
                    });
        </script>
    </body>
</html>
