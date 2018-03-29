var $ido = {gisuri : '192.168.1.5:9000'};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
String.prototype.escape = function() {
    return this.replace(/[\"]/g, '\\"')
    .replace(/[\\]/g, '\\\\')
    .replace(/[\/]/g, '\\/')
    .replace(/[\b]/g, '\\b')
    .replace(/[\f]/g, '\\f')
    .replace(/[\n]/g, '\\n')
    .replace(/[\r]/g, '\\r')
    .replace(/[\t]/g, '\\t');
};
String.prototype.htmlEscape = function() {
	 return this.replace(/&/g, '&amp;').replace(/>/g, '&gt;').replace(/</g, '&lt;').replace(/"/g, '&quot;');
};
String.prototype.htmlUnescape = function() {
	 return this.replace(/&amp;/g, '&').replace(/&gt;/g, '>').replace(/&lt;/g, '<').replace(/&quot;/g, '"');
};
String.prototype.datediffDays = function(date) {
	if (this.length != 10 || date.length != 10) return null;
 	var sd = this.split('-');
	var ed = date.split('-');
	var fromDate = new Date(sd[0], sd[1], sd[2]);
    var toDate = new Date(ed[0], ed[1], ed[2]);
    return (toDate.getTime() - fromDate.getTime()) / 1000 / 60 / 60 / 24;
};
String.prototype.datediffMonth = function(date) {
	if (this.length != 10 || date.length != 10) return null;
 	var sd = this.split('-');
	var ed = date.split('-');
	var fromDate = new Date(sd[0], sd[1], sd[2]);
    var toDate = new Date(ed[0], ed[1], ed[2]);
    if(sd[0] == ed[0]) {
    	return (parseInt(ed[1]) - parseInt(sd[1]));
    } else {
    	return Math.floor((toDate.getTime()-fromDate.getTime())/(1000*60*60*24*365.25/12));
    }
    return (toDate.getTime() - fromDate.getTime()) / 1000 / 60 / 60 / 24;
};
Number.prototype.zf = function(len){return this.toString().zf(len);};
Number.prototype.formatMoney = function(places, symbol, thousand, decimal) {
	places = !isNaN(places = Math.abs(places)) ? places : 2;
	symbol = symbol !== undefined ? symbol : '';
	thousand = thousand || ",";
	decimal = decimal || ".";
	var number = this, 
	    negative = number < 0 ? "-" : "",
	    i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
	    j = (j = i.length) > 3 ? j % 3 : 0;
	return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};
Number.prototype.msdate = function(){
	var milliseconds = this;
	var d = new Date(milliseconds);
	var yyyy = d.getFullYear();
	var month = d.getMonth() + 1;
	var day = d.getDate();
	var h = d.getHours();
	var m = d.getMinutes();
	var s = d.getSeconds();
	if(month < 10) month = "0"+month;
	if(day < 10) day = "0"+day;
	if(h < 10) h = "0"+h;
	if(m < 10) m = "0"+m;
	if(s < 10) s = "0"+s;
	return yyyy+"-"+month+"-"+day+" "+h+":"+m+":"+s;
};
Number.prototype.msday = function(){
	var milliseconds = this;
	var d = new Date(milliseconds);
	var yyyy = d.getFullYear();
	var month = d.getMonth() + 1;
	var day = d.getDate();
	if(month < 10) month = "0"+month;
	if(day < 10) day = "0"+day;
	return yyyy+"-"+month+"-"+day;
}; 
Number.prototype.roundXL = function(digits) {
	if(isNaN(this)) return 0;
	if(digits >= 0) return parseFloat(this.toFixed(digits)); // 소수부 반올림

	digits = Math.pow(10, digits); // 정수부 반올림
	var t = Math.round(this * digits) / digits;

	return parseFloat(t.toFixed(0));
};
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "上午" : "下午";
            default: return $1;
        }
    });
};
Date.prototype.getWeekPeriod = function(start){ 
    start = start || 0; 
    var today = new Date(this.setHours(0, 0, 0, 0)); 
    var day = today.getDay() - start; 
    var date = today.getDate() - day; 
    // Grabbing Start/End Dates 
    var startDate = new Date(today.setDate(date + 1));
    var tempDate = new Date(startDate);
    var endDate = new Date(tempDate.setDate(tempDate.getDate() + 4)); 
    endDate.setHours(23,59,59, 0);
    return {start :startDate, end : endDate}; 
};
Date.prototype.getMonthPeriod = function(){ 
	var year = this.getFullYear();
	var month = this.getMonth();
    var startDate = new Date(year, month, 1); 
    startDate.setHours(0,0,0, 0);
    var endDate = new Date(year, month+1, 0); 
    endDate.setHours(23,59,59, 0);
    return {start :startDate, end : endDate}; 
};	
$.datepicker.setDefaults({
    monthNames: ['年 1月','年 2月','年 3月','年 4月','年 5月','年 6月','年 7月','年 8月','年 9月','年 10月','年 11月','年 12月'],
    monthNamesShort: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
    changeMonth: true,
    changeYear: true,
    showMonthAfterYear:true,
    dateFormat: 'yy-mm-dd',
    buttonImageOnly: true,
    buttonText: "日历"
});
Array.prototype.remove = function(v) {
	$.grep(this, function(value) {
		return value != v;
	}); 
};
Array.prototype.removeIxs = function(indices) { 
  indices = indices.sort(function(l, r) { return r - l; });
  var result = this;

  $.each(indices, function(k, ix) {
    result.splice(ix, 1);
  });
};
Array.prototype.insert = function (index, item) {
	this.splice(index, 0, item);
};
Array.prototype.insertAll = function(items) {
	for (var i = 0;  i < items.length;  i++) {
       this.push(items[i]);
    }
};
Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
};

$.fn.exists = function(){return this.length>0;}