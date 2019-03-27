    var proCharts = {};
    $(function(){

    });

    proCharts.curveChart = function(id_name){
        var myChart = echarts.init(document.getElementById(id_name));
        var option = {
            title : {
                text : '',
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
                data : ['金闽路区域'],
                textStyle:{
                    color:'#fff'
                }
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
            }  ,
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
                data : ['2018-11-01']
            },
            yAxis : {
                type : 'value',
                splitLine:{show: true},
                axisLabel: {
                    formatter: '{value} h',
                    show: true,
                    textStyle: {
                        color: '#fff'

                    }
                },
                scale:true
            },
            series : [ {
                showAllSymbol: true,
                name : '金闽路',
                type : 'line',
                data : [55]
            }]
        };
        myChart.setOption(option);
        return myChart;
    };

    //仪表盘
    proCharts.instrument = function(id){
        var chart = echarts.init(document.getElementById(id));
        var option = {
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {},
            series: [
                {
                    name: '',
                    type: 'gauge',
                    detail: {formatter:'{value}次',textStyle:{fontSize:18}},
                    data: [{value: 0, name: '今日报警',}],
                    axisLine: { // 坐标轴线
                        lineStyle: { // 属性lineStyle控制线条样式
                            color: [
                                [0.09, '#00FF7C'],
                                [0.82, '#FF9900'],
                                [1, '#FF3A00']
                            ],
                            width:12,
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 0
                        }
                    },
                    splitLine: {           // 分隔线
                        length:12,         // 属性length控制线长
                        lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                            color: '#fff'
                        }
                    },
                    axisTick: {            // 坐标轴小标记
                        length:0,        // 属性length控制线长
                        lineStyle: {       // 属性lineStyle控制线条样式
                            color: '#fff'
                        }
                    },
                    pointer: {
                        width:5,
                        //shadowColor : '#fff', //默认透明
                        shadowBlur: 5
                    },
                    title : {
                        // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        //fontWeight: 'bolder',
                        fontSize: 14,
                        // fontStyle: '',
                        color:'#fff'
                    }
                }
            ]
        };
        chart.setOption(option);
        return chart;
    };