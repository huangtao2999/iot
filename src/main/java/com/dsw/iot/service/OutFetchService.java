package com.dsw.iot.service;

import com.dsw.iot.dto.OutClickConfirmRequest;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;

/**
 * 出所取物
 * @author zc
 *
 */
public interface OutFetchService {


	/**
	 * 储物柜控制打开
	 * lockerId 为储物柜主键id
	 */
	void getLockerOpen(Long lockerId);

	/**
	 * 签名确认 registerId 为注册人员主键id
	 */
	void signConfirm(Long registerId);

	/**
	 * 临时出办案区 registerId 为注册人员主键id cardId 卡芯片编码id
	 *
	 * @throws BizException
	 */
	ActionResult<String> outTempArea(Long registerId, String cardId, Long outId) throws BizException;

	/**
	 * 确认出办案区
	 * registerId 为注册人员主键id
	 *
	 */
	ActionResult<String> outArea(Long registerId, String cardId, Long outId) throws BizException;

	/**
	 * 页面点击签名确认
	 */
	ActionResult<String> clickConfirm(OutClickConfirmRequest clickConfirmRequest);
}
