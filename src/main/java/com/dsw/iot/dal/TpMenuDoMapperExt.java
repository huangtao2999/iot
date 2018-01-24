package com.dsw.iot.dal;

import com.dsw.iot.dal.base.TpMenuDoMapper;
import com.dsw.iot.model.TpMenuDo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TpMenuDoMapperExt extends TpMenuDoMapper {

    public List<TpMenuDo> selectTpMenuDoByUserId(@Param("userId") long userId);
}