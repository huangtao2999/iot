package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.UserDoMapper;
import com.dsw.iot.dto.UserRequest;
import com.dsw.iot.model.UserDo;

public interface UserDoMapperExt extends UserDoMapper {
    /**
     * 模糊搜索分页查询
     *
     * @param param
     * @return
     */
    List<UserDo> selectByParam(UserRequest param);

    /**
     * 分页查询总量
     *
     * @param param
     * @return
     */
    int countByParam(UserRequest param);

}