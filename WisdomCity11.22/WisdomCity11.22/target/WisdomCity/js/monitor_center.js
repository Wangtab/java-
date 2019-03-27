﻿    var statistics_switch_times = 600;
    var lamp_obj = null; //单灯打开时obj
    var LIGHT_SWITCH_OPERATION = 0;//灯控制操作
    var LIGHT_DIMMING_OPERATION = 1;//调光控制操作
    //单灯、道路 分组 回路 集中器 执行时间设定
    //循环查看处理结果时间
    var liaght_switch_times = null;//循环函数
    var lamp_uuid = "";
    var cur_page = 1;//当前页数
    var count_page = 0;//总共页数
    var page_show_Num = 20;//每页显示多少条数据
    var repepatGetDataTimes = 250 * 1000;//重复获取数据的间隔
    var map = '';//百度地图

    //灯具类型
    var LAMP_TYPE_COMMON_LAMP = 1;//市电灯具
    var LAMP_TYPE_SUN_LAMP = 2;//太阳能灯

    //开灯状态
    var LAMP_OPEN_STATUS = 0;//开灯状态
    var LAMP_CLOSE_STATUS = 1;//关灯状态

    //连接状态
    var NB_LAMP_CONNECT_STATE_ONlINE = 1;
    var NB_LAMP_CONNECT_STATE_OFFlINE = 2;
    var NB_LAMP_CONNECT_STATE_EXCEPTION = 3;
    var NB_ELE_BOX_CONNECT_STATE_ONlINE = 4;
    var NB_ELE_BOX_CONNECT_STATE_OFFlINE = 5;

    //设备类型
    var DEVICE_TYPE_LAMP = 1;
    var DEVICE_TYPE_CONCENTRATOR = 2;


    $(function(){
        init_effect();
        repeatLampData();
        liaght_switch_times = setInterval(repeatLampData,repepatGetDataTimes);
        show_all_lamp_data();
        //初始额一些数据新
        getDade1();
        init_roadList();
    });

    function init_effect(){
        map = initMap("allmap");
        map.disableDoubleClickZoom();
        var searchBox = $('.showAllData_win');
        proUntil.searchLinkSelect(searchBox.find('select[name = "areaId"]'),searchBox.find('select[name = "roadId"]'),searchBox.find('select[name = "lineId"]'));

        //关闭网络数据
        $('.showAllData_win > p span').click(function(){
            $('.device_num').find('.data_list_btn')[0].mark = true;
            $('.showAllData_win').animate({"top": "100%",'opacity':0}, 500);
        });

        $('.device_num').find('.data_list_btn').click(function() {
            var _this = $(this);
            var dom_this = _this[0];
            var mark = dom_this.mark;
            if(!mark){
                $('.showAllData_win').animate({"top": "100%",'opacity':0}, 500);
            }else{
                $('.showAllData_win').animate({"top": "50px",'opacity':1}, 500);
            }
            dom_this.mark = !mark;
        });


        //灯控窗口切换
        $('.lamp_controller_box').find('.cl_controller_group_name').find('span').click(function(){
            var _this = $(this);
            _this.addClass('current_group').siblings().removeClass('current_group');
            $('.cl_group_detail').css('display', 'none');
            $('.cl_group_detail').eq(_this.index()).css('display', 'block');
        });

        //集中器切换
        $('.concentrator_box').find('.cl_controller_group_name').find('span').click(function() {
            var _this = $(this);
            _this.addClass('current_group').siblings().removeClass('current_group');
            $('.cl_group_detail').css('display', 'none');
            $('.concentrator_box').find('.cl_group_detail').eq(_this.index()).css('display', 'block');
        });

        $('.controller_light > p span').click(function() {
            $('.controller_light').hide(400);
        });

        $(".dimming_light").slider({
            formatter: function (value){
                return  value + "%";
        }});

        //详细信息
        $('.roadList').find('i').click(function(){
            commonHide($('.roadList'),"right",$('.roadList_btn'));
        });

        $('.roadList_btn').click(function(){
            $(this).hide();
            commonShow($('.roadList'),"right");
        });

        $("#selectAllBtnd").unbind('click').click(function(){
            var _this = $(this);
            var isCheck = _this.prop('checked');
            $(".t0 input[type = 'checkbox']").each(function(){
                $(this).prop("checked",isCheck);
            });
        });

        //网格数据开关发送
        $('#showAllData_win_open_switchd').unbind('click').click(function(){
            showAllDataSendInfo(0);
        });

        $('#showAllData_win_open_dimming').unbind('click').click(function(){
            showAllDataSendInfo(1);
        });

    }

    //网格数据发送信息
    function showAllDataSendInfo(num){
        var checkboxList = $(".t0 input[type = 'checkbox']");
        if(!_checkHasCheck(checkboxList)){
            proUntil.alert_win("请先选择设备");
            return;
        }
        var onOff = $('.showAllData_win').find('.command_box').eq(0).find(':input:checked').val();
        var arr = [];
        $.each(checkboxList,function(index,val){
            val = $(val);
            if(val.prop("checked")){
                val = $(this).val();
                var obj = {'nbId':val};
                arr.push(obj);
            }
        });
        arr = JSON.stringify(arr);
        var dimming = $('.win_processBar').find('.dimming_light').val();
        var oper = num == LIGHT_SWITCH_OPERATION ? LIGHT_SWITCH_OPERATION : LIGHT_DIMMING_OPERATION;
        proUntil.request("batchSwitchOperLamp",{ids:arr, onOff:onOff,dimming:dimming,oper:num},function (data){
            data = JSON.parse(data)[0];
            var status = data.status;
            if("empty" == status){
                proUntil.alert_win('未找到相关数据请核实灯具状态和操作指令');
            } else if("success" == status){
                proUntil.alert_win('操作指令发送成功');
            }
        });
    }

    //判断子模块是否被选中
    function _checkHasCheck(obj){
        for (var i = 0; i < obj.length; i++) {
            var isCheck =obj[i].checked;
            if(isCheck){
                return true;
            }
        }
        return false;
    }

    function init_win_title(){
        var box = $('.data_deatil');
        var h = box.find('table').height() - box.height();
        if(h <= 0){
            return;
        }
        $('.dataBox').unbind('scroll').scroll(function(){
            var scroll_h = $('.dataBox').scrollTop();
            if(scroll_h < h){
                $('.dataBox').find('ul').css('top', scroll_h);
            }
        });
    }

    function strIsEmpty(str){
        if(null == str || '' == str || undefined == str){
            return true;
        }
        return false;
    }

    /**
     * 切换开关时 重新部署地图
     */
    function repeatLampData(){
        //灯具位置详细信息
        getLampDataLocation();
        init_concentratorData();
    }

    /**
     * 页面初始化 获取灯具位置信息
     */
    function getLampDataLocation(){
        proUntil.request('getLampDataLocation',{},function(data){
            if('n' == data){
                return;
            }
            data = JSON.parse(data);
            $.each(data,function(index,value){
                var conn_state = value.conn_state;
                var onOff = value.on_off;
                var typeId = value.lampTypeId || 1;
                typeId = parseInt(typeId);
                var nb_type = 1;
                var pic = "";
                if(LAMP_OPEN_STATUS == onOff && NB_LAMP_CONNECT_STATE_ONlINE == conn_state){//红色 开灯
                    pic = LAMP_TYPE_SUN_LAMP == typeId ? "images/openlight_sun_ico.png" : "images/openlight_led_ico.png";
                    nb_type = DEVICE_TYPE_LAMP;
                }else if( LAMP_CLOSE_STATUS == onOff && NB_LAMP_CONNECT_STATE_ONlINE == conn_state){//蓝色 关灯
                    pic = LAMP_TYPE_SUN_LAMP == typeId ? "images/closelight_sun_ico.png" : "images/closelight_led_ico.png";
                    nb_type = DEVICE_TYPE_LAMP;
                } else if(NB_LAMP_CONNECT_STATE_EXCEPTION == conn_state || NB_LAMP_CONNECT_STATE_OFFlINE == conn_state){// 黄色 异常
                    pic = LAMP_TYPE_SUN_LAMP == typeId ? "images/exception_sun.png" : "images/exception_led.png";
                    nb_type = DEVICE_TYPE_LAMP;
                } else if(NB_ELE_BOX_CONNECT_STATE_ONlINE == conn_state){ //配电箱 在线
                    pic = "images/living.png";
                    nb_type = DEVICE_TYPE_CONCENTRATOR;
                } else if(NB_ELE_BOX_CONNECT_STATE_OFFlINE == conn_state){ //配电箱 离线
                    pic = "images/leave_ico.png";
                    nb_type = DEVICE_TYPE_CONCENTRATOR;
                }

                //创建图标
                var point = new BMap.Point(value.lo,value.la);
                var myIcon = new BMap.Icon(pic, new BMap.Size(26,31));
                var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
                map.addOverlay(marker);// 将标注添加到地图中
                marker.addEventListener("click",function(){
                    proUntil.request("getLampDetailList",{id:value.id},function(data){
                        var w = 0;
                        var h = 0;
                        var text = "";
                        data = JSON.parse(data)[0];
                        conn_state = _judgeLampStateByNum(conn_state);
                        if(DEVICE_TYPE_LAMP == nb_type){//灯具
                            var arr = writeLampTextInfoById(data,conn_state,typeId);
                            text = arr[0];//文本信息
                            h = arr[1];//文本高度
                        }else if(DEVICE_TYPE_CONCENTRATOR == nb_type){//配电箱
                            text = "<p class = 'detailBox'>所属区域：" + obj.areaName + "</p>" +
                                "<p class = 'detailBox'>配电箱编号：" + obj.name + "</p>" +
                                "<p class = 'detailBox'>状态："+conn_state+"</p>" +
                                "<p class = 'detailBox'>上传时间："+obj.createTime+"</p>";
                        }
                        var opts = {
                            width : w,     // 信息窗口宽度
                            height: h,     // 信息窗口高度
                            position : point,    // 指定文本标注所在的地理位置
                            enableMessage:false//设置允许信息窗发送短息
                        };
                        var infoWindow = new BMap.InfoWindow(text, opts);  // 创建信息窗口对象
                        map.openInfoWindow(infoWindow,point); //开启信息窗口
                    });
                });
                conn_state = value.conn_state;
                marker.addEventListener("rightclick",function(){
                    if(NB_LAMP_CONNECT_STATE_OFFlINE == conn_state){//离线
                        proUntil.alert_win('该设备目前状态为离线,请勿操作');
                        return;
                    }
                    if(NB_LAMP_CONNECT_STATE_EXCEPTION == conn_state){//异常
                        proUntil.alert_win('该设备目前状态为异常,请勿操作');
                        return;
                    }
                    proUntil.getSelData("getGroupManageByControllerId",$('.cl_group_detail').eq(2).find('select'),{id:value.id},'<option value = "">请选择组号</option>');
                    proUntil.request("getDeviceStatusForWin",{id:value.id},function(data){
                        data = JSON.parse(data)[0];
                        lamp_obj = data;
                        proUntil.setSlider_width( $('.lamp_controller_box').find('.cl_group_detail').eq(0),data.dimming);
                        //灯控窗口第一页显示
                        $('.lamp_controller_box').find('.cl_group_detail').eq(0).css('display', 'block');
                        $('.lamp_controller_box').show(manage_table_time);
                    });
                    //init_lamp_win(2);
                });

            });
        });
    }

    /**
     * 根据灯具类型判断显示的文本格式
     */
    function writeLampTextInfoById(obj,conn_state,typeId){
        var arr = [];
        //市电灯具
        if(LAMP_TYPE_COMMON_LAMP == typeId){
            arr[0] = "<div class = 'lamp_info_box'>" +
                "<p class = 'detailBox'>所属区域："+obj.areaName+"</p>" +
                "<p class = 'detailBox'>配电箱编号："+obj.elecbox_name+"</p>" +
                "<p class = 'detailBox'>配电箱回路："+obj.dbcircuit+"</p>" +
                "<p class = 'detailBox'>所属道路："+obj.road_name+"</p>" +
                "<p class = 'detailBox'>所属线路："+obj.roadline_name+"</p>" +
                "<p class = 'detailBox'>灯杆编号：" + obj.poleCode + "</p>" +
                "<p class = 'detailBox'>灯具编号：" + obj.lampnum + "</p>" +
                "<p class = 'detailBox'>状态：" + conn_state + "</p>" +
                "<p class = 'detailBox'>调光值：" + obj.dimming + "%</p>" +
                "<p class = 'detailBox'>电压：" + obj.vol + "V</p>" +
                "<p class = 'detailBox'>电流：" + obj.ele + "A</p>" +
                "<p class = 'detailBox'>功率：" + obj.power +"W</p>" +
                "<p class = 'detailBox'>上传时间：" + obj.record_time + "</p>" +
                "<span isget = 'n' onclick = 'getStingleLampData("+obj.id+",this,"+LAMP_TYPE_COMMON_LAMP+")'>更新数据</span>"+
                "</div>";
            arr[1] = 300;
        }
        //太阳能灯
        else if(LAMP_TYPE_SUN_LAMP == typeId){
            arr[0] = "<div class = 'lamp_info_box'>" +
                "<p class = 'detailBox'>所属区域："+obj.areaName+"</p>" +
                "<p class = 'detailBox'>配电箱编号："+obj.elecbox_name+"</p>" +
                "<p class = 'detailBox'>配电箱回路："+obj.dbcircuit+"</p>" +
                "<p class = 'detailBox'>所属道路："+obj.road_name+"</p>" +
                "<p class = 'detailBox'>所属线路："+obj.roadline_name+"</p>" +
                "<p class = 'detailBox'>灯杆编号：" + obj.poleCode + "</p>" +
                "<p class = 'detailBox'>灯具编号：" + obj.lampnum + "</p>" +
                "<p class = 'detailBox'>状态：" + conn_state + "</p>" +
                "<p class = 'detailBox'>温度：" + obj.temp + "</p>" +
                "<p class = 'detailBox'>光伏电流：" + obj.pele + "A</p>" +
                "<p class = 'detailBox'>光伏电压：" + obj.pvol + "V</p>" +
                "<p class = 'detailBox'>蓄电池电压：" + obj.bvol + "V</p>" +
                "<p class = 'detailBox'>LED电压：" + obj.vol + "V</p>" +
                "<p class = 'detailBox'>LED电流：" + obj.ele + "A</p>" +
                "<p class = 'detailBox'>上传时间：" + obj.record_time + "</p>" +
                "<span isget = 'n' onclick = 'getStingleLampData("+obj.id+",this,"+LAMP_TYPE_SUN_LAMP+")'>更新数据</span>"+
                "</div>";
            arr[1] = 350;
        }
        return arr;
    }

    /**
     *  根据状态数字显示不同的文字
     * @returns {string}
     * @private
     */
    function _judgeLampStateByNum(conn_state){
        if(1 ==conn_state){
            conn_state = '在线';
        }else if(2 ==conn_state){
            conn_state = '离线';
        } else if(3 == conn_state){
            conn_state = '异常';
        } else if(4 == conn_state){
            conn_state = '配电箱在线';
        } else if(5 == conn_state){
            conn_state = '配电箱离线';
        }
        return conn_state;
    }

    /**
     * 初始化集中器数据
     */
    function init_concentratorData(){
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getDistributionListForMap",
            success: function (data) {
                $.each(data,function(index,value){
                    var pic = "images/living.png";
                    //创建图标
                    var point = new BMap.Point(value.lo,value.la);
                    var myIcon = new BMap.Icon(pic, new BMap.Size(26,31));
                    var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
                    map.addOverlay(marker);// 将标注添加到地图中
                    marker.addEventListener("click",function(e){
                        var w = 0;
                        var h = 0;
                        var conn_state = _judgeLampStateByNum(4);
                        var text = "<p class = 'detailBox'>所属区域：" + value.areaName + "</p>" +
                            "<p class = 'detailBox'>配电箱编号：" + value.name + "</p>" +
                            "<p class = 'detailBox'>状态："+conn_state+"</p>" +
                            "<p class = 'detailBox'>上传时间："+value.createTime+"</p>";
                        var opts = {
                            width : w,     // 信息窗口宽度
                            height: h,     // 信息窗口高度
                            position : point,    // 指定文本标注所在的地理位置
                            enableMessage:false//设置允许信息窗发送短息
                        };
                        var infoWindow = new BMap.InfoWindow(text, opts);  // 创建信息窗口对象
                        map.openInfoWindow(infoWindow,point); //开启信息窗口
                        /*marker.addEventListener("rightclick",function(){
                            $('.concentrator_box').find('.cl_group_detail').css('display','none');
                            $('.concentrator_box').find('.cl_group_detail').eq(0).css('display', 'block');
                            $('.concentrator_box').show(manage_table_time);
                        });*/
                    });
                });
            }
        });
    }

    //单灯控制
    function single_light_switch(num){
        var onOff = $('.cl_group_detail').eq(0).find(':input:checked').val();
        var dimming =  $('#single_light_dimming').val();

        var oper = num == LIGHT_SWITCH_OPERATION ? LIGHT_SWITCH_OPERATION : LIGHT_DIMMING_OPERATION;
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../singleDeviceController",
            data:{"id":lamp_obj.id,"onOff":onOff,"dimming":dimming,"oper":oper},
            success: function (data) {
                deallightSwitchResultData(data);
            }
        });
    }

    //道路控制
    function road_light_switch(num){
        var onOff = $('.cl_group_detail').eq(1).find(':input:checked').val();
        var dimming =  $('#road_light_dimming').val();
        var oper = num == LIGHT_SWITCH_OPERATION ? LIGHT_SWITCH_OPERATION : LIGHT_DIMMING_OPERATION;
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../RoadSwitchController",
            data:{"roadId":lamp_obj.road_id,"on_off":onOff,"dimming":dimming,"oper":oper},
            success: function (data) {
                deallightSwitchResultData(data);
            }
        });
    }

    //分组控制
    function group_light_switch(num){
        var groupId = $('.cl_group_detail').eq(2).find('select').val();
        if( strIsEmpty(groupId)){
            proUntil.alert_win('请选择分组，或在分组管理模块添加数据');
            return;
        }
        var onOff = $('.cl_group_detail').eq(2).find(':input:checked').val();
        var dimming =  $('#group_light_dimming').val();
        var oper = num == LIGHT_SWITCH_OPERATION ? LIGHT_SWITCH_OPERATION : LIGHT_DIMMING_OPERATION;
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../groupSwitchController",
            data:{"groupId":groupId,"on_off":onOff,"dimming":dimming,"oper":oper,"groupId":groupId},
            success: function (data) {
                deallightSwitchResultData(data);
            }
        });
    }

    //回路控制
    function loop_light_switch(){
        var onOff = $('.cl_group_detail').eq(3).find(':input:checked').val();
        var loop = $('.cl_group_detail').eq(3).find('select').val();
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../loopSwitchController",
            data:{"concenId":lamp_obj.concenId,"loop":loop,"onOff":onOff,"dimming":0,"oper":LIGHT_SWITCH_OPERATION},
            success: function (data) {
                deallightSwitchResultData(data);
            }
        });
    }


    //集中器开关灯、调光控制
    function concentrator_light_switch(num){
        var onOff = $('.concentrator_box').find('.cl_group_detail').eq(0).find(':input:checked').val();
        var dimming = $('#concentrator_light_dimming').val();
        var oper = num == LIGHT_SWITCH_OPERATION ? LIGHT_SWITCH_OPERATION : LIGHT_DIMMING_OPERATION;
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../loopSwitchController",
            data:{"concenId":lamp_obj.concenId,"onOff":onOff,"dimming":dimming,"oper":oper},
            success: function (data) {
                deallightSwitchResultData(data);
            }
        });
    }

    //集中器回路开关灯
    function concentrator_loop_switch(){
        var onOff = $('.concentrator_box').find('.cl_group_detail').eq(1).find(':input:checked').val();
        var loop = $('.concentrator_box').find('.cl_group_detail').eq(1).find('select').val();
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../loopSwitchController",
            data:{"concenId":lamp_obj.concenId,"onOff":onOff,"loop":loop,"dimming":0,"oper":LIGHT_SWITCH_OPERATION},
            success: function (data){
                deallightSwitchResultData(data);
            }
        });
    }

    /**
     * 显示等待按钮
     * [show_watting description]
     * @return {[type]} [description]
     */
    function show_watting(){
        $('.watting_bg').show(300);
        $('.watting_box').show(300);
    }

    /**
     * 隐藏等待按钮
     * [hide_watting description]
     * @return {[type]} [description]
     */
    function hide_watting(){
        $('.watting_bg').hide(300);
        $('.watting_box').hide(300);
    }

    /**
     * 处理所有开关返回数据
     * @param data
     */
    function deallightSwitchResultData(data){
        data = JSON.parse(data)[0];
        var status = data.status;
        if("empty" == status){
            proUntil.alert_win('未找到相关数据请核实灯具状态和操作指令');
        } else if("success" == status){
            proUntil.alert_win('操作指令发送成功');
        }
    }

    /**
     *  显示数据网格信息
     */
    function show_all_lamp_data(){
        var len = $('.showAllData_win').length;
        if(0 == len) return;
        cur_page = 1;
        getAllGroupData();
        getAll_lamp_data2();
    }
    /**
     * 获取数据网格中所有分组的信息
     */
    function getAllGroupData(){
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../getAllplanGroupData",
            async: false,
            success: function (data) {
                data = JSON.parse(data);
                var group_selet = $('.showAllData_win').find("select[name='groupId']");
                var html = "<option value = ''>请选择分组</option>";
                $.each(data,function(index,val){
                    html+="<option value = '"+val.id+"'>"+val.group_name+"</option>";
                });
                group_selet.html(html);
            }
        });
    }

    /**
     * 根据道路ID获取相应的数据信息
     */
    function getLampDataByRoadId(obj){
        var road_selet = $('.showAllData_win').find("select[name='roadId']").val();
        if(strIsEmpty(road_selet)){
            proUntil.alert_win('请先选择道路信息');
            return;
        }
        var _this = $(obj);
        var isget = _this.attr('isget');
        if('n' != isget){
            return;
        }
        _this.attr('isget','y');
        _this.val('正在获取数据');
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../getManyLampLotData",
            data:{"roadId":road_selet},
            success: function (msg){
                if(msg == 'ok'){
                    cur_page = 1;
                    getAll_lamp_data();
                    _this.val('数据已经更新');
                    setTimeout(function(){
                        _this.val('更新数据');
                        _this.attr('isget','n');
                    },3000);
                }
            }
        });
    }

    /**
     * 获取单个灯具的数据
     * @param cid 控制器ID
     * @param obj 窗口对象
     */
    function getStingleLampData(cid,obj,lampType){

       var _this = $(obj);
       var flag =  _this.attr('isget');
        var win = map.getInfoWindow();
       if('n' != flag){
           return;
       }

        _this.attr('isget','y');
        _this.html('正在获取中');

        proUntil.request("getSingleLampLotData",{'id':cid},function(data){
            if('n' == data){
                _this.html('数据已更新');
                setTimeout(function(){
                    _this.html('更新数据');
                    _this.attr('isget','n');
                },3000);
                return;
            }
            data = JSON.parse(data)[0];
            var parents =  _this.parents('.lamp_info_box');
            var p = parents.find('p');
            if(LAMP_TYPE_COMMON_LAMP == lampType){
                p.eq(8).html("调光值："+ data.dimming+"%");//调光值
                p.eq(9).html("电压："+ data.record_vol + "V");//电压
                p.eq(10).html("电流："+ data.record_ele + "A");//电流
                p.eq(11).html("功率："+ data.record_power + "W");//功率
                p.eq(12).html("上传时间："+ data.record_time);//上传时间
            }else if(LAMP_TYPE_SUN_LAMP == lampType){
                p.eq(8).html("温度："+ data.record_temp+"");
                p.eq(9).html("光伏电流："+data.record_pele + "A");
                p.eq(10).html("光伏电压：" + data.record_pvol + "V");
                p.eq(11).html("蓄电池电压：" + data.record_bvol + "V");
                p.eq(12).html("LED电压：" + data.record_vol + "V");
                p.eq(13).html("LED电流：" + data.record_ele + "A");
                p.eq(14).html("上传时间："+ data.record_time);//上传时间
            }
            _this.html('数据已更新');
            setTimeout(function(){
                _this.html('更新数据');
                _this.attr('isget','n');
                var html = "<div class = 'lamp_info_box'>"+parents.html()+"</div>";
                win.setContent(html);
            },3000);
        });
    }

    /**
     * 工作模式
     */
    function concentratorAutoModel(){
        proUntil.alert_win('集中器不在线，请勿操作');
    }

    //新增一些方法
    function getDade1(){

        var searchBox = $('.sw_searchbox');
        var areaSelect = searchBox.find('select[name ="selectAreaName"]');
        var roadSelect = searchBox.find('select[name ="selectRoadName"]');
        var roadLineSelect = searchBox.find('select[name ="selectRoadLine"]');
        var lampSelect = searchBox.find('select[name ="selectLampNum"]');
        //获取区域信息
        proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
        areaSelect.change(function(){
            var areaId = areaSelect.val();
            if(proUntil.strIsEmpty(areaId)){
                roadSelect.html('<option value = "">请选择道路</option>');
                roadLineSelect.html('<option value = "">请选择线路</option>');
                lampSelect.html('<option value = "">请选择单灯</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
            roadLineSelect.html('<option value = "">请选择线路</option>');
            lampSelect.html('<option value = "">请选择单灯</option>');
        });

        //道路选择显示线路
        roadSelect.change(function(){
            var roadId = roadSelect.val();
            if(proUntil.strIsEmpty(roadId)){
                roadLineSelect.html('<option value = "">请选择线路</option>');
                lampSelect.html('<option value = "">请选择单灯</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadLineNameForSelect",roadLineSelect,{roadId:roadId},'<option value = "">请选择线路</option>');
            lampSelect.html('<option value = "">请选择单灯</option>');
        });

        //线路选择显示单灯编号
        roadLineSelect.change(function(){
            var lineId = roadLineSelect.val();
            if(proUntil.strIsEmpty(lineId)){
                lampSelect.html('<option value = "">请选择单灯</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getLampNumForSelect",lampSelect,{lineId:lineId},'<option value = "">请选择单灯</option>');
        });

    }


    /**
     * 获取灯具细节
     */
    function getAll_lamp_data2(){

        var group_selet= $('.showAllData_win').find("select[name='groupId']").val();
        var area_selet = $('.showAllData_win').find("select[name='areaId']").val();
        var roadx_selet =$('.showAllData_win').find("select[name='roadId']").val();
        var road_selet = $('.showAllData_win').find("select[name='lineId']").val();
        var url="";
        var data="";
        if(group_selet==''){
            url="../getAllLampDetailData2";
            data={'curpage':cur_page,'showNum':page_show_Num,"roadId":road_selet,"areaId":area_selet,"roadxId":road_selet};
        }else{
            url="../getAllLampDetailData3";
            data={'curpage':cur_page,'showNum':page_show_Num,"groupId":group_selet};
        }
        $.ajax({
            type:"post",
            dataType:"text",
            url:url,
            data:data,
            async: false,
            success: function (data){
                data = JSON.parse(data)[0];
                var table = $('.showAllData_win').find('table');
                if(data.datas.length == 0){
                    $("#controllerPage").html('');
                    var len = $('.showAllData_win').find('.dataBox').find('li').length;
                    var tr = "<tr><td  colspan='"+len+"'>未找到相关信息</td></tr>";
                    table.html(tr);
                    return;
                }
                count_page = parseInt(data.count);

                var html = "";
                $.each(data.datas,function(index,val){
                    html += '<tr>\n' +
                        '<td class = "t0"><input type="checkbox" value="'+val.conId+'"></td>\n' +
                        '<td class = "t1">'+val.lampnum+'</td>\n' +
                        '<td class = "t1">'+val.cnum+'</td>\n' +
                        '<td class = "t1">'+val.roadname+'</td>\n' +
                        '<td class = "t1">'+val.ename+'</td>\n' +
                        '<td class = "t1">'+(val.areaname || '-')+'</td>\n' +
                        '<td class = "t4">'+(val.ele || '0')+'</td>\n' +
                        '<td class = "t4">'+(val.vol || '0')+'</td>\n' +
                        '<td class = "t4">'+(val.power || '0')+'</td>\n' +
                        '<td class = "t4">'+(val.temp || '0')+'</td>\n' +
                        '<td class = "t4">'+(val.state || '-')+'</td>\n' +
                        '<td class = "t4">'+(val.onff || '-')+'</td>\n' +
                        '<td class = "t4">'+(val.dimming || '0')+'</td>\n' +
                        '<td class = "t4">'+1200+'</td>' +
                        '<td class = "t4">'+6570.32+'</td>' +
                        '<td class = "t5">'+(val.recordtime || '-')+'</td>\n' +
                        '</tr>';
                    table.html(html);
                    var allNum = Math.ceil(count_page/page_show_Num)
                    $("#controllerPage").html('');
                    page({
                        id:'controllerPage',
                        nowNum: cur_page,
                        allNum: allNum,
                    });
                    //网络数据分页点击
                    $("#controllerPage").find("a").unbind('click').click(function(){
                        var _this = $(this);
                        var curpage = _this.attr('cur');
                        cur_page = parseInt(curpage);
                        getAll_lamp_data2();
                    });
                });
                init_win_title();
            }
        });
    }
    //控件管理
    function batch_light_switch(oper){
        var msg = $('.sw_searchbox_p').find(':input:checked').val();
        var idArr = $("#controlArr").val();
        var dam = $("#batch_switch_processbar").val();
        if(idArr != '' && idArr != null){
            switch_on_light(idArr,Number(msg),oper,dam);
        }else{
            proUntil.alert("未选择灯具");
        }
    }

    //开灯
    function switch_on_light(idArr,info,operMsg,damMsg){
        var sendUrl = "../batchSwitchOnLight.do";
        var sendData = {ids:idArr,onOff:info,oper:operMsg,damInfo:damMsg};
        $.ajax({
            url:sendUrl,
            data:sendData,
            type:"post",
            dataType:"json",
            success:function(msg){
                if(msg == null){
                    proUntil.alert_win('未找到相关数据请核实灯具状态和操作指令');
                } else{
                    proUntil.alert_win('操作指令发送成功');
                }
            }

        });
    }

    function init_roadList(){
        var html = _common_roadList("lampCommon/getAreaNameForSelect",{},"area");
        if(!proUntil.strIsEmpty(html)){
            $('.roadList_deatail').html(html);
            $('.roadList_deatail').find('ul').show();
            roadList_click( $('.roadList_deatail'));
        }
    }

    function _common_roadList(url,param,data_type){
        var html = "";
        proUntil.request(url,param,function(data){
            if("n" == data){
                return html;
            }
            data = JSON.parse(data);
            html = "<ul>";
            $.each(data,function(index,val){
                html +='<li>' +
                    '<p data_id = '+val.id+' data_type="'+data_type+'"><b>+</b>'+val.cname+'</p>' +
                    '</li>';
            });
            html +='</ul>';

        },"false");
        return html;
    }

    function roadList_click(JQuery_obj){
        JQuery_obj.find('p').unbind('click').click(function(){
            var _this = $(this);
            var id = _this.attr('data_id');
            var type = _this.attr('data_type');
            var b = _this.find('b');
            var mark = b.html();
            var url = null;
            var son_type = null;
            if ("area" == type){
                url = "lampCommon/getRoadNameForSelect";
                son_type = "road";
            } else if ("road" == type){
                url = "lampCommon/getRoadLineNameForSelect";
                son_type = "line";
            }else {
                return;
            }
            if('+' == mark){
                var html = _common_roadList(url,{areaId:id,roadId:id,lineId:id},son_type);
                if(!proUntil.strIsEmpty(html)){
                    _this.after(html);
                    _this.nextAll('ul').show(manage_table_time);
                    roadList_click(_this.parent('li'));
                }
            }else{
                _this.nextAll('ul').hide(manage_table_time).remove();
            }
            b.html('+' == mark ? '-' : "+");
        });

        JQuery_obj.find('p').unbind('dbclick').dblclick(function(){
            var _this = $(this);
            var id = _this.attr('data_id');
            var type = _this.attr('data_type');
            proUntil.request("getLongitudeAndlatitude",{dataType:type,dataId:id},function(data){
                if('n' == data){
                    proUntil.alert_win("该区域下无任何设备");
                    return;
                }
                data = JSON.parse(data)[0];
                if("area" == type){
                    map.centerAndZoom(new BMap.Point(data.lo,data.la),13);
                }else if ("road" == type){
                    map.centerAndZoom(new BMap.Point(data.lo,data.la),15);
                } else if("line" == type){
                    map.centerAndZoom(new BMap.Point(data.lo,data.la),18);
                }
            });
        });
    }