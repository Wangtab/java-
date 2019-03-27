var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10//每页显示多少条数据
var orderBy = "operTime";//排序字段
var sort = "desc";

$(function() {
    $('.form-control').datepicker({
        language: 'zh-CN',
        format: "yyyy-mm-dd",
        viewMode:'days',
    });
    init_data();
    selectAll();
});

function init_data() {
    var search_box = $('.work_content_box');
    var kindSelect=search_box.find('select[name ="selectKind"]');
    var cnameSelect = search_box.find('select[name ="selectCname"]');
    proUntil.getSelData("lampCommon/getMenuDataForSelect",cnameSelect,{},'<option value = "">请选择模块</option>');
   }




function isEmpty(str){
    if (str !== null || str !== undefined || str !== ''){
        return true;
    }
    return false;
}

function selectAll() {
    var searchBox = $('.search_box');
    var search_box = $('.work_content_box');
    var kindSelect=search_box.find('select[name ="selectKind"]').val();
    var cnameSelect = search_box.find('select[name ="selectCname"]').val();
    proUntil.request("../showCzList",{"showNum":page_show_Num,"startDate":$('#startDate').val(),"endDate":$('#endDate').val(),
        "curPage":cur_page,"sort":sort,"orderBy":orderBy,
        "kindId":kindSelect,
        "menuId":cnameSelect},function(data){

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
            var cc=val.kind_id;
            var d='';
            if(cc=='10'){
                d='添加';
            }
            if(cc=='20'){
                d='修改';
            }
            if(cc=='30'){
                d='删除';
            }
            html += '<tr>\n' +
                '<td>'+val.real_name+'</td>\n' +
                '<td>'+d+'</td>\n' +
                '<td>'+val.cname+'</td>\n' +
                '<td>'+val.operdes+'</td>\n' +
                '<td>'+val.opertime+'</td>\n' +
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
    });
}

function deal_table_th(){
    $('.table_box').find('th').click(function(){
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