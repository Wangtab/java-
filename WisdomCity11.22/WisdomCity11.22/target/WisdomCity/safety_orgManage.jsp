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
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/safe_organization_manage.js"></script>
</head>
<body>

	<h6></h6>
	<div class="work_content_box">
		<p>组织管理</p>
		<form action="/orgQuery" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="areaname" placeholder="部门组织名称" />
			</div>
			<input type="submit" value="查询">
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>组织ID</th>
					<th>组织名称</th>
					<th>操作</th>
				</tr>
				<c:forEach var="org" items="${tareas}">
					<tr>
						<td>${org.id}</td>
						<td>${org.areaname}</td>
						<td>
							<a title = "添加" class = "add_button" href="javascript:void(0);"></a>
							<a title = "修改" class = "edit_button" href="javascript:void(0);"></a>
							<a title = "删除" class = "delete_button" href="javascript:;"></a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="" method = "post" onsubmit = "check_form()">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<div class="form_box">
			<span>节点位置</span>
			<select name="">
				<option value="">作为子节点</option>
				<option value="">作为同级点</option>
			</select>
		</div>
		<div class="form_box">
			<span>组织名称</span>
			<input type="text" name="" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
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