package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.SwytJjRequest;
import com.dsw.iot.model.SwytJjDo;
import com.dsw.iot.service.SwytJjService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/SwytJj")
public class SwytJjRpc {

	@Autowired
	private SwytJjService swytJjService;
	
	@RequestMapping("/queryPage")
	public PageResult<SwytJjDo> queryPage(SwytJjRequest swytJjRequest) {
		PageResult<SwytJjDo> pageResult = swytJjService.queryPage(swytJjRequest);
		return pageResult;
	}
}
