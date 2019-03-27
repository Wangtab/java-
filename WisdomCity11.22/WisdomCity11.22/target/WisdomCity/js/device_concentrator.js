	var win_map = null;
	var table_map = null;
	var array_marker = [];
    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "opertime";//排序字段
    var sort = "desc";
    var roadId = null;
    var orderBy = "operTime";//排序字段
    var sort = "desc";
	$(function() {
		init_win_map();
        init_data();
        selectAll();
        addLis();
	});

	function init_win_map(){
		win_map = initMap('win_mapd');
		win_map.addEventListener("click",function(e){
		var longitude = e.point.lng;//经度
		var latitude = e.point.lat;//纬度
		$('#longitude_ipt').val(longitude);
		$('#latitude_ipt').val(latitude);
		win_map.clearOverlays();
		var point = new BMap.Point(longitude,latitude);
		var marker = new BMap.Marker(point);  // 创建标注
		win_map.addOverlay(marker); 
		});
	}

    function selectAll(){
        var dataName = $('.search_box').find('input[type="text"]').val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getConcentratorListData",
            data:{"cname":dataName,"showNum":page_show_Num,"curPage":cur_page,"sort":sort,"orderBy":orderBy},
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
                    html += '<tr>\n' +
                        '<td>'+val.concentratoraddr+'</td>\n' +
                        '<td>'+val.concentratorname+'</td>\n' +
                        '<td>'+val.concentratordes+'</td>\n' +
                        '<td>'+val.areaName+'</td>\n' +
                        '<td>'+val.road_name+'</td>\n' +
                        '<td>'+val.name+'</td>\n' +
                        '<td>'+val.concentrator_kind+'</td>\n' +
                        '<td>'+val.concentrator_model+'</td>\n' +
                        '<td>'+val.factory_name+'</td>\n' +
                        '<td>'+val.lo+'</td>\n' +
                        '<td>'+val.la+'</td>\n' +
                        '<td>'+val.real_name+'</td>\n' +
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
    }

    //初始化界面信息
    function init_data(){
        //获取配电箱
        proUntil.getSelData("lampCommon/getDistributionBoxForSelect",$('.second_form').find('select[name="ibox"]'),{},'<option value = "">请选择配电箱</option>');
        var search_box = $('.second_form');
        proUntil.winLinkSelect(search_box.find('select[name ="areaid"]'),search_box.find('select[name ="roadId"]'));

        var status = $(".concentrator_box").find('select').find("option:selected").attr("status");
        $(".concentrator_win").find('.son_box').css('display', 'none');
        $('.' + status).css('display', 'block');

        //关闭集中器设置窗口
        $('#concentrator_win_close').click(function(){
            $('.concentrator_win').hide(manage_table_time);
        });

        //打开集中器设置窗口
        $('.table_box').find('.set_pwd_button').click(function(){
            $('.concentrator_win').show(manage_table_time);
        });

        $('#close_scond_wind').click(function() {
            $('.second_form').hide(manage_table_time);
        });

        $(".concentrator_box").find('select').change(function() {
            var status = $(this).find("option:selected").attr("status");
            $(".concentrator_win").find('.son_box').css('display', 'none');
            $('.' + status).css('display', 'block');
        });

    }

    function init_btn(){
        //新建窗口
        $('.add_btn').unbind('click').click(function(){
            win_map.clearOverlays();
            proUntil.clear_form_data($('.second_form'));
        });

        //编辑窗口
        $('.edit_button').unbind('click').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"../getConcentratorDataById",
                data:{"id": $(this).attr('data_id')},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    var box = $('.second_form');
                    proUntil.ROAD_ID = val.road_id;
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="concentratoraddr"]').val(val.concentratoraddr);
                    box.find('select[name="concentratorkindid"]').val(val.concentratorkindid);
                    box.find('select[name="ibox"]').val(val.ibox);
                    box.find('input[name="concentratorname"]').val(val.concentratorname);
                    box.find('input[name="concentratordes"]').val(val.concentratordes);
                    box.find('select[name="areaid"]').val(val.areaid);
                    box.find('select[name="areaid"]').change();
                    box.find('input[name="ip"]').val(val.ip);
                    box.find('input[name="subnetmask"]').val(val.subnetmask);
                    box.find('input[name="defaultgateway"]').val(val.defaultgateway);
                    box.find('input[name="serverip"]').val(val.serverip);
                    box.find('input[name="serverport"]').val(val.serverport);
                    box.find('input[name="lo"]').val(val.lo);
                    box.find('input[name="la"]').val(val.la);
                    box.find('input[name="cKind"]').val(val.concentrator_kind);

                    box.find('input[name=cModel]').val(val.concentrator_model);
                    box.find('input[name="factoryName"]').val(val.factory_name);
                    win_map.clearOverlays();
                    var point = new BMap.Point(val.lo,val.la);
                    var marker = new BMap.Marker(point);  // 创建标注
                    win_map.addOverlay(marker);
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
                proUntil.delData("../deleteConcentratorById",{"id":$(this).attr('id')},$('.judge_win'),function(){
                    selectAll();
                });
            });


    }

    function saveData(){
        proUntil.saveData("../saveConcentratorData",$('.second_form'),function(){
            selectAll()
        });

    }

    function addLis() {
        var xlf = document.getElementById('controllerDataImp');
        if(xlf.addEventListener) xlf.addEventListener('change', handleFile, false);
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
                url:"../ImportConcentratorData",
                data:{"data":data},
                success: function (data) {
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
	    var url = "model/集中器模板.xls";
        top.location.href=url;

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
