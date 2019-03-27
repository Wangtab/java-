		var win_map = null;
		var table_map = null;
		var overlays = [];
        var cur_page = 1;//当前页数
        var count_page = 1;//总共页数
        var page_show_Num = 10;//每页显示多少条数据
        var color = ["#993333","#663366","#993399","#663300","#660099","#cc3366","#ff9900","#993366","#ff6666",
            "#333366","#006633","#666633","#996600","#666600","#009933","#cc0033","#660033"];
        var orderBy = "oper_time";//排序字段
        var sort = "desc";
		$(function() {
            init_data();
			init_win_map();
            selectAll();
		});

		function init_data(){
            table_map = initMap("main_table_mapd");
            $('.form_box').find('input[type=button]').click(function(){
                $('.table_win').find('input[name="points"]').val('');
                clearAll();
            });
            //获取区域信息
            proUntil.getSelData("lampCommon/getAreaNameForSelect",$('.search_box').find('select[name ="selAreaName"]'),{},'<option value = "">请选择区域</option>');
            //获取区域信息
            proUntil.getSelData("lampCommon/getAreaNameForSelect",$('.table_win').find('select[name ="areaId"]'),{},'<option value = "">请选择区域</option>');
        }

	function init_win_map(){
		win_map = initMap("win_mapd");
	    //样式绘制
	    var styleOptions = {
	        strokeColor:"red",    //边线颜色。
	        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
	        strokeWeight: 6,       //边线的宽度，以像素为单位。
	        strokeOpacity: 1,	   //边线透明度，取值范围0 - 1。
	        fillOpacity: 0.8,      //填充的透明度，取值范围0 - 1。
	        strokeStyle: 'solid' //边线的样式，solid或dashed。
	    }
	    //实例化鼠标绘制工具
	    var drawingManager = new BMapLib.DrawingManager(win_map, {
	        isOpen: false, //是否开启绘制模式
	        enableDrawingTool: false, //是否显示工具栏
	        drawingToolOptions: {
	            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
	            offset: new BMap.Size(5, 5), //偏离值
	        },
	        circleOptions: styleOptions, //圆的样式
	        polylineOptions: styleOptions, //线的样式
	        polygonOptions: styleOptions, //多边形的样式
	        rectangleOptions: styleOptions //矩形的样式
	    });
	    drawingManager.setDrawingMode(BMAP_DRAWING_POLYLINE);
	    drawingManager.addEventListener('overlaycomplete', function(e){
	    	overlays.push(e.overlay);
	    	getDrawArea();
	    });
	    win_map.addEventListener("rightclick", function(e){
	    	if(drawingManager._isOpen==false){
	    		clearAll();
	    		drawingManager.open();
	    	}
	    });
	}

	 function clearAll(){
		for(var i = 0; i < overlays.length; i++){
            win_map.removeOverlay(overlays[i]);
        }
        overlays.length = 0   
    }

    //获取矩形点集合
    function getDrawArea(){
    	if(overlays == null || overlays.length < 1) return;
    	var point_array = [];
    	debugger
    	var pos_array = overlays[0].ia;
    	var styles = {
    		    strokeColor:'red',    //边线颜色。
    		    fillColor:'red',      //填充颜色。当参数为空时，圆形将没有填充效果。
    		    strokeWeight: 3,       //边线的宽度，以像素为单位。
    		    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    		    fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
    		    strokeStyle: 'solid' //边线的样式，solid或dashed。
    		};
    	for (var i = 0; i < pos_array.length;i++){
    		var point = new BMap.Point(pos_array[i].lng,pos_array[i].lat);
			point_array.push(point);
    	}
    }

        function selectAll(){
            table_map.clearOverlays();
            var dataName = $('.search_box').find('input[type="text"]').val();
            var areaId = $('.search_box').find('select[name ="selAreaName"]').val();
            $.ajax({
                type:"post",
                dataType:"json",
                url:"../getRoadManageData",
                data:{"cname":dataName,"showNum":page_show_Num,"curPage":cur_page,"areaId":areaId,"sort":sort,"orderBy":orderBy},
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
                            '<td>'+val.road_name+'</td>\n' +
                            '<td>'+val.road_des+'</td>\n' +
                            '<td>'+val.areaName+'</td>\n' +
                            '<td>'+val.real_name+'</td>\n' +
                            '<td>'+val.oper_time+'</td>\n' +
                            '<td>\n' +
                            '<a title = "修改" data_id = "'+val.id+'" area_id = "'+val.area_id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                            '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                            '</td>\n' +
                            '</tr>';

                        var hasPoints = proUntil.strIsEmpty(val.points);
                        if(!hasPoints){
                            //绘制区域
                            drawAreas(val.points,val.road_name);
                        }
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

        function init_btn(){
            //新建窗口
            $('.add_btn').unbind('click').click(function(){
                clearAll();
                proUntil.clear_form_data( $('.table_win'));
                $('.table_win').show(manage_table_time);
            });

            //打开修改窗口
            $('.edit_button').unbind('click').click(function(){
                proUntil.request("../getRoadNameManageById",{"roadId": $(this).attr('data_id')},function(data){
                    var val = JSON.parse(data)[0];
                    var box = $('.table_win');
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="points"]').val(val.points);
                    box.find('input[name="roadDes"]').val(val.road_des);
                    box.find('input[name="roadName"]').val(val.road_name);
                    box.find('select[name ="areaId"]').val(val.area_id);
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
                proUntil.request("../deleteRoadManageDataById",{"roadId":$(this).attr('id')},function(msg){
                    var msg = $.trim(msg);
                    if('y' == msg){
                        $('.judge_win').hide(manage_table_time);
                        proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                        selectAll();
                    } else if('n' == msg){
                        proUntil.alert_win(proUntil.OPERTION_FAIL);
                    } else {
                        proUntil.alert_win('该路段下有正在使用的灯杆，请先删除灯杆信息');
                    }
                });
            });
        }

        //保存信息
        function saveData(){
            if(overlays != null && overlays.length > 0){
                var points = JSON.stringify(overlays[0].ia);
                $('.table_win').find('input[name="points"]').val(points);
            }
            proUntil.saveData("../saveRoadNameData",$('.table_win'),function(){
                selectAll();
            });
        }

        //绘制区域
        function drawAreas(data,areaName){
            data = JSON.parse(data);
            var arr = [];
            var colorOne = color[Math.floor(Math.random()*color.length)];
            var araeStyles = {
                strokeColor:colorOne,    //边线颜色。
                fillColor:colorOne,      //填充颜色。当参数为空时，圆形将没有填充效果。
                strokeWeight: 6,       //边线的宽度，以像素为单位。
                strokeOpacity: 1,	   //边线透明度，取值范围0 - 1。
                fillOpacity: 0.8,      //填充的透明度，取值范围0 - 1。
                strokeStyle: 'solid' //边线的样式，solid或dashed。
            };
            $.each(data,function(i,val){
                var point = new BMap.Point(val.lng,val.lat);
                arr.push(point);
            });
            var p = new BMap.Point(data[0].lng,data[0].lat);
            var opts = {
                position : p,    // 指定文本标注所在的地理位置
                offset   : new BMap.Size(30, -30)    //设置文本偏移量
            }
            var label = new BMap.Label(areaName, opts);
            label.setStyle({
                color : "#000",
                fontSize : "12px",
                height : "20px",
                lineHeight : "20px",
                "padding-left":"26px",
                "padding-right":"6px",
                fontFamily:"微软雅黑",
                border:"1px solid 248efc",
                background:'url(images/map_flag.png) 3px 2px no-repeat',
                "background-color":"#fff",
            });
            var polygon = new BMap.Polygon(arr, araeStyles);
            table_map.addOverlay(polygon);
            table_map.addOverlay(label);
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