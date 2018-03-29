var Caller = null;
var Unity = {
	$u : null, $plan : null,
	$toolbar : {},
	$isMapInit : false, $isDistance2D : false, $isDistance3D : false,$isDrawPolygon : false, $isCompassEnable : "true",
	$isPickingId : false, $lastPickingId : null, 
	$sbmId : null, $selectedComponentId : null,
	$sbmPath : "", $texturePath : "", $gkxmlPath : "",
	$createSBMCount : 0, $createSBMTotalCount : 0,
	$isfloorMerge : "false", $isWallMerge : "false", $isCharacterEanble : "false", $isPOIEnable : "true",  $isCameraAction : false,		
	$sbmList : new Array(), $selectList : new Array(), $copyOBJList : new Array(), 
	$componentList : new Array(), $planeList : new Array(), $polygonList : new Array(), $poiList : new Array(), $objList : new Array(),
	log : function(obj, log){
		if(log != undefined){
			console.log(obj, log);	
		}else{
			console.log(obj);
		}
	},
	init : function(caller, width, height, sbmPath, texturePath, gkxmlPath){
		this.log("Unity.init("+width+", "+height+")", "SBMPATH="+sbmPath+", TEXTUREPATH="+texturePath+",GKXML="+gkxmlPath);
		Caller = caller;
		this.$plan = caller.$plan;
		this.$isMapInit = false;
		this.$sbmPath = sbmPath; 
		this.$texturePath = texturePath;
		this.$gkxmlPath = gkxmlPath;
		var config = {
			width: '100%', 
			height: height,
			enableGoogleAnalytics : false,
			params: { logoimage: "/resources/commons/images/3d/logo_idolink.png"}
		};
		config.params["disableContextMenu"] = true;
		this.$u = new UnityObject2(config);
		var $missingScreen = $("#viewport").find(".missing");
		var $brokenScreen = $("#viewport").find(".broken");
		$missingScreen.hide();
		$brokenScreen.hide();
		this.$u.observeProgress(function (progress) {
			switch(progress.pluginStatus) {
				case "broken":
					$brokenScreen.find("a").click(function (e) {
						e.stopPropagation();
						e.preventDefault();
						Unity.$u.installPlugin();
						return false;
					});
					$brokenScreen.show();
				break;
				case "missing":
					$missingScreen.find("a").click(function (e) {
						e.stopPropagation();
						e.preventDefault();
						Unity.$u.installPlugin();
						return false;
					});
					$missingScreen.show();
				break;
				case "installed":
					$missingScreen.remove();
				break;
				case "first":
				break;
			}
		});
		this.$u.initPlugin($("#viewport-3d")[0], '/resources/commons/3d/GongVue.unity3d');
	},
	initToolbar : function(){
		this.$toolbar = $('#map3d-toolbar');
		if(this.$toolbar != undefined){
			this.$toolbar.find('#but-view-home').click(function(e) {
				Unity.screenHome();
			});
			this.$toolbar.find('#but-view-front').click(function(e) {
				Unity.screenFront();
			});
			this.$toolbar.find('#but-view-3d').click(function(e) {
				Unity.screen3D();
			});
			this.$toolbar.find('#but-view-2d').click(function(e) {
				Unity.screen2D();
			});
			this.$toolbar.find('#but-view-person').click(function(e) {
				Unity.screenPerson();
			});
			this.$toolbar.find('#but-zoom-in').mousedown(function(e) {
				Unity.characterFalse();
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "0");
				Unity.$isCameraAction = true;
			}).mouseup(function(e) {
				Unity.$isCameraAction = false;
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "-1");
			});
			this.$toolbar.find('#but-zoom-out').mousedown(function(e) {
				Unity.characterFalse();
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "1");
				Unity.$isCameraAction = true;
			}).mouseup(function(e) {
				Unity.$isCameraAction = false;
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "-1");
			});
			this.$toolbar.find('#but-rotate-left').mousedown(function(e) {
				Unity.characterFalse();
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "12");
				Unity.$isCameraAction = true;
			}).mouseup(function(e) {
				Unity.$isCameraAction = false;
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "-1");
			});
			this.$toolbar.find('#but-rotate-right').mousedown(function(e) {
				Unity.characterFalse();
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "13");
				Unity.$isCameraAction = true;
			}).mouseup(function(e) {
				Unity.$isCameraAction = false;
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "-1");
			});
			this.$toolbar.find('#but-default').click(function(e) {
				Unity.mouseDefault();
			});
			
			this.$toolbar.find('#but-distance-2d').click(function(e) {
				Unity.mouseDistance2D();
			});
			this.$toolbar.find('#but-distance-3d').click(function(e) {
				Unity.mouseDistance3D();
			});
			this.$toolbar.find('#but-draw-polygon').click(function(e) {
				Unity.mouseDrawPolygon();
				
			});
			this.$toolbar.find('#but-layer-onoff').click(function(e) {
				if(Unity.$isPOIEnable == 'true'){
					Unity.$isPOIEnable = "false";	
					$('#but-layer-onoff > img').attr('src' , '/resources/commons/images/3d/icon_off.png');
					$('#but-layer-onoff').removeClass('ui-selected');
				}else{
					Unity.$isPOIEnable = "true";
					$('#but-layer-onoff > img').attr('src' , '/resources/commons/images/3d/icon_on.png');
					$('#but-layer-onoff').addClass('ui-selected');
				}
				Unity.setLayerEnable(Unity.$isPOIEnable);
			});
			this.$toolbar.find('#but-compass').click(function(e) {
				if(Unity.$isCompassEnable == "true"){
					Unity.$isCompassEnable = "false";
					$('#but-compass').removeClass('ui-selected');	
				}else{
					Unity.$isCompassEnable = "true";
					$('#but-compass').addClass('ui-selected');
				}
				Unity.$u.getUnity().SendMessage("VBCamera", "VBCompassEnable", Unity.$isCompassEnable);
				
			});
			this.$toolbar.find('#but-2dmap').click(function(e) {
				var loc = window.location.href;
				if(loc.indexOf('service.plan.3d') != -1){
					window.location = '/service/plan.action?pages=service.plan';
				}else if(loc.indexOf('service.rap.3d') != -1){
					window.location = '/service/plan.action?pages=service.rap';
				}else if(loc.indexOf('service.position.3d') != -1){
					window.location = '/service/plan.action?pages=service.position';
				}else if(loc.indexOf('service.panel') != -1){
					if(Caller != null){
						if(Caller.$id == 'Panel'){
							Unity.clearOBJ();
							Caller.ui.$mapType = '2D';
							Caller.ui.indoorMap();
						}
					}
				}
			});
			this.$toolbar.find('#but-reload').click(function(e) {
				Unity.clearSBM();
				Unity.$u.getUnity().SendMessage("VBGKXML", "VBCreateGKXML","TP," + Unity.$gkxmlPath);
			});
		}
	},
	initCamera : function(){
		this.$u.getUnity().SendMessage("VBCamera", "VBBackGroundColor", "0,0,0,255");
		this.$u.getUnity().SendMessage("VBCamera", "VBSetCameraZoomDistance", "50");
		this.$u.getUnity().SendMessage("VBCamera", "VBCompassEnable", "true");
	},
	initInterface : function(){
		this.$u.getUnity().SendMessage("VBInterface", "VBButtonZoomPoint", "3,1000,50");
		this.$u.getUnity().SendMessage("VBInterface", "VBButtonZoomPoint", "4,10000,50");
		this.$u.getUnity().SendMessage("VBInterface", "VBMousePickingLayer", "0");
		if(Caller != null){
			if(Caller.$id == 'Rap'){
				this.$u.getUnity().SendMessage("VBInterface", "VBMouseEditComponent", "2");
			}else if(Caller.$id == 'Position'){
				this.$u.getUnity().SendMessage("VBInterface", "VBMouseEditLayer", "2");
			}else if(Caller.$id == 'Prebuild.planning'){
				this.$u.getUnity().SendMessage("VBInterface", "VBMousePickingSBM", "2");
			}else if(Caller.$id == 'Prebuild.analyzer'){
				this.$u.getUnity().SendMessage("VBInterface", "VBMouseEditLayer", "2");
			}
		}
		this.$u.getUnity().SendMessage("VBInterface", "VBButtonPanning", "1");
		this.$u.getUnity().SendMessage("VBInterface", "VBButtonPositionRotate", "0");
	},
	initLodingBar : function(){
		this.$u.getUnity().SendMessage("VBSBM", "VBSetLodingBar", "0,0,100,3");
		this.$u.getUnity().SendMessage("VBSBM","VBSetPickingColor","0,0,255,125");
	},
	initLayer : function(){
		this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "menu,true");
		this.$u.getUnity().SendMessage("VBLayer", "VBCreatePOI2DEx", "menu,1,50,0");
		//this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "13,/resources/commons/images/3d/icon_poi_indoor.png");
		if(Caller != null){
			if(Caller.$id == 'Position'){
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "position,true");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "1,/resources/commons/images/3d/icon_poi.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColorEnable","true");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColor","255,0,0");
			}else if(Caller.$id == 'Prebuild.analyzer'){
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "tag,true");
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "rap,true");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "1,/resources/commons/images/map/icon_tag_fixed.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "2,/resources/commons/images/map/icon_tag_move.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColorEnable","true");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColor","255,0,0");
			}
		}
	},
	defineComponent : function(){
		if(Caller != null){
			if(Caller.$id == 'Rap' || Caller.$id == 'Panel' || Caller.$id == 'Prebuild.planning' || Caller.$id == 'Prebuild.analyzer'){
				var sbmPath = '/resources/commons/3d/rap/';
				var texturePath = '/resources/commons/3d/rap/Maps/';
				var sbmFile = 'rap.sbm';
				var componentId = 'RAP'
				this.$u.getUnity().SendMessage("VBSBMEdit","VBDefineComponent",sbmPath + "," + texturePath + "," + sbmFile + "," + componentId);
			}
		}
	},
	createGKXML : function(sbmPath, texturePath, gkxmlPath, caller){ //GKXML로 도면 생성
		if(sbmPath != undefined) this.$sbmPath = sbmPath
		if(texturePath != undefined) this.$texturePath = texturePath
		if(gkxmlPath != undefined) this.$gkxmlPath = gkxmlPath
		if(caller != undefined){
			Caller = caller;
			this.$plan = caller.$plan;
		}
			
		this.log("Unity.createGKXML", "SBMPATH="+this.$sbmPath+", TEXTUREPATH="+this.$texturePath+", GKXML="+this.$gkxmlPath);
		this.$u.getUnity().SendMessage("VBGKXML", "VBCreateGKXML","TP," + this.$gkxmlPath);
		
	},
	createSBM : function(sbmPath, sbmFile, sbmId){ // SBM LOAD로 도면 생성
		this.log("Unity.createSBM", "SBMPATH="+sbmPath+", SBMFILE="+sbmFile);
		this.$sbmPath = sbmPath;
		this.$texturePath = sbmPath;
	    this.$createSBMCount++;
		this.$u.getUnity().SendMessage("VBSBM","VBCreateSBM",sbmPath + "," + sbmFile + "," + sbmId + ",true,true");
	},
	createTexture : function(sbmPath, texturePath, sbmFile, sbmId){ // SBM 파일과 Texture path 다를 경수 사용
		this.log("Unity.createTexture", "SBMPATH="+sbmPath+", TEXTUREPATH="+texturePath+", SBMFILE="+sbmFile);
		this.$sbmPath = sbmPath;
		this.$texturePath = texturePath;
		this.$createSBMCount++;
		this.$u.getUnity().SendMessage("VBSBM","VBCreateSBMTexture",sbmPath + "," + texturePath + "," + sbmFile + "," + sbmId + ",true,true");
	},
	createPOIType : function(arg){
		this.log("Unity.createPOIType", arg);
		//벽체 통합 + 층 통합
		if(this.$isWallMerge == "true" && this.$isfloorMerge == "true"){
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingSpace","TP,Unity.createMergePOI");
		}
		//벽체 통합 + 층 분리
		if(this.$isWallMerge == "true" && this.$isfloorMerge == "false"){
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingSpaceByID","TP," + arg + ",Unity.createMergePOI");
		}
		//벽체 분리 + 층 통합
		if(this.$isWallMerge == "false" && this.$isfloorMerge == "true"){
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingFloorEntity","TP, Unity.createPOI");
		}
		//벽체 분리 + 층 분리
		if(this.$isWallMerge == "false" && this.$isfloorMerge == "false"){
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingFloorEntityByID","TP," + arg + ",Unity.createPOI");
		}
	},
	createMergePOI : function(arg){
		this.log("Unity.createMergePOI", arg);
		var entityList = arg.split("//");
		var ix = 1;
		while(ix < entityList.length){
			var entityListInfo = entityList[ix].split(",");
			if(entityListInfo.length > 3){
				this.log('VBCreateLayer', entityListInfo[1]);
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", entityListInfo[1] + "," + this.$isPOIEnable);
			}
			var poiIx = 0;
			var ix2 = 3;
			while(ix2 <= entityListInfo.length - 7){
				this.$u.getUnity().SendMessage("VBLayer", "VBCreatePOI3D", entityListInfo[1] + "," + poiIx + "," +  entityListInfo[ix2+4] + "," + (Number(entityListInfo[ix2+5])+ 10) + "," + entityListInfo[ix2+6]);
				//this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage",entityListInfo[1] + "," + poiIx + ",0,13,18,18");
				//this.$u.getUnity().SendMessage("VBLayer", "VBAddItemTextOutLine", entityListInfo[1] + "," + poiIx + ",1,0,0,0,255,255,255,255,255,12," +entityListInfo[ix2+2]);
				ix2+=7;
				poiIx++;
			}
			  
			ix++;
		}
	},
	createPOI : function(arg){
		this.log("Unity.createPOI", arg);
		//parent.LeftMenu.JSAddSBMInfo(arg);
	    
		var entityList = arg.split("//");
		
		var ix = 1;
		while(ix < entityList.length){
			var entityListInfo = entityList[ix].split(",");
		    //entityListInfo[0] = FloorName
		    //entityListInfo[1] = ID
		    //entityListInfo[2] = Type
		    //entityListInfo[3] = Longname
			if(entityListInfo.length >= 12){
				this.log('VBCreateLayer', entityListInfo[1]);
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", entityListInfo[1] + "," + this.$isPOIEnable);
			  //mstrName, mstrID, mstrType, mstrLongName, mstrX, mstrY, mstrZ
				var ix2 = 4;
				while(ix2 <= entityListInfo.length - 8){
					if(entityListInfo[ix2 + 2] == "SPACE" || entityListInfo[ix2 + 2] == "Space"){
						//this.$u.getUnity().SendMessage("VBLayer", "VBCreatePOI3D", entityListInfo[1] + "," + entityListInfo[ix2 + 1] + "," +  entityListInfo[ix2+5] + "," + (Number(entityListInfo[ix2+6]) + 10) + "," + entityListInfo[ix2+7]);
						//this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage",entityListInfo[1] + "," + entityListInfo[ix2 + 1] + ",0,13,18,18");
						//this.$u.getUnity().SendMessage("VBLayer", "VBAddItemTextOutLine", entityListInfo[1] + "," + entityListInfo[ix2 + 1] + ",1,0,0,0,255,255,255,255,255,12," +entityListInfo[ix2+3]);
					}
					ix2+=8;
				}
			}
			ix++;
		}
		this.$u.getUnity().SendMessage("VBLayer", "VBReDraw", null);
	},
	createMergeSBM : function(arg){
		this.log("Unity.createMergeSBM", arg);
		var floorList = arg.split("//");
		if(floorList.length == 2){
			if(floorList[1] == "") return;
			var floorListInfo = floorList[1].split(",");
			this.$u.getUnity().SendMessage("VBSBM","VBCreateSBMTexture",this.$sbmPath + "," + this.$texturePath + "," + floorListInfo[0] + "," + this.$createSBMCount + ",true,true");
			this.$createSBMCount++;
		}
	},
	createFloorSBM : function(arg){
		this.log("Unity.createFloorSBM", arg);
		var floorList = arg.split("//");
		if(floorList[1] == "") 	return;
	      
		var ix = 1;
		while(ix < floorList.length){
			var floorListInfo = floorList[ix].split(",");
			if(floorListInfo.length > 0){
				if(floorListInfo[3] == "SBM") {
					this.$u.getUnity().SendMessage("VBSBM","VBCreateSBMTexture",this.$sbmPath + "," + this.$texturePath + "," + floorListInfo[2] + "," + floorListInfo[1] + ",true,true");
					this.$createSBMCount++;
				}
			}
			ix++;
		}
	},
	exportSBM : function(arg){
		this.log("Unity.exportSBM", arg);
		
		var exportList = arg.split("//");
		var ix = 1;

		if(exportList[1] == ""){
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingMergeFile", exportList[0] + ",Unity.createMergeSBM");
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingFloorFile", exportList[0] + ",Unity.createFloorSBM");
			return;
		}

		while(ix < exportList.length){
			var exportListInfo = exportList[ix].split(",");
			if(exportListInfo.length > 1){
				if(exportListInfo[0] == "floorMerge"){
					if(exportListInfo[1] == "True")
						this.$isfloorMerge = "true";
					else
						this.$isfloorMerge = "false";
				}
				if(exportListInfo[0] == "wallMerge"){
					if(exportListInfo[1] == "True")
						this.$isWallMerge = "true";
					else
						this.$isWallMerge = "false";
				}
			}
			ix++;
		}

		if(this.$isfloorMerge == "true"){
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingMergeFile", exportList[0] + ",Unity.createMergeSBM");
		}else{
			this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingFloorFile", exportList[0] + ",Unity.createFloorSBM");
		}
	},
	screenHome : function(){
		Unity.characterFalse();
		Unity.$u.getUnity().SendMessage("VBSBM", "VBGetCameraPositionEnable", "6,Unity.cameraMove");
	},
	screenFront : function(){
		Unity.characterFalse();
		Unity.$u.getUnity().SendMessage("VBSBM", "VBGetCameraPositionEnable", "4,Unity.cameraMove");
	},
	screen3D : function(){
		if($('#but-view-2d').hasClass('ui-selected')){
			Unity.characterFalse();
			Unity.$u.getUnity().SendMessage("VBCamera", "VBSetCameraHorizonLimit", "false,0");
			Unity.$u.getUnity().SendMessage("VBCamera", "VBSetPerspectiveByCamera","Main Camera");
			$('#but-view-2d').removeClass('ui-selected');
			$('#but-view-3d').addClass('ui-selected');
		}
	},
	screen2D : function(){
		if($('#but-view-3d').hasClass('ui-selected')){
			Unity.characterFalse();
			Unity.$u.getUnity().SendMessage("VBSBM", "VBGetCameraPositionEnable", "4,Unity.cameraMove");
			Unity.$u.getUnity().SendMessage("VBCamera", "VBSetCameraHorizonLimit", "true,90");
			Unity.$u.getUnity().SendMessage("VBCamera", "VBSetOrthoGraphic", null);
			$('#but-view-2d').addClass('ui-selected');
			$('#but-view-3d').removeClass('ui-selected');
		}
		
		
	},
	screenPerson : function(){
		if(Unity.$isCharacterEanble == "false"){
			Unity.$isCharacterEanble = "true";
			Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseCharacter", null);
			$('#but-view-person > img').attr('src' , '/resources/commons/images/3d/icon_person_on.png');
		}else{
			Unity.$isCharacterEanble = "false";
			Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseDefalut", null);
			$('#but-view-person > img').attr('src' , '/resources/commons/images/3d/icon_person_off.png');
		}
	},
	mouseDefault : function(){
		Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseDefalut", null);
		$('#but-default > img').attr('src' , '/resources/commons/images/3d/icon_default_on.png');
		$('#but-default').addClass('ui-selected');
		$('#but-distance-2d > img').attr('src' , '/resources/commons/images/3d/icon_distance_2d_off.png');
		$('#but-distance-3d > img').attr('src' , '/resources/commons/images/3d/icon_distance_3d_off.png');
		$('#but-draw-polygon > img').attr('src' , '/resources/commons/images/3d/icon_draw_polygon_off.png');
		$('#but-distance-2d').removeClass('ui-selected');
		$('#but-distance-3d').removeClass('ui-selected');
		$('#but-draw-polygon').removeClass('ui-selected');
	},
	mouseDistance2D : function(){
		Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseDistance2D", null);	
		$('#but-distance-2d > img').attr('src' , '/resources/commons/images/3d/icon_distance_2d_on.png');
		$('#but-distance-2d').addClass('ui-selected');

		$('#but-default > img').attr('src' , '/resources/commons/images/3d/icon_default_off.png');
		$('#but-distance-3d > img').attr('src' , '/resources/commons/images/3d/icon_distance_3d_off.png');
		$('#but-draw-polygon > img').attr('src' , '/resources/commons/images/3d/icon_draw_polygon_off.png');
		
		$('#but-default').removeClass('ui-selected');
		$('#but-distance-3d').removeClass('ui-selected');
		$('#but-draw-polygon').removeClass('ui-selected');
	},
	mouseDistance3D :function(){
		Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseDistance", null);
		$('#but-distance-3d > img').attr('src' , '/resources/commons/images/3d/icon_distance_3d_on.png');
		$('#but-distance-3d').addClass('ui-selected');
		
		$('#but-default > img').attr('src' , '/resources/commons/images/3d/icon_default_off.png');
		$('#but-distance-2d > img').attr('src' , '/resources/commons/images/3d/icon_distance_2d_off.png');
		$('#but-draw-polygon > img').attr('src' , '/resources/commons/images/3d/icon_draw_polygon_off.png');
		
		$('#but-default').removeClass('ui-selected');
		$('#but-distance-2d').removeClass('ui-selected');
		$('#but-draw-polygon').removeClass('ui-selected');
	},
	mouseDrawPolygon : function(){
		Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseDrawPolygon", "Unity.callbackDrawPolygon,Main Camera");
		$('#but-draw-polygon > img').attr('src' , '/resources/commons/images/3d/icon_draw_polygon_on.png');
		$('#but-draw-polygon').addClass('ui-selected');
		
		$('#but-default > img').attr('src' , '/resources/commons/images/3d/icon_default_off.png');
		$('#but-distance-2d > img').attr('src' , '/resources/commons/images/3d/icon_distance_2d_off.png');
		$('#but-distance-3d > img').attr('src' , '/resources/commons/images/3d/icon_distance_3d_off.png');
		
		$('#but-default').removeClass('ui-selected');
		$('#but-distance-2d').removeClass('ui-selected');
		$('#but-distance-3d').removeClass('ui-selected');
	},
	getTransform : function(type){ //최상위 Transform(Position, Scale, Rotation) 정보를 리턴한다
		this.$u.getUnity().SendMessage("VBSBM", "VBGetTransform", type+",Unity.callbackTransform");
	},
	getSBMTransform : function(type, sbmId){ //생생된 SBM의 Transform(Position, Scale, Rotation) 정보를 리턴한다
		this.$u.getUnity().SendMessage("VBSBM", "VBGetSBMTransform", type+","+sbmId+",Unity.callbackSBMTransform");
	},
	getOBJTransform : function(type, objId){ //생생된 OBJ의 Transform(Position, Scale, Rotation) 정보를 리턴한다
		this.$u.getUnity().SendMessage("VBSBM", "VBGetOBJTransform", type+","+objId+",Unity.callbackOBJTransform");
	},
	getSMBCount : function(){ //생성된 SBM의 개수를 반환한다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetSBMCount", "Unity.callbackSBMCount");
	},
	getOBJCount : function(sbmId){ //해당 SBM내부의 생성된 OBJ의 개수를 반환한다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetOBJCount", sbmId+",callbackOBJCount");
	},
	getOBJIDList : function(sbmId){ // SBM에 등록된 OBJ 목록 정보
		this.$u.getUnity().SendMessage("VBSBM", "VBGetOBJIDList", sbmId+",Unity.callbackOBJIDList");
	},
	getBounding : function(){ // 생성된 전체 SBM들의 바운딩 박스값(최소XYZ최대XYZ)을 가져온다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetBounding", "Unity.callbackBounding");
	},
	getCenterSize : function(){ // 생성된 전체 SBM들의 바운딩 박스값(Center,Size)을 가져온다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetCenterSize", "Unity.callbackCenterSize");
	},
	getSBMBounding : function(sbmId){ // SBM 바운딩 박스값(최소XYZ최대XYZ)을 가져온다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetSBMBounding", sbmId+",Unity.callbackSBMBounding"); 
	},
	getSBMCenterSize : function(sbmId){ // SBM 바운딩 박스값(Center,Size)을 가져온다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetSBMCenterSize", sbmId+",Unity.callbackSBMCenterSize");
	},
	getOBJCenterSizeByID : function(objId){ //해당 오브젝트명의 중심값과 사이즈, 하위 OBJ을 포함한 중심값과 사이즈를 반환한다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetOBJCenterSizeByID", objId+",Unity.callbackOBJCenterSizeByID"); 
	},
	getPickingList : function(){ //현재 픽킹된 오브젝트 리스트를 반환한다.
		this.$u.getUnity().SendMessage("VBSBM", "VBGetPickingList", "Unity.callbackPickingList");
	},
	getAreaById : function(objId){ //해당 오브젝트의 구성된 삼각형 면적 합을 리턴한다.
		this.$u.getUnity().SendMessage("VBSBM", "VBAreaByID", objId+",Unity.callbackAreaById"); 
	},
	getItemValue : function(layer, poiId, itemId){
		this.$u.getUnity().SendMessage("VBLayer", "VBGetItemValue", layer+","+poiId+","+itemId+",Unity.callbackItemValue");
	},
	setOBJTransform:function(type, objId, x, y, z){ //생생된 OBJ의 Transform(Position, Scale, Rotation) 정보를 설정 한다.
		this.$u.getUnity().SendMessage("VBSBM", "VBSetOBJTransform", type+","+objId+","+x+","+z+","+y);
	},
	setPOIPosition:function(layer, poiId, x, y, z){ // POI 위치 이동
		this.$u.getUnity().SendMessage("VBLayer", "VBSetPOIPosition",layer+","+poiId+","+x+","+z+","+y);
	},
	setOBJEnable:function(objId, isEnable){ //해당 OBJ 랜더링을 On,Off한다
		this.$u.getUnity().SendMessage("VBSBM", "VBSetOBJEnable", objId+","+isEnable);
	},
	setOBJMaterialColor:function(objId, color, alpha){ //해당 OBJ 색상을 변경한다
		this.$u.getUnity().SendMessage("VBSBM", "VBSetOBJMaterialColor", objId+","+color.r+","+color.g+","+color.b+","+alpha);
	},
	setOBJChildMaterialColor:function(objId, color, alpha){ //해당 OBJ와 하위OBJ의 색상을 변경한다
		this.$u.getUnity().SendMessage("VBSBM", "VBSetOBJChildMaterialColor", objId+","+color.r+","+color.g+","+color.b+","+alpha);
	},
	setLayerEnable : function(arg){ // 해당 레이어의 목록을 On,Off 한다.
		this.log("Unity.setLayerEnable", arg);
		if(arg == "false"){
		    var i = 0;
		    while( i < this.$sbmList.length){
		    	this.$u.getUnity().SendMessage("VBLayer", "VBSetLayerEnable", this.$sbmList[i]+ ","+arg);
		    	i++;
		    }  
		}else{
			this.$u.getUnity().SendMessage("VBSBM", "VBGetSBMEnable", "true, Unity.callbackGetSBMEnable");
		}
	},
	setCompassEnable : function(arg){ // 나침판 hide/show
		this.$u.getUnity().SendMessage("VBCamera", "VBCompassEnable", arg);
	},
	addComponent : function(sbmId, componentId, rapId, x, y, z, rx, ry, rz, sx, sy, sz){ //COMPONENT 추가
		//SBM ID , SBM에 추가될 ID, Define Component ID, Position X,Y,Z, Rotation X,Y,Z, Scale X,Y,Z , 충돌처리
		if(!this.$componentList.contains(rapId)){
			var arg = sbmId+","+rapId+","+componentId+","+x+","+y+","+z+","+rx+","+ry+","+rz+","+sx+","+sy+","+sz +", true";
			//Unity.log("Unity.addComponent", arg);
			this.$u.getUnity().SendMessage("VBSBMEdit", "VBAddComponent", arg);
			this.$componentList.push(rapId);
			this.log(this.$componentList);
			//this.getOBJIDList(this.$sbmId);
		}
	},
	addPOI : function(layer, poiId, x, y, z){
		if(layer == 'rap'){
			var arg = layer+","+poiId+","+x+","+y+","+z+",1,1,1,2,30";
			this.$u.getUnity().SendMessage("VBLayer", "VBCreatePOI3D", arg);
		}else{
			if(!this.$poiList.contains(poiId)){
				var arg = layer+","+poiId+","+x+","+y+","+z+",1,1,1,2,30";
				this.$u.getUnity().SendMessage("VBLayer", "VBCreatePOI3D", arg);
				this.$poiList.push(poiId);
				this.log('Unity.addPOI('+layer+','+poiId+')', this.$poiList);
			}	
		}
		
		
	},
	addItemImage : function(layer, poiId, imageId){
		if(layer == 'position'){
			var arg = layer+","+poiId+",1,1,24,24";
			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage", arg);
		}else if(layer == 'tag'){
			var arg = layer+","+poiId+",1,"+imageId+",20,20";
			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage", arg);
		}
		
	},
	addItemText : function(layer, poiId, color, alpha, fontSize, text){
		if(layer == 'position'){
			var arg = layer+","+poiId+",2,"+color.r+","+color.g+","+color.b+","+alpha+","+fontSize+",true,"+text;
			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemTextBubble", arg);
		}else if(layer == 'tag'){
			var arg = layer+","+poiId+",2,"+color.r+","+color.g+","+color.b+","+alpha+","+fontSize+",true,"+text;
			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemTextBubble", arg);
		}else if(layer == 'rap'){
			var arg = layer+","+poiId+",1,"+color.r+","+color.g+","+color.b+","+alpha+","+fontSize+",true,"+text;
			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemTextBubble", arg);
		}
		
	},
	addOBJSphere : function(sbmId, objId, x, y, z, r, color, alpha){ // OBJ 생성 - 구
		if(!this.$objList.contains(objId)){
			var arg =  sbmId+","+objId+",1,true,true,true,true,Unity.callbackCreateOBJSphere,"+x+","+y+","+z+","+r;
			this.log("Unity.addOBJSphere", arg);
			this.$u.getUnity().SendMessage("VBSBMEdit", "VBCreateOBJSphere", arg);
			this.$objList.push(objId);
			this.log(this.$objList);
			if(color != undefined){
				if(alpha == undefined) alpha = 125;
				this.$u.getUnity().SendMessage("VBSBM", "VBSetOBJMaterialColor",  sbmId+"_OBJ_"+objId+"_0,"+color.r+","+color.g+","+color.b+","+alpha);
			}
		}
	},
	addOBJPlane : function(sbmId, objId, type, ltx, lty, ltz, rtx, rty, rtz, lbx, lby, lbz, rbx, rby, rbz){
		var arg = sbmId+","+objId+","+type+",true,true,true,false, Unity.callbackCreateOBJPlane,";
		arg += ltx+","+lty+","+ltz+",";
		arg += rtx+","+rty+","+rtz+",";
		arg += lbx+","+lby+","+lbz+",";
		arg += rbx+","+rby+","+rbz;
		if(!this.$planeList.contains(objId)){
			this.$u.getUnity().SendMessage("VBSBMEdit","VBCreateOBJPlane", arg);
			this.$planeList.push(objId);
		}
		
	},
	addGraph : function(graphId, cx, cy, cz, sx, sy, sz, unit, value, count){
		var arg = graphId+",";
		arg += cx+","+cz+","+cy+",";
		arg += sx+","+sz+","+sy+",";
		arg += unit+","+value;
		if(count != undefined){
			if(count == 3){
				this.$u.getUnity().SendMessage("VBUtil", "VBDefineColor", "0,255,0,125");	
			}else if(count == 2){
				this.$u.getUnity().SendMessage("VBUtil", "VBDefineColor", "255,255,0,125");	
			}else{
				this.$u.getUnity().SendMessage("VBUtil", "VBDefineColor", "255,0,0,125");
			}
		}else{
			if(value >= 80){
				this.$u.getUnity().SendMessage("VBUtil", "VBDefineColor", "0,255,0,125");	
			}else if(value > 50 && value < 80){
				this.$u.getUnity().SendMessage("VBUtil", "VBDefineColor", "255,255,0,125");	
			}else{
				this.$u.getUnity().SendMessage("VBUtil", "VBDefineColor", "255,0,0,125");
			}	
		}
		this.$u.getUnity().SendMessage("VBUtil", "VBCreateGraph", arg);
	},
	addOBJPolygon : function(sbmId, objId, type, coordinates){
		if(!this.$polygonList.contains(objId)){
			var arg = sbmId+","+objId+","+type+",true,true,true,true,Unity.callbackCreateOBJPolygon,"+coordinates;
			this.$u.getUnity().SendMessage("VBSBMEdit","VBCreateOBJPolygon",arg);
			this.$polygonList.push(objId);
		}
		
	},
	deletePOI : function(layer, poiId){
		this.$u.getUnity().SendMessage("VBLayer", "VBDeletePOI", layer+","+poiId);
		this.$poiList = $.grep(this.$poiList, function(value) {
			return value != poiId;
		}); 
		this.log('deletePOI('+layer+','+poiId+')', this.$poiList);
	},
	deleteItem : function(layer, poiId, itemId){
		this.$u.getUnity().SendMessage("VBLayer", "VBDeleteItem", layer+","+poiId+","+itemId);
	},
	deleteOBJ : function(objId){ //OBJ 삭제
		this.log('Unity.deleteOBJ', objId);
		if(Caller.$id == 'Rap'){
			if(objId.indexOf('RAP') != -1){
				var args = objId.split('_');
				this.$componentList = $.grep(this.$componentList, function(value) {
					return value != args[2];
				}); 
				this.log(this.$componentList);
			}
				
		}else if(Caller.$id == 'Panel'){
			var args = objId.split('_');
			this.$objList = $.grep(this.$objList, function(value) {
				return value != args[2];
			}); 
			this.log(this.$objList);
		}else if(Caller.$id == 'Prebuild.planning' || Caller.$id == 'Prebuild.analyzer'){
			if(objId.indexOf('BARRIER') != -1){
				var args = objId.split('_');
				this.$polygonList = $.grep(this.$polygonList, function(value) {
					return value != args[2];
				}); 
				this.log(this.$polygonList);
			}
		}
	    this.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
	},
	deleteGraph : function(graphId){ //GRAPH 삭제
		this.$u.getUnity().SendMessage("VBUtil", "VBDeleteGraph", graphId);
	},
	clearSBM : function(){ //SBM 초기화
		this.log("Unity.clearSBM");
		this.$u.getUnity().SendMessage("VBGKXML", "VBClearGKXML", null);
		
		this.$u.getUnity().SendMessage("VBGKXML", "VBClearGKXML", null);
		this.$u.getUnity().SendMessage("VBSBM", "VBClearSBM", null);
		this.$u.getUnity().SendMessage("VBLayer", "VBClearLayer", null);
	  
		this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "menu,true");
		this.$u.getUnity().SendMessage("VBLayer", "VBCreatePOI2DEx", "menu,1,50,0");
		if(Caller != null){
			if(Caller.$id == 'Position'){
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "position,true");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "1,/resources/commons/images/3d/icon_poi.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColorEnable","true");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColor","255,0,0");
			}else if(Caller.$id == 'Prebuild.planning'){
				this.clearGraph();
				this.clearOBJPlane();
				this.clearOBJPolygon();
			}else if(Caller.$id == 'Prebuild.planning'){
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "tag,true");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "1,/resources/commons/images/map/icon_tag_fixed.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColorEnable","true");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColor","255,0,0");
				this.clearGraph();
				this.clearOBJPlane();
				this.clearOBJPolygon();
			}else if(Caller.$id == 'Prebuild.analyzer'){
				this.$u.getUnity().SendMessage("VBLayer", "VBCreateLayer", "tag,true");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "1,/resources/commons/images/map/icon_tag_fixed.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBDefineImage", "2,/resources/commons/images/map/icon_tag_move.png");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColorEnable","true");
				this.$u.getUnity().SendMessage("VBLayer", "VBSetPickingColor","255,0,0");
				this.clearGraph();
				this.clearOBJPlane();
				this.clearOBJPolygon();
			}
		}
		
		this.$sbmList = new Array();
		this.$selectList = new Array();
		this.$copyOBJList = new Array(); 
		this.$componentList = new Array();
		this.$planeList = new Array();
		this.$polygonList = new Array();
		this.$poiList = new Array();
		
		this.$createSBMCount = 0;
		this.$createSBMTotalCount = 0;
		
	},
	clearPOI : function(){
		if(Caller != null){
			if(Caller.$id == 'Position'){
				var delpois = new Array();
				for(var i=0; i < this.$poiList.length; i++){
					delpois.push(this.$poiList[i])
				}
				var poiId = 0;
				for(var i=0; i < delpois.length; i++){
					 poiId = this.$poiList[i];
					this.deleteItem('position', poiId, 1);
					this.deleteItem('position', poiId, 2);
					this.deletePOI('position', poiId);
				}
				this.redraw();
			}else if(Caller.$id == 'Prebuild.analyzer'){
				var delpois = new Array();
				for(var i=0; i < this.$poiList.length; i++){
					delpois.push(this.$poiList[i])
				}
				var poiId = 0;
				for(var i=0; i < delpois.length; i++){
					poiId = delpois[i];
					this.deleteItem('tag', poiId, 1);
					this.deleteItem('tag', poiId, 2);
					this.deletePOI('tag', poiId);
				}
				this.redraw();
			}
		}
		
	},
	clearOBJ : function(){ //등록된 OBJ 모두 삭제
		this.log('Unity.clearOBJ');
		if(Caller != null){
			if(Caller.$id == 'Rap'){
				for(var i=0; i < Unity.$componentList.length; i++){
					var objId = Unity.$sbmId+"_Component_"+Unity.$componentList[i]+"_0";
					Unity.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
				}
				this.$componentList = new Array();
			}else if(Caller.$id == 'Panel'){
				for(var i=0; i < Unity.$componentList.length; i++){
					var objId = Unity.$sbmId+"_OBJ_"+this.$componentList[i]+"_0";
					Unity.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
				}
				this.$componentList = new Array();
			}else if(Caller.$id == 'Prebuild.planning'){
				for(var i=0; i < Unity.$componentList.length; i++){
					var objId = Unity.$sbmId+"_Component_"+Unity.$componentList[i]+"_0";
					Unity.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
				}
				this.$componentList = new Array();
				this.clearGraph();
				this.clearOBJPlane();
				this.clearOBJPolygon();
			}else if(Caller.$id == 'Prebuild.analyzer'){
				for(var i=0; i < Unity.$componentList.length; i++){
					var objId = Unity.$sbmId+"_Component_"+Unity.$componentList[i]+"_0";
					Unity.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
				}
				this.$componentList = new Array();
				this.clearGraph();
				this.clearOBJPlane();
				this.clearOBJPolygon();
			}
		}
		
	},
	clearOBJPlane : function(){
		for(var i=0, len = Unity.$planeList.length; i < len; i++){
			var objId = Unity.$sbmId+"_OBJ_"+Unity.$planeList[i]+"_0";
			Unity.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
		}
		Unity.$planeList = new Array();
	},
	clearOBJPolygon : function(){
		for(var i=0; i < Unity.$polygonList.length; i++){
			var objId = Unity.$sbmId+"_OBJ_"+this.$polygonList[i]+"_0";
			Unity.$u.getUnity().SendMessage("VBSBM", "VBDeleteOBJ", objId);
		}
		Unity.$polygonList = new Array();
	},
	clearPicking : function(){ //선택 OBJ 모두 선택 해제
		this.$u.getUnity().SendMessage("VBSBM", "VBClearPicking", null);
	},
	clearMousePicking : function(){ //픽킹 버튼 초기화
		this.$u.getUnity().SendMessage("VBInterface", "VBMousePickingNULL", "Main Camera");
	},
	clearOBJMaterialColor:function(objId){ //해당 OBJ 변경된 색상을 초기화 한다
		this.$u.getUnity().SendMessage("VBSBM", "VBReSetOBJMaterialColor", objId);
	},
	clearOBJChildMaterialColor:function(objId){ //해당 OBJ의 하위OBJ 포함 변경된 색상을 초기화 한다
		this.$u.getUnity().SendMessage("VBSBM", "VBReSetOBJChildMaterialColor", objId);
	},
	clearMaterialColorAll:function(){ //전체 변경된 색상을 초기화 한다
		this.$u.getUnity().SendMessage("VBSBM", "VBReSetMaterialColorAll", null);
	},
	clearGraph : function(){
		this.$u.getUnity().SendMessage("VBUtil", "VBClearGraph", null);
	},
	isPOI : function(poiId){
		return this.$poiList.contains(poiId);
	},
	isOBJ : function(objId){ // OBJ 등록 유무
		if(objId.indexOf('PLANE') != -1){
			return this.$planeList.contains(objId);
		}else if(objId.indexOf('BARRIER') != -1){
			return this.$polygonList.contains(objId);
		}else if(objId.indexOf('RAP') != -1){
			return this.$componentList.contains(objId);
		}else{
			return this.$objList.contains(objId);
		}
		return false;
	},
	componentPickingById : function(componentId){ // ID로 OBJ 선택 (하위 OBJ 포함)
		this.log("Unity.componentPickingById", componentId);
		this.$isPickingId = true;
		this.$u.getUnity().SendMessage("VBSBM", "VBObjChildPickingByID", componentId+",0,0,255");
	},
	poiPickingById : function(layer, poiId){ // ID로 POI를 선택
		this.$u.getUnity().SendMessage("VBLayer", "VBLayerPickingByID", layer+","+poiId+",Unity.callbackPickingPOI,Main Camera");
	},
	objPickingById : function(objId){ // ID로 OBJ 선택 (하위 OBJ 포함)
		this.log("Unity.objPickingById", objId);
		this.$isPickingId = true;
		this.$u.getUnity().SendMessage("VBSBM", "VBObjPickingByID", objId+",0,0,255");
	},
	mousePickingSBM : function(mouseButType){ //픽킹 버튼 추가 : 0 : 왼쪽버튼, 1 : 중간버튼, 2 : 오른쪽버튼
		this.$u.getUnity().SendMessage("VBInterface", "VBMousePickingSBM", mouseButType);
	},
	cameraMove : function(arg){ // 카메라를 해당 위치와 방향으로 분할할 개수만큼 나뉘어져서 이동한다
		this.log("Unity.cameraMove", arg);
		var arraylist = arg.split(",");
		this.$u.getUnity().SendMessage("VBCamera", "VBCameraMove", arraylist[1] + "," + arraylist[2] + "," + arraylist[3] + "," + arraylist[4] + "," + arraylist[5] + "," + arraylist[6] + ",50");
	},
	characterFalse : function(){
		this.log("Unity.characterFalse");
		if(this.$isCharacterEanble == "true"){                                                                            
			this.$isCharacterEanble = "false";                                                
			this.$u.getUnity().SendMessage("VBInterface", "VBMouseDefalut", null);           
//			this.$u.getUnity().SendMessage("VBLayer", "VBDeleteItem", "menu,1,15");          
//			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage","menu,1,14,14,57,85");
//			this.$u.getUnity().SendMessage("VBLayer", "VBReDraw", null);                     
		}                                                                            
	},
	redraw : function(){ // Layer POI 갱신
		this.$u.getUnity().SendMessage("VBLayer", "VBReDraw",null);
	},
	show : function(){
		$("#viewport-3d").show();
	},
	hide : function(){
		$("#viewport-3d").hide();
	},
	resize : function(width, height){
		if(this.$u != null){
			if(width != null){
				this.$u.getUnity().style.width = width + "px";	
			}
			if(height != null && height != undefined){
				this.$u.getUnity().style.height = height + "px";	
			}
			
		}
	},
	callbackCreateGKXML : function(arg){ //GKXML의 생성시 옵션 정보를 반환한다.
		this.$u.getUnity().SendMessage("VBGKXML", "VBGetBuildingExportOption",arg + ",Unity.exportSBM");
	},
	callbackCreateSBM : function(sbmId){ // SBM 생성시 반환함수 
		this.log('Unity.callbackCreateSBM', sbmId);
		this.$sbmList[this.$createSBMTotalCount] = sbmId;
		this.$createSBMTotalCount++;
		this.$createSBMCount--;
		this.$sbmId = sbmId; //도면 SBM ID
		Unity.createPOIType(sbmId);
		Unity.getSBMBounding(sbmId);
		if(Caller != null){
			this.$isMapInit = true;
			this.$u.getUnity().SendMessage("VBSBM", "VBGetCameraPositionEnable", "4,Unity.cameraMove");
			if(Caller.$id == 'Rap'){
				Caller.callback.addComponents(sbmId);
			}else if(Caller.$id == 'Position'){
				Caller.callback.addPOIs(sbmId);
			}else if(Caller.$id == 'Panel'){
				Caller.callback.addComponents(sbmId);
			}else if(Caller.$id == 'Prebuild.planning'){
				Caller.callback.addComponents(sbmId);
				this.getOBJIDList(sbmId);
			}else if(Caller.$id == 'Prebuild.analyzer'){
				Caller.callback.addComponents(sbmId);
				
			}
		}
		
	},
	callbackCreateOBJSphere : function(arg){
		this.log("Unity.callbackCreateOBJSphere", arg);
	},
	callbackCreateOBJPlane : function(objId){
		//this.log('Unity.callbackCreateOBJPlane', objId);
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning' || Caller.$id == 'Prebuild.analyzer' ){
				Caller.callback.createOBJPlane(objId);
			}
		}
	},
	callbackCreateOBJPolygon : function(objId){
		this.log("Unity.callbackCreateOBJPolygon", objId);
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning' || Caller.$id == 'Prebuild.analyzer'){
				Caller.callback.createOBJPolygon(objId);
			}
		}
	},
	callbackDefineImage : function(arg){ //이미지 정의시 반환함수
//		if(arg == "11"){
//			this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage","menu,1," + arg + "," + arg + ",57,85");
//			return;
//		}
//		this.$u.getUnity().SendMessage("VBLayer", "VBAddItemImage","menu,1," + arg + "," + arg + ",57,85");
//		this.$u.getUnity().SendMessage("VBLayer", "VBReDraw", null);
	},
	callbackGetSBMEnable : function(arg){
		this.log("Unity.callbackEnableSetLayer", arg);
		var enableSBMlist = arg.split(",");
		var i = 0;
		while( i < enableSBMlist.length){
			this.$u.getUnity().SendMessage("VBLayer", "VBSetLayerEnable", enableSBMlist[i]+ ",true");
			i++;
		}  	
		this.$u.getUnity().SendMessage("VBLayer", "VBReDraw", null);
	},
	callbackPickingLayer:function(arg){ //Layer픽킹 추가시 반환함수
		if(Caller != null){
			if(Caller.$id == 'Prebuild.analyzer'){
				var args = arg.split(',');
				Caller.callback.selectedPOI(args[0], args[1], args[2]);
			}
		}
	},
	callbackPickingSBM : function(arg){
		var args = arg.split(',');
		var objId = args[0];
		if(this.$isPickingId){
			this.clearPicking();
    		if(this.$lastPickingId != null){
    			if(this.$lastPickingId.indexOf('Component') != -1){ //컴포넌트
    				this.clearOBJChildMaterialColor(this.$lastPickingId);
    			}else if(this.$lastPickingId.indexOf('PLANE') != -1){ //PLANE
    				this.setOBJMaterialColor(this.$lastPickingId, {r:220, g: 220, b:220}, 25);
    			}else if(this.$lastPickingId.indexOf('BARRIER') != -1){ //장애물
    				this.setOBJMaterialColor(this.$lastPickingId, {r:255, g: 0, b:0}, 255);
    			}else{
    				this.clearOBJMaterialColor(this.$lastPickingId);
    			}
    		}
    		if(objId.indexOf('Component') != -1){
	    		this.setOBJChildMaterialColor(objId, {r:0, g: 0, b:255}, 125);
			}else {
				this.setOBJMaterialColor(objId, {r:0, g: 0, b:255}, 125);
			}
			this.$lastPickingId = objId;
			this.$isPickingId = false;
		}else{
			if(this.$lastPickingId != null){
    			if(this.$lastPickingId.indexOf('Component') != -1){ //컴포넌트
    				this.clearOBJChildMaterialColor(this.$lastPickingId);
    			}else if(this.$lastPickingId.indexOf('PLANE') != -1){ //PLANE
    				this.setOBJMaterialColor(this.$lastPickingId, {r:220, g: 220, b:220}, 25);
    			}else if(this.$lastPickingId.indexOf('BARRIER') != -1){ //장애물
    				this.setOBJMaterialColor(this.$lastPickingId, {r:255, g: 0, b:0}, 255);
    			}else{
    				this.clearOBJMaterialColor(this.$lastPickingId);
    			}
    		}
    		this.$lastPickingId = null;
		}
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning'){
				var localX = parseFloat(args[1]);
				var localZ = parseFloat(args[2]);
				var localY = parseFloat(args[3]);
			    if(objId.indexOf('Component') != -1){ //컴포넌트 선택시
			    	if(objId.indexOf('OBJ') != -1){ //Mouse 선택
			    		var componentId = objId.substring(0, objId.indexOf('_OBJ'));
					    var componentIds = componentId.split('_');
					    var sbmId = componentIds[0];
					    var rapId = componentIds[2];
					    this.$selectedComponentId = rapId;
					    this.deleteOBJ(componentId); // 원 Component 삭제
						this.$u.getUnity().SendMessage("VBSBM", "VBSetSBMMaterialAlpha", sbmId+",50"); // SBM 튜명도 조절
					    this.$u.getUnity().SendMessage("VBInterface", "VBMouseComponent", "RAP,"+localZ+",0,0,0,5,5,5,Main Camera"); // 마우스로 컴포넌트 배치모드 활성화
					    Caller.callback.selectedComponent(componentId, localX, -localY, localZ);
		 	    	}else{ // ID 선택
		 	    		Caller.callback.selectedComponent(objId, localX, -localY, localZ);
		 	    	}
				}else if(objId.indexOf('PLANE') != -1){ //그래프 선택 
					Caller.callback.selectedOBJPlane(objId, localX, -localY, localZ);
				}else if(objId.indexOf('BARRIER') != -1){ //장애물 선택 
					Caller.callback.selectedOBJPolygon(objId, localX, -localY, localZ);
				}else{
					if(this.$planeList.length == 0){
						Caller.callback.selectedOBJ(objId, localX, -localY, localZ);
					}
				}
			}else if(Caller.$id == 'Prebuild.analyzer'){
				var localX = parseFloat(args[1]);
				var localZ = parseFloat(args[2]);
				var localY = parseFloat(args[3]);
			    if(objId.indexOf('Component') != -1){ //컴포넌트 선택시
			    	Caller.callback.selectedComponent(objId, localX, -localY, localZ);
				}else if(objId.indexOf('PLANE') != -1){ //그래프 선택 
					Caller.callback.selectedOBJPlane(objId, localX, -localY, localZ);
				}else if(objId.indexOf('BARRIER') != -1){ //장애물 선택 
					Caller.callback.selectedOBJPolygon(objId, localX, -localY, localZ);
				}else{
					if(this.$planeList.length == 0){
						Caller.callback.selectedOBJ(objId, localX, -localY, localZ);
					}
				}
			}
		}
	},
	callbackPickingPOI : function(arg){ //POI선택시 반환함수
		this.log('Unity.callbackPickingPOI', arg)
		if(Caller != null){
			if(Caller.$id =='Position'){
				var args = arg.split(',');
				var layer = args[0];
				var poiId = args[1];
				Caller.callback.selectedPOI(layer, poiId);
			}else if(Caller.$id =='Prebuild.analyzer'){
				var args = arg.split(',');
				var layer = args[0];
				var poiId = args[1];
				Caller.callback.selectedPOI(layer, poiId);
			}
		}
	},
	callbackMsgToolComponent : function(arg){
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning'){
				var pickingInfo = arg.split(",");
				var objId = pickingInfo[0].split("_");
				var localX = parseFloat(pickingInfo[1]);
				var localZ = parseFloat(pickingInfo[2]);
				var localY = parseFloat(pickingInfo[3]);
			  
				this.$u.getUnity().SendMessage("VBSBMEdit", "VBAddComponentEnable", this.$sbmId + "," +this.$selectedComponentId + ",true");
				this.$u.getUnity().SendMessage("VBInterface", "VBMouseDefalut",null);//컴포넌트 추가 상태 취소
				this.$u.getUnity().SendMessage("VBSBM", "VBSetSBMMaterialAlpha", this.$sbmId+",255"); // SBM 튜명도 조절
				this.componentPickingById(this.$sbmId+"_Component_"+this.$selectedComponentId+"_0");
				if(!this.$componentList.contains(this.$selectedComponentId)){
					this.$componentList.push(this.$selectedComponentId);
				}
				Caller.callback.modComponent(this.$selectedComponentId, localX, -localY, localZ);
				this.log(this.$componentList);
			}
		}
	},
	callbackEditComponent : function(arg){ //Component 이동시 반환함수
		if(Caller != null){
			if(Caller.$id == 'Rap'){ //RAP 위치 수정 및 등록
				var pickingInfo = arg.split(",");
				var objId = pickingInfo[0];
				var localX = parseFloat(pickingInfo[1]);
				var localZ = parseFloat(pickingInfo[2]);
				var localY = parseFloat(pickingInfo[3]);
				Caller.callback.modComponent(objId, localX, -localY, localZ);
				this.log(this.$componentList);
			}
		}
	},
	callbackEditLayer : function(arg){ // POI 이동시 반환함수
		if(Caller != null){
			if(Caller.$id == 'Position'){ //위치 수정 및 등록
				var pickingInfo = arg.split(",");
				var layer = pickingInfo[0];
				var poiId = pickingInfo[1];
				var localX = parseFloat(pickingInfo[2]);
				var localZ = parseFloat(pickingInfo[3]);
				var localY = parseFloat(pickingInfo[4]);
				Caller.callback.modPOI(layer, poiId, localX, -localY, localZ);
			}else if(Caller.$id == 'Prebuild.analyzer'){ //위치 수정 및 등록
				var pickingInfo = arg.split(",");
				var layer = pickingInfo[0];
				var poiId = pickingInfo[1];
				var localX = parseFloat(pickingInfo[2]);
				var localZ = parseFloat(pickingInfo[3]);
				var localY = parseFloat(pickingInfo[4]);
				Caller.callback.modPOI(layer, poiId, localX, -localY, localZ);
			}
		}
	},
	callbackDrawPolygon : function(arg){
		this.log('Unity.callbackDrawPolygon', arg);
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning'){
				// 마우스 초기화
				Unity.$u.getUnity().SendMessage("VBInterface", "VBMouseDefalut", null);
				$('#but-default > img').attr('src' , '/resources/commons/images/3d/icon_default_on.png');
				$('#but-default').addClass('ui-selected');
				$('#but-distance-2d > img').attr('src' , '/resources/commons/images/3d/icon_distance_2d_off.png');
				$('#but-distance-3d > img').attr('src' , '/resources/commons/images/3d/icon_distance_3d_off.png');
				$('#but-draw-polygon > img').attr('src' , '/resources/commons/images/3d/icon_draw_polygon_off.png');
				$('#but-distance-2d').removeClass('ui-selected');
				$('#but-distance-3d').removeClass('ui-selected');
				$('#but-draw-polygon').removeClass('ui-selected');
				
				// Main Camera,519.9742,270,-628.0619,1656.286,270,-630.6426,1656.176,270,-649.5505,519.6542,270,-649.5103,519.9193,270,-626.7913
				arg = arg.substring(arg.indexOf(',')+1, arg.length);
				Caller.callback.drawPolygon(arg);
			}
		}
	},
	callbackGetOBJPositionByScreen : function(arg){
		this.log('Unity.callbackCopySBMOBJ', arg);	
	},
	callbackCopySBMOBJ : function(arg){ // OBJ Copy시  반환함수
		this.log('Unity.callbackCopySBMOBJ', arg);		
	},
	callbackMouseMSG : function(arg){
		var arraylist = arg.split(",");
		if(arraylist[0] == "LButtonUp" && this.$isCameraAction == true){
			this.$isCameraAction = false;
			this.$u.getUnity().SendMessage("VBCamera", "VBCameraAction", "-1");
		}
		if(arraylist[0] == "LButtonDblClick"){
			this.$u.getUnity().SendMessage("VBSBM", "VBGetOBJPositionByScreen", arraylist[1] +"," + arraylist[2]+",Unity.callbackGetOBJPositionByScreen");
		}
	},
	callbackKeyMSG : function(arg){
		var arraylist = arg.split(",");
		if(arraylist[0] == "LeftControlDown" && this.$selectList.length > 0){
			this.$copyOBJList = this.$selectList.slice(0);
		}
		if(arraylist[0] == "DownArrowDown" && this.$copyOBJList.length > 0){
			var strCopyOBJList = "";
			var strCopyOBJIDList = "";
			strCopyOBJList = this.$copyOBJList[0];
			strCopyOBJIDList = "0";
			var iCopyOBJList = 1;
			while( iCopyOBJList < this.$copyOBJList.length){
				strCopyOBJList = strCopyOBJList + ";" + this.$copyOBJList[iCopyOBJList];
				strCopyOBJIDList = strCopyOBJIDList + ";" + iCopyOBJList;
				iCopyOBJList++;
			}
		  
		  var strtestSBMID = this.$copyOBJList[0].split("_");
		  this.$u.getUnity().SendMessage("VBSBMEdit","VBCopySBMOBJ",strCopyOBJList + "," + strtestSBMID[0] + "," + strCopyOBJIDList + ",Unity.callbackCopySBMOBJ,Main Camera");
		}
	},
	callbackCreateOBJSphere : function(arg){
		this.log('Unity.callbackCreateOBJSphere', arg);
	},
	callbackTransform : function(arg){
		this.log('Unity.callbackTransform', arg);
	},
	callbackSBMTransform : function(arg){
		this.log('Unity.callbackSBMTransform', arg);
	},
	callbackOBJTransform : function(arg){
		this.log('Unity.callbackOBJTransform', arg);
	},
	callbackSBMCount : function(arg){
		this.log('Unity.callbackSBMCount', arg);
	},
	callbackOBJCount : function(arg){
		this.log('Unity.callbackOBJCount', arg);
	},
	callbackOBJIDList : function(arg){
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning'){
				Caller.callback.addOBJs(arg);
			}
		}
		var infos = arg.split(',');
		var item = '';
		for(var i=0; i < infos.length; i++){
			item = infos[i];
			if(item.indexOf('RAP') != -1){
				this.log(item);		
			}else{
				//console.log(item);
			}
		}
	},
	callbackBounding : function(arg){
		this.log('Unity.callbackBounding', arg);
	},
	callbackCenterSize : function(arg){
		this.log('Unity.callbackCenterSize', arg);
	},
	callbackSBMBounding : function(arg){
		this.log('Unity.callbackSBMBounding', arg);
		if(Caller != null){
			if(Caller.$id == 'Prebuild.planning' || Caller.$id == 'Prebuild.analyzer'){
				//0,0.0001220703,0,-1662,1918,270,68
				var args = arg.split(',');
				var x = parseFloat(args[4]).toFixed(2);
				var y = parseFloat(args[3]).toFixed(2) * -1;
				var z = parseFloat(args[5]).toFixed(2);
				Caller.callback.mapSize(x, y, z);
			}
		}
	},
	callbackSBMCenterSize : function(arg){
		this.log('Unity.callbackSBMCenterSize', arg);
	},
	callbackOBJCenterSizeByID : function(arg){
		this.log('Unity.callbackOBJCenterSizeByID', arg);
	},
	callbackPickingList : function(arg){
		this.log('Unity.callbackPickingList', arg);
	},
	callbackAreaById : function(arg){
		this.log('Unity.callbackAreaById', arg);
	},
	callbackItemValue : function(arg){
		this.log('Unity.callbackItemValue', arg);
		if(Caller != null){
			if(Caller.$id == 'Position'){
				var args = arg.split(',');
				var layer = args[0];
				var poiId = args[1];
				var itemId = args[2];
				var value = args[3];
				Caller.callback.itemValue(layer, poiId, itemId, value);
			}
		}
	}
};

function VBError(arg){ //유니티 에러 메시지 리턴
	Unity.log("VBError", arg);
}
function VBInit(arg){ //유티니 초기화 상태 리턴
	//Unity.log("VBInit", arg);
	if(arg == "VBSocket"){}
	if(arg == "VBCamera"){Unity.initCamera();}
	if(arg == "VBInterface"){Unity.initInterface();}
    if(arg == "VBSBM"){Unity.initLodingBar();}
	if(arg == "VBSBMEdit"){Unity.defineComponent();}
	if(arg == "VBGKXML"){
		Unity.createGKXML()
		//Unity.createSBM(Unity.$sbmPath,'map_1.sbm', 'map');
		//Unity.createTexture(Unity.$sbmPath, Unity.$texturePath, 'map_1.sbm', 'map');
	};
	if(arg == "VBLayer") {
		Unity.initLayer();
		Unity.initToolbar();
	}
}

function VBCreateGKXML(arg){ // GKXML의 생성시 옵션 정보를 반환한다.
	Unity.log("VBCreateGKXML", arg);
	Unity.callbackCreateGKXML(arg);
}

function VBCreateSBM(arg){ //SBM 생성시 반환 함수
	Unity.log("VBCreateSBM", arg);
	Unity.callbackCreateSBM(arg);
}
function VBClearSBM(arg){ //SBM 생성시 반환 함수
	Unity.log("VBClearSBM", arg);
}
function VBDefineComponent(arg){ //COMPONENT 선언시 반환 함수
	Unity.log("VBDefineComponent", arg);
}
function VBDefineImage(arg){ //2D 내비게이션 메뉴 드로우
	Unity.log("VBDefineImage", arg);
	Unity.callbackDefineImage(arg);
}
function VBMouseMsg(arg){ //눌려진 마우스 버튼 정보 반환
	//Unity.log("VBMouseMsg = "+arg);
	Unity.callbackMouseMSG(arg);
}

function VBKeyMsg(arg){ //눌려진 Key 버튼을 반환한다.
	Unity.log("VBKeyMsg = "+arg);
	Unity.callbackKeyMSG(arg);
}

function VBPickingLayer(arg){ //해당 픽킹 버튼에 Layer픽킹을 추가시 반환함수
	Unity.log("VBPickingLayer", arg);
	Unity.callbackPickingLayer(arg);
}

function VBPickingSBM(arg){ //SBM Picking 시 반환 함수
	Unity.log("VBPickingSBM", arg);
	Unity.callbackPickingSBM(arg);
}

function VBMsgToolComponent(arg){ //마우스에서 클릭 업버튼일시 픽킹 결과 알려줌
	Unity.log("VBMsgToolComponent", arg);
	Unity.callbackMsgToolComponent(arg);
}

function VBEditComponent(arg){ // 마우스 Component 이동시 반환함수 
	Unity.log("VBEditComponent", arg);
	Unity.callbackEditComponent(arg);
}
function VBEditLayer(arg){ // 마우스 POI 이동시 반환함수 
	Unity.log("VBEditLayer", arg);
	Unity.callbackEditLayer(arg);
}
