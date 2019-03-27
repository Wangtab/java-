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
	<link rel="stylesheet" type="text/css" href="css/lamp_type.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<script type="text/javascript" src = "js/alert.js"></script>
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/lamp_type.js"></script>
	<style>
		.win_show_img{width: 400px;height: 400px;background-color:#fff;position: absolute;left: 20px;top:100px;border:14px solid #fff;display: none;z-index: 2000;overflow: hidden;}
		.win_show_img img{width: 400px;height: 400px;}
		.form_box .model{color:#FF0000;font-size: 13px;float: right;margin: -23px 7px auto;display: none}
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
	<p>灯具类型管理</p>
	<form  action="javascript:;" class="search_box" onsubmit="selectAll()">
		<div class="ipt_box">
			<input type="text" name="typeName" placeholder="灯具型号名称"/>
			<input type="hidden" name="page" value="1">
		</div>
		<input type="submit" value="查询">
		<div class="add_btn" onclick="addclick()"><img src="images/+.png"/>添加</div>

	</form>
	<div class="table_box">
		<table>
			<tr>
				<th>灯具类型</th>
				<th>类型描述</th>
				<th>灯具型号</th>
				<th>灯具类型图片</th>
				<th>功率</th>
				<th>厂家</th>
				<th>调光模式</th>
				<th>太阳能板功率(W)</th>
				<th>蓄电池功率(AH)</th>
				<th>操作者</th>
				<th>操作时间</th>
				<th>操作</th>
			</tr>
		</table>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
<form class="table_win" method = "post">
	<p>详细信息<span id = "close_table_win">X</span></p>
	<div class="form_box">
		<span>类型名称</span>
		<select name="lamptypename" id="lamptypename">
			<option value = '0'>请选灯具类型</option>
			<option value = '1'>普通路灯</option>
			<option value = '2'>太阳能路灯</option>
		</select>
	</div>
	<div class="form_box">
		<span>类型描述</span>
		<input type="text" name="lamptypedes" id="lamptypedes">
	</div>
	<div class="form_box">
		<span>灯具型号</span>
		<input type="text" name="lampModel" id="lampModel">
		<div class="model">*型号已存在</div>
		<span id="mod" style="display: none;"></span>
	</div>
	<div class="form_box">
		<span>功率</span>
		<input type="text" name="power" id="power">
	</div>
	<div class="form_box">
		<span>厂家</span>
		<input type="text" name="lampFactory" id="lampFactory">
	</div>
	<div class="form_box">
		<span>调光模式</span>
		<select name="dimmingmode" id="dimmingmode">
			<%--<option value>请选择调光值</option>--%>
			<option value="0-5V">0-5V</option>
			<option value="0-10V">0-10V</option>
			<option value="pwm">pwm</option>
		</select>
	</div>
	<div class="form_box" id="spowerBox">
		<span>太阳能板功率</span>
		<input type="text" name="spower" id="spower">
	</div>
	<div class="form_box" id="bpowerBox">
		<span>蓄电池功率</span>
		<input type="text" name="bpower" id="bpower">
	</div>
	<div class="form_add_box">
		<input type="file" id = "form_add_box_ipt" name="imgurl" onchange = "checkPicFile(this)"/>
		<span>添加图片</span>
		<div id = "win_addBox" onclick = "form_add_box_ipt.click()">
			<img id="imgurl" src="images/add_pic_bg.png"/>
		</div>
	</div>
	<div class="form_box">
		<input type="hidden" id="lId" name="id">
		<input type="hidden" id="lMsg">
		<input type="hidden" id="lPath" name="path">
		<input type="button" value="保存" id="butn" style="margin-left: 130px">
	</div>
</form>
<div class="judge_win">
	<p>信息<span class = "close_judge_win">X</span></p>
	<h1>确定要删除该条信息吗？</h1>
	<div class="judge_btn">
		<h2 class = "sure_delete_judge_win" id="deleteWin">
			确定
			<input type="hidden" id="delId">
		</h2>
		<h2 class = "close_judge_win">取消</h2>
	</div>
</div>
</body>
<!--选择路灯显示功率字段-->
<script type="text/javascript">
	$("#lamptypename").bind("click",function(){
		var msg = $("#lamptypename :selected").val();
		if(msg == 1){
			$("#spowerBox").css("display","none");
			$("#bpowerBox").css("display","none");
		}else{
			$("#spowerBox").css("display","block");
			$("#bpowerBox").css("display","block");
		}
	});
</script>

<!--判断是否有灯具型号-->
<script type="text/javascript">
	$("#lampModel").focusout(function(){
		var that = this;
		var val = $(that).val();
		var opts = document.getElementById("lampModel");
		var info = $("#lamptypename :selected").val();
		var lId = $("#lId").val();
		var lMsg = $("#lMsg").val();
		if(val == ''){
			promptBox(opts,"灯具型号为空");
		}else{
			if(info == 0){
				promptBox(opts,"请您选择灯具类型");
			}else{
				$.post("/queryLampModel.do",{mid:info,msg:val},function(data){
					$("#mod").text(data);
					if(lId !=''){
						if(val == lMsg){
							$(".model").css("display","none");
							$(that).css("border","1px solid #ccc");
						}else{
							if(Number(data) == 0){
								$(".model").css("display","none");
								$(that).css("border","1px solid #ccc");
							}else{
								$(".model").css("display","block");
								$(that).css("border","1px solid #FF0000");
							}
						}
					}else{
						if(Number(data) == 0){
							$(".model").css("display","none");
							$(that).css("border","1px solid #ccc");
						}else{
							$(".model").css("display","block");
							$(that).css("border","1px solid #FF0000");
						}
					}
				});
			}
		}
	});
</script>
<script>
	function addclick(){
		var url = "../addTypeManage.do";
		$(".table_win").attr("action",url);
		console.log(url);
		$("#lamptypedes").val("");
		$("#power").val("");
		$("#imgurl").attr("src","images/add_pic_bg.png");
		$("#lId").val("");
		$("#lampModel").val("");
		$("#lMsg").val("");
		$("#spower").val("");
		$("#bpower").val("");
		/*选择显示灯具型号*/
		$("#mod").text("");
		$(".model").css("display","none");
		$("#lampModel").css("border","1px solid #ccc");
		/*显示功率框*/
		$("#spowerBox").css("display","block");
		$("#bpowerBox").css("display","block");
		/*灯具类型*/
		var lampType = document.getElementById("lamptypename");
		lampType.length=1;
		lampType[1] = new Option("普通路灯",1);
		lampType[2] = new Option("太阳能路灯",2);
		/*工厂信息*/
		$("#lampFactory").val("");
		/*调光值信息*/
		var dimming = document.getElementById("dimmingmode");
		dimming.length=1;
		$.post("/querydimmingmodeList.do","",function(data){
			var dimmingArr = eval(data);
			for(var i = 0;i< dimmingArr.length;i++) {
				dimming[i + 1] = new Option(dimmingArr[i].dimminName, dimmingArr[i].dimminName);
			}
		});
	};
</script>
<!--提交form表单-->
<script type="text/javascript">
	$("#butn").click(function(){
		var opts = document.getElementById("butn");
		var formData = new FormData($(".table_win")[0]);
		var url = $(".table_win").attr("action");
		var num = $("#lamptypename :selected").val();
		var mod = $("#mod").text();
		var lId = $("#lId").val();
		var lMsg = $("#lMsg").val();
		var lampModel = $("#lampModel").val();
		if(num != 0){
			if(lId != ''){
				if(lampModel == lMsg){
					submitLamp(opts,url,formData);
				}else{
					if(mod == 0 && mod != ''){
						submitLamp(opts,url,formData);
					}else{
						promptBox(opts,"灯具型号不正确");
					}
				}
			}else{
				if(mod == 0 && mod != ''){
					submitLamp(opts,url,formData);
				}else{
					promptBox(opts,"灯具类型不正确");
				}
			}
		}else{
			promptBox(opts,"未选择灯具类型");
		}
	});
	function submitLamp(opts,url,formData){
		$.ajax({
			url: url,
			type: 'POST',
			data: formData,
			contentType: false,
			processData: false,
			success: function(){
				$('.table_win').hide(manage_table_time);
				selectAll();
				promptBox(opts,"操作成功");
			}
		});
	}
</script>
<!--更新灯具类型信息-->
<script type="text/javascript">
	<%--$(".edit_button").click(function(){
		var lampId = $(this).attr('data_id');
		console.log(lampId);
		var url = "/updateTypeManage.do";
		$(".table_win").attr("action",url);
		/*灯具类型*/
		var lampType = document.getElementById("lamptypename");
		lampType.length=1;
		lampType[1] = new Option("普通路灯",1);
		lampType[2] = new Option("太阳能路灯",2);
		/*根据ID查询灯具信息*/
		$.post("/queryLampType.do",{id:lampId},function(data){
			var lamp = eval(data);
			lampType[lamp[0].lamptypename].selected = true;
			if(lamp[0].lamptypename == 1){
				$("#spowerBox").css("display","none");
				$("#bpowerBox").css("display","none");
			}else{
				$("#spowerBox").css("display","block");
				$("#bpowerBox").css("display","block");
			}
			$("#lamptypedes").val(lamp[0].lamptypedes);
			$("#lampModel").val(lamp[0].lampModel);
			$("#lMsg").val(lamp[0].lampModel);
			$("#power").val(lamp[0].power);
			$("#lampFactory").val(lamp[0].lampFactory);
			dimmingSelect(lamp[0].dimmingmode);
			$("#imgurl").attr("src",lamp[0].imgurl);
			$("#lId").val(lamp[0].id);
			$("#lPath").val(lamp[0].imgurl);
			$("#spower").val(lamp[0].spower);
			$("#bpower").val(lamp[0].bpower);
		});
	});--%>
	/*调光值*/
	function dimmingSelect(name){
		var dimming = document.getElementById("dimmingmode");
		dimming.length=1;
		$.post("/querydimmingmodeList.do","",function(data){
			var dimmingArr = eval(data);
			for(var i = 0;i< dimmingArr.length;i++) {
				dimming[i + 1] = new Option(dimmingArr[i].dimminName, dimmingArr[i].dimminName);
				if(dimmingArr[i].dimminName == name){
					dimming[i+1].selected = true;
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
			var typeName = $("#tName").val();
			var cur = $(this).attr("cur");
			var url ="/lampTypeManage.do?typeName="+typeName+"&page="+cur;
			$(this).attr("href",url);
		});
	});
</script>
</html>