<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<link rel="stylesheet" type="text/css" href="css/buildinfo.css">
	<script type="text/javascript" src="js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/maintain_build.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>施工信息管理</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="orderNum" placeholder="请输入要查询的编号"  />
			</div>
			<div class="ipt_box">
				<i></i>
				<input type="text" name="startDate" class = "form-control" placeholder="请选择开始时间" />
			</div>
			<div class="ipt_box">
				<i></i>
				<input type="text" name="endDate" class = "form-control"  placeholder="请选择结束时间" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
			 <div class="excel_btn">导出数据</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderby ="ordernum">编号</th>
					<th orderby ="areaName">区域名称</th>
					<th orderby ="road_name">道路名称</th>
					<th orderby ="modelnum">设备型号</th>
					<th orderby ="devicenum">设备编号</th>
					<th orderby ="name">维修人员</th>
					<th orderby ="number">工号</th>
					<th orderby ="buildname">施工标准</th>
					<th orderby ="repairtype">维护类型</th>
					<th orderby ="deal_result">处理结果</th>
					<th orderby ="buildtime">施工时间</th>
					<th orderby ="real_name">操作者</th>
					<th orderby ="opertime">操作时间</th>
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
		<input type="hidden" name="id">
		<div class="form_detail_warpper">
			<div class="detail_box">
				<span>编号</span>
				<input type="text" name="ordernum" readonly = "readonly" id="OrderNum">
			</div>
			<div class="detail_box">
				<span>所属区域</span>
				<select name="areaid"></select>
			</div>
			<div class="detail_box">
				<span>所属道路</span>
				<select name="roadid"></select>
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
				<select name="repairmanid" id="repairMan"></select>
			</div>
			<div class="detail_box">
				<span>工&nbsp;&nbsp;号</span>
				<input type="text" name="number" readonly="readonly">
			</div>
			<div class="detail_box">
				<span>施工标准</span>
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
				<textarea name="node" allow_null = "ok"></textarea>
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
			<h2 class = "sure_delete_judge_win" url = "Maintain/deleteBuildingShowData">确定</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>
</body>
</html>