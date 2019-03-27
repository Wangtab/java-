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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
<script type="text/javascript">
	$(function(){
		initMap('main_table_mapd');
		init_win_map()
	});	

	function init_win_map(){
			win_map = initMap('win_mapd');
			win_map.addEventListener("click",function(e){
			var longitude = e.point.lng;//经度
			var latitude = e.point.lat;//纬度
			$('#longitude_ipt').val(longitude);
			$('#latitude_ipt').val(latitude);
			win_map.clearOverlays();
			var point = new BMap.Point(longitude,latitude);
			var marker = new BMap.Marker(point);  // 创建标注
			win_map.addOverlay(marker); 
		});
	}

	function addPoles(){
		$.ajax({
			type:"post",
			dataType:"text",
			url:"/addPoles",
			data:{"poleCode":$("#poleCode").val(),"poletype":$("#poletype").val(),"install_peo":$("#install_peo").val(),"longitude":$("#longitude_ipt").val(),"latitude":$("#latitude_ipt").val(),
"userid":"${loginUser.id}"},
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
<style type="text/css">
.win_show_img{width: 400px;height: 400px;background-color:#fff;position: absolute;left: 20px;top:100px;border:14px solid #fff;display: none;z-index: 2000;}
.win_show_img img{width: 400px;height: 400px;}
</style>

</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>灯杆管理</p>
		<form action="/poleQuery" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="poleCode" placeholder="灯具厂家名称" />
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>灯杆ID</th>
					<th>灯杆编号</th>
					<th>经度</th>
					<th>纬度</th>
					<th>灯杆类型</th>
					<th>安装人</th>
					<th>安装时间</th>
					<th>操作者</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${poles}" var="pole">
					<tr>
						<td>${pole.id}</td>
						<td>${pole.pole_code}</td>
						<td>${pole.longitude}</td>
						<td>${pole.latitude}</td>
						<td>${pole.type_name}</td>
						<td>${pole.install_peo}</td>
						<td>${pole.install_time}</td>
						<td>${pole.user_name}</td>
						<td>${pole.oper_time}</td>
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
		<div class="main_table_map" id = "main_table_mapd"></div>
	</div>
	<form class="table_win" action="" method = "post">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<div class="form_box">
			<span>灯杆编号</span>
			<input type="text" name="" id="poleCode">
		</div>
		<div class="form_box">
			<span>灯杆类型</span>
			<select name="" id="poletype">
				<option value="1">请选择所属组织</option>
				<option value="1">组织1</option>
				<option value="1">组织2</option>
			</select>
		</div>
		<div class="form_box">
			<span>安装人</span>
			<input type="text" id="install_peo"  >
		</div>
		<div class="form_box">
			<span>经度</span>
			<input type="text" name="" readonly="readonly" id="longitude_ipt">
		</div>
		<div class="form_box">
			<span>纬度</span>
			<input type="text" name="" readonly="readonly" id="latitude_ipt">
		</div>
		<div class="win_map" id = "win_mapd"></div>
		<div class="form_box">
			<input type="submit" value="保存" onclick="addPoles()">
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