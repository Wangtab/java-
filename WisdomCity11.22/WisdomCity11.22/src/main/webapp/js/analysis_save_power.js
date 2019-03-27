    $(function(){
        init_effort();
    });

    function init_effort(){
        var searchBox = $('.search_box');
        proUntil.searchLinkSelect(searchBox.find('select[name ="areaId"]'),searchBox.find('select[name ="roadId"]'));
    }

    function searchData(){
        var searchBox = $('.search_box');
        var roadSel = searchBox.find('select[name ="roadId"]');
        var roadId = roadSel.val();
        var roadName = roadSel.find("option:selected").text();
        var power = searchBox.find('input[name ="power"]').val();
        if(proUntil.strIsEmpty(roadId)){
            proUntil.alert_win("请选择道路");
            return;
        }

        proUntil.request("getSavePowerCharData",{roadId:roadId,power:power},function(data){
            data = JSON.parse(data)[0];
            var roadName_arr = [];
            roadName_arr[0] = roadName;
            var energy_arr = [];
            energy_arr[0] = data.energy;
            var calPower_arr = [];
            calPower_arr[0] = data.calPower;
            savePower1(roadName_arr,energy_arr,calPower_arr);
        });

    }


    function savePower1(roadName,energy,calPower){
        var save_power_chart = echarts.init(document.getElementById('broken'));
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
                data :roadName
            } ],
            yAxis : [ {
                type : 'value',
                scale : true,
                min : 0,
                // splitNumber: 3,
                axisLabel : {
                    formatter : function(KWH) {
                        return KWH + ' kwh';
                    },
                    textStyle:{
                        color:"#fff"
                    }
                },
                textStyle:{
                    color:"#fff"
                },
                boundaryGap : [ 0.5, 1 ],
                splitArea : {
                    show : true
                }
            } ],
            series : [ {
                name : '实际功率',
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
                data : [energy/1000]
            },{
                    name : '理论能耗',
                    type : 'bar',
                    symbol : 'none',
                    data : [calPower/1000],
                    barMaxWidth : 50
                    /*itemStyle: {
                        normal:{
                            color: function (params){
                                var colorList = ['#8ACDF8'];
                                return colorList[params.dataIndex];
                            }
                        },
                    }*/
                },]
        };
        save_power_chart.setOption(option);
    }