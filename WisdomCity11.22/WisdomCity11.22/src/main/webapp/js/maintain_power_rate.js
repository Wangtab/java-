var win_map = null;
var table_map = null;
var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 8//每页显示多少条数据
var color = ["#CD3700","#B03060","#556B2F","#4A708B","#8B008B","#CDAD00"];
var orderBy = "recordTime";//排序字段
var sort = "desc";
    $(function() {
        selectAll();
        init_data();
    });

    function init_data(){
        var searchBox = $('.search_box');
        var areaSelect = searchBox.find('select[name ="selectAreaName"]');
        var roadSelect = searchBox.find('select[name ="selectRoadName"]');
        //获取区域信息
        proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
        areaSelect.change(function(){
            var areaId = areaSelect.val();
            if(proUntil.strIsEmpty(areaId)){
                return;
            }
            proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
        });
    }


    //查询全部
    function selectAll(){
        var searchBox = $('.search_box');
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getPowerPriceData",
            data:{"areaId":searchBox.find('select[name ="selectAreaName"]').val(),
                "roadId":searchBox.find('select[name ="selectRoadName"]').val(),
                "month":searchBox.find('select[name ="selectMonth"]').val(),
                "showNum":page_show_Num,
                "curPage":cur_page,"orderBy":orderBy,"sort":sort},
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
                    if(null == val.energy){
                        table.html('');
                        check_table_data();
                        return;
                    }
                    html += '<tr>\n' +
                        '<td>'+val.price+'</td>\n' +
                        '<td>'+val.energy+'</td>\n' +
                        '<td>'+val.sum_price+'</td>\n' +
                        '<td>'+val.record_time+'</td>\n' +
                        '<td>'+val.org_name+'</td>\n' +
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
            }
        });
        return false;
    }
