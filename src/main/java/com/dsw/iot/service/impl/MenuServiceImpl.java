package com.dsw.iot.service.impl;

import com.dsw.iot.dal.TpMenuDoMapperExt;
import com.dsw.iot.model.TpMenuDo;
import com.dsw.iot.service.MenuSerivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务类
 *
 * @author huangt
 * @create 2018-01-17 11:18
 **/
@Service
public class MenuServiceImpl implements MenuSerivce {
    @Autowired(required = false)
    private TpMenuDoMapperExt tpMenuDoMapperExt;

    @Override
    public List<TpMenuDo> findMenuByUserId(Long userId) {
        return tpMenuDoMapperExt.selectTpMenuDoByUserId(userId);
    }
}
