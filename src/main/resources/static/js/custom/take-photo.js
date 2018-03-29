+ function(win, doc, $, undefined) {
    // 定义插件类
    function TakePhoto(el, options) {
        this.$el = $(el);

        // 获取 data 配置值
        var _dataSettings = this.getDataParams();
        if ($.type(_dataSettings['initImages']) === 'string') {
            _dataSettings['initImages'] = _dataSettings['initImages'].split(',');
        }
        // 参数优先级 方法配置参数 > data 自定义参数 > 默认参数
        this.settings = $.extend(true, {}, TakePhoto.defaults, _dataSettings, options || {});

        // 保存初始图组的 一个副本
        this.imgs = this.settings.initImages.slice();

        // 初始化
        this.init();
    }

    // 定义插件默认参数
    TakePhoto.defaults = {
        type:'',
        maxCount: 5, //最多显示的图片数量 Number
        initImages: [], //初始化时，要显示的图片数组 Array<String> Array<Object>

        imgWrapperClass: 'dsw-take-photo-img-box', // String
        imgMainClass: 'dsw-take-photo-img-main', // String
        imgMainCallback: imgMainCallback, // 点击图片时 的 回调函数
        imgCloseClass: 'dsw-take-photo-img-close', // String
        imgCloseUrl: 'images/close.png', // String

        imgTakePhotoClass: 'dsw-take-photo-img-btn', // String
        imgTakePhotoUrl: 'images/take-photo.png', // String
        isShowTakePhotoBtn: true, // 是否显示 拍照按钮 Boolean
        takePhotoCallback: takePhotoCallback, // 点击拍照按钮时 的 回调函数

        renderedCallback: null, //渲染完成之后的 回调函数
        insertedCallback: null, //动态插入完成之后的 回调函数
        deletedCallback: null, //动态删除完成之后的 回调函数

    };

    function imgMainCallback(e, _this, self) {
        var _data = [];
        var _index = self.$el.find('.' + self.settings.imgWrapperClass).index($(_this).parent());
        self.$el.find('.' + self.settings.imgMainClass).each(function() {
            _data.push({ src: $(this).prop('src') });
        });
		
        layer.photos({
            shade:[0.3,'#000'],
            success:function(layero,index){
                $('#divPlugin').css('visibility','hidden');
            },
            end:function() {
                $('#divPlugin').css('visibility','visible');
            },
            tab:function(pic,layero){
                $('#divPlugin').css('visibility','hidden');
            },
            photos: {
                "title": "", //相册标题
                "id": '', //相册id
                "start": _index, //初始显示的图片序号，默认0
                "data": _data
            }
        });
    }

    function takePhotoCallback(e, _this, self) {
        layer.open({
            type: 2,
            title: false,
            resize:false,
            content: '/Video/index.html?type='+self.settings.type,
            area: ['900px', '500px'],
            success: function(layero, index) {
                var frameWindow = window[layero.find('iframe').prop('name')];

                frameWindow.take_photo({ initImages: self.imgs,maxCount:self.settings.maxCount });
            },
            end: function() {
                if ($('input[type="hidden"][data-dsw-take-photo-temp]').data('dsw-take-photo-temp-updated')) {
                    var _newImages = $('input[type="hidden"][data-dsw-take-photo-temp]').data('dsw-take-photo-temp');
                    self.rebuild(_newImages);
                    $('input[type="hidden"][data-dsw-take-photo-temp]').data('dsw-take-photo-temp', []).data('dsw-take-photo-temp-updated', false);
                }
            }

        });
    }

    // 定义插件方法
    TakePhoto.prototype = $.extend(true, {}, TakePhoto.prototype, {
        // 初始化方法
        init: function() {
            var _$el = this.$el;
            var _settings = this.settings;
            var _initImages = this.imgs;
            var _maxCount = _settings.maxCount;
            var _renderedCallback = _settings.renderedCallback;

            // 获取初始化显示图片长度
            var _initLen = Math.min(_initImages.length || 0, _maxCount);

            // 长度未达到最大显示数量时 显示拍照按钮
            this.createTakePhotoBtn(_initLen < _maxCount);

            // 根据图片初始数组 生成 DOM 结构
            for (var _i = 0; _i < _initLen; _i++) {
                this.createTakePhotoImg(_initImages[_i]);
            }

            // 绑定事件
            this.bindEvent();

            // 渲染回调
            _renderedCallback && _renderedCallback(this);

        },
        // 创建图片方法
        createTakePhotoImg: function(img) {
            var _src = $.type(img) === 'string' ? img : img.src;

            var _showImg = $('<img/>').prop({ src: _src }).addClass(this.settings.imgMainClass);
            var _closeImg = $('<img/>').prop({ src: this.settings.imgCloseUrl }).addClass(this.settings.imgCloseClass);

            var _takePhotoBtn = this.$el.find('.' + this.settings.imgTakePhotoClass);
            $('<div></div>').addClass(this.settings.imgWrapperClass).append(_showImg).append(_closeImg).insertBefore(_takePhotoBtn);

        },
        // 创建拍照按钮
        createTakePhotoBtn: function(isShow) {
            var _takePhotoBtn = $('<img/>').prop({ src: this.settings.imgTakePhotoUrl }).addClass(this.settings.imgTakePhotoClass);
            if (!isShow || !this.settings.isShowTakePhotoBtn) {
                _takePhotoBtn.hide();
            }
            _takePhotoBtn.appendTo(this.$el);
        },
        // 绑定事件
        bindEvent: function() {
            var self = this;
            this.$el.on('click', '.' + this.settings.imgCloseClass, function(e) {
                var _deletedCallback = self.settings.deletedCallback;
                var _index = self.$el.find('.' + self.settings.imgWrapperClass).index($(this).parent());
                self.imgs.splice(_index, 1);
                $(this).parent().remove();
                self.showTakePhotoBtn();
                _deletedCallback && _deletedCallback(e, this, self);
            }).on('click', '.' + this.settings.imgTakePhotoClass, function(e) {
                var _takePhotoCallback = self.settings.takePhotoCallback;

                _takePhotoCallback && _takePhotoCallback(e, this, self);
            }).on('click', '.' + this.settings.imgMainClass, function(e) {
                var _imgMainCallback = self.settings.imgMainCallback;

                _imgMainCallback && _imgMainCallback(e, this, self);
            });
        },
        // 获取 data 自定义参数
        getDataParams: function(k) {
            // 未传值时，获取全部
            if (k == undefined) {
                // 保存 data 自定义属性
                var _dataSettings = {};

                for (var _key in TakePhoto.defaults) {
                    _dataSettings[_key] = this.$el.data(_key.replace(/([A-Z])/g, function($1) {
                        return '-' + $1.toLowerCase();
                    }));
                }

                return _dataSettings;
            } else if (k in TakePhoto.defaults) {
                // 获取指定的值
                return this.$el.data(k.replace(/([A-Z])/g, function($1) {
                    return '-' + $1.toLowerCase();
                }));
            }

            return undefined;
        },
        // 判断是否显示照相按钮
        showTakePhotoBtn: function() {
            var _currentImgCount = this.$el.find('.' + this.settings.imgWrapperClass).length;
            var _takePhotoBtn = this.$el.find('.' + this.settings.imgTakePhotoClass);

            if (_currentImgCount < this.settings.maxCount && this.settings.isShowTakePhotoBtn) {
                _takePhotoBtn.show();
            } else {
                _takePhotoBtn.hide();
            }
        },
        // 添加图片到初始图片数组
        addImages: function(img) {
            this.imgs.push(img);
        },
        // 插入拍照图片
        insertTakePhotoImg: function(img) {
            var _insertedCallback = this.settings.insertedCallback;
            this.createTakePhotoImg(img);
            this.addImages(img);
            this.showTakePhotoBtn();
            _insertedCallback && _insertedCallback(this);
        },
        // 视图重构
        rebuild: function(imgs) {
            if ($.type(imgs) === 'array') {
                this.imgs = imgs.slice(0);
            }
            var _initImages = this.imgs;
            var _maxCount = this.settings.maxCount;

            this.$el.empty();

            // 获取初始化显示图片长度
            var _initLen = Math.min(_initImages.length || 0, _maxCount);

            // 长度未达到最大显示数量时 显示拍照按钮
            this.createTakePhotoBtn(_initLen < _maxCount);

            // 根据图片初始数组 生成 DOM 结构
            for (var _i = 0; _i < _initLen; _i++) {
                this.createTakePhotoImg(_initImages[_i]);
            }

        }

    });

    // 挂在插件到 jQuery
    $.fn.dsw_take_photo = function(options) {
        if (!$('input[type="hidden"][data-dsw-take-photo-temp]').get(0)) {
            $('<input />').prop('type', 'hidden').attr('data-dsw-take-photo-temp', '[]').appendTo('body');
        }
        var _argv = Array.prototype.slice.call(arguments, 1);
        return this.each(function(index, item) {
            var instance = $(this).data('dsw-take-photo');

            if (!instance) {
                instance = new TakePhoto(this, options);
                $(this).data('dsw-take-photo', instance);
            }

            if ($.type(options) === 'string') {
                return instance[options].apply(instance, _argv);
            }

        });
    };
}(window, document, jQuery);
