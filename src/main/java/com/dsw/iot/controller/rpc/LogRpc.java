package com.dsw.iot.controller.rpc;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.LogRequest;
import com.dsw.iot.model.LogDo;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/Log")
public class LogRpc {

	@Autowired
	LogService logService;

	/**
	 * 分页查询
	 *
	 * @param logRequest
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/queryPage")
	public PageResult<LogDo> queryPage(LogRequest logRequest) throws ParseException {
		return logService.queryPage(logRequest);
	}

}
