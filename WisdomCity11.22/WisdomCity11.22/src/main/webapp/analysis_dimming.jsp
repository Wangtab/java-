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
    <link rel="stylesheet" type="text/css" href="css/dimming_analysis.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/echarts-all.js" ></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="js/analysis_dimming.js" charset="UTF-8"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>亮灯率分析</p>
    <form action="javascript:void(0);" method="post" class="search_box" onsubmit="tableSelect()">
        <select name = "areaId"></select>
        <select name = "roadId"><option value="">请选择道路</option></select>
        <select name = "lineId"><option value="">请选择线路</option></select>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="startDate" id="startDated" class = "form-control" placeholder="请输入开始时间" />
        </div>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="endDate" id="endDated" class = "form-control" placeholder="请输入结束时间" />
        </div>
        <input type="hidden" id="num" name="num" value="">
        <input type="hidden" id="sort" name="sort" value="">
        <input type="submit" value="查询" id="search_btn">
        <div class="import_btn">导出数据</div>
        <input type="hidden" name="curPage"/>
        <input type="hidden" name="showNum"/>
    </form>

    <div class="area_analysis">
        <div class="table_list">
            <div class="analysis_title">平均亮灯率列表</div>
            <table>
                <tr>
                    <th>名称</th>
                    <th>平均亮灯率(%)</th>
                </tr>
            </table>
            <div class="page">
                <div class="pagebox" id = 'controllerPage'></div>
            </div>
        </div>
        <div class="area_model" id = "dimmingChart"></div>
    </div>
</div>
</body>
</html>