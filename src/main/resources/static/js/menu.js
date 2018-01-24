$(document).ready(function(){
	
    //下拉菜单
	$('.menu').each(function(){
		var childobj = $(".i-"+this.id);
		$(this).click(function(){
			if($(childobj).length > 0) {
				if($(childobj).is(':visible')) {
							$(childobj).hide();
							$(this).removeClass('active');
				} else {
					$('.nav-list').each(function(){
							$(this).hide();
							$('.menu').removeClass('active');
					});
							$(childobj).show();
							$(this).addClass('active');
				}
				return false;	
			}
		});
	});

    //顶托下拉框
    $(".top_icon .wy-user-li .top_a").closest("li").hover(function(e) {
        $(this).toggleClass("open");
    });;    
});