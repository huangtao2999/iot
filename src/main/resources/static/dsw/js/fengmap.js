'use strict';

+function (win, doc, $, undefined) {
    //定义地图类
    function DswFengmap(el, options) {
        this.el = el;
        this.map = null;
        this.imageLayer = null;
        this.textLayer = null;
        this.popMarker = null;
        this.tagMap = new Map();
        this.timer = 0;
        this.client = null;
        this.currentTagEuid = '';
        this.previousTagEuid = '';
        this.previousZoneId = 0;
        this.previousAvatar = '';

        this.settings = $.extend(true, {}, DswFengmap.defaults, $(el).data(), options || {});

        privateMethod.init(this);
    }

    //默认配置
    DswFengmap.defaults = {
        appKey: 'c8e965cef46ca96287c7e4acb88ee327',
        appName: 'zoodac_haungshigang',
        baseUrl: '/personPosition/data/',
        sceneId: 'zoodac-huangshigang',
        themeName: '2002',

        multiplePop: false,//是否可以创建多个 弹层
        crontabInterval: 5000,//定时任务间隔时间 单位 ms
        numPerPage: 200,//每页显示 数据量
        tagUrl: '',//获取标签 的 地址
        positionUrl: '',//定位地址
        moveDuration: 1,//移动持续时间 单位 s
    };

    //常量配置
    DswFengmap.KEY_INSTANCE = 'dsw-fengmap-instance';
    DswFengmap.KEY_ACTION = 'dsw-fengmap-action';
    DswFengmap.KEY_TAG = 'dsw-fengmap-tag';
    DswFengmap.KEY_TAG_POSITION = 'dsw-fengmap-tag-position';
    DswFengmap.KEY_TAG_EUID = 'dsw-fengmap-tag-euid';
    DswFengmap.KEY_IMAGE_MARKER = 'dsw-fengmap-image-marker';
    DswFengmap.KEY_TEXT_MARKER = 'dsw-fengmap-text-marker';
    DswFengmap.KEY_POP_INFO = 'dsw-fengmap-pop-info';

    DswFengmap.VAL_ADD = 1;
    DswFengmap.VAL_MOVE = 2;

    //实现静态方法
    $.extend(true, DswFengmap, {});

    //实现共有方法
    $.extend(true, DswFengmap.prototype, {});

    //实现私有方法
    var privateMethod = {
        init: function (self) {
            this.createMap(self);
            this.cleanResource(self);
        },
        createMap: function (self) {
            var _self = this;
            var settings = self.settings;
            //实例化地图
            self.map = new fengmap.FMMap(this.mapOptions(self, settings));
            //显示指南针
            self.map.showCompass=true;
            //显示地图
            self.map.openMapById(settings.sceneId);
            self.map.showCompass=true;

            // 绑定地图事件
            self.map.on('loadComplete', function (e) {
                _self.initLayer(self).labelVisibilty(self,false).startRequest(self).startSocket(self);
            }).on('mapClickNode', function (e) {
                // console.log(e);
                switch (e.nodeType) {
                    case fengmap.FMNodeType.IMAGE_MARKER:
                    case fengmap.FMNodeType.TEXT_MARKER: {
                        self.currentTagEuid = e[DswFengmap.KEY_TAG_EUID];
                        _self.switchVCR(self, e.target);
                        break;
                    }
                    default: {
                        break;
                    }
                }

                return false;
            }).on('mapClickCompass', function() {
                self.map.rotateTo({
                    to: 0,
                    duration: .3
                })
            });
        },
        mapOptions: function (self, settings) {
            var options = new fengmap.MapOptions({
                container: self.el,
                key: settings.appKey,
                appName: settings.appName,

                mapServerURL: settings.baseUrl + settings.sceneId,
                mapThemeURL: settings.baseUrl + 'theme',
                defaultThemeName: settings.themeName,

                defaultVisibleGroups: [1],           //设置初始显示楼层，数组格式，可单个，可多个
                defaultFocusGroup: 1,               //初始聚焦楼层
                defaultGroupSpace: 40, //两楼层间的高度

                mapScaleLevelRange: [16, 23],       // [15, 29], 比例尺级别范围， 16级到23级
                // mapScaleRange: [200, 4000]      // [200, 4000]， 自定义比例尺范围，单位（厘米）
                defaultMapScaleLevel: 23,          // 默认比例尺级别设置为19级
                // defaultMapScale: 1000,          // 默认自定义比例尺为 1：1000（厘米）

                defaultControlsPose: fengmap.FMDirection.NORTH,
                // defaultControlsPose: 90,     //角度值。

                defaultViewMode: fengmap.FMViewMode.MODE_3D, //初始二维还是三维状态，三维状态为MODE_3D

                modelSelectedEffect: true,          //支持单击模型高亮，false为单击时模型不高亮

                focusAnimateMode: true,            //开启聚焦层切换的动画显示
                viewModeAnimateMode: true,          //开启2维，3维切换的动画显示
                moveToAnimateMode: true,           //地图定位跳转动画设置
                naviLineAnimation: true,            //路径线流动动画开关。false关闭

                lazyCreateMode: false,
                useStoreApply: true,
                focusAlphaMode: true,
                focusAlpha: 0,
                compassOffset: [5, 20], //初始指南针的偏移量
                compassSize: 48, //指南针大小默认配置
            });

            return options;
        },
        initLayer: function (self) {
            var groupLayer = this.getGroup(self);
            self.imageLayer = new fengmap.FMImageMarkerLayer();
            self.textLayer = new fengmap.FMTextMarkerLayer();
            groupLayer.addLayer(self.imageLayer);
            groupLayer.addLayer(self.textLayer);

            return this;
        },
        labelVisibilty:function(self,isShow){

            this.getGroup(self).traverse(function(fm, deep) {
                console.log(fm);
                if (fm instanceof fengmap.FMLabelLayer) {
                    fm.visible = isShow || false;
                }
            });

            return this;
        },
        getGroup: function (self) {
            return self.map.getFMGroup(1);
        },
        startSocket: function (self) {
            var _self = this;

            var socket = new SockJS(self.settings.positionUrl+'/rtls/sockjs');

            self.client = Stomp.over(socket);

            self.client.debug = false;

            self.client.connect({}, function (frame) {
                var unsubscribe = this.subscribe('/queue/position', function (message) {
                    var body = JSON.parse(message.body);
                    var tagEuid = body.tagEuid;
                    var tag = self.tagMap.get(tagEuid);

                    if (body.errorCase==0) {
                        if (tag && tag[DswFengmap.KEY_ACTION] === DswFengmap.VAL_MOVE) {
                            tag[DswFengmap.KEY_TAG_POSITION] = body;
                            self.tagMap.set(tagEuid, tag);
                            _self.movePeople(self, tagEuid);
                        } else {
                            // TODO 已签发 并且 开始移动 但是 尚未发起获取标签列表请求进行更新
                            tag || (tag = {});

                            tag[DswFengmap.KEY_TAG_POSITION] = body;
                            tag[DswFengmap.KEY_ACTION] = DswFengmap.VAL_MOVE;
                            self.tagMap.set(tagEuid, tag);

                            _self.addPeople(self, tagEuid);
                        }
                    }
                });
            }, function (frame) {

            });

            return this;
        },
        startCrontab: function (self) {
            var _self = this;

            self.timmer = setTimeout(function () {
                _self.startRequest(self);
            }, self.settings.crontabInterval);

            return this;
        },
        startRequest: function (self, paging) {
            var _self = this;

            //处理分页信息
            paging = paging || {};
            var currentPageNum = paging.currentPageNum || 0;
            var scaleNum = paging.scaleNum || self.settings.numPerPage;

            //获取已签发标签列表信息
            $.ajax({
                type: 'get',
                url: self.settings.positionUrl+'/service/tag.json',
                data: {
                    action: 'get.tags',
                    startNum: scaleNum * currentPageNum,
                    scaleNum: scaleNum,
                    active: 0,
                    type: 0,
                    status: 0,
                },
                dataType: 'jsonp'
            }).done(function (result, textStatus, xhr) {
                var tags = result.tags;
                var paging = result.paging;

                if (paging.currentPageNum !== paging.totalPageNum) {
                    _self.startRequest(self, paging);
                } else {
                    _self.startCrontab(self);
                }

                tags.forEach(function (v, k) {
                    var _v = {};
                    if (v.status === 2 && !self.tagMap.has(v.euid)) {//新签发标签
                        _v[DswFengmap.KEY_TAG] = v;
                        _v[DswFengmap.KEY_ACTION] = DswFengmap.VAL_ADD;
                        self.tagMap.set(v.euid, _v);
                    } else if (self.tagMap.has(v.euid) && v.status !== 2) {//已删除标签
                        _self.deletePeople(self, v.euid);
                        self.tagMap.delete(v.euid);
                    }
                });

            }).fail(function (xhr, textStatus, errorThrown) {

            }).always(function (XMLHttpRequest, textStatus) {

            });

            return this;
        },
        createMapCoord: function (x, y, z, height, groupId) {
            var mapCoord = new fengmap.FMMapCoord(x, y, z);

            mapCoord.height = height || 5;
            mapCoord.groupID = groupId || 1;

            return mapCoord;
        },
        createScreenCoord: function (x, y, height) {
            var screenCoord = new fengmap.FMScreenCoord(x, y);

            screenCoord.height = height || 5;

            return screenCoord;
        },
        getMapCoord: function (self, x, y, z) {
            // var mapCoord = self.map.coordScreenToMap(x||0,y||0, z||0);

            var mapCoord = {
                x: 12808161.67 + x,
                y: 3533397.83 - y,
            };

            return mapCoord;
        },
        addPeople: function (self, euid) {
            var tag = self.tagMap.get(euid);
            var _tag = tag[DswFengmap.KEY_TAG] || {};
            var tagPosition = tag[DswFengmap.KEY_TAG_POSITION];
            var username = _tag.name || tagPosition.tagAlias;
            var role = tagPosition.tagType;//1-办案人 2-嫌疑人
            var gender = tagPosition.tagGender;//1-男 2-女
            var metersPerPixel = tagPosition.planMeter;
            var x = tagPosition.localX * metersPerPixel;
            var y = tagPosition.localY * metersPerPixel;
            var mapCoord = this.getMapCoord(self, x, y);

            var avatars = {
                '11': '/dsw/vendors/fengmap/images/peopleMarkerPolice.png',
                '12': '/dsw/vendors/fengmap/images/peopleMarkerPolice.png',
                '21': '/dsw/vendors/fengmap/images/peopleMarkerMan.png',
                '22': '/dsw/vendors/fengmap/images/peopleMarkerWoman.png'
            };

            //添加人物
            // mapCoord=this.getMapCoord(self, x, y);
            var imageMarker = new fengmap.FMImageMarker({
                x: mapCoord.x,
                y: mapCoord.y,
                height: 0,
                szie: 32,
                // url: avatars[role + '' + gender],
                url: '/dsw/vendors/fengmap/images/peopleMarker_blue.png',
                callback: function () {
                    imageMarker.alwaysShow();
                }
            });
            imageMarker[DswFengmap.KEY_TAG_EUID] = euid;
            self.imageLayer.addMarker(imageMarker);
            tag[DswFengmap.KEY_IMAGE_MARKER] = imageMarker;

            //添加名称
            // mapCoord=self.map.coordScreenToMap(self.map.coordMapToScreen(mapCoord.x, mapCoord.y, 0));
            var textMarker = new fengmap.FMTextMarker({
                x: mapCoord.x,
                y: mapCoord.y,
                height: 1,
                name: username || '未知',
                fontsize: 10,
                fillcolor: '255,0,0',
                strokecolor: '255,255,0',
                alpha: 0.9,
                callback: function () {
                    textMarker.alwaysShow();
                }
            });
            textMarker[DswFengmap.KEY_TAG_EUID] = euid;
            self.textLayer.addMarker(textMarker);
            tag[DswFengmap.KEY_TEXT_MARKER] = textMarker;

            self.tagMap.set(euid, tag);

            return this;
        },
        movePeople: function (self, euid) {
            var tag = self.tagMap.get(euid);
            var _tag = tag[DswFengmap.KEY_TAG] || {};
            var tagPosition = tag[DswFengmap.KEY_TAG_POSITION];
            var imageMarker = tag[DswFengmap.KEY_IMAGE_MARKER];
            var textMarker = tag[DswFengmap.KEY_TEXT_MARKER];
            var metersPerPixel = tagPosition.planMeter;
            var x = tagPosition.localX * metersPerPixel;
            var y = tagPosition.localY * metersPerPixel;
            var mapCoord = this.getMapCoord(self, x, y);

            imageMarker.moveTo({x: mapCoord.x, y: mapCoord.y, time: self.settings.moveDuration});
            textMarker.x = mapCoord.x;
            textMarker.y = mapCoord.y;

            this.switchVCR(self, imageMarker, euid);

            return this;
        },
        switchVCR: function (self, eImageOrTextMarker, euid) {

            euid = euid || eImageOrTextMarker[DswFengmap.KEY_TAG_EUID];

            /**
             * 1、人员存在
             * 2、当前已经选择了某一个被观察人员
             * 3、当前选择人员 就是 当前推送人员
             */
            if (euid && self.currentTagEuid && self.currentTagEuid === euid) {

                var tag = self.tagMap.get(euid) || {};
                var previousTag = null;
                var imageMarker = tag[DswFengmap.KEY_IMAGE_MARKER] || {};
                var tagPosition = tag[DswFengmap.KEY_TAG_POSITION] || {};
                var popInfoWindow = tag[DswFengmap.KEY_POP_INFO];

                /**
                 * 切换视频：
                 * 1、a 当前选择人员切换了 关闭已经打开的视频框 新开一个视频框
                 * 1、b 当前选择人员切换了 或者 当前选择人员所在区域发生变化
                 */
                if (self.previousTagEuid !== euid || self.previousZoneId !== tagPosition.zoneId) {
                    /**
                     * 非第一次人员发生切换时，需要恢复前一个人员的头像、关闭视频框，同时切换选中人员的头像、打开视频框、显示所在区域视频
                     */
                    if (self.previousTagEuid && self.previousTagEuid !== euid) {
                        previousTag = self.tagMap.get(self.previousTagEuid);

                        previousTag[DswFengmap.KEY_IMAGE_MARKER].url = self.previousAvatar;
                        previousTag[DswFengmap.KEY_POP_INFO] = previousTag[DswFengmap.KEY_POP_INFO].close();

                        self.tagMap.set(self.previousTagEuid, previousTag);
                    }
                    if (self.previousTagEuid !== euid) {
                        self.previousAvatar = imageMarker.url;
                        imageMarker.url = '/dsw/vendors/fengmap/images/peopleMarker.png';
                    }
                    /**
                     * 所在区域发生变化时，仅仅切换所在区域视频
                     */
                    self.previousTagEuid = euid;
                    self.previousZoneId = tagPosition.zoneId;
                    if (!popInfoWindow) {
                        this.openPopInfoWindow(self, euid, imageMarker.x, imageMarker.y);
                    } else if(dswUtils.channelsMap[self.previousZoneId]){
                        console.log("视频切换区域:"+self.previousZoneId);
                        dswUtils.startRealPlay(dswUtils.channelsMap[self.previousZoneId]);
                    }
                } else if (popInfoWindow) {
                    /**
                     * 移动位置：仅仅移动视频框
                     */
                    popInfoWindow.mapCoord_.x = imageMarker.x;
                    popInfoWindow.mapCoord_.y = imageMarker.y;
                }

            }

            return this;
        },
        deletePeople: function (self, euid) {
            var tag = self.tagMap.get(euid);
            var imageMarker = tag[DswFengmap.KEY_IMAGE_MARKER];
            var textMarker = tag[DswFengmap.KEY_TEXT_MARKER];
            var popInfoWindow = tag[DswFengmap.KEY_POP_INFO];

            if (euid === self.currentTagEuid) {
                self.currentTagEuid = '';
                self.previousTagEuid = '';
            }

            self.textLayer.removeMarker(textMarker);
            self.imageLayer.removeMarker(imageMarker);
            popInfoWindow && popInfoWindow.close();

            return this;
        },
        openPopInfoWindow: function (self, euid, x, y) {
            var _self = this;
            var optionsJson = new fengmap.controlOptions({
                IsScreenCoord: false,
                mapCoord: this.createMapCoord(x, y, 0, 1),
                // screenCoord:this.createScreenCoord(120,240,0),

                width: 300, //设置弹框的宽度
                height: 250, //设置弹框的高度
                marginTop: 1, //距离地图的高度
                content: '<div data-toggle="dsw-web-video-ctrl" id="' + euid + '"></div>',
                // content: $('#dsw-web-video-container').html(),
                closeCallBack: function (bClose) {
                    var euid = this[DswFengmap.KEY_TAG_EUID];
                    var currentTag = self.tagMap.get(euid);

                    currentTag[DswFengmap.KEY_IMAGE_MARKER].url = self.previousAvatar;
                    currentTag[DswFengmap.KEY_POP_INFO] = currentTag[DswFengmap.KEY_POP_INFO].close();

                    self.tagMap.set(euid, currentTag);
                    /**
                     *避免点击 × 关闭时，下次点击无反应，所以必须清零
                     */
                    self.currentTagEuid = '';
                    self.previousTagEuid = '';

                    dswUtils.stopRealPlay(true);

                    _self.closePopInfoWindow(self);
                }
            });

            optionsJson[DswFengmap.KEY_TAG_EUID] = euid;

            var popMarker = null;
            var tag = self.tagMap.get(euid);

            if (!self.popMarker || self.settings.mutiplePop === true) {
                popMarker = new fengmap.FMPopInfoWindow(self.map, optionsJson);
                self.popMarker || (self.popMarker = popMarker);
                tag[DswFengmap.KEY_POP_INFO] = popMarker;
                self.tagMap.set(euid, tag);

                console.log("初始化窗口区域:"+self.previousZoneId);
                dswUtils.initWebVideo(euid,dswUtils.channelsMap[self.previousZoneId]);
            }

            return this;
        },
        closePopInfoWindow: function (self) {
            self.popMarker && (self.popMarker = self.popMarker.close());

            return this;
        },
        cleanResource: function (self) {
            $(win).on('beforeunload', function (e) {
                self.client.disconnect(function () {

                });

                dswUtils.stopRealPlay(true);

                self.timmer && (self.timmer = clearTimeout(self.timmer));
            });
        }
    };

    //挂载方法
    $.fn.extend({
        dswFengmap: function (options, force) {
            var argv = Array.prototype.slice.call(arguments, 1);

            if ($.type(options) === 'boolean') {
                force = options;
                options = {};
            }

            return this.each(function (index, item) {
                var instance = $(this).data(DswFengmap.KEY_INSTANCE);
                if (!instance || ($.type(options) === 'object' && force === true)) {
                    instance = new DswFengmap(this, options);
                    $(this).data(DswFengmap.KEY_INSTANCE, instance);
                }

                if ($.type(options) === 'string') {
                    instance[options] && instance[options].apply(instance, argv);
                }
            });
        }
    });

    //自动调用
    $(function () {
        $('[data-toggle="dsw-fengmap"]').dswFengmap();
    });

}(window, document, jQuery);
