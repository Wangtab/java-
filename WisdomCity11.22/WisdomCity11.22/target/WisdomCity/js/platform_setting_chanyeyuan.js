    $(function() {
        selectAll();
    });

    //查询全部
    function selectAll(){
        var btn_obj = {title:"修改",className:"edit_button"};
        var data_arr = [];
        data_arr[0] = btn_obj;
        proUntil.commonSelect("PlatFomSetting/getChanYeYuanIot",{},function(){
            init_btn();
        },null,null,data_arr);
    }

    function init_btn(){
        $('.edit_button').click(function(){
            proUntil.request("PlatFomSetting/getChanYeYuanIotById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="protocol"]').val(dealProtocol(val.protocol_type));
                box.find('input[name="address"]').val(val.iot_address);
                box.show(manage_table_time);
            });
        });
    }

    function saveData(){
        proUntil.saveData("PlatFomSetting/saveChanYeYuanIot",$(".table_win"),function(){
            selectAll();
        });
    }

    function dealProtocol(protocol_type){
        if(protocol_type == 0){
            return "UDP协议";
        }else{
            return "COAP协议";
        }

    }