$(document).ready(function () {
    var heightAll = $(".mainConBox").height();
    $("#diologBox").height(heightAll + 'px');
});

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

//文本域高度自适应
$("textarea").onkeyup = function () {
	this.style.height = 'auto';
	this.style.height = this.scrollHeight + "px";
}