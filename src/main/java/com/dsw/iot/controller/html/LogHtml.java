package com.dsw.iot.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.LogDo;
import com.dsw.iot.service.LogService;

@Controller
@RequestMapping("/LogHtml")
public class LogHtml {

    @Autowired
    LogService logService;

    /**
     * 跳转到日志查询首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "logs/index";
    }

    /**
     * 查看日志详情
     *
     * @param model
     * @return
     */
    @RequestMapping("/detailDialog")
    public String detailDialog(Model model, Long id) {
        LogDo logDo = logService.selectByPrimaryKey(id);
        model.addAttribute("item", logDo);
        return "logs/detail";
    }
}
