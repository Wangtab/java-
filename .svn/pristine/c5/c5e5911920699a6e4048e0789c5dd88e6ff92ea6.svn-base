     $(function() {
        selectAll();
         init_btn();
    });

    //保存信息
    function saveData(){
        var box = $('.table_win');
        var number = box.find("input[name='number']").val();
        if(proUntil.strIsEmpty(number)){
            proUntil.alert_win("请填写工号");
            return;
        }

        proUntil.request("Maintain/checkRepairNum",{number:number},function(data){
            var id = box.find("input[name='id']").val();
            if(0 != data&&id==""){
                proUntil.alert_win("此工号已存在");
                return;
            }
            proUntil.saveData("Maintain/saveRepData",$('.table_win'),function(){

                selectAll();
            });
        });


    }
//查询所有信息
    function selectAll(){
        var search_box = $('.search_box');
        proUntil.commonSelect("Maintain/getRepairData",
            {name:search_box.find('input[name="name"]').val()},function(){
                init_btn();
        });
    }
    
    function init_btn() {
        $('.add_btn').unbind('click').click(function(){
            proUntil.request("Maintain/getLoginUserBelongOrgName",{},function(data){
                proUntil.clear_form_data( $('.table_win'));
                $('.table_win').find('input[name="orgname"]').val(data);
            });

        });

        $('.edit_button').unbind('click').click(function(){
            proUntil.request("Maintain/getRepairDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="name"]').val(val.name);
                box.find('input[name="number"]').val(val.number);
                box.find('input[name="numjob"]').val(val.numjob);
                $("input[name='sex']").eq(val.sex).attr("checked","checked");
                box.find('input[name="tel"]').val(val.tel);
                box.find('input[name="address"]').val(val.address);
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="orgname"]').val(val.orgname);
                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }

