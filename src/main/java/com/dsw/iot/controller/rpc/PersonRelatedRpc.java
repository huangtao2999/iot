package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.PersonRelatedRequest;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.service.PersonRelatedService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/PersonRelated")
public class PersonRelatedRpc {

	@Autowired
	PersonRelatedService personRelatedService;

	/**
	 * 分页查询
	 *
	 * @param personRelatedRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<PersonRelatedDo> queryPage(PersonRelatedRequest personRelatedRequest) {
		return personRelatedService.queryPage(personRelatedRequest);
	}

	/**
	 * 保存陪同人
	 * 
	 * @param personRelatedDo
	 * @return
	 */
	@RequestMapping(value = "/saveRelated", method = RequestMethod.POST)
	public PersonRelatedDo saveRelated(PersonRelatedDo personRelatedDo) {
		return personRelatedService.saveRelated(personRelatedDo);
	}
}
