<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>地图展示</title>
  <link rel="shortcut icon" href="images/favicon.ico" />
  <link href="css/bootstrap.min1.css" rel="stylesheet">
  <link href="css/bootstrap-slider.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/common.css">
  <link rel="stylesheet" type="text/css" href="css/monitor_center.css">
  <link rel="stylesheet" type="text/css" href="css/alert.css">
  <script type="text/javascript" src = "js/alert.js"></script>
  <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src = "js/common.js"></script>
  <script type="text/javascript" src = "js/page.js"></script>
  <script type="text/javascript" src = "js/alert.js"></script>
  <script type="text/javascript" src = "js/monitor_center.js"></script>
  <script type="text/javascript" src = "js/bootstrap-slider.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
</head>
<body>
<div class="device_num">
  <ul>
    <li class = "data_list_btn">数据网格</li>

    <li><img src="images/statistics_open.png"  title="开灯个数"/><span class = "open_lamp">0</span></li>
    <li><img src="images/statistics_close.png" title="关灯个数" /><span class = "close_lamp">0</span></li>
    <li><img src="images/statistics_unusual.png" title="异常个数"/><span class = "expection_lamp">0</span></li>
    <li><img src="images/statistics_online.png" title="配电箱在线" /><span class = "online_exe">0</span></li>
    <li><img src="images/statistics_outline.png" title="配电箱离线" /><span class = "offline_exe">0</span></li>


  </ul>
</div>

<div class="controller_light lamp_controller_box">
  <p><i>详细信息</i><span>X</span></p>
  <div class="cl_controller_group_name">
    <span class = "current_group"  status= "single_lamp">单灯</span>
    <span status= "road_lamp">道路</span>
    <span status= "group_lamp">分组</span>
    <span status = "loop_lamp">回路</span>
  </div>
  <div class="cl_group_detail">
    <div class="cgd_box">
      <span>开关灯</span>
      <p>
        <input type="radio" value="0" name="light_switch" id = "single_open_light" checked = 'true' /><label for="single_open_light">开灯</label>
        <input type="radio" value="1" name="light_switch" id = "single_close_light"/><label for="single_close_light">关灯</label>
      </p>
      <a href="javascript:;" onclick="single_light_switch(0)">发送</a>
    </div>
    <div class="cgd_box">
      <span>调光</span>
      <div class="cgd_processbar">
        <input id="single_light_dimming" class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
      </div>
      <a href="javascript:;" onclick="single_light_switch(1)">发送</a>
    </div>
  </div>
  <div class="cl_group_detail">
    <div class="cgd_box">
      <span>开关灯</span>
      <p>
        <input type="radio" value="0" name="road_open_light" id = "road_open_light" checked = 'true'/><label for="road_open_light">开灯</label>
        <input type="radio" value="1" name="road_open_light" id = "road_close_light"/><label for="road_close_light">关灯</label>
      </p>
      <a href="javascript:void(0);" onclick = "road_light_switch(0)">发送</a>
    </div>

    <div class="cgd_box">
      <span>调光</span>
      <div class="cgd_processbar">
        <input id="road_light_dimming" class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
      </div>
      <a href="javascript:void(0);" onclick = "road_light_switch(1)">发送</a>
    </div>
  </div>
  <div class="cl_group_detail">
    <div class="cgd_box">
      <span>组号</span>
      <select name=""></select>
    </div>
    <div class="cgd_box">
      <span>开关灯</span>
      <p>
        <input type="radio" value="0" name="light_switch1" id = "group_open_light" checked = 'true'/><label for="group_open_light">开灯</label>
        <input type="radio" value="1" name="light_switch1" id = "group_close_light"/><label for="group_close_light">关灯</label>
      </p>
      <a href="javascript:void(0);" onclick="group_light_switch(0)">发送</a>
    </div>
    <div class="cgd_box">
      <span>调光</span>
        <input id="group_light_dimming" name="dimming" class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
      <a href="javascript:void(0);" onclick="group_light_switch(0)">发送</a>
    </div>
  </div>
  <div class="cl_group_detail">
    <div class="cgd_box">
      <span>选择回路</span>
      <select>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        <option value="13">13</option>
        <option value="14">14</option>
        <option value="15">15</option>
        <option value="16">16</option>
      </select>
    </div>
    <div class="cgd_box">
      <span>回路控制</span>
      <p>
        <input type="radio" value="0" name="controller_open" id = "single_controller_light_close"  /><label for="single_controller_light_close">闭合</label>
        <input type="radio" value="1" name="controller_open" id = "single_controller_light_open" checked = 'true'/><label for="single_controller_light_open">断开</label>
      </p>
      <a href="javascript:void(0);" onclick = "loop_light_switch()">发送</a>
    </div>
  </div>
</div>
<div id="allmap"></div>
<div class="watting_box">
  <img src="images/watting.jpg">
  正在执行中 请勿进行任何操作...
</div>
<div class="watting_bg"></div>
<div class="controller_light concentrator_box">
  <p>详细信息<span>X</span></p>
  <div class="cl_controller_group_name">
    <span class = "current_group" style="width:190px">网关</span>
    <span style="width:200px">回路</span>
  </div>
  <div class="cl_group_detail">
    <div class="cgd_box">
      <span>开关灯</span>
      <p>
        <input type="radio" value="0" name="light_switch11" id = "concentrator_open_light" /><label for="concentrator_open_light">开灯</label>
        <input type="radio" value="1" name="light_switch11" id = "concentrator_close_light" checked = 'true' /><label for="concentrator_close_light">关灯</label>
      </p>
      <a href="javascript:;" onclick = "concentrator_light_switch(0)">发送</a>
    </div>
    <div class="cgd_box">
      <span>工作模式</span>
      <select name="" style = "width: 170px">
        <option value="">自动模式</option>
        <option value="">远程模式</option>
      </select>
      <a href="javascript:;" onclick="concentratorAutoModel()">发送</a>
    </div>
    <div class="cgd_box">
      <span>调光</span>
      <div class="cgd_processbar">
        <input id="concentrator_light_dimming" class = "dimming_light" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0"/>
      </div>
      <a href="javascript:;" onclick = "concentrator_light_switch(1)">发送</a>
    </div>
  </div>
  <div class="cl_group_detail">
    <div class="cgd_box">
      <span>选择回路</span>
      <select>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        <option value="13">13</option>
        <option value="14">14</option>
        <option value="15">15</option>
        <option value="16">16</option>
      </select>
    </div>
    <div class="cgd_box">
      <span>回路控制</span>
      <p>
        <input type="radio" value="0" name="controller_open" id = "concentrator_loop_open" checked = 'true' /><label for="concentrator_loop_open">闭合</label>
        <input type="radio" value="1" name="controller_open" id = "concentrator_loop_close"/><label for="concentrator_loop_close">断开</label>
      </p>
      <a href="javascript:;" onclick = "concentrator_loop_switch()">发送</a>
    </div>
  </div>
</div>

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
<div class="roadList" obj_location = "30">
  <i></i>
  <h1><img src="images/list_detail_title_pic.png" alt="">详细信息</h1>
  <div class="roadList_deatail"></div>
</div>
<div class="roadList_btn common_screen_btn"></div>
</body>
<script>
    $(function(){
      proUntil.request("screen/getSingleData",{},function(data) {
        data = JSON.parse(data)[0];
        var lampList = $('.device_num');
        lampList.find('.open_lamp').html(data.lamp_open_num);
        lampList.find('.close_lamp').html(data.lamp_close_num);
        lampList.find('.expection_lamp').html(data.lamp_exp_num);
        lampList.find('.online_exe').html(data.ele_open_num);
        lampList.find('.offline_exe').html(data.ele_close_num);
      });
    })

</script>
</html>