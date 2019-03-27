    $(function() {
        selectAll();

    });

    //查询全部
    function selectAll(){
        var btn_obj = {title:"操作",className:"edit_button"};
        var data_arr = [];
        data_arr[0] = btn_obj;
        proUntil.commonSelect("PlatFomSetting/telecomManageList",null,function(){
            init_btn();
        },null,null,data_arr);
    }

    function init_btn(){
        $('.edit_button').unbind("click").click(function(){
            proUntil.request("subscribe/sendSubscribeData",{"id": $(this).attr('data_id')},function(data){
                if(data == '' || data == null){
                    proUntil.alert_win("开启失败，请稍后开启");
                }else{
                    proUntil.alert_win("开启成功");
                }
            });
            selectAll();
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function(){
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        proUntil.init_common_btn();
    }

    function saveData(){
        proUntil.saveData("PlatFomSetting/updateDianXiIotDataById",$(".table_win"),function(){
            selectAll();
        });
    }
