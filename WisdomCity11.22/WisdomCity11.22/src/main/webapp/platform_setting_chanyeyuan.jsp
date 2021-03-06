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
	<script type="text/javascript" src = "js/platform_setting_chanyeyuan.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>产业院IOT接口平台设置</p>
	<div class="table_box">
		<table>
			<tr>
				<th orderBy = "protocol_type">协议名称</th>
				<th orderBy = "iot_address">连接地址</th>
				<th orderBy = "real_name">操作者</th>
				<th orderBy = "oper_time">操作时间 <img src="images/th_ico_down.png"></th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id"/>
	<div class="form_box">
		<span>协议名称</span>
		<input type="text" readonly = "readonly" name="protocol" value = "UDP协议"/>
	</div>
	<div class="form_box">
		<span>连接地址</span>
		<input type="text" name="address" />
	</div>
	<div class="form_box">
		<input type="submit" value="保存">
	</div>
</form>
</body>
</html>