package com.dsw.iot.service;

import com.dsw.iot.model.TpMenuDo;

import java.util.List;


/**
 * 菜单服务
 */
public interface MenuSerivce {

    public List<TpMenuDo> findMenuByUserId(Long userId);
}
