<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>智慧城市公共设施管理平台</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/mainPage.js"></script>
</head>
<body>
<div class="top">
	<div class="top_left">
		<a href=""><img src="<%=request.getSession().getAttribute("logoPic")%>" title = "<%=request.getSession().getAttribute("logoName")%>"/></a>
		<h1><%=request.getSession().getAttribute("logoName")%></h1>
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
					<li><a href="/loginOut.do">注销</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="location">
	<div class="loc_box">
		<i></i>
		<a href="javascript:void(0);">平台首页</a>
	</div>
</div>
<div class="content">
	<div class="models">
		<a href="screendata.jsp"><img src="images/1.png" alt=""></a>
		<a href="javascript:void(0);"><img src="images/2.png" alt=""></a>
		<a href="javascript:void(0);"><img src="images/3.png" alt=""></a>
		<a href="javascript:void(0);"><img src="images/4.png" alt=""></a>
		<a href="javascript:void(0);"><img src="images/5.png" alt=""></a>
		<a href="javascript:void(0);"><img src="images/6.png" alt=""></a>
	</div>
</div>
<div class="foot">
	<p>
		<span>服务电话：021-12345678</span>
		<strong>版本号V2.0&nbsp;&nbsp;上海市产业技术研究院</strong>
	</p>
</div>
</body>
</html>