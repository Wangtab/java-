    $(function(){
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });

        $('.reset_win').find('.close_judge_win').click(function(){
            $('.reset_win').hide(manage_table_time);
        });

        selectAll();
    });

    //查询全部
    function selectAll(){
        var data_arr = [];
        data_arr[0] = {title:"修改",className:"edit_button"};
        data_arr[1] = {title:"删除",className:"delete_button"};
        data_arr[2] = {title:"恢复",className:"reset_button"};
        proUntil.commonSelect("safe/getDataBaseData",{"startDate":$('#startDated').val(),"endDate":$("#endDated").val()},function(){
            init_btn();
        },null,null,data_arr);
    }


    function init_btn(){
        //打开修改窗口
        $('.edit_button').unbind('click').click(function(){
            proUntil.request("safe/getDataBaseDataById",{id:$(this).attr('data_id')},function(data){
                data = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(data.id);
                box.find('input[name="info"]').val(data.info);
                box.show(manage_table_time);
            });
        });

        proUntil.init_common_btn();

        $('.reset_button').click(function() {
            var _this = $(this);
            $('.sure_reset_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.reset_win').show(manage_table_time);
        });

        //恢复
        $('.sure_reset_judge_win').unbind('click').click(function(){
            $('.reset_win').hide();
            show_watting();
            proUntil.request("safe/restMySqlData",{"id": $(this).attr('id')},function(data){
                hide_watting();
                if('y' == data){
                    proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                    selectAll();
                } else{
                    proUntil.alert_win(proUntil.OPERTION_FAIL);
                }
            });
        });
    }

    //保存信息
    function saveData(){
        proUntil.saveData("safe/saveMySqlData",$(".table_win"),function(){
            selectAll();
        });
    }

    /**
     * 显示等待按钮
     * [show_watting description]
     * @return {[type]} [description]
     */
    function show_watting(){
        $('.watting_bg').show(300);
        $('.watting_box').show(300);
    }

    /**
     * 隐藏等待按钮
     * [hide_watting description]
     * @return {[type]} [description]
     */
    function hide_watting(){
        $('.watting_bg').hide(300);
        $('.watting_box').hide(300);
    }




