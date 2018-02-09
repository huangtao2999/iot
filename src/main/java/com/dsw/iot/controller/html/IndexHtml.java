package com.dsw.iot.controller.html;

import com.dsw.iot.model.MenuDo;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.util.CookieUtil;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired(required = false)
    @Qualifier("dmSqlSessionFactoryBean")
    private SqlSessionFactoryBean dmSqlSessionFactoryBean;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {
            SqlSession session = dmSqlSessionFactoryBean.getObject().openSession();
            List list = session.selectList("select * from user");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        //获取不到用户 跳转到登录页面
        if (null == userDo) {
            return "/login/login";
        }
        userDo.setPassword(null);
        model.addAttribute("user", userDo);
        //初始化菜单
        List<MenuDo> menus = menuSerivce.findMenuByUserId(userDo.getId());
        model.addAttribute("menus", menus);
        //
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
        List<RoleDo> roleHas = roleService.selectRoleDoListByUserId(userDo.getId());
        model.addAttribute("user", userDo);
        model.addAttribute("roleList", roleHas);
    }

}
