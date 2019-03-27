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
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/dianbiao_status.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>电表状态报表</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <select name="selectAreaName"></select>
        <select name="selectRoadName"><option value="">请选择道路</option></select>
        <input type="submit" value="查询">
        <div class="excel_btn" onclick="exportExcel()">导出Excel</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby = "areaName" width = "15%">区域名称</th>
                <th orderby = "roadName" width = "20%">道路名称</th>
                <th orderby = "name" width = "10%">配电箱</th>
                <th orderby = "c_name" width = "10%">电表名称</th>
                <th orderby = "num" width = "15%">耗电量</th>
                <th orderby = "recordTime" width = "30%">记录月份 <img src="images/th_ico_down.png"></th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>
</body>
</html>