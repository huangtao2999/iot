package com.dsw.iot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsw.iot.dto.RoleRequest;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

public interface RoleService {
    /**
     * 分页查询角色
     *
     * @param param
     * @return
     */
    public PageResult<RoleDo> queryPage(RoleRequest param);

    /**
     * 通过id查询角色
     *
     * @param id
     * @return
     */
	RoleDo getRole(Long id);

    /**
     * 新增/编辑角色
     *
     * @param record
     * @param loginUserDo
     * @return
     */
	void saveRole(HttpServletRequest request, RoleDo record);

    /**
	 * 删除角色
	 *
	 * @param param
	 * @return
	 * @throws BizException
	 */
	void removeRole(RoleRequest param) throws BizException;

    /**
     * 用户选择角色，查询所有角色
     *
     * @return
     */
	List<RoleDo> listAllRole();

    /**
     * 登录时，查询登录人拥有的所有角色
     *
     * @return
     */
	List<RoleDo> listRoleByUserId(Long userId);

	/**
	 * 查询所有有效角色
	 * @return
	 */
	List<RoleDo> listRoles(RoleRequest param);
}
