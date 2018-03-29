package com.dsw.iot.service;

import javax.servlet.http.HttpServletRequest;

import com.dsw.iot.dto.DelayConfirmRequest;
import com.dsw.iot.model.DelayConfirmDo;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.DelayConfirmVo;

public interface DelayConfirmService {
	/**
	 * 分页查询延期留置审核
	 *
	 * @param OutConfirmRequest
	 * @return
	 */
	PageResult<DelayConfirmDo> queryPage(DelayConfirmRequest delayConfirmRequest);

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id
	 * @return
	 */
	DelayConfirmDo getDelayConfirm(Long id);

	/**
	 * 保存记录
	 *
	 * @param OutConfirmVo
	 */
	DelayConfirmDo saveDelayConfirm(DelayConfirmVo delayConfirmVo,HttpServletRequest request);
	
	/**
	 * 审核
	 *
	 * @param OutConfirmDo
	 */
	void checkDelayConfirm(DelayConfirmDo delayConfirmDo);
}
