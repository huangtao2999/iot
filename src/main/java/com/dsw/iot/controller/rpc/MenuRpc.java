package com.dsw.iot.controller.rpc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.MenuRequest;
import com.dsw.iot.model.MenuDo;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.service.UserService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.MenuTreeVo;

@RestController
@RequestMapping("/Menu")
public class MenuRpc {

	@Autowired
	MenuSerivce menuSerivce;
	@Autowired
	UserService userService;

	/**
	 * 新增/编辑菜单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/saveMenu")
	public int saveMenu(MenuDo menuDo) {
		int i = menuSerivce.saveMenu(menuDo);
        return i;
    }

    /**
	 * 删除菜单
	 * 
	 * @param param
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deleteMenuNodeCascade")
	public int deleteMenuNodeCascade(Long id) throws BizException {
		int i = menuSerivce.deleteMenuNodeCascade(id);
    	return i;
    }

    /**
     * 查询菜单树
     * @param model
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/queryTree")
    public ActionResult queryTree( HttpServletRequest request, HttpServletResponse response){
    	ActionResult res = new ActionResult<>();
    	List<MenuTreeVo> menuTree = menuSerivce.findAllMenuToTree();
    	res.setSuccess(true);
    	res.setContent(menuTree);
    	return res;
    }

    /**
     * 通过id查询菜单详情
     * @param param
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getMenuDoByMenuId")
    public ActionResult getMenuDoByMenuId(MenuRequest param){
    	ActionResult res = new ActionResult<>();
    	MenuDo menuDo = menuSerivce.selectByPrimaryKey(param.getId());
    	res.setSuccess(true);
    	res.setContent(menuDo);
    	return res;
    }
}
