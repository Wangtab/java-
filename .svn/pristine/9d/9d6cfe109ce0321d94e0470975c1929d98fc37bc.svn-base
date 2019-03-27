    var myChart = null;
    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 8;//每页显示多少条数据
    var sort = "desc";
    $(function() {
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });
        init_data();

        $('.import_btn').click(function(){
            window.location.href="../exportDimmingDataExcel?"+$('.search_box').serialize();
        });
    });

    function init_data(){
        var searchBox = $('.search_box');
        var areaSelect = searchBox.find('select[name ="areaId"]');
        var roadSelect = searchBox.find('select[name ="roadId"]');
        var roadLineSelect = searchBox.find('select[name ="lineId"]');
        //获取区域信息
        proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
        areaSelect.change(function(){
            var areaId = areaSelect.val();
            if(proUntil.strIsEmpty(areaId)){
                roadSelect.html('<option value = "">请选择道路</option>');
                roadLineSelect.html('<option value = "">请选择线路</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
            roadLineSelect.html('<option value = "">请选择线路</option>');
        });

        //道路选择显示线路
        roadSelect.change(function(){
            var roadId = roadSelect.val();
            if(proUntil.strIsEmpty(roadId)){
                roadLineSelect.html('<option value = "">请选择线路</option>');
                return;
            }
            proUntil.getSelData("lampCommon/getRoadLineNameForSelect",roadLineSelect,{roadId:roadId},'<option value = "">请选择线路</option>');
        });
    }

    function tableSelect(){
        var curPageObj = $('.search_box').find('input[name="curPage"]');
        var ShowNum =  $('.search_box').find('input[name="showNum"]');
        curPageObj.val(1);
        ShowNum.val(page_show_Num);
        selectAll();

    }

    function selectAll(){
        var searchBox =  $('.search_box');
        var startDate = searchBox.find('input[name="startDate"]').val();
        var endDate =  searchBox.find('input[name="endDate"]').val();
        if(startDate > endDate){
            proUntil.alert_win('输入时间区间有误');
            return;
        }
        proUntil.request("dimmingDataAnalysis",$('.search_box').serialize(),function(data){
            var table = $('.table_list').find('table');
            var firstTr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            if('n' == data){
                $('.table_list').hide();
                $('#dimmingChart').hide();
                proUntil.alert_win('没有查找到相关信息');
                table.html(firstTr);
                return;
            }
            data = JSON.parse(data)[0];
            var curPageObj = $('.search_box').find('input[name="curPage"]');
            cur_page = curPageObj.val();
            cur_page = parseInt(cur_page);
            count_page = Math.ceil(parseInt(data.count)/page_show_Num);
            var html = "";
            $("#controllerPage").html("");
            $.each(data.data,function(index,val){
                html += '<tr dataId = "'+val.id+'" datatype="'+val.dataType+'">\n' +
                    '<td>'+val.cName+'</td>\n' +
                    '<td>'+val.num+'</td>\n' +
                    '</tr>';
            });
            table.html(firstTr + html);
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
                curPageObj.val(curpage);
                selectAll();
            });
            tableListClikc();
            $('.table_list').show();
        });
    }

    /**
     * 列表点击
     */
    function tableListClikc(){
        $('.table_list').find('table').find('tr').not(':first').click(function(){
            $('#dimmingChart').css('display','block');
            var _this = $(this);
            var startDate = $('#startDated').val() || "";
            var endDate = $('#endDated').val() || "";
            $.ajax({
                type : "post",
                url : "../getDimmingDataCuredChartsById",
                data:{dataType:_this.attr('datatype'),dataId:_this.attr('dataId'),startDate:startDate,endDate:endDate},
                dataType : 'json',
                beforeSend:function(XHR){
                    myChart = echarts.init(document.getElementById('dimmingChart'));
                    myChart.showLoading({
                        text : '加载中...',
                        effect : "whirling",
                        textStyle : {
                            fontSize : 20
                        }
                    });
                    $("#search_btn").attr("disabled","disabled");
                },
                success : function(result){
                    result = result[0];
                    if(result.oper == 'n'){
                        proUntil.alert_win('没有查找到相关信息');
                        return;
                    }
                    var dataName = _this.find('td').eq(0).html();
                    var name_arr = [];
                    name_arr[0] = dataName;
                    result.dataName = name_arr;
                    areaDataChart(result);
                },
                complete : function(XHR, TS) {
                    myChart.hideLoading();
                    $("#search_btn").removeAttr("disabled");
                }
            });

        });
    }



    function areaDataChart(result){
        myChart = echarts.init(document.getElementById('dimmingChart'));
        option = {
            title : {
                text : result.dataName[0] + '曲线图',
                textStyle:{
                    color:'#fff'
                },
                x : 'center'
            },
            tooltip : {
                trigger : 'axis'
            },
            legend : {
                y:'30',
                data : result.dataName,
                textStyle:{
                    color:'#fff'
                },
            },
            grid : {
                containLabel : true
            },
            dataZoom: {
                show: true,
                realtime: true,
                bottom:20,
                height: 20,
                start: 0,
                end: 100,
                textStyle: {
                    color: '#fff'
                }
            },
            toolbox : {
                show:true,
                orient:'vertical',
                y:'center',
                feature : {
                    dataZoom : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },

            xAxis : {
                type : 'category',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                boundaryGap : false,
                data : result.timeList
            },
            yAxis : {
                type : 'value',
                splitLine:{show: true},
                axisLabel: {
                    formatter: '{value} %',
                    show: true,
                    textStyle: {
                        color: '#fff'

                    }
                },
                scale:false,
            },
            series : [ {
                name : result.dataName[0],
                type : 'line',
                data : result.dimmingList
            }]
        };
        myChart.setOption(option);
    }