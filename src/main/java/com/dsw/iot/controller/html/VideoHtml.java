package com.dsw.iot.controller.html;

import com.dsw.iot.manager.VideoManager;
import com.dsw.iot.vo.CaptureCfgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 海康威视视频预览
 *
 * @author huangt
 * @create 2018-02-27 9:33
 **/
@Controller
@RequestMapping("/Video")
public class VideoHtml {
    @Autowired
    private VideoManager videoManager;

    @RequestMapping("/index")
    public String index(Model model, String type) {
        CaptureCfgVo captureCfgVo = videoManager.initCaptureCfg(type);
        model.addAttribute("ip", captureCfgVo.getIp());
        model.addAttribute("port", captureCfgVo.getWebPort());
        model.addAttribute("userName", captureCfgVo.getUserName());
        model.addAttribute("password", captureCfgVo.getPassword());
        model.addAttribute("channel", captureCfgVo.getWebChannel());
        model.addAttribute("type", type);
        return "video/index";
    }

    @RequestMapping("/faceToPeople")
    public String faceToPeople() {
        return "video/faceToPeople";
    }
}
