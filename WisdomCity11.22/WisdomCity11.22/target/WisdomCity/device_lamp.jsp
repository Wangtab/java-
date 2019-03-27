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
	<script type="text/javascript" src = "js/alert.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/device_lamp.js"></script>
	<script type="text/javascript" src = "js/xlsx.full.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>灯具管理</p>
		<form  action="javascript:;" class="search_box" onsubmit="selectAll()">
			<select name="areaId"></select>
			<select name="roadId"><option value = "">请选择道路</option></select>
			<select name="lineId"><option value = "">请选择线路</option></select>
			<div class="ipt_box">
				<i></i>
				<input type="text" name="poleCode" placeholder="灯杆编号"/>
			</div>
			<input type="submit" value="查询">
			<div class="add_btn" id="add_btn"><img src="images/+.png"/> 添加</div>
			<div class="import_btn"   onclick = "lampDataImp.click()">批量导入</div>
			<div class="import_btn" onclick="downModel()">下载模板</div>
			<div class="import_btn" onclick="">批量升级</div>
			<input type="file" name="" id="lampDataImp" style="display: none">
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderby = "areaName">所属区域</th>
					<th orderby = "road_name">所属道路</th>
					<th orderby = "cname">所属线路</th>
					<th orderby = "name">配电箱编号</th>
					<th orderby = "dbcircuit">配电箱回路</th>
					<th orderby = "poleCode">灯杆编号</th>
					<th orderby = "lampModel">灯具型号</th>
					<th orderby = "lampnum">灯具编号</th>
					<th orderby = "nb_code">控制器编号</th>
					<th orderby = "power">额定功率</th>
					<th orderby = "lampFactory">灯具厂家</th>
					<th orderby = "lo">经度</th>
					<th orderby = "la">纬度</th>
					<th orderby = "real_name">操作者</th>
					<th orderby = "opertime">操作时间</th>
					<th>操作</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
		<div class="table_map" id = "main_table_mapd"></div>
	</div>
	<form class="second_form" action="javascript:;" method = "post" onsubmit="saveData()">
		<p>详细信息<span id = "close_scond_wind">X</span></p>
		<input type="hidden" name="id"  value="">
		<div class="detail_box">
			<span>所属区域</span>
			<select name="areaId"></select>
		</div>
		<div class="detail_box">
			<span>所属道路</span>
			<select name="roadId"><option value = ''>请选择道路</option></select>
		</div>
		<div class="detail_box">
			<span>所属线路</span>
			<select name="roadlineId"><option value =''>请选择线路</option></select>
		</div>
		<div class="detail_box">
			<span>灯具编号</span>
			<input type="text" name="lampnum">
		</div>
		<div class="detail_box">
			<span>灯具类型</span>
			<select name="typeSel">
				<option value = '1'>普通路灯</option>
				<option value = '2'>太阳能路灯</option>
			</select>
		</div>
		<div class="detail_box">
			<span>灯具型号</span>
			<select name="typeId"></select>
		</div>
		<div class="detail_box">
			<span>配电箱编号</span>
			<select name="pdId">
				<option value ='0'>请选择配电箱编号</option>
			</select>
		</div>
		<div class="detail_box">
			<span>配电箱回路</span>
			<select name="dbcircuit" id="dbcircuit">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
			</select>
		</div>
		<div class="detail_box">
			<span>灯杆编号</span>
			<input type="text" name="poleCode">
		</div>
		<div class="detail_box">
			<span>控制器</span>
			<select name="controllerId"></select>
		</div>
		<div class="detail_box">
			<span>经度</span>
			<input type="text" name="lo" readonly="readonly" id="longitude_ipt">
		</div>
		<div class="detail_box">
			<span>纬度</span>
			<input type="text" name="la" readonly="readonly" id="latitude_ipt">
		</div>
		<div class="win_map" id = "win_mapd"></div>
		<div class="detail_box_submit">

			<input type="submit" value="保存" i>
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" url = "delLampManageDataById">确定</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>		
</body>
</html>