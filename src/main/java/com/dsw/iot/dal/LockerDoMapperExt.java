package com.dsw.iot.dal;

import com.dsw.iot.dal.base.LockerDoMapper;

public interface LockerDoMapperExt extends LockerDoMapper {
	String selectLockerNo(String type);
}