    var manage_table_time = 300;
    var screen_show_time = 400;
    var table_delete_object = null;//点击删除一行时 保存行对象
    var alert_common_time = 4000;//通用弹出框时间
    var alert_win = null;
    var proUntil = {};
    proUntil.OPERTION_SUCCESS = '操作成功';
    proUntil.OPERTION_FAIL = '操作失败，请刷新页面后再做尝试';
    proUntil.TABLE_PAGE_NUM = 10;
    proUntil.TABLE_CUR_PAGE = 1;
    proUntil.ROAD_ID = null;
    proUntil.ROAD_LINE_ID = null;
    proUntil.TABLE_SELECT_ALL = null;
    $(function(){
        //关闭详细信息窗口  
        $('#close_table_win').unbind('click').click(function(){
            $('.table_win').hide(manage_table_time);
        });

        //新建窗口  
        $('.add_btn').unbind('click').click(function() {
            proUntil.clear_form_data($('.table_win'));
            $('.table_win').show(manage_table_time);
        });

        //打开修改窗口
        $('.edit_button').unbind('click').click(function(){
            $('.table_win').show(manage_table_time);
        });

        //关闭删除窗口
        $('.close_judge_win').unbind('click').click(function() {
            $('.judge_win').hide(manage_table_time);
        });

        //打开删除窗口
        $('.delete_button').unbind('click').click(function(){
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        proUntil.init_common_btn();
        updatePageTitle();
        initCheckBox();
        judgeBtnisShow();
    });

    /**
     * 全选
     */
    function initCheckBox(){
        $(".table_box th input[type = 'checkbox']").click(function(){
            var that = this;
            var arr = [];
            if($(that).prop('checked')){
                $(".table_box td input[type = 'checkbox']").each(function(){
                    $(this).prop("checked",true);
                    if($(this).prop('checked')){
                        var msg =$(this).val();
                        arr.push(msg);
                    }
                });
            }else{
                $(".table_box td input[type = 'checkbox']").each(function(){
                    $(this).prop("checked",false);
                });
            }
        });
    }

    //查找是否有信息
    function check_table_data(){
        var table = $('.table_box').find('table');
        var tr_len = table.find('tr').length;
        var th_len = table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(1 == tr_len){
            table.append(tr);
        }
    }

    /**
     * 初始化百度地图
     * @param  控件 ID 名称
     * @return 百度地图
     */
    function initMap(map_id){
        var map = 2;
        proUntil.request("../getCityForMap",{},function(data){
            data = JSON.parse(data)[0];
            // 百度地图API功能
            map = new BMap.Map(map_id); // 创建Map实例
            map.centerAndZoom(new BMap.Point(data.longitude,data.latitude),16);  // 初始化地图,设置中心点坐标和地图级别
            map.setCurrentCity(data.city_name);          // 设置地图显示的城市 此项是必须设置的
            map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            map.setMapStyle({style:'midnight'});
        },"false");
        return map;
    }

    //下面用于多图片上传预览功能
    function setImagePreviews(obj) {
        var docObj = obj
        var dd = document.getElementById("win_addBox");
        dd.innerHTML = "";
        var fileList = docObj.files;
        for (var i = 0; i < fileList.length; i++) {            
            dd.innerHTML += "<img id='img" + i + "'  />";
            var imgObjPreview = document.getElementById("img"+i); 
            if (docObj.files && docObj.files[i]) {
                //火狐下，直接设img属性
                /*imgObjPreview.style.display = 'block';*/
                imgObjPreview.style.width = '120px';
                imgObjPreview.style.height = '120px';
                //imgObjPreview.src = docObj.files[0].getAsDataURL();
                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
            }
            else {
                //IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                var localImagId = document.getElementById("img" + i);
                //必须设置初始大小
                localImagId.style.width = "120px";
                localImagId.style.height = "120px";
                //图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }
                catch (e) {
                    alert("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
        }  
        return true;
    }

    function checkPicFile(obj)
    {
         var n1 = obj.value;
         if(null == n1 || n1.length < 1) {     
            return false;
         }
         var n2 = obj.files.length;
         var pos = n1.lastIndexOf(".");
         var lastName = n1.substring(pos,n1.length);
         
         if(".gif" == lastName || ".jpg" == lastName || ".png" ==lastName){
            setImagePreviews(obj);
            return true;
         }
         else{
              alert('请选择正确的图片格式类型');

              return false;
        }
    }

    function getuuid() {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";
     
        var uuid = s.join("");
        return uuid;
    }

    function onoff() {
        var radio = document.getElementsByName("light_switch");
        var nbDevice = "bd90e05a-9993-4f73-a490-2eb5da402dd7";
        for (i=0; i<radio.length; i++) {
            if (radio[i].checked) {
                var statue=radio[i].value;
            }
        }
        if(statue==0){
            dimming = 100;
        }else{
            dimming = 0;
        }
        $.ajax({
            type:"post",
            dataType:"text",
            url:"/onoffControl",
            data:{"dimming":dimming,"nbDevice":nbDevice},
            success: function (msg) {
                console.log("msg:"+msg);
                if(msg==1){
                    alert("控制成功");
                }else if(msg==0){
                    alert("检查设备是否存在");
                }
            }

        });
    }

    proUntil.strIsEmpty = function(str){
        return null == str || '' == str || undefined == str || str.length == 0;
    };

    proUntil.request = function (url,data,callBackFunction,async_flag,dataType,type,FailFunction){
        async_flag =  proUntil.strIsEmpty(async_flag) ? true : false;
        type = type || "post";
        dataType = dataType || "text";
        data = data || {};
        $.ajax({
            type: type,
            async: async_flag,
            data: data,
            url: url,
            dataType: dataType,
            success: function(data){
                if (callBackFunction && typeof(callBackFunction) === "function"){
                    callBackFunction(data);
                }
            },error:function(xhr){
                if (FailFunction && typeof(FailFunction) === "function"){
                    FailFunction(xhr.responseText);
                }
            }
        });
    };

    proUntil.getSelData = function (url,sel,param,default_text,callBackFunction){
        proUntil.request(url,param,function(data){
            var html = default_text || '';
            if('n' == data){
                sel.html(html);
                return;
            }
            data = JSON.parse(data);
                $.each(data,function(i,val){
                    html += '<option value = "'+val.id+'">'+val.cname+'</option>';
                });
            sel.html(html);
            if (callBackFunction && typeof(callBackFunction) === "function"){
                callBackFunction();
            }
        });
    }

    proUntil.saveData = function (url,win_obj,callBackFunction){
        var isOK = proUntil.check_form(win_obj);
        if(!isOK){
            return;
        }
        var param = win_obj.serialize();
        proUntil.request(url,param,function(data){
            data = $.trim(data);
            if('y' == data){
                win_obj.hide(manage_table_time);
                proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                callBackFunction();
            } else {
                proUntil.alert_win(proUntil.OPERTION_FAIL);
            }
        });
    };

    proUntil.init_common_btn = function(){

        $('.delete_button').unbind('click').click(function(){
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });

        $('.sure_delete_judge_win').unbind('click').click(function(){
            var _this = $(this);
            proUntil.delData(_this.attr('url'),{"id":_this.attr('id')},$('.judge_win'),function(){
                selectAll();
            });
        });
    };


    proUntil.check_form = function(win){
        var result_data = true;
        result_data = proUntil.checkElements(win.find('select'),'请选择：');

        if(!result_data){
            return false;
        }
        result_data = proUntil.checkElements(win.find('input[type="text"]'),'请填写：');

        if(!result_data){
            return false;
        }

        result_data = proUntil.checkElements(win.find('textarea'),'请填写：');
        return result_data;
    }

    proUntil.checkElements = function(JQuery_obj,prefix_text){
        var result_data = true;
        $.each(JQuery_obj,function(index,el){
            var _this = $(el);
            var allow_null = false;
            if(!proUntil.strIsEmpty(_this.attr("allow_null")) || _this.prop("disabled") == true){
                allow_null = true;
            }
            if(allow_null){
                return true;
            }
            var isOk = proUntil.strIsEmpty(_this.val());
            if(isOk){
                proUntil.alert_win(prefix_text + _this.prev('span').html());
                result_data = false;
                return false;
            }
        });
        return result_data;
    }

    proUntil.alert_win = function(text){
        $('.alert-container').css('display','none');
        alert_win = jqueryAlert({
            'content' : text,
            'closeTime' : alert_common_time
        });
    };

    proUntil.setSlider_width = function(obj,num){
        var location = "0px";
        if(num == 100){
            location = "-24px";
        } else if(num >=10 || num <=99){
            location = "-20.5px";
        } else {
            location = "-17px";
        }
        obj.find('.tooltip-main').css("margin-left",location);
    };

    proUntil.clear_form_data = function(JQuery_obj){
        JQuery_obj.find('input[type="text"]').val('');
        JQuery_obj.find('input[name="id"]').val('');
        JQuery_obj.find('textarea').val('');
        JQuery_obj.find('select').val('');
        JQuery_obj.show(manage_table_time);
    };

    proUntil.commonSelect = function(url,param,callBackFunction,dataBackFunction,JQuery_table,Array_button,th_sort,orderBy,sort){
        th_sort = th_sort || "y";
        JQuery_table = JQuery_table || $('.table_box').find('table');
        var firstTr = '<tr>' + JQuery_table.find('tr').eq(0).html() + '</tr>';
        var html = "";
        var th_list = $(firstTr).find('th[orderBy]');
        var columns_arr = [];
        $.each(th_list,function(index,val){
            columns_arr[index] = $(val).attr("orderby");
        });
        orderBy = orderBy || columns_arr[columns_arr.length - 1];
        sort = proUntil.strIsEmpty(sort) ? "desc" : sort;
        param = param || {};
        param.showNum = proUntil.TABLE_PAGE_NUM;
        param.curPage = proUntil.TABLE_CUR_PAGE;
        param.orderBy = orderBy;
        param.sort = sort;
        proUntil.request(url,param,function(data){
            if('n' == data){
                JQuery_table.html(firstTr +'');
                proUntil.check_table_data(JQuery_table);
                return;
            }
            data = JSON.parse(data)[0];
            var countPage = Math.ceil(parseInt(data.count)/proUntil.TABLE_PAGE_NUM);
            $.each(data.data, function(index, val){
                html+= '<tr>';
                   $.each(columns_arr,function(index,elements){
                       if('id' == elements){
                           return true;
                       }
                       html +=  '<td>'+ (val[elements] || '-') +'</td>';
                   });

                if("no_btn" == Array_button){
                    html+= '</tr>';
                }else{
                    html+= '<td>';
                    if(proUntil.strIsEmpty(Array_button)){
                        html+=
                            '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>' +
                            '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>';

                    }else if(Array_button.length > 0){
                        $.each(Array_button,function(index,ele){
                            html += '<a title = "'+ele.title+'" data_id = "'+val.id+'" class = "'+ele.className+'" href="javascript:void(0);"></a>';
                        });
                    }
                    html+= '</td></tr>';
                }
                if (dataBackFunction && typeof(dataBackFunction) === "function"){
                    dataBackFunction(val);
                }
            });
            JQuery_table.html(firstTr + html);
            $("#controllerPage").html('');
            page({
                id:'controllerPage',
                nowNum: proUntil.TABLE_CUR_PAGE,
                allNum: countPage
            });
            $("#controllerPage").find("a").click(function(){
                var _this = $(this);
                var curNum = parseInt(_this.attr('cur'));
                proUntil.TABLE_CUR_PAGE = curNum;
                selectAll();
            });

            if (callBackFunction && typeof(callBackFunction) === "function"){
                callBackFunction(data);
            }

            if('y' == th_sort){
                JQuery_table.find('th[orderBy]').unbind("click").click(function(){
                    proUntil.TABLE_CUR_PAGE = 1;
                    var _this = $(this);
                    sort = _this.attr('sort');
                    JQuery_table.find('th').removeAttr('sort');
                    JQuery_table.find('th').find('img').remove();
                    if(proUntil.strIsEmpty(sort)){
                        sort = "desc";
                        _this.html(_this.html() + '<img src="images/th_ico_down.png">');
                    } else if ("asc" == sort){
                        sort = "desc";
                        _this.html(_this.html() + '<img src="images/th_ico_down.png">');
                    } else if("desc" == sort){
                        sort = "asc";
                        _this.html(_this.html() + '<img src="images/th_ico_up.png">');
                    }
                    _this.attr('sort',sort);
                    orderBy =  _this.attr("orderBy");
                    proUntil.commonSelect(url,param,callBackFunction,dataBackFunction,JQuery_table,Array_button,th_sort,orderBy,sort);
                });
            }
        },"false");
    };

    proUntil.check_table_data = function (JQuery_table){
        var tr_len = JQuery_table.find('tr').length;
        var th_len = JQuery_table.find('th').length;
        var tr = "<tr><td  colspan='"+th_len+"'>未找到相关信息</td></tr>";
        if(tr_len == 1){
            JQuery_table.append(tr);
        }
    };

    proUntil.searchLinkSelect = function(JQuery_area,JQuery_road,JQuery_roadLine,JQuery_lamp){
        var status = 2;
        if(undefined != JQuery_roadLine || null != JQuery_roadLine){
            status = 3;
        }
        if(undefined != JQuery_lamp || null != JQuery_lamp){
            status = 4;
        }
        proUntil.getSelData("lampCommon/getAreaNameForSelect",JQuery_area,{},'<option value = "">请选择区域</option>');
            JQuery_area.change(function(){
                var areaId = JQuery_area.val();
                if(proUntil.strIsEmpty(areaId)){
                    JQuery_road.html('<option value = "">请选择道路</option>');
                    if(status >= 3) JQuery_roadLine.html('<option value = "">请选择线路</option>');
                    if(status == 4) JQuery_lamp.html('<option value = "">请选择单灯</option>');
                    return;
                }
                proUntil.getSelData("lampCommon/getRoadNameForSelect",JQuery_road,{areaId:areaId},'<option value = "">请选择道路</option>');
                if(status >= 3) JQuery_roadLine.html('<option value = "">请选择线路</option>');
                if(status == 4) JQuery_lamp.html('<option value = "">请选择单灯</option>');
            });
            if(status >= 3){
                JQuery_road.change(function(){
                    var roadId = JQuery_road.val();
                    if(proUntil.strIsEmpty(roadId)){
                        JQuery_roadLine.html('<option value = "">请选择线路</option>');
                        if(status == 4) JQuery_lamp.html('<option value = "">请选择单灯</option>');
                        return;
                    }
                    proUntil.getSelData("lampCommon/getRoadLineNameForSelect",JQuery_roadLine,{roadId:roadId},'<option value = "">请选择线路</option>');
                });
            }

            if(status == 4){
                JQuery_roadLine.change(function(){
                    var lineId = JQuery_roadLine.val();
                    if(proUntil.strIsEmpty(lineId)){
                        JQuery_lamp.html('<option value = "">请选择单灯</option>');
                        return;
                    }
                    proUntil.getSelData("lampCommon/getLampNumForSelect",JQuery_lamp,{lineId:lineId},'<option value = "">请选择单灯</option>');
                });
            }
    };

    proUntil.winLinkSelect = function(JQuery_area,JQuery_road,JQuery_roadLine){
        var status = 2;
        if(undefined != JQuery_roadLine || null != JQuery_roadLine){
            status = 3;
        }
        proUntil.getSelData("lampCommon/getAreaNameForSelect",JQuery_area,{},'<option value = "">请选择区域</option>');
        JQuery_area.change(function(){
            var areaId = JQuery_area.val();
            if(proUntil.strIsEmpty(areaId)){
                JQuery_road.html('<option value = "">请选择道路</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadNameForSelect",JQuery_road,{areaId:areaId},'<option value = "">请选择道路</option>',function(){
                if(proUntil.ROAD_ID != null){
                    JQuery_road.find("option[value='"+proUntil.ROAD_ID+"']").prop("selected",true);
                    JQuery_road.change();
                    proUntil.ROAD_ID = null;
                }
            });
        });

        if(status < 3){
            return;
        }

        JQuery_road.change(function(){
            var roadId = JQuery_road.val();
            if(proUntil.strIsEmpty(roadId)){
                JQuery_roadLine.html('<option value = "">请选择线路</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadLineNameForSelect",JQuery_roadLine,{roadId:roadId},'<option value = "">请选择线路</option>',function(){
                if(proUntil.ROAD_LINE_ID != null){
                    JQuery_roadLine.find("option[value='"+proUntil.ROAD_LINE_ID+"']").prop("selected",true);
                    proUntil.ROAD_LINE_ID = null;
                }
            });
        });
    };

    proUntil.delData = function(url,param,win_obj,callBackFunction){
        proUntil.request(url,param,function(data){
            data = $.trim(data);
            if('y' == data){
                win_obj.hide(manage_table_time);
                proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                if (callBackFunction && typeof(callBackFunction) === "function"){
                    callBackFunction();
                }
            } else {
                proUntil.alert_win(proUntil.OPERTION_FAIL);
            }
        },"false");
    };


    function updatePageTitle(){
       var menu_name =  window.parent.$(".location").attr('now_menu');
       var isNull = proUntil.strIsEmpty(menu_name);
       if(!isNull){
           $('.work_content_box > p ').html(menu_name);
       }
    }

    function judgeBtnisShow(){
        var menu_id = window.parent.menu_id;
        if(!proUntil.strIsEmpty(menu_id)){
            proUntil.request("lampCommon/getMenuBtnByUser",{menuId:menu_id},function(data){
                if('n' == data){
                    return;
                }
                data = JSON.parse(data);
                var html = '<style type="text/css">';
                $.each(data,function(index,val){
                    html += val.style_css + '{display:inline-block !important;} '
                });
                html += "</style>";
                $('body > h6').html(html);
            });
        }
    }

     function showLocationTime (){
        var weekDayLabels = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();
        var hour=now.getHours();   //获取小时
        var minues;  // 获取分钟
        if(now.getMinutes() < 10){
            minues = "0" + now.getMinutes();
        }else{
            minues = now.getMinutes();
        }
        var second;  //  获取秒
        if(now.getSeconds() < 9){
            var num = now.getSeconds()+1;
            second = "0" + num;
        }else{
            second = now.getSeconds()+1;
        }
        document.getElementById("now_date").innerHTML = year + "年" + month + "月" + day + "日 "+"&nbsp;&nbsp;"+hour+":" + minues +":"+ second +"&nbsp;&nbsp;"+ weekDayLabels[now.getDay()]
    }

    function commonHide(obj,direction,btn_obj){
        var w = 100;
        if(direction == "left" || direction == "right"){
            w = obj.width()/2;
        } else if(direction == "top" || direction == "bottom"){
            w = obj.height()/2;
        }
        var location_value =  obj.css(direction);
        obj.attr('obj_location',location_value);
        w =  -w + "px";
        if(direction == "left"){
            obj.animate({'left': w,"opacity":0},screen_show_time, function(){
                btn_obj.show();
            });
        } else if(direction == "right"){
            obj.animate({'right': w,"opacity":0},screen_show_time, function(){
                btn_obj.show();
            });
        } else if(direction == "top"){
            obj.animate({'top': w,"opacity":0},screen_show_time, function(){
                btn_obj.show();
            });
        } else if(direction == "bottom"){
            obj.animate({'bottom': w,"opacity":0},screen_show_time, function(){
                btn_obj.show();
            });
        }
    }

    function commonShow(model_obj,direction){
        var right_value = model_obj.attr('obj_location');
        if(direction == "left"){
            model_obj.animate({"left": right_value,"opacity":1,},screen_show_time);
        } else if(direction == "right") {
            model_obj.animate({"right": right_value,"opacity":1,},screen_show_time);
        } else if (direction == "top") {
            model_obj.animate({"top": right_value,"opacity":1,},screen_show_time);
        } else if(direction == "bottom"){
            model_obj.animate({"bottom": right_value,"opacity":1,},screen_show_time);
        }

    }

