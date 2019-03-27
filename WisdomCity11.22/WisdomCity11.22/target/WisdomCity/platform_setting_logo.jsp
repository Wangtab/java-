<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
	<title></title>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src="js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/platform_setting_logo.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/jquery-form.js"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>logo配置</p>
		<div class="table_box">
			<table>
				<tr>
					<th orderBy="imager">logo图片</th>
					<th orderBy="name">文字描述</th>
					<th orderBy="org_name">所属组织</th>
					<th orderBy="real_name">操作者</th>
					<th orderBy="uptime">修改时间</th>
					<th>操作</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id="controllerPage"></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()" enctype='multipart/form-data'>
		<p>详细信息<span id = "close_table_win">X</span></p>
		<input type="hidden" name="id">
		<div class="form_box">
			<span>文字描述</span>
			<input type="text" name="name" id="lname">
		</div>
		<div class="form_add_box">
			<input type="file" id = "form_add_box_ipt" name="files" onchange = "checkPicFile(this)"/>
			<span>logo图片</span>
			<div id = "win_addBox" onclick = "form_add_box_ipt.click()">
				<img src="images/add_pic_bg.png" id="ims"/>
			</div>
		</div>
		<div class="form_box">
			<input type="submit" value="保存">
		</div>
	</form>
</body>

</html>