$(document).ready(function () {
    //顶部tab栏点击方法
    $(".lawCtrTabBox ul li").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    })

    //身份分类的点击样式
    $(".IdentyBox ul li").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    })

    //多选框的样式
    $(".infoExBox .boxesBox .formBox").each(function (index, item) {
        var boxesH = $(item).height();
        // console.log(boxesH);
        $(item).parents(".boxesBox").find(".mandatoryImgBox").height('25px');
        $(item).parents(".boxesBox").find(".descCon").css('lineHeight', boxesH + 'px');
    });

    //存物图片点击删除方法
    //$(".lawConBox .workAreaBox .itemsInfoBox .itemsInfoMainBox .columIscBox ul").on("click",'li .pics .imgsBox .imgOne .closeImg', function () {
    //    $(this).parents(".imgOne").remove();
    //})

    //存物全删的方法
    //$(".lawConBox .workAreaBox .itemsInfoBox .itemsInfoMainBox .columIscBox ul").on("click",'li .pics .delAll', function () {
    //    $(this).siblings(".imgsBox").html("");
    //})


    //添加按钮点击的方法
    $(".addBtnBox").on("click", function () {
        var type=$(this).data('type');
        addPresonGoods({},"."+$(this).attr("cls"),type);
    });

    //文本域高度自适应
    $("textarea").onkeyup = function () {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + "px";
    }

//    //页面的下一步上一步的页面切换方法
//
//    var indexPage = 1; //默认是第一个页面显示;
//    $(".nextBox").on("click", function () {
//        if (indexPage == 1) { //人员信息盒子隐藏;办案区信息盒子显示;
//            $(".perMsgBox").hide();
//            $(".lawCtrTabBox").hide();
//            $(".workAreaBox").show();
//            $(".otherConBox").hide();
//            $(".btnBox .prevBox").show();
//            $(".lawConTabBox .tabCon").eq(indexPage).addClass("active");
//            $(".lawConTabBox .tabCon").eq(indexPage).siblings().removeClass("active");
//            indexPage = 2;
//        } else if (indexPage == 2) {
//            $(".perMsgBox").hide();
//            $(".workAreaBox").hide();
//            $(".otherConBox").show();
//            $(".btnBox .nextBox").hide();
//            $(".lawConTabBox .tabCon").eq(indexPage).addClass("active");
//            $(".lawConTabBox .tabCon").eq(indexPage).siblings().removeClass("active");
//            indexPage = 3;
//        }
//    });
//    $(".prevBox").on("click", function () {
//        if (indexPage == 2) { //人员信息盒子隐藏;办案区信息盒子显示;
//            $(".lawCtrTabBox").show();
//            $(".perMsgBox").show();
//            $(".workAreaBox").hide();
//            $(".otherConBox").hide();
//            $(".btnBox .prevBox").hide();
//            $(".btnBox .nextBox").show();
//            $(".lawConTabBox .tabCon").eq(indexPage - 2).addClass("active");
//            $(".lawConTabBox .tabCon").eq(indexPage - 2).siblings().removeClass("active");
//            indexPage = 1;
//        } else if (indexPage == 3) {
//            $(".perMsgBox").hide();
//            $(".workAreaBox").show();
//            $(".otherConBox").hide();
//            $(".btnBox .nextBox").show();
//            $(".lawConTabBox .tabCon").eq(indexPage - 2).addClass("active");
//            $(".lawConTabBox .tabCon").eq(indexPage - 2).siblings().removeClass("active");
//            indexPage = 2;
//        }
//    });

    //其他的男女选择的样式方法
    $(".sexOneBox").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
    })

    //多选按钮的方法
    $(".checkOneBox").on("click", function () {
        var acType = $(this).attr("class");
        var index = acType.indexOf('active');
        if (index < 0) {
            $(this).addClass("active");
        } else {
            $(this).removeClass("active");
        }
    })
});

//添加高度自适应的方法
function itemsHeight() {
    var itemMainBoxH = $(".itemsMainBox1").height();
    console.log(itemMainBoxH);
    $(".itemsInfoBox").height(itemMainBoxH + 'px');
}

function createPresonGoodsImg(id){
	var url = '/FileUpload/getAttach.json?id=';
	var imgHtml='';
	if(!!id || parseInt(id,10)===0){
		url += id;
		imgHtml +='<div class="imgOne"><div class="posRel wh100"><img src="'+url+'" alt="" class="posAbs wh100">' +
		'<img src="../images/lawContro//noChooseSel.png" alt="" class="posAbs closeImg"></div></div>';
	}

	return imgHtml;
}

/**
 * 添加物品信息
 * @param data
 * @param cls
 */
function addPresonGoods(data,cls,type){
	if(!data){
		data = {};
	}

	var imgHtml = "";
	var sourceIds = data.sourceIds ? data.sourceIds.split(",") : [];
	for ( var i = 0; i < sourceIds.length; i++) {
		imgHtml+=createPresonGoodsImg(sourceIds[i]);
	}

	var Li = document.createElement("li");
    Li.innerHTML = ' <div class="itemsName" style="width:20%">' +
        '<div class="posRel wh100">' +
        '<div style="position:absolute;width:100%;height:100%;background:#fff;opacity:0;top:0;left:0;"></div>' +
        '<input type="hidden" name="hide-id" value="'+(data.id || "")+'">' +
        '<input type="text" class="posAbs cenMid" name="goodsName" value="'+(data.goodsName || data.part || "")+'">' +
        '<img src="../images/lawContro/shurukuang-wenben.png" alt="" class="posAbs wh100">' +
        '</div>' +
        '</div>' +
        '<div class="itemsName itemDesc" style="width:40%">' +
        '<div class="posRel wh100">' +
        '<div style="position:absolute;width:100%;height:100%;background:#fff;opacity:0;top:0;left:0;"></div>' +
        '<input type="text" class="posAbs cenMid" name="goodsDesc" value="'+(data.goodsDesc || data.remark || "")+'">' +
        '<img src="../images/lawContro/shurukuang-wenben.png" alt="" class="posAbs wh100">' +
        '</div>' +
        '</div>' +
        '<div class="pics" style="width:40%" data-imgs=\''+sourceIds.join(',')+'\'>' +
        '<div class="posRel wh100">' +
        '<div class="imgsBox posAbs mid clear">' +
        imgHtml +
        '</div>' +
        '<img src="../images/lawContro/paizhao.png" alt="" class="cameraImg posAbs mid" data-type="'+type+'">' +
        '<img src="../images/lawContro/delAll.png" alt="" class="delAll posAbs mid">' +
        '<img src="../images/lawContro/shurukuang-wenben.png" alt="" class="posAbs wh100">' +
        '</div>' +
        '</div>';
    $(cls).find(".itemsInfoMainBox ul").append(Li);
    //itemsHeight();
    //存物图片点击删除方法
    /*$(".lawConBox .workAreaBox .itemsInfoBox .itemsInfoMainBox .columIscBox ul li .pics .imgsBox .imgOne .closeImg").on("click", function () {
        $(this).parents(".imgOne").remove();
    })*/

    //存物全删的方法
    /*$(".lawConBox .workAreaBox .itemsInfoBox .itemsInfoMainBox .columIscBox ul li .pics .delAll").on("click", function () {
        $(this).parents("li").remove();
    })*/
}