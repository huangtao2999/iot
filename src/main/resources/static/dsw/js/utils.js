'use strict';

window.dswUtils = window.dswUtils || {};

$.extend(true, window.dswUtils, {
    dataTablesConfig: {},
    openIframe: function (url, params, options) {

        if ($.type(params) === 'object') {
            params = $.param(params);
        }

        options = $.extend({
            type: 2,
            title: false,
            area: ['80%', '80%'],
            closeBtn: 2,
            shadeClose: false,
            resize: false,
            scrollbar: false,
            content: url + '?' + params,
            // btn:['yes']
        }, options || {});

        return layer.open(options);
    },
    openPage: function (content, options) {

        options = $.extend({
            type: 1,
            title: false,
            area: ['80%', '80%'],
            closeBtn: 1,
            shadeClose: false,
            resize: false,
            scrollbar: false,
            content: content,
        }, options || {});

        return layer.open(options);
    },

    openConfirm: function (title, content, options) {
        options = $.extend({
            type: 0,
            title: title || false,
            area: ['80%', '80%'],
            closeBtn: 1,
            shadeClose: false,
            resize: false,
            scrollbar: false,
            content: content,
        }, options || {});

        return layer.open(options);
    },

    closeIframeSelf: function () {
        var index = window.parent.layer.getFrameIndex(window.name);
        window.parent.layer.close(index);
    },

    parseSchema: function (uri) {
        var schema = {};

        var scehmaReg = /([^:]*:\/\/)?([^:\/\?#]*)?(:[^\/\?#]*)?([^\?#]*)?(\?[^#]*)?(#.*)?/;

        var matches = uri.match(scehmaReg);

        matches && matches.forEach(function (v, k) {
            switch (k) {
                case 0: {
                    schema.href = v || '';
                    break;
                }
                case 1: {
                    schema.protocol = v && v.split(':')[0];
                    break;
                }
                case 2: {
                    schema.hostname = v || '';
                    break;
                }
                case 3: {
                    schema.port = v && v.substr(1);
                    break;
                }
                case 4: {
                    schema.pathname = v || '';
                    break;
                }
                case 5: {
                    schema.search = v && v.substr(1);
                    break;
                }
                case 6: {
                    schema.hash = v && v.substr(1);
                    break;
                }
                default: {
                    break;
                }
            }
        });

        return schema;
    },

    parseStr:function(str){
        var strObj={};

        str && str.split('&').forEach(function (v,k) {
            if (v!==''){
                var va=v.split('=');
                strObj[va[0]]=va[1];
            }
        });

        return strObj;
    },

    dateFormatter: function (datetime, formatter) {
        formatter = formatter || 'yyyy-MM-dd HH:mm:ss';

        datetime = new Date(datetime);

        var regExp = {
            '([yY]+)': this.padString(datetime.getFullYear(), 4),
            '(M+)': this.padString(datetime.getMonth() + 1, 4),
            '(d+)': this.padString(datetime.getDate(), 4),
            '(H+)': this.padString(datetime.getHours(), 4),
            '(m+)': this.padString(datetime.getMinutes(), 4),
            '(s+)': this.padString(datetime.getSeconds(), 4),
            '(S+)': this.padString(datetime.getMilliseconds(), 4)
        };

        for (var k in regExp) {
            if ((new RegExp(k)).test(formatter)) {
                formatter = formatter.replace(RegExp.$1, regExp[k].substr(4 - RegExp.$1.length));
            }
        }

        // if (/(S+)/.test(formatter)){
        //     formatter=formatter.replace(RegExp.$1,datetime.getMilliseconds());
        // }

        return formatter;
    },

    padString: function (str, minCount, padStr, prefix) {
        str = String(str === undefined ? '' : str);
        minCount = parseInt(minCount, 10) || 2;
        padStr = String(padStr === undefined ? '0' : padStr);
        prefix = prefix === undefined ? true : false;

        var strLen = str.length;
        var padLen = padStr.length;
        var _padStr = '';

        if (strLen < minCount) {
            var deltaCount = minCount - strLen;

            var entire = Math.floor(deltaCount / padLen);
            var part = deltaCount % padLen;

            while (entire--) {
                _padStr += padStr;
            }

            _padStr += padStr.substr(0, part);

        }

        return prefix ? _padStr + str : str + _padStr;
    },

});


