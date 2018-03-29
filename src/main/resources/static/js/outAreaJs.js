$(document).ready(function () {
    //其他的男女选择的样式方法
    $(".sexOneBox").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    })

    //柜子开关的点击效果样式
    $(".switchBox").on("click", function () {
        var swiType = $(this).find(".kaiImg").css("display");
        if (swiType == 'none') {
            $(this).find(".kaiImg").show();
            $(this).find(".guanImg").hide();
        } else {
            $(this).find(".kaiImg").hide();
            $(this).find(".guanImg").show();
        }
    });

    //图片位置的高度
    var perItemH = $(".perItemsBox .leftBox").height();
    $(".perItemsBox .rightBox").height(perItemH + 'px');

    //取物图片的点击删除方法
    $(".outAreaBox .itemsInfoBox .itemsInfoMainBox .columIscBox ul li .outpics .imgsBox .imgOne .closeImg").on("click", function () {
        $(this).parents(".imgOne").remove();
    })

})