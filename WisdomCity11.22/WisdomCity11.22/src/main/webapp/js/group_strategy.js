    var groupId = null;

    $(function(){

        selectAll();
        $(".dimming_light").slider({
            formatter: function (value){
                return  value + "%";
            }});
        $('.planContent').change(function(){
            var parent_box = $('.second_form');
            var startDate = parent_box.find('.startDate');
            var over_date = parent_box.find('.overDate');
            var start_time = parent_box.find('.startTime');
            var over_time = parent_box.find('.overTime');
            var weekSel = $('.selWeeks');
            var areaSel = parent_box.find('select[name = "areaId"]');
            var groupSel  = parent_box.find('select[name = "groupId"]');
            var senceSel  = parent_box.find('select[name = "senceId"]');
            var dimming = $(".dimming_light");

            groupSel.prop('disabled',false);
            areaSel.prop('disabled',false);
            senceSel.prop('disabled',true);
            startDate.prop('disabled', true);
            over_date.prop('disabled', true);
            start_time.prop('disabled', true);
            over_time.prop('disabled', true);
            weekSel.multiselect('disable');
            weekSel.multiselect('clearSelection');
            dimming.slider('enable');
            startDate.val('');
            over_date.val('');
            start_time.val('');
            over_time.val('');

            var val =  $('.planContent').val();
            if(1 == val){//开关灯
                startDate.prop('disabled', false);
                over_date.prop('disabled', false);
                start_time.prop('disabled', false);
                over_time.prop('disabled', false);
                senceSel.val('');
            } else if(2 == val){//调光
                startDate.prop('disabled', false);
                start_time.prop('disabled', false);
                senceSel.val('');
            } else if(3 == val){//每天
                start_time.prop('disabled', false);
                over_time.prop('disabled', false);
                senceSel.val('');
            } else if(4 == val){ //每周
                start_time.prop('disabled', false);
                over_time.prop('disabled', false);
                weekSel.multiselect('enable');
                senceSel.val('');
            } else if(5 == val){//场景
                senceSel.prop('disabled',false);
                areaSel.val('');
                groupSel.val('');
                dimming.slider('setValue',0);
                dimming.slider('disable');
                proUntil.setSlider_width($('.detail_box'),0);
                groupSel.prop('disabled',true);
                groupSel.prop('disabled',true);
                areaSel.prop('disabled',true);
                startDate.prop('disabled',true);
                over_date.prop('disabled',true);
                start_time.prop('disabled',true);
                over_time.prop('disabled',true);
            }
            init_dateTime();
        });

        $('.selWeeks').multiselect();
        $('.selWeeks').multiselect('disable');
        proUntil.getSelData("getPlanStrategyPlanData",$('.second_form').find('select[name="planId"]'));
        proUntil.getSelData("getPlanSenceDataForSelect",$('.second_form').find('select[name="senceId"]'),{},'<option value = "">请选择场景</option>');
        init_dateTime();

        var areaSelect = $('.second_form').find('select[name ="areaId"]');
        proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
        areaSelect.change(function(){
            var areaId = areaSelect.val();
            if(proUntil.strIsEmpty(areaId)){
                return;
            }
            proUntil.getSelData("lampCommon/getGroupNameForSelect",$('.second_form').find('select[name ="groupId"]'),{areaId:areaId},'<option value = "">请选择分组</option>',
                function(){
                    if(groupId != null){
                        $('.second_form').find('select[name ="groupId"]').find("option[value='"+groupId+"']").prop("selected",true);
                        groupId = null;
                    }
                });
        });

        $('#close_scond_wind').click(function() {
            $('.second_form').hide(manage_table_time);
        });
    });

    function init_dateTime(){
        $('.form-control').datetimepicker({
            locale:'zh-cn',
            format: 'YYYY-MM-DD',
        });
        $(".form-control_hour").datetimepicker({
            locale:'zh-cn',
            format: "HH:mm",

        });
    }

    function init_btn(){
        //新建窗口
        $('.add_btn').unbind('click').click(function() {
            proUntil.clear_form_data($(".second_form"));
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function() {
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sure_delete_judge_win').click(function(){
            proUntil.delData("deltePlanStrategyById",{"id":$(this).attr('id')},$('.judge_win'),function(){
                selectAll();
            });
        });

        //修改
        $('.edit_button').click(function(){
            proUntil.request("getPlanStragegyById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $(".second_form");
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="cName"]').val(val.cname);
                groupId = val.group_id;
                box.find('select[name="areaId"]').val(val.area_id).change();
                box.find('select[name="senceId"]').val(val.sence_id);
                box.find('input[name="startDate"]').val(val.start_date);
                box.find('input[name="overDate"]').val(val.end_date);
                box.find('input[name="startTime"]').val(val.start_time);
                box.find('input[name="endTime"]').val(val.end_time);
                var dimming = val.dimming || 0;
                $(".dimming_light").slider('setValue',dimming);
                proUntil.setSlider_width($('.detail_box'),dimming);
                box.find('select[name="areaId"]').change();
                if(!proUntil.strIsEmpty(val.weeks)){
                    box.find("select[name='weeks']").multiselect('select',val.weeks.split(","));
                }
                box.find('select[name="planId"]').val(val.plan_id).change();
                box.show(manage_table_time);
            });
        });
    }

    function selectAll(){
       var name =  $('.search_box').find('input[name="name"]').val();
        proUntil.commonSelect("getPlanStrategyList",{cName:name},function(){
            init_btn();
        });
    }

    //保存信息
    function saveData(){
        var JQuery_form = $(".second_form");
        var dimming = $(".dimming_light").slider('getValue') || 0;
        JQuery_form.find('input[name="dimming"]').val(dimming);
        proUntil.saveData("savePlanStrategyData",$('.second_form'),function(){
            selectAll()
        });
    }