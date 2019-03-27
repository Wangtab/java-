    var win_map = null;
    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "oper_time";//默认排序字段
    var sort = "desc";

    $(function() {
        init_win_map();
        $('#city_srh_btnd').click(function() {
            var city = document.getElementById("cityNamed").value;
            if(city != ""){
                win_map.centerAndZoom(city,11); // 用城市名设置地图中心点
            }
        });
        selectAll();
        deal_table_th();
    });

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

    function init_win_map(){
        win_map = initMap('win_mapd');
        win_map.addEventListener("click",function(e){
            var longitude = e.point.lng;//经度
            var latitude = e.point.lat;//纬度
            $('#longitude_ipt').val(longitude);
            $('#latitude_ipt').val(latitude);
            win_map.clearOverlays();
            var point = new BMap.Point(longitude,latitude);
            var marker = new BMap.Marker(point);  // 创建标注
            win_map.addOverlay(marker);
        });
    }

    //查询全部
    function selectAll(){
        proUntil.request("../getCityData",{"showNum":page_show_Num,"curPage":cur_page,"orderBy":orderBy,"sort":sort},function(data){
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
                    '<td>'+val.city_name+'</td>\n' +
                    '<td>'+val.longitude+'</td>\n' +
                    '<td>'+val.latitude+'</td>\n' +
                    '<td>'+(val.org_name || '')+'</td>\n' +
                    '<td>'+(val.real_name || '')+'</td>\n' +
                    '<td>'+(val.oper_time || '')+'</td>\n' +
                    '<td>' +
                    '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                    '</td>\n' +
                    '</tr>';
            });
            table.html(frist_tr + html);
           /* $("#controllerPage").html('');
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
            });*/
            check_table_data();
            init_btn();
            deal_table_th();
        });
    }

    function init_btn(){
        $('.edit_button').click(function(){
            proUntil.request("../getCityDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="cityName"]').val(val.city_name);
                box.find('input[name="lo"]').val(val.longitude);
                box.find('input[name="la"]').val(val.latitude);
                win_map.clearOverlays();
                var point = new BMap.Point(val.longitude,val.latitude);
                var marker = new BMap.Marker(point);  // 创建标注
                win_map.centerAndZoom(point,13);
                win_map.addOverlay(marker);
                box.show(manage_table_time);
            });
        });
    }

    function saveData(){
        proUntil.saveData("../updateCityData",$(".table_win"),function(){
            selectAll()
        });
    }