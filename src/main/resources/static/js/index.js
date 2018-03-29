
$(document).ready(function() {
    $.ajax({
        url: "/Menu/getMenuTreeForIndex.json", //json文件位置
        type: "GET", //请求方式为get
        cache:false,
        dataType: "json", //返回数据格式为json
        success: function(menuData) { //请求成功完成后要执行的方法
            var data = menuData.content;
            if (data && data.length > 0) {
                var html = '';
                for (var i = 0, len = data.length; i < len; i++) {
                    var childernHtml = "";
                    if (data[i].children && data[i].children.length > 0) {
                        childernHtml += '<div class="erjiMenuBox">';
                        childernHtml += '<div class="posRel wh100">';
                        childernHtml += '	<img src="../images/index/bg-1.png" alt="" class="erjiMenuBgImg posAbs">';
                        childernHtml += '	<ul class="posAbs cenMid">';
                        for (var j = 0, clen = data[i].children.length; j < clen; j++) {
                            childernHtml += '<li data-href="' + data[i].children[j].action + '">';
                            childernHtml += '<div class="posRel wh100">';
                            childernHtml += '	<span class="posAbs cenMid">' + data[i].children[j].text + '</span>';
                            childernHtml += '	<img src="../images/index/menu/caidan-xuanzhong.png" alt="" class="erjiMenuBg wh100 posAbs">';
                            childernHtml += '</div>';
                            childernHtml += '</li>';
                        }
                        childernHtml += '	</ul>';
                        childernHtml += '</div>';
                        childernHtml += '</div>';
                    }

                    html += '<li class="posRel" data-href="' + data[i].action + '">';
                    html += '	<img src="../images/index/menu/caidan-xuanzhong.png" alt="" class="bg">';
                    html += '	<img src="../FileUpload/getAttach.json?id=' + data[i].menuIcon + '" alt="" class="logoImg mid">';
                    html += '	<span class="cenMid">' + data[i].text + '</span>';
                    html += '	<img src="../images/index/menu/xitongguanli-jiantou.png" alt="" class="more mid">';
                    html += '	<img src="../images/index/menu/zicaidan-xitongguanli-jiantou.png" alt="" class="packUp mid">';
                    html += '	<img src="../images/index/menu/caidan-fengexian.png" alt="" class="bottomImg posAbs">';
                    html += childernHtml;
                    html += '</li>';
                }
                $(".menu").append(html);
            }

            //左边菜单栏的球旋转方法
            $(".circleBox .imgS").addClass("rotateF");
            $(".circleBox .imgB").addClass("rotateZ");
            //默认第一个默认状态
            $(".menuBox .menu>li").eq(0).find(".bg").css('display', 'block');
            $(".menuBox .menu>li").eq(0).find(".more").css('display', 'block');
            $(".tabHtmlBox .tabBox ul li").eq(0).addClass("active");
            $(".menuBox .menu>li").mouseover(function() {
                //背景图显示隐藏
                $(this).find(".bg").show();
                $(this).siblings().find(".bg").hide();
                //二级菜单显示隐藏
                $(this).find(".erjiMenuBox").show();
                $(this).siblings().find(".erjiMenuBox").hide();
                //二级菜单的高度方法
                var oneLiH = $(this).find(".erjiMenuBox ul li").height();
                var lisLength = $(this).find(".erjiMenuBox ul li").length;
                $(this).find(".erjiMenuBox").height(oneLiH * lisLength + 100 + 'px');
                //箭头的显示隐藏
                $(this).find(".more").hide();
                $(this).find(".packUp").show();
                $(this).siblings().find(".more").hide();
                $(this).siblings().find(".packUp").hide();
                //二级菜单的定位位置
                var index = $(this).index();
                var length = $(".menu>li").length;
                if (length < 4) {
                    $(this).find(".erjiMenuBox").css("top", "-100%");
                } else {
                    if (index < length / 2) {
                        $(this).find(".erjiMenuBox").css("top", "-100%");
                    } else if (index >= length / 2) {
                        $(this).find(".erjiMenuBox").css("bottom", "0%");
                    }
                }


                //二级菜单鼠标划出就消失
                $(".erjiMenuBox").mouseout(function() {
                    $(".erjiMenuBox").hide();
                    $(this).parents(".menuBox .menu li").find(".more").show();
                    $(this).parents(".menuBox .menu li").find(".packUp").hide();
                });
            });



            // 显示、隐藏定位
            // $('.packUpImg,.showUpImg').css({left:-($('.menuBox.posAbs').width()+45)});
            //菜单Menu显示隐藏
            $(".showUpImg").on("click", function() {
                $(".menuBox").css({
                    "left": "2%",
                    "opacity": "1"
                });
                $(this).hide();
                $(".packUpImg").show();
                $(".tabHtmlBox").css("width", "81%");
                //方法1
                // $('#ifr').attr('src', $('#ifr').attr('src'));
            })
            $(".packUpImg").on("click", function() {
                $(".menuBox").css({
                    "left": "-10%",
                    "opacity": "0"
                });
                $(this).hide();
                $(".showUpImg").show();
                $(".tabHtmlBox").css("width", "94%");
                // $('#ifr').attr('src', $('#ifr').attr('src'));
            })

            //二级菜单点击之后tab栏的显示效果
            var clickFunc = function() {
                if ($(this).find(".erjiMenuBox").length > 0) {
                    return;
                }
                $(this).parents(".erjiMenuBox").hide();
                var text = $(this).find(">span").text() || $(this).find(">div>span").text();
                var href = $(this).attr("data-href");

                if (!href) {
                    return;
                }

                setTimeout(function() {
                	//注意：打开tab的昂发已经转移到这个函数中，因为其他地方也会用，比如主页尿检预警右上角的的“更多”，要更改请更改该函数的内容
                	addMenuTab(text,href);
                }, 100);
            };
            $(document).on('click.index_menu', ".menuBox .menu>li", clickFunc);
            $(document).on('click.index_menu', ".erjiMenuBox li", clickFunc);

            //右上角的菜单显示隐藏
            $(".loginBox .con").eq(1).on("click", function() {
                var that = this;
                var displayType = $(that).find(".downBox").css("display");
                if (displayType == 'none') {
                    $(that).find(".downBox").show();
                    $(that).find(".downImg").hide();
                    $(that).find(".upImg").show();
                    //菜单的高度方法
                    var LiH = $(that).find(".downBox ul li").height();
                    var lisLen = $(that).find(".downBox ul li").length;
                    $(that).find(".downBox").height(LiH * lisLen + 30 + 'px');
                    $(that).find(".downBox ul li").on("click", function() {
                        $(this).parents(".downBox").hide();
                    });
                    $(".downBox").mouseover(function() {
                        $(this).show();
                        $(this).parent(".con").find(".downImg").hide();
                        $(this).parent(".con").find(".upImg").show();
                    })
                    $(".downBox").mouseout(function() {
                        $(this).hide();
                        $(this).parent(".con").find(".downImg").show();
                        $(this).parent(".con").find(".upImg").hide();
                    })
                } else {
                    $(that).find(".downBox").hide();
                    $(that).find(".downImg").show();
                    $(that).find(".upImg").hide();
                }
            });

        }
    });

    //绑定修改密码功能
    $(".update-pwd").click(function() {
        var html = '<form class="layui-form" id="update-pwd-form" action="" style="padding: 20px;">';
        html += '<div class="layui-form-item">';
        html += '	<label class="layui-form-label">原密码</label>';
        html += '    <div class="layui-input-block">';
        html += '    	<input type="password" name="oldPwd" lay-verify="oldPwd" autocomplete="off" placeholder="请输入原密码" class="layui-input">';
        html += '    </div>';
        html += '</div>';

        html += '<div class="layui-form-item">';
        html += '	<label class="layui-form-label">新密码</label>';
        html += '    <div class="layui-input-block">';
        html += '    	<input type="password" name="newPwd" lay-verify="newPwd" autocomplete="off" placeholder="请输入新密码" class="layui-input">';
        html += '    </div>';
        html += '</div>';

        html += '<div class="layui-form-item">';
        html += '	<label class="layui-form-label">新密码确认</label>';
        html += '    <div class="layui-input-block">';
        html += '    	<input type="password" name="newPwdConfirm" lay-verify="newPwdConfirm" autocomplete="off" placeholder="请再次输入新密码" class="layui-input">';
        html += '    </div>';
        html += '</div>';
        html += '</form>';

        var updatePwdWinIndex = layer.open({
            type: 1,
            offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            id: 'update-pwd-win', //防止重复弹出
            content: html,
            title: '修改密码',
            area: '500px',
            btn: '确认修改',
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) {
                var form = $("form#update-pwd-form");
                var oldPwd = form.find("input[name=oldPwd]").val();
                var newPwd = form.find("input[name=newPwd]").val();
                var newPwdConfirm = form.find("input[name=newPwdConfirm]").val();
                if (oldPwd == null || oldPwd == "") {
                    layer.msg('请填写原密码', { icon: 5 });
                    return;
                }
                if (newPwd == null || newPwd == "") {
                    layer.msg('请填写新密码', { icon: 5 });
                    return;
                }
                if (newPwdConfirm == null || newPwdConfirm == "") {
                    layer.msg('请填写新密码确认', { icon: 5 });
                    return;
                }
                if (newPwd != newPwdConfirm) {
                    layer.msg('两次新密码输入不一致，请确认', { icon: 5 });
                    return;
                }
                if (newPwd == oldPwd) {
                    layer.msg('新密码与原密码不能相同', { icon: 5 });
                    return;
                }
                $.ajax({
                    url: "/User/updatePwd",
                    //json文件位置
                    type: "POST",
                    //请求方式为get
                    dataType: "json",
                    data: {
                        oldPwd: oldPwd,
                        newPwd: newPwd,
                        newPwdConfirm: newPwdConfirm
                    },
                    //返回数据格式为json
                    success: function(data) {
                        if (data.success == true) {
                            layer.msg('密码修改成功', { icon: 6 });
                            layer.close(updatePwdWinIndex);
                        } else {
                            layer.msg(data.errorMsg, { icon: 5 });
                        }
                    }
                });
            }
        });
    });

    //暂时放个帮助页面
    $(".update-user").click(function() {
        layer.open({
            type: 2,
            title: false,
            maxmin: false,
            area: ['60%', '90%'],
            content: "/Index/help.html"
        });
    });
    //绑定注销功能
    $(".cancellationImg").click(function() {
        layer.confirm('是否确定退出系统?', {
                icon: 3,
                title: '提示'
            },
            function(index) {
                $.ajax({
                    url: "/loginRpc/logout.json",
                    //json文件位置
                    type: "GET",
                    //请求方式为get
                    dataType: "json",
                    //返回数据格式为json
                    success: function(data) {
                        if (data.success == true) {
                            window.location.href = "/Login/login.html";
                        } else {
                            layer.msg(data.errorMsg || '注销失败，请联系管理员', { icon: 5 });
                        }
                    }
                });
            });
    });
});

/**
 * 添加菜单tab
 * @param text tab上显示的文本
 * @param href 链接地址
 */
function addMenuTab(text,href){
	var arr = [];
	$(".tabHtmlBox .tabBox ul li").each(function(index, item) {
        var dataHref = $(item).attr("data-href");
        arr.push(dataHref);
    })
    var ind = $.inArray(href, arr);
    if (ind >= 0) {
        // $(".htmlBox").attr("src", "../" + href + ".html");
        $(".tabHtmlBox .tabBox ul li").eq(ind).addClass("active").siblings().removeClass("active");
        $('#htmlBox li').eq(ind).show().siblings().hide();
    } else {
        $(".tabHtmlBox .tabBox ul").append("<li class='posRel' data-href=" + href + "><img src='../images/index/menu/close.png' class='posAbs closeImg mid'><img src='../images/index/menu/caidan-xuanzhong.png' class='posAbs wh100 tabBgImg'><span class='posAbs mid desc'>" + text + "</span></li>");
        $(".tabHtmlBox .tabBox ul li:last-child").addClass("active").siblings().removeClass("active");
        // $(".htmlBox").attr("src", "../" + href + ".html");
        $("#htmlBox>ul").append('<li data-href="" ><iframe src="../' + href + '.html" scrolling="auto" frameborder="0"></iframe></li >');
        $('#htmlBox ul li:last-child').show().siblings().hide();
        setTimeout(function() {
            //tab栏删除效果
            $(".tabHtmlBox .tabBox ul li").find(".closeImg").on("click", function() {
                // $(this).parent("li").remove();
                // $(".htmlBox").attr("src", "/Index/indexHtml");
                var _tabIndex = $(this).closest('li').index();
                var _conIndex = $('#htmlBox ul li:not(:hidden)').index();

                if (_tabIndex == -1) {
                    return;
                }

                if (_tabIndex === _conIndex) {
                    $(".tabHtmlBox .tabBox ul li").first().addClass('active');
                    $('#htmlBox ul li').first().show();
                }

                $(this).parent("li").remove();
                $('#htmlBox ul li').eq(_tabIndex).remove();
            })
            //iframe页面的加载页面方法
            $(".tabHtmlBox .tabBox ul li").on("click", function() {
            	if($(this).hasClass('dsw-click-refresh')){
            		$('#htmlBox ul li').eq($(this).closest('li').index()).find('iframe').prop('src',$(this).data('href'));
            	}
            
                // var href = $(this).attr("data-href");
                $(this).addClass("active").siblings().removeClass("active");
                // $(".htmlBox").attr("src", "../" + href + ".html");
                $('#htmlBox ul li').eq($(this).closest('li').index()).show().siblings().hide();
            });
        }, 100)
    }
}
