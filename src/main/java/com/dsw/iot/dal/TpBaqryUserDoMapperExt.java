package com.dsw.iot.dal;

import com.dsw.iot.dal.base.TpBaqryUserDoMapper;
import com.dsw.iot.model.TpBaqryUserDo;

import org.apache.ibatis.annotations.Param;

public interface TpBaqryUserDoMapperExt extends TpBaqryUserDoMapper {

    /**
     * 根据询问室房间号查询当前在使用的用户
     */
    public TpBaqryUserDo selectNowUserByRoomNo(@Param("roomNo") String roomNo);
}
