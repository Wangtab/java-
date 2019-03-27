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
	<title></title>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/platform_setting_initializtion.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>电信IOT订阅平台</p>
	<div class="table_box">
		<table>
			<tr>
				<th orderBy = "telecom_name">订阅名称</th>
				<th orderBy = "telecom_url">订阅接口</th>
				<th orderBy = "telecom_back_url">订阅回调接口</th>
				<th orderBy = "typename">开启订阅平台</th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
</body>
</html>