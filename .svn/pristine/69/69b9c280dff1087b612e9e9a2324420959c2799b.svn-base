<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/scene_manage.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/scene_manage.js"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<input type="hidden" id="save_group_data">
<div class="work_content_box">
    <p>场景管理</p>
    <form action="javascript:;" onsubmit="selectAll()" class="search_box">
        <span>区域选择：</span>
        <select id="selGroupArea">
            <option value="">请选择区域</option>
            <c:forEach var="area" items="${areaList}">
                <option value="${area.id}">${area.areaName}</option>
            </c:forEach>
        </select>
        <span>分组选择：</span>
        <select id="selGroupd">
            <option value="">请先选择区域</option>
        </select>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="group_box">
        <div class="left_group">
            <table>
                <tr>
                    <th>所属区域</th>
                    <th>所属分组</th>
                    <th>场景名称</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="sence" items="${senceList}">
                <tr test_light = "${sence.test_light}" sence_id = "${sence.id}">
                    <td>${sence.areaName}</td>
                    <td>${sence.group_name}</td>
                    <td>${sence.cname}</td>
                    <td>
                        <a title = "修改" sence_id = "${sence.id}" area_id = "${sence.area_id}" group_id = "${sence.group_id}" test_light = "${sence.test_light}" class = "edit_button" href="javascript:void(0);"></a>
                        <a title = "删除" sence_id = "${sence.id}" class = "delete_button" href="javascript:void(0);"></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div class="right_group">
            <table>
                <tr>
                    <th>场景名称</th>
                    <th>场景调光值</th>
                    <th>分组</th>
                    <th>区域</th>
                    <th>执行情况</th>
                </tr>
            </table>
        </div>
    </div>
</div>
<form class="table_win" action="savePlanSenceData" method = "post">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id" id="save_sence_id"/>
    <div class="form_box">
        <span>场景名称</span>
        <input type="text" id="cnamed" name="cname" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
    </div>
    <div class="form_box">
        <span>区域名称</span>
        <select  id="win_area_sel" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
            <option value="">请选择区域</option>
            <c:forEach var="area" items="${areaList}">
                <option value="${area.id}">${area.areaName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form_box">
        <span>分组名称</span>
        <select name="groupId" id="win_group_sel" required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')">
            <option value="">请先选择区域</option>
        </select>
    </div>
    <div class="form_box">
        <span>调光值</span>
        <input type="text" id="test_light" name="test_light" maxlength="3" onkeyup="javascript:this.value=this.value.replace(/[^\d]/g,'');if(this.value<0){
this.value=0;};if(this.value>100){this.value=100;}" onchange="javascript:this.value=this.value.replace(/[^\d]/g,'');if(this.value<0){
this.value=0;};if(this.value>100){this.value=100;}"  required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>&nbsp;%
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

<div class="sence_wind">
    <p>场景执行情况<span>X</span></p>
    <div class="sence_wind_table_box">
        <table>
            <tr>
                <th>序号</th>
                <th>控制器名称</th>
                <th>分组</th>
                <th>执行情况</th>
            </tr>
            <tr>
                <td>1</td>
                <td>控制器名称1</td>
                <td>分组2</td>
                <td>未执行</td>
            </tr>
        </table>
    </div>
    <div class="sence_wind_btn">关闭</div>
</div>

</body>
</html>
