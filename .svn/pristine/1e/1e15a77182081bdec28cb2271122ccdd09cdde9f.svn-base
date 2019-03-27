    $(function(){
        selectAll();
        proUntil.getSelData("lampCommon/getDistributionBoxForSelect",$(".table_win").find("select[name='elecboxId']"),'<option value = "">请选择配电箱</option>');
    });

    //保存信息
    function saveData(){
        proUntil.saveData("saveDianbiaoData",$('.table_win'),function(){
            selectAll();
        });
    }

    //查询全部
    function selectAll(){
        proUntil.commonSelect("getAmmeterList",{cName:$('.search_box').find('input[name="cName"]').val()},function(){
           init_btn();
        });
    }

    function init_btn() {
            //打开修改窗口
            $('.edit_button').unbind('click').click(function(){
            proUntil.request("getAmmeterDataById",{"Id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="cAddress"]').val(val.c_address);
                box.find('input[name="cName"]').val(val.c_name);
                $('#elecboxId').val(val.elecbox_id);
                $('#elecBoxLoop').val(val.elec_box_loop);
                box.find('input[name="cFlag"]').val(val.c_flag);

                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }