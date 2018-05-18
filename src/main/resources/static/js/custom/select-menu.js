+ function(win, doc, $, undefined) {

    function SelectMenu(el, options) {
        this.$el = $(el);
        this.id = String(Math.random()).substring(2);
        var _dataSettings = this.getDataSettings();
        this.settings = $.extend(true, {}, SelectMenu.defaults, _dataSettings, options);

        this.init();
    }

    var zNodes = [
        { id: 1, pid: 0, name: "北京" },
        { id: 2, pid: 0, name: "天津" },
        { id: 3, pid: 0, name: "上海" },
        { id: 6, pid: 0, name: "重庆" },
        { id: 4, pid: 0, name: "河北省", open: true },
        { id: 41, pid: 4, name: "石家庄" },
        { id: 42, pid: 4, name: "保定" },
        { id: 43, pid: 4, name: "邯郸" },
        { id: 44, pid: 4, name: "承德" },
        { id: 5, pid: 0, name: "广东省", open: true },
        { id: 51, pid: 5, name: "广州" },
        { id: 52, pid: 5, name: "深圳" },
        { id: 53, pid: 5, name: "东莞" },
        { id: 54, pid: 5, name: "佛山" },
        { id: 6, pid: 0, name: "福建省", open: true },
        { id: 61, pid: 6, name: "福州" },
        { id: 62, pid: 6, name: "厦门" },
        { id: 63, pid: 6, name: "泉州" },
        { id: 64, pid: 6, name: "三明" }
    ];

    SelectMenu.defaults = {
        width: true, //下拉框宽度，true 自动获取输入框的宽度，或者具体数值
        multipleSelect: false, //是否可以多选
        selectFolder:false,//是否可以选择文件夹
        ztreeSettings: {
            view: {
                dblClickExpand: true,
                selectedMulti: false,
            },
            data: {
            		keep:{
            			leaf:false,
            			parent:false
            		},
            		key:{
            			isParent:'isParent'
            		},
                simpleData: {
                    enable: true,
                    idKey: 'id',
                    pIdKey: 'pid',
                    rootPId: null
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            },
            async: {
                enable: true,
                url: '',
                type: 'get',
                dataType: 'json',
                dataFilter: filter,
                autoParam: ['id=pid'],
                otherParam: function(treeId,treeNode){
                	if (treeNode==null) {
                		return ['pid',230];
                	}

                	return [];
                }
            }
        }, //ztree 设置
    };

    function beforeClick(treeId, treeNode) {
    	if($('#'+treeId).data('dsw-select-menu').settings.selectFolder!==true){
        var check = !!(treeNode && (!treeNode.isParent || (treeNode.children && treeNode.children.length===0)));
        //if (!check) alert("只能选择城市...");
        return check;
    	}

    	return true;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId),
            nodes = zTree.getSelectedNodes(),
            v = "";
            code = "";
        nodes.sort(function compare(a, b) { return a.id - b.id; });
        for (var i = 0, l = nodes.length; i < l; i++) {
            v += nodes[i].name + ",";
            code += nodes[i].code + ",";
        }
        if (v.length > 0) v = v.substring(0, v.length - 1);
        if (code.length > 0) code = code.substring(0, code.length - 1);
        var cityObj = $('#' + treeId).data('dsw-select-menu').$el;
        //填写选中的code
        cityObj.data("code",code);
        cityObj.val(v);
    }

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        // for (var i = 0, l = childNodes.length; i < l; i++) {
        //     childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        // }
        return childNodes.content;
    }

    function elClick(e, self, multipleSelect) {
        self.$menuBox.slideDown('fast');
        self.$el.off('click.dsw-select-menu');

        $('body').on('mousedown.dsw-select-menu', function(e) {
            if (e.target.id !== self.$el.prop('id') && (!multipleSelect || (multipleSelect && !$(e.target).closest('#' + self.$menuBox.prop('id')).get(0)))) {
                self.$menuBox.slideUp('fast');
                $('body').off('mousedown.dsw-select-menu');
                self.$el.on('click.dsw-select-menu', function(e) {
                    elClick(e, self, multipleSelect);
                });
            }
        });
    }

    SelectMenu.prototype = $.extend(true, {}, SelectMenu.prototype, {
        init: function() {
            var _$el = this.$el;
            var _id = this.id;

            this.generateBox();

            this.mergeZtreeSettings();

            this.generateZtree();

            this.bindEvents();
        },
        mergeZtreeSettings: function() {
            var _$el = this.$el;

            var _enable = _$el.data('enable');
            var _url = _$el.data('url');
            var _type = _$el.data('type');
            var _dataType = _$el.data('data-type');
            var _dataFilter = _$el.data('data-filter');
            var _autoParam = _$el.data('auto-param');
            var _otherParam = _$el.data('other-param');

            this.settings.ztreeSettings.view.selectedMulti = this.settings.multipleSelect;
            this.settings.ztreeSettings.async.enable = !!_enable;
            this.settings.ztreeSettings.async.url = _url;
            if (!!_type) {
                this.settings.ztreeSettings.async.type = _type;
            }
            if (!!_dataType) {
                this.settings.ztreeSettings.async.dataType = _dataType;
            }
            if (!!_dataFilter && $.type(win[_dataFilter]) === 'function') {
                this.settings.ztreeSettings.async.dataFilter = win[_dataFilter];
            }
            if ($.type(_autoParam) === 'array') {
                this.settings.ztreeSettings.async.autoParam = _autoParam;
            }
            if ($.type(_otherParam) === 'array') {
                this.settings.ztreeSettings.async.otherParam = _otherParam;
            }
        },
        generateBox: function() {
            var _$el = this.$el;
            var _id = this.id;
            var _elOffset = _$el.offset();
            var _width = this.settings.width;

            if (_width === true) {
                _width = _$el.outerWidth();
            }

            // 生成下拉 容器
            var $menuBox = $('<div></div>').addClass('menuContent').css('position', 'absolute').prop('id', 'menuContent' + _id).hide();
            $menuBox.css({ left: _elOffset.left + "px", top: _elOffset.top + _$el.outerHeight() + "px" });

            var $ul = $('<ul></ul>').addClass('ztree').prop('id', 'treeDemo' + _id).css('width', _width);
            $ul.data('dsw-select-menu', this);

            $ul.appendTo($menuBox);
            _$el.after($menuBox);
            this.$menuBox = $menuBox;
        },
        generateZtree: function() {
            var _id = this.id;
            var _ztreeSettings = this.settings.ztreeSettings;
            if (_ztreeSettings.async.enable === true) {
                $.fn.zTree.init($("#treeDemo" + _id), _ztreeSettings);
            } else {

                $.ajax({
                	url:_ztreeSettings.async.url,
                	type: _ztreeSettings.async.type,
                  dataType: _ztreeSettings.async.dataType,
                  //dataFilter: _ztreeSettings.async.dataFilter,
                  data: $.extend(true, {}, _ztreeSettings.async.autoParam, _ztreeSettings.async.otherParam)
                }).then(function(result) {
                    // $.fn.zTree.init($("#treeDemo" + _id), _ztreeSettings, zNodes);
                    $.fn.zTree.init($("#treeDemo" + _id), _ztreeSettings, result.content);
                }, function(reason) {
                });
            }
        },
        bindEvents: function() {
            var _self = this;
            var _multipleSelect = this.settings.multipleSelect;

            this.$el.on('click.dsw-select-menu', function(e) {
                elClick(e, _self, _multipleSelect);
            });
        },
        getDataSettings: function(dataKey) {
            // 未传值时，获取全部
            if (dataKey == undefined) {
                // 保存 data 自定义属性
                var _dataSettings = {};

                for (var _key in SelectMenu.defaults) {
                    _dataSettings[_key] = this.$el.data(_key.replace(/([A-Z])/g, function($1) {
                        return '-' + $1.toLowerCase();
                    }));
                }

                return _dataSettings;
            } else {
                // 获取指定的值
                return this.$el.data(dataKey.replace(/([A-Z])/g, function($1) {
                    return '-' + $1.toLowerCase();
                }));
            }

            return undefined;
        }
    });

    $.fn.dsw_select_menu = function(options) {

        var _argv = Array.prototype.slice.call(arguments, 1);

        return this.each(function(index, dom) {
            var instance = $(dom).data('dsw-select-menu');
            if (!instance) {
                instance = new SelectMenu(this, options);
                $(dom).data('dsw-select-menu', instance);
            }

            if ($.type(options) === 'string') {
                return instance[options].apply(instance, _argv);
            }

        });

    }

}(window, document, jQuery);
