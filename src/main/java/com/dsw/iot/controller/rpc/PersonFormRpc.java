package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.PersonFormRequest;
import com.dsw.iot.service.PersonFormService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.PersonFormVo;

@RestController
@RequestMapping("/PersonForm")
public class PersonFormRpc {

	@Autowired
	PersonFormService personFormService;

	/**
	 * 保存人员登记表信息
	 *
	 * @param personRegisterRequest
	 */
	@RequestMapping("/savePersonForm")
	public ActionResult<String> savePersonForm(PersonFormRequest personFormRequest) throws BizException {
		return personFormService.savePersonForm(personFormRequest);
	}

	/**
	 * 保存人员登记表信息
	 *
	 * @param personRegisterRequest
	 */
	@RequestMapping("/getPersonForm")
	public PersonFormVo getPersonForm(Long registerId) throws BizException {
		return personFormService.selectByRegisterId(registerId);
	}

}
