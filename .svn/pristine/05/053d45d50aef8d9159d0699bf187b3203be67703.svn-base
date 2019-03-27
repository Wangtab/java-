    $(function(){
        $(".form-control_hour").datetimepicker({
            locale:'zh-cn',
            format: "HH:mm"
        });

        $('.form-control').datetimepicker({
            locale:'zh-cn',
            format: "YYYY-MM-DD"
        });

        selectAll();
    });

    //查询全部
    function selectAll(){
        var search_box = $('.search_box');
        proUntil.commonSelect("safe/getDataBaseStrategy",
            {startDate:search_box.find('input[name ="startDate"]').val(),endDate:search_box.find('input[name ="endDate"]').val()},function(){
                init_btn();
        });
    }

    function init_btn(){
        //打开修改窗口
        $('.edit_button').unbind('click').click(function(){
            proUntil.request("safe/getDataBaseStrategyById",{"id": $(this).attr('data_id')},function(data){
                data = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(data.id);
                box.find('input[name="clname"]').val(data.clname);
                box.find('input[name="zxtime"]').val(data.zxtime);
                box.find('select[ame="zxname"]').val(data.zxname);
                box.show(manage_table_time);
            });
        });

        proUntil.init_common_btn();
    }

    //保存信息
    function saveData(){
        proUntil.saveData("safe/saveDataBaseStrategy",$(".table_win"),function(){
            selectAll();
        });
    }




