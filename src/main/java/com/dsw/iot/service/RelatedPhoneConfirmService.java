package com.dsw.iot.service;

import javax.servlet.http.HttpServletRequest;

import com.dsw.iot.dto.RelatedPhoneConfirmRequest;
import com.dsw.iot.model.RelatedPhoneConfirmDo;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.RelatedPhoneConfirmVo;

public interface RelatedPhoneConfirmService {

	/**
	 * 分页查询亲情电话列表
	 *
	 * @param relatedPhoneConfirmRequest
	 * @return
	 */
	PageResult<RelatedPhoneConfirmVo> queryPage(RelatedPhoneConfirmRequest relatedPhoneConfirmRequest);

	/**
	 * 通过主键查询
	 *
	 * @param id
	 * @return
	 */
	RelatedPhoneConfirmDo getRelatedPhoneConfirm(Long id);

	/**
	 * 保存记录
	 * 
	 * @param relatedPhoneConfirmDo
	 */
	void saveRelatedPhoneConfirm(RelatedPhoneConfirmDo relatedPhoneConfirmDo,HttpServletRequest request);
}
