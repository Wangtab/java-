var roadid = null;//保存路段ID
var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10;//每页显示多少条数据
var orderBy = "operTime";//排序字段
var sort = "desc";
$(function(){
    init_data();
    init_btn();
});


//二级联动查询区域和道路
function init_data(){
    //窗口区域选择
    var winAreaSelect = $('.table_win').find('select[name ="areaId"]');
    proUntil.getSelData("lampCommon/getAreaNameForSelect",winAreaSelect,{},'<option value = "">请选择区域</option>');
    winAreaSelect.change(function(){
        var areaId = winAreaSelect.val();
        proUntil.getSelData("lampCommon/getRoadNameForSelect",$('.table_win').find('select[name ="roadid"]'),
            {areaId:areaId},'<option value = "">请选择道路</option>',function(){
                if(roadid != null){
                    $('.table_win').find('select[name ="roadid"]').find("option[value='"+roadid+"']").prop("selected",true);
                    roadid = null;
                }
            });
    });
    //下拉框获取维修人员

    var repairPeoSelect = $('.table_win').find('select[name ="uid"]');
    proUntil.getSelData("/repairPeopleAll.do",repairPeoSelect,{},'<option value = "">选择人员</option>');
    selectAll();
}


//查询全部
function selectAll(){
  //  cname:cname,showNum:page_show_Num,curPage:cur_page,orderBy:orderBy,sort:sort
    var cname=$('.search_box').find('input[name="peopleName"]').val();
    proUntil.request("../RoutingInspectionManage.do",{orderBy:orderBy,sort:sort,cname:cname,showNum:page_show_Num,curPage:cur_page},function(data){

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
                '<td>'+val.areaName+'</td>\n' +
                '<td>'+val.roadname+'</td>\n' +
                '<td>'+val.peopleName+'</td>\n' +
                '<td>'+val.startime+'</td>\n' +
                '<td>'+val.endtime+'</td>\n' +
                '<td>'+val.checkdescribe+'</td>\n' +
                '<td>'+val.opername+'</td>\n' +
                '<td>'+val.opertime+'</td>\n' +
                '<td>\n' +
                '<a title = "修改" rou_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
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



//保存信息
function saveData(){
    /*时间校验*/
    if ($('.table_win').find('input[name="startime"]').val()<$('.table_win').find('input[name="endtime"]').val()){
        proUntil.saveData("../addRoutingInspectionManage.do",$('.table_win'),function(){
            selectAll();
        });
    }else {
        proUntil.alert_win('开始时间不能晚于结束时间')
    }

}


function init_btn(){
//修改窗口
    $('.add_btn').click(function(){
        proUntil.clear_form_data($('.table_win'));
    });

    $('.edit_button').click(function(){
        proUntil.request("../queryRoutingInspection.do",{"id": $(this).attr('rou_id')},function(data){
            var val = JSON.parse(data)[0];
            var box = $('.table_win');
            box.find('input[name="id"]').val(val.id);
            box.find('select[name="areaId"]').val(val.areaid);
            roadid = val.roadid;
            box.find('select[name="areaId"]').change();
            box.find('select[name="uid"]').val(val.uid);
            box.find('input[name="startime"]').val(val.startime);
            box.find('input[name="endtime"]').val(val.endtime);
            box.find('input[name="checkdescribe"]').val(val.checkdescribe);
            box.show(manage_table_time);
        });
    });
    proUntil.init_common_btn();

}
//排序
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