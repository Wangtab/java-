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
  <link rel="stylesheet" type="text/css" href="css/alert.css">
  <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
  <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src = "js/common.js"></script>
  <script type="text/javascript" src = "js/alert.js"></script>
  <script type="text/javascript" src = "js/page.js"></script>
  <script type="text/javascript" src = "js/device_area.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
  <p></p>
  <form action="javascript:;" class="search_box" onsubmit="selectAll()">
    <div class="ipt_box">
      <i></i>
      <input type="text" required="required" placeholder="区域名称" />
    </div>
    <input type="submit" value="查询">
    <div class="add_btn"><img src="images/+.png"/> 添加</div>
  </form>
  <div class="table_box">
    <table>
      <tr>
        <th orderby = "areaName">区域名称</th>
        <th orderby = "areaDes">区域描述</th>
        <th orderby = "real_name">操作者</th>
        <th orderby = "operTime" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
        <th>操作</th>
      </tr>
    </table>
    <div class="page">
      <div class="pagebox" id = 'controllerPage'></div>
    </div>
  </div>
  <div class="table_map" id = "main_table_map"></div>
</div>
<form class="table_win" action="javascript:;" onsubmit="saveData()">
  <p>详细信息<span id = "close_table_win">X</span></p>
  <input type="hidden" name="id"/>
  <input type="hidden" name="points"/>
  <div class="form_box">
    <span>区域名称</span>
    <input type="text" name="areaname"/>
  </div>
  <div class="form_box">
    <span>区域描述</span>
    <input type="text" name="areades" allow_null = "ok" />
  </div>
  <div class="win_map" id = "win_map"></div>
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