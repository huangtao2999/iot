package com.dsw.iot.controller.rpc;

import com.dsw.iot.dto.RoleRequest;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public void saveRole(RoleDo role, HttpServletRequest request) {
        roleService.saveRole(request, role);
    }

    /**
     * 删除角色
     *
     * @param param
     * @throws BizException
     */
    @RequestMapping("/removeRole")
    public void removeRole(RoleRequest param) throws BizException {
        roleService.removeRole(param);
    }

    @RequestMapping(value = "/listRoles")
    public List<RoleDo> listRoles(RoleRequest param) {
        return roleService.listRoles(param);
    }
}
