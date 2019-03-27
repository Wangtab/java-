<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/roadLine_manage.js"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>线路管理</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <select name = "areaId"></select>
        <select name = "roadId">
            <option value="">请选择道路</option>
        </select>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="cName" placeholder="线路名称" />
        </div>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby = "areaName">所属区域</th>
                <th orderby = "road_name">所属道路</th>
                <th orderby = "cname">线路名称</th>
                <th orderby = "oper_name">操作者</th>
                <th orderby = "oper_time" sort = "desc">操作时间<img src="images/th_ico_down.png"></th>
                <th width="10%">操作</th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>
<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id"/>
    <div class="form_box">
        <span>所属区域</span>
        <select name="selectAreaName"></select>
    </div>
    <div class="form_box">
        <span>所属道路</span>
        <select name="roadId">
            <option value="">请选择所属道路</option>
        </select>
    </div>
    <div class="form_box">
        <span>线路名称</span>
        <input type="text" name="cName"/>
    </div>
    <div class="form_box">
        <input type="submit" value="保存">
    </div>
</form>
<div class="judge_win">
    <p>信息<span class = "close_judge_win">X</span></p>
    <h1>确定要删除该条信息吗？</h1>
    <div class="judge_btn">
        <h2 class = "sure_delete_judge_win" url = "deleteRoadLineManageById">确定</h2>
        <h2 class = "close_judge_win">取消</h2>
    </div>
</div>
</body>
</html>
