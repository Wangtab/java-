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
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/platform_setting_telecom.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>电信IOT平台</p>
	<form action="javascript:;" class="search_box" onsubmit="selectAll()">
		<select name="orgId"></select>
		<input type="submit" value="查询">
		<div class="add_btn" id="add"><img src="images/+.png"/> 添加</div>
	</form>
	<div class="table_box">
		<table>
			<tr>
				<th orderBy = "orgName">组织平台</th>
				<th orderBy = "iotType">平台设置</th>
				<th orderBy = "iot_address">接口地址</th>
				<th orderBy = "appid">APPID</th>
				<th orderBy = "secret">SECRET</th>
				<th orderBy = "service_address">服务器地址</th>
				<th orderBy = "real_name">操作人</th>
				<th orderBy = "oper_time">操作时间</th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<iframe src="platform_setting_chanyeyuan.jsp"  width="100%" height= "1000px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id"/>
	<input type="hidden" id="oId" name="oId">
	<input type="hidden" id="typeId">
	<div class="form_box">
		<span>组织平台</span>
		<select name="orgId"></select>
	</div>
	<div class="form_box">
		<span>平台设置</span>
		<select name="iotType"></select>
	</div>
	<div class="form_box">
		<span>电信平台地址</span>
		<input type="text"  name="iotAddress"/>
	</div>
	<div class="form_box">
		<span>APPID</span>
		<input type="text" name="appId"/>
	</div>
	<div class="form_box">
		<span>SECRET</span>
		<input type="text" name="secret"/>
	</div>
	<div class="form_box">
		<span>服务器地址</span>
		<input type="text" name="serviceAddress"/>
	</div>
	<div class="form_box">
		<input type="submit" value="保存">
	</div>
</form>
<div class="judge_win">
	<p>信息<span class = "close_judge_win">X</span></p>
	<h1>确定要删除该条信息吗？</h1>
	<div class="judge_btn">
		<h2 class = "sure_delete_judge_win" url = "PlatFomSetting/delPlatFomSettingById">确定</h2>
		<h2 class = "close_judge_win">取消</h2>
	</div>
</div>
</body>
</html>