package com.dsw.iot.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.service.PersonTraceService;

@Controller
@RequestMapping("/PersonTraceHtml")
public class PersonTraceHtml {

	@Autowired
	PersonTraceService personTraceService;

	/**
	 * 人员轨迹
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model, Long registerId) {
		model.addAttribute("registerId", registerId);
		return "personTrace/index";
	}
}
