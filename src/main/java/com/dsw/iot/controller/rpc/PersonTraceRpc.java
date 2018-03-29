package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.PersonTraceRequest;
import com.dsw.iot.model.PersonTraceDo;
import com.dsw.iot.service.PersonTraceService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/PersonTrace")
public class PersonTraceRpc {

	@Autowired
	PersonTraceService personTraceService;

	/**
	 * 分页查询人员信息
	 *
	 * @param personRegisterRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<PersonTraceDo> queryPage(PersonTraceRequest personTraceRequest) {
		PageResult<PersonTraceDo> pageResult = personTraceService.queryPage(personTraceRequest);
		return pageResult;
	}
}
