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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
<script type="text/javascript">
	function promptBox(obj,msg){
		obj.dialog1 = jqueryAlert({
			'content' : msg,
			'closeTime' : alert_common_time,
		});
	}

	$(function() {
		initMap('main_table_mapd');
		init_win_map()
	});	

	function init_win_map(longitude,latitude){
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
		<form action="/queryPoleManage.do" class="search_box">
			<div class="ipt_box">
				<i></i>
				<input type="text" name="poleCode" value="${poleCode}" placeholder="灯杆编号" />
				<input type="hidden" name="page" value="1">
			</div>
			<input type="submit" value="查询">
			 <div class="add_btn"><img src="images/+.png"/> 添加</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>灯杆编号</th>
					<th>经度</th>
					<th>纬度</th>
					<th>灯杆类型</th>
					<th>所属区域</th>
					<th>所属路段</th>
					<th>安装人</th>
					<th>安装时间</th>
					<th>操作者</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${list}" var="item" begin="0">
				<tr>
					<td>${item.poleCode}</td>
					<td>${item.longitude}</td>
					<td>${item.latitude}</td>
					<td>${item.typeName}</td>
					<td>${item.areaName}</td>
					<td>${item.roadName}</td>
					<td>${item.installer}</td>
					<td>${item.installTime}</td>
					<td>${item.operator}</td>
					<td>${item.operTime}</td>
					<td>
						<a title = "修改" class = "edit_button" href="javascript:void(0);">
							<input type="hidden" id="edId" value="${item.id}">
						</a>
						<a title = "删除" class = "delete_button" href="javascript:void(0);">
							<input type="hidden" id="delId" value="${item.id}">
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page">
				<input type="hidden" id="pcode" value="${poleCode}">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
		<div class="main_table_map" id = "main_table_mapd"></div>
	</div>
	<form class="table_win" action="" method = "post">
		<p>详细信息<span id = "close_table_win">X</span></p>
		<div class="form_box">
			<span>灯杆编号</span>
			<input type="text" name="poleCode" id="poleCode">
		</div>
		<div class="form_box">
			<span>灯杆类型</span>
			<select name="typeId" id="typeId">
				<option value="0">请选择所属组织</option>
			</select>
		</div>
		<div class="form_box">
			<span>所属区域</span>
			<select name="areaId" id="areaId">
				<option value="">请选择所属区域</option>
			</select>
		</div>
		<div class="form_box">
			<span>所属路段</span>
			<select name="roadId" id="roadId">
				<option value="">请选择所属路段</option>
			</select>
		</div>
		<div class="form_box">
			<span>安装人</span>
			<input type="text" name="installer" id="installer">
		</div>
		<div class="form_box">
			<span>经度</span>
			<input type="text" name="longitude" readonly="readonly" id="longitude_ipt">
		</div>
		<div class="form_box">
			<span>纬度</span>
			<input type="text" name="latitude" readonly="readonly" id="latitude_ipt">
		</div>
		<div class="win_map" id = "win_mapd"></div>
		<div class="form_box">
			<input type="hidden" name="id" id="plid" value="">
			<input type="button" value="保存" id="butn" style="margin-left: 130px">
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" id="judge">
				<input type="hidden" id="judgeId" value="">
				确定
			</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>		
</body>
<!--提交form表单-->
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
						location.href = "/queryPoleManage.do?poleCode=&page=1";
					},jtime);
				}
			}
		});
	});
</script>
<!--删除灯杆信息-->
<script type="text/javascript">
	$(".delete_button").click(function(){
		var dId = $(this).find("#delId").val();
		$("#judgeId").val(dId);
	});
	$(".sure_delete_judge_win").click(function(){
		var opts = document.getElementById("judge");
		var pid = $(this).find("#judgeId").val();
		$.post("/deletePoleManage.do",{id:pid},function(data){
			if(data != "" && data != null){
				promptBox(opts,data);
				var jtime = alert_common_time+300;
				setTimeout(function(){
					location.href = "/queryPoleManage.do?poleCode=&page=1";
				},jtime);
			}
		});
	});
</script>
<!--更新灯杆信息-->
<script type="text/javascript">
	$(".edit_button").click(function(){
		var url = "/updatePoleManage.do";
		$(".table_win").attr("action",url);
		var pid = $(this).find("#edId").val();
		$.post("/queryByPoleOne.do",{id:pid},function(data){
			var pone = eval(data);
			$("#poleCode").val(pone[0].poleCode);
			$("#installer").val(pone[0].installer);
			$("#longitude_ipt").val(pone[0].longitude);
			$("#latitude_ipt").val(pone[0].latitude);
			$("#plid").val(pid);
			/*灯杆类型信息*/
			var pole = document.getElementById("typeId");
			pole.length=1;
			poleTypeSelect(pole,pone[0].typeId);
			/*区域信息*/
			var area = document.getElementById("areaId");
			area.length=1;
			areaSelect(area,pone[0].areaId);
			/*删除路段*/
			var road = document.getElementById("roadId");
			road.length=1;
			roadSelect(road,pone[0].roadId,pone[0].areaId);

			win_map.clearOverlays();
			var point = new BMap.Point(pone[0].longitude,pone[0].latitude);
			var marker = new BMap.Marker(point);  // 创建标注
			win_map.addOverlay(marker);

		});
	});
</script>
<!--增加灯杆信息-->
<script type="text/javascript">
	$(".add_btn").click(function(){
		var url = "/addPoleManage.do";
		$(".table_win").attr("action",url);
		$("#poleCode").val("");
		$("#installer").val("");
		$("#longitude_ipt").val("");
		$("#latitude_ipt").val("");
		/*灯杆类型信息*/
		var pole = document.getElementById("typeId");
		pole.length=1;
		poleTypeSelect(pole,-1);
		/*区域信息*/
		var area = document.getElementById("areaId");
		area.length=1;
		areaSelect(area,-1);
		/*删除路段*/
		var road = document.getElementById("roadId");
		road.length=1;
	});
</script>
<!--增加二级联动-->
<script type="text/javascript">
	$("#areaId").click(function(){
		var areaId = $(this).find("option:selected").val();
		/*删除路段*/
		var road = document.getElementById("roadId");
		road.length=1;
		roadSelect(road,-1,areaId);
	});
</script>
<!--查询灯杆信息-->
<script type="text/javascript">
	function poleTypeSelect(pole,type){
		$.post("/selectPoleType.do","",function(data){
			var typeArr = eval(data);
			for(var i = 0;i< typeArr.length;i++) {
				pole[i + 1] = new Option(typeArr[i].typeName,typeArr[i].id);
				if(typeArr[i].id == type){
					pole[i+1].selected = true;
				}
			}
		});
	}
	function areaSelect(area,areaId){
		$.post("/selectAreaAll.do","",function(data){
			var areaArr = eval(data);
			for(var i = 0;i< areaArr.length;i++) {
				area[i + 1] = new Option(areaArr[i].areaname,areaArr[i].id);
				if(areaArr[i].id == areaId){
					area[i+1].selected = true;
				}
			}
		});
	}
	function roadSelect(road,roadId,areaId){
		$.post("/queryRoadList.do",{aid:areaId},function(data){
			var roadArr = eval(data);
			for(var i = 0;i< roadArr.length;i++) {
				road[i + 1] = new Option(roadArr[i].roadName,roadArr[i].id);
				if(roadArr[i].id == roadId){
					road[i+1].selected = true;
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
			var poleCode = $("#pcode").val();
			var cur = $(this).attr("cur");
			var url ="/queryPoleManage.do?poleCode="+poleCode+"&page="+cur;
			$(this).attr("href",url);
		});
	});
</script>
</html>