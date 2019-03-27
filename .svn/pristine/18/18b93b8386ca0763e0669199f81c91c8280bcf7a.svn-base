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
	<link rel="stylesheet" type="text/css" href="css/backup.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/safe_mysqlbackup.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
<div class="watting_box">
	<img src="images/watting.jpg">
	正在执行中 请勿进行任何操作...
</div>
<div class="watting_bg"></div>
	<h6></h6>
	<div class="work_content_box">
		<p>数据备份</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<div class="ipt_box">
				<i></i>
				<input type="text" class = "form-control" name="" id="startDated" placeholder="请选择开始时间" />
			</div>
			<div class="ipt_box">
				<i></i>
				<input type="text" class = "form-control" name="" id="endDated" placeholder="请选择结束时间" />
			</div>
			<input type="submit" value="查询">
			<div class="add_btn" id="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderby = "sjkname">文件名称</th>
					<th orderby = "sjkaddress">文件地址</th>
					<th orderby = "info">备注</th>
					<th orderby = "addtime">创建时间</th>
					<th orderby = "real_name">操作者</th>
					<th orderby = "cztime" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
					<th>操作</th>
				</tr>

			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
<form class="table_win" id="ad"  action="javascript:;" onsubmit="saveData()" method = "post">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id" id="eed" />
	<div class="form_box">
		<span>备注</span>
		<input type="text" name="info" />
	</div>
	<div class="form_box">
		<input type="submit" value="数据备份">
	</div>
</form>
<div class="judge_win">
  <p>信息<span class = "close_judge_win">X</span></p>
  <h1>确定要删除该条信息吗？</h1>
  <div class="judge_btn">
    <h2 class = "sure_delete_judge_win" url = "safe/delDataBaseDataById">确定</h2>
    <h2 class = "close_judge_win">取消</h2>
  </div>
</div>

<div class="reset_win">
  <p>信息<span class = "close_judge_win">X</span></p>
  <h1>确定要恢复此时间的数据吗？</h1>
  <div class="judge_btn">
    <h2 class = "sure_reset_judge_win">确定</h2>
    <h2 class = "close_judge_win">取消</h2>
  </div>
</div>				
</body>
</html>