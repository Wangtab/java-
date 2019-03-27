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
	<script type="text/javascript" src = "js/function.js"></script>
</head>
<body>
	<h6></h6>
	<div class="work_content_box">
		<p>配电箱状态报表</p>
		<form action="concentratorState.do" id="selectBox" class="search_box" method="get">
			<select name="aId" id="area_select">
				<option value="0">请选择区域</option>
				<c:forEach items="${tareamanageList}" var="item">
				<option value="${item.id}">${item.areaname}</option>
				</c:forEach>
			</select>
			<%--<select name="" id="road_select">
				<option value="0">请选择路段</option>
			</select>--%>
			<select name="kId" id="con_select">
				<option value="0">请选择集中器</option>
				<c:forEach items="${tconcentratorkindList}" var="item">
					<option value="${item.id}">${item.kindname}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="page" value="1"/>
			<input type="submit" value="查询">
		 	<div class="excel_btn" onclick="exportExcel()">导出Excel</div>
		</form>
		<div class="table_box">
			<table>
				<tr>
					<th>序号</th>
					<th>区域名称</th>
					<th>集中器名称</th>
					<th>联网状态</th>
					<th>A相电压</th>
					<th>A相电流</th>
					<th>A相功率</th>
					<th>B相电压</th>
					<th>B相电流</th>
					<th>B相功率</th>
					<th>C相电压</th>
					<th>C相电流</th>
					<th>C相功率</th>
					<th>温度</th>
					<th>上传时间</th>
				</tr>
				<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.csId}</td>
					<td>${item.areaName}</td>
					<td>${item.concentratorname}</td>
					<c:if test="${item.concentratorname != '1099'}">
					<td><img src="images/wifi_close.png" title = "不在线"/></td>
					</c:if>
					<c:if test="${item.concentratorname == '1099'}">
					<td><img src="images/wifi_online.png" title = "在线"/></td>
					</c:if>
					<td>${item.aLov}</td>
					<td>${item.aElec}</td>
					<td>${item.aPower}</td>
					<td>${item.bLov}</td>
					<td>${item.bElec}</td>
					<td>${item.bPower}</td>
					<td>${item.cLov}</td>
					<td>${item.cElec}</td>
					<td>${item.cPower}</td>
					<td>${item.csTemp}</td>
					<td>${item.csDate}</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page">
				<input type="hidden" id="aId" value="${aId}">
				<input type="hidden" id="kId" value="${kId}">
				<div class="pagebox" id = 'controllerPage'></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var carNum = ${count};
		var curpage = ${curPage};
		var aId = $("#aId").val();
		var kId = $("#kId").val();
		carNum = Math.ceil(carNum/2);
		page({
			id:'controllerPage',
			nowNum: curpage,
			allNum: carNum
		});
		$("#controllerPage").find("a").bind("click",function(){
			var cur = $(this).attr("cur");
			var url ="/concentratorState.do?aId="+aId+"&kId="+kId+"&page="+cur;
			$(this).attr("href",url);
		});
	});
</script>
<script type="text/javascript">
	$("#area_select").click(function() {
		var val = $(this).find("option:selected").val();
		if(val != 0){
			var url = "/queryRoadList.do";
			var sendData = {aid:val};
			$.post(url,sendData,function(data){
				var road = document.getElementById("road_select");
				road.length=1;
				var arr = eval(data);
				for(var i=0;i<arr.length;i++){
					road[i+1]=new Option(arr[i].roadName,arr[i].id);
				}
			});
		}else{
			var road = document.getElementById("road_select");
			road.length=1;
		}

	})
</script>
<script type="text/javascript">
	function exportExcel(){
		var aId = $("#aId").val();
		var kId = $("#kId").val();
		var url = "/concentratorStateExportExcel.do?aId="+aId+"&kId="+kId;
		openWindow(url,"700","400");
	}
</script>
</html>