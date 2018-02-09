package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.MenuDoMapper;
import com.dsw.iot.dto.MenuRequest;
import com.dsw.iot.model.MenuDo;

public interface MenuDoMapperExt extends MenuDoMapper {
    /**
     * 查询用户id下的菜单
     *
     * @param param
     * @return
     */
    public List<MenuDo> findMenuByUserId(MenuRequest param);

    /**
     * 查询角色id下的菜单
     *
     * @param param
     * @return
     */
    public List<MenuDo> findMenuByRoleId(MenuRequest param);
}