package com.dsw.iot.controller.index.html;

import com.dsw.iot.model.TpMenuDo;
import com.dsw.iot.model.TpUserDo;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.util.CookieUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页页面控制器
 *
 * @author huangt
 * @create 2018-01-16 15:45
 **/
@Controller
@RequestMapping("/Index")
public class IndexHtml {
    @Autowired
    private MenuSerivce menuSerivce;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        TpUserDo tpUserDo = CookieUtil.getUserFromCookie(request, response);
        //获取不到用户 跳转到登录页面
        if (null == tpUserDo) {
            return "/login/login";
        }
        tpUserDo.setPassword(null);
        model.addAttribute("user", tpUserDo);
        //初始化菜单
        List<TpMenuDo> menus = menuSerivce.findMenuByUserId(tpUserDo.getId());
        model.addAttribute("menus", menus);
        //
        return "/index/index";
    }

    @RequestMapping("/top")
    public String top(Model model, HttpServletRequest
            request, HttpServletResponse response) {
        initModel(model, request, response);
        return "/index/top";
    }

    @RequestMapping("/left")
    public String left(Model model, HttpServletRequest
            request, HttpServletResponse response) {
        TpUserDo tpUserDo = CookieUtil.getUserFromCookie(request, response);
        if (null != tpUserDo) {
            //初始化菜单
            List<TpMenuDo> menus = menuSerivce.findMenuByUserId(tpUserDo.getId());
            model.addAttribute("menus", menus);
        }
        return "/index/left";
    }

    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest
            request, HttpServletResponse response) {
        initModel(model, request, response);
        return "/index/main";
    }

    private void initModel(Model model, HttpServletRequest request, HttpServletResponse response) {
        TpUserDo tpUserDo = CookieUtil.getUserFromCookie(request, response);
        tpUserDo.setPassword(null);
        model.addAttribute("user", tpUserDo);
    }

}
