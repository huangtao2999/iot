
$(function (  ) {
  //高级搜索的样式
  $( "#leftBox .searchBox .selectImgBox" ).on( "click", function () {
    var disType = $( ".adSearchBox" ).css( "display" );
    if ( disType == "none" ) {
      $( ".adSearchBox" ).show();
      $( this ).find( ".upImg" ).show();
      $( this ).find( ".downImg" ).hide();
      $( this ).find( "span" ).hide();
			/*$(".adSearchBox").mouseover(function () {
			 $(this).show();
			 $(this).prev(".selectImgBox").find(".upImg").show();
			 $(this).prev(".selectImgBox").find(".downImg").hide();
			 $(this).prev(".selectImgBox").find("span").hide();
			 })
			 $(".adSearchBox").mouseout(function () {
			 $(this).hide();
			 $(this).prev(".selectImgBox").find(".upImg").hide();
			 $(this).prev(".selectImgBox").find(".downImg").show();
			 $(this).prev(".selectImgBox").find("span").show();
			 })*/
    } else {
      $( ".adSearchBox" ).hide();
      $( this ).find( ".upImg" ).hide();
      $( this ).find( ".downImg" ).show();
      $( this ).find( "span" ).show();
    }
  } );
});

/**
 * 表格组件
 * @param params 里面可以传$.extend({内定义的所有参数，具体参数请阅读注释。
 * @returns me
 * 伪例：见"登记查询页面"
 */
function iotTable(params){
	var me = $.extend({
		heads : [],//表格头部信息
		select : true,//是否可以多选，默认可以多选
		dataUrl : '',//请求数据的地址
		dataType:'json',//请求数据类型
		clickFirst : false,//是否默认点击第一条
		limit : 10,//每页显示条数
		page : 1,//第几页，不建议修改，初始化默认展示第一页
		dataCount : 0,//列表总数据
		pageCount : 1,//总页数
		queryFields : [],//查询条件
		baseQueryParams : {},//默认查询参数，queryParams中有同名参数时会被queryParams中的参数覆盖
		queryParams : {},//查询参数，不需要重写
		defaultHeadWidth : 10,//默认head宽度
		renderTo : '',//要渲染到哪里去,渲染到的位置必须有table-head、table-body、pageTool三个class，来定位列表需要的容器
		//初始化方法
		init : function(){
      $( me.renderTo ).find( ".table-head" ).parents('.titBox').hide();
			me.loadBody();
			me.bindPageTool();
			me.createQueryTool();
			me.addItemClick();
			me.setSelection();
		},
		loadBody : function(){
			/*-----创建body-----*/
			if(!me.dataUrl){
				layer.msg('创建表格时必须传入dataUrl属性', {icon: 5});
				return;
			}
			var waitIndex = wait();
			$.ajax({
                 type: "get",
                 url: me.dataUrl,
                 cache:false,
                 dataType: me.dataType,
                 data: $.extend({
                	 limit : me.limit,
                	 page : me.page
                 },me.baseQueryParams,me.queryParams),
                 success: function (datas) {
                	 $(me.renderTo).find(".table-body").html("");
                	me.dataCount = datas.count;
                 	if(datas && datas.data && datas.data.length > 0){
                 		for(var i = 0 ; i < datas.data.length ; i++){
                 			var thisData = datas.data[i];
                 			var existsPeople = false;
                 			var id = thisData.id;
                 			var title = "无人员信息记录";
                            var groupFaceFullUrl = "../images/lawContro/face_msrpot.png";
                            // var background = 'url(../images/lawContro/face_question.png) no-repeat scroll 50% 50%';
                            if(thisData.groupFaceFullUrl){
                                existsPeople = true;
                                groupFaceFullUrl=thisData.groupFaceFullUrl;
                                title = thisData.name+'/'+thisData.birthDate+'/'+getGridDict(thisData.sex, "SEX")+'/'+thisData.cardId+'/'+thisData.natives;
                            }
                 			
                 			var bodyHtml = '';
                 			bodyHtml += '<div class="perOnePhoto" data-exist="'+existsPeople+'" data-this-data=\''+JSON.stringify(thisData)+'\'>';
                 			bodyHtml += '     <span class="perDescBox">';
                 			bodyHtml += '         '+title;
                 			bodyHtml += '     </span>';
                 			bodyHtml += '     <div class="conBox clear">';
                 			bodyHtml += '         <div class="imgLeft">';
                 			bodyHtml += '             <img src="'+thisData.faceImageFullUrl+'" alt="" class="wh100">';
                 			bodyHtml += '         </div>';
                 			bodyHtml += '         <div class="mainBox">';
                 			bodyHtml += '             <div id="'+id+'" class="piBox" style="background: url(../images/lawContro/face_question.png) no-repeat scroll 50% 50%"></div>';
                 			bodyHtml += '             <span class="perDesc">'+thisData.faceGroup+'</span>';
                 			bodyHtml += '             <span class="timeDesc">'+new Date(parseInt(thisData.createTime)).format("yyyy-MM-dd hh:mm:ss")+'</span>';
                 			bodyHtml += '         </div>';
                 			bodyHtml += '         <div class="imgRight">';
                 			bodyHtml += '             <img src="'+groupFaceFullUrl+'" alt="" class="wh100">';
                 			bodyHtml += '         </div>';
                 			bodyHtml += '     </div>';
                 			bodyHtml += '    <div class="botBox clear">';
                 			bodyHtml += '         <img src="../images/lawContro/paizhao.png" alt="">';
                 			bodyHtml += '         <span style="cursor: pointer;" onclick=openVideoLeft('+JSON.stringify(thisData)+')>黄石市黄石港分局</span>';
                 			if(existsPeople){
                                bodyHtml += '        <div style="cursor: pointer;" onclick=openVideoRight('+JSON.stringify(thisData)+') class="radioBox clear">';
                                bodyHtml += '             <span>人员登记>></span>';
                                bodyHtml += '         </div>';
							}
                 			bodyHtml += '     </div>';
                 			bodyHtml += '</div>';
                 			$(me.renderTo).find(".table-body").append(bodyHtml);
                 			
                 			/*var piBox = document.getElementById(id);
                 			if(existsPeople){
                                var myChart = echarts.init(piBox);
                                option = {
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                                    },
                                    series: [{
                                        name: '相似度',
                                        type: 'pie',
                                        radius: ['50%', '70%'],
                                        avoidLabelOverlap: false,
                                        label: {
                                            normal: {
                                                show: false,
                                                position: 'center'
                                            },
                                            emphasis: {
                                                show: true,
                                                textStyle: {
                                                    fontSize: '14',
                                                    fontWeight: 'bold',
                                                    color: '#0cf5b6'
                                                }
                                            }
                                        },
                                        labelLine: {
                                            normal: {
                                                show: false
                                            }
                                        },
                                        data: [{
                                            value: (thisData.simScore*100).toFixed(2),
                                            name:  (thisData.simScore*100).toFixed(2)+ '%'
                                        },{
                                            value: (100-thisData.simScore*100).toFixed(2),
                                            name:  (100-(thisData.simScore*100)).toFixed(2)+ '%'
                                        }]
                                    }]
                                };
                                myChart.setOption(option);
                                
						        myChart.dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: 0});
						        
                                myChart.on('mouseover',function(v){
						            if( v.dataIndex != 0){
						                myChart.dispatchAction({type: 'downplay',seriesIndex: 0,dataIndex: 0});
						            }
						        });
						        
						        myChart.on('mouseout',function(v){
						                myChart.dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: 0});
						        });
						        
						        
                            }*/
                 		}
                 		
                 		$(me.renderTo).find(".table-body.perPhotoBox .perOnePhoto").each(function(index,dom){
                 			var exist=$(dom).data('exist');
                 			
                 			if(exist){
                 				
                 				var data=$(this).data('this-data');
                 				
                 				var myChart = echarts.init($('#'+data.id,this).get(0));
                                option = {
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                                    },
                                    series: [{
                                        name: '相似度',
                                        type: 'pie',
                                        radius: ['50%', '70%'],
                                        avoidLabelOverlap: false,
                                        label: {
                                            normal: {
                                                show: false,
                                                position: 'center'
                                            },
                                            emphasis: {
                                                show: true,
                                                textStyle: {
                                                    fontSize: '14',
                                                    fontWeight: 'bold',
                                                    color: '#0cf5b6'
                                                }
                                            }
                                        },
                                        labelLine: {
                                            normal: {
                                                show: false
                                            }
                                        },
                                        data: [{
                                            value: (data.simScore*100).toFixed(2),
                                            name:  (data.simScore*100).toFixed(2)+ '%'
                                        },{
                                            value: (100-data.simScore*100).toFixed(2),
                                            name:  (100-(data.simScore*100)).toFixed(2)+ '%'
                                        }]
                                    }]
                                };
                                myChart.setOption(option);
                                
						        myChart.dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: 0});
						        
                                myChart.on('mouseover',function(v){
						            if( v.dataIndex != 0){
						                myChart.dispatchAction({type: 'downplay',seriesIndex: 0,dataIndex: 0});
						            }
						        });
						        
						        myChart.on('mouseout',function(v){
						                myChart.dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: 0});
						        });
                 			
                 			}
                 			
                 		});
                 		
                 		waitOver(waitIndex);
                 		me.loadPage();

                 		//默认点击第一条
                 		if(me.clickFirst === true){
                 			$(".table-body li:first").click();
                 			me.clickFirst = false;
                 		}
                 	}else{
                 		waitOver(waitIndex);
                 		$(me.renderTo).find(".table-body").html("");
                 	}
                 }
			});
		},
		//创建分页栏
		loadPage : function(){
			$(me.renderTo).find(".pageTool").find(".page-num").html(me.page);
			$(me.renderTo).find(".pageTool").find(".page-count-data").html(me.dataCount);
			me.pageCount = me.dataCount % me.limit == 0 ? me.dataCount / me.limit : parseInt(me.dataCount / me.limit)+1;
			$(me.renderTo).find(".pageTool").find(".page-count").html(me.pageCount);
			$(".select-limit select").val(me.limit);
		},
		//绑定分页栏功能
		bindPageTool : function(){
			//首页
			$(me.renderTo).find(".pageTool").on("click",'#firstPage',function(){
				me.page = 1;
				me.loadBody();
			});
			//上一页
			$(me.renderTo).find(".pageTool").on("click",'#prePage',function(){
				if(me.page > 1){
					me.page--;
					me.loadBody();
				}
			});
			//下一页
			$(me.renderTo).find(".pageTool").on("click",'#nextPage',function(){
				if(me.page < me.pageCount){
					me.page++;
					me.loadBody();
				}
			});
			//尾页
			$(me.renderTo).find(".pageTool").on("click",'#lastPage',function(){
				me.page = me.pageCount;
				me.loadBody();
			});
			//选择每页条数
			$(".select-limit select").change(function(){
				var val = $(this).val() || me.limit;
				if(parseInt(val) != me.limit){
					me.limit = val;
					me.loadBody();
				}
			});
			//刷新
			$(me.renderTo).find(".pageTool").on("click",'#reload-data',function(){
				//me.loadBody();
				$(".searchBtnBox > div").triggerHandler('click');
			});
		},
		//生成查询栏
		createQueryTool : function(){
			if(me.queryFields.length > 0){
				var queryHtml = '';
				for(var i = 0 ; i < me.queryFields.length ; i++){
					queryHtml += '<div class="searBox nameId">';
					queryHtml += '	<span class="desc">'+me.queryFields[i].text+':</span>';
					if(me.queryFields[i].type == "select"){
						var dicCode = me.queryFields[i].dicCode;
						var dics = findDicItems(dicCode);
						var selectHtml = '';
						selectHtml += '<select name="'+me.queryFields[i].name+'">';
						selectHtml += '<option></option>';
						for(var j = 0 ; j < dics.length ; j++){
							selectHtml += '<option value="'+dics[j].code+'">'+dics[j].name+'</option>';
						}
						selectHtml += '</select>';
						queryHtml += selectHtml;
					}else if(me.queryFields[i].type == "date"){
                        queryHtml += '	<input name="'+me.queryFields[i].name+'" class="Wdate" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',readOnly:true})" >';
                    }
					else{
						queryHtml += '	<input type="text" name="'+me.queryFields[i].name+'">';
					}
					queryHtml += '</div>';
				}
				$(".searConBox").html(queryHtml);

				//查询按钮点击事件
				$(".searchBtnBox > div").click(function(){
					var queryParams = {};
					$(".searConBox").find("input").each(function(){
						var value = $(this).val();
						var name = $(this).attr("name");
						if(value && name){
							queryParams[name] = value;
						}
					});
					$(".searConBox").find("select").each(function(){
						var value = $(this).val();
						var name = $(this).attr("name");
						if(value && name){
							queryParams[name] = value;
						}
					});
					$(".radioBox .selImg").each(function(){
						var display = $(this).css("display");
						var name = $(this).parent().parent().parent().attr("name");
						var value = $(this).parent().parent().attr("value");
						if(display != "none"){
							queryParams[name] = value;
						}
					});
					me.queryParams = queryParams;
					me.page = 1;
					me.loadBody();
				});
			}else{
				$(".selectImgBox").hide();
			}
		},
   		//添加行点击事件
   		addItemClick : function(){
   			if($("#rightBox").length == 0){
   				return;
   			}
   			$(".table-body").on("dblclick","li",function(){
   				var loading = wait();
   				var id = $(this).data("id");
   				var res = PublicAjax('/PersonRegister/getPersonListClickVo.json', {'id': id});//获取数据
   				if(res.success){
   					var content = res.content;
   					var data = {
   						userData : content.showInfo,
   						//照片地址
   						photoUrl : '/FileUpload/getAttach.json?id=' + content.photoUrl,
   						//可点击的按钮code
   						btns : content.btns,
   						//已经走过的流程
   						doTask : content.doTask
   					};
   					//首先创建用户信息
   					if(data.userData && data.userData.length > 0){
   						var html = '';
   						for(var i = 0 ; i < data.userData.length ; i++){
   							if(i > 5){
   								break;
   							}
   							html += '<li>';
   							html += '    <div class="posRel wh100">';
   							html += '        <span class="posAbs mid desc">'+data.userData[i].name+':</span>';
   							html += '        <span class="nameTxt posAbs mid">'+data.userData[i].value+'</span>';
   							html += '    </div>';
   							html += '</li>';
   						}
   						$(".right-user-data").html(html);
   					}

   					//添加图片
   					$(".perHeadImg img").attr("src",data.photoUrl);

   					//设置哪些按钮可以点击，哪些不能
   					$(".right-top-btn>li").addClass("nvalid-btn");
   					if(data.btns && data.btns.length > 0){
   						for(var i = 0 ; i < data.btns.length ; i++){
   							$(".right-top-btn").find(".btn-"+data.btns[i]).removeClass("nvalid-btn");
   						}
   					}
   					//为所有按钮设置data-id属性
   					$(".right-top-btn span").attr("data-id",id);

   					//设置未走过的节点变灰
   					$(".timeLineCon .rightBox").addClass("untreated-task");
   					if(data.doTask && data.doTask.length > 0){
   						for(var i = 0 ; i < data.doTask.length ; i++){
   							$(".timeLineCon").find(".task-"+data.doTask[i].name).find(".leftBox span").html(data.doTask[i].value);
   							$(".timeLineCon").find(".task-"+data.doTask[i].name).find(".rightBox").removeClass("untreated-task");
   						}
   					}

   					waitOver(loading);
   				}else{
   					waitOver(loading);
   					layer.msg("访问人员数据错误");
   				}


   			});
   		},
		//设置列表选择事件
		setSelection : function(){
			//全选
			if(me.select === true){
				$("#leftBox .dataListBox .titBox .con .selBox img").click(function(){
					$("#leftBox .dataListBox .titBox .con .selBox img").hide();
					if($(this).hasClass("defImg")){
						$("#leftBox .dataListBox .titBox .con .selBox .selImg").show();
						$("#leftBox .dataListBox .listBox ul li").addClass("active");
					}else{
						$("#leftBox .dataListBox .titBox .con .selBox .defImg").show();
						$("#leftBox .dataListBox .listBox ul li").removeClass("active");
					}
				});
			}else{
				$("#leftBox .dataListBox .titBox .con .selBox img").hide();
			}

			//判断是否全部选中
			function checkAllSelect(){
				var selectAll = $("#leftBox .dataListBox .listBox ul li").length > 0 ? true : false;
				$("#leftBox .dataListBox .listBox ul li").each(function(){
					if(!$(this).hasClass("active")){
						selectAll = false;
					}
				});
				return selectAll;
			}

			//单行选中
			$("#leftBox .dataListBox .listBox ul").on("click",'li', function () {
				if(me.select === true){
		            if ($(this).hasClass("active")) {
		                $(this).removeClass("active");
		            } else {
		                $(this).addClass("active");
		            }
				}else{
					$(this).parent().find("li").removeClass("active");
	                $(this).addClass("active");
				}

				if(me.select === true){
					if(checkAllSelect()){
						$("#leftBox .dataListBox .titBox .con .selBox img").hide();
						$("#leftBox .dataListBox .titBox .con .selBox .selImg").show();
		            }else{
		            	$("#leftBox .dataListBox .titBox .con .selBox img").hide();
						$("#leftBox .dataListBox .titBox .con .selBox .defImg").show();
		            }
				}
	        });
		}
	},params || {});
	return me;
}

/**
 * 等待框
 * @retun 等待框的index，用于消除等待框
 */
function wait(){
	return layer.open({
		closeBtn : false,
		move : false,
		btn : [],
		title: '加载中',
		content: '<center>数据加载中，请稍后...<center>'
	});
}

/**
 * 消除等待框
 * @param index 等待框的index，用于消除等待框
 */
function waitOver(index){
	layer.close(index);
}
