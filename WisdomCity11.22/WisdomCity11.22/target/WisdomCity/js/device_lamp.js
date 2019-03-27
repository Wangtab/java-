	var table_map = null;
	var win_map = null;
	var array_marker = [];
	var lampModel = null;

	$(function() {
		init_win_map();
		init_table_map();
		addLis();
        selectAll();
		init_data()
	});

    function init_data(){
    	var searchBox = $('.search_box');
    	var winBox = $('.second_form');
    	proUntil.searchLinkSelect(searchBox.find('select[name="areaId"]'),searchBox.find('select[name="roadId"]'),searchBox.find('select[name="lineId"]'));
        proUntil.winLinkSelect(winBox.find('select[name="areaId"]'),winBox.find('select[name="roadId"]'),winBox.find('select[name="roadlineId"]'));

        $('.add_btn').unbind('click').click(function() {
            proUntil.clear_form_data(winBox);
            winBox.find('select[name="typeSel"]').val(1);
            winBox.find('select[name="typeSel"]').change();
            winBox.show(manage_table_time);
        });

        //关闭详细信息窗口
        $('#close_scond_wind').unbind('click').click(function(){
            winBox.hide(manage_table_time);
        });

        proUntil.getSelData("lampCommon/getDistributionBoxForSelect",winBox.find('select[name="pdId"]'),{},'<option value="">请选择配电箱</option>');
        proUntil.getSelData("lampCommon/getControllerNumDataForSelect",winBox.find('select[name="controllerId"]'),{},'<option value="">请选择控制器</option>');

        winBox.find('select[name="typeSel"]').change(function(){
        	var val = winBox.find('select[name="typeSel"]').val();
            proUntil.getSelData("lampCommon/getLampTypeDataForSelect",winBox.find('select[name="typeId"]'),{typeId:val},null,function(){
            	if(lampModel != null){
                    winBox.find('select[name="typeId"]').find("option[value='"+lampModel+"']").prop("selected",true);
                    lampModel = null;
				}
			});
		});

    }

	function addLis(){
		var xlf = document.getElementById('lampDataImp');
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

    function selectAll(){
		var searchBox = $('.search_box');
		var param = {
			areaId : searchBox.find('select[name="areaId"]').val(),
			roadId : searchBox.find('select[name="roadId"]').val(),
			lineId : searchBox.find('select[name="lineId"]').val(),
			poleCode : searchBox.find('input[name="poleCode"]').val()
		};

        proUntil.commonSelect("getLampManageData",param,function(){
            init_btn();
        });
    }

    function saveData(){
        proUntil.saveData("saveLampManageData",$(".second_form"),function(){
            selectAll();
        });
    }

    function init_btn() {
		$('.edit_button').unbind('click').click(function(){
            proUntil.request("getLampManageDataById",{"id": $(this).attr('data_id')},function(data){
                win_map.clearOverlays();
            	data = JSON.parse(data)[0];
				var box = $('.second_form');
				proUntil.ROAD_ID = data.road_id;
				proUntil.ROAD_LINE_ID = data.roadline_id;
                lampModel = data.typeId;
				box.find('select[name="areaId"]').val(data.area_id);
                box.find('select[name="areaId"]').change();
                box.find('input[name="lampnum"]').val(data.lampnum);
                box.find('select[name="typeSel"]').val(data.lamptypename);
                box.find('select[name="typeSel"]').change();
                box.find('select[name="pdId"]').val(data.pdId);
                box.find('select[name="dbcircuit"]').val(data.dbcircuit);
                box.find('input[name="poleCode"]').val(data.poleCode);
                box.find('select[name="controllerId"]').val(data.controller_id);
                box.find('input[name="lo"]').val(data.lo);
                box.find('input[name="la"]').val(data.la);
                var point = new BMap.Point(data.lo,data.la);
                var marker = new BMap.Marker(point);  // 创建标注
                win_map.addOverlay(marker);
                box.show(manage_table_time);
			});
        });

        proUntil.init_common_btn();
    }