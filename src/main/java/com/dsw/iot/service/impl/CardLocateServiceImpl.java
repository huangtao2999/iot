package com.dsw.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.CardLocateDoMapperExt;
import com.dsw.iot.dto.CardLocateRequest;
import com.dsw.iot.model.CardLocateDo;
import com.dsw.iot.model.CardLocateDoExample;
import com.dsw.iot.service.CardLocateService;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageResult;

@Service
public class CardLocateServiceImpl implements CardLocateService {
	@Autowired
	CardLocateDoMapperExt cardLocateDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	LogService logService;

	/**
	 * 新增人员定位信息
	 *
	 * @param cardLocateDo
	 */
	@Override
	@Transactional
	public void saveLocate(CardLocateDo cardLocateDo) {
		DomainUtil.setCommonValueForCreate(cardLocateDo, currentUserService.getPvgInfo());
		cardLocateDoMapperExt.insertSelective(cardLocateDo);
	}

	/**
	 * 人员离办案区的时候，更新定位信息为历史记录
	 *
	 * @param registerId
	 *            人员表主键
	 * @param cardId
	 *            卡芯片编码id
	 */
	@Override
	@Transactional
	public void updateToHistory(Long registerId, String cardId) {
		CardLocateDo cardLocateDo = new CardLocateDo();
		cardLocateDo.setIsHistory("1");// 更新为历史记录
		CardLocateDoExample example = new CardLocateDoExample();
		CardLocateDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andRegisterIdEqualTo(registerId);
		criteria.andCardIdEqualTo(cardId);
		cardLocateDoMapperExt.updateByExampleSelective(cardLocateDo, example);
	}

	/**
	 * 根据参数查找相关定位记录，用于展示轨迹
	 *
	 * @param registerId
	 * @param cardId
	 * @return
	 */
	@Override
	public List<CardLocateDo> getLiveLocateList(Long registerId, String cardId) {
		CardLocateDoExample example = new CardLocateDoExample();
		CardLocateDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andRegisterIdEqualTo(registerId);
		criteria.andCardIdEqualTo(cardId);
		List<CardLocateDo> resultList = cardLocateDoMapperExt.selectByExample(example);
		return resultList;
	}

	/**
	 * 分页查询
	 *
	 * @param cardLocateRequest
	 * @return
	 */
	@Override
	public PageResult<CardLocateDo> queryPage(CardLocateRequest cardLocateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
