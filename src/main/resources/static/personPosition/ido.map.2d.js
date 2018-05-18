;(function($) {
    /*
     * jQuery Observer pattern 
     * inspired by @addyosmani 's code
     * see: http://addyosmani.com/resources/essentialjsdesignpatterns/book/#highlighter_506612
     */
    var topics = [];
    function getTopic(id) {
        var callbacks;
        topic = id && topics[id];
        if (!topic) {
            callbacks = $.Callbacks();
            topic = {
                publish: callbacks.fire,
                subscribe: callbacks.add,
                unsubscribe: callbacks.remove
            };
            if (id) topics[id] = topic;
        }
        return topic;
    }
    $.observer = {
        publish: function(id, item) {
        	var t = getTopic(id);
        	return t.publish.apply(t, item);
        },
        subscribe: function(id, fn) {
            return getTopic(id).subscribe(fn);
        },
        unsubscribe: function(id, fn) {
            return getTopic(id).unsubscribe(fn);
        }
    };
})(jQuery);

var Map = {
	$mapWidth : 0, $mapHeight : 0,
	$target : null, //호출할 객체
	$curveDatas:new HashMap(),
	//$lastX : null, $lastY : null,//add
	//$beforeLastX : null, $beforeLastY : null,//add
	init : function(target){ // MAP 초기화(도면키, 보기유무, 툴바기능정의, 이벤트유무)
		this.$target = target;
		this.ui.viewport();
		this.ui.tool();
		this.ui.grid();
		this.ui.ruler();
		if(target.$id == 'Panel'){
			target.callback.addMarkers();
		}
	},
	ui : {
		$viewport : null, 
		$ruler: {top : null, left : null},
		$tool: {button : null},
		viewport : function(){ //VIEW PORT 생성
			var height = parseInt(Map.$target.$plan.height);
			var html = '<div id="map">';
			html += '<div id="map-gruler-top"></div>';
			html += '<div id="map-gruler-left"></div>';
			html += '<div id="map-tool-button"></div>';
			html += '<div id="map-viewport"></div>';
			html += '</div>';
			$('#viewport').html(html);
			this.$viewport = $('#map-viewport');
			this.$viewport.css("width", "100%");
			this.$viewport.css("height", height+"px");
			this.$viewport.css('background-image','url(/files/plan/map_'+Map.$target.$plan.planId+'.'+Map.$target.$plan.ext+'?time='+ new Date().getTime() +')');
			this.$viewport.css('background-repeat', 'no-repeat');
			Map.$mapWidth = this.$viewport.width();
			Map.$mapHeight = this.$viewport.height();
			$('#map-gruler-top').css('width', Map.$mapWidth+'px');
			$('#map-gruler-left').css('height', Map.$mapHeight+'px');
			this.$ruler.top = new Raphael("map-gruler-top", Map.$mapWidth, 16);	
			this.$ruler.left = new Raphael("map-gruler-left", 16, Map.$mapHeight);	
		},
		resize : function(){
			$.map.ruler.top.clear();
			$.map.ruler.left.clear();
			Map.$mapWidth = this.$viewport.width();
			Map.$mapHeight = this.$viewport.height();
			$('#map-gruler-top').css('width', Map.$mapWidth+'px');
			$('#map-gruler-left').css('height', Map.$mapHeight+'px');
			this.$ruler.top.setSize(Map.$mapWidth,  16);
			this.$ruler.left.setSize(16,  Map.$mapHeight);
			console.log(Map.$mapWidth, Map.$mapHeight);
			this.ruler();
		},
		tool : function(){ // 툴바생성
			Map.ui.$tool.button = $('#map-tool-button');
			Map.ui.$tool.button.css('background-image', 'url(/resources/commons/images/map/icon_3d.png)').css('background-size', '16px 16px').css('background-repeat', 'no-repeat');
			Map.ui.$tool.button.click(function(e) {
				if(Map.$target != null && Map.$target.$id == 'Panel'){
					Map.$target.$isInit = false;
					Map.$target.ui.clearIndoorNode();
					Map.$target.ui.$mapType = '3D';
					Map.$target.ui.indoorMap();
				}
				
			});
		},
		grid : function(){ // 그리드생성
			Map.canvas.removeSvg("gline", "gline");
			var path100 = '', path50 = '';
			var dist = 0;
			var pixels = Map.$target.$plan.pixels;
			/*
			if(pixels >= 50){
				dist = 10;
			}else if(pixels >= 20){
				dist = 5;
			}else{
				dist = 2.5;
			}
			*/
			if(pixels >= 50){
				dist = 10;
			}else if(pixels >= 25){
				dist = 5;
			}else if(pixels >= 12.5){
				dist = 2.5;
			}else if(pixels >= 6.25){
				dist = 1.25;
			}else if(pixels >= 3.125){
				dist = 0.625;
			}else if(pixels >= 1.5){
				dist = 0.3125;
			}else if(pixels >= 0.75){
				dist = 0.15625;
			}else{
				dist = 0.078125;
			}
			var meter = pixels/dist;
			var count = Math.round(Map.$mapWidth/meter);
			for(var i=1; i <= count; i++){
				if(i % 10 == 0){
					path100 += 'M'+(i * meter)+' 0L'+(i * meter)+' '+Map.$mapHeight;
				}else if(i % 5 == 0){
					path50 += 'M'+(i * meter)+' 0L'+(i * meter)+' '+Map.$mapHeight;	
				}
			}
			path100 += 'Z';
			path50 += 'Z';
			Map.canvas.drawLine('gline', path100, 1, '#a4a4a4', 'line');
			Map.canvas.drawLine('gline', path50, 1, '#808080', 'dot');
			
			path100 = '', path50 = '';
			meter = pixels/dist;
			count = Math.round(Map.$mapHeight/meter);
			for(var i=1; i <= count; i++){
				if(i % 10 == 0){
					path100 += 'M0 '+(i * meter)+'L'+Map.$mapWidth+' '+(i * meter);
				}else if(i % 5 == 0){
					path50 += 'M0 '+(i * meter)+'L'+Map.$mapWidth+' '+(i * meter);	
				}
			}
			Map.canvas.drawLine('gline', path100, 1, '#a4a4a4', 'line');
			Map.canvas.drawLine('gline', path50, 1, '#808080', 'dot');
		},
		ruler : function(){ // ruler생성
			this.$ruler.top.clear();
			this.$ruler.left.clear();
			$('#map-gruler-top').children('svg').children().each(function(){
				$(this).remove();
			});
			$('#map-gruler-left').children('svg').children().each(function(){
				$(this).remove();
			});
			// TOP RULER
			var path = '', t;
			var dist = 0;
			var pixels = Map.$target.$plan.pixels;
			/*
			if(pixels >= 50){
				dist = 10;
			}else if(pixels >= 20){
				dist = 5;
			}else{
				dist = 2.5;
			}
			*/
			if(pixels >= 50){
				dist = 10;
			}else if(pixels >= 25){
				dist = 5;
			}else if(pixels >= 12.5){
				dist = 2.5;
			}else if(pixels >= 6.25){
				dist = 1.25;
			}else if(pixels >= 3.125){
				dist = 0.625;
			}else if(pixels >= 1.5){
				dist = 0.3125;
			}else if(pixels >= 0.75){
				dist = 0.15625;
			}else{
				dist = 0.078125;
			}
			var meter = pixels/dist;
			var count = Math.round(Map.$mapWidth/meter);
			
			if(pixels < 50){
				for(var i=1; i <= count; i++){
					if(i % 10 == 0){
						path += 'M'+(i * meter)+' 1L'+(i * meter)+' 16';
						t = this.$ruler.top.text((i * meter)+13, 4, (i/dist)+'m');
						t.node.id = 'gruler-text';
					}else if(i % 5 == 0){
						path += 'M'+(i * meter)+' 6L'+(i * meter)+' 16';	
					}else{
						path += 'M'+(i * meter)+' 10L'+(i * meter)+' 16';	
					}
				}
			}else{
				for(var i=1; i <= count; i++){
					if(i % 10 == 0){
						path += 'M'+(i * meter)+' 1L'+(i * meter)+' 16';
						t = this.$ruler.top.text((i * meter)+13, 4, (i/dist)+'m');
						t.node.id = 'gruler-text';
					}else if(i % 5 == 0){
						path += 'M'+(i * meter)+' 6L'+(i * meter)+' 16';	
					}else{
						path += 'M'+(i * meter)+' 10L'+(i * meter)+' 16';	
					}
				}
			}
			path += 'Z';
			var p = this.$ruler.top.path(path);
			p.attr ("stroke-width", 1);
			p.attr ("stroke", '#000000');
			p.node.id = 'gruler';
			
			// LEFT RULER
			path = '';
			meter = pixels/dist;
			count = Math.round(Map.$mapHeight/meter);
			for(var i=1; i <= count; i++){
				if(i % 10 == 0){
					path += 'M1 '+(i * meter)+'L16 '+(i * meter);
					t = this.$ruler.left.text(4, (i * meter)+13, (i/dist)+'m');
					t.transform('R-90')
					t.node.id = 'gruler-text';
				}else if(i % 5 == 0){
					path += 'M6 '+(i * meter)+'L16 '+(i * meter);	
				}else{
					path += 'M10 '+(i * meter)+'L16 '+(i * meter);	
				}
				
			}
			path += 'Z';
			p = this.$ruler.left.path(path);
			p.attr ("stroke-width", 1);
			p.attr ("stroke", '#000000');
			p.node.id = 'gruler';
		},
		 
	},
	marker : {
		add : function(item){
			Map.ui.$viewport.append(item.marker);
		},
		remove : function(id){
			Map.ui.$viewport.children().each(function(){
				if($(this).attr('id') == id){
					$( this ).remove();
				}
			});
		},
		removeAll : function(){
			Map.ui.$viewport.children().each(function(){
				if($( this ).attr('name') == 'marker'){
					$( this ).remove();
				}
			});
		},
		
	},
	canvas : {
		$papers : new HashMap(), $items : new HashMap(),
		paper : function(id){ 
			var paper = this.$papers.get(id);
			if(paper == null || $("#map-viewport-" + id).length == 0){
				$("#map-viewport").append("<div id='map-viewport-"+id+"' style='position:absolute; padding:0px; margin:0px; left : 0px; top : 0px; width:"+$("#map-viewport").width()+"px; height: "+$("#map-viewport").height()+"px;'></div>")
				paper = new Raphael("map-viewport-"+id, $("#map-viewport").width(), $("#map-viewport").height());
				this.$papers.put(id, paper);
			}
			return paper;
		},
		clearPaper : function(){
			var keys = this.$items.keys();
			for(var i=0; i < keys.length; i++){
				this.paper(keys[i]).clear();	
			}
			this.$items.clear();
		},
		clearNode : function(){ // Canvas 초기화
			Map.canvas.removeSvg('src_node');
			var keys = this.$items.keys();
			for(var i=0; i < keys.length; i++){
				Map.canvas.removeSvg(keys[i], 'target_path_'+keys[i]);
				Map.canvas.removeSvg(keys[i], 'target_node_'+keys[i]);
				Map.canvas.removeSvg(keys[i], 'target_text_'+keys[i]);
				this.$items.remove(keys[i]);
			}
		},
		clearPath : function(){ // Canvas 초기화
			var keys = this.$items.keys();
			for(var i=0; i < keys.length; i++){
				Map.canvas.removeSvg(keys[i], 'src_node');
				Map.canvas.removeSvg(keys[i], 'target_path_'+keys[i]);	
			}
		},
		removeNode : function(id){
			this.$items.remove(id);
			this.removeSvg(id, 'target_node_'+id);
			this.removeSvg(id, 'target_text_'+id);
			this.removeSvg(id, 'target_path_'+id);
		},
		removeSvg : function(id , nodeId){
			$('#map-viewport-'+id).children('svg').children().each(function(){
				if($(this).attr('id') == nodeId){
					$(this).remove();
				}
			});
		},
		drawLine : function(id, path, border, color, type){
			var paper = this.paper(id)
			var p = paper.path(path);
			if(border == null && border == undefined) border = 1;
			if(color == null && color == undefined) color = '#FF0000';
			if(type != null && type != undefined  && type == 'dot'){
				p.attr ("stroke-dasharray", '. ');
				
			}
			if(id != null && id != undefined){
				p.node.id = id;
			}
			p.attr ("stroke-width", border);
			p.attr ("stroke", color);
			return p;
		},
		initNode : function(id, x, y, size, color, name){
			$.observer.unsubscribe(id, Map.canvas.moveNode);
			var paper = this.paper(id);
			var title = $('<div/>').html("姓名:"+name+
						"&#10;颜色:"+color+
						"&#10;MAC地址:"+id).text();
			var node = paper.circle(x, y, size).attr({ "stroke": "#000000", "stroke-width": 1, "stroke-opacity": 0.5, "fill": ''+color, "fill-opacity" : 0.5, "title" : title});
			node.node.id = "target_node_"+id;
			var text = paper.text(x, y+size+4, name);
			text.attr({'font-size':'11px', 'fill':'#000000', 'font-weight':'nolmal'});
			text.node.id = 'target_text_'+id;
			this.$items.put(id, {
				'euid' : id,
				'node' : node,
				'text' : text,
				'name' : name,
				'queue' : [],
				'aniData' : {},
				'size' :  size,
				'color' : color
			});
			$.observer.subscribe(id, Map.canvas.moveNode);
		},
		addItem : function(id, seq, name, euid, x, y, time, processTime, movePath, distance, status){
			var item = this.$items.get(id);
			if(item == null || item == undefined){
				return false;
			}
			var paper = this.paper(id);
			
			if(Map.$target.worker.$isLineMovement && Map.$curveDatas.get(euid) && Map.$curveDatas.get(euid).lastX && Map.$curveDatas.get(euid).beforeLastX){
				//平滑二次贝塞尔曲线
				//var srcnode = paper.path("M" + Map.$lastX +" "+ Map.$lastY +" T"+ x +" "+ y).attr({ "stroke": "green", "stroke-width": 1, "stroke-opacity": 1, "fill": ''+item.color, "fill-opacity" : 1 });
				//直线
				//var srcnode = paper.path("M" + Map.$lastX +" "+ Map.$lastY +" L"+ x +" "+ y).attr({ "stroke": "red", "stroke-width": 1, "stroke-opacity": 1, "fill": ''+item.color, "fill-opacity" : 1 });
				//打点圆圈
				//var srcnode = paper.circle(x, y, 4).attr({ "stroke": "#000000", "stroke-width": 1, "stroke-opacity": 0.5, "fill": ''+item.color, "fill-opacity" : 0.3 });
				//平滑三次贝塞尔曲线
				var srcnode = paper.path("M"+Map.$curveDatas.get(euid).beforeLastX+" "+Map.$curveDatas.get(euid).beforeLastY+",S"+Map.$curveDatas.get(euid).lastX+" "+Map.$curveDatas.get(euid).lastY+" " +x+" "+y).attr({ "stroke": ''+item.color, "stroke-width": 2, "fill": 'none' });
				//二次贝塞尔曲线
				//var srcnode = paper.path("M"+Map.$beforeLastX+" "+Map.$beforeLastY+"Q"+Map.$lastX+" "+Map.$lastY+" " +x+" "+y).attr({ "stroke": "purple", "fill": 'none' });
				
				srcnode.node.id = 'src_node';
				
			}else if(Map.$target.worker.$isCircleMovement){
				//打点圆圈
				var srcnode = paper.circle(x, y, 4).attr({ "stroke": "#000000", "stroke-width": 1, "stroke-opacity": 0.5, "fill": ''+item.color, "fill-opacity" : 0.3 });
				
				srcnode.node.id = 'src_node';
			}
			
			//Map.$beforeLastX = Map.$lastX; Map.$beforeLastY = Map.$lastY;
			//Map.$lastX = x; Map.$lastY = y;
			var curveData = new Object();
			if(Map.$curveDatas.get(euid)){
				curveData.beforeLastX=Map.$curveDatas.get(euid).lastX;
				curveData.beforeLastY=Map.$curveDatas.get(euid).lastY;
				curveData.lastX=x;
				curveData.lastY=y;
			}else{
				curveData.lastX=x;
				curveData.lastY=y;
			}
			Map.$curveDatas.put(euid,curveData);
			
//			if(status == 1){
//				Map.canvas.removeSvg(id, 'target_node_'+id);
//				Map.canvas.removeSvg(id, 'target_text_'+id);
//				item.node = paper.circle(x, y, item.size).attr({ "stroke": "#000000", "stroke-width": 1, "stroke-opacity": 0.5, "fill": ''+item.color, "fill-opacity" : 1 });
//				item.node.node.id = "target_node_"+id;
//				item.text = paper.text(x, y+(item.size + 4), name);
//				item.text.attr({'font-size':'11px', 'fill':'#000000', 'font-weight':'normal'});
//				item.text.node.id = 'target_text_'+id;
//			}
			
			item.queue.push({
					'seq' : seq, 
					'name' : name,
					'euid' : euid,
					'x' : x, 
					'y' : y,
					'time' : time, 
					'processTime' : processTime,
					'distance' : distance,
					'movePath' : movePath
			});
			$.observer.publish(id, [item]);
			return true;
		},
		moveNode : function(){
			var item = arguments[0];
			item.aniData = item.queue.shift();
			if(Map.$target.worker.$isCircleMovement){
				//1.1.4.6中注释掉了下面这段
//				Map.canvas.removeSvg(item.euid, 'target_path_'+item.euid);
//				var tpath = "";
//				for(var i=0; i < item.aniData.movePath.length; i++){
//					if(i==0){
//						tpath += "M"+ item.aniData.movePath[i].x+" "+ item.aniData.movePath[i].y;
//					}else{
//						tpath += "L"+ item.aniData.movePath[i].x+" "+ item.aniData.movePath[i].y;
//					}
//				}
//				var targetPath = Map.canvas.paper(item.euid).path(tpath);
//				targetPath.attr ("stroke-width", 1);
//				targetPath.attr ("stroke", '#FF0000');
//				targetPath.node.id = 'target_path_'+item.euid;
			}
			
			item.aniData.movePath.shift();
			Map.canvas.animateNode(item);
		},
		animateNode : function(item){
			var point = item.aniData.movePath.shift();
			if(point != undefined){
				item.node.animate({cx : point.x, cy : point.y}, 10, function () {
					if(item.aniData.movePath.length == 0){
						//console.log(item.aniData.processTime+"ms", ($.now()-item.aniData.time)+"ms", item.queue.length);	
					}else{
						Map.canvas.animateNode(item);
					}
				});
				item.text.animate({x : point.x, y : (parseInt(point.y) + parseInt(item.size) + 4)}, 10, function () {
					
				});
			}
			
		}
		
	}
};