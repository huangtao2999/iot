package com.dsw.iot.service;

import com.dsw.iot.dto.PersonFormRequest;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.PersonFormVo;

public interface PersonFormService {

	/**
	 * 通过人员id查找人员登记表
	 *
	 * @param registerId
	 * @return
	 */
	PersonFormVo selectByRegisterId(Long registerId);

	/**
	 * 保存人员登记表
	 *
	 * @param personFormRequest
	 * @return
	 * @throws BizException
	 */
	ActionResult<String> savePersonForm(PersonFormRequest personFormRequest) throws BizException;
}
