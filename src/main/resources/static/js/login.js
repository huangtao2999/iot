$(document).ready(function () {
    $(".circleBox .imgS").addClass("rotateF");
    $(".circleBox .imgB").addClass("rotateZ");

    //登录框的placeholder方法
    //login();
    function login() {
    	var textName = $("#username").val();
    	var textPass = $("#password").val();
    	if(textName != ""){
    		$(".nameBox span").hide();
    	}
    	if(textPass != ""){
    		$(".PassBox span").hide();
    	}
        $(".iptBox input").val("");
        $(".iptBox").on("click", function () {
            $(this).find(".pla").hide();
            $(this).find("input").focus();
        });
        $("#password").focus(function () {
            $(this).next().hide();
        });
        $("#password").blur(function () {
            if($(this).val()==""){
                $(this).next().show();
            }
        });
        $("#username").focus(function () {
            $(this).next().hide();
        });
        $("#username").blur(function () {
            if($(this).val()==""){
                $(this).next().show();
            }
        });
    }
})