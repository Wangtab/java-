<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/role_manage.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/role_manage.js"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<div class="selRoleWin">
    <p>权限分配<span>X</span></p>
    <div class="Roletable_box">
        <table cellspacing="0"></table>
    </div>
    <div class="win_btn">
        <div class="win_btn_box">
            <label for="selAllBtn">
                <input type="checkbox" name="" id = "selAllBtn"/>全选
            </label>
            <input type="button" name = 'save_btn' value="保存">
            <input type="button" name = 'rest_btn' value="重置">
        </div>
    </div>
</div>
<h6></h6>
<div class="work_content_box">
    <p>角色管理</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <div class="ipt_box">
            <i></i>
            <input type="text" name="" placeholder="角色名查询" />
        </div>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th>序号</th>
                <th orderby = "role_name">角色名</th>
                <th orderby = "role_desc">角色描述</th>
                <th orderby = "real_name">操作者</th>
                <th orderby = "operTime"  sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
                <th>操作</th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>

<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id">
    <div class="form_box">
        <span>角色名</span>
        <input type="text" name="roleName"/>
    </div>
    <div class="form_box">
        <span>角色描述</span>
        <input type="text" name="roleDesc"/>
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

<div class="btn_win">
    <p>信息<span class = "close_judge_win">X</span></p>
    <table cellspacing="0"></table>
</div>
</body>
</html>
