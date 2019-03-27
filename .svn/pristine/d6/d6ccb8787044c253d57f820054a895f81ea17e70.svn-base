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
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/lamp_manage.js"></script>
	<script type="text/javascript" src = "js/xlsx.full.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<script type="text/javascript">
		function promptBox(obj,msg){
			obj.dialog1 = jqueryAlert({
				'content' : msg,
				'closeTime' : alert_common_time,
			});
		}
	</script>
</head>
<body onload="lampLoad()">
	<h6></h6>
	<div class="work_content_box">
		<p>灯具管理</p>
		<form  action="javascript:;" class="search_box" onsubmit="selectAll()">
			<select name="region_id" id="region_id">
				<option value = "">请选择区域</option>
			</select>
			<select name="road_id" id="road_id">
				<option value = "">请选择道路</option>
			</select>
			<select name="line_id" id="line_id">
				<option value = "">请选择线路</option>
			</select>
			<div class="ipt_box">
				<i></i>
				<input type="text" name="lampName" id="lampName" placeholder="灯杆编号"/>
			</div>
			<%--<input type="hidden"id="rId" value="${rId}">
			<input type="hidden"id="aId" value="${aId}">
			<input type="hidden"id="lId" value="${lId}">--%>
			<input type="hidden" name="page" value="1">
			<input type="submit" value="查询">
			<div class="add_btn" id="add_btn"><img src="images/+.png"/> 添加</div>
			<div class="import_btn"   onclick = "lampDataImp.click()">批量导入</div>
			<div class="import_btn" onclick="downModel()">下载模板</div>
			<div class="import_btn" onclick="">批量升级</div>
			<input type="file" name="" id="lampDataImp" style="display: none">
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>所属区域</th>
					<th>所属道路</th>
					<th>所属线路</th>
					<th>配电箱编号</th>
					<th>配电箱回路</th>
					<th>灯杆编号</th>
					<th>灯具型号</th>
					<th>灯具编号</th>
					<th>控制器编号</th>
					<th>额定功率</th>
					<th>灯具工厂</th>
					<th>经度</th>
					<th>纬度</th>
					<th>操作者</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
			</table>
			<div class="page">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
		<div class="table_map" id = "main_table_mapd"></div>
	</div>
	<form class="second_form" action="" method = "post">
		<p>详细信息<span id = "close_scond_wind">X</span></p>
		<div class="detail_box">
			<span>所属区域</span>
			<select name="regionId" id="regionId">
				<option value = '0'>请选择所属区域</option>
			</select>
		</div>
		<div class="detail_box">
			<span>所属道路</span>
			<select name="roadId" id="roadId">
				<option value = '0'>请选择所属道路</option>
			</select>
		</div>
		<div class="detail_box">
			<span>所属线路</span>
			<select name="roadlineId" id="roadlineId">
				<option value ='0'>请选择所属线路</option>
			</select>
		</div>
		<div class="detail_box">
			<span>灯具编号</span>
			<input type="text" name="lampnum" id="lampnum">
		</div>
		<div class="detail_box">
			<span>灯具类型</span>
			<select  id="lamtId">
				<option value = '0'>请选择灯具类型</option>
			</select>
		</div>
		<div class="detail_box">
			<span>灯具型号</span>
			<select name="typeId" id="typeId">
				<option value = '0'>请选择灯具型号</option>
			</select>
		</div>
		<div class="detail_box">
			<span>配电箱编号</span>
			<select name="pdId" id="pdId">
				<option value ='0'>请选择配电箱编号</option>
			</select>
		</div>
		<div class="detail_box">
			<span>配电箱回路</span>
			<select name="dbcircuit" id="dbcircuit">
				<option value ='0'>请选择配电箱回路</option>
			</select>
		</div>
		<div class="detail_box">
			<span>灯杆编号</span>
			<input type="text" name="poleCode" id="poleCode">
		</div>
		<div class="detail_box">
			<span>控制器</span>
			<select name="controller_id" id="controller_id">
				<option value="">不关联控制器</option>
			</select>
		</div>
		<div class="detail_box">
			<span>经度</span>
			<input type="text" name="lo" readonly="readonly" id="longitude_ipt">
		</div>
		<div class="detail_box">
			<span>纬度</span>
			<input type="text" name="la" readonly="readonly" id="latitude_ipt">
		</div>
		<div class="win_map" id = "win_mapd"></div>
		<div class="detail_box_submit">
			<input type="hidden" name="id" id="udId" value="">
			<input type="button" value="保存" id="butn">
		</div>
	</form>
	<div class="judge_win">
		<p>信息<span class = "close_judge_win">X</span></p>
		<h1>确定要删除该条信息吗？</h1>
		<div class="judge_btn">
			<h2 class = "sure_delete_judge_win" id="delwin">
				<input type="hidden" value="">
				确定
			</h2>
			<h2 class = "close_judge_win">取消</h2>
		</div>
	</div>		
</body>
<!--联查-->
<script type="text/javascript">
	function lampLoad(){
		var aId = $("#aId").val();
		var rId = $("#rId").val();
		var lId = $("#lId").val();
		/*区域*/
		var region = document.getElementById("region_id");
		region.length = 1;
		regionQuery(region,Number(aId),Number(rId),Number(lId));
		var road = document.getElementById("road_id");
		road.length = 1;
		var line = document.getElementById("line_id");
		line.length = 1;

	}
	/*区域道路联动*/
	$("#region_id").bind("change",function(){
		var msg = $(this).find("option:selected").val();
		if(msg != 0){
			roadQuery(msg,-1);
		}else{
			var road = document.getElementById("road_id");
			road.length = 1;

			var roadline = document.getElementById("line_id");
			roadline.length = 1;
		}
	});
	/*道路*/
	$("#road_id").bind("change",function(){
		var msg = $(this).find("option:selected").val();
		if(msg != 0){
			roadlineQuery(msg,-1);
		}else{
			var roadline = document.getElementById("line_id");
			roadline.length = 1;
		}
	});
</script>
<!--提示-->
<script type="text/javascript">
	$("#roadId").click(function(){
		/*区域*/
		var region = $("#regionId option:selected").val();
		/*道路*/
		var road = document.getElementById("roadId");

		if(Number(region) == 0){
			promptBox(road,"请选择区域");
		}

	});
	$("#roadlineId").click(function(){
		/*区域*/
		var region = $("#regionId option:selected").val();
		/*道路*/
		var road = $("#roadId option:selected").val();
		/*线路*/
		var roadline = document.getElementById("roadlineId");
		var len = roadline.length;

		if(Number(region) != 0){
			if(Number(road) == 0){
				promptBox(roadline,"请选择道路");
			}else{
				if(Number(len) == 1){
					promptBox(roadline,"该道路没有线路");
				}
			}
		}else{
			promptBox(roadline,"请选择区域");
		}

	});
</script>
<!--提交form表单-->
<script type="text/javascript">
	$("#butn").click(function(){
		var opts = document.getElementById("butn");
		var formData = new FormData($(".second_form")[0]);
		var url = $(".second_form").attr("action");
		var num = $("#lampnum").val();
		var roadlineId = $("#roadlineId ").find("option:selected").val();
		var reg = /^[0-9]*$/;
		var lampt = $("#typeId :selected").val();
		var poleCode = $("#poleCode").val();
		var conval = $("#controller_id option:selected").val();
		if(roadlineId != 0){
			if(reg.test(num)){
				if(lampt != 0){
					if(poleCode != ''){
						if(conval != ''){
							submitLampManage(opts,url,formData); //提交表单
						}else{
							promptBox(opts,"请选择控制器");
						}
					}else{
						promptBox(opts,"请填写灯杆编号");
					}
				}else{
					promptBox(opts,"请选择灯具型号");
				}
			}else{
				promptBox(opts,"灯具编号错误");
			}
		}else{
			promptBox(opts,"请选择所属线路");
		}
	});
	function submitLampManage(opts,url,formData){
		$.ajax({
			url: url,
			type: 'POST',
			data: formData,
			contentType: false,
			processData: false,
			success: function(data){
				if(data != "" && data != null){
					promptBox(opts,data);
					var jtime = alert_common_time+300;
					setTimeout(function(){
						location.href = "/lampManage.do?lampName=&page=1";
					},jtime);
				}
			}
		});
	}
</script>
<script type="text/javascript">
	/*区域道路联动*/
	$("#regionId").bind("change",function(){
		var msg = $(this).find("option:selected").val();
		if(msg != 0){
			roadSelect(msg,-1,-1);
		}else{
			var road = document.getElementById("roadId");
			road.length = 1;

			var roadline = document.getElementById("roadlineId");
			roadline.length = 1;
		}
	});
	/*道路*/
	$("#roadId").bind("change",function(){
		var msg = $(this).find("option:selected").val();
		if(msg != 0){
			roadlineSelect(msg,-1);
		}else{
			var roadline = document.getElementById("roadlineId");
			roadline.length = 1;
		}
	});
	/*灯具类型型号联动*/
	$("#lamtId").bind("change",function(){
		var msg = $(this).find("option:selected").val();
		if(msg != 0){
			lampTypeSelect(msg,-1);
		}else{
			var typeId = document.getElementById("typeId");
			typeId.length = 1;
		}
	});
	/*型号*/
	$("#typeId").bind("click",function(){
		var opts = document.getElementById("typeId");
		var msg = opts.length;
		if(msg == 1){
			promptBox(opts,"请选择型号");
		}
	});
</script>
<!--增加灯具信息-->
<script type="text/javascript">
	$("#add_btn").click(function(){
		var url = "/addLampManage.do";
		$(".second_form").attr("action",url);
		$("#lampnum").val("");

		/*区域*/
		var region = document.getElementById("regionId");
        region.length = 1;
        regionSelect(region,-1,-1,-1);
        var road = document.getElementById("roadId");
        road.length = 1;
		var roadline = document.getElementById("roadlineId");
		roadline.length = 1;
		/*灯具类型*/
		var lamt = document.getElementById("lamtId");
		lamt.length = 1;
		lamtSelect(lamt,-1,-1);
		var type = document.getElementById("typeId");
		type.length = 1;
		/*配电箱编号*/
		var pdbox = document.getElementById("pdId");
		pdbox.length = 1;
		pdboxSelect(pdbox,-1);
		/*灯杆编号*/
		$("#poleCode").val("");
		/*控制器*/
		var controller = document.getElementById("controller_id");
		controller.length = 1;
		controllerSelect(controller,-1);
		/*配电箱回路*/
		var circuit = document.getElementById("dbcircuit");
		circuit.length = 1;
		dbcircuitSelect(circuit,-1);

		$("#longitude_ipt").val("");
		$("#latitude_ipt").val("");

		$("#udId").val("");

		win_map.clearOverlays();
	});
</script>
<!--更新灯具信息-->
<script type="text/javascript">
	$(".edit_button").click(function(){
		var url = "/updateLampManage.do";
		$(".second_form").attr("action",url);
		var eid = $(this).find("#lamId").val();
		$("#udId").val(eid);
		$.post("/queryLampMeg.do",{id:eid},function(data){
			var lamp = eval(data);
			$("#lampnum").val(lamp[0].lampnum);

			/*区域*/
			var region = document.getElementById("regionId");
			region.length = 1;
			regionSelect(region,lamp[0].regionId,lamp[0].roadId,lamp[0].roadlineId);
			/*灯具类型*/
			var lamt = document.getElementById("lamtId");
			lamt.length = 1;
			lamtSelect(lamt,lamp[0].lamptypename,lamp[0].typeId);
			/*配电箱编号*/
			var pdbox = document.getElementById("pdId");
			pdbox.length = 1;
			pdboxSelect(pdbox,lamp[0].pdId);
			/*灯杆编号*/
			$("#poleCode").val(lamp[0].poleCode);
			/*控制器*/
			var controller = document.getElementById("controller_id");
			controller.length = 1;
			controllerSelect(controller,lamp[0].conId);
			/*配电箱回路*/
			var circuit = document.getElementById("dbcircuit");
			circuit.length = 1;
			dbcircuitSelect(circuit,lamp[0].dbcircuit);

			$("#longitude_ipt").val(lamp[0].lo);
			$("#latitude_ipt").val(lamp[0].la);

			$("#udId").val(lamp[0].id);

			win_map.clearOverlays();
			var point = new BMap.Point(lamp[0].lo,lamp[0].la);
			var marker = new BMap.Marker(point);  // 创建标注
			win_map.addOverlay(marker);
		});
	});
</script>
<!--删除灯具信息-->
<script type="text/javascript">
	$(".delete_button").click(function(){
		var id = $(this).find("#delId").val();
		$("#delwin").find("input").val(id);
	});
	$("#delwin").click(function(){
		var opts = document.getElementById("butn");
		var delid = $(this).find("input").val();
		$.post("/deleteLampManage.do",{id:delid},function(data){
			if(data != "" && data != null){
				promptBox(opts,data);
				var jtime = alert_common_time+300;
				setTimeout(function(){
					location.href = "/lampManage.do?lampName=&page=1";
				},jtime);
			}
		});
	});
</script>
<script type="text/javascript">
	/*区域*/
	function regionSelect(region,msg,rid,lineId){
		$.post("/queryRegionList.do","",function(data){
			var regionArr = eval(data);
			for(var i = 0;i< regionArr.length;i++){
				region[i + 1] = new Option(regionArr[i].areaName, regionArr[i].id);
				if(regionArr[i].id == msg){
					region[i+1].selected = true;
					roadSelect(msg,rid,lineId);
				}
			}
		});
	}
	function regionQuery(region,msg,rid,lineId){
		$.post("/queryRegionList.do","",function(data){
			var regionArr = eval(data);
			for(var i = 0;i< regionArr.length;i++){
				region[i + 1] = new Option(regionArr[i].areaName, regionArr[i].id);
				if(regionArr[i].id == msg){
					region[i+1].selected = true;
					roadQuery(msg,rid,lineId);
				}
			}
		});
	}
	/*道路*/
	function roadSelect(regionId,msg,roadlineId){
		var road = document.getElementById("roadId");
		road.length = 1;
		var roadline = document.getElementById("roadlineId");
		roadline.length = 1;
		$.post("/queryRegionArr.do",{id:regionId},function(data){
			var roadArr = eval(data);
			if(roadArr != ""){
				for(var i = 0;i< roadArr.length;i++){
					road[i + 1] = new Option(roadArr[i].roadName, roadArr[i].id);
					if(roadArr[i].id == msg){
						road[i+1].selected = true;
						roadlineSelect(msg,roadlineId);
					}
				}
			}
		});
	}
	function roadQuery(regionId,msg,roadlineId){
		var road = document.getElementById("road_id");
		road.length = 1;
		var roadline = document.getElementById("line_id");
		roadline.length = 1;
		$.post("/queryRegionArr.do",{id:regionId},function(data){
			var roadArr = eval(data);
			if(roadArr != ""){
				for(var i = 0;i< roadArr.length;i++){
					road[i + 1] = new Option(roadArr[i].roadName, roadArr[i].id);
					if(roadArr[i].id == msg){
						road[i+1].selected = true;
						roadlineQuery(msg,roadlineId);
					}
				}
			}
		});
	}
	/*线路*/
	function roadlineSelect(roadId,msg){
		var roadline = document.getElementById("roadlineId");
		roadline.length = 1;
		$.post("/queryRoadlineArr.do",{id:roadId},function(data){
			var roadlineArr = eval(data);
			if(roadlineArr != ""){
				for(var i = 0;i< roadlineArr.length;i++){
					roadline[i + 1] = new Option(roadlineArr[i].cname, roadlineArr[i].id);
					if(roadlineArr[i].id == msg){
						roadline[i+1].selected = true;
					}
				}
			}
		});
	}
	function roadlineQuery(roadId,msg){
		var roadline = document.getElementById("line_id");
		roadline.length = 1;
		$.post("/queryRoadlineArr.do",{id:roadId},function(data){
			var roadlineArr = eval(data);
			if(roadlineArr != ""){
				for(var i = 0;i< roadlineArr.length;i++){
					roadline[i + 1] = new Option(roadlineArr[i].cname, roadlineArr[i].id);
					if(roadlineArr[i].id == msg){
						roadline[i+1].selected = true;
					}
				}
			}
		});
	}
	/*灯具类型*/
	function lamtSelect(lamt,msg,mid){
		lamt[1] = new Option("普通路灯", 1);
		lamt[2] = new Option("太阳能路灯", 2);
		if(msg != -1){
			lamt[msg].selected = true;
			lampTypeSelect(msg,mid);
		}
	}

	/*灯具型号*/
	function lampTypeSelect(tid,msg){
		var lampType = document.getElementById("typeId");
		lampType.length = 1;

		$.post("/queryLampTypeList.do",{id:tid},function(data){
			var lampTypeArr = eval(data);
			if(lampTypeArr != ''){
				for(var i = 0;i< lampTypeArr.length;i++) {
					lampType[i + 1] = new Option(lampTypeArr[i].lampModel, lampTypeArr[i].id);
					if(lampTypeArr[i].id == msg){
						lampType[i+1].selected = true;
					}
				}
			}
		});
	}
	/*配电箱编号*/
	function pdboxSelect(pdbox,msg){
		$.post("/queryPdboxAll.do","",function(data){
			var pdboxArr = eval(data);
			for(var i = 0;i< pdboxArr.length;i++) {
				pdbox[i + 1] = new Option(pdboxArr[i].name, pdboxArr[i].id);
				if(pdboxArr[i].id == msg){
					pdbox[i+1].selected = true;
				}
			}
		});
	}
	/*配电箱回路*/
	function dbcircuitSelect(dbcircuit,msg){
		for(var i = 0;i <15 ; i++){
			dbcircuit[i+1] = new Option(i+1,i+1);
		}
		if(msg != -1){
			dbcircuit[msg].selected = true;
		}
	}
	/*控制器*/
	function controllerSelect(controller,msg){
		$.post("/queryControllerList.do","",function(data){
			var controllerArr = eval(data);
			for(var i = 0;i< controllerArr.length;i++) {
				controller[i + 1] = new Option(controllerArr[i].nbCode, controllerArr[i].id);
				if(controllerArr[i].id == msg){
					controller[i+1].selected = true;
				}
			}
			controllerSelected(controller,controllerArr.length,msg);
		});
	}
	function controllerSelected(controller,num,msg){
		$.post("/queryContSelected.do",{contId:msg},function(data){
			var controllerArr = eval(data);
			if(controllerArr != ""){
				controller[num+1] = new Option(controllerArr[0].nbCode, controllerArr[0].id);
				controller[num+1].selected = true;
			}

		});
	}

</script>
<!--js分页-->
<script type="text/javascript">
	$(function(){
		var aId = $("#aId").val();
		var rId = $("#rId").val();
		var lId = $("#lId").val();
		var carNum = '${count}' || 0;
		var curpage = '${curPage}' || 0;
		carNum = Math.ceil(carNum/10);
		page({
			id:'controllerPage',
			nowNum: curpage,
			allNum: carNum
		});
		$("#controllerPage").find("a").bind("click",function(){
			var lampName = $("#lamName").val();
			var cur = $(this).attr("cur");
			var url ="/lampManage.do?region_id="+aId+"&road_id="+rId+"&line_id="+lId+"&lampName="+lampName+"&page="+cur;
			$(this).attr("href",url);
		});
	});
</script>
</html>