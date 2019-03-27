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
	<title>组织管理</title>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/organization_manage.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/safe_organization_manage.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>组织管理</p>
	<div class="tree_warpper">
		<div class="left_content_tree"></div>
		<div class="right_data">
			<form action="" class="search_box">
				<div class="ipt_box">
					<i></i>
					<input type="text" name="" placeholder="组织名称" />
				</div>
				<input type="submit" value="查询">
				<div class="add_btn"><img src="images/+.png"/> 添加</div>
			</form>
			<div class="table_box">
				<table>
					<tr>
						<th orderby = "org_name">组织名称</th>
						<th orderby = "org_des">组织描述</th>
						<th orderby = "real_name">操作人</th>
						<th orderby = "operTime" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
						<th>操作</th>
					</tr>
				</table>
				<div class="page">
					<div class="pagebox" id = 'controllerPage'></div>
				</div>
			</div>
		</div>
	</div>
</div>
<form class="table_win" action="javascript:;" method = "post" onsubmit = "saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id"/>
	<input type="hidden" name="pid" value="0"/>
	<div class="form_box">
		<span>组织名称</span>
		<input type="text" name="orgName">
	</div>
	<div class="form_box">
		<span>组织描述</span>
		<input type="text" name="orgDes" allow_null = "ok">
	</div>
	<div class="form_box">
		<span>上级组织</span>
		<input type="text" value="上海管理组织" name = "showParentName" readonly = "readonly" allow_null = "ok" />
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