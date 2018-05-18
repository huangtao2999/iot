package com.dsw.iot.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BurnRecordHtml")
public class BurnRecordHtml {

	@RequestMapping("/index")
	public String index(Model model) {
		return "burnRecord/index";
	}
	
}
