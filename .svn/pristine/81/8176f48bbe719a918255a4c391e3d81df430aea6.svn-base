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
    <link rel="stylesheet" type="text/css" href="css/controller_setting.css">
    <link rel="stylesheet" type="text/css" href="css/alert.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
    <script type="text/javascript" src = "js/controller_setting.js"></script>
    <script type="text/javascript" src = "js/xlsx.full.min.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>控制器配置</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <div class="ipt_box">
            <i></i>
            <input type="text" name="" placeholder="设备编号" />
        </div>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
        <div class="import_btn" onclick = "controllerDataImp.click()">批量导入</div>
        <div class="import_btn" onclick="downModel()">下载模板</div>
        <input type="file" name="" id="controllerDataImp" style="display: none">
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby = "nb_code1">设备编号</th>
                <th orderby = "kindname">控制器类型</th>
                <th orderby = "c_num">控制器型号</th>
                <th orderby = "factory_name">厂家名称</th>
                <th orderby = "concentratorname">所属集中器</th>
                <th orderby = "business">运营商</th>
                <th orderby = "protocol">协议类型</th>
                <th orderby = "nb_device">NB设备编号</th>
                <th orderby = "sim_code">SIM卡号</th>
                <th orderby = "realName">操作者</th>
                <th orderby = "opertime" sort = "desc">操作时间 <img src="images/th_ico_down.png"></th>
                <th>操作</th>
            </tr>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>
<div class="judge_win">
    <p>信息<span class = "close_judge_win">X</span></p>
    <h1>确定要删除该条信息吗？</h1>
    <div class="judge_btn">
        <h2 class = "sure_delete_judge_win">确定</h2>
        <h2 class = "close_judge_win">取消</h2>
    </div>
</div>
<form action="javascript:;" class="second_form" onsubmit="saveData()">
    <p>详细信息 <span id = "close_scond_wind">X</span></p>
    <input type="hidden" name = "id"/>
    <div class="form_detail_warpper">
        <div class="detail_box">
            <span>设备编号</span>
            <input type="text" name="nbCode"/>
        </div>
        <div class="detail_box">
            <span>控制器类型</span>
            <select name="controllerkindid"></select>
        </div>
        <div class="detail_box">
            <span>控制器型号</span>
            <input type="text" name="cNum" />
        </div>
        <div class="detail_box">
            <span>厂家名称</span>
            <input type="text" name="factoryName"/>
        </div>
        <div class="detail_box">
            <span>所属集中器</span>
            <select name="concentratorId"></select>
        </div>
        <div class="detail_box">
            <span>运营商</span>
            <select name="business"/>
                <%--<option value="1">中国移动</option>
                <option value="2">中国联通</option>--%>
                <option value="3">中国电信IOT平台</option>
                <option value="4">上海产业院IOT平台</option>
            </select>
        </div>
        <div class="detail_box">
            <span>协议类型</span>
            <select name="protocol">
                <option value="0">UDP协议</option>
                <option value="1">COAP协议</option>
            </select>
        </div>
        <div class="detail_box">
            <span>SIM卡号</span>
            <input type="text" name="simCode"/>
        </div>
        <div class="detail_box_submit">
            <input type="submit" value="保存">
        </div>
    </div>
</form>
</body>
</html>
