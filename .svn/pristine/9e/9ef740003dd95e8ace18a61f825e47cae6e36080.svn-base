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
    });

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
        var btn_obj = {title:"修改",className:"edit_button"};
        var data_arr = [];
        data_arr[0] = btn_obj;
        proUntil.commonSelect("PlatFomSetting/getCityData",{},function(){
            init_btn();
        },null,null,data_arr);
    }

    function init_btn(){
        $('.edit_button').click(function(){
            proUntil.request("PlatFomSetting/getCityDataById",{"id": $(this).attr('data_id')},function(data){
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
        proUntil.saveData("PlatFomSetting/updateCityData",$(".table_win"),function(){
            selectAll()
        });
    }