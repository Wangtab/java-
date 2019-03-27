<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
	<link rel="stylesheet" type="text/css" href="css/lamp_bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<link rel="stylesheet" type="text/css" href="css/light_leaveStrategy.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/safe_mysql_strategy.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/moment-with-locales.js"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/lamp_bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<style type="text/css">
		.table_win .form_box select{width:220px}
		.form_times_box{float:left;position: relative}
	</style>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>备份策略</p>
	<form action="javascript:;" class="search_box" onsubmit="selectAll()">
		<input type="hidden" name="id" />
		<div class="ipt_box">
			<i></i>
			<input type="text" class = "form-control" name="startDate" placeholder="请选择开始时间" />
		</div>
		<div class="ipt_box">
			<i></i>
			<input type="text" class = "form-control" name="endDate" placeholder="请选择结束时间" />
		</div>
		<input type="submit" value="查询">
		<div class="add_btn" id="add_btn"><img src="images/+.png"/> 添加</div>
	</form>
	<div class="table_box">
		<table>
			<tr>
				<th orderby = "clname">策略名称</th>
				<th orderby = "zxname">执行方式</th>
				<th orderby = "zxtime">执行时间</th>
				<th orderby = "addtime">创建时间</th>
				<th orderby = "real_name">操作者</th>
				<th orderby = "uptime" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<form class="table_win" id="tbu" action="javascript:;" method = "post" onsubmit="saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id" />
	<div class="form_box">
		<span>策略名称</span>
		<input type="text" name="clname" />
	</div>
	<div class="form_box">
		<span>执行方式</span>
		<select name="zxname">
			<option value="1">每月 [每月1号]</option>
			<option value="2">每周 [每周周一]</option>
			<option value="3">每天</option>
		</select>
	</div>
	<div class="form_box">
		<span>执行时间</span>
		<div class = "form_times_box">
			<input type="text" name="zxtime" class="form-control_hour"/>
		</div>
	</div>
	<div class="form_box">
		<input type="submit" value="保存">
	</div>
</form>
<div class="judge_win">
	<p>信息<span class = "close_judge_win">X</span></p>
	<h1>确定要删除该条信息吗？</h1>
	<div class="judge_btn">
		<h2 class = "sure_delete_judge_win" url = "safe/deleteDataBaseStrategyById">确定</h2>
		<h2 class = "close_judge_win">取消</h2>
	</div>
</div>
</body>
</html>