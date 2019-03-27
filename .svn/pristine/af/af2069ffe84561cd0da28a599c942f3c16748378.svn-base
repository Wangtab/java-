    $(function() {
        selectAll();

        getOrganizationData("PlatFomSetting/getOrganizationList",$('.search_box'),$('.search_box').find('select[name="orgId"]'),{},'<option value = "">请选择组织</option>');
        getOrganizationData("PlatFomSetting/getOrganizationList",$('.table_win'),$('.table_win').find('select[name="orgId"]'),{},'<option value = "">请选择组织</option>');

        getIOTTypeData($('.table_win'),$('.table_win').find('select[name="iotType"]'),'<option value = "">请选择类型</option>');
    });
    //电信类型
    function getIOTTypeData(parentAll,sel,default_text){
        sel.html("");
        var html = default_text || '';
        var str = parentAll.find("#typeId").val();
        if(str != null && str != ""){
            if(Number(str) == 1){
                html += '<option value = "1" selected="selected">电信</option>';
            }else if(Number(str) == 2){
                html += '<option value = "1">电信</option>';
            }
        }else{
            html += '<option value = "1">电信</option>';
        }
        sel.html(html);
    }
    //组织平台
    function getOrganizationData(url,parentAll,sel,param,default_text,callBackFunction){
        proUntil.request(url,param,function(data){
            sel.html("");
            var html = default_text || '';
            var str = parentAll.find("#oId").val();
            if('n' == data){
                sel.html(html);
                return;
            }
            data = JSON.parse(data);
            $.each(data,function(i,val){
                if(str != null && str != ''){
                    if(Number(str) == val.id){
                        html += '<option value = "'+val.id+'" selected="selected">'+val.orgName+'</option>';
                    }
                    html += '<option value = "'+val.id+'">'+val.orgName+'</option>';
                }else{
                    html += '<option value = "'+val.id+'">'+val.orgName+'</option>';
                }
            });
            sel.html(html);
            if (callBackFunction && typeof(callBackFunction) === "function"){
                callBackFunction();
            }
        });
    }
    //查询全部
    function selectAll(){
        var orgId = $(".search_box").find("select[name='orgId'] option:selected").val();
        proUntil.commonSelect("PlatFomSetting/getDianXiIotData",{"orgId":orgId},function(){
            init_btn();
        });
    }

    function init_btn(){
        $('.edit_button').unbind("click").click(function(){
            proUntil.request("PlatFomSetting/getDianXiIotDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0].data[0];
                var box = $('.table_win');
                box.find('input[name="oId"]').val(val.org_id);
                box.find('#typeId').val(val.iot_type);
                getOrganizationData("PlatFomSetting/getOrganizationList",$('.table_win'),$('.table_win').find('select[name="orgId"]'),{},'<option value = "">请选择组织</option>');
                getIOTTypeData($('.table_win'),$('.table_win').find('select[name="iotType"]'),'<option value = "">请选择类型</option>');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="iotAddress"]').val(val.iot_address);
                box.find('input[name="appId"]').val(val.appid);
                box.find('input[name="secret"]').val(val.secret);
                box.find('input[name="serviceAddress"]').val(val.service_address);
                box.show(manage_table_time);
            });
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
