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
	<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
	<link rel="stylesheet" type="text/css" href="css/lamp_bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
	<link href="css/bootstrap-slider.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/group_strategy.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/moment-with-locales.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src = "js/bootstrap-slider.js"></script>
	<script type="text/javascript" src="js/lamp_bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap-multiselect.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/group_strategy.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/alert.js" charset="UTF-8"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>分组策略</p>
	<form action="javascript:;" class="search_box" onsubmit="selectAll()">
		<div class="ipt_box">
			<i></i>
			<input type="text" name="name" placeholder="计划名称" />
		</div>
		<input type="submit" value="查询">
		<div class="add_btn"><img src="images/+.png"/> 添加</div>
	</form>
	<div class="table_box">
		<table>
			<tr>
				<th orderby = "cname">计划名称</th>
				<th orderby = "areaName">所属区域</th>
				<th orderby = "group_name">所属分组</th>
				<th orderby = "content_name">计划内容</th>
				<th orderby = "weeks">星期</th>
				<th orderby = "start_date">开始日期</th>
				<th orderby = "end_date">结束日期</th>
				<th orderby = "start_time">开灯时间</th>
				<th orderby = "end_time">关灯时间</th>
				<th orderby = "dimming">调光值</th>
				<th orderby = "real_name">操作人</th>
				<th orderby = "oper_time">操作时间</th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<form action="javascript:;" class="second_form" onsubmit="saveData()">
	<p>详细信息 <span id = "close_scond_wind">X</span></p>
	<input type="hidden" name = "id"/>
	<div class="form_detail_warpper">
		<div class="detail_box">
			<span>计划名称</span>
			<input type="text" name="cName"/>
		</div>
		<div class="detail_box">
			<span>所属区域</span>
			<select name="areaId"></select>
		</div>
		<div class="detail_box">
			<span>所属分组</span>
			<select name="groupId"></select>
		</div>
		<div class="detail_box">
			<span>计划内容</span>
			<select name="planId" class = "planContent"></select>
		</div>
		<div class="detail_box">
			<span>场景名称</span>
			<select name="senceId" disabled = "disabled"></select>
		</div>
		<div class="detail_box">
			<span>星期</span>
			<select name="weeks" class = "selWeeks" multiple="multiple">
				<option value="1">星期一</option>
				<option value="2">星期二</option>
				<option value="3">星期三</option>
				<option value="4">星期四</option>
				<option value="5">星期五</option>
				<option value="6">星期六</option>
				<option value="7">星期天</option>
			</select>
		</div>
		<div class="detail_box">
			<span>开始日期</span>
			<div class="date_box">
				<input type="text" name="startDate" class="form-control startDate"/>
			</div>
		</div>
		<div class="detail_box">
			<span>结束日期</span>
			<div class="date_box">
				<input type="text" name="overDate" class="form-control overDate"/>
			</div>
		</div>
		<div class="detail_box">
			<span>开始时间</span>
			<div class="date_box">
				<input type="text" name="startTime" class="form-control_hour startTime"/>
			</div>
		</div>
		<div class="detail_box">
			<span>结束时间</span>
			<div class="date_box">
				<input type="text" name="endTime" class="form-control_hour overTime"/>
			</div>
		</div>
		<div class="detail_box">
			<span>调光值</span>
			<input type="text" name="dimming" class = "dimming_light" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
		</div>

		<div class="detail_box_submit">
			<input type="submit" value="保存">
		</div>
	</div>
</form>
<div class="judge_win">
	<p>信息<span class = "close_judge_win">X</span></p>
	<h1>确定要删除该条信息吗？</h1>
	<div class="judge_btn">
		<h2 class = "sure_delete_judge_win">确定</h2>
		<h2 class = "close_judge_win">取消</h2>
	</div>
</div>
</body>
</html>