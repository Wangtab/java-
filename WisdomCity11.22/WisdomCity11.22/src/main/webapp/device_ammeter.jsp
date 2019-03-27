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
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/device_ammeter.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>电表管理</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <div class="ipt_box">
            <i></i>
            <input type="text" name="cName" placeholder="电表名称" />
        </div>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby="c_address">电表地址</th>
                <th orderby="c_name">电表名称</th>
                <th orderby="name">配电箱</th>
                <th orderby="elec_box_loop">配电箱回路</th>
                <th orderby="c_flag">通信唯一标志</th>
                <th orderby="real_name">操作者</th>
                <th orderby="oper_time" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
                <th>操作</th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
    <div class="main_table_map" id = "main_table_mapd"></div>
</div>
<form class="table_win" action="javascript:;" method = "post" onsubmit="saveData()">
    <p>详细信息<span id = "close_table_win">X</span></p>
    <input type="hidden" name="id"/>
    <div class="form_box">
        <span>电表地址</span>
        <input type="text" name="cAddress">
    </div>
    <div class="form_box">
        <span>电表名称</span>
        <input type="text" name="cName">
    </div>
    <div class="form_box">
        <span>配电箱</span>
        <select name="elecboxId" id="elecboxId" >
        </select>
    </div>
    <div class="form_box">
        <span>配电箱回路</span>
        <select name="elecBoxLoop" id="elecBoxLoop">
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
    <div class="form_box">
        <span>通信唯一标识</span>
        <input type="text" name="cFlag"/>
    </div>
    <div class="form_box">
        <input type="submit" value="保存">
    </div>
</form>
<div class="judge_win">
    <p>信息<span class = "close_judge_win">X</span></p>
    <h1>确定要删除该条信息吗？</h1>
    <div class="judge_btn">
        <h2 class = "sure_delete_judge_win" url ="deleteAmmeterDataById">确定</h2>
        <h2 class = "close_judge_win">取消</h2>
    </div>
</div>
</body>
</html>
