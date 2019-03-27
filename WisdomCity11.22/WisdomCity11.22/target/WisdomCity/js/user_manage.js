var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10;//每页显示多少条数据
var orderBy = "operTime";//排序字段
var sort = "desc";
$(function() {
    $('#close_update_pwd_win').click(function() {
        $('.update_pwd').hide(manage_table_time);
    });
    //获取角色
    proUntil.getSelData("safe/getRolManageForSelect",$('.table_win').find('select[name="authId"]'));
    //获取组织
    proUntil.getSelData("safe/getOrgManageForSelect",$('#srh_org'),{},"<option value=''>请选择要查询的平台</option>");
    proUntil.getSelData("safe/getOrgManageForSelect",$('.table_win').find('select[name="orgId"]'));
    selectAll();
    deal_table_th();
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
        selectAll();
    });
}

    function changePwd(){
        var old_pwd = $('#son_old_pwd').val().trim();
        var new_pwd = $('#son_new_pwd').val().trim();
        var repeat_pwd = $('#son_repeat_pwd').val().trim();
        var userId = $('.update_pwd').find('input[name="id"]').val();
        if(new_pwd != repeat_pwd){
            jqueryAlert({
                'content' : '两次密码输入不一致,请重新输入',
                'closeTime' : alert_common_time,
            });
            return;
        }
        $.ajax({
            type:"post",
            dataType:"text",
            url:"safe/changeUserPwd",
            data:{"oldPwd":old_pwd,"new_pwd":new_pwd,"userId":userId},
            success: function (msg) {
                if("no_pwd" == msg){
                    jqueryAlert({
                        'content' : '输入旧密码不正确',
                        'closeTime' : alert_common_time,
                    });
                } else if('y' == msg){
                    jqueryAlert({
                        'content' : '操作成功',
                        'closeTime' : alert_common_time,
                    });
                    $('.update_pwd').hide(manage_table_time);
                }else{
                    jqueryAlert({
                        'content' : '操作失败，请稍后再次尝试',
                        'closeTime' : alert_common_time,
                    });
                }
            }
        });
    }




    /*修改用户密码*/
    function alterpwd(){
        var userid = $('#save').attr('userid');
        var old_pwd = $('#son_old_pwd').val().trim();
        var new_pwd = $('#son_new_pwd').val().trim();
        var repeat_pwd = $('#son_repeat_pwd').val().trim();
        if(new_pwd != repeat_pwd){
            jqueryAlert({
                'content' : '两次密码输入不一致,请重新输入',
                'closeTime' : alert_common_time,
            });
            return false;
        }
    }

    //查询全部
    function selectAll(){
        var box = $('.search_box');
        var userName = box.find('input[name="userName"]').val();
        var realName = box.find('input[name="realName"]').val();
        var orgId = $('#srh_org').val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"safe/getUserList",
            data:{"showNum":page_show_Num,"curPage":cur_page,"userName":userName,"realName":realName,"orgId":orgId,"sort":sort,"orderBy":orderBy},
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
                        '<td>'+val.user_name+'</td>\n' +
                        '<td>'+val.real_name+'</td>\n' +
                        '<td>'+val.role_name+'</td>\n' +
                        '<td>'+val.org_name+'</td>\n' +
                        '<td>'+val.oper_name+'</td>\n' +
                        '<td>'+val.oper_time+'</td>\n' +
                        '<td>' +
                        '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "重新设置密码" data_id = "'+val.id+'" class = "set_pwd_button" href="javascript:void(0);"></a>' +
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
                deal_table_th();
                init_btn();
            }
        });
        return false;
    }

    function init_btn(){
        //新建窗口
        $('.add_btn').click(function(){
            proUntil.clear_form_data($('.table_win'));
        });


        $('.edit_button').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"safe/getUserById",
                data:{"id": $(this).attr('data_id')},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    var box = $('.table_win');
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="userName"]').val(val.user_name);
                    box.find('input[name="realName"]').val(val.real_name);
                    box.find('select[name="authId"]').val(val.auth_id);
                    box.find('select[name="orgId"]').val(val.org_id);
                    box.show(manage_table_time);
                }
            });
        });

        $('.set_pwd_button').click(function() {
            var _this = $(this);
            $('.update_pwd').find('input[name="id"]').val(_this.attr('data_id'));
            $('.update_pwd').show(manage_table_time);
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
            proUntil.delData("safe/delUserById",{"id":$(this).attr('id')},$('.judge_win'),function(){
                selectAll();
            });
        });
    }

    function saveData(){
        //添加时判断用户名是否存在
        var un=$("#userName").val();
        var og=$("#orgId").val();
        proUntil.request("safe/getUserByUsername",{"username":un,"orgid":og},function(data){
          if(data>0){
              proUntil.alert_win("用户名存在！");
          }else{
              proUntil.saveData("safe/saveUserData",$('.table_win'),function(){
                  selectAll()
              });
          }
        })
    }

