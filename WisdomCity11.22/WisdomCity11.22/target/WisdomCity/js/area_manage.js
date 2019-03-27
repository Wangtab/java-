	var win_map = null;
	var table_map = null;
	var overlays = [];//图形绘制点集合
    var color = ["#CD3700","#B03060","#556B2F","#4A708B","#8B008B","#CDAD00"];
    var styles = {
        strokeColor:'red',    //边线颜色。
        fillColor:'red',      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    };
	$(function() {
        table_map = initMap("main_table_map");
        init_win_map();
        selectAll();
		//清除原有图形信息
		$('.form_box').find('input[type=button]').unbind('click').click(function(){
            $('.table_win').find('input[name="points"]').val('');
			clearAll();
		});
	});

	function init_win_map(){
		win_map = initMap("win_map");
        clearAll();
	    //实例化鼠标绘制工具
	    var drawingManager = new BMapLib.DrawingManager(win_map, {
	        isOpen: false, //是否开启绘制模式
	        enableDrawingTool: false, //是否显示工具栏
	        drawingToolOptions: {
	            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
	            offset: new BMap.Size(5, 5), //偏离值
	        },
	        circleOptions: styles, //圆的样式
	        polylineOptions: styles, //线的样式
	        polygonOptions: styles, //多边形的样式
	        rectangleOptions: styles //矩形的样式
	    });
	    drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
	    drawingManager.addEventListener('overlaycomplete', function(e){
	    	overlays.push(e.overlay);
	    });
	    win_map.addEventListener("rightclick", function(e){
	    	if(drawingManager._isOpen==false){
	    		clearAll();
	    		drawingManager.open();
	    	}
	    });
	}

	 function clearAll(){
        overlays.length = 0;
         win_map.clearOverlays();
    }

    //查询全部
    function selectAll(){
        table_map.clearOverlays();
        proUntil.commonSelect("getAreaManageByareaName",{"areaName":$('.search_box').find('input[type="text"]').val()},function(){
            init_btn();
        },drawAreas);
    }


    function init_btn(){
        //新建窗口
        $('.add_btn').unbind('click').click(function(){
            win_map.clearOverlays();
            proUntil.clear_form_data( $('.table_win'));
        });

        //打开修改窗口
        $('.edit_button').unbind('click').click(function(){
            proUntil.request("../getAreaManageDataById",{"areaId": $(this).attr('data_id')},function(data){
                win_map.clearOverlays();
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="areaname"]').val(val.areaName);
                box.find('input[name="areades"]').val(val.areaDes);
                box.find('input[name="points"]').val(val.points);
                var isOk = proUntil.strIsEmpty(val.points);
                if(!isOk){
                    //绘制区域
                    drawwinAreas(val.points);
                }
                box.show(manage_table_time);
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
            proUntil.request("../delteAreaMangeDataById",{"areaId":$(this).attr('id')},function(msg){
                var msg = $.trim(msg);
                if('y' == msg){
                    $('.judge_win').hide(manage_table_time);
                    proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                    selectAll();
                } else if('n' == msg){
                    proUntil.alert_win(proUntil.OPERTION_FAIL);
                } else {
                    proUntil.alert_win('该区域下有正在使用的路段，请先删除相关路段信息');
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

        proUntil.saveData("../saveAreaMangeData",$('.table_win'),function(){
            selectAll();
        });
    }

    //绘制区域
    function drawAreas(data){
        if(proUntil.strIsEmpty(data.points)){
            return;
        }
        var areaName = data.areaName;
        data = JSON.parse(data.points);
        var arr = [];
        var colorOne = color[Math.floor(Math.random()*color.length)];
        var araeStyles = {
            strokeColor:colorOne,    //边线颜色。
            fillColor:colorOne,      //填充颜色。当参数为空时，圆形将没有填充效果。
            strokeWeight: 1,       //边线的宽度，以像素为单位。
            strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
            fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
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

    //绘制窗体区域
    function drawwinAreas(data){
        data = JSON.parse(data.points);
        var arr = [];
        $.each(data,function(i,val){
            var point = new BMap.Point(val.lng,val.lat);
            arr.push(point);
        });
        var polygon = new BMap.Polygon(arr, styles);
        win_map.addOverlay(polygon);
    }