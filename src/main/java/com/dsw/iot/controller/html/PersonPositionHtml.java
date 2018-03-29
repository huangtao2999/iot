package com.dsw.iot.controller.html;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PersonPositionHtml")
public class PersonPositionHtml {

    @Value("${person.position.url}")
    private String url;
    @Value("${form.org}")
    private String orgName;

    /**
     * 跳到3D人员定位首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("url", url);
        model.addAttribute("orgName", orgName);
        return "personPosition/index";
    }

}
