<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/analysis_sun_lamp_status_report.js"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>太阳能状态报表</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<select name="areaId"></select>
			<select name="roadId"><option value="">请选择道路</option></select>
			<input type="submit" value="查询">
			<div class="excel_btn">导出Excel</div>
			<input type="hidden" name="typeId" value="2"/>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderBy="areaName">区域</th>
					<th orderBy="road_name">道路</th>
					<th orderBy="cname">线路</th>
					<th orderBy="lampnum">灯具编号</th>
					<th orderBy="nb_device">设备ID号</th>
					<th orderBy="nb_code">设备号</th>
					<th orderBy="on_off">开灯状态</th>
					<th orderBy="conn_state">联网状态</th>
					<th orderBy="vol">LED电压(V)</th>
					<th orderBy="ele">LED电流(mA)</th>
					<th orderBy="pvol">光伏电压</th>
					<th orderBy="pele">光伏电流</th>
					<th orderBy="bvol">蓄电池电压</th>
					<th orderBy="temp">温度</th>
					<th orderBy = "sendele">总充电安时</th>
					<th orderBy = "enterele">总放电安时</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
</body>
</html>