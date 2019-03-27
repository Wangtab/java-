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
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/md5.js"></script>
	<script type="text/javascript" src = "js/login.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
	<form class="login" action="javascript:;">
		<p>智慧城市公共设施管理平台<span>用户登录</span></p>
		<div class="login_box">
			<i></i>
			<b></b>
			<input type="text" id="username" name="username" value="${requestScope.name}" placeholder = "用户名" />
			<input type="password" id="password" name="password" value="${requestScope.password}" placeholder = "密码"/>
		</div>
		<div class="login_checkbox">
			<p>
				<input type="checkbox" id = "saveLoginInfod" name="saveLogin" value="no" ${requestScope.checked}/> <label for = "saveLoginInfod" >保存账号密码</label>
			</p>
			<p>
				<input type="checkbox" id = "autoLogind" name="autoLogin" value="no" ${requestScope.autoChecked}/><label for = "autoLogind">自动登录</label>
			</p>
		</div>
		<div class="login_sub_btn">
			<input type="button"  value="登录" onclick="login()">
		</div>
	</form>
</body>
</html>