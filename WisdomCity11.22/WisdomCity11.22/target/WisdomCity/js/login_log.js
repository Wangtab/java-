var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10//每页显示多少条数据
var orderBy = "logDate";//排序字段
var sort = "desc";

$(function() {
        selectAll();
	});

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


    //查询全部
    function selectAll(){
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getoperationLoginLog",
            data:{"showNum":page_show_Num,"page":cur_page,"sort":sort,"orderBy":orderBy},
            success: function (data){
                var table = $('.table_box').find('table');
                var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
                var html = "";
                if('n' == data){
                    table.html('');
                    check_table_data();
                    return;
                }
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
                       /* '<td>'+val.uuid+'</td>\n' +*/
                        '<td>'+val.log_name+'</td>\n' +
                        '<td>'+val.log_realname+'</td>\n' +
                        '<td>'+val.ip+'</td>\n' +
                        '<td>'+val.log_date+'</td>\n' +
                        '</tr>';

                    var hasPoints = proUntil.strIsEmpty(val.points);
                    if(!hasPoints){
                        //绘制区域
                        drawAreas(val.points,val.road_name);
                    }
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

