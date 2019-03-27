<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/menu_manage.js"></script>
    <script type="text/javascript" src = "js/alert.js"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>菜单管理</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <div class="ipt_box">
            <i></i>
            <input type="text"  placeholder="菜单名称" />
        </div>
        <input type="submit" value="查询">
        <div class="add_btn"><img src="images/+.png"/> 添加</div>
    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby="cname">菜单名称</th>
                <th orderby="curl">菜单地址</th>
                <th orderby="img">图片地址</th>
                <th orderby="orderby">排序</th>
                <th orderby="real_name">操作人</th>
                <th orderby="opertime">操作时间</th>
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
        <span>菜单名称</span>
        <input type="text" name="cName"/>
    </div>
    <div class="form_box">
        <span>菜单地址</span>
        <input type="text" name="curl" allow_null = "ok"/>
    </div>
    <div class="form_box">
        <span>图片地址</span>
        <input type="text" name="img" allow_null = "ok"/>
    </div>
    <div class="form_box">
        <span>排序</span>
        <input type="text" name="orderBy"/>
    </div>
    <div class="form_box">
        <span>上级节点</span>
        <select name="pid"></select>
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
</body>
</html>