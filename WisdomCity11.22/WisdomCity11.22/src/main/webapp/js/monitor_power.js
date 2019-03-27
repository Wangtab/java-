    var savePower = null;
    $(function() {
        var search_box = $('.search_box');
        proUntil.searchLinkSelect(search_box.find('select[name ="areaId"]'),search_box.find('select[name ="roadId"]'));
        savePower1();
    });


    /*加载显示*/
    function selectAll(){
       var search_box = $('.search_box');
       var roadId = search_box.find("select[name='roadId']").val();
       if(proUntil.strIsEmpty(roadId)){
           proUntil.alert_win("请选择道路");
           return;
       }

       proUntil.request("queryRoadPower",{"roadId":roadId},function(data){
           if("n" == data){
               $('.canvens_box').hide();
               proUntil.alert_win('没有数据');
               return;
           }
           $('.canvens_box').css('opacity',1);
           var option = savePower.getOption();
           var roadName = search_box.find("select[name='roadId']").find("option:selected").text();
           data = JSON.parse(data);
           var today_power = [];
           today_power[0] = data[0].power;
           var yesterday_power = [];
           yesterday_power[0] = data[1].power;
           option.xAxis[0].data = new Array([roadName]);
           option.series[0].data = today_power;
           option.series[1].data = yesterday_power;
           savePower.setOption(option,true);
       });
    }

    /*柱状图*/
    function savePower1(){
        savePower = echarts.init(document.getElementById('broken'));
        var option = {
            title : {
                textStyle:{
                    color:"#fff",
                    fontSize:16,
                },
                x : 'center'
            },

            tooltip : {
                trigger : 'axis',
                textStyle:{
                    color:"#fff"
                },
                // showDelay: 0
            },
            xAxis : [ {
                type : 'category',
                boundaryGap : true,
                // axisTick: {onGap:false},
                splitLine : {
                    show : false
                },
                axisLabel:{
                    textStyle:{
                        color:"#fff"
                    }
                },
                textStyle:{
                    color:"#fff"
                },
                data : ['金闽路']
            } ],
            yAxis : [ {
                type : 'value',
                scale : true,
                min : 0,
                // splitNumber: 3,
                axisLabel : {
                    formatter : function(KWH) {
                        return KWH + ' kw';
                    },
                    textStyle:{
                        color:"#fff"
                    }
                },
                textStyle:{
                    color:"#fff"
                },
                boundaryGap : [ 0.05, 0.05 ],
                splitArea : {
                    show : true
                }
            } ],
            series : [ {
                name : '今日功率',
                type : 'bar',
                barMaxWidth : 50,
                symbol : 'none',
                itemStyle: {
                    label:{
                        normal:{
                            show: true,
                            position: "left",
                        }
                    },
                },
                data : [45]
            },
                {
                    name : '昨日功率',
                    type : 'bar',
                    symbol : 'none',
                    data : [50],
                    barMaxWidth : 50,
                    itemStyle: {
                        normal:{
                            color: function (params){
                                var colorList = ['#8ACDF8'];
                                return colorList[params.dataIndex];
                            }
                        },
                    }
                }]
        };
        savePower.setOption(option);
    }