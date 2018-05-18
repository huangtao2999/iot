package com.dsw.iot.service;

import com.dsw.iot.dto.BurnRecordRequest;
import com.dsw.iot.model.BurnRecordDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.BurnRecordVo;

public interface BurnRecordService {
	
	public PageResult<BurnRecordVo> queryPage(BurnRecordRequest burnRecordRequest);
	
	public BurnRecordDo createAndStore(PersonRegisterDo personRegisterDo);
	
	public boolean hasBurningTaskToday(long registerId);
	
	public void updateRecordFailed(BurnRecordDo record);
	
}
