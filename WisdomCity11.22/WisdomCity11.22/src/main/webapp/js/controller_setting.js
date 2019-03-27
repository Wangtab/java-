    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "opertime";//排序字段
    var sort = "desc";

	$(function() {
        selectAll();
        addLis();
        deal_table_th();
        init_data();
	});

	function init_data(){
        $('#close_scond_wind').click(function() {
            $('.second_form').hide(manage_table_time);
        });

        $('.edit_button').click(function(){
            $('.second_form').show(manage_table_time);
        });

        //关闭集中器设置窗口
        $('#concentrator_win_close').click(function(){
            $('.concentrator_win').hide(manage_table_time);
        });

        //打开集中器设置窗口
        $('.table_box').find('.set_pwd_button').click(function(){
            $('.concentrator_win').show(manage_table_time);
        });

        $(".concentrator_box").find('select').change(function() {
            var status = $(this).find("option:selected").attr("status");
            $(".concentrator_win").find('.son_box').css('display', 'none');
            $('.' + status).css('display', 'block');
        });

        var status = $(".concentrator_box").find('select').find("option:selected").attr("status");
        $(".concentrator_win").find('.son_box').css('display', 'none');
        $('.' + status).css('display', 'block');

        //控制器类型
        proUntil.getSelData("lampCommon/getControllerKindForSelect",$('.second_form').find('select[name="controllerkindid"]'),{},'<option value = "">请选择控制器类型</option>');
        //所属集中器
        proUntil.getSelData("lampCommon/getConcentratorForSelect",$('.second_form').find('select[name="concentratorId"]'),{},'<option value = "">请选择集中器</option>');

    }


    function init_btn(){
        //新建窗口
        $('.add_btn').click(function(){
            proUntil.clear_form_data($('.second_form'));
        });

        //编辑窗口
        $('.edit_button').click(function(){
            proUntil.request("../getCOntrollerDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.second_form');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="nbCode"]').val(val.nb_code);
                box.find('select[name="controllerkindid"]').val(val.controllerkindid);
                box.find('input[name="cNum"]').val(val.c_num);
                box.find('input[name="factoryName"]').val(val.factory_name);
                box.find('select[name="concentratorId"]').val(val.concentrator_id);
                box.find('select[name="business"]').val(val.business);
                box.find('select[name="protocol"]').val(val.protocol);
                box.find('input[name="simCode"]').val(val.sim_code);
                box.show(manage_table_time);
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
                proUntil.delData("../deleteControllerById",{"id":$(this).attr('id')},$('.judge_win'),function(){
                    selectAll();
                });
            });
	}

	function saveData(){
        var box = $('.second_form');
        var isOK = proUntil.check_form(box);
        if(!isOK){
            return;
        }
        proUntil.request("saveControllerData",box.serialize(),function(data){
            data = JSON.parse(data)[0];
            if("success" == data.operation){
                proUntil.alert_win(proUntil.OPERTION_SUCCESS);
            } else if ("fail" == data.operation){
                proUntil.alert_win(data.error);
            } else {
                proUntil.alert_win(proUntil.OPERTION_FAIL);
            }
            selectAll();
            box.hide(manage_table_time);
        });
	}

    function selectAll(){
        var dataName = $('.search_box').find('input[type="text"]').val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getControllerDatas",
            data:{"cname":dataName,"showNum":page_show_Num,curPage:cur_page,"sort":sort,"orderBy":orderBy},
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
                $.each(data.data, function(index, val){
                   var business =  val.business;
                   if(business == 1){
                       business = "中国移动";
                   }else if (business == 2){
                       business = "中国联通";
                   } else if(business == 3){
                       business = "中国电信IOT平台";
                   } else if(business == 4){
                       business = "上海产业院IOT平台";
                   }

                   var protocol = val.protocol;
                   if(0 == protocol){
                       protocol = "UDP协议";
                   } else if(1 == protocol){
                       protocol = "COAP协议";
                   }
                    html += '<tr>\n' +
                        '<td>'+val.nb_code1+'</td>\n' +
                        '<td>'+val.kindname+'</td>\n' +
                        '<td>'+val.c_num+'</td>\n' +
                        '<td>'+val.factory_name+'</td>\n' +
                        '<td>'+val.concentratorname+'</td>\n' +
                        '<td>'+business+'</td>\n' +
                        '<td>'+protocol+'</td>\n' +
                        '<td>'+(val.nb_device || '')+'</td>\n' +
                        '<td>'+val.sim_code+'</td>\n' +
                        '<td>'+val.realName+'</td>\n' +
                        '<td>'+val.opertime+'</td>\n' +
                        '<td>\n' +
                        '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "设置" class = "set_pwd_button" href="javascript:void(0);"></a>\n' +
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
                init_btn();
                deal_table_th();
            }
        });
        return false;
    }

    function addLis() {
        var xlf = document.getElementById('controllerDataImp');
        if(xlf.addEventListener) xlf.addEventListener('change', handleFile, false);
    }

    function handleDragover(e) {
        e.stopPropagation();
        e.preventDefault();
        e.dataTransfer.dropEffect = 'copy';
    }

    function onDropDown(e) {
        e.stopPropagation();
        e.preventDefault();
        var files = e.dataTransfer.files;
        var f = files[0];
        readFile(f);
    }

    function handleFile(e) {
        var files = e.target.files;
        var f = files[0];
        readFile(f);
    }

    function readFile(file) {
        if(file == undefined || file == ""){
            return;
        }
        var name = file.name;
        var point = name.lastIndexOf('.');
        var lastName = name.substring(point);
        if('.xls' != lastName){
            proUntil.alert_win('请导入.xls文件');
            return;
        }

        var reader = new FileReader();
        reader.onload = function (e) {
            var data = e.target.result;
            var wb = XLSX.read(data, { type: "binary" });
            var data = JSON.stringify( XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]) );
            $.ajax({
                type:"post",
                dataType:"json",
                url:"../ImportControllerData",
                data:{"data":data},
                success: function (msg) {
                    var info = data[0].info;
                    if("yes" == info){
                        proUntil.alert_win("导入成功");
                    }else {
                        proUntil.alert_win("导入失败请检查excel表中数据");
                    }
                    selectAll();
                }
            });
        };
        reader.readAsBinaryString(file);
    }

    //下载模板
    function downModel(){
        top.location.href="/model/控制器模板.xls";
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