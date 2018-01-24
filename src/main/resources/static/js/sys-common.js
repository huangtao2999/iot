/**
 * 说明：通用JS抽取
 * author：WS
 */



/**
 * 删除
 * @param url
 * @param trId:可选参数
 * 
 */
function doDelete(url,trId){
	
	if(typeof(trId) == 'undefined'){//如果找不到trId
		trId = getId('delete');
		if(trId==false){
			return;
		}
	}

    //执行删除操作
	if(confirm("确定删除吗？")){
		$.ajax({
		url:url,
		data:{'fIds':trId},
		dataType:'json',
		type:'post',
		success:function(data){
			if(data>0){
				alert('删除成功！');
				location.reload();
			}else{
				alert('删除失败！');
			}
		},
		error:function(){
			alert('系统错误！');
		}
	    });
    }
	
}


 /**
 * 勾选已选择ID
 */
function getId(msg){
	var count = 0;
	var trId = '';
	var chks = $('input[name="chk"]');
	for ( var i = 0; i < chks.length; i++) {
		if($(chks[i]).attr('checked')){
			//alert($(chks[i]).attr('checked'));
			count++;
			trId += $(chks[i]).parent().parent().attr('id')+",";//获取tr的id，即数据id
		}
	}
	if(msg=='update'){
		if(count==0){
			alert('请选择操作项！');
			return false;
		}else if(count>1){
			alert('只能选择一个操作项！');
			return false;
		}else{
			return trId.substring(0, trId.length-1);
		}
	}else if(msg=='delete'){
		if(count==0){
			alert('请选择操作项！');
			return false;
		}else{
			return trId.substring(0, trId.length-1);
		}
	}
	
}


/**
 * JS 取系统当前时间 如： 2014-08-20
 */
function getNowDate(){
	var md=new Date();
	var yyyy=md.getFullYear();
	var MM=(md.getMonth()+1)<10?"0"+(md.getMonth()+1):(md.getMonth()+1);
	var DD=md.getDate()<10?"0"+md.getDate():md.getDate();
	var nowDate=yyyy+"-"+MM+"-"+DD;
	return nowDate;
}


/**
 *   全选/反选
**/
function checkAll(obj){
	jQuery('input[type="checkbox"]').attr('checked',obj.checked);
}


/**
 * 全/反选
 * @param obj checkbox的对象
 * @param name checkbox的name值 ，name外需有引号
 * 
 */
function selectAll(obj,name){
	jQuery('input[name='+ name +']').attr('checked',obj.checked);
}



/**
 * 表单提交前的验证
 * @param form1  所提交表单的名称
 * @returns {Boolean}  返回值
 * dataType:String "字符串"
 *   int "整数型"
 *   number "实数型"
 *   email "电子邮箱型"
 *   tel "电话号码类型"
 *   mobile "手机号类型"
 * 
 * title：字段名称
 * 
 * want：是否允许为空；yes不允许--no允许
 * 
 */
function checkForm(form){
	var result = true;
	jQuery(form).find('input').each(function(){
		if(checkObj(this) == false){
			result = false;
			return result;
		}
	});
	jQuery(form).find('select').each(function(){
		if(checkObj(this) == false){
			result = false;
			return result;
		}
	});
	jQuery(form).find('textarea').each(function(){
		if(checkObj(this) == false){
			result = false;
			return result;
		}
	});
	
	if(!result){
	  alert("*号项必填！");
	}
	
	return result;
}

function checkObj(obj){
	var dataType = jQuery(obj).attr('dataType');
	var title = jQuery(obj).attr('title');
	var want = jQuery(obj).attr('want');
	var value = jQuery(obj).val();
	var res = true;
	if(value == null || value == ''){
		if(want == 'yes'){
			jQuery(obj).focus();
			//alert(title+"不得为空！",2000);
			res = false;
		}
	} else {
		if(dataType == 'int'){
			var checkNum = /^\d+$/;
			if(!checkNum.test(value)){//验证字符串是否全为数字
				jQuery(obj).focus();
				alert( title + " 只能输入数字！",2000);
				res = false;
			}
		}else if(dataType == 'number'){
			if(isNaN(value)){//验证字符串是否全为数字
				jQuery(obj).focus();
				alert(title+" 只能输入数字和小数点！",3000);
				res = false;
			}
		}else if(dataType == 'tel'){
			if( value.isTel() == false){
				jQuery(obj).focus();
				alert("请输入正确的 "+title+" 格式",2000);
				res = false;
			}
		}else if(dataType == 'mobile'){
			if( value.isMobile() == false){
				jQuery(obj).focus();
				alert("请输入正确的 "+title+" 格式",2000);
				res = false;
			}
		}else if(dataType == 'email'){
			var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}jQuery/;//email判断正则表达式
			if(!reg.test(value)){
				jQuery(obj).focus();
				alert("请输入正确的 "+title+" 格式",2000);
				res = false;
			}
		}else if(dataType == 'imgfile'){
			var reg = /\.(jpg|JPG|gif|GIF|jpeg|JPEG|png|PNG)jQuery/;//图片后缀正则表达式
			if(!reg.test(value)){
				jQuery(obj).focus();
				alert("请选择正确的图片文件:<br/> jpg,JPG,gif,GIF,jpeg,JPEG,png,PNG",2000);
				res = false;
			}
		}
	}
	return res;
}


//同步访问ajax函数
function synchroAjax(url, data, type){
	if(typeof(type) == 'undefined')
		type = 'text';
	var str = '';
	var load = new loading();
	load.show();
	jQuery.ajax({
		async : false,
		url : url,
		data : data,
		type : 'POST',
		dataType : type,
		success : function(text) {
			load.close();
			str = text;
		},
		error : function(){
			load.close();
			alertMsg(3, '访问失败');
		}
	});
	return str;
}

//公用ajax封装
function PublicAjax(url, data){
	// var load = new loading();
	// load.show();
	var str = '';
	jQuery.ajax({
		async : false,
		url : url,
		data : data,
		type : 'POST',
		dataType : 'json',
		success : function(text) {
			str = text;
		},
		error : function(){
			// load.close();
			alert('访问失败');
		}
	});
	// load.close();
	return str;
}

//读取eip接口ajax封装
function ajaxTest(url, bodyParam, callBack){
	var common_url = 'http://120.27.105.87/tp_crm/index.php/Home/Api/';
	bodyParam.appId='87a21758189ded250cce06bfc32a3118'; //为ajax的data参数bodyParam添加clientCode属性	
	bodyParam.appKey='d862f894b2e022c2c4755cdcf41bfab5'; //为ajax的data参数bodyParam添加clientCode属性
	//alert(bodyParam.values.token);
	$.ajax({
		    async : false,
		    url : common_url + url,
		    data: bodyParam,
		    dataType : 'json',
		    type : 'GET',
		    timeout : 30,
	        success:function(data){
                callBack(data);
	        },
	        error:function(){
	        	alert('系统错误！');
	        }
	});
}

//同步访问ajax函数
function ajaxJson(url, data,succ_callback,error_callback){
	// var load = new loading();
	// load.show();
	// var str = '';
	jQuery.ajax({
		async : false,
		url : url,
		// headers: {
  //           // "Content-type": "application/json; charset=UTF-8",
  //           "appId": '87a21758189ded250cce06bfc32a3118',
  //           "appKey": 'd862f894b2e022c2c4755cdcf41bfab5'
  //        },
		data : data,
		type : 'POST',
		dataType : 'json',
		success : function(text) {
			succ_callback(text);
		},
		error : function(){
			// load.close();
			// alert('访问失败');
			error_callback();
		}
	});
	// load.close();
	// return str;
}

//读取eip接口ajax封装
function ajaxEip(url, bodyParam, callBack){
  var common_url = 'http://eip.jingpai.com/api/v1/';
  bodyParam.clientCode='d94cdea079b041d09df21f71a1a6523e'; //为ajax的data参数bodyParam添加clientCode属性
  //alert(bodyParam.values.token);
  $.ajax({
    async : false,
    url : common_url + url,
    data: bodyParam,
    dataType : 'json',
    type : 'GET',
    timeout : 30,
    success:function(data){
      callBack(data);
    },
    error:function(){
      alert('系统错误！');
    }
  });
}