package com.dsw.iot.controller.comm.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 字典插件控制
 *
 * @author huangt
 * @create 2018-01-26 10:53
 **/
@Controller
@RequestMapping("/dictionary")
public class DictionaryHtml {

    public String index() {
        return "/component/dictionaryIndex";
    }
}
