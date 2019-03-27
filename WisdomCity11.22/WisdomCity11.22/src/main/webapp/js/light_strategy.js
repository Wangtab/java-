var road_id = null;//保存路段ID
var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10;//每页显示多少条数据
var orderBy = "operTime";//排序字段
var sort = "desc";
$(function() {
    init_btn();
    init_data();
});


//二级联动查询区域和道路
function init_data(){
    //窗口区域选择
    var winAreaSelect = $('.table_win').find('select[name ="area_id"]');
    proUntil.getSelData("lampCommon/getAreaNameForSelect",winAreaSelect,{},'<option value = "">请选择区域</option>');
    winAreaSelect.change(function(){
        var areaId = winAreaSelect.val();
        proUntil.getSelData("lampCommon/getRoadNameForSelect",$('.table_win').find('select[name ="road_id"]'),
            {areaId:areaId},'<option value = "">请选择道路</option>',function(){
                if(road_id != null){
                    $('.table_win').find('select[name ="road_id"]').find("option[value='"+road_id+"']").prop("selected",true);
                    road_id = null;
                }
            });
    });
    selectAll();
}







//查询全部
function selectAll(){

    var cname=$('.search_box').find('input[name="cname"]').val();
    proUntil.request("../selectLight_strategy",{cname:cname,showNum:page_show_Num,curPage:cur_page,orderBy:orderBy,sort:sort},function(data){

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
                '<td>'+val.cname+'</td>\n' +
                '<td>'+val.areaName+'</td>\n' +
                '<td>'+val.road_name+'</td>\n' +
                '<td>'+val.light_num+'</td>\n' +
                '<td>'+val.diming+'</td>\n' +
                '<td>'+val.is_open+'</td>\n' +
                '<td>'+val.oper_name+'</td>\n' +
                '<td>'+val.oper_time+'</td>\n' +
                '<td>\n' +
                '<a title = "修改" lightstr_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                '<a title = "删除" lightstr_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
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


function init_btn(){
//修改窗口
    $('.add_btn').click(function(){
        proUntil.clear_form_data($('.table_win'));
        $('.table_win').find('input[name="light_num"]').val("20");//光照调光策略只读默认值为20
    });

    $('.edit_button').click(function(){
        proUntil.request("selectLight_strategyByid",{"id": $(this).attr('lightstr_id')},function(data){
            var val = JSON.parse(data)[0];
            var box = $('.table_win');
            box.find('input[name="cname"]').val(val.cname);
            box.find('input[name="id"]').val(val.id);
            box.find('select[name="area_id"]').val(val.area_id);
            road_id = val.road_id;
            box.find('select[name="area_id"]').change();
            box.find('input[name="light_num"]').val(val.light_num);
            box.find('input[name="diming"]').val(val.diming);
            if(val.is_open==0){
                box.find('#status_open').prop("checked",true);
            }else {
                box.find('#status_close').prop("checked",true);
            }
            box.show(manage_table_time);
        });
    });








//打开删除窗口
$('.delete_button').unbind('click').click(function() {
    var _this = $(this);
    $('.sure_delete_judge_win').attr('id',_this.attr('lightstr_id'));
    table_delete_object =  _this.parents('tr');
    $('.judge_win').show(manage_table_time);
});
//删除窗口 确定按钮
$('.sure_delete_judge_win').unbind('click').click(function(){
    proUntil.delData("../deleteLight_strategyById",{"id":$(this).attr('id')},$('.judge_win'),function(){
        selectAll();
    });
});
}



//保存信息
function saveData(){
    proUntil.saveData("../saveLight_strategy",$('.table_win'),function(){
        selectAll();
    });
}


//标题排序
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