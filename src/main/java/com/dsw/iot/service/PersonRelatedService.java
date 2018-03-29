package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.PersonRelatedRequest;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.util.PageResult;

public interface PersonRelatedService {
	/**
	 * 分页查询
	 *
	 * @param param
	 * @return
	 */
	public PageResult<PersonRelatedDo> queryPage(PersonRelatedRequest personRelatedRequest);

	/**
	 * 查单条记录
	 *
	 * @param id
	 * @return
	 */
	PersonRelatedDo getRelated(Long id);

	/**
	 * 新增/编辑陪同人
	 *
	 * @param record
	 * @param loginUserDo
	 * @return
	 */
	PersonRelatedDo saveRelated(PersonRelatedDo personRelatedDo);

	/**
	 * 删除角色
	 *
	 * @param param
	 * @return
	 */
	void removeRelated(PersonRelatedDo personRelatedDo);

	/**
	 * 通过人员id，查询所有陪同人信息
	 *
	 * @param id
	 * @return
	 */
	List<PersonRelatedDo> getRelatedListByRid(Long registerId);
}
