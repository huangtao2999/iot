'use strict';

+function (win,doc,$,undefined) {
    $('[data-toggle="dsw-slimscroll"]').each(function (index,item) {

        item.dsw_options={
            height:'auto'
        };

        $(item).slimscroll(item.dsw_options).on('resize',function (e) {
            $(this).slimscroll(item.dsw_options);
        });

        $(window).on('resize',function (e) {
            $(item).slimscroll(item.dsw_options);
        });
    });
}(window,document,jQuery);
