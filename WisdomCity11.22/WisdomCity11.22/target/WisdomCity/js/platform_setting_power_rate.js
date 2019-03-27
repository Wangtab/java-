    $(function(){
        selectAll();
    });
    //保存信息
    function saveData(){
        proUntil.saveData("PlatFomSetting/updatePowerRateData",$('.table_win'),function(){
            selectAll();
        });
    }

    function selectAll(){
        var btn_obj = {title:"修改",className:"edit_button"};
        var data_arr = [];
        data_arr[0] = btn_obj;
        proUntil.commonSelect("PlatFomSetting/getPowerRateList",{},function(){
            init_btn();
        },null,null,data_arr);
    }

    function init_btn() {
        $('.edit_button').unbind('click').click(function(){
            proUntil.request("PlatFomSetting/getPowerRateById",{"Id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="powerRate"]').val(val.power_rate);
                box.show(manage_table_time);
            });
        });
    }