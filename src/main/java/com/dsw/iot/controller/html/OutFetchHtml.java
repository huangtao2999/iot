package com.dsw.iot.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.vo.PersonRegisterVo;

/**
 * 出所取物
 * @author zc
 *
 */
@Controller
@RequestMapping("/OutFetchHtml")
public class OutFetchHtml {

	@Value("${form.org}")
	private String org;

	@Autowired
	PersonRegisterService personRegisterService;

    @RequestMapping("/index")
    public String index(Model model) {
        return "outfetch/index";
    }

	@RequestMapping("/outSign")
	public String outSign(Model model, Long registerId) {
		PersonRegisterVo personRegisterVo = new PersonRegisterVo();
		if (null != registerId) {
			personRegisterVo = personRegisterService.getPersonRegisterForm(registerId);
		}
		model.addAttribute("item", personRegisterVo);
		model.addAttribute("org", org);
		return "outfetch/outSign";
	}

}
