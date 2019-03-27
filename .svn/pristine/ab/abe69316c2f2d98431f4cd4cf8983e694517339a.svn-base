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
	<link href="css/bootstrap.min1.css" rel="stylesheet">
	<link href="css/bootstrap-slider.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/roadLightSwitch.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/bootstrap-slider.js"></script>
	<style>
		.slider.slider-horizontal{width: 232px;}
		.slider .tooltip.top{margin-top:30px;margin-left: -20px}
		.tooltip.top .tooltip-arrow{top:0px;border-width:0px 5px 5px 5px;border-bottom-color: #000;}
		.table_win .form_box{margin-top: 16px;}
	</style>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>道路照度调光</p>
		<form action="javascript:;" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="road" placeholder="道路名称" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>道路名称</th>
					<th>灯具类型</th>
					<th>调光值</th>
					<th>照度值</th>
					<th>安装人</th>
					<th>安装时间</th>
					<th>操作者</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${rllist}" var="roadlight">
					<tr>
						<td>${roadlight.id}</td>
						<td>${roadlight.roadName}</td>
						<td>${roadlight.lampType}</td>
						<td>${roadlight.dimming}</td>
						<td>${roadlight.lighting}</td>
						<td>${roadlight.install_peo}</td>
						<td>${roadlight.install_time}</td>
						<td>${roadlight.user_name}</td>
						<td>${roadlight.oper_time}</td>
						<td>
							<a title = "修改" class = "edit_button" href="javascript:void(0);"></a>
							<a title = "删除" class = "delete_button" href="javascript:void(0);"></a>
						</td>
					</tr>
				</c:forEach>

			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="" method = "post">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<div class="form_box">
			<span>道路名称</span>
			<select id="roadName">
				<option value="道路一">请选择所属道路</option>
				<option value="道路一">道路1</option>
				<option value="道路一">道路2</option>
			</select>
		</div>
		<div class="form_box">
			<span>灯具类型</span>
			<select name="" id="lampType">
				<option value="灯具类型1">请选择灯具类型</option>
				<option value="灯具类型1">灯具类型1</option>
				<option value="灯具类型1">灯具类型2</option>
			</select>
		</div>
		<div class="form_box">
			<span>调光值</span>
			<input id="single_light_dimming" name="dimming" class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
		</div>
		<div class="form_box">
			<span>照度值</span>
			<input id="single_light" name="lighting" name="dimming" class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
		</div>
		<div class="form_box">
			<input type="submit" value="保存">
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