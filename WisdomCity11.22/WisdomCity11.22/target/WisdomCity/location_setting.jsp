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
	<link rel="stylesheet" type="text/css" href="css/location_setting.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/location_setting.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>

</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>位置配置</p>
	<div class="table_box">
		<table>
			<tr>
				<th orderBy="city_name">所属城市</th>
				<th orderBy="longitude">经度</th>
				<th orderBy="latitude">纬度</th>
				<th orderBy="org_name">所属组织</th>
				<th orderBy="real_name">操作者</th>
				<th orderBy="oper_time">操作时间</th>
				<th>操作</th>
			</tr>
		</table>
	</div>
</div>
<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id"/>
	<div class="form_box">
		<span>城市名称</span>
		<input type="text" name="cityName" id="cityNamed">
		<h1 id = "city_srh_btnd">查询</h1>
	</div>
	<div class="form_box">
		<span>经度</span>
		<input type="text" name="lo" readonly="readonly" id="longitude_ipt">
	</div>
	<div class="form_box">
		<span>纬度</span>
		<input type="text" name="la" readonly="readonly" id="latitude_ipt">
	</div>
	<div class="win_map" id = "win_mapd"></div>
	<div class="form_box">
		<input type="submit" value="保存">
	</div>
</form>
</body>
</html>