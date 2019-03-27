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
	<link rel="stylesheet" type="text/css" href="css/lamp_warn.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/lampwarn.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>灯具报警</p>
	<form action="javascript:;" class="search_box" onsubmit="selectAll()">
		<select name = "areaId">
			<option value="">请选择区域</option>
		</select>
		<select name = "roadId">
			<option value="">请选择道路</option>
		</select>
		<select name = "lineId">
			<option value="">请选择线路</option>
		</select>
		<select name="lampId" id="selLight">
			<option value="">请选择单灯</option>
		</select>
		<div class="ipt_box">
			<i></i>
			<input type="text" class = "form-control"  id = "startDated" placeholder="请选择开始时间" />
		</div>
		<div class="ipt_box">
			<i></i>
			<input type="text" class = "form-control" id = "endDated"  placeholder="请选择结束时间" />
		</div>
		<input type="submit" value="查询">
		<div class="excel_btn" id="excel_btnd">导出Excel</div>
	</form>
	<div class="table_box">
		<table id="warn_tabled">
			<tr>
				<th orderby = "areaName">区域</th>
				<th orderby = "roadName">道路</th>
				<th orderby = "linename">线路</th>
				<th orderby = "factoryname">灯具厂家</th>
				<th orderby = "lamptypename">灯具类型</th>
				<th orderby = "kindname" >控制器类型</th>
				<th orderby = "levelname" >报警级别</th>
				<th orderby = "warn_name" >报警内容</th>
				<th orderby = "operTime" sort = "desc" >报警时间 <img src="images/th_ico_down.png"></th>
				<th orderby = "deal_flag" >处理情况</th>
				<th orderby = "ordernum">工单编号</th>
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
	<input type="hidden" name="nb_device" id="nb_deviced"/>
	<div class="form_detail_warpper">
		<div class="detail_box">
			<span>编号</span>
			<input type="text" name="ordernum" readonly = "readonly" id="OrderNum">
		</div>
		<div class="detail_box">
			<span>所属区域</span>
			<select name="areaid" id="areaIdSel2">
				<option value="">请选择区域</option>
			</select>
		</div>
		<div class="detail_box">
			<span>所属道路</span>
			<select name="roadid" id="selRoad2">
				<option value="">请选择道路</option>
			</select>
		</div>

		<div class="detail_box">
			<span>设备型号</span>
			<input type="text" name="modelnum">
		</div>
		<div class="detail_box">
			<span>设备编号</span>
			<input type="text" name="devicenum">
		</div>
		<div class="detail_box">
			<span>维修人员</span>
			<select name="repairmanid"></select>
		</div>
		<div class="detail_box">
			<span>工&nbsp;&nbsp;号</span>
			<input type="text" name="" readonly="readonly">
		</div>
		<div class="detail_box">
			<span>设备类型</span>
			<select name="buildtypeid" id="buildtypeid"></select>
		</div>
		<div class="detail_box">
			<span>维护类型</span>
			<select name="repairtype">
				<option value="0">维修</option>
				<option value="1">更换</option>
			</select>
		</div>
		<div class="detail_box">
			<span>处理结果</span>
			<select name="deal_result">
				<option value="0">在维</option>
				<option value="1">完成</option>
			</select>
		</div>
		<div class="detail_box">
			<span>施工时间</span>
			<input type="text" name="buildtime" class = "form-control"/>
		</div>
		<div class="detail_text">
			<span>说明</span>
			<textarea name="node"></textarea>
		</div>
		<div class="detail_box_submit">
			<input type="submit" value="保存">
		</div>
	</div>
</form>
</body>
</html>