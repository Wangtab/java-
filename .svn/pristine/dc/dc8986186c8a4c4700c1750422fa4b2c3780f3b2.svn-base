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
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<style>
		.win_show_img{width: 400px;height: 400px;background-color:#fff;position: absolute;left: 20px;top:100px;border:14px solid #fff;display: none;z-index: 2000;overflow: hidden;}
		.win_show_img img{width: 400px;height: 400px;}
	</style>
	<script type="text/javascript">
		function promptBox(obj,msg){
			obj.dialog1 = jqueryAlert({
				'content' : msg,
				'closeTime' : alert_common_time,
			});
		}
		$(function(){
			$('.table_box').find('img').hover(function() {
				var _this = $(this);
				var dom_this = _this[0];
				var add_distance = 50;
				var top = _this.offset().top - add_distance * 3;
				var left = _this.offset().left + add_distance;
				$('.win_show_img').find('img').attr('src', _this.attr('src'));
				$('.win_show_img').css({'left':left + 'px','top': top + 'px'});
				$('.win_show_img').show(300);
			}, function() {
				$('.win_show_img').hide(300);
			});
		});
	</script>
</head>
<body>
	<div class="win_show_img" style="display: none;">
		<img src="images/road.jpg">
	</div>
	<h6></h6>
	<div class="work_content_box">
		<p>灯杆类型管理</p>
		<form action="/poleTypeManage.do" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="poleName" value="${poleName}" placeholder="类型名称" />
				<input type="hidden" name="page" value="1">
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>灯杆类型</th>
					<th>类型描述</th>
					<th>灯杆类型图片</th>
					<th>所属组织</th>
					<th>操作者</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${list}" var="item" begin="0">
				<tr>
					<td>${item.typeName}</td>
					<td>${item.typeDes}</td>
					<td><img src="${item.imgUrl}" width = "50" height = "50"/></td>
					<td>${item.orgName}</td>
					<td>${item.operator}</td>
					<td>${item.operTime}</td>
					<td>
						<a title = "修改" class = "edit_button" href="javascript:void(0);">
							<input type="hidden" id="editId" value="${item.id}">
						</a>
						<a title = "删除" class = "delete_button" href="javascript:void(0);">
							<input type="hidden" id="delId" value="${item.id}">
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page">
				<input type="hidden" id="pName" value="${poleName}">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
	<form class="table_win" action="" method = "post">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<div class="form_box">
			<span>灯杆类型名称</span>
			<input type="text" name="typeName" id="typeName">
		</div>
		<div class="form_box">
			<span>类型描述</span>
			<input type="text" name="typeDes" id="typeDes">
		</div>
		<div class="form_box">
			<span>所属组织</span>
			<select name="orgId" id="orgId">
				<option value="">请选择所属组织</option>
			</select>
		</div>
		<div class="form_add_box">
			<input type="file" name="imgUrl" id = "form_add_box_ipt" onchange = "checkPicFile(this)"/>
			<input type="hidden" name="path" id="path" value="">
			<span>添加图片</span>
			<div id = "win_addBox" onclick = "form_add_box_ipt.click()">
				<img id="imgUrl" src="images/add_pic_bg.png"/>
			</div>
		</div>
		<div class="form_box">
			<input type="hidden" name="id" id="pid" value="">
			<input type="button" value="保存" id="butn" style="margin-left: 130px">
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" id="judge">
				<input type="hidden" id="judgeId">
				确定
			</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>		
</body>
<!--form表单提交-->
<script type="text/javascript">
	$("#butn").click(function(){
		var opts = document.getElementById("butn");
		var formData = new FormData($(".table_win")[0]);
		var url = $(".table_win").attr("action");
		$.ajax({
			url: url,
			type: 'POST',
			data: formData,
			contentType: false,
			processData: false,
			success: function(data){
				if(data != "" && data != null){

					$('.table_win').hide(manage_table_time);
					promptBox(opts,data);
					var jtime = alert_common_time+300;
					setTimeout(function(){
						location.href = "/poleTypeManage.do?poleName=&page=1";
					},jtime);
				}
			}
		});
	});
</script>
<!--删除灯杆信息-->
<script type="text/javascript">
	$(".delete_button").click(function(){
		var id = $(this).find("#delId").val();
		$("#judgeId").val(id);
	});
	$(".sure_delete_judge_win").click(function(){
		var del = $(this).find("#judgeId").val();
		var opts = document.getElementById("judge");
		$.post("/deletePoleType.do",{id:del},function(data){
			if(data != "" && data != null){
				promptBox(opts,data);
				var jtime = alert_common_time+300;
				setTimeout(function(){
					location.href = "/poleTypeManage.do?poleName=&page=1";
				},jtime);
			}
		});
	});
</script>
<!--更新灯杆信息-->
<script type="text/javascript">
	$(".edit_button").click(function(){
		var url = "/updatePoleTypeManage.do";
		$(".table_win").attr("action",url);
		var pid = $(this).find("#editId").val();
		$.post("/queryPoleType.do",{id:pid},function(data){
			var pole = eval(data);
			$("#typeName").val(pole[0].typeName);
			$("#typeDes").val(pole[0].typeDes);
			$("#imgUrl").attr("src",pole[0].imgUrl);
			$("#path").val(pole[0].imgUrl);
			$("#pid").val(pid);
			/*所属组织信息*/
			var org = document.getElementById("orgId");
			org.length=1;
			organization(org,pole[0].orgId);
		});
	});
</script>
<!--添加灯杆信息-->
<script type="text/javascript">
	$(".add_btn").click(function(){
		var url = "/addPoleTypeManage.do";
		$(".table_win").attr("action",url);
		$("#typeName").val("");
		$("#typeDes").val("");
		$("#imgUrl").attr("src","images/add_pic_bg.png");
		/*所属组织信息*/
		var org = document.getElementById("orgId");
		org.length=1;
		organization(org,-1);
	});
</script>
<!--所属组织-->
<script type="text/javascript">
	function organization(org,pole){
		$.post("/queryOrganizationAll.do","",function(data){
			var orgArr = eval(data);
			for(var i = 0;i< orgArr.length;i++) {
				org[i + 1] = new Option(orgArr[i].orgName, orgArr[i].id);
				if(orgArr[i].id == pole){
					org[i+1].selected = true;
				}
			}
		});
	}
</script>
<!--js分页-->
<script type="text/javascript">
	$(function(){
		var carNum = ${count};
		var curpage = ${curPage};
		carNum = Math.ceil(carNum/10);
		page({
			id:'controllerPage',
			nowNum: curpage,
			allNum: carNum
		});
		$("#controllerPage").find("a").bind("click",function(){
			var poleName = $("#pName").val();
			var cur = $(this).attr("cur");
			var url ="/poleTypeManage.do?poleName="+poleName+"&page="+cur;
			$(this).attr("href",url);
		});
	});
</script>
</html>