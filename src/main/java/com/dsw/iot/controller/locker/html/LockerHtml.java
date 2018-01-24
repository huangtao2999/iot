package com.dsw.iot.controller.locker.html;

import com.dsw.iot.service.LockerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 储物柜管理页面控制器
 *
 * @author huangt
 * @create 2018-01-17 22:08
 **/
@Controller
@RequestMapping("/Locker")
public class LockerHtml {

    @Autowired
    private LockerService lockerService;

    @RequestMapping("index")
    public String index(Model model) {
//        List<TpLockerDo> lockers = lockerService.queryLocker();
//        model.addAttribute("lockers", lockers);
        return "/locker/index";
    }
}
