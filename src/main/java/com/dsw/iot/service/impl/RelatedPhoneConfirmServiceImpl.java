package com.dsw.iot.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.BusinessBizConfig;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.RelatedPhoneConfirmDoMapperExt;
import com.dsw.iot.dto.RelatedPhoneConfirmRequest;
import com.dsw.iot.model.RelatedPhoneConfirmDo;
import com.dsw.iot.model.RelatedPhoneConfirmDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.RelatedPhoneConfirmService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.RelatedPhoneConfirmVo;

@Service
public class RelatedPhoneConfirmServiceImpl implements RelatedPhoneConfirmService {

	@Autowired
	RelatedPhoneConfirmDoMapperExt relatedPhoneConfirmDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	LogService logService;

	/**
	 * 分页查询家属通知审核列表
	 */
	@Override
	public PageResult<RelatedPhoneConfirmVo> queryPage(RelatedPhoneConfirmRequest relatedPhoneConfirmRequest) {
		// 定义返回集合
		PageResult<RelatedPhoneConfirmVo> pageResult = new PageResult<>();
		RelatedPhoneConfirmDoExample example = new RelatedPhoneConfirmDoExample();
		RelatedPhoneConfirmDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		//审核状态
		if (StringUtils.isNotBlank(relatedPhoneConfirmRequest.getStatus())) {
			criteria.andStatusEqualTo(relatedPhoneConfirmRequest.getStatus());
		}
		// 开始结束时间区间
		if (null != relatedPhoneConfirmRequest.getStartDate()) {
			criteria.andApplyTimeGreaterThanOrEqualTo(relatedPhoneConfirmRequest.getStartDate());
		}
		if (null != relatedPhoneConfirmRequest.getEndDate()) {
			criteria.andApplyTimeLessThanOrEqualTo(relatedPhoneConfirmRequest.getEndDate());
		}
		//申请人
		if (StringUtils.isNotBlank(relatedPhoneConfirmRequest.getApplyName())) {
			criteria.andApplyNameLike("%" + relatedPhoneConfirmRequest.getApplyName() + "%");
		}
		//申请原因
		if (StringUtils.isNotBlank(relatedPhoneConfirmRequest.getApplyReason())) {
			criteria.andApplyReasonLike("%" + relatedPhoneConfirmRequest.getApplyReason() + "%");
		}
		//人员注册id
		if (null!=relatedPhoneConfirmRequest.getRegisterId()) {
			criteria.andRegisterIdEqualTo(relatedPhoneConfirmRequest.getRegisterId());
		}
		//审批民警
		if (StringUtils.isNotBlank(relatedPhoneConfirmRequest.getAuditUser())) {
			criteria.andAuditUserLike("%" + relatedPhoneConfirmRequest.getAuditUser() + "%");
		}/*
		// 联系人
		if (StringUtils.isNotBlank(relatedPhoneConfirmRequest.getCallName())) {
			criteria.andCallNameLike("%" + relatedPhoneConfirmRequest.getCallName() + "%");
		}
		// 电话
		if (StringUtils.isNotBlank(relatedPhoneConfirmRequest.getTel())) {
			criteria.andTelEqualTo(relatedPhoneConfirmRequest.getTel());
		}*/
		// 排序字段
		if (StringUtils.isBlank(relatedPhoneConfirmRequest.getOrderByClause())) {
			example.setOrderByClause("create_time desc");
		} else {
			example.setOrderByClause(relatedPhoneConfirmRequest.getOrderByClause());
		}
		//分页
		int count = relatedPhoneConfirmDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(relatedPhoneConfirmRequest.getPage(), relatedPhoneConfirmRequest.getLimit(),
				count);
		example.setPageDto(pageDto);
		List<RelatedPhoneConfirmVo> list = relatedPhoneConfirmDoMapperExt.selectRelatedPhoneRecordVo(example);
		// 返回
		pageResult.setData(list);
		pageResult.setCount(count);
		return pageResult;
	}

	/**
	 * 通过主键查询
	 */
	@Override
	public RelatedPhoneConfirmDo getRelatedPhoneConfirm(Long id) {
		return relatedPhoneConfirmDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void saveRelatedPhoneConfirm(RelatedPhoneConfirmDo relatedPhoneConfirmDo,HttpServletRequest request) {
		if (null == relatedPhoneConfirmDo.getId()) {
			// add
			DomainUtil.setCommonValueForCreate(relatedPhoneConfirmDo, currentUserService.getPvgInfo());
			relatedPhoneConfirmDo.setApplyTime(new Date());
			//家属通知审核默认审核通过
			DomainUtil.setCommonValueForUpdate(relatedPhoneConfirmDo, currentUserService.getPvgInfo());
			relatedPhoneConfirmDo.setAuditTime(new Date());
			relatedPhoneConfirmDo.setStatus(BusinessBizConfig.AuditStatus.PASS);//默认审批通过
			relatedPhoneConfirmDoMapperExt.insertSelective(relatedPhoneConfirmDo);

			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.PERSON_RELATED.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
					currentUserService.getPvgInfo().getName() + "  新增了一条家属通知");
		} else {
			// edit
			//家属通知审核默认审核通过
			DomainUtil.setCommonValueForUpdate(relatedPhoneConfirmDo, currentUserService.getPvgInfo());
			relatedPhoneConfirmDo.setAuditTime(new Date());
			relatedPhoneConfirmDo.setStatus(BusinessBizConfig.AuditStatus.PASS);//默认审批通过
			relatedPhoneConfirmDoMapperExt.updateByPrimaryKeySelective(relatedPhoneConfirmDo);

			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.PERSON_RELATED.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
					currentUserService.getPvgInfo().getName() + "  编辑了一条家属通知");
		}
	}
}
