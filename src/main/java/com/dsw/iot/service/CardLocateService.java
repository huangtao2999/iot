package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.CardLocateRequest;
import com.dsw.iot.model.CardLocateDo;
import com.dsw.iot.util.PageResult;

public interface CardLocateService {

	/**
	 * 新增人员定位信息
	 *
	 * @param cardLocateDo
	 */
	void saveLocate(CardLocateDo cardLocateDo);

	/**
	 * 人员离办案区的时候，更新定位信息为历史记录
	 *
	 * @param registerId
	 *            人员表主键
	 * @param cardId
	 *            卡芯片编码id
	 */
	void updateToHistory(Long registerId, String cardId);

	/**
	 * 根据参数查找相关定位记录，用于展示轨迹
	 *
	 * @param registerId
	 * @param cardId
	 * @return
	 */
	List<CardLocateDo> getLiveLocateList(Long registerId, String cardId);

	/**
	 * 分页查询
	 * 
	 * @param cardLocateRequest
	 * @return
	 */
	PageResult<CardLocateDo> queryPage(CardLocateRequest cardLocateRequest);
}
