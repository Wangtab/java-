var sort = "desc";
$(function() {
		sortNum();
        init_selec_data();
        check_right_tabe_data();
        init_btns();

        //table 关闭按钮
        $('#light_group_win_close').click(function() {
            $('.light_group_win').hide(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../delPlanGroup",
                async:false,
                data:{"id":$('#plan_id').val()},
                success: function (msg) {
                    msg = $.trim(msg);
                    if(msg=='y'){
                        $('.judge_win').hide(manage_table_time,function(){
                            proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                            table_delete_object.remove();
                            check_left_table_data();
                            $(".right_group tr:gt(0)").remove();
                            check_right_tabe_data();
                        });
                    }else{
                        proUntil.alert_win(proUntil.OPERTION_FAIL);
                    }
                }
            });
        });

        //选择路段
        $('#roadSeld').change(function(){
            var _this = $('#roadSeld');
            var group_id = $('#roadSeld').attr('group_id');
            var val = _this.val();
            $('.right_table').find('table').html('');
            if('' == val || null == val ) return;
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getConCernDataByRoadId",
                async:false,
                data:{"roadId":val,"groupId":group_id},
                success: function (data) {
                    data = JSON.parse(data);
                    var str = "";
                    $.each(data[0],function(index,val) {
                        str +="" +
                            '<tr>'+
							'<td style = "width:45px">'+(index + 1)+'</td>'+
							'<td style = "width:165px">'+val.concentratorname+'</td>'+
							'<td style = "width:99px">'+val.controllername+'</td>'+
							'<td style = "width:90px">'+val.lampname+'</td>'+
							'<td style = "width:42px">'+val.lampnum+'</td>'+
							'<td style = "width:78px">'+val.road_name+'</td>'+
							'<td style = "width:42px">'+
							'<a class = "add_button" concen_id = "'+val.concen_id+'"  controller_id = "'+val.controller_id+'" lamp_id = "'+val.lamp_id+'"  href="javascript:void(0);">+</a>'+
							'</td>'+
                        	'</tr>';
                    });
                    $('.left_table').find('table').html(str);
                    $('.left_table').find('a').click(function() {
                        left_table_click($(this)[0]);
                    });

                    str = "";
                    //初始化选择的集中器
                    var sel_data = data[1];
                    if(null == sel_data || sel_data.length == 0) return;
                    $.each(data[1],function(index,val) {
                        str +="" +
                            '<tr>'+
                            '<td style = "width:45px">'+(index + 1)+'</td>'+
                            '<td style = "width:125px">'+val.concentratorname+'</td>'+
                            '<td style = "width:99px">'+val.controllername+'</td>'+
                            '<td style = "width:80px">'+val.lampname+'</td>'+
                            '<td style = "width:42px">'+val.lampnum+'</td>'+
                            '<td style = "width:52px">'+val.road_name+'</td>'+
                            '<td style = "width:38px">'+val.is_work+'</td>'+
                            '<td style = "width:38px">'+
                            '<a class = "add_button" concen_id = "'+val.concen_id+'"  controller_id = "'+val.controller_id+'" lamp_id = "'+val.lamp_id+'"  href="javascript:void(0);">-</a>'+
                            '</td>'+
                            '</tr>';
                    });
                    $('.right_table').find('table').html(str);
                    $('.right_table').find('a').click(function() {
                        right_table_click($(this)[0]);
                    });
                }
            });
        });

        //提交按钮
        $('#subGroupBtn').click(function() {
            var a = $('.right_table').find('a');
            var len = a.length;
            if(0 == len){
                proUntil.alert_win('请选择分组信息');
                return;
            }
            var group_id = $('#roadSeld').attr('group_id');
            var data_arr = new Array();
            $.each(a, function(index, val){
                var _this = $(val);
                var data = new Object();
                data.concen_id = _this.attr('concen_id');
                data.controller_id = _this.attr('controller_id');
                data.lamp_id = _this.attr('lamp_id');
                data.is_work = 0;
                data.group_id = group_id;
                data.road_id = $('#roadSeld').val();
                data_arr[index] = data;
            });
            var json = JSON.stringify(data_arr);
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../savePlanGroupSelGroupData",
                async:false,
                data:{"data":json},
                success: function (msg) {
                    msg = $.trim(msg);
                    if('y' == msg){
                        $('.light_group_win').hide(manage_table_time);
                        proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                    }else{
                        proUntil.alert_win(proUntil.OPERTION_FAIL);
                    }
                }
            });
        });

        leftGroupSort(); //左侧排序
    });


    //左侧排序
    function leftGroupSort(){
        $(".left_group_th").find("th").not(":first").not(":last").click(function(){
            var _this = $(this);
            var th_sort = _this.attr('sort');
            $('.left_group_th').find('th').removeAttr('sort');
            $('.left_group_th').find('th').find('img').remove();
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
            sort = th_sort;

            var cur = _this.attr("cur");

            $("#sort").val(th_sort);
            $("#orderMsg").val(cur);

            selectAll();
        });
    }

	//左侧按钮点击 将信息添加到右侧
	function left_table_click(obj){
		var _this = $(obj);
		var parent_tr = _this.parents('tr');
		parent_tr.find('td').eq(0).html($('.right_table').find('tr').length + 1);
		parent_tr.find('td').eq(6).find('a').html('-');
		parent_tr.find('td').eq(5).append('<td style = "width:38px">0</td>');
		var left_tr = '<tr>'+parent_tr.html()+'</tr>';
		parent_tr.remove();
		$('.right_table').find('table').append(left_tr);
		$('.right_table').find('a').unbind('click').click(function(){
			right_table_click($(this)[0]);
		});
		sortNum();
	}

	//右侧按钮点击 将信息添加到左侧
	function right_table_click(obj){
		var _this = $(obj);
		var parent_tr = _this.parents('tr');
		parent_tr.find('td').eq(0).html($('.left_table').find('tr').length + 1);
		parent_tr.find('td').eq(7).find('a').html('+');
		parent_tr.find('td').eq(6).remove();
		var left_tr = '<tr>'+parent_tr.html()+'</tr>';
		parent_tr.remove();
		$('.left_table').find('table').append(left_tr);
		$('.left_table').find('a').unbind('click').click(function() {
			left_table_click($(this)[0]);
		});
		sortNum();
	}

	//整理序号
	function sortNum(){
		//整理序号
		$('.right_table').find('tr').each(function(index, el) {
			var _this = $(this);
			_this.find('td').eq(0).html(index + 1);
		});

		$('.left_table').find('tr').each(function(index, el) {
			var _this = $(this);
			_this.find('td').eq(0).html(index + 1);
		});
	}

    /**
	 * 初始化选择参数
     */
	function init_selec_data(){
        check_left_table_data();
	}

    //查找是否有信息
    function check_left_table_data(){
        var table = $('.left_group').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    //查找右侧表是否有信息
    function check_right_tabe_data(){
        var table = $('.right_group').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    function selectAll(){
        var areaId = $('#manSeld').val();

        var sort = $("#sort").val();
        var orderMsg = $("#orderMsg").val();

        $(".left_group").find("td").remove();
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../ajaxGetPlanGroupManage",
            data:{"areaId":areaId,sort:sort,orderMsg:orderMsg},
            async:false,
            success: function (data) {

                var table = $('.left_group_th');
                var html = "";
                data = JSON.parse(data);

                $.each(data, function(index, val) {
                    html += ' <tr plan_id = "'+val.id+'">\n' +
                        '<td><input type="checkbox" name=""/></td>\n' +
                        '<td>'+val.group_name+'</td>\n' +
                        '<td>'+val.group_code+'</td>\n' +
                        '<td>'+praseStrEmpty(val.areaName)+'</td>\n' +
                        '<td>\n' +
                        '<a title = "添加" plan_id = "'+val.id+'" area_id = "'+val.area_id+'" class = "add_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "修改" plan_id = "'+val.id+'" area_id = "'+val.area_id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "删除" plan_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                        '</td>\n' +
                        '</tr>';
                });
                table.after(html);
                init_btns();
                check_left_table_data();
            }
        });
    }
    function praseStrEmpty(str){
    if(!str || str=="undefined" || str=="null"){
        return "";
    }
    return str;
}

    //保存信息
    function saveData(){
        proUntil.saveData("../savePlanGroup",$('.table_win'),function(){
            selectAll()
        });
        return false;
    }

    function init_btns(){
        //打开编辑菜单
        $('.edit_button').unbind('click').click(function(){
            var _this = $(this);
            var area_id = _this.attr('area_id');
            var id = _this.attr('plan_id');
            var group_name = _this.parents('tr').find('td').eq(1).html();
            var table = $('.table_win');
            table.find('select').val(area_id);
            table.find('input[type=text]').val(group_name);
            $('#plan_id').val(id);
            table.show(manage_table_time);
        });

        //新建窗口
        $('.add_btn').click(function(){
            $('#plan_id').val('');
            $('.table_win').find('input[name="groupName"]').val('');
            $('.table_win').show(manage_table_time);
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function(){
            var _this = $(this);
            var id = _this.attr('plan_id');
            $('#plan_id').val(id);
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //左侧行点击
        $('.left_group').find('tr').click(function(){
            var _this = $(this);
            var plan_id = _this.attr('plan_id');
            if(null == plan_id || '' == plan_id) return;
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getDetailByPlanId",
                async:false,
                data:{"planId":_this.attr('plan_id')},
                success: function (data){
                    data = JSON.parse(data);
                    var html = "";
                    var first_tr = '<tr>' + $('.right_group').find('tr').eq(0).html() + "</tr>";
                    if(data == null || data.length == 0){
                        $('.right_group').find('table').html(first_tr);
                        check_right_tabe_data();
                        return;
                    }
                    $.each(data, function(index, val) {
                        var status = val.is_work == 0 ? '未执行' : '已执行';
                        html += '' +
                            '<tr>'+
                            '<td>'+(index+1)+'</td>'+
                            '<td>'+praseStrEmpty(val.concentratorname)+'</td>'+
                            '<td>'+praseStrEmpty(val.controllername)+'</td>'+
                            '<td>'+praseStrEmpty(val.lampname)+'</td>'+
                            '<td>'+praseStrEmpty(val.lampnum)+'</td>'+
                            '<td>'+praseStrEmpty(val.road_name)+'</td>'+
                            '<td>'+praseStrEmpty(status)+'</td>'+
                            '</tr>';
                    });
                    $('.right_group').find('table').html(first_tr + html);
                }
            });
        });

        //table 添加按钮
        $('.left_group .add_button').click(function(){
            var group_id = $(this).attr('plan_id');
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getPlanRoadByAreaId",
                async:false,
                data:{"areaId":$(this).attr('area_id')},
                success: function (data) {
                    data = JSON.parse(data);
                    var road_sel = $('#roadSeld');
                    var str = "<option value = ''>选择道路</option>";
                    $.each(data,function(index,val) {
                        str +="<option value = '"+val.id+"'>"+val.road_name+"</option>";
                    });
                    road_sel.html(str);
                    road_sel.attr('group_id',group_id);
                    $('.light_group_win').find('table').html('');
                    $('.light_group_win').show(manage_table_time);
                }
            });
        });

    }

