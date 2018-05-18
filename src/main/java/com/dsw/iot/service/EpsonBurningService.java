package com.dsw.iot.service;

import com.dsw.iot.util.BizException;

public interface EpsonBurningService {
	
	public void sendBurningRequest(long registerId) throws BizException ;
	
	public String getBurningState(long registerId);
}
