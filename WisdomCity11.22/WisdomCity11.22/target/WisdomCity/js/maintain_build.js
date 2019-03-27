     $(function(){
        init_data();
        selectAll();
    });

    function init_data(){
        var formBox = $('.second_form');
        proUntil.winLinkSelect(formBox.find('select[name ="areaid"]'),formBox.find('select[name ="roadid"]'));
        proUntil.getSelData("lampCommon/getBuildStandardForSelect",formBox.find('select[name="buildtypeid"]'),{},'<option value="">请选择施工标准</option>');

        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });

        $('#close_scond_wind').click(function() {
            $('.second_form').hide(manage_table_time);
        });

        $('.excel_btn').click(function(){
            window.location.href="Maintain/exportBuildingData?"+$('.search_box').serialize();
        });

        //新建窗口
        $('.add_btn').unbind('click').click(function(){
            proUntil.clear_form_data($('.second_form'));
            var num = (new Date()).valueOf();
            $('#OrderNum').val(num);
        });

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
    }

    function init_btn(){
        $('.edit_button').unbind('click').click(function(){
            proUntil.request("Maintain/getBuildingInfoDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.second_form');
                proUntil.ROAD_ID = val.roadid;
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="ordernum"]').val(val.ordernum);
                box.find('select[name="areaid"]').val(val.areaid);
                box.find('select[name="areaid"]').change();
                box.find('input[name="modelnum"]').val(val.modelnum);
                box.find('input[name="devicenum"]').val(val.devicenum);
                box.find('select[name="repairmanid"]').val(val.repairmanid);
                box.find('select[name="repairmanid"]').change();
                box.find('select[name="buildtypeid"]').val(val.buildtypeid);
                box.find('select[name="repairtype"]').val(val.repairtype);
                box.find('select[name="deal_result"]').val(val.deal_result);
                box.find('input[name="buildtime"]').val(val.buildtime);
                box.find('input[name="node"]').val(val.node);
                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }

    function saveData(){
        proUntil.saveData("Maintain/saveBuildingData",$(".second_form"),function(){
            selectAll()
        });
    }

    //查询全部
    function selectAll(){
        var search_box = $('.search_box');
        var orderNum = search_box.find('input[name="orderNum"]').val();
        var startDate =  search_box.find('input[name="startDate"]').val();
        var endDate = search_box.find('input[name="endDate"]').val();
        proUntil.commonSelect("Maintain/getBuildingShowData",{orderNum:orderNum,startDate:startDate,endDate:endDate},function(){
                init_btn();
        });
    }
