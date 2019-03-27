<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <link rel="stylesheet" type="text/css" href="css/energy_analysis.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/echarts-all.js" ></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="js/analysis_repair.js" charset="UTF-8"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/function.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p></p>
    <form action="javascript:;" method="post" class="search_box" onsubmit="selectAll()">
        <select name="areaId"></select>
        <select name="roadId"><option value = "">请选择道路</option></select>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="startDate" class = "form-control" placeholder="请输入开始时间" />
        </div>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="endDate" class = "form-control" placeholder="请输入结束时间" />
        </div>
        <input type="submit" value="查询" id="search_btn">
        <div  class="import_btn">导出数据</div>
    </form>

    <div class="area_analysis">
        <div class="table_list">
            <div class="analysis_title">维修列表</div>
            <table>
                <tr>
                    <th>名称</th>
                    <th>次数</th>
                </tr>

            </table>
            <div class="page">
                <div class="pagebox" id = 'controllerPage'></div>
            </div>
        </div>
        <div class="area_model" id = "areaPower"></div>
    </div>
</div>
</body>
</html>