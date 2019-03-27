	var table_map = null;
	var array_marker = [];
    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据

	$(function() {
		initMap("main_table_mapd");
        init_data();
		init_win_map();
		init_table_map();
		addLis();
        selectAll();
		//关闭详细信息窗口
		$('#close_scond_wind').unbind('click').click(function(){
			$('.second_form').hide(manage_table_time);
		});
	});

	function init_data(){
		var searchBox = $('.search_box');
		var formBox = $('.second_form');
        proUntil.searchLinkSelect(searchBox.find('select[name ="areaId"]'),searchBox.find('select[name ="roadId"]'),searchBox.find('select[name ="lineId"]'));
		proUntil.winLinkSelect(formBox.find('select[name ="areaId"]'),formBox.find('select[name ="roadId"]'),formBox.find('select[name ="lineId"]'));
	}

	function addLis(){
		var xlf = document.getElementById('lampDataImp');
		if(xlf.addEventListener) xlf.addEventListener('change', handleFile, false);
	}

	function handleFile(e) {
		debugger
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
				url:"../ImportDeviceData",
				data:{"data":data},
				success: function (msg) {
					alert(msg);
				}
			});
		};
		reader.readAsBinaryString(file);
	}

	//下载模板
	function downModel(){
		top.location.href="/model/灯具管理模板.xls";
	}

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

	/**
	 * 初始化主页面点的集合
	 *
	 */
	function init_table_map(){
		table_map = initMap('main_table_mapd');
		$('.table_box').find('table').find('tr').each(function(index, el) {
			if(0 != index){
				var _this = $(this);
				var default_text = "暂无信息";
				var td = _this.find('td');
				var address = td.eq(0).text() || default_text;//控制器地址
				var name = td.eq(2).text() || default_text;//控制器名称
				var longitude = td.eq(3).text() || default_text;//经度
				var latitude = td.eq(4).text() || default_text;//纬度
				var text = "<p class = 'detailBox'>控制器地址："+address+"</p>" +
					"<p class = 'detailBox'>控制器名称："+name+"</p>";
				var point = new BMap.Point(longitude,latitude);
				var marker = new BMap.Marker(point);
				var opts = {
					width : 200,     // 信息窗口宽度
					height: 30,     // 信息窗口高度
					position : point,    // 指定文本标注所在的地理位置
					enableMessage:false//设置允许信息窗发送短息
				}
				var infoWindow = new BMap.InfoWindow(text, opts);  // 创建信息窗口对象
				marker.addEventListener("click",function(e){
					this.openInfoWindow(infoWindow,point); //开启信息窗口
				});
				var uuid = getuuid();
				array_marker[uuid] = marker;
				_this.attr('marker',uuid);
				table_map.addOverlay(marker);
				//点击添加动画效果
				_this.click(function(event) {
					var marker_index = _this.attr('marker');
					marker = array_marker[marker_index];
					marker.setAnimation(BMAP_ANIMATION_BOUNCE);
					table_map.setCenter(marker.point);
					setTimeout(function(){
						marker.setAnimation(null);
					},2000);
				});
			}
		});
	}

    //查询全部
    function selectAll(){
        var region_id=$("#region_id option:selected").val();
        var road_id = $("#road_id option:selected").val();
        var line_id = $("#line_id option:selected").val();
        var lampName = $("#lampName option:selected").val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getlampManage",
            data:{"showNum":page_show_Num,"curPage":cur_page,"region_id":region_id,"road_id":road_id,"line_id":line_id,"lampName":lampName},
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
                        '<td>' +val.areaName+'</td>\n' +
                        '<td>'+val.roadName+'</td>\n' +
                        '<td>'+val.cname+'</td>\n' +
                        '<td>'+val.dbcircuit+'</td>\n' +
                        '<td>'+val.poleCode+'</td>\n' +
                        '<td>'+val.lampModel+'</td>\n' +
                        '<td>'+val.dimmingmode+'</td>\n' +
                        '<td>'+val.lampnum+'</td>\n' +
                        '<td>'+val.nbCode+'</td>\n' +
                        '<td>'+val.power+'</td>\n' +
                        '<td>'+val.lampFactory+'</td>\n' +
                        '<td>'+val.lo+'</td>\n' +
                        '<td>'+val.la+'</td>\n' +
                        '<td>'+val.realName+'</td>\n' +
                        '<td>'+val.opertime+'</td>\n' +
                        '<td>\n' +
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
            }
        });
        return false;
    }

    function init_btn() {
        $('.add_btn').unbind('click').click(function() {
            proUntil.clear_form_data($('.second_form'));
            $('.second_form').show(manage_table_time);
        });
        //打开修改窗口
			$('.edit_button').unbind('click').click(function(){
			var url = "/updateLampManage.do";
			$(".second_form").attr("action",url);
			var eid = $(this).attr('data_id');
			$("#udId").val(eid);
			$.post("/queryLampMeg.do",{id:eid},function(data){
				var box = $('.second_form');
				var lamp = eval(data);
				$("#lampnum").val(lamp[0].lampnum);

					/*区域*/
				var region = document.getElementById("regionId");
				region.length = 1;
				regionSelect(region,lamp[0].regionId,lamp[0].roadId,lamp[0].roadlineId);
				/*灯具类型*/
				var lamt = document.getElementById("lamtId");
				lamt.length = 1;
				lamtSelect(lamt,lamp[0].lamptypename,lamp[0].typeId);
				/*配电箱编号*/
				var pdbox = document.getElementById("pdId");
				pdbox.length = 1;
				pdboxSelect(pdbox,lamp[0].pdId);
				/*灯杆编号*/
				$("#poleCode").val(lamp[0].poleCode);
				/*控制器*/
				var controller = document.getElementById("controller_id");
				controller.length = 1;
				controllerSelect(controller,lamp[0].conId);
				/*配电箱回路*/
				var circuit = document.getElementById("dbcircuit");
				circuit.length = 1;
				dbcircuitSelect(circuit,lamp[0].dbcircuit);

				$("#longitude_ipt").val(lamp[0].lo);
				$("#latitude_ipt").val(lamp[0].la);

				$("#udId").val(lamp[0].id);

				win_map.clearOverlays();
				var point = new BMap.Point(lamp[0].lo,lamp[0].la);
				var marker = new BMap.Marker(point);  // 创建标注
				win_map.addOverlay(marker);

                box.show(manage_table_time);
            });
        });

        $('.delete_button').unbind('click').click(function () {
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id', _this.attr('data_id'));
            table_delete_object = _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });
        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function () {
            proUntil.request("../deleteLampManage.do", {"id": $(this).attr('id')}, function (msg) {
                var msg = $.trim(msg);
                if ('y' == msg) {
                    $('.judge_win').hide(manage_table_time);
                    proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                    selectAll();
                } else if ('n' == msg) {
                    proUntil.alert_win(proUntil.OPERTION_FAIL);
                }
            });
        });
    }