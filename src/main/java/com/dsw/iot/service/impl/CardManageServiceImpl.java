package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.CardManageDoMapperExt;
import com.dsw.iot.dto.CardManageRequest;
import com.dsw.iot.model.CardManageDo;
import com.dsw.iot.model.CardManageDoExample;
import com.dsw.iot.service.CardManageService;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class CardManageServiceImpl implements CardManageService {
    @Autowired(required = false)
    CardManageDoMapperExt cardManageDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    LogService logService;

    /**
     * 卡芯片管理分页查询
     */
    @Override
    public PageResult<CardManageDo> queryPage(CardManageRequest param) {
        // 分页返回集合
        PageResult<CardManageDo> pageResult = new PageResult<>();
        // 查询条件的容器
        CardManageDoExample example = new CardManageDoExample();
        // 新建查询条件
        CardManageDoExample.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 排序
        if (StringUtils.isBlank(param.getOrderByClause())) {
            example.setOrderByClause("create_time desc");
        } else {
            example.setOrderByClause(param.getOrderByClause());
        }

        // 分页
        int count = cardManageDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(param.getPage(), param.getLimit(), count);
        example.setPageDto(pageDto);
        List<CardManageDo> list = cardManageDoMapperExt.selectByExample(example);
        // 返回集合
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 新增/编辑 卡芯片信息
     *
     * @throws BizException
     */
    @Override
    @Transactional
    public void saveCard(CardManageDo cardManageDo) throws BizException {
        if (null == cardManageDo.getId()) {
            // 新增
            // 判断芯片是否唯一
            CardManageDoExample example = new CardManageDoExample();
            CardManageDoExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
            criteria.andCardIdEqualTo(cardManageDo.getCardId());
            List<CardManageDo> cardIdList = cardManageDoMapperExt.selectByExample(example);
            if (CollectionUtils.isNotEmpty(cardIdList)) {
                throw new BizException("该卡已注册，请核实");
            }
            // 执行插入操作
            DomainUtil.setCommonValueForCreate(cardManageDo, currentUserService.getPvgInfo());
            cardManageDoMapperExt.insertSelective(cardManageDo);
        } else {
            // 编辑
            DomainUtil.setCommonValueForUpdate(cardManageDo, currentUserService.getPvgInfo());
            cardManageDoMapperExt.updateByPrimaryKeySelective(cardManageDo);
        }
    }

    /**
     * 删除芯片信息
     *
     * @param ids
     */
    @Override
    public void deleteCard(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idsArray = ids.split(",");
            for (String id : idsArray) {
                if (StringUtils.isNotBlank(id)) {
                    CardManageDo cardManageDo = new CardManageDo();
                    cardManageDo.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForDelete(cardManageDo, currentUserService.getPvgInfo());
                    cardManageDoMapperExt.deleteByPrimaryKey(cardManageDo);
                }
            }
        }
    }

    /**
     * 激活芯片
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public void activateCard(Long id) throws BizException {
        CardManageDo cardManageDo = new CardManageDo();
        if (null != id) {
//			cardManageDo.setIsActivate("1");// 激活
//			CardManageDoExample example = new CardManageDoExample();
//			CardManageDoExample.Criteria criteria = example.createCriteria();
//			criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
//			criteria.andCardIdEqualTo(cardId);
            cardManageDo.setId(id);
            cardManageDo.setIsActivate("1");// 激活
            DomainUtil.setCommonValueForUpdate(cardManageDo, currentUserService.getPvgInfo());
            int i = cardManageDoMapperExt.updateByPrimaryKeySelective(cardManageDo);
            if (i == 0) {
                throw new BizException("芯片激活失败，请检查是否芯片录入信息");
            }
        }
    }

    /**
     * 禁用芯片
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public void deactivateCard(Long id) throws BizException {
        CardManageDo cardManageDo = new CardManageDo();
        if (null != id) {
            cardManageDo.setId(id);
            cardManageDo.setIsActivate("0");// 禁用
            DomainUtil.setCommonValueForUpdate(cardManageDo, currentUserService.getPvgInfo());
            int i = cardManageDoMapperExt.updateByPrimaryKeySelective(cardManageDo);
            if (i == 0) {
                throw new BizException("芯片停用失败，请重试");
            }
        }
    }


    @Override
	public CardManageDo getCarManageByCarNo(String cardId) {
		if (StringUtils.isNotBlank(cardId)) {
            CardManageDoExample example = new CardManageDoExample();
            CardManageDoExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
			criteria.andCardIdEqualTo(cardId);
            List<CardManageDo> list = cardManageDoMapperExt.selectByExample(example);
            if (list != null && list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }


}
