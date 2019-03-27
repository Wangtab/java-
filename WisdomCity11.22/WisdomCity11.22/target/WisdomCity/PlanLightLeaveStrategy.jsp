<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
    <link rel="stylesheet" type="text/css" href="css/lamp_bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <link rel="stylesheet" type="text/css" href="css/light_leaveStrategy.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/PlanLightLeaveStrategy.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/moment-with-locales.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="js/lamp_bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/plan_analysis.js"></script>
    <style>
        .form_times_box{float:left;position: relative}
    </style>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>离线策略</p>
    <form action="javascript:;" class="search_box" onsubmit="getLightStrategyData()">
        <select name="selectAreaName" id="selGroupArea" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
        </select>
        <select name="selectRoadName" id="selRoad" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
            <option value = "">请选择道路</option>
        </select>
        <select name="selectRoadLine" id="selLight" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
            <option value = "">请选择线路</option>
        </select>
        <select name="selectLampNum" id="lampNum" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
            <option value = "">请选择单灯编号</option>
        </select>
        <input type="submit" value="查询">
    </form>
    <div class="table_box">
        <div class="left_group">
            <table>
                <tr>
                    <th orderBy="lampnum">灯具名称</th>
                    <th orderBy="cname">计划内容</th>
                    <th>操作</th>
                </tr>
                <tr><td  colspan='3'>请先根据条件筛选出相应数据</td></tr>
            </table>
        </div>
        <div class="right_group">
            <table>
                <tr>
                    <th>序号</th>
                    <th>灯控器名称</th>
                    <th>灯号</th>
                    <th>路段名称</th>
                    <th>任务名称</th>
                    <th>计划内容</th>
                    <th>生效时间</th>
                    <th>控制值</th>
                    <th>执行情况</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>SITT_控制器</td>
                    <td>1</td>
                    <td>金闽路</td>
                    <td>监控测试计划</td>
                    <td>开灯</td>
                    <td>15:31</td>
                    <td>100</td>
                    <td>已执行</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<form class="table_win" action="javascript:;" onsubmit="saveData()">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id" id="light_id">
    <input type="hidden" name="lampId" id="win_lampId">
    <div class="form_box">
        <span>计划名称</span>
        <input type="text" name="cname" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
    </div>
    <div class="form_box">
        <span>计划内容</span>
        <select name="contentId" id="light_content">
            <c:forEach var="content" items="${contentList}">
            <option value="${content.id}">${content.cname}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form_box">
        <span>生效时间</span>
        <div class = "form_times_box">
            <input type="text" name="exe_time" class="form-control_hour"/>
        </div>

    </div>
    <div class="form_box change_light_dimming">
        <span>控制值</span>
        <input type="text" name="dimming" />
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
