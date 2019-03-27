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
	<link rel="stylesheet" type="text/css" href="css/user_manage.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/user_manage.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
	<p>用户管理</p>
	<form action="javascript:;" class="search_box" onsubmit="selectAll()">
		<div class="ipt_box">
			<i></i>
			<input type="text" name="userName" placeholder="用户名查询" />
		</div>
		<div class="ipt_box">
			<i></i>
			<input type="text" name="realName" placeholder="用户名称查询" />
		</div>
		<select id="srh_org">
		</select>

		<input type="submit" value="查询">
		<div class="add_btn"><img src="images/+.png"/> 添加</div>
	</form>
	<div class="table_box">
		<table>
			<tr>
				<th orderBy="operTime">序号</th>
				<th orderBy="user_name">用户名</th>
				<th orderBy="real_name">姓名</th>
				<th orderBy="role_name">角色</th>
				<th orderBy="org_name">所属组织</th>
				<th orderBy="oper_name">操作人</th>
				<th orderBy="operTime">操作时间</th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<input type="hidden" name="id"/>
	<div class="form_box">
		<span>用户名</span>
		<input type="text" id="userName" name="userName" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>姓名</span>
		<input type="text" name="realName" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
	</div>
	<div class="form_box">
		<span>电话</span>
		<input type="text" name="telNum" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
	</div>
	<div class="form_box">
		<span>角色</span>
		<select name="authId" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"></select>
	</div>
	<div class="form_box">
		<span>所属组织</span>
		<select id="orgId" name="orgId" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"></select>
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
<form action = "javascript:;" method = "post" class="update_pwd" onsubmit = "changePwd()">
	<p>修改密码<span id = "close_update_pwd_win">X</span></p>
	<input type="hidden" name="id"/>
	<div class="form_box">
		<span>原密码</span>
		<input type="password" id = "son_old_pwd" name="" placeholder = "初始密码为：123456" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>新密码</span>
		<input type="password" id = "son_new_pwd" name=""  required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<span>密码确认</span>
		<input type="password" id = "son_repeat_pwd" name=""  required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
	</div>
	<div class="form_box">
		<input type="submit" value="保存">
	</div>
</form>
</body>
</html>