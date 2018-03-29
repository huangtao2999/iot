package com.dsw.iot.dal;

import com.dsw.iot.dal.base.UserRoleDoMapper;

public interface UserRoleDoMapperExt extends UserRoleDoMapper {
	/**
	 * 删除该用户的角色
	 * 
	 * @param id
	 * @return
	 */
	int deleteByUserId(Long id);
}