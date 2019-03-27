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
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/stockManage.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>库存管理</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="equipName" placeholder="设备类型名称"/>
			</div>
			<select name="cname">
			</select>
			<input type="submit" value="查询">
			 <div class="add_btn" id="add"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderby = "catName">类别</th>
					<th orderby = "operTime">设备类型</th>
					<th orderby = "stocknum">当前库存量</th>
					<th orderby = "changeNum">最后一次变化量</th>
					<th orderby = "endopertime">最后一次操作</th>
					<th orderby = "node">备注</th>
					<th orderby = "realName">操作者</th>
					<th orderby = "operTime" sort = "desc">操作时间<img src="images/th_ico_down.png"></th>
					<th>操作</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = "controllerPage"></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="javascript:;" method="post" onsubmit="saveData()">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<input type="hidden" name="id">
		<div class="form_box">
			<span>类别</span>
			<select id="catId" name="catId">
			</select>
		</div>
		<div class="form_box">
			<span>设备类型</span>
			<input type="text"  name="equipName" class="equipName"/>
		</div>
		<div class="form_box">
			<span>入库时间</span>
			<input type="text"  name="totime" class="form-control" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
		</div>
		<div class="form_box">
			<span>当前库存量</span>
			<input type="text" class="stocknum" name="stocknum" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')" maxlength="8" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
		</div>
		<div class="form_box">
			<span>备注</span>
			<input type="text" class="node" name="node">
		</div>
		<div class="form_box">
			<input type="submit" value="保存" id="btn" class="alert-btn">
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