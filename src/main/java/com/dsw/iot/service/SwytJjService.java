package com.dsw.iot.service;

import com.dsw.iot.dto.SwytJjRequest;
import com.dsw.iot.model.SwytJjDo;
import com.dsw.iot.util.PageResult;

public interface SwytJjService {
	
	public PageResult<SwytJjDo> queryPage(SwytJjRequest swytJjRequest);
	
	
}
