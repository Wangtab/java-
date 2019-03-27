var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10;//每页显示多少条数据
var roadId = null;//保存路段ID
var orderBy = "operTime";//排序字段
var sort = "desc";
    $(function(){
        init_data();
        selectAll();
    });

    function init_data(){
        var searchBox = $('.search_box');
        proUntil.searchLinkSelect(searchBox.find('select[name ="areaId"]'),searchBox.find('select[name ="roadId"]'),searchBox.find('select[name ="lineId"]'),searchBox.find('select[name ="lampId"]'));
        var formBox = $('.second_form');
        proUntil.winLinkSelect(formBox.find('select[name ="areaid"]'),formBox.find('select[name ="roadid"]'));
        proUntil.getSelData("lampCommon/getBuildStandardForSelect",formBox.find('select[name="buildtypeid"]'),{},'<option value="">请选择施工标准</option>');
        proUntil.request("Maintain/getRepairPeopleAndNumberData",{},function(data){
            if('n' == data){
                return;
            }
            data = JSON.parse(data);
            var box = $('.second_form');
            var sel = box.find('select[name="repairmanid"]');
            var html = "<option value = '' num = ''>请选择维修人员</option>";
            $.each(data,function(index,val){
                html += '<option value="'+val.id+'" num = "'+val.number+'">'+val.cname+'</option>';
            });
            sel.html(html);
            sel.change(function(){
                var num = sel.find("option:selected").attr("num");
                box.find('input[name="number"]').val(num);
            });
        });

        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });
    }

    //查询全部
    function selectAll(){
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getLampWarnningData",
            data:{"areaId":$('#areaIdSel').val(),"roadId":$("#selRoad").val(),"lampId":$('#selLight').val(),"lineId":$('#selLine').val(),
                "startDate":$('#startDated').val(),"endDate":$("#endDated").val(),
                "showNum":page_show_Num,"curpage":cur_page,"orderBy":orderBy,"sort":sort},
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
                var orderList = data.orderList;
                $.each(data.datas, function(index, val){
                    html += '<tr>\n' +
                        '<td  showname= "show_area">'+val.areaName+'</td>\n' +
                        '<td  showname = "show_road">'+val.road_name+'</td>\n' +
                        '<td  showname = "show_pole">'+val.linename+'</td>\n' +
                        '<td>'+val.factoryname+'</td>\n' +
                        '<td>'+val.lamptypename+'</td>\n' +
                        '<td>'+val.kindname+'</td>\n' +
                        '<td>'+(val.levelname || '低')+'</td>\n' +
                        '<td showname = "warn_content">'+(val.warn_name || '单灯通信故障报警')+'</td>\n' +
                        '<td>'+val.warn_time+'</td>\n' +
                        '<td>'+val.deal_flag+'</td>\n' +
                        dealNum(val,orderList) +
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
                init_btn();
                deal_table_th();
            }
        });
        return false;
    }

    function dealNum(val,orderList){

        var str = '<td>'+(val.ordernum || '')+'</td>'+
            '<td><img src="images/repair_show_ico.png"/><a href="javascript:;" data_id = "'+val.lampId+'" nb_device = "'+val.nb_device+'" class ="sendRepairBtn">派工</a></td>\n';
        var dealText = val.deal_flag;
        if('已处理' == dealText){
            str = '<td>'+val.ordernum+'</td>'+
                '<td>已派工</td>\n';
        }
        if(orderList == null || orderList.length == 0){
            return str;
        }

        var nb_device = val.nb_device;

        $.each(orderList, function(index, orderObj){
            if(nb_device == orderObj.nb_device && '未处理' == dealText){//如果设备类型一致 并且是未处理状态
                str = '<td>'+orderObj.ordernum+'</td>'+
                 '<td>已派工</td>\n';
                return str;
            } else if('已处理' == dealText){
                str = '<td>'+orderObj.ordernum+'</td>'+
                    '<td>已派工</td>\n';
                return str;
            }
        });
        return str;
    }

    function init_btn(){
        $('#warn_tabled').find('a').hover(function(){
            var _this = $(this);
            _this.css('color',"#FF9900");
            var td = _this.parent('td');
            td.find('img').attr('src','images/repair_hover_ico.png');
        },function(){
            var _this = $(this);
            _this.css('color',"#FFF");
            var td = _this.parent('td');
            td.find('img').attr('src','images/repair_show_ico.png');
        });

        $('#warn_tabled').find('a').click(function(){
            var _this = $(this);
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getSendBuildingDataByLampId",
                data:{"id":$(this).attr("data_id")},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    var box = $('.second_form');
                    box.find('input[type="text"]').val('');
                    var box = $('.second_form');
                    var num = (new Date()).valueOf();
                    box.find('input[name="ordernum"]').val(num);
                    box.find('select[name="areaid"]').val(val.areaid);
                    roadId = val.roadid;
                    box.find('select[name="areaid"]').change();
                    $('#nb_deviced').val(_this.attr('nb_device'));
                    box.find('input[name="devicenum"]').val(val.nb_code);
                    var node = _this.parents('tr').find('td[showname = "warn_content"]').html();
                    box.find('textarea[name="node"]').val('报警内容：'+node);
                    $('.second_form').show(manage_table_time);
                }
            });
        });

    }

    function saveData(){
        proUntil.saveData("../LampWarnsaveBuildingData",$(".second_form"),function(){
            selectAll()
        });
    }


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