package com.dsw.iot.controller.html;

import com.dsw.iot.model.RoleDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.service.UserRoleService;
import com.dsw.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public String login(Model model) {
        return "user/index";
    }

    /**
     * 新增用户页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(Model model, HttpServletRequest request,
                          HttpServletResponse response) {
        //查询角色
        List<RoleDo> roleList = roleService.selectAllRole();
        model.addAttribute("roleList", roleList);
        return "user/addUser";
    }

    /**
     * 编辑用户页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        UserDo userEntity = new UserDo();
        if (null != id) {
            //有id，是更新记录
            userEntity = userService.selectByPrimaryKey(id);
        }
        //查询所有角色
        List<RoleDo> roleList = roleService.selectAllRole();
        //查询当前用户的角色id
        String roleIds = userRoleService.selectRolesByUserId(id);
        //数据放attribute，页面去取值
        model.addAttribute("roleIds", roleIds);
        model.addAttribute("roleList", roleList);
        model.addAttribute("item", userEntity);
        return "user/editUser";
    }

    /**
     * 查看用户详情
     *
     * @param model
     * @return
     */
    @RequestMapping("/detailDialog")
    public String detailDialog(Model model, Long id) {
        UserDo userEntity = new UserDo();
        if (null != id) {
            //有id，是更新记录
            userEntity = userService.selectByPrimaryKey(id);
        }
        //查询所有角色
        List<RoleDo> roleList = roleService.selectAllRole();
        //查询当前用户的角色
        String roleIds = userRoleService.selectRolesByUserId(id);
        //数据放attribute，页面去取值
        model.addAttribute("roleIds", roleIds);
        model.addAttribute("roleList", roleList);
        model.addAttribute("item", userEntity);
        return "user/detail";
    }


}
