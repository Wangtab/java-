var orderBy = "lampnum";//排序字段
var sort = "desc";
        $(function() {
            //主页面区域选择得出路段相关信息
            $('#selGroupArea').change(function(){
                var area_val = 	$('#selGroupArea').val();
                var htmls = '<option value="">请先选择区域</option>';
                $('#selRoad').html(htmls);
                if(null == area_val || '' == area_val) return;
                $.ajax({
                    type:"post",
                    dataType:"text",
                    url:"../getRoadByAreaId",
                    async:false,
                    data:{"id":area_val},
                    success: function (data) {
                        data = JSON.parse(data);
                        var html = '<option value="">请先选择区域</option>';
                        $.each(data, function(index, val) {
                            html += '<option value = "'+val.id+'">'+val.road_name+'</option>';
                        });
                        $('#selRoad').html(html);
                    }
                });
            });

            //主页面路段选择得出灯具相关信息
            $('#selRoad').change(function(){
                var road_val = 	$('#selRoad').val();
                var htmls = '<option value="">请先选择道路</option>';
                $('#selLight').html(htmls);
                if(null == road_val || '' == road_val) return;
                $.ajax({
                    type:"post",
                    dataType:"text",
                    url:"../getLampByRoadId",
                    async:false,
                    data:{"id":road_val},
                    success: function (data) {
                        data = JSON.parse(data);
                        var html = '<option value="">请先选择道路</option>';
                        $.each(data, function(index, val) {
                            html += '<option value = "'+val.id+'">'+val.lampname+'</option>';
                        });
                        $('#selLight').html(html);
                    }
                });
            });

            $(".form-control_hour").datetimepicker({
                locale:'zh-cn',
                format: "HH:mm",

            });

            //工作计划更变时候
            $('#light_content').change(function(){
                var val = $('#light_content').val();
                if(3 == val){
                    $('.change_light_dimming').css('display','block');

                } else{
                    $('.change_light_dimming').css('display','none');
                }
            });
            check_right_tabe_data();
        });

        function deal_table_th(){
            $('.table_box').find('th').not(':last').click(function(){
                var _this = $(this);
                var th_sort = _this.attr('sort');
                $('.table_box').find('th').removeAttr('sort');
                $('.table_box').find('th').find('img').remove();
                if(proUntil.strIsEmpty(sort)){
                    th_sort = "desc";
                    _this.html(_this.html() + '<img src="images/th_ico_down.png">');
                } else if ("asc" == sort){
                    th_sort = "desc";
                    _this.html(_this.html() + '<img src="images/th_ico_down.png">');
                } else if("desc" == sort){
                    th_sort = "asc";
                    _this.html(_this.html() + '<img src="images/th_ico_up.png">');
                }
                _this.attr('sort',th_sort);
                orderBy =  _this.attr("orderBy");
                sort = th_sort;
                getLightStrategyData();
            });
        }

    /**
     * 根据灯具ID获取相关离线数据
     */
    function getLightStrategyData(){
        var lamp_id = $('#lampNum').val();
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../getLampStrategyByLampId",
            async:false,
            data:{"lampId":lamp_id,"orderBy":orderBy,"sort":sort},
            success: function (data) {
                debugger
                data = JSON.parse(data);
                var html = '';
                var table = $('.left_group').find('table');
                var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
                $.each(data, function(index, val){
                    var plan_content = val.cname;
                    if(strIsEmpty(plan_content)){
                        plan_content = '';
                    }
                    html += '<tr>\n' +
                        '<td>'+val.lampname+'</td>\n' +
                        '<td>'+plan_content+'</td>\n' +
                        '<td>\n' +
                        '<a title = "添加" lamp_id = "'+lamp_id+'" class = "add_button" href="javascript:void(0);"></a>'+
                        '<a title = "修改" lamp_id = "'+lamp_id+'" iswork = "'+val.is_work+'" lightId = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "删除" lightId = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                        '</td>\n' +
                        '</tr>';
                });
                table.html(frist_tr + html);
                init_btns();
                deal_table_th();
            }
        });
    }

    function strIsEmpty(str){
        if(null == str || '' == str || undefined == str){
            return true;
        }
        return false;
    }

    function check_right_tabe_data(){
        var table = $('.right_group').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    function check_left_table_data(){
        var table = $('.left_group').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>请根据条件进行查询</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    function init_btns(){
        //新建窗口
        $('.left_group').find('.add_button').click(function() {
            $('.table_win').find('input[type="text"]').val('');
            $('#light_id').val($(this).attr('lightId'));
            $('#win_lampId').val($(this).attr('lamp_id'));
            $('.table_win').show(manage_table_time);
        });

        //打开修改窗口
        $('.left_group').find('.edit_button').unbind('click').click(function() {
            var light_id = $(this).attr('lightId');
            var iswork = $(this).attr('iswork');
            if(1 == iswork){
                proUntil.alert_win('任务已执行完毕,请勿操作');
                return;
            }
            $('.table_win').find('input[type="text"]').val('');
            $('#light_id').val(light_id);
            $('#win_lampId').val($(this).attr('lamp_id'));
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getLampStrategyById",
                async:false,
                data:{"id":light_id},
                success: function (data) {
                    data = JSON.parse(data);
                    $.each(data, function(index, val) {
                        var box = $('.table_win');
                        box.find('input[name="cname"]').val(val.cname);
                        $('#light_content').val(val.content_id);
                        box.find('input[name="exe_time"]').val(val.exe_time);
                        box.find('input[name="dimming"]').val(val.dimming);
                    });
                    $('.table_win').show(manage_table_time);
                }
            });
        });


        //打开删除按钮
        $('.left_group').find('.delete_button').click(function() {
            var _this = $(this);
            var id = _this.attr('lightId');
            $('.sure_delete_judge_win').attr('light_id',id);
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../delLampStrategyData",
                async:false,
                data:{"id":$(this).attr('light_id')},
                success: function (msg) {
                    var msg = $.trim(msg);
                    if('y' == msg){
                        $('.judge_win').hide(manage_table_time,function(){
                            jqueryAlert({
                                'content' : '操作成功',
                                'closeTime' : alert_common_time,
                            });
                        });
                        table_delete_object.remove();
                        check_left_table_data();

                    } else {
                        jqueryAlert({
                            'content' : '操作失败，请刷新页面后再次尝试',
                            'closeTime' : alert_common_time,
                        });
                    }
                }
            });
        });

        //点击tr显示右侧信息
        $('.left_group').find('tr').click(function(){
            var _this = $(this);
            var id = _this.find('.edit_button').attr('lightId');
            if(null == id || id == "") return;
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getLampStrategyById",
                async:false,
                data:{"id":id},
                success: function (data) {
                    data = JSON.parse(data);
                    var table = $('.right_group').find('table');
                    var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
                    var html = '';
                    if(null == data || data.length){
                        check_right_tabe_data();
                    }
                    $.each(data, function(index, val) {
                        var is_work = val.is_work;
                        if(0 == is_work){
                            is_work = '未执行';
                        } else if(1 == is_work){
                            is_work = '已执行';
                        } else {
                            is_work = '';
                        }

                        html += '<tr>' +
                            '<td>'+(index+1)+'</td>'+
                            '<td>'+val.controllername+'</td>' +
                            '<td>'+val.lampnum+'</td>' +
                            '<td>'+val.road_name+'</td>' +
                            '<td>'+val.cname+'</td>' +
                            '<td>'+val.conten_name+'</td>' +
                            '<td>'+val.exe_time+'</td>' +
                            '<td>'+(val.dimming || '')+ '</td>' +
                            '<td>'+is_work+'</td>' +
                            '</tr>';
                    });
                    var right_table = $('.right_group').find('table');
                    right_table.html(frist_tr + html);
                }
            });
        });

    }

    /**
     * 保存工作计划
     */
    function saveData(){
        var data = $('.table_win').serialize();
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../saveLampStrategyData?" +data,
            async:false,
            success: function (msg) {
               msg =  $.trim(msg);
               if('y' == msg){
                   $('.table_win').hide(manage_table_time,function(){
                       getLightStrategyData();
                       proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                   });
               }else{
                   proUntil.alert_win(proUntil.OPERTION_FAIL);
               }
            }
        });
    }

