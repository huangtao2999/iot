//页面的下一步上一步的页面切换方法
var indexPage = 1; //默认是第一个页面显示;

/**
 * 下一步
 */
function nextStep(){
    if (indexPage == 1) { //人员信息盒子隐藏;办案区信息盒子显示;
        $(".perMsgBox").hide();
        $(".lawCtrTabBox").hide();
        $(".workAreaBox").show();
        $(".otherConBox").hide();
        $(".btnBox .prevBox").show();
        $(".lawConTabBox .tabCon").eq(indexPage).addClass("active");
        $(".lawConTabBox .tabCon").eq(indexPage).siblings().removeClass("active");
        indexPage = 2;
    } else if (indexPage == 2) {
        $(".perMsgBox").hide();
        $(".workAreaBox").hide();
        $(".otherConBox").show();
        $(".btnBox .nextBox").hide();
        $(".lawConTabBox .tabCon").eq(indexPage).addClass("active");
        $(".lawConTabBox .tabCon").eq(indexPage).siblings().removeClass("active");
        indexPage = 3;
    }
}
/**
 * 绑定上一步
 */
function preStep(){
	if (indexPage == 2) { //人员信息盒子隐藏;办案区信息盒子显示;
		$(".lawCtrTabBox").show();
		$(".perMsgBox").show();
		$(".workAreaBox").hide();
		$(".otherConBox").hide();
		$(".btnBox .prevBox").hide();
		$(".btnBox .nextBox").show();
		$(".lawConTabBox .tabCon").eq(indexPage - 2).addClass("active");
		$(".lawConTabBox .tabCon").eq(indexPage - 2).siblings().removeClass("active");
		indexPage = 1;
	} else if (indexPage == 3) {
		$(".perMsgBox").hide();
		$(".workAreaBox").show();
		$(".otherConBox").hide();
		$(".btnBox .nextBox").show();
		$(".lawConTabBox .tabCon").eq(indexPage - 2).addClass("active");
		$(".lawConTabBox .tabCon").eq(indexPage - 2).siblings().removeClass("active");
		indexPage = 2;
	}
}
//$(".prevBox").on("click", function () {
//});

/**
 * 页面跳转
 * @param page int类型,必须传入数字。要跳转到的页码，1代表第一页，2代表第二页，3代表第三页，只有2、3有效，1看不出效果，超过3只会跳转到3
 */
function toPage(page, registerId){
//	var tt = wait();//等待框
	if(page <= 1 || page == "1"){
		//第一页
	}else if(page == 2 || page == "2"){
		indexPage = 1;
		nextStep();
	}else if(page >= 3 || page == "3"){
		indexPage = 2;
		nextStep();
	}
	//查询所有数据，并赋值
	var res = PublicAjax('/PersonRegister/getPersonVo.json', {'id' : registerId});
	if(res.success){
		var data = res.content;
		loadPerMsg(data.personInfo);//加载第一步
		loadWorkAreaBoxData(data.personInfo);//加载第二步----办案区信息
		if(data.goodsInfo){
			var goodsInfo = data.goodsInfo;
			$(".goods-list li").remove();//清空现有表格
			for(var i = 0; i<goodsInfo.length; i++){
				addPresonGoods(goodsInfo[i],".goods-list");//加载第二步---物品存放信息
			}
		}
		if(data.injuryInfo){
			var injuryInfo = data.injuryInfo;
			$(".injury-list li").remove();//清空现有表格
			for(var i = 0; i<injuryInfo.length; i++){
				addPresonGoods(injuryInfo[i],".injury-list");//加载第二步---伤情信息
			}
		}
		//自动分配等候室勾勾去掉
		$(".selectBox").find(".defImg").show();
		$(".selectBox").find(".selImg").hide();

	    loadOtherData(data);//加载第三步

	}else{
		layer.msg(res.content);
	}
}


/**
 * 是否分配等候室勾勾
 */
$(".selectBox").on("click", function () {
    var disType = $(this).find(".defImg").css("display");
    if (disType == 'block') {
        $(this).find(".defImg").hide();
        $(this).find(".selImg").show();
    } else {
        $(this).find(".defImg").show();
        $(this).find(".selImg").hide();
    }
});

/**
 * 柜子开关的点击
 */
$(".switchBox").on("click", function () {
    var swiType = $(this).find(".kaiImg").css("display");
    if (swiType == 'none') {
        $(this).find(".kaiImg").show();
        $(this).find(".guanImg").hide();
    } else {
        $(this).find(".kaiImg").hide();
        $(this).find(".guanImg").show();
    }

    //开柜
    var lockerId = $("#lockerId").val();
    if(lockerId){
    	var tt = wait();
    	var res = PublicAjax('/Locker/openLocker.json', {"id": lockerId,"type":"in"});
    	waitOver(tt);
    	if(res.success){
    		layer.msg(res.content);
    	}else{
    		layer.msg(res.content);
    	}
    }else{
    	layer.msg("未找到储物柜信息");
    }
    $(this).find(".kaiImg").show();
    $(this).find(".guanImg").hide();
});

/**
 * 选择柜子类型的change事件，自动查找可用柜子
 */
$("#box_size").change(function(){
	var type = $("#box_size").val() + '';
	if(type){
		var res = PublicAjax('/Locker/getFreeLocker.json', {"type": type});
		if(res.success){
			var data = res.content;
			if(data){
				//赋值
				$("#lockerId").val(data.id);
				$("#lockerNo").text(data.lockerNo);
			}else{
				$("#box_size").val("");
				$("#lockerId").val("");
				$("#lockerNo").text("");
			}
		}else{
			layer.msg(res.errorMsg);
			$("#box_size").val("");
			$("#lockerId").val("");
			$("#lockerNo").text("");
		}
	}else{
		$("#box_size").val("");
		$("#lockerId").val("");
		$("#lockerNo").text("");
	}
})

/**
 * 同案预警change事件，是的时候，要填警情编号
 */
$("#isWarned").change(function(){
	var type = $(this).val() + '';
	var alarm = $("#alarmNumber").parent().parent().parent().find('.mandatoryImgBox');
	if(type == "1"){
		if($(alarm).hasClass("hideManda")){
			$(alarm).removeClass("hideManda");
		}
	}else{
		if(!$(alarm).hasClass("hideManda")){
			$(alarm).addClass("hideManda");
		}
	}
})


/**
 * 加载人员基本信息 + 隐藏控件的信息
 * @param data 要加载的数据
 */
function loadPerMsg(data){
	if(data){
		//日期控件格式化
		if(data.birthDate){
			data.birthDate = formatDate(data.birthDate);
		}
		if(data.inTime){
			$("#inTime").val(formatTime(data.inTime));//入区时间
		}
		$(".perMsgBox .infoOne input,.perMsgBox .infoOne select,.hideInputs input").each(function(){
			var name = $(this).attr("name");
			if(name && data[name]){
				$(this).val(data[name]);
			}
		});
		//加载照片
		var personImgIds = data.personImgIds;
		if(personImgIds){
			$("#faceImg").prop("src","/FileUpload/getAttach.json?id=" + personImgIds);//显示头像
			$("#personImgIds").val(personImgIds);
		}

	}
}

/**
 * 获取人员类型
 */
function getPersonType(){
	$("#peopleType").val($(".person-type li.active").find("span").attr("value"));
}

/**
 * 获取身份是否明确类型
 */
function getIdentyType(){
	$("#unknownPerson").val($(".IdentyBox ul li.active").find("span").attr("value"));
}

/**
 * 获取人员基本信息的表单数据
 * 包括隐藏控件的值
 */
function getPerMsgData(){
	var data = {};

	getPersonType();//获取人员类型
	getIdentyType();//获取身份是否明确类型

	$(".perMsgBox .infoOne input,.perMsgBox .infoOne select,.hideInputs input").each(function(){
		var name = $(this).attr("name");
		if(name){
			data[name] = $(this).val();
		}
	});
	return data;
}

/**
 * 加载人员基本信息是否必填
 * @param must 是否必填
 */
function PerMsgNotNull(must){
	$(".perMsgBox .mandatoryImgBox").each(function(){
		//该标签没有必填，但是需要必填
		if(!$(this).hasClass("hideManda") && must === false){
			if(!$(this).hasClass("needHideManda")){
				$(this).addClass("needHideManda");
			}
		}

		if($(this).hasClass("needHideManda") && must === true){
			$(this).removeClass("needHideManda");
		}
	});
	//姓名必填
	if($("#no_three").hasClass("hideManda")){
		$("#no_three").removeClass("hideManda");
	}
	if($("#no_three").hasClass("needHideManda")){
		$("#no_three").removeClass("needHideManda");
	}

}

/**
 * 第一步提交前判断
 */
function stepOnePreCheck(){
	var flag = true;
	$(".perMsgBox .mandatoryImgBox").each(function(){
		//该标签没有必填，但是需要必填
		if(!$(this).hasClass("hideManda") && !$(this).hasClass("needHideManda") ){
			var elemSpan = $(this).parent().find("span").text().substring(0,8);
			var elemVal = $(this).parent().find("input").val() || $(this).parent().find("select").val();
			if(!elemVal){
				flag = false;
				layer.closeAll('loading');
				layer.msg("请补充必填项：" + elemSpan);
				return false;
			}
		}
	});
	return flag;
}
/**
 * 第一页点击完成或下一步操作
 */
function submitStepOne(){
	//提交前判断
	if(!stepOnePreCheck()){
		return false;
	}

	//遍历数据，拼接成对象，包括隐藏的那些input
	var data = getPerMsgData();
	var res = PublicAjax('/PersonRegister/savePerson.json', data);//提交数据
	if(res.success){
		var content = res.content;//返回整个人员对象，包括各种信息
		var pInfo = content.personInfo;
		//赋值基础信息
		loadPerMsg(pInfo);
		layer.msg("信息保存完成");
		return "信息保存完成";
	}else{
		layer.msg(res.errorMsg);
		return false;
	}
}

/**
 * 第二步获取存放物品信息和伤情信息集合
 */
function getItemsData(cls){
	var columIsc= $(cls+" #columIscBox li");
	var datas = [];

	if(columIsc.length > 0){
		columIsc.each(function(){
			var ids = $(this).find(".pics").data("imgs");
			//判断是那种数据，拼接
			if(cls.indexOf("goods") >= 0){
				//物品信息
				datas.push({
					goodsName : $(this).find("input[name=goodsName]").val(),
					goodsDesc : $(this).find("input[name=goodsDesc]").val(),
					sourceIds : ids,
					id : $(this).find("input[name=hide-id]").val(),
					registerId : $("#id").val(),
					removeIds : delGoods
				});
			}else if(cls.indexOf("injury") >= 0){
				//伤情信息
				datas.push({
					part : $(this).find("input[name=goodsName]").val(),
					remark : $(this).find("input[name=goodsDesc]").val(),
					sourceIds : ids,
					id : $(this).find("input[name=hide-id]").val(),
					registerId : $("#id").val(),
					removeIds : delInjury
				});
			}
		});
	}

	return datas;
}
/**
 * 获取办案区信息
 */
function getWorkAreaBoxData(){
	//获取办案区信息
	var datas = {};
	$(".workAreaBox .infoExBox .infoOne input,.workAreaBox .infoExBox .infoOne select,.hideInputs input,.workAreaBox .textAreaBox textarea").each(function(){
		var name = $(this).attr("name");
		if(name){
			datas[name] = $(this).val();
		}
	});
	datas.personStatus = "1";//进了办案区
	datas.lockerId = $("#lockerId").val();//储物柜id
	datas.lockerNo = $("#lockerNo").html();//储物柜编号
	datas.doCheck = $("#doCheck").val();//是否核查比对
	//采集项目
	var collectProject = "";
	$(".workAreaBox .checkOneBox.active span").each(function(){
		if(!collectProject){
			collectProject += $(this).html();
		}else{
			collectProject += "," + $(this).html();
		}
	});
	datas.collectProject = collectProject;
	$("#collectProject").val(collectProject);
	return datas;
}
/**
 * 加载办案区信息
 */
function loadWorkAreaBoxData(data){
	if(data){
		//日期控件格式化
		if(data.inTime){
			$("#inTime").val(formatTime(data.inTime));//入区时间
		}
		//加载办案区信息
		var datas = {};
		$(".workAreaBox .infoExBox .infoOne input,.workAreaBox .infoExBox .infoOne select,.workAreaBox .textAreaBox textarea").each(function(){
			var name = $(this).attr("name");
			if(name && data[name]){
				$(this).val(data[name]);
			}
		});
		$("#lockerId").val(data.lockerId);//储物柜id
		$("#lockerNo").html(data.lockerNo);//储物柜编号
		//采集项目
		var collectProject = $("#collectProject").val() || data.collectProject;
		$(".workAreaBox .checkOneBox span").each(function(){
			if(collectProject.indexOf($(this).html()) > -1){
				$(this).parent().parent().addClass("active");
			}
		});
		//是否同案预警
		var alarmType = data.isWarned;
		var alarm = $("#alarmNumber").parent().parent().parent().find('.mandatoryImgBox');
		if(alarmType == "1"){
			if($(alarm).hasClass("hideManda")){
				$(alarm).removeClass("hideManda");
			}
		}
	}
}
/**
 * 第二步提交前检查
 */
function stepTwoPreCheck(){
	var flag = true;
	$(".areaBox .mandatoryImgBox").each(function(){
		//该标签没有必填，但是需要必填
		if(!$(this).hasClass("hideManda") && !$(this).hasClass("needHideManda") ){
			var elemSpan = $(this).parent().find("span").text().substring(0,8);
			var elemVal = $(this).parent().find("input").val() || $(this).parent().find("select").val();
			if(!elemVal){
				flag = false;
				layer.closeAll('loading');
				layer.msg("请补充必填项：" + elemSpan);
				return false;
			}
		}
	});
	//判断胸牌，手环编号不能重复
	var card1 = $("#hostPoliceCardNo").val();
	var card2 = $("#joinPoliceCardNo").val();
	var card3 = $("#braceletNo").val();
	if(card1 && card2){
		if(card1 == card2 || card1 == card3 || card2 == card3){
			flag = false;
			layer.closeAll('loading');
			layer.msg("主办胸牌、协办胸牌、手环编号不能相同");
		}
	}
	if(card3){
		if(card1 == card3 || card2 == card3){
			flag = false;
			layer.closeAll('loading');
			layer.msg("主办胸牌、协办胸牌、手环编号不能相同");
		}
	}

	return flag;
}
/**
 * 第二页点击完成或下一步
 */
function submitStepTwo(){
	var flag = true;
	//提交前判断!
	if(!stepTwoPreCheck()){
		return false;
	}

    //获取存放物品/伤情信息
    var goodsInfo = getItemsData(".goods-list");
    var injuryInfo = getItemsData(".injury-list");
    var data = getWorkAreaBoxData();
    data.injuryInfo = injuryInfo;
    data.goodsInfo = goodsInfo;
	var res = PublicAjaxToJson('/PersonRegister/updatePerson.json', data);//提交数据
	if(res.success){
		//判断是否打勾了自动分配等候室
		var autoWaitFlag = $(".selectBox").find(".defImg").css("display");
		if(autoWaitFlag != "block"){
			var content = res.content;//返回整个人员对象，包括各种信息
			var personInfo = content.personInfo;//获取人员信息
			var registerId = personInfo.id;
			//分配等候室
			var res2 = PublicAjax('/PersonRegister/autoSetWaitingRoom.json', {'id' :registerId});

			//重新加载物品和伤情数据
			if(content.goodsInfo){
				var goodsInfo = content.goodsInfo;
				$(".goods-list .itemsInfoMainBox li").remove();//清空现有表格
				for(var i = 0; i<goodsInfo.length; i++){
					addPresonGoods(goodsInfo[i],".goods-list");//加载第二步---物品存放信息
				}
			}
			if(content.injuryInfo){
				var injuryInfo = content.injuryInfo;
				$(".injury-list .itemsInfoMainBox li").remove();//清空现有表格
				for(var i = 0; i<injuryInfo.length; i++){
					addPresonGoods(injuryInfo[i],".injury-list");//加载第二步---伤情信息
				}
			}

			return res2.content || res2.errorMsg || "";
		}else{
			var content = res.content;
			//重新加载物品和伤情数据
			if(content.goodsInfo){
				var goodsInfo = content.goodsInfo;
				$(".goods-list .itemsInfoMainBox li").remove();//清空现有表格
				for(var i = 0; i<goodsInfo.length; i++){
					addPresonGoods(goodsInfo[i],".goods-list");//加载第二步---物品存放信息
				}
			}
			if(content.injuryInfo){
				var injuryInfo = content.injuryInfo;
				$(".injury-list .itemsInfoMainBox li").remove();//清空现有表格
				for(var i = 0; i<injuryInfo.length; i++){
					addPresonGoods(injuryInfo[i],".injury-list");//加载第二步---伤情信息
				}
			}

			return "信息保存完成";
		}
	}else{
		layer.msg(res.errorMsg);
		return false;
	}
}


/**
 * 获取第三页的其他信息
 */
function getOtherData(){
	var data = {};
	data.id = $("#id").val();

	//获取抓获信息
	var catchInfo = {};
	$(".otherConBox .infoExBox .infoOne input,.otherConBox .infoExBox .infoOne select").each(function(){
		var name = $(this).attr("name");
		if(name){
			catchInfo[name] = $(this).val();
		}
	});
	data.catchInfo = catchInfo;

	var relatedInfo = [];

	//获取数据
	function getdatas(cls){
		//获取性别
		function getSex(){
			return $(cls+" .sexBox .active span").html();
		}

		var datas = {};
		$(cls+" .infoOne input,"+cls+" .infoOne select").each(function(){
			var name = $(this).attr("name");
			if(name){
				datas[name] = $(this).val();
			}
		});
		datas.sex =  getSex();
		return datas;
	}

	var relatedInfo = [];

	//获取监护人信息
	var guardianData = getdatas(".guardian");
	if(guardianData.name){
		guardianData.type = "1";
		relatedInfo.push(guardianData);
	}

	//获取翻译人信息
	var translator = getdatas(".translator");
	if(translator.name){
		translator.type = "2";
		relatedInfo.push(translator);
	}

	//获取社区人员信息
	var comwork = getdatas(".comwork");
	if(comwork.name){
		comwork.type = "3";
		relatedInfo.push(comwork);
	}

	data.relatedInfo = relatedInfo;

	return data;
}

/**
 * 加载第三页的其他信息
 */
function loadOtherData(data){
	if(data){
		//加载抓捕信息
		if(data.catchInfo){
			//日期控件格式化
			if(data.catchInfo.catchTime){
				data.catchInfo.catchTime = formatDate(data.catchInfo.catchTime);
			}
			$(".otherConBox .infoExBox .infoOne input,.otherConBox .infoExBox .infoOne select").each(function(){
				var name = $(this).attr("name");
				if(name && data.catchInfo[name]){
					$(this).val(data.catchInfo[name]);
				}
			});
		}

		//加载数据
		function loaddatas(cls,accoData){
			if(accoData){
				$(cls+" .sexBox .sexWoanBox").removeClass("active");
				$(cls+" .sexBox .sexManBox").removeClass("active");
				if(accoData.sex == "女"){
					$(cls+" .sexBox .sexWoanBox").addClass("active");
				}else{
					$(cls+" .sexBox .sexManBox").addClass("active");
				}

				var datas = {};
				$(cls+" .infoOne input,"+cls+" .infoOne select").each(function(){
					var name = $(this).attr("name");
					if(name && accoData[name]){
						$(this).val(accoData[name]);
					}
				});
			}
		}
		if(data.relatedInfo){
			var relatedInfo = data.relatedInfo;
			for(var n = 0; n<relatedInfo.length; n++){
				if(relatedInfo[n].type == "1"){
					//加载监护人信息
					loaddatas(".guardian",relatedInfo[n]);
				}else if(relatedInfo[n].type == "2"){
					//加载翻译人信息
					loaddatas(".translator",relatedInfo[n]);
				}else if(relatedInfo[n].type == "3"){
					//加载监护人信息
					loaddatas(".comwork",relatedInfo[n]);
				}
			}
		}
	}
}

/**
 * 第三步提交判断
 */
function stepThreePreCheck(){
	var flag = true;

	//判断陪同人的胸牌号不能相同
	var chip1 = $("#chipNo2").val();//监护人
	var chip2 = $("#chipNo3").val();//翻译人
	var chip3 = $("#chipNo4").val();//社区人

	var card1 = $("#hostPoliceCardNo").val();
	var card2 = $("#joinPoliceCardNo").val();
	var card3 = $("#braceletNo").val();

	if(chip1){
		if(chip1 == card1 || chip1 == card2 || chip1 == card3 || chip1 == chip2 || chip1 == chip3){
			flag = false;
			layer.closeAll('loading');
			layer.msg("该胸牌号已被占用");
		}
	}
	if(chip2){
		if(chip2 == card1 || chip2 == card2 || chip2 == card3 || chip1 == chip2 || chip2 == chip3){
			flag = false;
			layer.closeAll('loading');
			layer.msg("该胸牌号已被占用");
		}
	}
	if(chip3){
		if(chip3 == card1 || chip3 == card2 || chip3 == card3 || chip3 == chip2 || chip1 == chip3){
			flag = false;
			layer.closeAll('loading');
			layer.msg("该胸牌号已被占用");
		}
	}

	return flag;
}

/**
 * 第三步点击完成，保存抓获信息和陪同人信息
 */
function submitStepThree(){
	var flag = true;
	//提交前判断!
	if(!stepThreePreCheck()){
		return false;
	}

	var data = getOtherData();
	var res = PublicAjaxToJson('/PersonRegister/saveStepThree.json', data);//提交数据
	if(res.success){
		var content = res.content;//返回整个人员对象，包括各种信息
		return "信息保存完成";
	}else{
		flag = false;
		layer.msg(res.errorMsg);
	}
	return flag;
}

//关闭弹出框
function closeDialog(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.table.loadBody();//刷新列表数据
    parent.layer.close(index);//关闭弹窗
}

/**
 * 绑定完成按钮事件 + 下一步按钮事件
 */
function bindFinishBtn(){
	//绑定完成按钮事件
	$(".keepBox").on('click',function(){
		var step = $(".lawConTabBox>div.active").find("span").attr("value");//找到是第几步
		var tt = wait();
		if(step == "01"){
			//第一页点击完成
			var msg = submitStepOne();
			waitOver(tt);
			if(msg){
				layer.alert(msg, function(index){
					layer.close(index);
					closeDialog();
				})
			}
		}else if(step == "02"){
			//第二页点击完成
			var msg = submitStepTwo();
			waitOver(tt);
			if(msg){
				layer.alert(msg, function(index){
					layer.close(index);
					closeDialog();
				})
			}
		}else if(step == "03"){
			//第三页点击完成
			var msg = submitStepThree();
			waitOver(tt);
			if(msg){
				layer.alert(msg, function(index){
					layer.close(index);
					closeDialog();
				})
			}
		}else{
			waitOver(tt);
			layer.msg("流程出错，请刷新重试");
		}
	})

	//绑定下一步点击事件
	$(".nextBox").on('click',function(){
		var step = $(".lawConTabBox>div.active").find("span").attr("value");//找到是第几步
		var tt = wait();
		if(step == "01"){
			//第一页点击下一步
			if(submitStepOne()){
				waitOver(tt);
				nextStep();
			}
		}else if(step == "02"){
			//第二页点击下一步
			var msg = submitStepTwo();
			waitOver(tt);
			if(msg){
				layer.alert(msg, function(index){
					layer.close(index);
					nextStep();
				})
			}
		}else{
			waitOver(tt);
			layer.msg("流程出错，请刷新重试");
		}
	})

	//绑定上一步点击事件
	$(".prevBox").on('click',function(){
		var step = $(".lawConTabBox>div.active").find("span").attr("value");//找到是第几步
		var tt = wait();
		if(step == "01"){
		}else if(step == "02"){
			//第二页点击上一步
			var msg = submitStepTwo();
			waitOver(tt);
			if(msg){
				layer.alert(msg, function(index){
					layer.close(index);
					preStep();
				})
			}
		}else if(step == "03"){
			//第三页点上一步
			var msg = submitStepThree();
			waitOver(tt);
			if(msg){
				layer.msg(msg);
				preStep();
			}
		}else{
			waitOver(tt);
			layer.msg("流程出错，请刷新重试");
		}
	})
}


var personWaitNumber = wait();
/**
 * 主函数
 */
$(function(){
	//点击身份是否明确，来改变页面的是否必填
    $(".IdentyBox ul li").on("click", function () {
        PerMsgNotNull(!$(this).hasClass("check-not-null"));
    })

    //绑定完成按钮事件
    bindFinishBtn();

	waitOver(personWaitNumber);
});