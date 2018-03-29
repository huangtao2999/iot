
/*
检查是否安装 flash 及 版本号
*/
function detectFlash() {  
    //navigator.mimeTypes是MIME类型，包含插件信息  
    if (navigator.mimeTypes.length > 0) {  
        //application/x-shockwave-flash是flash插件的名字  
        var flashAct = navigator.mimeTypes["application/x-shockwave-flash"];  
        return flashAct != null ? flashAct.enabledPlugin != null : false;  
    } else if (self.ActiveXObject) {  
        try {  
            new ActiveXObject('ShockwaveFlash.ShockwaveFlash');  
            return true;  
        } catch (oError) {  
            return false;  
        }  
    }  
}

function flashChecker() {
    var hasFlash = 0; //是否安装了flash
    var flashVersion = 0; //flash版本
    var isIE =/*@cc_on!@*/0; //是否IE浏览器
    if (isIE) {
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
        if (swf) {
            hasFlash = 1;
            flashVersion = swf.GetVariable("$version");
        }
    } else {
        if (navigator.plugins && navigator.plugins.length > 0) {
            var swf = navigator.plugins["Shockwave Flash"];
            if (swf) {
                hasFlash = 1;
                flashVersion = swf.description.split(" ");
            }
        }
    }
    return {
        f: hasFlash,
        v: flashVersion
    };
}

/**
 * 删除
 * @param url
 * @param trId:可选参数
 *
 */
function doDelete(url, trId) {
    if (typeof(trId) == 'undefined') {//如果找不到trId
        trId = getId('delete');
        if (trId == false) {
            return;
        }
    }
    //执行删除操作
    if (confirm("确定删除吗？")) {
        $.ajax({
            url: url,
            data: {'fIds': trId},
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data > 0) {
                    alert('删除成功！');
                    location.reload();
                } else {
                    alert('删除失败！');
                }
            },
            error: function () {
                alert('系统错误！');
            }
        });
    }

}

/**
 * 勾选已选择ID
 */
function getId(msg) {
    var count = 0;
    var trId = '';
    var chks = $('input[name="chk"]');
    for (var i = 0; i < chks.length; i++) {
        if ($(chks[i]).attr('checked')) {
            //alert($(chks[i]).attr('checked'));
            count++;
            trId += $(chks[i]).parent().parent().attr('id') + ",";//获取tr的id，即数据id
        }
    }
    if (msg == 'update') {
        if (count == 0) {
            alert('请选择操作项！');
            return false;
        } else if (count > 1) {
            alert('只能选择一个操作项！');
            return false;
        } else {
            return trId.substring(0, trId.length - 1);
        }
    } else if (msg == 'delete') {
        if (count == 0) {
            alert('请选择操作项！');
            return false;
        } else {
            return trId.substring(0, trId.length - 1);
        }
    }

}

/**
 *   全选/反选
 **/
function checkAll(obj) {
    jQuery('input[type="checkbox"]').attr('checked', obj.checked);
}

/**
 * 全/反选
 * @param obj checkbox的对象
 * @param name checkbox的name值 ，name外需有引号
 *
 */
function selectAll(obj, name) {
    jQuery('input[name=' + name + ']').attr('checked', obj.checked);
}

/**
 * 表单提交前的验证
 * @param form1  所提交表单的名称
 * @returns {Boolean}  返回值
 * dataType:String "字符串"
 *   int "整数型"
 *   number "实数型"
 *   email "电子邮箱型"
 *   tel "电话号码类型"
 *   mobile "手机号类型"
 *
 * title：字段名称
 *
 * want：是否允许为空；yes不允许--no允许
 *
 */
function checkForm(form) {
    var result = true;
    jQuery(form).find('input').each(function () {
        if (checkObj(this) == false) {
            result = false;
            return result;
        }
    });
    jQuery(form).find('select').each(function () {
        if (checkObj(this) == false) {
            result = false;
            return result;
        }
    });
    jQuery(form).find('textarea').each(function () {
        if (checkObj(this) == false) {
            result = false;
            return result;
        }
    });

    if (!result) {
        alert("*号项必填！");
    }

    return result;
}

function checkObj(obj) {
    var dataType = jQuery(obj).attr('dataType');
    var title = jQuery(obj).attr('title');
    var want = jQuery(obj).attr('want');
    var value = jQuery(obj).val();
    var res = true;
    if (value == null || value == '') {
        if (want == 'yes') {
            jQuery(obj).focus();
            //alert(title+"不得为空！",2000);
            res = false;
        }
    } else {
        if (dataType == 'int') {
            var checkNum = /^\d+$/;
            if (!checkNum.test(value)) {//验证字符串是否全为数字
                jQuery(obj).focus();
                alert(title + " 只能输入数字！", 2000);
                res = false;
            }
        } else if (dataType == 'number') {
            if (isNaN(value)) {//验证字符串是否全为数字
                jQuery(obj).focus();
                alert(title + " 只能输入数字和小数点！", 3000);
                res = false;
            }
        } else if (dataType == 'tel') {
            if (value.isTel() == false) {
                jQuery(obj).focus();
                alert("请输入正确的 " + title + " 格式", 2000);
                res = false;
            }
        } else if (dataType == 'mobile') {
            if (value.isMobile() == false) {
                jQuery(obj).focus();
                alert("请输入正确的 " + title + " 格式", 2000);
                res = false;
            }
        } else if (dataType == 'email') {
            var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}jQuery/;//email判断正则表达式
            if (!reg.test(value)) {
                jQuery(obj).focus();
                alert("请输入正确的 " + title + " 格式", 2000);
                res = false;
            }
        } else if (dataType == 'imgfile') {
            var reg = /\.(jpg|JPG|gif|GIF|jpeg|JPEG|png|PNG)jQuery/;//图片后缀正则表达式
            if (!reg.test(value)) {
                jQuery(obj).focus();
                alert("请选择正确的图片文件:<br/> jpg,JPG,gif,GIF,jpeg,JPEG,png,PNG", 2000);
                res = false;
            }
        }
    }
    return res;
}

//同步访问ajax函数
function synchroAjax(url, data, type) {
    if (typeof(type) == 'undefined')
        type = 'text';
    var str = '';
    var load = new loading();
    load.show();
    jQuery.ajax({
        async: false,
        url: url,
        data: data,
        type: 'POST',
        dataType: type,
        success: function (text) {
            load.close();
            str = text;
        },
        error: function () {
            load.close();
            alertMsg(3, '访问失败');
        }
    });
    return str;
}
/***
 *字典缓存文件
 */
var dictonaryMap= new Map();

/**
 * 通过类型获取字典类别
 */
function findDicItems(dictType) {
    if(dictonaryMap.has(dictType)){
        return dictonaryMap.get(dictType);
    }else{
//        var res = new PublicAjax('/Dictionary/queryTableListByType.json', {'type': dictType});
        var res = new PublicAjax('/Dictionary/queryComboList.json', {'code': dictType});//查单层字典，传code
        if (res.success) {
            dictonaryMap.set(dictType,res.content)
            return res.content;
        }
    }
}

/**
 * 列表里获取某列的字典翻译成汉字（只查找当前code的下一级）
 */
function getGridDict(val, code){
	var dicList = findDicItems(code);
	for(var e = 0; e < dicList.length; e++){
		if(val == dicList[e].code){
			return dicList[e].name;
		}
	}
	return "";
}

/**
 * 公用ajax封装
 */
function PublicAjax(url, data) {
    // var load = new loading();
    // load.show();
    var str = '';
    jQuery.ajax({
        async: false,
        url: url,
        cache:false,
        data: data,
        type: 'POST',
        dataType: 'json',
        success: function (text) {
            str = text;
        },
        error: function () {
            // load.close();
            alert('访问失败');
        }
    });
    // load.close();
    return str;
}
/**
 * 公用ajax-json封装
 */
function PublicAjaxToJson(url, data) {
    // var load = new loading();
    // load.show();
    var str = '';
    jQuery.ajax({
        async: false,
        url: url,
        data: JSON.stringify(data),
        type: 'POST',
        dataType: 'json',
        contentType:"application/json",
        success: function (text) {
            str = text;
        },
        error: function () {
            // load.close();
            alert('访问失败');
        }
    });
    // load.close();
    return str;
}

/**
 * 打开等待框
 */
function openWaitWindow() {
    return layer.msg('努力中...', {icon: 16, shade: [0.5, '#f5f5f5'], scrollbar: false, offset: 'auto', time: 10000});
}

/**
 * 关闭等待框
 */
function closeWaitWindow(index) {
    layer.close(index);
}

/**
 * ht 解决IE版本态度 不支持Map
 * @constructor
 */
function Map() {
    this.elements = new Array();
    // 获取Map元素个数
    this.size = function() {
        return this.elements.length;
    },
        // 判断Map是否为空
        this.isEmpty = function() {
            return (this.elements.length < 1);
        },
        // 删除Map所有元素
        this.clear = function() {
            this.elements = new Array();
        },
        // 向Map中增加元素（key, value)
        this.put = function(_key, _value) {
            if (this.containsKey(_key) == true) {
                if (this.containsValue(_value)) {
                    if (this.remove(_key) == true) {
                        this.elements.push({
                            key : _key,
                            value : _value
                        });
                    }
                } else {
                    this.elements.push({
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push({
                    key : _key,
                    value : _value
                });
            }
        },
        // 向Map中增加元素（key, value)
        this.set = function(_key, _value) {
            if (this.containsKey(_key) == true) {
                if (this.containsValue(_value)) {
                    if (this.remove(_key) == true) {
                        this.elements.push({
                            key : _key,
                            value : _value
                        });
                    }
                } else {
                    this.elements.push({
                        key : _key,
                        value : _value
                    });
                }
            } else {
                this.elements.push({
                    key : _key,
                    value : _value
                });
            }
        },
        // 删除指定key的元素，成功返回true，失败返回false
        this.remove = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

        // 删除指定key的元素，成功返回true，失败返回false
        this.delete = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

        // 获取指定key的元素值value，失败返回null
        this.get = function(_key) {
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        return this.elements[i].value;
                    }
                }
            } catch (e) {
                return null;
            }
        },

        // set指定key的元素值value
        this.setValue = function(_key, _value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements[i].value = _value;
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

        // 获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null
        this.element = function(_index) {
            if (_index < 0 || _index >= this.elements.length) {
                return null;
            }
            return this.elements[_index];
        },

        // 判断Map中是否含有指定key的元素
        this.containsKey = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

        // 判断Map中是否含有指定key的元素
        this.has = function(_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

        // 判断Map中是否含有指定value的元素
        this.containsValue = function(_value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].value == _value) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

        // 获取Map中所有key的数组（array）
        this.keys = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].key);
            }
            return arr;
        },

        // 获取Map中所有value的数组（array）
        this.values = function() {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].value);
            }
            return arr;
        };

    /**
     * map遍历数组
     * @param callback [function] 回调函数；
     * @param context [object] 上下文；
     */
    this.forEach = function forEach(callback,context){
        context = context || window;

        //IE6-8下自己编写回调函数执行的逻辑
        var newAry = new Array();
        for(var i = 0; i < this.elements.length;i++) {
            if(typeof  callback === 'function') {
                var val = callback.call(context,this.elements[i].value,this.elements[i].key,this.elements);
                newAry.push(this.elements[i].value);
            }
        }
        return newAry;
    }

}
