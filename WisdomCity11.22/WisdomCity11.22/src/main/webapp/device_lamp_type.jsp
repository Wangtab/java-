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
	<script type="text/javascript" src = "js/device_lamp_type.js"></script>
	<style>
		.win_show_img{width: 400px;height: 400px;background-color:#fff;position: absolute;left: 20px;top:100px;border:14px solid #fff;display: none;z-index: 2000;overflow: hidden;}
		.win_show_img img{width: 400px;height: 400px;}
		.form_box .model{color:#FF0000;font-size: 13px;float: right;margin: -23px 7px auto;display: none}
		#spowerBox{display: none}
		#bpowerBox{display: none}
	</style>
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
			<i></i>
			<input type="text" name="typeName" placeholder="灯具型号名称"/>
		</div>
		<input type="submit" value="查询">
		<div class="add_btn"><img src="images/+.png"/>添加</div>

	</form>
	<div class="table_box">
		<table>
			<tr>
				<th orderby = "lamptypename">灯具类型</th>
				<th orderby = "lamptypedes">类型描述</th>
				<th orderby = "lampModel">灯具型号</th>
				<th orderby = "imgurl">灯具类型图片</th>
				<th orderby = "power">功率</th>
				<th orderby = "lampFactory">厂家</th>
				<th orderby = "dimmingmode">调光模式</th>
				<th orderby = "spower">太阳能板功率(W)</th>
				<th orderby = "bpower">蓄电池功率(AH)</th>
				<th orderby = "real_name">操作者</th>
				<th orderby = "opertime">操作时间</th>
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
		<span>类型名称</span>
		<select name="lamptypename">
			<option value = '1'>普通路灯</option>
			<option value = '2'>太阳能路灯</option>
		</select>
	</div>
	<div class="form_box">
		<span>类型描述</span>
		<input type="text" name="lamptypedes" allow_null = "ok">
	</div>
	<div class="form_box">
		<span>灯具型号</span>
		<input type="text" name="lampModel">
	</div>
	<div class="form_box">
		<span>功率</span>
		<input type="text" name="power">
	</div>
	<div class="form_box">
		<span>厂家</span>
		<input type="text" name="lampFactory">
	</div>
	<div class="form_box">
		<span>调光模式</span>
		<select name="dimmingmode"></select>
	</div>
	<div class="form_box" id="spowerBox">
		<span>太阳能板功率</span>
		<input type="text" name="spower" allow_null = "ok">
	</div>
	<div class="form_box" id="bpowerBox" >
		<span>蓄电池功率</span>
		<input type="text" name="bpower" allow_null = "ok">
	</div>
	<div class="form_add_box">
		<input type="file" id = "form_add_box_ipt" name="imgurl" onchange = "checkPicFile(this)"/>
		<span>添加图片</span>
		<div id = "win_addBox" onclick = "form_add_box_ipt.click()">
			<img id="imgurl" src="images/add_pic_bg.png"/>
		</div>
	</div>
	<div class="form_box">
		<input type="submit" value="保存" style="margin-left: 130px">
	</div>
</form>
<div class="judge_win">
	<p>信息<span class = "close_judge_win">X</span></p>
	<h1>确定要删除该条信息吗？</h1>
	<div class="judge_btn">
		<h2 class = "sure_delete_judge_win" url = "delLampTypeDataById">确定</h2>
		<h2 class = "close_judge_win">取消</h2>
	</div>
</div>
</body>
</html>