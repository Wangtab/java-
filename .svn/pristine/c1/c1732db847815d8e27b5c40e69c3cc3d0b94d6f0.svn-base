
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/common_table.css">
    <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src = "js/common.js"></script>
    <script type="text/javascript" src = "js/page.js"></script>
    <script type="text/javascript" src = "js/operation_log.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
</head>
<body>
<h6></h6>
<div class="work_content_box">
    <p>操作日志</p>
    <form action="javascript:;" class="search_box" onsubmit="selectAll()">
        <select name = "selectCname"></select>
        <select name = "selectKind" size="1">
            <option value="">请选择类型</option>
            <option value="10">添加</option>
            <option value="20">修改</option>
            <option value="30">删除</option>
        </select>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="" id="startDate" class = "form-control" placeholder="请输入开始时间" />
        </div>
        <div class="ipt_box">
            <i></i>
            <input type="text" name="" id="endDate" class = "form-control" placeholder="请输入结束时间" />
        </div>
        <input type="submit" value="查询">

    </form>
    <div class="table_box">
        <table>
            <tr>
                <th orderby = "real_name" width="5%">用户名</th>
                <th orderby = "cc" width="5%">操作类型</th>
                <th orderby = "cname" width="5%">操作模块</th>
                <th orderby = "operdes" width="20%">操作内容</th>
                <th orderby = "operTime" sort = "desc" width="15%">操作时间 <img src="images/th_ico_down.png"></th>
            </tr>
            <%--<c:forEach items="${list}" var="item" begin="0" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${item.real_name}</td>
                    <td>
                        <c:if test="${item.kind_id=='10'}">添加</c:if>
                        <c:if test="${item.kind_id=='20'}">修改</c:if>
                        <c:if test="${item.kind_id=='30'}">删除</c:if>
                    </td>
                    <td>${item.cname}</td>
                    <td>${item.operdes}</td>
                    <td>${item.opertime}</td>
                </tr>
            </c:forEach>--%>
        </table>
        <div class="page">
            <div class="pagebox" id = 'controllerPage'></div>
        </div>
    </div>
</div>
</body>
</html>
