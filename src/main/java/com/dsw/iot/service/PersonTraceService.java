package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.PersonTraceRequest;
import com.dsw.iot.model.PersonTraceDo;
import com.dsw.iot.util.PageResult;

public interface PersonTraceService {
	/**
	 * 分页查询
	 * @param param
	 * @return
	 */
	PageResult<PersonTraceDo> queryPage(PersonTraceRequest param);

	/**
	 * 查询list集合
	 *
	 * @param param
	 * @return
	 */
	List<PersonTraceDo> queryList(Long registerId);
}
