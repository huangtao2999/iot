package com.dsw.iot.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 手写板签名
 **/
@Controller
@RequestMapping("/SignHtml")
public class SignHtml {

    @RequestMapping("/signBoard")
    public String signBoard(Model model, String name) {
        model.addAttribute("name", name);
        return "signature/signBoard";
    }


}
