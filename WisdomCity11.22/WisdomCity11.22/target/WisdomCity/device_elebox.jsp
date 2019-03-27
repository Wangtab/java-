<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/device_elebox.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>配电箱管理</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="name" placeholder="配电箱号" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderby = "areaName" width = "12%">所属区域</th>
					<th orderby = "road_name" width = "12%">所属道路</th>
					<th orderby = "name" width = "12%">配电箱号</th>
					<th orderby = "longitude" width = "12%">经度</th>
					<th orderby = "latitude" width = "12%">纬度</th>
					<th orderby = "real_name" width = "12%">操作者</th>
					<th orderby = "oper_time" width = "12%">操作时间 <img src="images/th_ico_down.png"></th>
					<th>操作</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
		<div class="main_table_map" id = "table_mapd"></div>
	</div>
	<form class="table_win" action="javascript:;" onsubmit="saveData()">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<input type="hidden" name="id" id="eleBox">
		<div class="form_box">
			<span>配电箱编号</span>
			<input type="text" name="name">
		</div>
		<div class="form_box">
			<span>所属区域</span>
			<select name="areaId" id="areaId"></select>
		</div>
		<div class="form_box">
			<span>所属道路</span>
			<select name="roadId" id="roadId"><option value = ''>请选择所属道路</option></select>
		</div>
		<div class="form_box">
			<span>经度</span>
			<input type="text" name="longitude" readonly="readonly" id="longitude_ipt">
		</div>
		<div class="form_box">
			<span>纬度</span>
			<input type="text" name="latitude" readonly="readonly" id="latitude_ipt">
		</div>
		<div class="win_map" id = "win_mapd"></div>
		<div class="form_box">
			<input type="submit" value="保存">
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" url = "deleteEleBoxDataById">确定</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>		
</body>
</html>