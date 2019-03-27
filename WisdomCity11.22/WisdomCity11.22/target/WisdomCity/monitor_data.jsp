<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/monitor_data.css">
    <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/echarts.min.js" ></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/monitor_center.js"></script>
    <script type="text/javascript" src = "js/monitor_data.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</head>
<body>
<div class="pandect">
    <div class="pandect_left_top">
        <h2>智慧城市公共设施管理平台实时监控中心</h2>
        <a href="菜单.html"></a>
    </div>
    <div class="pandect_left">
        <div class="area_analysis data_common_style">
            <h1>总能耗分析</h1>
            <div class="area_histogram" id = "area_histogramd"></div>
        </div>
        <div class="area_concentrator data_common_style">
            <h1>区域能耗分析</h1>
            <div class="area_curve" id = "area_curved"></div>
        </div>
        <div class="controller_manage data_common_style">
            <h1>控制器管理</h1>
            <div class="controller_list">
                <p>
                    <span>控制器名称</span>
                    <strong>操作时间</strong>
                </p>
                <ul>
                    <li>
                        <span>SITI-00031</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>SITI-00031</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>SITI-00031</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>SITI-00031</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>SITI-00031</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="pandect_middle">
        <div class="pandect_map data_common_style">
            <div id="allmap"></div>
            <div class="controller_light">
                <p>SITI-00031控制勤上灯具QS-00001<span>X</span></p>
                <div class="cl_controller_group_name">
                    <span class = "current_group">单灯</span>
                    <span>路段</span>
                    <span>分组</span>
                    <span>回路</span>
                </div>
                <div class="cl_group_detail">
                    <div class="cgd_box">
                        <span>开关灯</span>
                        <p>
                            <input type="radio" name="light_switch" id = "single_open_light" /><label for="single_open_light">开灯</label>
                            <input type="radio" name="light_switch" id = "single_close_light" checked = 'true' /><label for="single_close_light">关灯</label>
                        </p>
                        <a href="">发送</a>
                    </div>
                    <div class="cgd_box">
                        <span>调光</span>
                        <div class="cgd_processbar">
                            <h2></h2>
                            <i id = "cgd_processbar_point"></i>
                            <div class="processbar_shownum">
                                <i></i>
                                <b id = "processbar_shownum_percent">0%</b>
                            </div>
                        </div>
                        <a href="">发送</a>
                    </div>
                </div>
                <div class="cl_group_detail">
                    <div class="cgd_box">
                        <span>开关灯</span>
                        <p>
                            <input type="radio" name="road_open_light" id = "road_open_light" checked = 'true'/><label for="road_open_light">开灯</label>
                            <input type="radio" name="road_open_light" id = "road_close_light"/><label for="road_close_light">关灯</label>
                        </p>
                        <a href="">发送</a>
                    </div>
                    <div class="cgd_box">
                        <span>调光</span>
                        <div class="cgd_processbar">
                            <h2></h2>
                            <i id = "cgd_processbar_point2"></i>
                            <div class="processbar_shownum">
                                <i></i>
                                <b id = "processbar_shownum_percent2">0%</b>
                            </div>
                        </div>
                        <a href="">发送</a>
                    </div>
                </div>
                <div class="cl_group_detail">
                    <div class="cgd_box">
                        <span>组号</span>
                        <select name="" id="">
                            <option value="">测试组号1226</option>
                            <option value="">测试组号1227</option>
                            <option value="">测试组号1228</option>
                            <option value="">测试组号1229</option>
                        </select>
                    </div>
                    <div class="cgd_box">
                        <span>开关灯</span>
                        <p>
                            <input type="radio" name="light_switch" id = "group_open_light" checked = 'true'/><label for="group_open_light">开灯</label>
                            <input type="radio" name="light_switch" id = "group_close_light"/><label for="group_close_light">关灯</label>
                        </p>
                        <a href="">发送</a>
                    </div>
                    <div class="cgd_box">
                        <span>调光</span>
                        <div class="cgd_processbar">
                            <h2></h2>
                            <i id = "cgd_processbar_point3"></i>
                            <div class="processbar_shownum">
                                <i></i>
                                <b id = "processbar_shownum_percent3">0%</b>
                            </div>
                        </div>
                        <a href="">发送</a>
                    </div>
                </div>
                <div class="cl_group_detail">
                    <div class="cgd_box">
                        <span>选择回路</span>
                        <select name="" id="">
                            <option value="">测试组号1226</option>
                            <option value="">测试组号1227</option>
                            <option value="">测试组号1228</option>
                            <option value="">测试组号1229</option>
                        </select>
                    </div>
                    <div class="cgd_box">
                        <span>回路控制</span>
                        <p>
                            <input type="radio" name="controller_open" id = "single_controller_light_close" checked = 'true' /><label for="single_controller_light_close">闭合</label>
                            <input type="radio" name="controller_open" id = "single_controller_light_open"/><label for="single_controller_light_open">断开</label>
                        </p>
                        <a href="">发送</a>
                    </div>
                </div>
            </div>
            <div class="statistics">
                <div class="statistics_switch">收缩</div>
                <div class="sta_box">
                    <div class="sta_warpper">
                        <p class= "sta_big_ico_open">
                            <img src="images/statistics_open.png"/>
                            <span>开灯</span>
                        </p>
                        <h3>
                            <strong>10个</strong>
                            <span>开灯灯具</span>
                        </h3>
                        <b></b>
                    </div>
                </div>
                <div class="sta_box">
                    <div class="sta_warpper">
                        <p class= "sta_big_ico_close">
                            <img src="images/statistics_close.png"/>
                            <span>关灯</span>
                        </p>
                        <h3>
                            <strong>10个</strong>
                            <span>关灯灯具</span>
                        </h3>
                        <b></b>
                    </div>
                </div>
                <div class="sta_box">
                    <div class="sta_warpper">
                        <p class= "sta_big_ico_unusual">
                            <img src="images/statistics_unusual.png"/>
                            <span>异常</span>
                        </p>
                        <h3>
                            <strong>12个</strong>
                            <span>异常灯具</span>
                        </h3>
                        <b></b>
                    </div>
                </div>
                <div class="sta_box">
                    <div class="sta_warpper">
                        <p class= "sta_big_ico_online">
                            <img src="images/statistics_online.png"/>
                            <span>在线</span>
                        </p>
                        <h3>
                            <strong>50个</strong>
                            <span>在线集中器</span>
                        </h3>
                        <b></b>
                    </div>
                </div>
                <div class="sta_box">
                    <div class="sta_warpper">
                        <p class= "sta_big_ico_outline">
                            <img src="images/statistics_outline.png"/>
                            <span>离线</span>
                        </p>
                        <h3>
                            <strong>12个</strong>
                            <span>离线集中器</span>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="lamp_manage data_common_style" >
            <h1>灯杆管理</h1>
            <div class="lamp_list">
                <div class="lamp_list_title">
                    <p>灯杆编号</p>
                    <p>灯杆类型</p>
                    <p>操作者</p>
                    <p>操作时间</p>
                </div>
                <div class="lamp_list_data">
                    <p>
                        <span>22584601</span>
                        <span>金闽路单叉灯杆</span>
                        <span>王平</span>
                        <span>2017-11-17 18:48:32</span>
                    </p>
                    <p>
                        <span>22584601</span>
                        <span>金闽路单叉灯杆</span>
                        <span>王平</span>
                        <span>2017-11-17 18:48:32</span>
                    </p>
                    <p>
                        <span>22584601</span>
                        <span>金闽路单叉灯杆</span>
                        <span>王平</span>
                        <span>2017-11-17 18:48:32</span>
                    </p>
                    <p>
                        <span>22584601</span>
                        <span>金闽路单叉灯杆</span>
                        <span>王平</span>
                        <span>2017-11-17 18:48:32</span>
                    </p>
                    <p>
                        <span>22584601</span>
                        <span>金闽路单叉灯杆</span>
                        <span>王平</span>
                        <span>2017-11-17 18:48:32</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="pandect_right">
        <div class="today_alarm data_common_style">
            <h1>今日报警</h1>
            <div class="tody_alarm_disc" id= "tody_alarm_discd"></div>
            <div class="alarm_text">
                <p><span class = "span_fine"></span>良好</p>
                <p><span class = "span_allow"></span>合格</p>
                <p><span class = "span_not_allow"></span>较差</p>
            </div>
        </div>
        <div class="tody_power data_common_style">
            <h1>今日功率分析</h1>
            <div class="tody_power_curve" id = "tody_power_curved"></div>
        </div>
        <div class="concentrator_manage data_common_style">
            <h1>集中器管理</h1>
            <div class="concentrator_manage_list">
                <p>
                    <span>集中器名称</span>
                    <strong>操作时间</strong>
                </p>
                <ul>
                    <li>
                        <span>金辉路国城科RTU</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>金辉路国城科RTU</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>金辉路国城科RTU</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>金辉路国城科RTU</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                    <li>
                        <span>金辉路国城科RTU</span>
                        <strong>2017-11-07 18:48:13</strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>