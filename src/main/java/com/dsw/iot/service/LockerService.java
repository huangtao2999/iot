package com.dsw.iot.service;

import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.model.TpLockerDo;
import com.dsw.iot.util.PageResult;

/**
 * 储物柜服务
 */
public interface LockerService {
    /**
     * 分页查询储物柜
     */
    public PageResult<TpLockerDo> queryPage(LockerResquest request);
}
