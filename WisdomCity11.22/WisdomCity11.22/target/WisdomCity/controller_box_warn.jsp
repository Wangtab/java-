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
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>路灯控制箱报警</p>
	<form action="" class="search_box">
		<select name="">
			<option value="">请选择区域</option>
		</select>
		<select name="">
			<option value="">请选择道路</option>
		</select>
		<div class="ipt_box">
			<i></i>
			<input type="text" class = "form-control" name="" placeholder="请选择开始时间" />
		</div>
		<div class="ipt_box">
			<i></i>
			<input type="text" class = "form-control" name="" placeholder="请选择结束时间" />
		</div>
		<input type="submit" value="查询">
		<div class="excel_btn">导出Excel</div>
	</form>
	<div class="table_box">
		<table>
			<tr>
				<th>序号</th>
				<th>区域</th>
				<th>道路</th>
				<th>配电箱编号</th>
				<th>所属厂家</th>
				<th>设备型号</th>
				<th>集中器厂家</th>
				<th>集中器型号</th>
				<th>报警级别</th>
				<th>报警内容</th>
				<th>报警时间</th>
				<th>处理</th>
			</tr>
			<tr>
				<td>1</td>
				<td>金闽路区域</td>
				<td>金闽路</td>
				<td>20358</td>
				<td>上海华通</td>
				<td>SITI-00008</td>
				<td>上海华通</td>
				<td>25586623</td>
				<td>高</td>
				<td>柜门开启</td>
				<td>2017-12-12 12:01:01</td>
				<td>已处理</td>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<form class="table_win" action="" method = "post">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<div class="form_box">
		<span>灯具名称</span>
		<input type="text" name="" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>灯具编码</span>
		<input type="text" name="" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>经度</span>
		<input type="text" name="" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>纬度</span>
		<input type="text" name="" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>维修人员</span>
		<select name="">
			<option value="">作为子节点</option>
			<option value="">作为同级点</option>
		</select>
	</div>
	<div class="form_box">
		<span>维修人员</span>
		<input type="radio" name="require_man" id="require_status_yes"><label for="require_status_yes">未维修</label>
		<input type="radio" name="require_man" id="require_status_no"><label for="require_status_no">已维修</label>
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