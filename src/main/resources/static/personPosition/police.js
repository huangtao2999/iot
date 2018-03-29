
var Panel = {

	$euid : null, $zoneId : 0,
	$cameras : null, $camera : null,
	start : function(planId){
		Panel.$planId = planId;
		// 检查插件是否已经安装过
		var iRet = WebVideoCtrl.I_CheckPluginInstall();
		if (-1 == iRet) {
			alert("您还未安装过插件，双击开发包目录里的WebComponentsKit.exe安装！");
			return;
		}
		var oPlugin = {
			iWidth : $("#screen").width(), // plugin width
			iHeight : $("#screen").height()
		};


		// 初始化插件参数及插入插件
		WebVideoCtrl.I_InitPlugin(
			oPlugin.iWidth,
			oPlugin.iHeight,
			{
				bWndFull : true, // 是否支持单窗口双击全屏，默认支持 true:支持 false:不支持
				iWndowType : 1,
				cbInitPluginComplete : function() {
					WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
					// 检查插件是否最新
					if (-1 == WebVideoCtrl.I_CheckPluginVersion()) {
						alert("检测到新的插件版本，双击开发包目录里的WebComponentsKit.exe升级！");
						return;
					}

				}

			});
		Panel.getCameras();
		var cameras = Panel.$cameras;
		var camera = null;
		//判断是否切换区域
		var sock = new SockJS(url+'/rtls/sockjs');
	  	var client = Stomp.over(sock);
	  	client.debug = null;
	    client.connect({}, function(frame) {
	    	console.log("Stomp.sock.connect");
			client.subscribe("/queue/history", function(message) {
				history_data = JSON.parse(message.body);
				if(Panel.$euid == null || Panel.$euid == ""){
					return;
				}
				var euid = Panel.$euid;

				if(history_data.isUpdate=="false" && $.trim(history_data.euid)==$.trim(euid)){
					Panel.$zoneId = history_data.zoneId;

					for(var i=0; i<Panel.$cameras.length; i++){
						if(Panel.$zoneId == Panel.$cameras[i].zoneId){
							camera =  Panel.$cameras[i];
							break;
						}
					}
					if(camera == null){
						return;
					}

					var oWndInfo = WebVideoCtrl.I_GetWindowStatus(0);
					if(oWndInfo != null){
			            // 登出设备
						WebVideoCtrl.I_Stop();
						WebVideoCtrl.I_Logout(Panel.$camera.ip);
					}else {
						// 登录设备
						var oLiveView = {
					            iProtocol: 1,			// protocol 1：http, 2:https
					            szIP: camera.ip,	// protocol ip
					            szPort: "80",			// protocol port
					            szUsername: "admin",	// device username
					            szPassword: "admin12345",	// device password
					            iStreamType: 1,			// stream 1：main stream  2：sub-stream  3：third stream  4：transcode stream
					            iChannelID: 1,			// channel no
					            bZeroChannel: false		// zero channel
				    	   };
			            WebVideoCtrl.I_Login(
			            		oLiveView.szIP,
			            		oLiveView.iProtocol,
			            		oLiveView.szPort,
			            		oLiveView.szUsername,
			            		oLiveView.szPassword, {
			                success: function (xmlDoc) {
			                    // 开始预览
			                    WebVideoCtrl.I_StartRealPlay(oLiveView.szIP, {
			                        iStreamType: oLiveView.iStreamType,
			                        iChannelID: oLiveView.iChannelID,
			                        bZeroChannel: oLiveView.bZeroChannel
			                    });
			                    Panel.$camera = camera;
			                }
			            });
					}

				}
			});
	    });
	    sock.onclose = function(event) {
	    	console.log("Stomp.sock.closed");
	    	Panel.window.close();
	    };
	 	$(window).bind('beforeunload',function(){
	    	console.log("Stomp.window.beforeunload");
	    	if(client != null && client.connected){
	    		client.disconnect();
	    	}
	    });

		// 关闭浏览器
		$(window).unload(function() {
			WebVideoCtrl.I_Stop();
		});

	},
	follow : function(euid){
		Panel.$euid = euid;
	},
	getCameras : function(){

		$.ajax({
			async : false,
			type: 'GET',
			url: url+"/service/camera.json?action=get.cames",
			contentType: "application/json; charset=utf-8",
            dataType: 'jsonp',
            data : {
            	"planId" : 1
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
            	console.log("responseText: " + XMLHttpRequest.responseText+ ", textStatus: " + textStatus+ ", errorThrown: " + errorThrown);
            },
            success : function (data) {

            	if(typeof(data.cameras)=="undefined"){
            		return;
            	}
            	Panel.$cameras = data.cameras;

			}
		});

	}
};


