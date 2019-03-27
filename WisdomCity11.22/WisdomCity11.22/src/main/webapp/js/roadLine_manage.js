    $(function(){
        init_data();
        selectAll();
    });

    function init_data(){
        var search_box = $('.search_box');
        proUntil.searchLinkSelect(search_box.find('select[name ="areaId"]'),search_box.find('select[name ="roadId"]'));
        var winTable = $('.table_win');
        proUntil.winLinkSelect(winTable.find('select[name ="selectAreaName"]'),winTable.find('select[name ="roadId"]'));
    }

    function saveData(){
        proUntil.saveData("../saveRoadLineData",$(".table_win"),function(){
            selectAll();
        });
    }

    function selectAll(){
        var search_box = $('.search_box');
        proUntil.commonSelect("queryRoadLineManage",
            {areaId:search_box.find('select[name ="areaId"]').val(),roadId:search_box.find('select[name ="roadId"]').val(),
                cName:search_box.find('input[name="cName"]').val()},function(){
            init_btn();
        });
    }

    function init_btn(){
        $('.edit_button').click(function(){
            proUntil.request("queryRoadLineManageById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('select[name="selectAreaName"]').val(val.areaId);
                box.find('select[name="selectAreaName"]').change();
                proUntil.ROAD_ID = val.roadId;
                box.find('input[name="cName"]').val(val.cname);
                box.show(manage_table_time);
            });
        });

        proUntil.init_common_btn();
    }