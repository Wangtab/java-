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
    <script type="text/javascript" src = "js/maintain_power_rate.js"></script>
</head>
<body>
<h6></h6> 
<div class="work_content_box">
    <p></p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <select name="selectAreaName"></select>
        <select name="selectRoadName"><option value="">请选择道路</option></select>
        <select name="selectMonth">
            <option value="">请选择月份</option>
            <option value="1">一月</option>
            <option value="2">二月</option>
            <option value="3">三月</option>
            <option value="4">四月</option>
            <option value="5">五月</option>
            <option value="6">六月</option>
            <option value="7">七月</option>
            <option value="8">八月</option>
            <option value="9">九月</option>
            <option value="10">十月</option>
            <option value="11">十一月</option>
            <option value="12">十二月</option>
        </select>
        <input type="submit" value="查询">
        <div class="excel_btn" onclick="exportExcel()">导出Excel</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby = "price">单价(元)</th>
                <th orderby = "energy">能耗(千瓦时)</th>
                <th orderby = "sum_price">总费用(万元)</th>
                <th orderby = "record_time">统计月份 <img src="images/th_ico_down.png"></th>
                <th orderby = "org_name">所属组织</th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>
</body>
</html>