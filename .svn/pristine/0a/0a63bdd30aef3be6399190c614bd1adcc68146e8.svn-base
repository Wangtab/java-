    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var sceneId = null;//场景ID
    var groupId = null;//分组ID
    var sort = "desc";
    $(function(){
        $('.sence_table').find('.sence_edit_button').click(function() {
            $('.second_form').show(manage_table_time);
        });

        $('.sence_win_judge_win >p span').click(function() {
            $('.sence_win_judge_win').hide(manage_table_time);
        });

        $('.sence_delete_button').click(function() {
            $('.sence_win_judge_win').show(manage_table_time);
        });

        $('.sence_win_close_judge_win').click(function(){
            $('.sence_win_judge_win').hide(manage_table_time);
        });

        $('.sence_win_sure_delete_judge_win').click(function() {
            $('.sence_win_judge_win').hide(manage_table_time);
        });



        $('.second_form > p span').click(function() {
            $('.second_form').hide(manage_table_time);
        });

        $('.sence_table > p span').click(function() {
            $('.sence_table').hide(manage_table_time);
        });

        $(".dimming_light").slider({
            formatter: function (value){
                return  value + "%";
        }});

        $('.planContent').change(function(){
            var parent_box = $('.second_form');
            var startDate = parent_box.find('.startDate');
            var over_date = parent_box.find('.overDate');
            var start_time = parent_box.find('.startTime');
            var over_time = parent_box.find('.overTime');
            var weekSel = $('.selWeeks');

            startDate.prop('disabled', true);
            over_date.prop('disabled', true);
            start_time.prop('disabled', true);
            over_time.prop('disabled', true);
            weekSel.multiselect('disable');

            weekSel.multiselect('clearSelection');
            startDate.val('');
            over_date.val('');
            start_time.val('');
            over_time.val('');

            var val =  $('.planContent').val();
            if(1 == val){//开关灯
                startDate.prop('disabled', false);
                over_date.prop('disabled', false);
                start_time.prop('disabled', false);
                over_time.prop('disabled', false);
            } else if(2 == val){//调光
                startDate.prop('disabled', false);
                start_time.prop('disabled', false);
            } else if(3 == val){//每天
                start_time.prop('disabled', false);
                over_time.prop('disabled', false);
            } else if(4 == val){ //每周
                start_time.prop('disabled', false);
                over_time.prop('disabled', false);
                weekSel.multiselect('enable');
            }
            init_dateTime();
        });

        $('.selWeeks').multiselect();
        $('.selWeeks').multiselect('disable');
        var areaSelect = $('.second_form').find('select[name ="areaId"]');
        proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
        proUntil.getSelData("lampCommon/getPlanContentForSelect",$('.second_form').find('select[name ="planId"]'));
        areaSelect.change(function(){
            var areaId = areaSelect.val();
            if(proUntil.strIsEmpty(areaId)){
                return;
            }
            proUntil.getSelData("lampCommon/getGroupNameForSelect",$('.second_form').find('select[name ="groupId"]'),{areaId:areaId},'<option value = "">请选择分组</option>',
                function(){
                    if(groupId != null){
                        $('.second_form').find('select[name ="groupId"]').find("option[value='"+groupId+"']").prop("selected",true);
                        groupId = null;
                    }
                });
        });
        selectAll();
        init_dateTime();

        senceSort(); //排序
    });

    function init_btn(){
        //新建窗口
        $('.add_btn').click(function() {
            proUntil.clear_form_data($(".table_win"));
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function() {
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sure_delete_judge_win').click(function(){
            proUntil.delData("deletePlanSenceData",{"id":$(this).attr('id')},$('.judge_win'),function(){
                selectAll();
            });
        });

        //修改
        $('.edit_button').click(function(){
            proUntil.request("getSceneDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="cName"]').val(val.cname);
                box.show(manage_table_time);
            });
        });

        $('.set_pwd_button').click(function(){
            var id = $(this).attr('data_id');
            sceneId = id;
            $(".second_form").find('input[name="sceneId"]').val(id);
            select_deatail();
            $('.sence_table').show(manage_table_time);
        });

        $('.sence_table').find('.sence_add_btn').click(function(){
            var form = $('.second_form');
            proUntil.clear_form_data(form);
            proUntil.setSlider_width($('.detail_box'),0);
            form.show(manage_table_time);
        });

    }

    function saveData(num){
        var cn=$("#cName").val();
        proUntil.request("selectBycname",{cname:cn},function(data){
           if(data==1){
               proUntil.alert_win("场景名称存在！")
           }else{
               var url = "saveSceneData";
               var JQuery_form = $(".table_win");
               if(1 == num){
                   url = "saveSceneDetailData";
                   JQuery_form = $(".second_form");
                   var dimming = $(".dimming_light").slider('getValue') || 0;
                   JQuery_form.find('input[name="dimming"]').val(dimming);
               }
               proUntil.saveData(url,JQuery_form,function(){
                   if(1 == num){
                       select_deatail();
                       return;
                   }
                   selectAll();
               });
           }
        })
    }

    //排序
    function senceSort(){
        $(".senceTh").find("th").not(':last').click(function(){
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
            sort = th_sort;

            var cur = _this.attr("cur");

            $("#sort").val(th_sort);
            $("#orderMsg").val(cur);
            selectAll();
        });
    }

    //查询全部
    function selectAll(){
        $('.table_box table').find("td").remove();
        var search_box = $('.search_box');
        var cname = search_box.find('input[name ="name"]').val();

        var sort = $("#sort").val();
        var orderMsg = $("#orderMsg").val();

        proUntil.request("initSceneSetting",{name:cname,showNum:page_show_Num,curPage:cur_page,sort:sort,orderMsg:orderMsg},function(data){
            var table = $('.senceTh');
            var html = "";
            if('n' == data){
                table.html('');
                check_table_data();
                return;
            }

            data = JSON.parse(data)[0];
            count_page = Math.ceil(parseInt(data.count)/page_show_Num);
            $.each(data.data, function(index, val){
                html += '<tr>' +
                    '<td>'+val.cname+'</td>\n' +
                    '<td>'+val.real_name +'</td>\n' +
                    '<td>'+val.oper_time+'</td>\n' +
                    '<td>' +
                    '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                    '<a title = "设置" data_id = "'+val.id+'" class = "set_pwd_button" href="javascript:void(0);"></a>'+
                    '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                    '</td>' +
                    '</tr>';
            });
            table.after(html);
            $("#controllerPage").html('');
            page({
                id:'controllerPage',
                nowNum: cur_page,
                allNum: count_page
            });

            //网络数据分页点击
            $("#controllerPage").find("a").unbind('click').click(function(){
                var _this = $(this);
                var curPageNum = _this.attr('cur');
                cur_page = parseInt(curPageNum);
                selectAll();
            });
            check_table_data();
            init_btn();
        });
    }

    function select_deatail(){
        proUntil.request("getPlanSceneDetailData",{id:sceneId},function(data){
            var table = $('.sence_table').find('table');
            var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            var html = "";
            if('n' == data){
                table.html(frist_tr);
                return;
            }
            data = JSON.parse(data);
            $.each(data, function(index, val){
                html += '<tr>' +
                    '<td>'+val.areaName+'</td>' +
                    '<td>'+val.group_name+'</td>' +
                    '<td>'+val.cname+'</td>' +
                    '<td>'+(val.weeks || '-')+'</td>' +
                    '<td>'+ (val.start_date || '-')+'</td>' +
                    '<td>'+(val.end_date || '-')+'</td>' +
                    '<td>'+(val.start_time || '-')+'</td>' +
                    '<td>'+(val.end_time || '-')+'</td>' +
                    '<td>'+(val.dimming || '-')+'</td>' +
                    '<td>' +
                    '<a title = "修改" data_id = "'+val.id+'" class = "sence_edit_button" href="javascript:void(0);"></a>' +
                    '<a title = "删除" data_id = "'+val.id+'" class = "sence_delete_button" href="javascript:void(0);"></a>' +
                    '</td>\n' +
                    '</tr>';
            });
            table.html(frist_tr + html);
            init_detail_btn();
        },"false");
    }

    function init_detail_btn(){
        $('.sence_edit_button').click(function(){
            proUntil.request("getPlanSceneDetailById",{"id": $(this).attr('data_id')},function(data){
                data = JSON.parse(data)[0];
                var box = $(".second_form");
                box.find("select[name='planId']").val(data.plan_id);
                box.find("select[name='planId']").change();
                box.find("input[name='id']").val(data.id);
                box.find('select[name="areaId"]').val(data.area_id);
                box.find("input[name='startDate']").val(data.start_date);
                box.find("input[name='endDate']").val(data.end_date);
                box.find("input[name='startTime']").val(data.start_time);
                box.find("input[name='endTime']").val(data.end_time);
                var dimming = data.dimming || 0;
                $(".dimming_light").slider('setValue',dimming);
                proUntil.setSlider_width($('.detail_box'),dimming);
                groupId = data.group_id;
                box.find('select[name="areaId"]').change();

                if(!proUntil.strIsEmpty(data.weeks)){
                    box.find("select[name='weeks']").multiselect('select',data.weeks.split(","));
                }
                box.show(manage_table_time);
            });
        });

        //打开删除窗口
        $('.sence_delete_button').click(function() {
            var _this = $(this);
            $('.sence_win_sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.sence_win_judge_win').show(manage_table_time);
        });

        //删除窗口 确定按钮
        $('.sence_win_sure_delete_judge_win').click(function(){
            proUntil.delData("deleteSceneDetailById",{"id":$(this).attr('id')},$('.sence_win_judge_win'),function(){
                select_deatail();
            });
        });

    }

    function init_dateTime(){
        $('.form-control').datetimepicker({
            locale:'zh-cn',
            format: 'YYYY-MM-DD',
        });
        $(".form-control_hour").datetimepicker({
            locale:'zh-cn',
            format: "HH:mm",

        });
    }