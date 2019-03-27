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
	<link href="css/bootstrap.min1.css" rel="stylesheet">
	<link href="css/bootstrap-slider.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/common_table.css">
	<link rel="stylesheet" type="text/css" href="css/screen_data.css">
	<link rel="stylesheet" type="text/css" href="css/alert.css">
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/echarts.min.js" ></script>
	<script type="text/javascript" src = "js/common_charts.js"></script>
	<script type="text/javascript" src = "js/common.js"></script>
	<script type="text/javascript" src = "js/page.js"></script>
	<script type="text/javascript" src = "js/bootstrap-slider.js"></script>
	<script type="text/javascript" src = "js/bootstrap-slider.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<script type="text/javascript" src = "js/monitor_center.js"></script>
	<script type="text/javascript" src = "js/sereen_data.js"></script>
	<script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="body_warpper">
	<div class="top_left_corner">
		<i></i>
	</div>
	<div class="left_border_bg"></div>
	<div class="left_bottom_border">
		<i></i>
	</div>
	<div class="top_right_corner"></div>
	<div class="right_border_bg"></div>
	<div class="right_top_border_bg"></div>
	<div class="right_bottom_border"></div>
	<div id="allmap" class = "baiduMap"></div>
	<div class="screen_title">
		<img src="<%=request.getSession().getAttribute("logoPic")%>">
		<h1><%=request.getSession().getAttribute("logoName")%></h1>
	</div>
	<div class="light_rate">
		<a href="getMenuJsp?urlTo=analysis_dimming.jsp">亮灯率</a>
		<div class="light_rate_content">
			<div class="light_num">
				<span>亮灯数</span>
				<ul>
					<li>0</li>
					<li>0</li>
					<li>0</li>
					<li>0</li>
					<li>0</li>
					<li>0</li>
				</ul>
			</div>
			<div class="lightRateCharts" >
				<a class = "lightRateChartsNum" href="">0%</a>
				<div class="lightRateCharts_box" id = "lightRateChartsd">

				</div>
			</div>
			<h6></h6>
		</div>
	</div>
	<div class="lamp_warn">
		<a href="getMenuJsp?urlTo=lampwarn.jsp">今日报警</a>
		<em></em>
		<b></b>
		<i></i>
		<div class="tody_alarm_disc" id= "today_alarm_disc"></div>
		<div class="alarm_text">
			<p><span class = "span_fine"></span>良好</p>
			<p><span class = "span_allow"></span>合格</p>
			<p><span class = "span_not_allow"></span>较差</p>
		</div>
		<div class="deal_num">
			<p class = "frist_dealBox">已处理：0个</p>
			<p class = "second_dealBox">未处理：0个</p>
		</div>
	</div>
	<div class="realConsumePower">
		<div class="realConsumePower_warpper">
			<i></i>
			<img src="images/screen_realConsumePower.png"/>
			<div class="title">
				<h1>实施能耗</h1>
			</div>
			<div class="rcp_content">

				<div class="rcp_box rcp_box_frist">
					<a href="getMenuJsp?urlTo=energy_analysis.jsp">总能耗分析</a>
					<div class = "charts_box" id = "allEnergyChart"></div>
				</div>
				<div class="rcp_box">
					<a href="getMenuJsp?urlTo=energy_analysis.jsp" id="areaPowerCurvedlink">区域能耗分析</a>
					<div class = "charts_box" id = "areaPowerCurved"></div>
				</div>
				<div class="rcp_box savePower">
					<a href="getMenuJsp?urlTo=analysis_save_power.jsp">节能率对比</a>
					<div class = "charts_box" id = "savePowerd">

					</div>
				</div>
				<div class="rcp_box">
					<a href="getMenuJsp?urlTo=monitor_power.jsp">总功率分析</a>
					<div class = "charts_box" id = "allPowerChart">

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="roadList">
		<i></i>
		<h1><img src="images/list_detail_title_pic.png" alt="">详细信息</h1>
		<div class="roadList_deatail"></div>
	</div>
	<div class="time_warpper">
		<a href="menu.jsp" title = "返回列表"></a>
		<span></span>
		<h1></h1>
		<p>
			<img src="<%=request.getSession().getAttribute("weatherPic")%>" width = "15" height="15">
			<%=request.getSession().getAttribute("lowTemp")%>-<%=request.getSession().getAttribute("heightTemp")%> ℃
		</p>
		<i map_style = "midnight"></i>
	</div>
	<div class="lampNumList">
		<span class = "open_lamp">0</span>
		<span class = "close_lamp">0</span>
		<span class = "expection_lamp">0</span>
		<span class = "online_exe">0</span>
		<span class = "offline_exe">0</span>
		<h1>数据网格</h1>
		<i></i>
	</div>
	<div class="roadList_btn common_screen_btn"></div>
	<div class="light_rate_btn common_screen_btn"></div>
	<div class="lamp_warn_btn common_screen_btn"></div>
	<div class="lampNumList_btn common_screen_transverse_btn"></div>
	<div class="realConsumePower_btn common_screen_transverse_btn"></div>
	<div class="showAllData_win">
		<p>网络数据<span>X</span></p>
		<form action="javascript:;" method="post" class="search_box" onsubmit="getAll_lamp_data2()">
			<select name = "areaId" id="areaid">
				<option value="">请选择区域</option>
			</select>
			<select name = "roadId" id="roadid">
				<option value="">请选择道路</option>
			</select>
			<select name = "lineId" id="roadxid">
				<option value="">请选择线路</option>
			</select>
			<select name = 'groupId'>
				<option value="">请选择分组</option>
			</select>
			<input type="submit" value="查询">
			<input type="button" value="更新数据" class="import_btn" isget = "n" onclick = "getLampDataByRoadId(this)">
			<div class = "command_box">
				<span>开关灯</span>
				<input type="radio" value="0" name="light_switch11" id = "showAllData_win_radio_open" />
				<label for="showAllData_win_radio_open">开灯</label>
				<input type="radio" value="1" name="light_switch11" id = "showAllData_win_radio_close" checked = 'true' />
				<label for="showAllData_win_radio_close">关灯</label>
				<input type="button" value="发送" class="send_btn" id="showAllData_win_open_switchd"/>
			</div>
			<div class = "command_box">
				<span>调光</span>
				<div class="win_processBar">
					<input  class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
				</div>
				<input type="button" value="发送" class="send_btn" id="showAllData_win_open_dimming"/>
			</div>
		</form>
		<div class="dataBox">
			<ul>
				<li class = "a0"><input type="checkbox" id = "selectAllBtnd"></li>
				<li class = "a2">灯具编号</li>
				<li class = "a2">控制器编号</li>
				<li class = "a2">所属道路</li>
				<li class = "a2">配电箱编号</li>
				<li class = "a2">所属区域</li>
				<li class = "a6">电流</li>
				<li class = "a6">电压</li>
				<li class = "a6">功率</li>
				<li class = "a6">温度</li>
				<li class = "a6">在线状态</li>
				<li class = "a6">开关状态</li>
				<li class = "a6">调光值</li>
				<li class = "a6">工作时间</li>
				<li class = "a6">电量</li>
				<li class = "a7">上传时间</li>
			</ul>
			<div class="data_deatil">
				<table></table>
			</div>
		</div>
		<div class="page">
			<div class="pagebox" id = 'controllerPage'></div>
		</div>
	</div>
</div>
</body>
</html>