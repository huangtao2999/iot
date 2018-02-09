package com.dsw.iot.controller.html;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.MenuDo;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.vo.MenuTreeVo;

@Controller
@RequestMapping("/MenuHtml")
public class MenuHtml {

    @Autowired
    MenuSerivce menuSerivce;

    /**
     * 跳到菜单管理首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String login(Model model) {
        List<MenuTreeVo> menuTree = menuSerivce.findAllMenuToTree();
        model.addAttribute("menuTree", menuTree);
        return "menu/index";
    }

    /**
     * 编辑当前节点
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editMenuNode")
    public String editMenuNode(Model model, Long id) {
        MenuDo menuDo = new MenuDo();
        if (null != id) {
            menuDo = menuSerivce.selectByPrimaryKey(id);
        }
        model.addAttribute("item", menuDo);
        return "menu/addEdit";
    }

    /**
     * 新增同级/子级节点
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toAddTreeMenuNode")
    public String toAddTreeMenuNode(Model model, Long pid) {
        MenuDo menuDo = new MenuDo();
        menuDo.setPid(pid);
        // 放空对象，避免页面报错
        model.addAttribute("item", menuDo);
        return "menu/addEdit";
    }
}
