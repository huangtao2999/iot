package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.CatchInfoRequest;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.service.CatchInfoService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/CatchInfo")
public class CatchInfoRpc {

	@Autowired
	CatchInfoService catchInfoService;

	/**
	 * 分页查询
	 *
	 * @param personRelatedRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<CatchInfoDo> queryPage(CatchInfoRequest catchInfoRequest) {
		return catchInfoService.queryPage(catchInfoRequest);
	}

	/**
	 * 保存陪同人
	 *
	 * @param personRelatedDo
	 * @return
	 */
	@RequestMapping(value = "/saveRelated", method = RequestMethod.POST)
	public CatchInfoDo saveRelated(CatchInfoDo catchInfoDo) {
		return catchInfoService.saveCatchInfo(catchInfoDo);
	}
}
