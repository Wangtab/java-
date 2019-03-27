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
	<script type="text/javascript">
		function addPoletype(){
			$.ajax({
				type:"post",
				dataType:"text",
				url:"/addPoletype",
				data:{"ptName":$("#ptName").val(),"ptDes":$("#ptDes").val(),"ptOrg":$("#ptOrg").val(),"userId":"${loginUser.id}"},
				success: function (msg) {
					if(msg==1){
						console.log("msg:"+msg);
						document.location.reload();
					}else{
						window.parent.location.href = "login.jsp";
					}

				}
			});
		}
	</script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>灯杆类型管理</p>
		<form action="/poletypeQuery" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="PTname" required="required" placeholder="类型名称" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>灯杆编号</th>
					<th>类型名称</th>
					<th>类型描述</th>
					<th>灯杆类型图片</th>
					<th>所属组织</th>
					<th>操作者</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${poletypes}" var="poletype">
					<tr>
						<td>${poletype.id}</td>
						<td>${poletype.type_name}</td>
						<td>${poletype.type_des}</td>
						<td>暂无</td>
						<td>${poletype.org}</td>
						<td>${poletype.user_name}</td>
						<td>${poletype.oper_time}</td>
						<td>
							<a title = "修改" class = "edit_button" href="javascript:void(0);"></a>
							<a title = "删除" class = "delete_button" href="javascript:void(0);"></a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="" method = "post">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<div class="form_box">
			<span>灯杆类型名称</span>
			<input type="text" name="" id="ptName">
		</div>
		<div class="form_box">
			<span>类型描述</span>
			<input type="text" name="" id="ptDes">
		</div>
		<div class="form_box">
			<span>所属组织</span>
			<select name="" id="ptOrg">
				<option value="产研院">请选择所属组织</option>
				<option value="产研院">组织1</option>
				<option value="产研院">组织2</option>
			</select>
		</div>
		<div class="form_add_box">
			<input type="file" id = "form_add_box_ipt" onchange = "checkPicFile(this)"/>
			<span>添加图片</span>
			<div id = "win_addBox" onclick = "form_add_box_ipt.click()">
				<img src="images/add_pic_bg.png"/>
			</div>
		</div>
		<div class="form_box">
			<input type="submit" value="保存" onclick="javascript:addPoletype()">
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