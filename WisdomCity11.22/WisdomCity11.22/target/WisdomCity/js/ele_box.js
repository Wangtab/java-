    var table_map = null;
    var road_id = null;//保存路段ID
    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "oper_time";//排序字段
    var sort = "desc";

    $(function() {

        init_win_map();
        selectAll();
        init_data();
    });



    //二级联动查询区域和道路
    function init_data(){
        table_map = initMap("table_mapd");
        var search_box = $('.table_win');
        proUntil.searchLinkSelect(search_box.find('select[name ="areaId"]'),search_box.find('select[name ="roadId"]'));
        selectAll();
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

    function init_btn(){
        $('.add_btn').unbind('click').click(function(){
            proUntil.clear_form_data($('.table_win'));
            win_map.clearOverlays();
            $('.table_win').show(manage_table_time);
        });

        //编辑窗口
        $('.edit_button').unbind('click').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getEleBoxDataById",
                data:{"id": $(this).attr('data_id')},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    var box = $('.table_win');
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="name"]').val(val.name);
                    box.find('input[name="longitude"]').val(val.longitude);
                    box.find('select[name="areaId"]').val(val.area_id);
                    roadId = val.road_id;
                    box.find('select[name="areaId"]').change();
                    box.find('select[name="belongorg"]').val(val.belongorg);
                    box.find('input[name="latitude"]').val(val.latitude);
                    win_map.clearOverlays();
                    var point = new BMap.Point(val.lo,val.la);
                    var marker = new BMap.Marker(point);  // 创建标注
                    win_map.addOverlay(marker);
                    $('.table_win').show(manage_table_time);
                }
            });
        });


        proUntil.init_common_btn();
    }

    function saveData(){
        proUntil.saveData("../saveEleBoxData",$(".table_win"),function(){
            selectAll()
        });
    }

    function selectAll(){
        proUntil.commonSelect("getEleBoxData",{eleNum:$('.search_box').find('input[type="text"]').val()},function(){
                init_btn();
       });

    }