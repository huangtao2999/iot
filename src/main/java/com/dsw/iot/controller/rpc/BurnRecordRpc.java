package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.BurnRecordRequest;
import com.dsw.iot.service.BurnRecordService;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.BurnRecordVo;

@RestController
@RequestMapping("/BurnRecordRpc")
public class BurnRecordRpc {

	@Autowired
	private BurnRecordService burnRecordService;
	
	@RequestMapping("/queryPage")
	public PageResult<BurnRecordVo> queryPage(BurnRecordRequest burnRecordRequest) {
		PageResult<BurnRecordVo> pageResult = burnRecordService.queryPage(burnRecordRequest);
		return pageResult;
	}
}
