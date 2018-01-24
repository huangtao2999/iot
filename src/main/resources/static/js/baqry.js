$(function() {

// 设置默认选中
// var test = $("#pst_self").text();
// // alert(test);
// $("input[value='"+test+"']").attr('checked','true');
});

$.ajaxJson = function(url, data, callback, complete) {
	if(jQuery.isFunction(data)) {
		callback = data;
		data = null;
	}
	if(data != null) {
		data = JSON.stringify(data);
	}
	$.ajax({
		url: url,
		type: "POST",
		traditional: true,
		contentType: "application/json; charset=utf-8",
		data: data,
		dataType: "json",
		success: callback,
		complete: complete,
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
			// alert("请求出错");
			console.log(XMLHttpRequest.responseText + ":" + textStatus); // parser error;
		}

	});
}

function mytest(){ 
	var trStr = '';//动态拼接table
	var tt = $("#condition").val();
	 $.ajax({
            url:"selectUser",//这里指向的就不再是页面了，而是一个方法。
            data:{'tt':tt},
            // async : true,
            type:"POST",
            dataType:"JSON",
            success: function(data){
            	// alert(data);
                // alert(data.username);//这里要用索引，使用eq读取不出来数据。
                for(var i =0;i< data.length;i++)
            {
                console.log(data[i].username);
                    // document.getElementById("operator_select").innerHTML = data[i].login_name;
                     trStr += '<tr id="'+data[i].id+'" ondblclick=callUser('+data[i].id+',"'+data[i].username+'")>';//拼接处规范的表格形式
					 trStr += '<td>' +(i+1) + '</td>';//数据表的主键值
					 trStr += '<td >' + data[i].username + '</td>';//对应数组表的字段值
					 trStr += '<td >' + data[i].username + '</td>';
					 /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
					 trStr += '</tr>';  
            }
            // var dateList = data;
            $("#readUserList").html(trStr);//要刷新的div
               
            }
        })

} 
// 办案警察查询
function police_select(){ 
	var trStr = '';//动态拼接table
	var tt = $("#condition").val();
	 $.ajax({
            url:"selectPolice",//这里指向的就不再是页面了，而是一个方法。
            data:{'tt':tt},
            // async : true,
            type:"POST",
            dataType:"JSON",
            success: function(data){
            	// alert(data);
                // alert(data.username);//这里要用索引，使用eq读取不出来数据。
                for(var i =0;i< data.length;i++)
            {
                console.log(data[i].username);
                    // document.getElementById("operator_select").innerHTML = data[i].login_name;
                     trStr += '<tr id="'+data[i].id+'" ondblclick=callPolice('+data[i].id+',"'+data[i].username+'")>';//拼接处规范的表格形式
					 trStr += '<td>' +(i+1) + '</td>';//数据表的主键值
					 trStr += '<td >' + data[i].username + '</td>';//对应数组表的字段值
					 trStr += '<td >' + data[i].idcard + '</td>';
					 /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
					 trStr += '</tr>';  
            }
            // var dateList = data;
            $("#readPoliceList").html(trStr);//要刷新的div
               
            }
        })

} 

// 办案警察查询
function Room_select(){ 
	var trStr = '';//动态拼接table
	// var tt = $("#condition").val();
	 $.ajax({
            url:"selectRoom",//这里指向的就不再是页面了，而是一个方法。
            data:{},
            // async : true,
            type:"POST",
            dataType:"JSON",
            success: function(data){
            	// alert(data);
                // alert(data.username);//这里要用索引，使用eq读取不出来数据。
                for(var i =0;i< data.length;i++)
            {
                    // document.getElementById("operator_select").innerHTML = data[i].login_name;
                     trStr += '<tr>';//拼接处规范的表格形式
					 trStr += '<td>' +data[i].room + '</td>';//数据表的主键值
					 trStr += '<td >' + data[i].idle_num + '</td>';//对应数组表的字段值
					 trStr += '<td >' + data[i].same_num + '</td>';
					 // alert(data[i].status);
					 // if(data[i].status=="未分配"){
					 trStr += '<td><input type="button" class="btn btn-primary" id="'+data[i].id+'" onclick=callRoom('+data[i].id+',"'+data[i].room+'") value="分配"/></td>';//拼接处规范的表格形式
					 // }
					 // else{
					 // 	 trStr += '<td><input type="button" class="btn"  value="分配" disabled/></td>';
					 // }

					 /*经典之处，要将主键对应的值以json的形式进行传递，才能在后台使用*/
					 trStr += '</tr>';  
            }
            // var dateList = data;
            $("#readRoomList").html(trStr);//要刷新的div
               
            }
        })

} 

function callUser(id,username){
 $("#userName").val(username);

 hide02();
}

function callPolice(id,username){
 $("#police_name").val(username);

 hide();
}
function callRoom(id,room){
 $("#wait_room").val(room);

 hide02();
}



function mysubmit(){
	console.log($("#form1"));
$("#form1").submit();
}

function opern_layer(id){
 layer.confirm('确定人员归所？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
            url:"user_in",//这里指向的就不再是页面了，而是一个方法。
            data:{'id':id},
            async : true,
            type:"POST",
            dataType:"JSON",
            success: function(data){
              layer.msg('人员归所成功', {icon: 1});
              // window.location.href='index';
               
            }
        })
        }, function(){
            layer.closeAll();
        });
}
 // $('tr').click(function(event) {
 //         Act on the event 
 //        alert($(this).find('p').text())
 //    });