package com.dsw.iot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.service.OutFetchService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.PersonRegisterVo;

public class TestOutFetch extends BaseTest {

	PersonRegisterVo personRegisterVo;
	PersonRegisterDo personRegisterDo;

	@Autowired
	OutFetchService outFetchService;
	@Autowired
	PersonRegisterService personRegisterService;


	@Test
	public void register() throws BizException {
		getBraceletNo();
		receiverSign();

	}

	    /**
	     * 查询手环编号
	     */
	private void getBraceletNo() {
		personRegisterVo = personRegisterService.getPersonRegister("110100");
		if (personRegisterVo != null) {
			System.out.println("手环编号=" + personRegisterVo.getPersonInfo().getBraceletNo());
		} else {
			System.out.println("手环为空");
		}
	}

	    /**
	     * 更新人员出所签字信息
	     */
	private void receiverSign() {
		personRegisterService.updatePersonRegisterRsign(Long.parseLong("18"), "3232");
		personRegisterDo = personRegisterService.getPersonRegister(Long.parseLong("3"));
		if (personRegisterDo != null) {
			System.out.println("rsign=" + personRegisterDo.getReceiverSign());

		}
	}
}
