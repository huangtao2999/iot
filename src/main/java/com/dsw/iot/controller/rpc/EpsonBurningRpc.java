package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsw.iot.service.EpsonBurningService;
import com.dsw.iot.util.BizException;

@Controller
@RequestMapping("/BurningRpc")
public class EpsonBurningRpc {
	
	@Autowired
	private EpsonBurningService epsonBurningService;
	
	/**
	 * 刻录笔录
	 * @param registerId
	 * @throws BizException
	 */
	@RequestMapping("/burnBl")
	@ResponseBody
	public void burnBl(long registerId) throws BizException {
		epsonBurningService.sendBurningRequest(registerId);
	}
}
