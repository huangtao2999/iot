package com.dsw.iot.controller.html;

import com.dsw.iot.model.MenuDo;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @Autowired
    RoleService roleService;

    @Value("${driverUrl}")
    private String driverUrl;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        //获取不到用户 跳转到登录页面
        if (null == userDo) {
            return "login/login";
        }
        userDo.setPassword(null);
        model.addAttribute("user", userDo);
        //初始化菜单
        List<MenuDo> menus = menuSerivce.findMenuByUserId(userDo.getId());
        model.addAttribute("menus", menus);
        // 查询当前用户的角色，返回给前台
        List<RoleDo> roleHas = roleService.listRoleByUserId(userDo.getId());
        model.addAttribute("roleList", roleHas);
        return "index/index";
    }

    @RequestMapping("/top")
    public String top(Model model, HttpServletRequest
            request, HttpServletResponse response) {
        initModel(model, request, response);
        return "index/top";
    }

    @RequestMapping("/left")
    public String left(Model model, HttpServletRequest
            request, HttpServletResponse response) {
        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        if (null != userDo) {
            //初始化菜单
            List<MenuDo> menus = menuSerivce.findMenuByUserId(userDo.getId());
            model.addAttribute("menus", menus);
        }
        return "index/left";
    }

    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest
            request, HttpServletResponse response) {
        initModel(model, request, response);
        return "index/main";
    }

    private void initModel(Model model, HttpServletRequest request, HttpServletResponse response) {
        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        //查询当前用户的角色，返回给前台
        List<RoleDo> roleHas = roleService.listRoleByUserId(userDo.getId());
        model.addAttribute("user", userDo);
        model.addAttribute("roleList", roleHas);
    }

    /**
     * 新界面引用（中间内容部分）
     *
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/indexHtml")
    public String indexHtml(Model model, HttpServletRequest request, HttpServletResponse response) {
        initModel(model, request, response);
        return "index/indexHtml";
    }

    @RequestMapping("/help")
    public String help(Model model) {
        model.addAttribute("driverUrl", driverUrl);
        return "index/help";
    }


}
