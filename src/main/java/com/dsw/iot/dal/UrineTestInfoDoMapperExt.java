package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.UrineTestInfoDoMapper;
import com.dsw.iot.dto.UrineTestInfoRequest;
import com.dsw.iot.model.UrineTestInfoDo;
import com.dsw.iot.model.UrineTestInfoDoExample;

public interface UrineTestInfoDoMapperExt extends UrineTestInfoDoMapper {
	
	int countsByExample(UrineTestInfoRequest parms);
	
	void updatePerson(UrineTestInfoDo urineTestDo,Long id);
	
	List<UrineTestInfoDo> getByExample(UrineTestInfoRequest parms);
	
	List<UrineTestInfoDo> getOverDeadtimeB();
	
	int updateDeadtimeBStatus(UrineTestInfoRequest parms);
	
}