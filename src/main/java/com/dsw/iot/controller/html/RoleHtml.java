package com.dsw.iot.controller.html;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.MenuDo;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.service.RoleService;

/**
 * 角色管理页面控制
 *
 * @author zcb
 **/
@Controller
@RequestMapping("/RoleHtml")
public class RoleHtml {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuSerivce menuSerivce;

    /**
     * 跳到角色管理首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
	public String index(Model model) {
        return "role/index";
    }

    /**
     * 跳到角色新增编辑页面
     *
     * @param model
     * @return
     */
	@RequestMapping("/addEdit")
	public String addEdit(Model model, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        RoleDo roleEntity = new RoleDo();
        List<MenuDo> menuHas = new ArrayList<MenuDo>();
        if (!StringUtils.isBlank(id)) {
            //有id，是更新记录
			roleEntity = roleService.getRole((long) Integer.parseInt(id));
            //查询当前角色拥有的菜单
            menuHas = menuSerivce.findMenuByRoleId(Long.parseLong(id));
        }
        //查询所有角色
        List<MenuDo> menuAll = menuSerivce.findAllMenu();
        //返回集合
        model.addAttribute("menuHas", menuHas);
        model.addAttribute("menuAll", menuAll);
        model.addAttribute("item", roleEntity);
		return "role/addEdit";
    }

    /**
     * 查看角色详情
     *
     * @param model
     * @return
     */
	@RequestMapping("/detail")
	public String detail(Model model, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        RoleDo roleEntity = new RoleDo();
        if (!StringUtils.isBlank(id)) {
            //有id，是更新记录
			roleEntity = roleService.getRole((long) Integer.parseInt(id));
        }
        model.addAttribute("item", roleEntity);
        return "role/detail";
    }

}
