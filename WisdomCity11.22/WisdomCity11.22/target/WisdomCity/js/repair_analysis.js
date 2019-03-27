    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var myChart = null;
    $(function() {
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });
        var search_box =  $('.search_box');
        proUntil.searchLinkSelect(search_box.find('select[name ="areaId"]'),search_box.find('select[name ="roadId"]'));

        $('.import_btn').click(function(){
            var searchBox =  $('.search_box');
            var url = "exportRepairAnalysisDataForList?areaId="+searchBox.find('select[name="areaId"]').val() +
                "&roadId="+searchBox.find('select[name="roadId"]').val() +
                "&lineId="+searchBox.find('select[name="lineId"]').val() +
                "&startDate="+searchBox.find('input[name="startDate"]').val() +
                "&endDate="+searchBox.find('input[name="endDate"]').val();
            window.location.href=url;
        });

    });

    function selectAll(){
        var searchBox =  $('.search_box');
        var paramObj = {
            areaId : searchBox.find('select[name="areaId"]').val(),
            roadId : searchBox.find('select[name="roadId"]').val(),
            startDate : searchBox.find('input[name="startDate"]').val(),
            endDate : searchBox.find('input[name="endDate"]').val(),
            curPage : cur_page,
            showNum : page_show_Num
        };
        proUntil.request("repairAnalysisDataForList",paramObj,function(data){
            if("n" == data){
                $('.table_list').hide();
                $('.area_model').hide();
                proUntil.alert_win('没有查找到相关信息');
                return;
            }
            var table = $('.table_list').find('table');
            var firstTr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            data = JSON.parse(data)[0];
            count_page = Math.ceil(parseInt(data.count)/page_show_Num);
            var html = "";
            $.each(data.data,function(index,val){
                html += '<tr data_id = "'+val.id+'"  flag_name = "'+val.flag+'" >' +
                    '<td>'+val.cName+'</td>' +
                    '<td>'+val.num+'</td>' +
                    '</tr>';
            });
            table.html(firstTr + html);
            page({
                id:'controllerPage',
                nowNum: cur_page,
                allNum: count_page
            });

            $("#controllerPage").find("a").unbind('click').click(function(){
                var _this = $(this);
                cur_page = _this.attr('cur');
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
            var _this = $(this);
            $('.area_model').show();
            $.ajax({
                type : "post",
                url : "repairAnalysisDataForChart",
                data:{dataType:_this.attr('flag_name'),dataId:_this.attr('data_id'),startDate : $('.search_box').find('input[name="startDate"]').val(),
                    endDate : $('.search_box').find('input[name="endDate"]').val()},
                dataType : 'json',
                beforeSend:function(XHR){
                    myChart = echarts.init(document.getElementById('areaPower'));
                    myChart.showLoading({
                        text : '加载中...',
                        effect : "whirling",
                        textStyle : {
                            fontSize : 20
                        }
                    });
                },
                success : function(result){
                    if('n' == result){
                        proUntil.alert_win('没有查找到相关信息');
                        return;
                    }
                    result = result[0];
                    var dataName = _this.find('td').eq(0).html();
                    var name_arr = [];
                    name_arr[0] = dataName;
                    result.dataName = name_arr;
                    repairDataChart(result);
                },
                complete : function() {
                    myChart.hideLoading();
                }
            });

        });
    }

    function repairDataChart(result){
        myChart = echarts.init(document.getElementById('areaPower'));
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
                data : result.dateList
            },
            yAxis : {
                type : 'value',
                splitLine:{show: true},
                axisLabel: {
                    formatter: '{value} 次',
                    show: true,
                    textStyle: {
                        color: '#fff'

                    }
                },
                scale:true,
            },
            series : [ {
                name : result.dataName[0],
                type : 'line',
                data : result.dataList
            }]
        };
        myChart.setOption(option);
    }
