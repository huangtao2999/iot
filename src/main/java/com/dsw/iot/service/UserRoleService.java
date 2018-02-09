package com.dsw.iot.service;

public interface UserRoleService {
    /**
     * 查询当前用户有哪些角色
     *
     * @param userId
     * @return
     */
    String selectRolesByUserId(Long userId);
}
