<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/group_strategy.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/group_strategy.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="js/moment-with-locales.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>分组策略</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <div class="ipt_box">
            <i></i>
            <input type="text" name="name" placeholder="计划名称" />
        </div>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="table_box">
        <div class="left_group">
            <table>
                <tr>
                    <th orderBy="areaName">所属区域</th>
                    <th orderBy="group_name">所属分组</th>
                    <th orderBy="cname">计划名称</th>
                    <th>操作</th>
                </tr>
            </table>
        </div>
        <div class="right_group">
            <table>
                <tr>
                    <th>序号</th>
                    <th orderBy="con_name">集中器名称</th>
                    <th orderBy="content_name">计划内容</th>
                    <th orderBy="start_time">起止日期</th>
                    <th orderBy="open_time">起止时间</th>
                    <th orderBy="dimming">控制值</th>
                    <th orderBy="is_work">执行情况</th>
                </tr>
            </table>
        </div>
    </div>
</div>
<form class="table_win" action="javascript:;" onsubmit=" return  addPlanStrategy()">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id" id="win_stragegy_id">
    <div class="form_box">
        <span>计划名称</span>
        <input type="text" name="cname" id="planName"  required="required" oninvalid="setCustomValidity('请填写该条信息')" oninput="setCustomValidity('')"/>
    </div>
    <div class="form_box">
        <span>区域</span>
        <select id="win_area_sel"  required="required" oninvalid="setCustomValidity('请选择该条信息')" oninput="setCustomValidity('')">
            <option value="">请选择区域</option>
            <c:forEach var="area" items="${areaList}">
                <option value="${area.id}">${area.cname}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form_box">
        <span>分组</span>
        <select id="win_group_sel" name="groupId" required="required" oninvalid="setCustomValidity('请选择该条信息')" oninput="setCustomValidity('')">
        </select>
    </div>
    <div class="form_box">
        <span>计划内容</span>
        <select name = "contentId" id="contentId">
            <c:forEach var="content" items="${contentList}">
                <option value="${content.id}">${content.cname}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form_box">
        <span>生效日期</span>
        <input type="text" name="startTime"  class="form-control"/>
    </div>
    <div class="form_box">
        <span>失效日期</span>
        <input type="text"  name="endTime" class="form-control"/>
    </div>
    <div class="form_box">
        <span>开灯时间</span>
        <input type="text" name="openTime" class="form-control_hour"/>
    </div>
    <div class="form_box" id="closeLightTime">
        <span>关灯时间</span>
        <input type="text" name="closeTime"  class="form-control_hour"/>
    </div>
    <div class="form_box">
        <span>调光值</span>
        <input type="text" name = "dimming" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name=""/>
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