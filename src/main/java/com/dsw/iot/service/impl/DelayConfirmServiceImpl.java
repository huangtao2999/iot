package com.dsw.iot.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.BusinessBizConfig;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.DelayConfirmDoMapperExt;
import com.dsw.iot.dal.PersonRegisterDoMapperExt;
import com.dsw.iot.dto.DelayConfirmRequest;
import com.dsw.iot.model.DelayConfirmDo;
import com.dsw.iot.model.DelayConfirmDoExample;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RemindDo;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.DelayConfirmService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.RemindService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.DelayConfirmVo;

@Service
public class DelayConfirmServiceImpl implements DelayConfirmService {

	@Autowired
	DelayConfirmDoMapperExt delayConfirmDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	LogService logService;
	@Autowired
	RemindService remindService;
	@Autowired
	PersonRegisterService personRegisterService;
	@Autowired
	PersonRegisterDoMapperExt personRegisterDoMapperExt;

	@Override
	public PageResult<DelayConfirmDo> queryPage(DelayConfirmRequest delayConfirmRequest) {
		PageResult<DelayConfirmDo> pageResult = new PageResult<>();
		DelayConfirmDoExample example = new DelayConfirmDoExample();
		DelayConfirmDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		//审核状态
		if (StringUtils.isNotBlank(delayConfirmRequest.getStatus())) {
			criteria.andStatusEqualTo(delayConfirmRequest.getStatus());
		}
		// 申请开始结束时间区间
		if (null != delayConfirmRequest.getApplyStartTime()) {
			criteria.andApplyTimeGreaterThanOrEqualTo(delayConfirmRequest.getApplyStartTime());
		}
		if (null != delayConfirmRequest.getApplyEndTime()) {
			criteria.andApplyTimeLessThanOrEqualTo(delayConfirmRequest.getApplyEndTime());
		}
		// 申请人姓名
		if (StringUtils.isNotBlank(delayConfirmRequest.getApplyName())) {
			criteria.andApplyNameLike("%" + delayConfirmRequest.getApplyName() + "%");
		}
		//申请原因
		if (StringUtils.isNotBlank(delayConfirmRequest.getApplyReason())) {
			criteria.andApplyReasonEqualTo(delayConfirmRequest.getApplyReason());
		}
		//审核人
		if (StringUtils.isNotBlank(delayConfirmRequest.getAuditUser())) {
			criteria.andAuditUserLike("%" + delayConfirmRequest.getAuditUser() + "%");
		}
		// 排序字段
		if (StringUtils.isBlank(delayConfirmRequest.getOrderByClause())) {
			example.setOrderByClause("create_time desc");
		} else {
			example.setOrderByClause(delayConfirmRequest.getOrderByClause());
		}
		int count = delayConfirmDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(delayConfirmRequest.getPage(), delayConfirmRequest.getLimit(), count);
		example.setPageDto(pageDto);
		List<DelayConfirmDo> list = delayConfirmDoMapperExt.selectByExample(example);
		pageResult.setCount(count);
		pageResult.setData(list);
		return pageResult;
	}

	@Override
	public DelayConfirmDo getDelayConfirm(Long id) {
		return delayConfirmDoMapperExt.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public DelayConfirmDo saveDelayConfirm(DelayConfirmVo delayConfirmVo,HttpServletRequest request) {
		
		System.out.println("延期置留id"+delayConfirmVo.getId());
		DelayConfirmDo delayConfirmDo = new DelayConfirmDo();
		//BeanUtils.copyProperties(delayConfirmDo, delayConfirmVo);
		
		delayConfirmDo.setApplyName(delayConfirmVo.getApplyName());
		delayConfirmDo.setApplyReason(delayConfirmVo.getApplyReason());
		delayConfirmDo.setApplyTime(delayConfirmVo.getApplyTime());
		delayConfirmDo.setAuditContent(delayConfirmVo.getAuditContent());
		delayConfirmDo.setAuditTime(delayConfirmVo.getAuditTime());
		delayConfirmDo.setAuditUser(delayConfirmVo.getAuditUser());
		delayConfirmDo.setCreateTime(delayConfirmVo.getCreateTime());
		delayConfirmDo.setCreateUser(delayConfirmVo.getCreateUser());
		delayConfirmDo.setDelayHour(delayConfirmVo.getDelayHour());
		delayConfirmDo.setDept(delayConfirmVo.getDept());
		delayConfirmDo.setId(delayConfirmVo.getId());
		delayConfirmDo.setIsDeleted(delayConfirmVo.getIsDeleted());
		delayConfirmDo.setRegisterId(delayConfirmVo.getRegisterId());
		delayConfirmDo.setRemark(delayConfirmVo.getRemark());
		delayConfirmDo.setStatus(delayConfirmVo.getStatus());
		delayConfirmDo.setUpdateTime(delayConfirmVo.getUpdateTime());
		delayConfirmDo.setUpdateUser(delayConfirmVo.getUpdateUser());
		
		RemindDo remindDo = new RemindDo();

		if (null == delayConfirmDo.getId()) {
			// 填写申请，新增新数据
			DomainUtil.setCommonValueForCreate(delayConfirmDo, currentUserService.getPvgInfo());
			delayConfirmDo.setApplyTime(new Date()); // 申请时间
			delayConfirmDo.setStatus(BusinessBizConfig.AuditStatus.WAIT); // 3-待审核；2-审核不通过；1-审核通过
			delayConfirmDoMapperExt.insertSelective(delayConfirmDo);

			//如果为新增，则新增一条待办数据
			remindDo.setRoleId(delayConfirmVo.getRoleId());
			//待办内容
			String remindContent = "";
			remindContent = delayConfirmDo.getApplyName() + "的" +	 BusinessBizConfig.RemindTaskBelong.getName(BusinessBizConfig.RemindTaskBelong.DELAY_CONFIRM) + "申请";	

			remindDo.setContent(remindContent);
			remindDo.setTitle(remindContent);
			remindDo.setPath(BusinessBizConfig.RemindTaskBelong.getPath(BusinessBizConfig.RemindTaskBelong.DELAY_CONFIRM));
			remindDo.setTaskId(Long.valueOf(delayConfirmDo.getId()));
			remindDo.setTaskBelong(BusinessBizConfig.RemindTaskBelong.DELAY_CONFIRM);
			remindDo.setStatus(BusinessBizConfig.RemindStatus.NOTPROCESSED);//0-未读；1-已读
			remindDo.setCreateUser(delayConfirmDo.getCreateUser());
			remindDo.setCreateTime(new Date());
			remindService.saveRemind(remindDo);
		} else {
			// 审核编辑，更新审核信息
			DomainUtil.setCommonValueForUpdate(delayConfirmDo, currentUserService.getPvgInfo());
			delayConfirmDo.setAuditTime(new Date());
			delayConfirmDoMapperExt.updateByPrimaryKeySelective(delayConfirmDo);
			//业务审核完后将待办数据更新为已处理
			List<RemindDo> resultList = remindService.getRemindByTaskId(delayConfirmDo.getId(), BusinessBizConfig.RemindTaskBelong.DELAY_CONFIRM);
			if (resultList.size() > 0) {
				for (int i = 0 ; i < resultList.size() ; i++) {
					remindDo = (RemindDo)resultList.get(i);
					remindDo.setStatus(BusinessBizConfig.RemindStatus.PROCESSED);
					remindService.saveRemind(remindDo);
				}
			}

			/**
			 * 延期置留审核通过后，更新人员延期置留时限和是否延期关押信息
			 */
			if (BusinessBizConfig.AuditStatus.PASS.equals(delayConfirmDo.getStatus())) {
				PersonRegisterDo personRegisterDo = new PersonRegisterDo();
				personRegisterDo = personRegisterService.getPersonRegister(delayConfirmDo.getRegisterId());
				personRegisterDo.setDelayHour(delayConfirmDo.getDelayHour());
				//延期留置时限超过8小时，人员信息的是否延期留置改为是，否则为否
				if (StringUtils.isNotBlank(delayConfirmDo.getDelayHour()) && !BusinessBizConfig.DelayHour.EIGHT.equals(delayConfirmDo.getDelayHour())) {
					personRegisterDo.setIsDelay(BusinessBizConfig.YesOrNo.YES);
				}else{
					personRegisterDo.setIsDelay(BusinessBizConfig.YesOrNo.NO);
				}
				personRegisterDoMapperExt.updateByPrimaryKey(personRegisterDo);
			}
		}
		return delayConfirmDo;
	}

	@Override
	@Transactional
	public void checkDelayConfirm(DelayConfirmDo delayConfirmDo) {

		if (null != delayConfirmDo.getId()) {
			// 审核编辑，更新审核信息
			DomainUtil.setCommonValueForUpdate(delayConfirmDo, currentUserService.getPvgInfo());
			delayConfirmDo.setAuditTime(new Date());
			delayConfirmDoMapperExt.updateByPrimaryKeySelective(delayConfirmDo);
			
			RemindDo remindDo = new RemindDo();
			//业务审核完后将待办数据更新为已处理
			List<RemindDo> resultList = remindService.getRemindByTaskId(delayConfirmDo.getId(), BusinessBizConfig.RemindTaskBelong.DELAY_CONFIRM);
			if (resultList.size() > 0) {
				for (int i = 0 ; i < resultList.size() ; i++) {
					remindDo = (RemindDo)resultList.get(i);
					DomainUtil.setCommonValueForUpdate(remindDo, currentUserService.getPvgInfo());
					remindDo.setStatus(BusinessBizConfig.RemindStatus.PROCESSED);
					remindService.saveRemind(remindDo);
				}
			}
		}
	}
}
