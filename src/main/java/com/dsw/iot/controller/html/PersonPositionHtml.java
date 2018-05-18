package com.dsw.iot.controller.html;

import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.manager.VideoManager;
import com.dsw.iot.vo.CaptureCfgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PersonPositionHtml")
public class PersonPositionHtml {

    @Value("${person.position.url}")
    private String personPositionUrl;
    @Value("${form.org}")
    private String orgName;
    @Value("${3dmap.zone.video}")
    private String zoneVideo;

    @Autowired
    private VideoManager videoManager;

    /**
     * 跳到3D人员定位首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        //录像机登录参数，和尿检拍照共用一套录像机
        CaptureCfgVo captureCfgVo = videoManager.initCaptureCfg("urine");
        model.addAttribute("ip", captureCfgVo.getIp());
        model.addAttribute("port", captureCfgVo.getWebPort());
        model.addAttribute("userName", captureCfgVo.getUserName());
        model.addAttribute("password", captureCfgVo.getPassword());
        //定位服务器地址
        model.addAttribute("personPositionUrl", personPositionUrl);
        model.addAttribute("orgName", orgName);
        //区域摄像头通道配置
        model.addAttribute("zoneVideo", JSONObject.parse(zoneVideo));
//        return "personPosition/index2";
        return "personPosition/index";

    }

}
