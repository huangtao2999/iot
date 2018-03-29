package com.dsw.iot.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 出所刷胸牌
 * @author zc
 *
 */
@Controller
@RequestMapping("/OutCardHtml")
public class OutCardHtml {

    @RequestMapping("/index")
    public String index(Model model) {
        return "outCard/index";
    }
}
