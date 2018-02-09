package com.dsw.iot.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.LockerDo;
import com.dsw.iot.service.LockerService;

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
        return "locker/index";
    }

    @RequestMapping("index2")
    public String index2(Model model) {
//        List<TpLockerDo> lockers = lockerService.queryLocker();
//        model.addAttribute("lockers", lockers);
        return "locker/index2";
    }

    // 新增页面
    @RequestMapping("add")
    public String add() {
        return "locker/add";
    }

    // 查看页面
    @RequestMapping("detail")
    public String detail(Model model, Long id) {
        LockerDo lockerDo = lockerService.selectByPrimaryKey(id);
        model.addAttribute("lockerDo", lockerDo);
        return "locker/detail";
    }

    // 编辑页面
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
        LockerDo lockerDo = lockerService.selectByPrimaryKey(id);
        model.addAttribute("lockerDo", lockerDo);
        return "locker/edit";
    }
}
