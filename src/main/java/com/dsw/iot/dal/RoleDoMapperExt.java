package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.RoleDoMapper;
import com.dsw.iot.dto.RoleRequest;
import com.dsw.iot.model.RoleDo;

public interface RoleDoMapperExt extends RoleDoMapper {
	/**
	 * 通过用户id查询该用户的角色列表
	 * 
	 * @param param
	 * @return
	 */
    List<RoleDo> selectRoleDoListByUserId(RoleRequest param);

	/**
	 * 通过主键删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKeyReal(Long id);
}