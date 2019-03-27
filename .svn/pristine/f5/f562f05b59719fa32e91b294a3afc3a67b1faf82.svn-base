<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/group_manage.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/plan_group_manage.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>分组管理</p>
    <form action="javscript:;" class="search_box" onsubmit="selectAll()">
        <select name="areaId" id = "manSeld">
            <option value="">区域选择</option>
            <c:forEach var="area" items="${areaList}">
                <option value="${area.id}">${area.areaName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="group_box">
        <div class="left_group">
            <table>
                <tr class="left_group_th">
                    <th>&nbsp;</th>
                    <th cur="group_name">分组名称</th>
                    <th cur="group_code">分组编码</th>
                    <th cur="areaName">所属区域</th>
                    <th>操作</th>
                    <input type="hidden" id="sort" value="">
                    <input type="hidden" id="orderMsg" value="">
                </tr>
                <c:forEach var="plan" items="${groupList}">
                <tr plan_id = "${plan.id}">
                    <td><input type="checkbox" name=""/></td>
                    <td>${plan.group_name}</td>
                    <td>${plan.group_code}</td>
                    <td>${plan.areaName}</td>
                    <td>
                        <a title = "添加" plan_id = "${plan.id}" area_id = "${plan.area_id}" class = "add_button" href="javascript:void(0);"></a>
                        <a title = "修改" plan_id = "${plan.id}" area_id = "${plan.area_id}"   class = "edit_button" href="javascript:void(0);"></a>
                        <a title = "删除" plan_id = "${plan.id}" class = "delete_button" href="javascript:void(0);"></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div class="right_group">
            <table>
                <tr>
                    <th>编号</th>
                    <th>集中器名称</th>
                    <th>灯控器名称</th>
                    <th>灯具名称</th>
                    <th>灯号</th>
                    <th>所属线路</th>
                    <th>执行情况</th>
                </tr>
            </table>
        </div>
    </div>
</div>
<form class="table_win" action="javascript:;" onsubmit="saveData()">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id" id="plan_id" value=""/>
    <div class="form_box">
        <span>选择区域</span>
        <select name="areaId">
            <c:forEach var="area" items="${areaList}">
                <option value="${area.id}">${area.areaName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form_box">
        <span>分组名称</span>
        <input type="text" name="groupName" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
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
<div class="light_group_win">
    <p>灯控器分组<span id = "light_group_win_close">X</span></p>
    <div class="search_box">
        <span>道路选择</span>
        <select id="roadSeld"></select>
    </div>
    <div class="light_group_win_table_box">
        <div class="left_table win_main_table">
            <ul>
                <li style = "width:41px">编号</li>
                <li style = "width:150px">集中器名称</li>
                <li style = "width:99px">灯控器名称</li>
                <li style = "width:86px">灯具名称</li>
                <li style = "width:42px">灯号</li>
                <li style = "width:78px">所属路线</li>
                <li style = "width:36px">操作</li>
            </ul>
            <div class="main_table">
                <table></table>
            </div>
        </div>
        <div class="right_table win_main_table">
            <ul>
                <li style = "width:41px">编号</li>
                <li style = "width:155px">集中器名称</li>
                <li style = "width:99px">灯控器名称</li>
                <li style = "width:90px">灯具名称</li>
                <li style = "width:52px">灯号</li>
                <li style = "width:68px">所属路线</li>
                <li style = "width:62px">执行情况</li>
                <li style = "width:36px">操作</li>
            </ul>
            <div class="main_table">
                <table></table>
            </div>
        </div>
    </div>
    <div class="button_box">
        <input type="button" value="提交" id="subGroupBtn">
    </div>
</div>


</body>
</html>