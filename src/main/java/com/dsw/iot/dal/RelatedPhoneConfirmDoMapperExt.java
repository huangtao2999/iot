package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.RelatedPhoneConfirmDoMapper;
import com.dsw.iot.model.RelatedPhoneConfirmDoExample;
import com.dsw.iot.vo.RelatedPhoneConfirmVo;

public interface RelatedPhoneConfirmDoMapperExt extends RelatedPhoneConfirmDoMapper {
	/**
	 * 原来model仅新增 附件表 id
	 * 
	 * @param example
	 * @return
	 */
	List<RelatedPhoneConfirmVo> selectRelatedPhoneRecordVo(
			RelatedPhoneConfirmDoExample example);
}