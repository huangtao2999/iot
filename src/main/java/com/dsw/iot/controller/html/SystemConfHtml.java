package com.dsw.iot.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.LockerDo;

/**
 *  属性配置页面控制
 * 
 * @author dearseewe
 *
 */
@Controller
@RequestMapping("/SystemConfig")
public class SystemConfHtml {
   
    
    /**
	 * 新增页面
	 *
	 * 
	 */
@RequestMapping("/add")
public String add() {
    return "systemConfig/add";
}


	/**
	 * 查看页面
	 *
	 
	 */
@RequestMapping("/index")
public String index(Model model, Long id) {
    
    return "systemConfig/index";
}


	/**
	 * 编辑页面
	 *
	
	 */
@RequestMapping("/edit")
public String edit(Model model, Long id) {
		
    
    return "systemConfig/edit";
}

}
