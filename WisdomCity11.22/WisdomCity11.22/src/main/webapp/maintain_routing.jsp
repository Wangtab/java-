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
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/maintain_routing.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
	<style>
		.table_win .form_box input[type=text]{width: 232px;}
	</style>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>巡检管理</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="peopleName" placeholder="请输入巡检人员的名称" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>

					<th orderby = "areaName" width="5%">巡检区域</th>
					<th orderby = "road_name" width="6%">巡检路段</th>
					<th orderby = "peopleName" width="6%">巡检人员</th>
					<th orderby = "startime" width="8%">巡检开始时间</th>
					<th orderby = "endtime" width="8%">巡检结束时间</th>
					<th orderby = "checkdescribe" width="8%">巡检备注</th>
					<th orderby = "opername" width="5%">操作者</th>
					<th orderby = "opertime" sort = "desc" width="10%">操作时间 <img src="images/th_ico_down.png"></th>
					<th width="5%">操作</th>
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
			<span>所属区域</span>
			<select name="areaId">
			</select>
		</div>
		<div class="form_box">
			<span>所属道路</span>
			<select name="roadid">
				<option value="">请选择所属道路</option>
			</select>
		</div>

		<div class="form_box">
			<span>人员</span>
			<select name="uid" id="repair_select">
			</select>
		</div>
		<div class="form_box">
			<span>开始时间</span>
			<input type="text" id="startime" name="startime" class="form-control"/>
		</div>
		<div class="form_box">
			<span>结束时间</span>
			<input type="text" id="endtime" name="endtime" class="form-control"/>
		</div>
		<div class="form_box">
			<span>巡检描述</span>
			<input type="text" id="checkdescribe" name="checkdescribe">
		</div>
		<div class="form_box">
			<input type="hidden" id="butId" name="id">
			<input type="submit" value="保存" id="btn" class="alert-btn" style="margin-left: 130px">
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" url = "Maintain/delRoutingDataById">确定</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>		
</body>
</html>