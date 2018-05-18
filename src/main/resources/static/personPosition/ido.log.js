
var Log = {
	log : function(type, msg){
		if(window.console == undefined){
			console = {log : function(){}};
		}
		if(type == 'ERROR'){
			console.error(this.getTimestamp(), '['+type+']', msg);
		}else{
			console.log(this.getTimestamp(), '['+type+']', msg);	
		}
		
	},
	debug : function(msg){
		this.log("DEBUG", msg);
	},
	error : function(msg){
		this.log("ERROR", msg);
	},
	sessionend : function(){
		$("#dialog-message").dialog({
			title : "确认信息",
	        width: "340",
	        bgiframe: true,
	        autoOpen: false,
	        modal: true,
	        resizable: false,
	        buttons: {
				"确认": function() {
					$('#dialog-message').dialog('close');
					window.location = '/admins/account.action?pages=admin.login.form';
				}
	        },
	        close: function() {
	        	$("#dialog-message").empty();
	        	$('#dialog-message').dialog('destroy');
	        }
    
		});
		$("#dialog-message").append("<p>会话结束了.</p><p>登录后使用.</p>");
		$('#dialog-message').dialog('open');
	},
	message : function(title, msg){
		$("#dialog-message").dialog({
			title : title,
	        width: "400",
	        bgiframe: true,
	        autoOpen: false,
	        modal: true,
	        resizable: false,
	        buttons: [{
	        	text : "确认",
				click : function() {
					$( this ).dialog( "close" );
				}
			}],
			close: function() {
				$("#dialog-message").empty();
	        }
	    });
		$("#dialog-message").html(msg);
		$('#dialog-message').dialog('open');
	},
	dialog : function(title, msg, position){
		if(position != undefined){
			$("#dialog-message").dialog({
				title : title,
		        width: "400",
		        bgiframe: true,
		        autoOpen: false,
		        modal: true,
		        resizable: false,
		        position: position,
				buttons: [{
		        	text : "确认",
					click : function() {
						$( this ).dialog( "close" );
					}
				}],
				close: function() {
					$("#dialog-message").empty();
		        }
		    });
			$("#dialog-message").html(msg);
			$('#dialog-message').dialog('open').parent('.ui-dialog').css('zIndex',9999);;
		}else{
			$("#dialog-message").dialog({
				title : title,
		        width: "400",
		        bgiframe: true,
		        autoOpen: false,
		        modal: true,
		        resizable: false,
		        buttons: [{
		        	text : "确认",
					click : function() {
						$( this ).dialog( "close" );
					}
				}],
				close: function() {
					$("#dialog-message").empty();
		        }
		    });
			$("#dialog-message").html(msg);
			$('#dialog-message').dialog('open').parent('.ui-dialog').css('zIndex',9999);;
		}
		
	},
	getTimestamp : function() {
	    var d = new Date();

	    var mm = d.getMilliseconds(), hh = d.getHours(),
	        MM = d.getMinutes(), ss = d.getSeconds();

	    return (hh < 10 ? "0" : "") + hh + (MM < 10 ? ":0" : ":") + MM + (ss < 10 ? ":0" : ":") + ss + ":" + mm;
	}
};
