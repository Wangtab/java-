	$(function() {
        init_btns();
		//主页面区域选择
		$('#selGroupArea').change(function() {
		var area_val = 	$('#selGroupArea').val();
        var htmls = '<option value="">请先选择区域</option>';
        $('#selGroupd').html(htmls);
		if(null == area_val || '' == area_val) return;
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getPlanGroupIdByAreaId",
                async:false,
                data:{"areaId":area_val},
                success: function (data) {
                    data = JSON.parse(data);
                    var html = '<option value="">请先选择区域</option>';
                    $.each(data, function(index, val) {
                        html += '<option value = "'+val.id+'">'+val.group_name+'</option>';
                    });
                   $('#selGroupd').html(html);
                }
            });
		});

		//窗口区域选择
        $('#win_area_sel').change(function(){
            var area_val = 	$('#win_area_sel').val();
            $('#win_group_sel').html('');
            if(null == area_val || '' == area_val) return;
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getPlanGroupIdByAreaId",
                async:false,
                data:{"areaId":area_val},
                success: function (data) {
                    data = JSON.parse(data);
                    var html = '';
                    $.each(data, function(index, val) {
                        html += '<option value = "'+val.id+'">'+val.group_name+'</option>';
                    });
                    $('#win_group_sel').html(html);
                }
            });
        });



        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../deletePlanSenceData",
                async:false,
                data:{"id": $('#save_sence_id').val()},
                success: function (data) {
                    data = $.trim(data);
                    if('y' == data){
                        jqueryAlert({
                            'content' : '操作成功',
                            'closeTime' : alert_common_time,
                        });
                        $('#save_sence_id').val('');
                        table_delete_object.remove();
                        $('.judge_win').hide(manage_table_time);
                        check_table_data();
                    } else {
                        jqueryAlert({
                            'content' : '操作失败 请稍后尝试',
                            'closeTime' : alert_common_time,
                        });
                    }
                }
            });
        });
        /** 显示执行情况相关信息*/
        //左上角X
        $('.sence_wind').find('>p').find('span').click(function() {
            $('.sence_wind').hide(manage_table_time);
        });

        //关闭按钮
        $('.sence_wind').find('.sence_wind_btn').click(function() {
            $('.sence_wind').hide(manage_table_time);
        });
        check_right_tabe_data();
	});

    function check_right_tabe_data(){
        var table = $('.right_group').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    function check_left_tabe_data(){
        var table = $('.left_group').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    function addExecuteSetBtnClick(){
        //打开执行情况
        $('.right_group').find('.set_pwd_button').click(function() {

            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getPlanSenceExecuteStand",
                async:false,
                data:{"id": $(this).attr('sence_id')},
                success: function (data) {
                    data = JSON.parse(data);
                    var table = $('.sence_wind_table_box').find('table');
                    var frist_tr = '<tr>' + table.find('tr').eq(0).html() +'</tr>';
                    table.html('');
                    var html = '';
                    $.each(data, function(index, val) {
                        var is_work = val.is_work;
                        var controllerName = val.controllername || '';
                        if(0 == is_work){
                            is_work = '未执行';
                        } else if(1 == is_work){
                            is_work = '已执行';
                        } else {
                            is_work = '';
                        }
                        html += ''+
                            '<tr>' +
                                '<td>'+(index+1)+'</td>'+
                                '<td>'+controllerName+'</td>'+
                                '<td>'+val.group_name+'</td>'+
                                '<td>'+is_work+'</td>'
                            '</tr>';
                    });
                    table.html(frist_tr +html);
                    $('.sence_wind').show(manage_table_time);
                }
            });
        });
    }

    function strIsEmpty(str){
        if(null == str || '' == str || undefined == str){
            return true;
        }
        return false;
    }

    /**
     * 根据区域和分组查询数据信息
     */
    function selectAll(){
        var areaId = $('#selGroupArea').val();
        var groupId =  $('#selGroupd').val();
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../ajaxGetPlanSenceManage",
            async:false,
            data:{"areaId":areaId,'groupId':groupId},
            success: function (data) {
                var table = $('.left_group').find('table');
                var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
                var html = '';
                data = JSON.parse(data);
                $.each(data,function(index,val){
                    html += '<tr test_light = "'+val.test_light+'" sence_id = "'+val.id+'">\n' +
                        '<td>'+val.areaName+'</td>\n' +
                        '<td>'+val.group_name+'</td>\n' +
                        '<td>'+val.cname+'</td>\n' +
                        '<td>\n' +
                        '<a title = "修改" sence_id = "'+val.id+'" area_id = "'+val.area_id+'" group_id = "'+val.group_id+'" test_light = "'+val.test_light+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "删除" sence_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                        '</td>\n' +
                        '</tr>';
                });
                table.html(frist_tr + html);
                check_left_tabe_data();
                check_right_tabe_data();
                init_btns();
            }
        });
    }

    function init_btns(){
        //新建窗口
        $('.add_btn').unbind('click').click(function(){
            $('#win_area_sel').removeAttr('disabled');
            $('#win_group_sel').removeAttr('disabled');
            $('#save_sence_id').val('');
            $('#cnamed').val('');
            $('#test_light').val('');
            $('.table_win').show(manage_table_time);
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function(){
            var _this = $(this);
            var id = _this.attr('sence_id');
            $('#save_sence_id').val(id);
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //编辑窗口
        $('.edit_button').unbind('click').click(function(){
            var _this = $(this);
            var td = _this.parents('tr').find('td');
            var group_name = td.eq(1).html();
            var sence_name = td.eq(2).html();
            $('#cnamed').val(sence_name);
            var id = _this.attr('sence_id');
            $('#save_sence_id').val(id);
            $('#test_light').val(_this.attr('test_light'));
            var area_id = _this.attr('area_id');
            $('#win_area_sel').val(area_id).attr("disabled","disabled");
            $('#win_group_sel').html('<option>'+ group_name+'</option>');
            $('#win_group_sel').attr("disabled","disabled");
            $('.table_win').show(manage_table_time);
        });

        //左侧table点击 右侧table显示数据
        $('.left_group').find('tr').click(function(){
            var _this = $(this);
            var sence_id = _this.attr('sence_id');
            if(strIsEmpty(sence_id)) return;
            var td = _this.find('td');
            var right_table = $('.right_group').find('table');
            var frist_tr = '<tr>' + right_table.find('tr').eq(0).html() + '</tr>';
            var html = '' +
                '<tr>' +
                '<td>'+td.eq(2).html()+'</td>'+
                '<td>'+_this.attr('test_light')+'%</td>'+
                '<td>'+td.eq(1).html()+'</td>'+
                '<td>'+td.eq(0).html()+'</td>'+
                '<td><a title="执行情况" sence_id = '+sence_id+' class="set_pwd_button" href="javascript:void(0);"></a></td>'+
                '</tr>';
            right_table.html(frist_tr +html);
            addExecuteSetBtnClick();
        });
    }