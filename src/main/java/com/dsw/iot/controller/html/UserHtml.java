package com.dsw.iot.controller.html;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.RoleDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.service.UserRoleService;
import com.dsw.iot.service.UserService;

//这里的url名、方法名、html文件名保持一致
@Controller
@RequestMapping("/UserHtml")
public class UserHtml {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;

    /**
     * 跳到用户管理首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
	public String index(Model model) {
        return "user/index";
    }

    /**
     * 新增用户页面
     *
     * @param model
     * @return
     */
	@RequestMapping("/add")
	public String add(Model model, HttpServletRequest request,
                          HttpServletResponse response) {
        //查询角色
		List<RoleDo> roleList = roleService.listAllRole();
        model.addAttribute("roleList", roleList);
		return "user/add";
    }

    /**
     * 编辑用户页面
     *
     * @param model
     * @return
     */
	@RequestMapping("/edit")
	public String edit(Model model, Long id) {
        UserDo userEntity = new UserDo();
        if (null != id) {
            //有id，是更新记录
			userEntity = userService.getUser(id);
        }
        //查询所有角色
		List<RoleDo> roleList = roleService.listAllRole();
        //查询当前用户的角色id
        String roleIds = userRoleService.selectRolesByUserId(id);
        //数据放attribute，页面去取值
        model.addAttribute("roleIds", roleIds);
        model.addAttribute("roleList", roleList);
        model.addAttribute("item", userEntity);
		return "user/edit";
    }

    /**
     * 查看用户详情
     *
     * @param model
     * @return
     */
	@RequestMapping("/detail")
	public String detail(Model model, Long id) {
        UserDo userEntity = new UserDo();
        if (null != id) {
            //有id，是更新记录
			userEntity = userService.getUser(id);
        }
        //查询所有角色
		List<RoleDo> roleList = roleService.listAllRole();
        //查询当前用户的角色
        String roleIds = userRoleService.selectRolesByUserId(id);
        //数据放attribute，页面去取值
        model.addAttribute("roleIds", roleIds);
        model.addAttribute("roleList", roleList);
        model.addAttribute("item", userEntity);
        return "user/detail";
    }


}
