<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="shortcut icon" href="images/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="css/common.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src = "js/common.js"></script>
        <script type="text/javascript" src = "js/menu.js"></script>
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
        <script>
            var url_to = "<%=request.getAttribute("urlTo")%>";
            $(function() {
                if(!proUntil.strIsEmpty(url_to) && "null" != url_to)
                $('#main_iframe').attr('src', url_to);
            });
        </script>
</head>
<body id = "bodyd">
<div id="area_full_screen"></div>
<div class="top">
    <div class="top_left">
        <a href=""><img src="<%=request.getSession().getAttribute("logoPic")%>" title = "<%=request.getSession().getAttribute("logoName")%>"/></a>
        <h1><%=request.getSession().getAttribute("logoName")%></h1>
        <%--<h1 style="margin-left: 120px;font-size:26px;margin-top: -20px; ">SITI IN CITY</h1>--%>
    </div>
    <div class="top_right">
        <div class="time_warpper">
            <h1 id = "now_date"></h1>
            <p>&nbsp;&nbsp; <%=request.getSession().getAttribute("temp_detail")%>
                &nbsp;&nbsp; <img class = "common_weather_pic" src="<%=request.getSession().getAttribute("weatherPic")%>">
                <%=request.getSession().getAttribute("lowTemp")%>-<%=request.getSession().getAttribute("heightTemp")%> ℃</p>
        </div>
        <div class="user_function">
            <div class = "top_user_name">
               <p>${loginUser.realName}<i></i></p>
                <ul>
                    <li><a href="/loginOut.do">退出系统</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="location">
    <div class="loc_box">
        <i></i>
        <a href="javascript:void(0);" to_url = "mainPage.jsp">平台首页</a> &gt;&gt;
        <a href="javascript:void(0);" to_url = "monitcenter.jsp">智慧路灯</a> &gt;&gt;
        <a href="javascript:void(0);" to_url = "monitcenter.jsp">监控中心</a> &gt;&gt;
        <a href="javascript:void(0);" to_url = "monitcenter.jsp" class = "now_location">监控中心</a>
        <span id = "fullScreen_btn">全屏显示</span>
        <a href="screendata.jsp" class = "data_all_look">实时监控数据中心</a>
    </div>

</div>
<div class="content">
    <div class="menu"></div>
    <div class="work_content">
        <iframe id = "main_iframe" width = "100%" height = "100%" src="monitcenter.jsp" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>