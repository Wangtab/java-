    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var menu_btn_arr = [];
    var menu_btn_list = [];
var orderBy = "operTime";//排序字段
var sort = "desc";
	$(function() {
        init_effect();
        init_role_menu();
        selectAll();
        deal_table_th();
	});

	function init_effect(){
        $('.selRoleWin >p span').click(function() {
            $('.selRoleWin').hide(manage_table_time);
        });

        //重置
        $('.win_btn_box').find('input[name="rest_btn"]').click(function() {
            $('.selRoleWin').find('input[type="checkbox"]').prop('checked', false).change();
        });

        //全选
        $('#selAllBtn').click(function(){
            var _this = $(this);
            var isCheck = _this[0].checked;
            if(isCheck){
                $('.Roletable_box').find('input[type="checkbox"]').prop('checked', true).change();
            }else{
                $('.Roletable_box').find('input[type="checkbox"]').prop('checked', false).change();
            }
        });

        //保存
        $('.win_btn_box').find('input[name="save_btn"]').click(function() {
            var checkbox = $('.Roletable_box').find('input[type="checkbox"]:checked');
            if(checkbox.length == 0){
                proUntil.alert_win("请勾选菜单选项");
                return;
            }
            var arr = [];
            $.each(checkbox,function() {
                var val = $(this).val();
                var obj = {'menu_id':val};
                arr.push(obj);
            });
            var data_arr = [];
            data_arr[0] = arr;
            data_arr[1] = menu_btn_arr;
            data_arr = JSON.stringify(data_arr);
            proUntil.request("safe/savePowerList",{"powerJson": data_arr,"roleId":$(this).attr('data_id')},function (msg){
                if('y' == msg){
                    proUntil.alert_win('操作成功');
                    $('.selRoleWin').hide(manage_table_time);
                    $('.btn_win').hide(manage_table_time);
                } else if('n' == msg){
                    proUntil.alert_win('操作失败，请刷新页面后再次尝试');
                }
            });

        });

        //关闭按钮窗口
        $('.btn_win > p span').click(function(){
            var box =  $('.btn_win');
            var len = box.find('tr').length;
            if(0 == len){
                box.hide(manage_table_time);
                return;
            }
            var menu_id = $(this).attr('menu_id');
            var checkbox = box.find('input[type="checkbox"]');
            $.each(checkbox,function(index,val){
                var status = val.checked == true ? 1 : 0;
                var btnId = val.value;
                var isOk = false;

                $.each(menu_btn_arr,function(index,btn){
                    if(btnId == btn.id){
                        btn.status = status;
                        isOk = true;
                    }
                });

                if(!isOk && 1 == status){
                    var obj = {
                        menu_id : menu_id,
                        id : btnId,
                        status:1
                    };
                    menu_btn_arr.push(obj);
                }
            });
            box.hide(manage_table_time);
        });
    }

	function init_RoleMenubtn(){
		$('.set_pwd_button').click(function(){
		    var _this = $(this);
		    var roleId =  _this.attr("data_id");
            $('.win_btn_box').find('input[name="save_btn"]').attr('data_id',roleId);
            init_checkboxChange();
            $.ajax({
                type:"post",
                dataType:"text",
                url:"safe/getMenuListByRoleId",
                data:{"id": roleId},
                success: function (data){
                    if("n" == data){
                        $('.selRoleWin').show(manage_table_time);
                        return;
                    }
                    data = JSON.parse(data);
                    $('.selRoleWin').find('input[type="checkbox"]').prop('checked', false);
                    for (var i = 0; i < data.length; i++) {
                        $('#a'+data[i].menu_id).prop('checked', true);
                    }

                    proUntil.request("menu/getMenuDataByRoleId",{"roleId": roleId},function(data){
                        if("n" != data){
                            menu_btn_arr = JSON.parse(data);
                        }
                        $('.selRoleWin').show(manage_table_time);
                    });
                }
            });
		});
	}

	function init_checkboxChange(){
	    $('.Roletable_box').find('.second_cell').find('input[type="checkbox"]').change(function(){
	        var _this = $(this);
            var isCheck = _this[0].checked;
            var menu_id = _this[0].value;
            var btnList = menu_btn_list['a'+menu_id];
            if(proUntil.strIsEmpty(btnList) || btnList.length == 0){
                return;
            }

            var del_arr = [];
            $.each(menu_btn_arr,function(index,val){
                if(menu_id == val.menu_id){
                    del_arr.push(index);
                }
            });
            if(del_arr.length > 0){
                del_arr.sort(function(a,b){
                    return b - a;
                });
            }

            $.each(del_arr,function(index,val){
                menu_btn_arr.splice(val, 1);
            });

            if(isCheck){
                $.each(btnList,function(index,val){
                    var obj = {
                        menu_id:val.menuId,
                        status:1,
                        id :val.id
                    };
                    menu_btn_arr.push(obj);
                });
            }
        });
    }


	function init_BtntModel(){
        //父模块点击 子模块全选
		$('.frist_cell').find('input[type="checkbox"]').click(function() {
			var _this = $(this);
			var isCheck = _this[0].checked;
			if(isCheck){
				var checkbox = _this.parents('tr').find('.second_cell').find('input[type="checkbox"]');
				//如果子模块都没有被选中 子模块全选
				if(!checkSonModelIsChecked(checkbox)){
					checkbox.prop('checked', true).change();
				}
			}else{
				_this.parents('tr').find('.second_cell').find('input[type="checkbox"]').prop('checked', false).change();
			}
		});
        //初始化子模块点击后 父模块被选中
        $('.second_cell').find('input[type="checkbox"]').click(function(){
            var _this = $(this);
            var isCheck = _this[0].checked;
            if(isCheck){
                _this.parents('tr').find('.frist_cell').find('input[type="checkbox"]').prop('checked', true);
            }else{
                var checkbox = _this.parents('tr').find('.second_cell').find('input[type="checkbox"]');
                //如果子模块都没有被选中 父模块取消选中
                if(!checkSonModelIsChecked(checkbox)){
                    _this.parents('tr').find('.frist_cell').find('input[type="checkbox"]').prop('checked', false);
                }
            }
        });

        //初始化按钮
        $('.second_cell').find('img').click(function(){
            var menu_id = $(this).attr("menu_id");
            $('.btn_win > p span').attr("menu_id",menu_id);
            var table = $('.btn_win').find('table');
            var model_list = menu_btn_list['a'+menu_id];
            if(model_list == null || model_list.length == 0){
                table.html('');
                $('.btn_win').show(manage_table_time);
                return;
            }
            var html = "";
            $.each(model_list,function(index,val){
                var status = _elementsHasInArray(val.id,menu_btn_arr);
                var isCheck = 0 ==  status ? "" : "checked = 'checked'";
                html += ' <tr>' +
                    '<td>' +
                    '<label for="btn_'+val.id+'">' +
                    '<input type="checkbox" value = "'+val.id+'" name="" '+isCheck+' id="btn_'+val.id+'">' + val.btnName +
                    '</label>\n' +
                    '</td>\n' +
                    '</tr>';
            });
            table.html(html);
            $('.btn_win').show(manage_table_time);
        });
	}

	function _elementsHasInArray(element,arr){
	    var status = 0;
	    if(proUntil.strIsEmpty(element)){
	        return status;
        }

        $.each(arr,function(index,value){
            if(element == value.id){
                status = value.status;
                return status;
            }
        });
        return status;
    }

	//判断子模块是否被选中
	function checkSonModelIsChecked(obj){
		for (var i = 0; i < obj.length; i++) {
			var isCheck =obj[i].checked;
			if(isCheck){
				return true;
			}
		}
		return false;
	}

    /**
     * 初始化权限菜单
     */
    function init_role_menu(){
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../menu/getMenuDataAndBtns",
            success: function (data){
                data = JSON.parse(data);
                var html = "";
                for (var i = 0; i < data.length; i++){
                    var top_menu = data[i];
                    var list = top_menu.list;
                    var model_html = "<tr>" +
						'<td width = "18%" class = "frist_cell">' +
							'<label for="a'+top_menu.id+'">' +
								'<input type="checkbox" value = "'+top_menu.id+'" id="a'+top_menu.id+'">'
								+ top_menu.cName +
							'</label>' +
						'</td>';
                	model_html += '<td>';
                    for (var j = 0; j < list.length; j++) {
                        var son_menu =  list[j];
                        menu_btn_list['a'+son_menu.id] = son_menu.btnList;
                        model_html += '<div class="second_cell">' +
                            '<img menu_id = "'+son_menu.id+'"  src="images/role_setting_btn.png"/>'+
								'<label for="a'+son_menu.id+'">' +
                            		'<input type="checkbox" value = "'+son_menu.id+'" id="a'+son_menu.id+'">'
                            	+ son_menu.cName +
                        		'</label></div>';
                    }
                	model_html += '</td></tr>';
                    html += model_html;
                }
                $('.Roletable_box').find('table').html(html);
                init_BtntModel();
            }
        });
    }

    function init_btn(){
        //新建窗口
        $('.add_btn').click(function(){
            proUntil.clear_form_data($('.table_win'));
        });

        //编辑窗口
        $('.edit_button').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"safe/getRoleDataById",
                data:{"id": $(this).attr('data_id')},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    var box = $('.table_win');
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="roleName"]').val(val.role_name);
                    box.find('input[name="roleDesc"]').val(val.role_desc);
                    box.show(manage_table_time);
                }
            });
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function(){
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function(){
            proUntil.delData("safe/deleteRoleDataById",{"id":$(this).attr('id')},$('.judge_win'),function(){
                selectAll();
            });
        });
        init_RoleMenubtn();
    }

    //保存信息
    function saveData(){
        proUntil.saveData("safe/saveRoleData",$('.table_win'),function(){
            selectAll()
        });
    }

    //查询全部
    function selectAll(){
        var roleName = $('.search_box').find('input[type="text"]').val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"safe/getRoleManageData",
            data:{"roleName":roleName,"showNum":page_show_Num,"curPage":cur_page,"orderBy":orderBy,"sort":sort},
            success: function (data){
                var table = $('.table_box').find('table');
                var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
                var html = "";
                if('n' == data){
                    table.html('');
                    check_table_data();
                    return;
                }
                data = data[0];
                count_page = Math.ceil(parseInt(data.count)/page_show_Num);
                $.each(data.datas, function(index, val){
                    html += '<tr>\n' +
                        '<td>'+(index+1)+'</td>\n' +
                        '<td>'+val.role_name+'</td>\n' +
                        '<td>'+(val.role_desc || '')+'</td>\n' +
                        '<td>'+val.real_name+'</td>\n' +
                        '<td>'+val.oper_time+'</td>\n' +
                        '<td>' +
                        '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "设置权限"  data_id = "'+val.id+'" class = "set_pwd_button" href="javascript:void(0);"></a>' +
                        '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                        '</td>\n' +
                        '</tr>';
                });
                table.html(frist_tr + html);
                $("#controllerPage").html('');
                page({
                    id:'controllerPage',
                    nowNum: cur_page,
                    allNum: count_page
                });

                //网络数据分页点击
                $("#controllerPage").find("a").unbind('click').click(function(){
                    var _this = $(this);
                    var curpage = _this.attr('cur');
                    cur_page = parseInt(curpage);
                    selectAll();
                });
                check_table_data();
                init_btn();
                deal_table_th();
            }
        });
        return false;
    }

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
        selectAll();
    });
}