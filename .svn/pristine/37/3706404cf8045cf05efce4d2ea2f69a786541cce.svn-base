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
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src="js/maintain_repair.js"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>维修人员管理</p>
		<form action="javascript:;" class="search_box" onsubmit="selectAll()">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="name" placeholder="姓名" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th orderby="name" width="10%">姓名</th>
					<th orderby="sex" width="5%">性别</th>
					<th orderby="number" width="10%">工号</th>
					<th orderby="numjob" width="10%">工种</th>
					<th orderby="tel" width="10%">电话</th>
					<th orderby ="orgname" width="10%">所属组织管理</th>
					<th orderby="address" width="10%">地址</th>
					<th orderby ="real_name" width="5%">操作人</th>
					<th orderby ="oper_time" sort="desc" width="20%">操作时间 <img src="images/th_ico_down.png"></th>
					<th width="10%">操作</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="javascript:;" onsubmit="saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
		<input type="hidden" name="id">
		<div class="form_box">
			<span>姓名</span>
			<input type="text" name="name">
		</div>
		<div class="form_box">
			<span>工号</span>
			<input type="text" name="number">
		</div>
		<div class="form_box">
			<span>工种</span>
			<input type="text" name="numjob">
		</div>

		<div class="form_box">
			<span>性别</span>
			<input type="radio" name="sex" value="0" required="required" checked = "checked"  id="repaire_man"><label for="repaire_man">男</label>
			<input type="radio" name="sex" value="1" required="required"  id="repaire_woman"><label for="repaire_woman">女</label>
		</div>
		<div class="form_box">
			<span>手机</span>
			<input type="text" name="tel"/>
		</div>
		<div class="form_box">
			<span>地址</span>
			<input type="text" name="address">
		</div>
		<div class="form_box">
			<span>所属组织</span>
			<input type="text" name="orgname" readonly="readonly">
		</div>
		<div class="form_box">
			<input type="submit" value="保存">
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" url = "Maintain/deleteRepById">确定</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>
</body>
</html>