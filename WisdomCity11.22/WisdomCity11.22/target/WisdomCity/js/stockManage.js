var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10;//每页显示多少条数据
var orderBy = "operTime";//排序字段
var sort = "desc";

$(function() {
    init_data();
    init_btn();

    $('.form-control').datepicker({
        language: 'zh-CN',
        format: "yyyy-mm-dd"
    });


function promptBox(obj,msg) {
    obj.dialog1 = jqueryAlert({
        'content': msg,
        'closeTime': alert_common_time,
    });
}
});
//类型下拉菜单
 function init_data(){
    var winCatSelect = $('.table_win').find('select[name ="catId"]');
    var CatSelect = $('.work_content_box').find('select[name ="cname"]')
    var url="/queryTcategoryAll.do";
    var default_text='<option value = "">请选择类别</option>';
    proUntil.request(url,{},function(data) {
        var html = default_text || '';
        data = JSON.parse(data);
        $.each(data, function (i, val) {
            html += '<option value = "' + val.catId + '">' + val.catName + '</option>';
        });
        winCatSelect.html(html);
        CatSelect.html(html);
    });
     selectAll();
 }

//保存信息
function saveData(){

    proUntil.saveData("../addAssessmentStock.do",$('.table_win'),function(){
        selectAll()
    });
}
//查询全部
function selectAll(){

    var cname=$('.search_box').find('select[name="cname"]').val();
    var equipName=$('.search_box').find('input[name="equipName"]').val();
    proUntil.request("../queryAssessmentStock.do",{sort:sort,orderBy:orderBy,showNum:page_show_Num,curPage:cur_page,cname:cname,equipName:equipName},function(data){
        var table = $('.table_box').find('table');
        var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
        var html = "";
        if('n' == data){
            table.html('');
            check_table_data();
            return;
        }
        data = JSON.parse(data)[0];
        count_page = Math.ceil(parseInt(data.count)/page_show_Num);
        $.each(data.data, function(index, val){
            html += '<tr>\n' +
                '<td>'+val.catName+'</td>\n' +
                '<td>'+val.equipName+'</td>\n' +
                '<td>'+val.stocknum+'</td>\n' +
                '<td>'+val.changeNum+'</td>\n' +
                '<td>'+val.endopertime+'</td>\n' +
                '<td>'+val.node+'</td>\n' +
                '<td>'+val.realName+'</td>\n' +
                '<td>'+val.opertime+'</td>\n' +
                '<td>\n' +
                '<a title = "修改" stock_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                '<a title = "删除" stock_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                '</td>\n' +
                '</tr>';
            var ishas = proUntil.strIsEmpty(val.points);
            if(!ishas){
                //绘制区域
                drawAreas(val.points,val.areaName);
            }
        });
        table.html(frist_tr + html);
        $("#controllerPage").html('');
        page({
            id:'controllerPage',
            nowNum: cur_page,
            allNum: count_page
        });

        //  网络数据分页点击
        $("#controllerPage").find("a").unbind('click').click(function(){
            var _this = $(this);
            var curpage = _this.attr('cur');
            cur_page = parseInt(curpage);
            selectAll();
        });
         check_table_data();
         init_btn();
         deal_table_th();
    });
}

function init_btn() {
//修改窗口

    $('.add_btn').click(function () {
        proUntil.clear_form_data($('.table_win'));
    });

    $('.edit_button').click(function () {
        proUntil.request("../queryTStock.do", {"id": $(this).attr('stock_id')}, function (data) {
            var val = JSON.parse(data)[0];
            var box = $('.table_win');
            box.find('input[name="id"]').val(val.id);
            box.find('select[name="catId"]').val(val.catId);
            box.find('input[name="equipName"]').val(val.equipName);
            box.find('input[name="totime"]').val(val.totime);
            box.find('input[name="stocknum"]').val(val.stocknum);
            box.find('input[name="node"]').val(val.node);
            box.show(manage_table_time);
        });
    });




    //打开删除窗口
    $('.delete_button').unbind('click').click(function() {
        var _this = $(this);
        $('.sure_delete_judge_win').attr('stock_id',_this.attr('stock_id'));
        table_delete_object =  _this.parents('tr');
        $('.judge_win').show(manage_table_time);
    });
//删除窗口 确定按钮
    $('.sure_delete_judge_win').unbind('click').click(function(){
        proUntil.delData("../selectTStock.do",{"id":$(this).attr('stock_id')},$('.judge_win'),function(){
            selectAll();
        });
    });
}



// 标题排序
function deal_table_th(){
    $('.table_box').find('th').not(':last').click(function(){
        var _this = $(this);
        var th_sort = _this.attr('sort');
        $('.table_box').find('th').removeAttr('sort');
        $('.table_box').find('th').find('img').remove();
        if(proUntil.strIsEmpty(sort)){
            th_sort = "desc";
            _this.html(_this.html() + '<img src="images/th_ico_down.png">');
        } else if ("asc" == sort){
            th_sort = "desc";
            _this.html(_this.html() + '<img src="images/th_ico_down.png">');
        } else if("desc" == sort){
            th_sort = "asc";
            _this.html(_this.html() + '<img src="images/th_ico_up.png">');
        }
        _this.attr('sort',th_sort);
        orderBy =  _this.attr("orderBy");
        sort = th_sort;
        selectAll();
    });
}
