$(document).ready(function () {
	var myChart =echarts.init(document.getElementById('main'));//到案方式图表
    var myChart14 = echarts.init(document.getElementById('dbzhexian'));//人员类型图表
    var myChart13 = echarts.init(document.getElementById('zhexian'));//7天入办案区人数统计图表
    //默认显示周统计数据
    weekChange();


    //旋转
    $(".cenMidCircle .img1").addClass("rotate1");
    $(".cenMidCircle .img2").addClass("rotate2");
    $(".cenMidCircle .img3").addClass("rotate3");
    $(".cenMidCircle .img4").addClass("rotate4");
    $(".cenMidCircle .img5").addClass("rotate5");
    $(".cenMidCircle .img6").addClass("rotate6");
    $(".cenMidCircle .img7").addClass("rotate7");


    //周、月、年，三个按钮点击时间绑定
    $(".rightTop .rightBox div").on("click",function(){
    	$(this).addClass("active").siblings().removeClass("active");
    	var index = $(this).index();
    	if(index == 0){
    		$(".btnBox .prenBtn span").text("上一周");
    		$(".btnBox .nextBtn span").text("下一周");
    		//刷新数据
    		weekChange();
    	}else if(index == 1){
    		$(".btnBox .prenBtn span").text("上一月");
    		$(".btnBox .nextBtn span").text("下一月");
    		//刷新数据
    		monthChange();
    	}else if(index == 2){
    		$(".btnBox .prenBtn span").text("上一年");
    		$(".btnBox .nextBtn span").text("下一年");
    		//刷新年数据
    		yearChange();
    	}
    })

    //上一页  点击事件绑定
    $(".btnBox .prenBtn").on("click",function(){
    	var preText = $(".btnBox .prenBtn span").text();
    	if(preText.indexOf("周") >= 0){
    		//刷新周数据
    		weekChange('pre');
    	}else if(preText.indexOf("月") >= 0){
    		//刷新月数据
    		monthChange('pre');
    	}else if(preText.indexOf("年") >= 0){
    		//刷新年数据
    		yearChange('pre');
    	}
    })
    //下一页 点击事件绑定
    $(".btnBox .nextBtn").on("click",function(){
    	var preText = $(".btnBox .prenBtn span").text();
    	if(preText.indexOf("周") >= 0){
    		//刷新周数据
    		weekChange('next');
    	}else if(preText.indexOf("月") >= 0){
    		//刷新月数据
    		monthChange('next');
    	}else if(preText.indexOf("年") >= 0){
    		//刷新年数据
    		yearChange('next');
    	}
    })

    //周刷新
    function weekChange(type){
    	var param = $("#nowInput").val();
    	var preParam = $("#preInput").val();
    	var nextParam = $("#nextInput").val();
    	var startDate = "";
    	var endDate = "";
    	if(type == 'pre'){
    		$("#preInput").val(addDays(preParam, -7));
    		$("#nextInput").val(addDays(nextParam, -7));
    		startDate = preParam;
    		endDate = addDays(preParam, 6);
    		$("#nowInput").val(startDate + " 至 " + endDate);
    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeDateBetweenCount.json?startDate=" + startDate + "&endDate=" + endDate);
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeDateBetweenCount.json?startDate=" + startDate + "&endDate=" + endDate);
    	}else if(type == 'next'){
    		$("#preInput").val(addDays(preParam, 7));
    		$("#nextInput").val(addDays(nextParam, 7));
    		startDate = nextParam;
    		endDate = addDays(nextParam, 6);
    		$("#nowInput").val(startDate + " 至 " + endDate);
    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeDateBetweenCount.json?startDate=" + startDate + "&endDate=" + endDate);
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeDateBetweenCount.json?startDate=" + startDate + "&endDate=" + endDate);
    	}else{
    		var nowWeekStart = getWeekStartDate();
    		$("#nowInput").val(getWeekStartDate());
    		$("#preInput").val(addDays(nowWeekStart, -7));
    		$("#nextInput").val(addDays(nowWeekStart, 7));
    		startDate = nowWeekStart;
    		endDate = addDays(nowWeekStart, 6);
    		$("#nowInput").val(startDate + " 至 " + endDate);

    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeDateBetweenCount.json");
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeDateBetweenCount.json");
    	}
    }

    //月刷新
    function monthChange(type){
    	var param = $("#nowInput").val();
    	var preParam = $("#preInput").val();
    	var nextParam = $("#nextInput").val();
    	if(type == 'pre'){
    		$("#nowInput").val(preParam.substr(0,4) + "年" + preParam.substr(4,2) + "月");
    		$("#preInput").val(getPreMonth(preParam));
    		$("#nextInput").val(getPreMonth(nextParam));
    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeMonthCount.json?inMonth=" + preParam);
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeMonthCount.json?inMonth=" + preParam);
    	}else if(type == 'next'){
    		$("#nowInput").val(nextParam.substr(0,4) + "年" + nextParam.substr(4,2) + "月");
    		$("#preInput").val(getNextMonth(preParam));
    		$("#nextInput").val(getNextMonth(nextParam));
    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeMonthCount.json?inMonth=" + nextParam);
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeMonthCount.json?inMonth=" + nextParam);
    	}else{
    		//计算上月下月
    		var nowMonth = getNowMonth();
    		$("#nowInput").val(nowMonth.substr(0,4) + "年" + nowMonth.substr(4,2) + "月");
    		$("#preInput").val(getPreMonth());
    		$("#nextInput").val(getNextMonth());

    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeMonthCount.json");
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeMonthCount.json");
    	}
    }

    //年分刷新
    function yearChange(type){
    	var param = $("#nowInput").val();
    	var preParam = $("#preInput").val();
    	var nextParam = $("#nextInput").val();
    	if(type == 'pre'){
    		$("#nowInput").val(preParam + "年");
    		$("#preInput").val(parseInt(preParam) - 1);
    		$("#nextInput").val(parseInt(nextParam) - 1);
    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeYearCount.json?inYear=" + preParam);
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeYearCount.json?inYear=" + preParam);
    	}else if(type == 'next'){
    		$("#nowInput").val(nextParam + "年");
    		$("#preInput").val(parseInt(preParam) + 1);
    		$("#nextInput").val(parseInt(nextParam) + 1);
    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeYearCount.json?inYear=" + nextParam);
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeYearCount.json?inYear=" + nextParam);
    	}else{
    		var now = new Date();
    		var nowYear = now.getFullYear();
    		$("#nowInput").val(nowYear + "年");
    		$("#preInput").val(parseInt(nowYear)-1);
    		$("#nextInput").val(parseInt(nowYear)+1);

    		//刷新到案方式表格
    		weekInTypeCount("/Index/getInTypeYearCount.json");
    		//刷新身份类别表格
    		weekPersonTypeCount("/Index/getPersonTypeYearCount.json");
    	}
    }

    /**入区超时预警【start】*/
    //单个获取，一个一个加载显示
    function getDelayAlarmSingle(type){
    	var title = "";
    	var foot = "";
    	if(type == 8){
    		title = "入区八小时预警";
    		foot = "8H预警";
    	}else if(type == 12){
    		title = "入区十二小时预警";
    		foot = "12H预警";
    	}else if(type == 24){
    		title = "入区二十四小时预警";
    		foot = "24H预警";
    	}else if(type == 48){
    		title = "入区四十八小时预警";
    		foot = "48H预警";
    	}
        $.ajax({
        	   url: "/Index/getDelayAlarmSingle.json?delayHour="+type,//json文件位置
        	   cache:false,
        	   type: "GET",//请求方式为get
        	   dataType: "json", //返回数据格式为json
        	   success: function(enterOverTimeDatas) {
        		   if(enterOverTimeDatas.success == true){
	      	  	    	var datas = enterOverTimeDatas.content;
	      	  	    	var html = "";
	      	  	    	for ( var i = 0; i < datas.length; i++) {
	      	  	    		if(i>9){
								break;
							}
	      	  	    		html += '<span titles="'+datas[i].name+'" data-id="'+datas[i].id+'">'+datas[i].name+'</span>';
	      	  			}

	      	  	    	var imageUrl = datas.length > 0 ? "../images/index/warn/yujing.png" : "../images/index/warn/yujing02.png";
		      	  	    $(".warningBox>.wh100 .data-"+type).append(html);
		      	  	    $(".warningBox>.wh100 .image-"+type).attr("src",imageUrl);
	      	  	    	 setTimeout(function(){
	      	  	  		 $(".warningBox ul>li .circleBoxX div .imgB").addClass("rotateZ");
	      	  	    	 },200)
        		   }else{
        			   layer.msg(enterOverTimeDatas.errorMsg || '入区超时数据加载错误', {icon: 5});
        		   }
        	   }
          });
    }
    //逐个加载
    getDelayAlarmSingle(8);
    getDelayAlarmSingle(12);
    getDelayAlarmSingle(24);
    getDelayAlarmSingle(48);
	/*setTimeout(function(){
		getDelayAlarmSingle(12);
		setTimeout(function(){
			getDelayAlarmSingle(24);
			setTimeout(function(){
				getDelayAlarmSingle(48);
			},0)
		},0)
	},0)*/

    /**入区超时预警【end】*/

    /**尿样超期预警【start】*/
    $.ajax({
 	   url: "/UrineTestInfo/getOverDeadtimeB",//json文件位置
 	   cache:false,
 	   type: "GET",//请求方式为get
 	   dataType: "json", //返回数据格式为json
 	   success: function(urineOverTimeDatas) {
 		  if(urineOverTimeDatas.data.length > 0){
 		    	var datas = urineOverTimeDatas.data;
 		    	var html = "";
 		    	for ( var i = 0; i < datas.length; i++) {
 		    		if(i > 1){
 		    			break;
 		    		}
 		    		html += '<li>';
 		    		html += '    <a href="javascript:;" class="wh100">';
 		    		html += '        <span title="'+datas[i].bottleb+'">'+datas[i].bottleb+'</span>';
// 		    		html += '        <span title="'+datas[i].officer+'">'+datas[i].officer+'</span>';
 		    		html += '        <span>'+new Date(datas[i].bottleDeadtimeB).format("yyyy-MM-dd")+'</span>';
 		    		html += '    </a>';
 		    		html += '</li>';
 		    	}
 		    	$(".urineOverTime").html(html);
 		    }
 	   }
	});
    /**尿样超期预警【end】*/

    /**待办任务【start】*/
    $.ajax({
  	   url: "/Remind/listRemindByIds.json?roleIds="+$("#roleList_hidden").val(),//json文件位置
  	   cache:false,
  	   type: "GET",//请求方式为get
  	   dataType: "json", //返回数据格式为json
  	   success: function(gtaskDatas) {
  		   if(gtaskDatas.success == true){
  			 if(gtaskDatas.content.length > 0){
  	  	    	var datas = gtaskDatas.content;
  	  	    	var html = "";
  	  	    	for ( var i = 0; i < datas.length; i++) {
  	  	    		if(i > 1){
  	  	    			break;
  	  	    		}
  	  	    		html += '<li class="'+(datas[i].status == '0' ? 'do' : '')+'">';
  	  	    		html += '    <a href="javascript:;" class="wh100">';
  	  	    		html += '        <span class="descCon" title="'+datas[i].title+'">'+datas[i].title+'</span>';
  	  	    		html += '        <span class="type">'+new Date(datas[i].createTime).format("yyyy-MM-dd hh:mm")+'</span>';
  	  	    		html += '    </a>';
  	  	    		html += '</li>';
  	  	    	}
  	  	    	$(".gtask-list").html(html);
  	  	    }
  		   }else{
  			 layer.msg(gtaskDatas.errorMsg || '待办数据加载错误', {icon: 5});
  		   }
  	   }
 	});
    /**待办任务【end】*/

    /**到案方式统计【start】*/
    function weekInTypeCount(url){
    	var accoWayCountDatas = {};
    	$.ajax({
    		url: url,//json文件位置
    		cache:false,
    		type: "GET",//请求方式为get
    		async : false,
    		dataType: "json", //返回数据格式为json
    		success: function(datas) {
    			if(datas.success == true){
    				accoWayCountDatas = datas.content || {};
    				if(accoWayCountDatas.names)accoWayCountDatas.names = accoWayCountDatas.names.split(",");
    				if(accoWayCountDatas.datas)accoWayCountDatas.datas = accoWayCountDatas.datas.split(",");
    			}else{
    				layer.msg('到案方式统计数据加载错误', {icon: 5});
    			}
    		}
    	});
    	//初始化图表
    	myChart.setOption({
    			color: ['#3398DB'],
    			height: [80],

    			tooltip: {
    				trigger: 'axis',
    				axisPointer: { // 坐标轴指示器,坐标轴触发有效
    					type: 'line' // 默认为直线,可选为：'line' | 'shadow'
    				}
    			},

    			grid: {
    				left: '3%',
    				right: '4%',
    				top: '8%',
    				containLabel: true
    			},
    			xAxis: [{
    				type: 'category',
    				axisLabel: {
    					show: true,
    					textStyle: {
    						color: '#02aacf',
    						fontSize: '10'
    					}
    				},
    				data: accoWayCountDatas.names,
    				axisLine: {
    					lineStyle: {
    						color: '#02aacf',
    						width: 0.5, //这里是坐标轴的宽度,可以去掉
    					}
    				},
    				axisTick: {
    					alignWithLabel: true
    				}
    			}],
    			yAxis: [{
    				max: 'dataMax',
    				splitNumber : 3,
    				type: 'value',
    				splitLine: {
    					show: true,
    					lineStyle: {
    						color: '#1d3855',
    						width: 1
    					}
    				},
    				axisLine: {
    					lineStyle: {
    						color: '#02aacf',
    						width: 0.5, //这里是坐标轴的宽度
    					}
    				}
    			}],
    			series: [{
    				data: accoWayCountDatas.datas,
    				type: 'bar',
    				itemStyle : {
    					normal : {
    						color :function(params) {
    							　//首先定义一个数组
    							var colorList = [
    							'#EFE42A','#64BD3D','#EE9201','#29AAE3',
    							'#B74AE5','#0AAF9F','#E89589'
    							];
    							return colorList[params.dataIndex]
							},
    						label: {
    							show : true,
//    							position : 'top'
    						}
    					}
    				}
    			}]
    	})
    }
    /**到案方式统计【end】*/

	 /**人员身份类别统计【start】*/
    function weekPersonTypeCount(url){
    	var rankCount = {};
    	$.ajax({
    		url: url,//json文件位置
    		type: "GET",//请求方式为get
    		cache:false,
    		async : false,
    		dataType: "json", //返回数据格式为json
    		success: function(datas) {
    			if(datas.success == true){
    				rankCount = datas.content || {};
    				if(rankCount.names)rankCount.names = rankCount.names.split(",");
    				if(rankCount.datas)rankCount.datas = rankCount.datas.split(",");
    			}else{
    				layer.msg('人员身份类别统计数据加载错误', {icon: 5});
    			}
    		}
    	});

    	myChart14.setOption({
            height: [80],
            color: ['#02aacf', '#ffb400'],

            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                top: '8%',

                containLabel: true
            },

            xAxis: [{
                type: 'category',
                boundaryGap: true,
                axisLine: {
                    lineStyle: {
                        color: '#02aacf',
                        width: 0.1, //这里是坐标轴的宽度
                    }
                },
                data: rankCount.names
            }],
            yAxis: [{
				max: 'dataMax',
				splitNumber : 3,
                type: 'value',
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#1d3855',
                        width: 1
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#02aacf',
                        width: 0.5, //这里是坐标轴的宽度
                    }
                }
            }],
            series: [{
                	 data: rankCount.datas,
                	 type: 'line',
                     smooth: true, //这句就是让曲线变平滑的
                     itemStyle: {
                         normal: {
                        	 areaStyle: {
                        		 type: 'default'
                        	 },
                        	 color : 'orange',
                        	 label: {
                        		 show : true,
                        		 position : 'right',
                        		 color : 'white'
                        	 }
                         }
                     }
                 }
            ]
        })
    }
	 /**人员身份类别统计【end】*/

    /**最近7天办案区人数【start】*/
	var workingAreaNumberDatas = {};
	 $.ajax({
	  	   url: "/Index/getSevenDayCount.json",//json文件位置
	  	   type: "GET",//请求方式为get
	  	   cache:false,
	  	   async : false,
	  	   dataType: "json", //返回数据格式为json
	  	   success: function(datas) {
	  		   if(datas.success == true){
	  			   workingAreaNumberDatas = datas.content || {};
	  			   if(workingAreaNumberDatas.weeks)workingAreaNumberDatas.weeks = workingAreaNumberDatas.weeks.split(",");
	  			   if(workingAreaNumberDatas.datas)workingAreaNumberDatas.datas = workingAreaNumberDatas.datas.split(",");
	  		   }else{
	  			   layer.msg(enterOverTimeDatas.errorMsg || '最近7天办案区人数数据加载错误', {icon: 5});
	  		   }
	  	   }
	 	});
    /**最近7天办案区人数【end】*/

    var data = [];
    option15 = {
        height: [80],
        color: ['#02aacf'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器,坐标轴触发有效
                type: 'shadow' // 默认为直线,可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            top: '10%',

            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#02aacf',
                    width: 0.5, //这里是坐标轴的宽度
                }
            },
            data: workingAreaNumberDatas.weeks
        },
        yAxis: {
            type: 'value',
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#1d3855',
                    width: 1
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#02aacf',
                    width: 0.5, //这里是坐标轴的宽度
                }
            }
        },
        series: [{
            data: workingAreaNumberDatas.datas,
            type: 'line',
            itemStyle: {
                normal: {
                	color: "yellow",
					label: {
						show : true,
						position : 'right',
						color : 'white'
					}
                }
            }
        }]
    };
    myChart13.setOption(option15);

    //浏览器大小改变时重置大小
    window.addEventListener("resize", function () {
        myChart.resize();
        myChart13.resize();
        myChart14.resize();
    });
});