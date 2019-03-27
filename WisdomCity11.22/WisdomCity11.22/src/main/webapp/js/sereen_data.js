﻿	var map = null;
	var allEnergyChart = null;
	var areaPowerCurved = null;
	var allPowerChart = null;
	var save_power_chart = null;
    var today_alarm_charts = null;
    var light_rate_chart = null;
    var colorList = ['#C23531','#FF9900','#91C7AE','#9600FF','#FFA600','#01B7FF','#44FF00'];
    var roadListTimer = null;
	$(function() {
         init_effect();
         repeat_chartData();
         $(window).resize(function() {
             //window.location.href = "screendata.jsp";
        });
         //地图风格
         $('.time_warpper').find('i').click(function(){
             var obj = $(this);
             var map_style = obj.attr('map_style');
             var change_style = null;
             if('midnight' == map_style){
                 change_style = 'dark';
             }else {
                 change_style = "midnight";
             }
             map.setMapStyle({style:change_style});
             obj.attr('map_style', change_style);
         });

	});

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
                html +='<li>';
                if("line" == data_type){
                    html +='<p data_id = '+val.id+' data_type="'+data_type+'"><b>-</b>'+val.cname+'</p>';
				}else{
                    html +='<p data_id = '+val.id+' data_type="'+data_type+'"><b>+</b>'+val.cname+'</p>';
				}
                html +='</li>';
            });
            html +='</ul>';

        },"false");
        return html;
	}

	function roadList_click(JQuery_obj){
        JQuery_obj.find('p').unbind('click').click(function(){
			getLampDataLocation(this.attr('data_id'));
            clearTimeout(roadListTimer);
            var _this = $(this);
            roadListTimer = setTimeout(function(){
                roadClick(_this);
			},300);
		});
        JQuery_obj.find('p').unbind('dbclick').dblclick(function(){
            clearTimeout(roadListTimer);
            var _this = $(this);
            var dataType = _this.attr('data_type');
            proUntil.request("getLongitudeAndlatitude",{dataType:dataType,dataId:_this.attr('data_id')},function(data){
            	if('n' == data){
            		proUntil.alert_win("未找到设备信息");
            		return;
				}
				data = JSON.parse(data)[0];
				var level = 14;
				if("road" == dataType){
                    level = 16;
				}else if("line" == dataType){
                    level = 18;
				}
				debugger
                map.centerAndZoom(new BMap.Point(data.lo,data.la),level);
            });

        });
	}

	function roadClick(obj){
        var _this = $(obj);
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
            _this.nextAll('ul').hide(manage_table_time,function(){
                _this.nextAll('ul').remove();
			});

        }
        b.html('+' == mark ? '-' : "+");
	}


	//初始化效果
	function init_effect(){
        map = initMap("allmap");
        init_roadList();
        $(".dimming_light").slider({
            formatter: function (value){
                return  value + "%";
        }});

        $('.showAllData_win > p span').click(function(){
            $('.showAllData_win').fadeOut(manage_table_time);
        });

        $('.lampNumList').find('h1').click(function(){
            $('.showAllData_win').fadeIn(manage_table_time);
        });

		//详细信息
		$('.roadList').find('i').click(function(){
			commonHide($('.roadList'),"right",$('.roadList_btn'));
		});

		$('.roadList_btn').click(function(){
			$(this).hide();
			commonShow($('.roadList'),"right");
		});

		//亮灯率
		$('.light_rate_content').find('h6').click(function() {
			commonHide($('.light_rate'),"left",$('.light_rate_btn'));
		});

		$('.light_rate_btn').click(function() {
			$(this).hide();
			commonShow($('.light_rate'),"left");
		});

		//今日报警
		$('.lamp_warn').find('i').click(function() {
			commonHide($('.lamp_warn'),"left",$('.lamp_warn_btn'));
		});

		$('.lamp_warn_btn').click(function() {
			$(this).hide();
			commonShow($('.lamp_warn'),"left");
		});

		//灯具个数列表
		$('.lampNumList').find('i').click(function() {
			commonHide($('.lampNumList'),"top",$('.lampNumList_btn'));
		});


		$('.lampNumList_btn').click(function() {
			$(this).hide();
			commonShow($('.lampNumList'),"top");
		});

		//实时能耗
		$('.realConsumePower_warpper').find('i').click(function() {
			commonHide($('.realConsumePower'),"bottom",$('.realConsumePower_btn'));
		});

		$('.realConsumePower_btn').click(function() {
			$(this).hide();
			commonShow($('.realConsumePower'),"bottom");
		});

		getPoint();
		getClockDate();
		setInterval(getClockTime,60 * 1000);
		//图标初始化
        allEnergyChart1();
        areaPowerCurve();
        savePower1();
        lightRateCharts();
        today_alarm_charts = proCharts.instrument("today_alarm_disc");
        allPowerCurve();
	}

    /**
	 *  获取页面相关数据
     */
	function repeat_chartData(){
		repeat_power();
        repeat_energy();
        repeat_area_power();

        proUntil.request("screen/getSingleData",{},function(data){
            data = JSON.parse(data)[0];
            var lampList = $('.lampNumList');
            lampList.find('.open_lamp').html(data.lamp_open_num);
            lampList.find('.close_lamp').html(data.lamp_close_num);
            lampList.find('.expection_lamp').html(data.lamp_exp_num);
            lampList.find('.online_exe').html(data.ele_open_num);
            lampList.find('.offline_exe').html(data.ele_close_num);

            $(".frist_dealBox").text("已处理：" + data.deal_warn_num + "个");
            $(".second_dealBox").text("未处理：" + data.undeal_warn_num + "个");

            var today_alarm_option = today_alarm_charts.getOption();
            today_alarm_option.series[0].data[0].value = data.today_warn_num;
            today_alarm_charts.setOption(today_alarm_option,true);

            getLightRateNum(data.format_light_num,data.light_rate);

			var light_rate_option = light_rate_chart.getOption();
            light_rate_option.series[0].data[0].value = data.light_rate;
            light_rate_option.series[0].data[1].value = 100 - data.light_rate;
            light_rate_chart.setOption(light_rate_option,true);

            var save_power_option = save_power_chart.getOption();
            var energy_arr = [];
            energy_arr[0] = data.real_energy;
            var calPower_arr = [];
            calPower_arr[0] = data.theory_energy;
            save_power_option.series[0].data = energy_arr;
            save_power_option.series[1].data = calPower_arr;
            save_power_chart.setOption(save_power_option,true);

        });
	}

	function getPoint(){
		//top
		var top_left = $('.top_left_corner').find('i').position().left;
		var top_right =  $('.right_top_border_bg').position().left;
		var top_distance = top_right - top_left;
		$('.top_left_corner').find('i').css({'width':top_distance + "px",'marginRight':- top_distance + "px"});

		//bottom
		var bottom_left = $('.left_bottom_border').find('i').position().left;
		var bottom_right =  $('.right_bottom_border').position().left;
		var bottom_distance = bottom_right - bottom_left;
		$('.left_bottom_border').find('i').css({'width':bottom_distance + "px",'marginRight':- bottom_distance + "px"});
	}

	/**
	 * [获取亮灯数]
	 * @param  {[type]} lightNum [亮灯数]
	 * @param  {[type]} lightRate [亮灯率]
	 */
	function getLightRateNum(lightNum,lightRate){
		var arr = lightNum.split("");
		$.each($('.light_num').find('li'),function(index,obj){
			$(obj).html(arr[index]);
		});
		$('.lightRateChartsNum').html(lightRate + "%");
	}


	/**
	 * 获取当前时间
	 */
	function getClockDate(){
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hour=now.getHours();   //获取小时
		var minues = now.getMinutes();  // 获取分钟
		if(month < 10){
			month = "0" + month;
		}
		if(day < 10){
			day = "0" + day
		}
		if(minues < 10){
		    minues = "0" + minues;
		}
		
		var obj = $('.time_warpper');
		obj.find('span').html(year + "/" + month + "/" + day);
		obj.find('h1').html(hour + ":" + minues);
	}

	/**
	 * 获取钟表时间
	 */
	function getClockTime(){
		var now = new Date();
		var hour=now.getHours();
		var minues;
		if(now.getMinutes() < 10){
		    minues = "0" + now.getMinutes();
		}else{
		    minues = now.getMinutes();
		}
		$('.time_warpper').find('h1').html(hour + ":" + minues);
	}


	//总能耗分析
	function allEnergyChart1(){
	    allEnergyChart = echarts.init(document.getElementById('allEnergyChart'));
        var option = {
            title : {
                textStyle:{
                    color:'#FFF',
                    fontSize:16,
                },
                x : 'center'
            },
            tooltip : {},
            legend : {
                y:'30',
                data : [ '']
            },
            grid : {
                left: '5%',
                right: '12%',
                bottom: '30',
                containLabel : true
            },
            toolbox : {
                show:true,
                orient:'vertical',
                y:'center',
                feature : { }
            },

            xAxis : {
                type : 'category',
                splitLine:{show: true,
                    lineStyle:{
                        color:'rgba(255,255,255,0.5)',
                        width: 2
                    }
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                boundaryGap : false,
                data : [ '2016-12-19', '2016-10-21', '2016-10-23', '2016-10-25', '2016-10-27']
            },
            yAxis : {
                type : 'value',
                splitLine:{show: true,
                    lineStyle:{
                        color:'rgba(255,255,255,0.5)',
                        width: 2
                    }
                },
                axisLabel: {
                    formatter: '{value} kwh',
                    show: true,
                    textStyle: {
                        color: '#fff',

                    }
                },
                scale:false,
            },
            series : [ {
                name : '平台区域能耗总量',
                type : 'line',
                data : [ 90, 91, 92, 89, 93],
                lineStyle: {
                    //通常情况下：
                    normal:{
                        //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                        color: function (params){
                            var colorList = ['#00C2B2','#0018FF','#FE0058','#9600FF','#FFA600','#01B7FF','#44FF00'];
                            return colorList[params.dataIndex];
                        }
                    },
                },
            }]
        };
	    allEnergyChart.setOption(option);
	}


    //总功率分析
    function allPowerCurve(){
        allPowerChart = echarts.init(document.getElementById('allPowerChart'));
		var option = {
                title : {
                    textStyle:{
                        color:'#FFF',
                        fontSize:16,
                    },
                    x : 'center'
                },
                tooltip : {},
                legend : {
                    y:'30',
                    data : [ '']
                },
                grid : {
                    left: '5%',
                    right: '12%',
                    bottom: '30',
                    containLabel : true
                },
                toolbox : {
                    show:true,
                    orient:'vertical',
                    y:'center',
                    feature : { }
                },

                xAxis : {
                    type : 'category',
                    splitLine:{show: true,
                        lineStyle:{
                            color:'rgba(255,255,255,0.5)',
                            width: 2
                        }
                    },
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    boundaryGap : false,
                    data : [ '2016-12-19', '2016-10-20', '2016-10-21', '2016-10-22', '2016-10-23']
                },
                yAxis : {
                    type : 'value',
                    splitLine:{show: true,
                        lineStyle:{
                            color:'rgba(255,255,255,0.5)',
                            width: 2
                        }
                    },
                    axisLabel: {
                        formatter: '{value} kwh',
                        show: true,
                        textStyle: {
                            color: '#fff',

                        }
                    },
                    scale:true,
                },
                series : [ {
                    name : '平台总消耗值',
                    type : 'line',
                    data : [ 90, 91, 90, 89, 90],
                    itemStyle:{
                        normal:{
                            lineStyle:{
                                color:'#FDC42C',
                                width:2
                            }
                        },
                    }
                }]
            };
        allPowerChart.setOption(option);
    }

	//区域能耗分析
	function areaPowerCurve(){
	    areaPowerCurved = echarts.init(document.getElementById('areaPowerCurved'));
	    var option = {
	        title : {
	            textStyle:{
	               color:'#FFF',
	               fontSize:16,
	            },
	            x : 'center'
	        },
	        tooltip : {},
	        legend : {
	             y:'30',
	            data : [ '']
	        },
            grid : {
                left: '5%',
                right: '12%',
                bottom: '30',
                containLabel : true
            },
	        toolbox : {
	            show:true,
	            orient:'vertical',
	            y:'center',
	            feature : { }
	        },
	    
	        xAxis : {
	            type : 'category',
	        	splitLine:{show: true, 
	        		lineStyle:{
						color:'rgba(255,255,255,0.5)',
						width: 2
					}
				},
	            axisLabel: {
	                show: true,
	                textStyle: {
	                    color: '#fff'
	                }
	            },
	            boundaryGap : false,
	            data : [ '2016-12-19', '2016-10-20', '2016-10-21', '2016-10-22', '2016-10-23']
	        },
	        yAxis : {
	            type : 'value',
	        	splitLine:{show: true, 
	        		lineStyle:{
						color:'rgba(255,255,255,0.5)',
						width: 2
					}
				},
	            axisLabel: {
	                formatter: '{value} kwh',
	                show: true,
	                textStyle: {
	                    color: '#fff',

	                }
	            },
	            scale:true,
	        },
	        series : [ {
	            name : '平台区域能耗总量',
	            type : 'line',
	            data : [ 90, 91, 90, 89, 90],
                lineStyle: {
                    //通常情况下：
                    normal:{
                        //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                        color: function (params){
                            var colorList = ['#00C2B2','#0018FF','#FE0058','#9600FF','#FFA600','#01B7FF','#44FF00'];
                            return colorList[params.dataIndex];
                        }
                    },
                },
	        }]
	    };
	    areaPowerCurved.setOption(option);
	}



	//节能率
	function savePower1(){
		save_power_chart = echarts.init(document.getElementById('savePowerd'));
	    var option = {
	        title : {
	            textStyle:{
	                color:"#fff",
	                fontSize:16,
	            },
	            x : 'center'
	        },
            grid : {
                left: '5%',
                right: '12%',
                bottom: '30',
                containLabel : true
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
	            data : ['']
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
	            boundaryGap : [ 0.05, 0.05 ],
	            splitArea : {
	                show : true
	            }
	        } ],
	        series : [ {
	            name : '能耗',
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
	            data : [127.01]
	        },
	        {
			name : '理论值',
			type : 'bar',
			symbol : 'none',
			data : ['227.04'],
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
	    save_power_chart.setOption(option);
	}

	//亮灯率
	function lightRateCharts(){
        light_rate_chart = echarts.init(document.getElementById("lightRateChartsd"));
		var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    series: [
		        {
		            name:'亮灯比率',
		            type:'pie',
		            radius: ['58%', '75%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: false,
		                    textStyle: {
		                        fontSize: '12'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                },
		                lineStyle:{
		                	width:1,
		                	type:'solid'
		                }
		            },
		            data:[
		                {value:0, name:'亮灯比率',
		                itemStyle:{
		                	normal:{
		                		color:'#ff9900'
		                	}
		                }
		            },
		                {value:100, name:'灭灯比率',
		                itemStyle:{
		                	normal:{
		                		color:'#7f99ad'
		                	}
		                }
		            }
		            ]
		        }
		    ]
		};
		if (option && typeof option === "object") {
            light_rate_chart.setOption(option, true);
		}
	}


    //总功率
    function repeat_power(){
        $.ajax({
            type:"post",
            dataType:"text",
            url:"screen/getSumPowers",
            success: function (data) {
                data = JSON.parse(data)[0];
                var option = allPowerChart.getOption();
                option.series[0].data = data.data;
                option.xAxis[0].data = data.date;
                allPowerChart.setOption(option,true);
            }
        });
    }
	//能耗
    function repeat_energy(){
        $.ajax({
            type:"post",
            dataType:"text",
            url:"screen/getSumEnergyData",
            success: function (data) {
                data = JSON.parse(data)[0];
                var option = allEnergyChart.getOption();
                option.series[0].data = data.data;
                option.xAxis[0].data = data.date;
                allEnergyChart.setOption(option,true);
            }
        });
    }

    //今日区域消耗分析
    function repeat_area_power(){
        $.ajax({
            type:"post",
            dataType:"text",
            url:"screen/getAreasPowers",
            success: function (data) {
                var cure = $('.areaPowerBox').find('.chart_info');
                if('n' == data){
                    cure.css('display','block');
                    return;
                }
                cure.css('display','none');
                data = JSON.parse(data)[0];
                var option = areaPowerCurved.getOption();
                deal_color(data.data);
                option.series = data.data;
                option.xAxis[0].data = data.date;

                areaPowerCurved.setOption(option,true);
            }
        });
    }

    function deal_color(arr){
        var len = colorList.length;
        for(var i = 0; i< arr.length;i++){
            arr[i].itemStyle = {normal:{lineStyle:{color:colorList[i%len]}}};
        }
    }