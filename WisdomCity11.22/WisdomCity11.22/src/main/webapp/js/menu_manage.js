var cur_page = 1;//当前页数
var count_page = 1;//总共页数
var page_show_Num = 10;//每页显示多少条数据
var orderBy = "opertime";//默认排序字段
var sort = "desc";
    $(function(){
        init_selFistMenu();
        init_btn();
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

    function init_selFistMenu(){
        proUntil.getSelData("menu/getFistMenuData",$('.table_win').find('select[name="pid"]'),{},'<option value="0">无上级节点</option>');
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
                url:"menu/getMenuDataById",
                data:{"id": $(this).attr('data_id')},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    debugger
                    var box = $('.table_win');
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="cName"]').val(val.cname);
                    box.find('input[name="curl"]').val(val.curl);
                    box.find('input[name="img"]').val(val.img);
                    box.find('input[name="orderBy"]').val(val.orderby);
                    box.find('select[name="pid"]').val(val.pid);
                    box.show(manage_table_time);
                }
            });
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function() {
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function(){
            proUntil.delData("menu/delMenuById",{"id":$(this).attr('id')},$('.judge_win'),function(){
                selectAll();
            });
        });
    }

    //保存信息
    function saveData(){
        proUntil.saveData("menu/saveMenuData",$('.table_win'),function(){
            selectAll()
        });
    }

    //查询全部
    function selectAll(){
        var menuName = $('.search_box').find('input[type="text"]').val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"menu/getMenuDataForPage",
            data:{"menuName":menuName,"showNum":page_show_Num,"curpage":cur_page,"orderBy":orderBy,"sort":sort},
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
                        '<td>'+val.cname+'</td>\n' +
                        '<td>'+(val.curl || '')+'</td>\n' +
                        '<td>'+(val.img || '')+'</td>\n' +
                        '<td>'+val.orderby+'</td>\n' +
                        '<td>'+val.real_name+'</td>\n' +
                        '<td>'+val.opertime+'</td>\n' +
                        '<td>' +
                        '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
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
