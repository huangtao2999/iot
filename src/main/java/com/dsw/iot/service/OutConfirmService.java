package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.OutConfirmRequest;
import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

public interface OutConfirmService {
	/**
	 * 分页查询出所审核
	 *
	 * @param OutConfirmRequest
	 * @return
	 */
	PageResult<OutConfirmDo> queryPage(OutConfirmRequest outConfirmRequest);

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id
	 * @return
	 */
	OutConfirmDo getOutConfirm(Long id);

	/**
	 * 保存记录
	 *
	 * @param OutConfirmVo
	 * @throws BizException
	 */
	OutConfirmDo saveOutConfirm(OutConfirmRequest outConfirmRequest) throws BizException;

	/**
	 * 通过人员id查找审核记录
	 *
	 * @param id
	 * @return
	 */
	List<OutConfirmDo> getOutConfirmByRid(Long id, String status);

	/**
	 * 通过人员id，审批状态，是否历史查找审核记录
	 *
	 * @param id
	 * @return
	 */
	List<OutConfirmDo> getOutConfirmByRid(Long registerId, String status, String isHistory);

	/**
	 * 更新审批单为历史记录
	 *
	 * @param id
	 *            申请单id
	 * @return
	 */
	OutConfirmDo updateConfirmToHistory(Long id);

	/**
	 * 更新审批单返回时间
	 *
	 * @param id
	 *            申请单id
	 * @return
	 */
	OutConfirmDo updateConfirmBackTime(Long id);
}
