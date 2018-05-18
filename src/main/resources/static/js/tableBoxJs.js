$(document).ready(function () {
    //球旋转方法
    $("#rightBox .timeLineBox .timeLineCon li .rightBox .circleBox img").addClass("rotate1");

    //右边二级菜单方法
    $("#rightBox .timeLineBox .timeLineCon li .rightBox").on("click", function () {
    	if($(this).hasClass("untreated-task")){
    		return;
    	}
    	if($(this).hasClass("only-watch-task")){
    		return;
    	}
        var type = $(this).find(".diaBox").css("display");
        if (type == 'none') {
            $(this).find(".diaBox").show();
            $(this).parent("li").siblings().find(".diaBox").hide();
        } else {
            $(this).find(".diaBox").hide();
        }
    });


    //右边的8个菜单点击样式

    $("#rightBox .listBox ul li").on("click", function () {
        var className = $(this).attr("class");
        if (className.indexOf('nvalid-btn') < 0) {
            // alert('添加');
            $(this).addClass("active").siblings().removeClass("active");
        }
    })
    //右边列表的样式方法

   /*$("#rightBox .listBox ul li").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    });*/

    //单选样式方法

    $("#leftBox .searchBox .radioBox ul li").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    });

    //高级搜索的样式
    $("#leftBox .searchBox .selectImgBox").on("click", function () {
        var disType = $(".adSearchBox").css("display");
        if (disType == "none") {
            $(".adSearchBox").show();
            $(this).find(".upImg").show();
            $(this).find(".downImg").hide();
            $(this).find("span").hide();
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
            $(".adSearchBox").hide();
            $(this).find(".upImg").hide();
            $(this).find(".downImg").show();
            $(this).find("span").show();
        }
    })

    //grid列表的宽度设置
    var lenArr = [];
    var widthArr = [];
    var width = $(".dataListBox").width();
    $(".dataListBox .titOne").each(function (index, item) {
        var len = parseInt($(item).attr("data-len"));
        lenArr.push(len);
    })
    for (var i = 0; i < lenArr.length; i++) {
        $(".dataListBox .titOne").eq(i).width(lenArr[i] / 101 * width + 'px');
    }
    $(".dataListBox .titOne").each(function (index, item) {
        var widthOne = $(item).width();
        widthArr.push(widthOne);
    })
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
			if(me.heads.length > 0){
				/*-----创建head-----*/
				var headHtml = '';
				headHtml += '<div class="selBox titOne" style="width: 5%">';
				headHtml += '	<div class="posRel wh100">';
				headHtml += '        <img src="../images/perRegis/renyuandengji_tubiao_sel.png" alt="" class="selImg posAbs cenMid">';
				// headHtml += '        <img src="/guankong/Public/images/custom/renyuandengji_tubiao_sel.png" alt="" class="selImg posAbs cenMid">';
				headHtml += '        <img src="../images/perRegis/renyuandengji_tubiao_def.png" alt="" class="defImg posAbs cenMid">';
				// headHtml += '        <img src="/guankong/Public/images/custom/renyuandengji_tubiao_def.png" alt="" class="defImg posAbs cenMid">';
				headHtml += '    </div>';
				headHtml += '</div>';
				//序号头部
				headHtml += '<div class="titOne" style="width: 5%">';
				headHtml += '    <span></span>';
				headHtml += '</div>';

				for(var i = 0 ; i < me.heads.length ; i++){
					if(!me.heads[i].name || !me.heads[i].width || !me.heads[i].text){
						layer.msg('创建表格时传入的heads中，name、width、text三个属性为必要属性', {icon: 5});
						return;
					}
					headHtml += '<div class="titOne" style="width: '+(me.heads[i].width || me.defaultHeadWidth)+'%">';
					var text = me.heads[i].text;
					headHtml += '    <span>' + text + '</span>';
					headHtml += '</div>';
				}

				$(me.renderTo).find(".table-head").append(headHtml);
				me.loadBody();
				me.bindPageTool();
				me.createQueryTool();
				me.addItemClick();
				me.setSelection();

			}else{
				layer.msg('创建表格时必须传入heads属性', {icon: 5});
				return;
			}
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
                	me.dataCount = datas.count;
                 	if(datas && datas.data && datas.data.length > 0){
                 		var bodyHtml = '';
                 		for(var i = 0 ; i < datas.data.length ; i++){
                 			var rowHtml = '';
                 			var rowCls = '';//本行的其他class

                 			//多选
                 			rowHtml += '<div class="titOne" style="width: 5%">';
                 			rowHtml += '	<div class="posRel wh100">';
                      rowHtml += '        <img src="../images/perRegis/renyuandengji_tubiao_sel.png" alt="" class="selImg posAbs cenMid">';
                      // rowHtml += '        <img src="/guankong/Public/images/custom/renyuandengji_tubiao_sel.png" alt="" class="selImg posAbs cenMid">';
                      rowHtml += '        <img src="../images/perRegis/renyuandengji_tubiao_def.png" alt="" class="defImg posAbs cenMid">';
                      // rowHtml += '        <img src="/guankong/Public/images/custom/renyuandengji_tubiao_def.png" alt="" class="defImg posAbs cenMid">';
                 			rowHtml += '	</div>';
                 			rowHtml += '</div>';

                 			//序号
                 			rowHtml += '<div class="titOne" style="width: 5%">';
                 			rowHtml += '	<span class="">' + (me.limit*(me.page-1)+i+1) + '</span>';
                 			rowHtml += '</div>';
                 			for(var j = 0 ; j < me.heads.length ; j++){
                 				var dicCode = me.heads[j].dicCode;//字典编码
                 				var orgCode = me.heads[j].orgCode;//组织机构编码
                 				var text = datas.data[i][me.heads[j].name];//文本信息
                 				var dateFormat = me.heads[j].dateFormat;//日期格式
                 				var html = me.heads[j].html;//html内容
                 				var render = me.heads[j].render;//渲染方法
                 				var renderHtml = me.heads[j].renderHtml;//渲染html的方法
                 				var title = '';

                 				var val = '';
                 				//如果填写了html内容，则直接取html的内容，不取值
                 				if(html){
                 					val = html;
                 				}else{
                 					text = (text == null ? '' : text);
                 					val = dicCode ? getGridDict(text, dicCode) : text;//如果有dicCode,就按照字典翻译
                 					val = orgCode ? getOrgName(text, orgCode) : val;//如果有orgCode,就按照字典翻译
                         			val = dateFormat ? new Date(val).format(dateFormat) : val;//如果有日期格式，则按照日期格式格式化
                         			title = ' title="'+val+'" ';
                 				}

                 				//如果render有值且是函数，就调用该函数,最终反悔的是需要额外添加的class
                 				if(typeof render == "function"){
                 					rowCls = render(val,text) || '';
                 				}

                 				var newHtml = "";
                 				if(typeof renderHtml == "function"){
                 					newHtml = renderHtml(val,text);
                 				}

                 				if(newHtml){
                 					rowHtml += '<div class="titOne" style="width: '+(me.heads[j].width || me.defaultHeadWidth)+'%">';
                         			rowHtml += '	<span class="">' + newHtml + '</span>';
                         			rowHtml += '</div>';
                 				}else{
                 					rowHtml += '<div class="titOne" style="width: '+(me.heads[j].width || me.defaultHeadWidth)+'%">';
                         			rowHtml += '	<span class="" '+title+'>' + val + '</span>';
                         			rowHtml += '</div>';
                 				}
                 			}
                 			rowHtml = '<li class="body-data-list '+rowCls+'" data-id="'+datas.data[i].id+'">' + rowHtml + '</li>';
                 			bodyHtml += rowHtml;
                 		}
                        waitOver(waitIndex);
                 		$(me.renderTo).find(".table-body").html(bodyHtml);
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
                 },
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
					}else{
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
				//查询按钮中的input元素绑定 enter事件  huangt TODO；
                $(".adSearchBox").on("keydown","input",function(e){
                    var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
                    if (eCode == 13){
                        $(".searchBtnBox > div").triggerHandler('click');
                    }
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
   			// $(".table-body").on("dblclick","li",function(){
   			// 	var loading = wait();
   			// 	var id = $(this).data("id");
   			// 	var res = PublicAjax('/PersonRegister/getPersonListClickVo.json', {'id': id});//获取数据
   			// 	if(res.success){
   			// 		var content = res.content;
   			// 		var data = {
   			// 			userData : content.showInfo,
   			// 			//照片地址
   			// 			photoUrl : '/FileUpload/getAttach.json?id=' + content.photoUrl,
   			// 			//可点击的按钮code
   			// 			btns : content.btns,
   			// 			//已经走过的流程
   			// 			doTask : content.doTask
   			// 		};
   			// 		//首先创建用户信息
   			// 		if(data.userData && data.userData.length > 0){
   			// 			var html = '';
   			// 			for(var i = 0 ; i < data.userData.length ; i++){
   			// 				if(i > 5){
   			// 					break;
   			// 				}
   			// 				html += '<li>';
   			// 				html += '    <div class="posRel wh100">';
   			// 				html += '        <span class="posAbs mid desc">'+data.userData[i].name+':</span>';
   			// 				html += '        <span class="nameTxt posAbs mid">'+data.userData[i].value+'</span>';
   			// 				html += '    </div>';
   			// 				html += '</li>';
   			// 			}
   			// 			$(".right-user-data").html(html);
   			// 		}
               //
   			// 		//添加图片
   			// 		$(".perHeadImg img").attr("src",data.photoUrl);
               //
   			// 		//设置哪些按钮可以点击，哪些不能
   			// 		$(".right-top-btn>li").addClass("nvalid-btn");
   			// 		if(data.btns && data.btns.length > 0){
   			// 			for(var i = 0 ; i < data.btns.length ; i++){
   			// 				$(".right-top-btn").find(".btn-"+data.btns[i]).removeClass("nvalid-btn");
   			// 			}
   			// 		}
   			// 		//为所有按钮设置data-id属性
   			// 		$(".right-top-btn span").attr("data-id",id);
               //
   			// 		//设置未走过的节点变灰
   			// 		$(".timeLineCon .rightBox").addClass("untreated-task");
   			// 		if(data.doTask && data.doTask.length > 0){
   			// 			for(var i = 0 ; i < data.doTask.length ; i++){
   			// 				$(".timeLineCon").find(".task-"+data.doTask[i].name).find(".leftBox span").html(data.doTask[i].value);
   			// 				if(data.doTask.canClick){
   			// 					$(".timeLineCon").find(".task-"+data.doTask[i].name).find(".rightBox").removeClass("untreated-task");
   			// 				}
   			// 			}
   			// 		}
               //
   			// 		$("#registerId").val(id);
                //     $("#personStatus").val(content.personStatus);
   			// 		waitOver(loading);
   			// 	}else{
   			// 		waitOver(loading);
   			// 		layer.msg("访问人员数据错误");
   			// 	}
               //
               //
   			// });
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
function wait(msg){
	msg=msg||'数据加载中，请稍后...';
//	return layer.open({
//		closeBtn : false,
//		move : false,
//		btn : [],
//		title: '加载中',
//		content: '<center>数据加载中，请稍后...<center>'
//	});
	return layer.open({
		closeBtn : false,
		move : false,
		btn : [],
		title: false,
		time:5000,
		content: '<center>'+msg+'<center>'
	});
}

/**
 * 消除等待框
 * @param index 等待框的index，用于消除等待框
 */
function waitOver(index){
	layer.close(index);
}
