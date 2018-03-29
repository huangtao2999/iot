package com.dsw.iot.controller.html;

import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.DelayConfirmDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.DelayConfirmService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/DelayConfirmHtml")
public class DelayConfirmHtml {

    @Autowired
    DelayConfirmService delayConfirmService;
    @Autowired
    PersonRegisterService personRegisterService;

    /**
     * 延期置留首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "delayConfirm/index";
    }

    /**
     * 新增界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model, Long registerId, HttpServletRequest request, HttpServletResponse response) {
        PersonRegisterRequest personRegisterRequest = new PersonRegisterRequest();
        if (null != registerId) {
            personRegisterRequest = personRegisterService.getPersonRegisterInfo(registerId);
        }
        model.addAttribute("personRegisterDo", personRegisterRequest);

        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
        return "delayConfirm/add";
    }

    /**
     * 编辑页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id, HttpServletRequest request, HttpServletResponse response) {
        DelayConfirmDo delayConfirmDo = new DelayConfirmDo();
        PersonRegisterRequest personRegisterRequest = new PersonRegisterRequest();
        if (null != id) {
            delayConfirmDo = delayConfirmService.getDelayConfirm(id);
        }
        if (null != delayConfirmDo.getRegisterId()) {
            personRegisterRequest = personRegisterService.getPersonRegisterInfo(delayConfirmDo.getRegisterId());
        }
        model.addAttribute("delayConfirmDo", delayConfirmDo);
        model.addAttribute("personRegisterDo", personRegisterRequest);

        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
        return "delayConfirm/edit";
    }

    /**
     * 查看详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public String detail(Model model, Long id) {
        DelayConfirmDo delayConfirmDo = new DelayConfirmDo();
        PersonRegisterRequest personRegisterRequest = new PersonRegisterRequest();
        if (null != id) {
            delayConfirmDo = delayConfirmService.getDelayConfirm(id);
        }
        if (null != delayConfirmDo.getRegisterId()) {
            personRegisterRequest = personRegisterService.getPersonRegisterInfo(delayConfirmDo.getRegisterId());
        }
        model.addAttribute("delayConfirmDo", delayConfirmDo);
        model.addAttribute("personRegisterDo", personRegisterRequest);
        return "delayConfirm/detail";
    }
}
