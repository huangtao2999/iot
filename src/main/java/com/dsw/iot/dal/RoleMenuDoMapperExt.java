package com.dsw.iot.dal;

import com.dsw.iot.dal.base.RoleMenuDoMapper;

public interface RoleMenuDoMapperExt extends RoleMenuDoMapper {

	/**
	 * 通过角色id，删除角色对应的菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleId(Long roleId);
}