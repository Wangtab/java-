var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10//每页显示多少条数据
var orderBy = "record_time";//排序字段
var sort = "desc";
$(function(){
    deal_table_th();
    init_data();
    selectAll();
    init_btn();
});

function init_data(){
    var searchBox = $('.search_box');
    var areaSelect = searchBox.find('select[name ="selectAreaName"]');
    var roadSelect = searchBox.find('select[name ="selectRoadName"]');
    var concenSelect = searchBox.find('select[name ="concenSelect"]');
    //获取区域信息
    proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
    areaSelect.change(function(){
        var areaId = areaSelect.val();
        if(proUntil.strIsEmpty(areaId)){
            roadSelect.html('<option value = "">请选择道路</option>');
            concenSelect.html('<option value = "">请选择集中器</option>');
            return;
        }
        proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
        concenSelect.html('<option value = "">请选择集中器</option>');
    });

    //道路选择显示集中器
    roadSelect.change(function(){
        var roadId = roadSelect.val();
        if(proUntil.strIsEmpty(roadId)){
            concenSelect.html('<option value = "">请选择集中器</option>');
            return;
        }
        proUntil.getSelData("lampCommon/getConcenNameForSelect",concenSelect,{roadId:roadId},'<option value = "">请选择线路</option>');
    });

}

//查询全部
function selectAll(){
    $.ajax({
        type:"post",
        dataType:"json",
        url:"../getConcenStatusList",
        data:{"areaId":$('#areaIdSel').val(),"roadId":$("#selRoad").val(),"concenSelect":$('#concenSelect').val(),"showNum":page_show_Num,"curPage":cur_page,"sort":sort,"orderBy":orderBy},
        success: function (data){
            var table = $('.table_box').find('table');
            var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            var html = "";
            if('n' == data){
                table.html('');
                check_table_data();
                return;
            }
            data = data[0];
            count_page = Math.ceil(parseInt(data.count)/page_show_Num);
            $.each(data.datas, function(index, val){
                html += '<tr>\n' +
                    '<td>'+val.id+'</td>\n' +
                    '<td>'+val.areaName+'</td>\n' +
                    '<td>'+val.concentratorname+'</td>\n' +
                    '<td>'+val.link_status+'</td>\n' +
                    '<td>'+val.a_ele+'</td>\n' +
                    '<td>'+val.a_pov+'</td>\n' +
                    '<td>'+val.a_power+'</td>\n' +
                    '<td>'+val.b_ele+'</td>\n' +
                    '<td>'+val.b_pov+'</td>\n' +
                    '<td>'+val.b_power+'</td>\n' +
                    '<td>'+val.c_ele+'</td>\n' +
                    '<td>'+val.c_pov+'</td>\n' +
                    '<td>'+val.c_power+'</td>\n' +
                    '<td>'+val.temp+'</td>\n' +
                    '<td>'+val.record_time+'</td>\n' +
                      '</td>\n' +
                    '</tr>';
            });
            table.html(frist_tr + html);
            $("#controllerPage").html('');
            page({
                id:'controllerPage',
                nowNum: cur_page,
                allNum: count_page
            });

            //网络数据分页点击
            $("#controllerPage").find("a").unbind('click').click(function(){
                var _this = $(this);
                var curpage = _this.attr('cur');
                cur_page = parseInt(curpage);
                selectAll();
            });
            check_table_data();
            deal_table_th();
            init_btn();
        }
    });
    return false;
}
function deal_table_th(){
    $('.table_box').find('th').not(':last').click(function(){
        var _this = $(this);
        var th_sort = _this.attr('sort');
        $('.table_box').find('th').removeAttr('sort');
        $('.table_box').find('th').find('img').remove();
        if(proUntil.strIsEmpty(sort)){
            th_sort = "desc";
            _this.html(_this.html() + '<img src="../images/th_ico_down.png">');
        } else if ("asc" == sort){
            th_sort = "desc";
            _this.html(_this.html() + '<img src="../images/th_ico_down.png">');
        } else if("desc" == sort){
            th_sort = "asc";
            _this.html(_this.html() + '<img src="../images/th_ico_up.png">');
        }
        _this.attr('sort',th_sort);
        orderBy =  _this.attr("orderBy");
        sort = th_sort;
        selectAll();
    });
}

function init_btn() {

    $('#excel_btnd').click(function(){
        window.location.href="../exportConcenStatusData?areaId="+$('#areaIdSel').val()
            + "&roadId="+$("#selRoad").val() + "&concenSelect="+$('#concenSelect').val()+ "&sort="+sort+ "&orderBy="+orderBy;
    });

}
