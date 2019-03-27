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
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/proUntil.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/login_log.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>登录日志</p>
		<form action="/operationLoginLog.do" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="stratDate" class = "form-control" placeholder="请输入开始时间" />
			</div>
			<div class="ipt_box">
				<i></i>
				<input type="text" name="endDate" class = "form-control" placeholder="请输入结束时间" />
			</div>
			<input type="hidden" name="page" value="1">
			<input type="submit" value="查询">
		</form>
		<div class="table_box">
			<table>
				<tr>
					<%--<th>编号</th>--%>
					<th orderby ="log_name">登录用户名</th>
					<th orderby ="log_realname">登录者姓名</th>
					<th orderby ="ip">IP地址</th>
					<th orderby ="log_date">登录时间</th>

				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
</body>

</html>