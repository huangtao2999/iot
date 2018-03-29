/**
 * 日期格式化组件
 * yyyy : 年份
 * MM : 月份
 * dd : 日期
 * hh : 小时
 * mm : 分钟
 * ss : 秒
 * q : 季度
 * S ： 毫秒
 * 伪例：new Date().format("yyyy-MM-dd hh:mm:ss q S") ==> "2018-03-03 14:59:42 1 854"
 */
Date.prototype.format = function(fmt) {
     var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
     if(fmt.indexOf("aN") > -1){
    	 return "";
     }
    return fmt;
}


/**
 * JS 取系统当前时间 如： 2014-08-20
 */
function getNowDate() {
    var md = new Date();
    var yyyy = md.getFullYear();
    var MM = (md.getMonth() + 1) < 10 ? "0" + (md.getMonth() + 1) : (md.getMonth() + 1);
    var DD = md.getDate() < 10 ? "0" + md.getDate() : md.getDate();
    var nowDate = yyyy + "-" + MM + "-" + DD;
    return nowDate;
}

/**
 * 格式化日期，中文年月日
 */
function formatDateChn(pdate) {
	var date;
	if(pdate){
		date = new Date(pdate);
	}else{
		date = new Date();
	}
	var myyear = date.getFullYear();
	var mymonth = date.getMonth()+1;
	var myweekday = date.getDate();

	if(mymonth < 10){
	mymonth = "0" + mymonth;
	}
	if(myweekday < 10){
	myweekday = "0" + myweekday;
	}
	return (myyear+"年"+mymonth + "月" + myweekday + "日");
}

/**
 * 格式化日期
 */
function formatDate(pdate) {
	var date = new Date(pdate);
	var myyear = date.getFullYear();
	var mymonth = date.getMonth()+1;
	var myweekday = date.getDate();

	if(mymonth < 10){
	mymonth = "0" + mymonth;
	}
	if(myweekday < 10){
	myweekday = "0" + myweekday;
	}
	return (myyear+"-"+mymonth + "-" + myweekday);
}

/**
 * 格式化时间
 */
function formatTime(pdate) {
	var date = new Date(pdate);
	var fmt = "yyyy-MM-dd hh:mm:ss";
	var o = {
		"M+" : date.getMonth()+1,                 //月份
		"d+" : date.getDate(),                    //日
		"h+" : date.getHours(),                   //小时
		"m+" : date.getMinutes(),                 //分
		"s+" : date.getSeconds(),                 //秒
		"q+" : Math.floor((date.getMonth()+3)/3), //季度
		"S"  : date.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
}

/**
 * 获得本周的开始日期
 * @return yyyy-MM-dd
 */
function getWeekStartDate(date) {
	var now = "";
	if(date == undefined || date == null || date == ""){
		//默认当前月
		now = new Date(); //当前日期
	}else{
		now = new Date(date);
	}
	var nowDayOfWeek = now.getDay(); //今天本周的第几天
	var nowDay = now.getDate(); //当前日
	var nowMonth = now.getMonth(); //当前月
	var nowYear = now.getFullYear(); //当前年
	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek + 1);
	return formatDate(weekStartDate);
}

/**
 * 加天数
 * @return yyyy-MM-dd
 */
function addDays(date, days){
	if(date == undefined || date == null || date == ""){
		return;
	}
	var now = new Date(date);
	now.setDate(now.getDate() + days);
	return formatDate(now);
}


/**
 * 获取当前月，首页图表使用
 * @return yyyyMM
 */
function getNowMonth() {
    var md = new Date();
    var yyyy = md.getFullYear();
    var MM = (md.getMonth() + 1) < 10 ? "0" + (md.getMonth() + 1) : (md.getMonth() + 1);
    var DD = md.getDate() < 10 ? "0" + md.getDate() : md.getDate();
    var nowDate = yyyy + "" + MM ;
    return nowDate;
}

/**
 * 获取上月，首页图表使用
 * @param yyyyMM
 * @return yyyyMM
 */
function getPreMonth(date) {
	if(date == undefined || date == null || date == ""){
		//默认当前月
		date = getNowMonth();
	}
    var year = date.substring(0,4); //获取当前日期的年份
    var month = date.substring(4,6); //获取当前日期的月份
    var day = '01'; //获取当前日期的日

    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
//    var day2 = day;
//    var days2 = new Date(year2, month2, 0);
//    days2 = days2.getDate();
//    if (day2 > days2) {
//        day2 = days2;
//    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '' + month2;
    return t2;
}

/**
 * 获得下月，首页图表使用
 * @param yyyyMM
 * @return yyyyMM
 */
function getNextMonth(date) {
	if(date == undefined || date == null || date == ""){
		//默认当前月
		date = getNowMonth();
	}
    var year = date.substring(0,4); //获取当前日期的年份
    var month = date.substring(4,6); //获取当前日期的月份
    var day = '01'; //获取当前日期的日

    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中的月的天数
    var year2 = year;
    var month2 = parseInt(month) + 1;
    if (month2 == 13) {
        year2 = parseInt(year2) + 1;
        month2 = 1;
    }
//    var day2 = day;
//    var days2 = new Date(year2, month2, 0);
//    days2 = days2.getDate();
//    if (day2 > days2) {
//        day2 = days2;
//    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '' + month2 ;
    return t2;
}
