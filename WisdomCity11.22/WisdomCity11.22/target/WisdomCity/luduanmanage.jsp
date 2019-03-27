<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title></title>
  <link rel="shortcut icon" href="images/favicon.ico"/>
  <link href="css/bootstrap.min1.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/common.css">
  <link rel="stylesheet" type="text/css" href="css/common_table.css">
  <link rel="stylesheet" type="text/css" href="css/road_manage.css">
  <link rel="stylesheet" type="text/css" href="css/alert.css">
  <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
  <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src = "js/common.js"></script>
  <script type="text/javascript" src = "js/page.js"></script>
  <script type="text/javascript" src = "js/road_manage.js"></script>
  <script type="text/javascript" src = "js/alert.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
  <p>道路管理</p>
  <form action="javascript:;" class="search_box" onsubmit="selectAll()">
    <select name = "selAreaName"></select>
    <div class="ipt_box">
      <i></i>
      <input type="text" id="roadNameData" name="roadName" placeholder="请输入道路名称" />
    </div>
    <input type="submit" value="查询">
    <div class="add_btn"><img src="images/+.png"/> 添加</div>
  </form>
  <div class="table_box">
    <table>
      <tr>
        <th orderBy = "road_name">道路名称</th>
        <th orderBy = "road_des">道路描述</th>
        <th orderBy = "areaName">所属区域</th>
        <th orderBy = "real_name">操作者</th>
        <th orderBy = "oper_time" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
        <th>操作</th>
      </tr>
    </table>
    <div class="page">
      <div class="pagebox" id = 'controllerPage'></div>
    </div>
  </div>
  <div class="table_map" id = "main_table_mapd"></div>
</div>

<form class="table_win" action="javascript:;" onsubmit="saveData()">
  <p>详细信息<span id = "close_table_win">X</span></p>
  <input type="hidden" name="id"/>
  <div class="form_box">
    <span>道路名称</span>
    <input type="hidden" name="points" id="win_points">
    <input type="text" required="required" name="roadName">
  </div>
  <div class="form_box">
    <span>道路描述</span>
    <input type="text" allow_null = "ok" name="roadDes" >
  </div>
  <div class="form_box">
    <span>区域名称</span>
    <select name="areaId" id="areaIdSel"></select>
  </div>
  <div class="win_map" id = "win_mapd"></div>
  <div class="form_box">
    <input type="submit" value="保存">
    <input type="button" value="清除所有点">
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