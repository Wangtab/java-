    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "record_time";//排序字段
    var sort = "desc";

    $(function(){
        var search_box = $('.search_box');
        proUntil.searchLinkSelect(search_box.find('select[name ="areaId"]'),search_box.find('select[name ="roadId"]'));
        selectAll();
        $('.excel_btn').click(function(){
            window.location.href="../exportGetLampStatusReport?"+$('.search_box').serialize();
        });
    });


    function selectAll(){
        var searchBox = $('.search_box');
        var param = {
            areaId :searchBox.find('select[name ="areaId"]').val(),
            roadId :searchBox.find('select[name ="roadId"]').val(),
            curPage:cur_page,
            showNum:page_show_Num,
            orderBy:orderBy,
            sort:sort,
            typeId:1
        };
        proUntil.request("getLampStatusReport",param,function(data){
            var table = $('.table_box').find('table');
            var firstTr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            if('n' == data){
                table.html(firstTr);
                check_table_data();
                return;
            }
            var html = "";
            data = JSON.parse(data)[0];
            count_page = Math.ceil(parseInt(data.count)/page_show_Num);
            $.each(data.data, function(index, val){
                var onOff =  1 == val.on_off ? '<img src="images/switch_open.png" title = "开灯"/>' : '<img src="images/switch_close.png" title = "关灯"/>';
                var connSate = val.conn_state;
                if(1 == connSate){
                    connSate = '<img src="images/wifi_online.png" title = "在线"/>';
                }else{
                    connSate = '<img src="images/wifi_close.png" title = "不在线"/>';
                }

                html += '<tr>\n' +
                    '<td>'+val.areaName+'</td>\n' +
                    '<td>'+val.road_name+'</td>\n' +
                    '<td>'+val.cname+'</td>\n' +
                    '<td>'+val.lampnum+'</td>\n' +
                    '<td>'+val.nb_device+'</td>\n' +
                    '<td>'+val.nb_code+'</td>\n' +
                    '<td>'+onOff+'</td>\n' +
                    '<td>'+connSate+'</td>\n' +
                    '<td>'+val.vol+'</td>\n' +
                    '<td>'+val.ele+'</td>\n' +
                    '<td>'+val.power+'</td>\n' +
                    '<td>'+val.dimming+'</td>\n' +
                    '<td>'+val.working_hours+'</td>\n' +
                    '<td>'+(val.signal_intensity || 0)+'</td>\n' +
                    '<td>'+val.record_time+'</td>\n' +
                    '</tr>';
            });
            table.html(firstTr + html);
            $("#controllerPage").html('');
            page({
                id:'controllerPage',
                nowNum: cur_page,
                allNum: count_page
            });
            $("#controllerPage").find("a").unbind('click').click(function(){
                var _this = $(this);
                cur_page = parseInt(_this.attr('cur'));
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

