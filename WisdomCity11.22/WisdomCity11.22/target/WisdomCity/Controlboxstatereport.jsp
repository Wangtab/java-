<%--
  Created by IntelliJ IDEA.
  User: Hao Cheung
  Date: 2018/4/18
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/concen_status_setting.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>控制箱状态报表</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <select name = "selectAreaName" id ="areaIdSel">
            <option value="">请选择区域</option>
        </select>
        <select name = "selectRoadName" id="selRoad">
            <option value="">请选择道路</option>
        </select>
        <select name = "concenSelect" id="concenSelect">
            <option value="">请选择集中器</option>
        </select>
        <input type="submit" value="查询">
        <div class="excel_btn" id="excel_btnd">导出Excel</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th sort="id">序号</th>
                <th sort="areaName">区域名称</th>
                <th sort="concentratorname">集中器名称</th>
                <th sort="link_status">联网状态</th>
                <th sort="a_ele">A相电压</th>
                <th sort="a_pov">A相电流</th>
                <th sort="a_power">A相功率</th>
                <th sort="b_ele">B相电压</th>
                <th sort="b_pov">B相电流</th>
                <th sort="b_power">B相功率</th>
                <th sort="c_ele">C相电压</th>
                <th sort="c_pov">C相电流</th>
                <th sort="c_power">C相功率</th>
                <th sort="temp">温度</th>
                <th sort="record_time">上传时间</th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>
<form class="table_win" action="" method = "post">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <div class="form_box">
        <span>类别</span>
        <select name="">
            <option value="">作为子节点</option>
            <option value="">作为同级点</option>
        </select>
    </div>
    <div class="form_box">
        <span>设备类型</span>
        <select name="">
            <option value="">作为子节点</option>
            <option value="">作为同级点</option>
        </select>
    </div>
    <div class="form_box">
        <span>入库时间</span>
        <input type="text" name="" class="form-control"   required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
    </div>
    <div class="form_box">
        <span>改变数量</span>
        <input type="text" name=""   required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
    </div>
    <div class="form_box">
        <span>备注</span>
        <input type="text" name="" >
    </div>
    <div class="form_box">
        <input type="submit" value="保存">
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
