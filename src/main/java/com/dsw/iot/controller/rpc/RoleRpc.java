package com.dsw.iot.controller.rpc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.RoleRequest;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/Role")
public class RoleRpc {
    @Autowired
    private RoleService roleService;

	/**
	 * 分页查询角色
	 * 
	 * @param resquest
	 * @return
	 */
    @RequestMapping("/queryPage")
    public PageResult<RoleDo> queryPage(RoleRequest resquest) {
        PageResult<RoleDo> pageResult = roleService.queryPage(resquest);
        return pageResult;
    }

	/**
	 * 新增/编辑角色
	 * 
	 * @param role
	 * @param request
	 */
	@RequestMapping("/saveRole")
	public void saveRole(RoleDo role, HttpServletRequest request) {
		roleService.saveRole(request, role);
    }

	/**
	 * 删除角色
	 * 
	 * @param param
	 */
	@RequestMapping("/delRole")
	public void delRole(RoleRequest param) {
		roleService.delRole(param);
    }
}
