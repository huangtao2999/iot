package com.dsw.iot.dal;

import com.dsw.iot.dal.base.AttachDoMapper;
import com.dsw.iot.model.AttachDo;

public interface AttachDoMapperExt extends AttachDoMapper {
	/**
	 * 编辑时，把旧记录的状态设置为暂存，清除所属业务
	 *
	 * @param taskId
	 * @param taskBelong
	 */
	int updateToUnuse(AttachDo attachDo);
}