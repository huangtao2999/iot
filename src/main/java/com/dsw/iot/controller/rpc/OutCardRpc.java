package com.dsw.iot.controller.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.OutClickConfirmRequest;
import com.dsw.iot.model.GoodsRegisterDo;
import com.dsw.iot.service.GoodsRegisterService;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.service.OutFetchService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.PersonRegisterVo;

/**
 * 出所刷胸牌
 *
 * @author zc
 *
 */
@RestController
@RequestMapping("/OutCard")
public class OutCardRpc {

	@Autowired
	PersonRegisterService personRegisterService;

	/**
	 * 通过胸牌编号获取人员信息
	 *
	 * @param braceletNo
	 *            手环编号
	 * @return
	 */
	@RequestMapping("/getPersonRegister")
	public PersonRegisterVo getFetchDetail(String braceletNo) {
		return personRegisterService.getPersonRegister(braceletNo);
	}
}
