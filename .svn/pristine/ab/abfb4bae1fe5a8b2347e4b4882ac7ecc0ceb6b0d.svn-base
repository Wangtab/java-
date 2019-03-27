<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/monitor_power.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/echarts.common.min.js" ></script>
    <script type="text/javascript" src="js/monitor_savepower.js" ></script>
    <script type="text/javascript" src = "js/common.js"></script>

</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>节能率分析</p>
    <form action="javascript:;" method="post" class="search_box" onsubmit="searchData()">
        <select name="areaId"></select>
        <select name="roadId">
            <option value = "">请选择道路</option>
        </select>
        <div class="ipt_box">
            <i></i>
            <input name = "power" type="text"   placeholder="请输入理论值" />
        </div>
        <input type="submit" value="查询">
    </form>
    <div class="tody_power">
        <div class="canvens_box" id = "broken"></div>
    </div>
</div>
</body>
</html>