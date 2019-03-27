    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "record_time";//排序字段
    var sort = "desc";
    $(function() {
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });

        init_data();
        selectAll();
        $('.excel_btn').click(function(){
            window.location.href="../exportOperationLampData?"+$('.search_box').serialize();
        });
    });

    function init_data(){
        var searchBox = $('.search_box');
        var areaSelect = searchBox.find('select[name ="areaId"]');
        var roadSelect = searchBox.find('select[name ="roadId"]');
        var roadLineSelect = searchBox.find('select[name ="lineId"]');
        var lampSelect = searchBox.find('select[name ="lampId"]');
        //获取区域信息
        proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
        areaSelect.change(function(){
            var areaId = areaSelect.val();
            if(proUntil.strIsEmpty(areaId)){
                roadSelect.html('<option value = "">请选择道路</option>');
                roadLineSelect.html('<option value = "">请选择线路</option>');
                lampSelect.html('<option value = "">请选择单灯</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
            roadLineSelect.html('<option value = "">请选择线路</option>');
            lampSelect.html('<option value = "">请选择单灯</option>');
        });

        //道路选择显示线路
        roadSelect.change(function(){
            var roadId = roadSelect.val();
            if(proUntil.strIsEmpty(roadId)){
                roadLineSelect.html('<option value = "">请选择线路</option>');
                lampSelect.html('<option value = "">请选择单灯</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadLineNameForSelect",roadLineSelect,{roadId:roadId},'<option value = "">请选择线路</option>');
            lampSelect.html('<option value = "">请选择单灯</option>');
        });

        //线路选择显示单灯编号
        roadLineSelect.change(function(){
            var lineId = roadLineSelect.val();
            if(proUntil.strIsEmpty(lineId)){
                lampSelect.html('<option value = "">请选择单灯</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getLampNumForSelect",lampSelect,{lineId:lineId},'<option value = "">请选择单灯</option>');
        });
    }

    function selectAll(){
        var searchBox = $('.search_box');
        var param = {
            areaId :searchBox.find('select[name ="areaId"]').val(),
            roadId :searchBox.find('select[name ="roadId"]').val(),
            lineId :searchBox.find('select[name ="lineId"]').val(),
            lampId :searchBox.find('select[name ="lampId"]').val(),
            startDate:searchBox.find('input[name ="startDate"]').val(),
            endDate:searchBox.find('input[name ="endDate"]').val(),
            curPage:cur_page,
            showNum:page_show_Num,
            orderBy:orderBy,
            sort:sort,
            typeId:1
        };
        proUntil.request("getOperationLogData",param,function(data){
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
                var onOff =  1 == val.on_off ? "开灯" : "关灯";
                var connSate = _judgeLampStateByNum(val.conn_state);
                html += '<tr>\n' +
                    '<td>'+val.areaName+'</td>\n' +
                    '<td>'+val.road_name+'</td>\n' +
                    '<td>'+val.cname+'</td>\n' +
                    '<td>'+val.poleCode+'</td>\n' +
                    '<td>'+val.nb_device+'</td>\n' +
                    '<td>'+val.nb_code+'</td>\n' +
                    '<td>'+onOff+'</td>\n' +
                    '<td>'+connSate+'</td>\n' +
                    '<td>'+val.vol+'</td>\n' +
                    '<td>'+val.ele+'</td>\n' +
                    '<td>'+val.power+'</td>\n' +
                    '<td>'+val.dimming+'</td>\n' +
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

    /**
     *  根据状态数字显示不同的文字
     * @returns {string}
     * @private
     */
    function _judgeLampStateByNum(conn_state){
        if(1 ==conn_state){
            conn_state = '在线';
        }else if(2 ==conn_state){
            conn_state = '离线';
        } else if(3 == conn_state){
            conn_state = '异常';
        } else if(4 == conn_state){
            conn_state = '配电箱在线';
        } else if(5 == conn_state){
            conn_state = '配电箱离线';
        }
        return conn_state;
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
