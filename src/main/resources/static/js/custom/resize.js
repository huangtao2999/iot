$(function (  ) {
  function _resize(e){
    var _height=$('body.custom').find('form').height();
    var _width=$('body.custom').find('form').width();

    $('body.custom').find('.container-fluid').css({width:_width,height:_height});
  }
  $(window).on('resize reload',_resize);
});
