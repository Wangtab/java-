    $(function(){
        $('.table_box').find('img').hover(function() {
            var _this = $(this);
            var add_distance = 50;
            var top = _this.offset().top - add_distance * 3;
            var left = _this.offset().left + add_distance;
            $('.win_show_img').find('img').attr('src', _this.attr('src'));
            $('.win_show_img').css({'left':left + 'px','top': top + 'px'});
            $('.win_show_img').show(300);
        }, function() {
            $('.win_show_img').hide(300);
        });

        $(".table_win").find('select[name="lamptypename"]').change(function(){
            var _this = $(this);
            var status = _this.find(':selected').val();
            if(status == 1){
                $("#spowerBox").css("display","none");
                $("#bpowerBox").css("display","none");
            }else{
                $("#spowerBox").css("display","block");
                $("#bpowerBox").css("display","block");
            }
        });

        proUntil.getSelData("lampCommon/getDimmingModelForSelect",$('.table_win').find('select[name="dimmingmode"]'),{});

        selectAll();
    });
    function init_btn(){
        //新建窗口
        $('.add_btn').unbind('click').click(function() {
            var box =  $('.table_win');
            proUntil.clear_form_data(box);
            box.find('select[name="lamptypename"]').val(1);
            box.find('select[name="lamptypename"]').change();
            $('.table_win').show(manage_table_time);
        });

        $('.edit_button').unbind('click').click(function(){
            proUntil.request("getLampTypeDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('select[name="lamptypename"]').val(val.lamptypename);
                box.find('input[name="lamptypedes"]').val(val.lamptypedes);
                box.find('input[name="lampModel"]').val(val.lampModel);
                box.find('input[name="power"]').val(val.power);
                box.find('input[name="lampFactory"]').val(val.lampFactory);
                box.find('input[name="dimmingmode"]').val(val.dimmingmode);
                box.find('input[name="spower"]').val(val.spower);
                box.find('input[name="bpower"]').val(val.bpower);
                box.find('select[name="lamptypename"]').change();
                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }

    function selectAll(){
        proUntil.commonSelect("getLampTypeList",
            {typeName:$('.search_box').find('input[name="typeName"]').val()},function(){
                init_btn();
        });
    }

    function saveData(){
        var box = $('.table_win');
        var model = box.find("input[name='lampModel']").val();
        var typeId = box.find('select[name="lamptypename"]').val();
        var id = box.find("input[name='id']").val();
        if(proUntil.strIsEmpty(model)){
            proUntil.alert_win("请填写型号");
            return;
        }
        proUntil.request("checkLampTypeModel",{typeId:typeId,model:model,id:id},function(data){
            if(0 != data){
                proUntil.alert_win("此型号已存在");
                return;
            }
            proUntil.saveData("saveLampTypeData",box,function(){
                selectAll();
            });
        });
    }

