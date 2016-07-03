<%-- 
    Document   : navheader
    Created on : 2016-7-3, 15:14:48
    Author     : coco
--%>
<script>
    window.onload = function ()
    {
        document.getElementById("${param.type}").className='active';
    }
</script>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li id="index"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
        <li id="reports"><a href="#">Reports</a></li>
        <li><a href="#">Analytics</a></li>
        <li><a href="#">Export</a></li>
    </ul>
    <ul class="nav nav-sidebar">
        <li><a href="">Nav item</a></li>
        <li><a href="">Nav item again</a></li>
        <li><a href="">One more nav</a></li>
        <li><a href="">Another nav item</a></li>
        <li><a href="">More navigation</a></li>
    </ul>
    <ul class="nav nav-sidebar">
        <li><a href="">Nav item again</a></li>
        <li><a href="">One more nav</a></li>
        <li><a href="">Another nav item</a></li>
    </ul>
</div>