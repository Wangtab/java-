<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title></title>
  <link rel="shortcut icon" href="images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="css/common.css">
  <link rel="stylesheet" type="text/css" href="css/common_table.css">
  <link rel="stylesheet" type="text/css" href="css/concentrator_set.css">
  <link rel="stylesheet" type="text/css" href="css/alert.css">
  <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
  <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src = "js/common.js"></script>
  <script type="text/javascript" src = "js/page.js"></script>
  <script type="text/javascript" src = "js/alert.js"></script>
  <script type="text/javascript" src = "js/device_concentrator.js"></script>
  <script type="text/javascript" src = "js/xlsx.full.min.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
  <p>集中器配置</p>
  <form action="javascript:;" class="search_box" onsubmit="selectAll()">
    <div class="ipt_box">
      <i></i>
      <input type="text" name="" placeholder="集中器名称" />
    </div>
    <input type="submit" value="查询">
    <div class="add_btn"><img src="images/+.png"/> 添加</div>
    <div class="import_btn" onclick = "controllerDataImp.click()">批量导入</div>
    <div class="import_btn" onclick="downModel()">下载模板</div>
    <input type="file" name="" id="controllerDataImp" style="display: none">
  </form>
  <div class="table_box">
    <table>
      <tr>
        <th orderby = "concentratoraddr">集中器地址</th>
        <th orderby = "concentratorname">集中器名称</th>
        <th orderby = "concentratordes" >集中器描述</th>
        <th orderby = "areaName">所属区域</th>
        <th orderby = "road_name">所属道路</th>
        <th orderby = "name">配电箱编号</th>
        <th orderby = "concentrator_kind">集中器类型</th>
        <th orderby = "concentrator_model">集中器型号</th>
        <th orderby = "factory_name">厂家名称</th>
        <th orderby = "lo">经度</th>
        <th orderby = "la">纬度</th>
        <th orderby = "real_name">操作者</th>
        <th orderby = "opertime">操作时间 <img src="images/th_ico_down.png"></th>
        <th>操作</th>
      </tr>
    </table>
    <div class="page">
      <div class="pagebox" id = 'controllerPage'></div>
    </div>
  </div>
</div>
<div class="judge_win">
  <p>信息<span class = "close_judge_win">X</span></p>
  <h1>确定要删除该条信息吗？</h1>
  <div class="judge_btn">
    <h2 class = "sure_delete_judge_win">确定</h2>
    <h2 class = "close_judge_win">取消</h2>
  </div>
</div>
<form action="javascript:;" class="second_form" onsubmit="saveData()">
  <p>详细信息 <span id = "close_scond_wind">X</span></p>
  <input type="hidden" name = "id" value=""/>
  <div class="form_detail_warpper">
    <div class="detail_box">
      <span>集中器地址</span>
      <input type="text" name="concentratoraddr"/>
    </div>
    <div class="detail_box">
      <span>集中器类型</span>
      <input type="text" name="cKind" />
    </div>
    <div class="detail_box">
      <span>集中器名称</span>
      <input type="text" name="concentratorname" />
    </div>
    <div class="detail_box">
      <span>集中器描述</span>
      <input type="text" name="concentratordes"/>
    </div>
    <div class="detail_box">
      <span>所属区域</span>
      <select name="areaid"></select>
    </div>
    <div class="detail_box">
      <span>所属道路</span>
      <select name="roadId">
        <option>请选择道路</option>
      </select>
    </div>
    <div class="detail_box">
      <span>配电箱编号</span>
      <select name="ibox" ></select>
    </div>
    <div class="detail_box">
      <span>集中器型号</span>
      <input type="text" name="cModel">
    </div>
    <div class="detail_box">
      <span>厂家名称</span>
      <input type="text" name="factoryName">
    </div>
    <div class="detail_box">
      <span>经度</span>
      <input type="text" name="lo" readonly="readonly" id = "longitude_ipt"/>
    </div>
    <div class="detail_box">
      <span>纬度</span>
      <input type="text" name="la" readonly="readonly" id = "latitude_ipt"/>
    </div>
    <div class="win_map" id = "win_mapd"></div>
    <div class="detail_box_submit">
      <input type="submit" value="保存">
    </div>
  </div>
</form>
<form action="" class="concentrator_win">
  <p>详细信息 <span id = "concentrator_win_close">X</span></p>
  <div class="concentrator_box">
    <span>选择配置参数</span>
    <select name="">
      <option value="" status = "max_luminance" >最大亮度值</option>
      <option value="" status = "min_luminance" >最小亮度值</option>
      <option value="" status = "breakdown_luminance" >故障亮度值</option>
      <option value="" status = "luminance_rate" >调光系数</option>
      <option value="" status = "voltage_change" >输入电压变化阀值(0.1v)</option>
      <option value="" status = "electricity_change" >输入电流变化阀值(1MA)</option>
      <option value="" status = "power_change" >功率变化阀值(0.01W)</option>
      <option value="" status = "temperature_change" >温度变化阀值(0.01℃)</option>
      <option value="" status = "power_alarm" >功率报警阀值(0.01W)</option>
      <option value="" status = "temperature_alarm" >温度报警阀值(0.01℃)</option>
      <option value="" status = "import_voltage" >输入电压报警阀值(0.1v)</option>
      <option value="" status = "import_electricity" >输入电流报警阀值(1MA)</option>
      <option value="" status = "synchro_time" >时间同步</option>
      <option value="" status = "aiming_model" >调光模式</option>
      <option value="" status = "chain_setting" >信道设置</option>
      <option value="" status = "again_register_notice" >重新注册通知</option>
      <option value="" status = "clear_concentrator_plan" >集中器清空调光计划</option>
      <option value="" status = "concentrator_luminance_search" >集中器调光查询</option>
      <option value="" status = "ip_setting" >IP和端口设置</option>
    </select>
  </div>
  <div class="son_box max_luminance">
    <span>最大亮度值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box min_luminance">
    <span>最小亮度值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box breakdown_luminance">
    <span>故障亮度值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box luminance_rate">
    <span>调光系数</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box voltage_change">
    <span>阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box electricity_change">
    <span>阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box power_change">
    <span>阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box temperature_change">
    <span>阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box power_alarm">
    <span>下限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box power_alarm">
    <span>上限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box temperature_alarm">
    <span>下限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box temperature_alarm">
    <span>上限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box import_voltage">
    <span>下限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box import_voltage">
    <span>上限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box import_electricity">
    <span>下限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box import_electricity">
    <span>上限阀值</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box aiming_model">
    <span>工作模式</span>
    <select name="">
      <option value="">远程模式</option>
      <option value="">自动模式</option>
    </select>
  </div>
  <div class="son_box chain_setting">
    <span>信道设置</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box ip_setting">
    <span>IP地址</span>
    <input type="text" name="" id="">
  </div>
  <div class="son_box ip_setting">
    <span>端口号</span>
    <input type="text" name="" id="">
  </div>
  <div class="concentrator_box">
    <input type="submit" name="" id="">
  </div>
  <h6></h6>
  <div class="concentrator_info">
    <span>集中器地址：</span>
    <input type="text" name="" value = "114.80.231.178" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>调光工作模式：</span>
    <input type="text" name="" value = "PZ6工作模式" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>软件版本号：</span>
    <input type="text" name="" value = "1.2.1, 2.0, 5.0.0 build-13124" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>系统版本号：</span>
    <input type="text" name="" value = "1.2.1, 2.0, 5.0.0 build-13124" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>内核版本号：</span>
    <input type="text" name="" value = "1.2.1, 2.0, 5.0.0 build-13124" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>硬件版本号：</span>
    <input type="text" name="" value = "1.2.1, 2.0, 5.0.0 build-13124" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>厂家信息：</span>
    <input type="text" name="" value = "上海市金福集中器制造厂" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>工作模式：</span>
    <input type="text" name="" value = "SH_2641" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>状态：</span>
    <input type="text" name="" value = "正常" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>回路状态：</span>
    <input type="text" name="" value = "NA_01回路" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>连接状态：</span>
    <input type="text" name="" value = "已连接" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>zigbee工作信道：</span>
    <input type="text" name="" value = "SH_2641" readonly="readonly"/>
  </div>
  <div class="concentrator_info">
    <span>最后修改时间：</span>
    <input type="text" name="" value = "2017-01-12 12:26:30" readonly="readonly"/>
  </div>
</form>
</body>
</html>