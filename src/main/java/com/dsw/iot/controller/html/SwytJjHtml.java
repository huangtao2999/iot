package com.dsw.iot.controller.html;
	
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 储物柜管理页面控制器
 *
 * @author huangt
 * @create 2018-01-17 22:08
 **/
@Controller
@RequestMapping("/swytJj")
public class SwytJjHtml {

	/**
	 * 储物柜管理页面首页
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
    public String index(Model model) {
        return "swytJj/index";
    }
}
