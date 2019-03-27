<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src="js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/function.js"></script>
	<script type="text/javascript" src = "js/maintain_normal_data_log.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p></p>
	<form action="javascript:;" class="search_box" onsubmit="selectAll()">
		<select name = "areaId"></select>
		<select name = "roadId"><option value="">请选择道路</option></select>
		<select name = "lineId"><option value="">请选择线路</option></select>
		<select name = "lampId"><option value="">请选择单灯</option></select>
		<input type="hidden" name="typeId" value="1"/>
		<div class="ipt_box">
			<i></i>
			<input type="text" name="startDate" class = "form-control" placeholder="请输入开始时间" />
		</div>
		<div class="ipt_box">
			<i></i>
			<input type="text" name="endDate" class = "form-control" placeholder="请输入结束时间" />
		</div>
		<input type="submit" value="查询">
		<div class="excel_btn">导出Excel</div>
	</form>
	<div class="table_box">
		<table>
			<tr>
				<th orderby = "areaName">区域</th>
				<th orderby = "road_name">道路</th>
				<th orderby = "cname">线路</th>
				<th orderby = "poleCode">灯杆编号</th>
				<th orderby = "nb_device">SIM卡号</th>
				<th orderby = "nb_code">设备号</th>
				<th orderby = "on_off">开灯状态</th>
				<th orderby = "conn_state">连接状态</th>
				<th orderby = "vol">电压</th>
				<th orderby = "ele">电流</th>
				<th orderby = "power">功率</th>
				<th orderby = "dimming">调光值</th>
				<th orderby = "record_time" sort="desc">上传时间 <img src="images/th_ico_down.png"></th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<script>
	$(function(){
		proUntil.commonSelect("Maintain/getOperationLogData",param,null,null,null,"no_btn");
	})
</script>
</body>
</html>