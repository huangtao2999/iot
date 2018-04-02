package com.dsw.iot.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.BusinessBizConfig;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.OutConfirmDoMapperExt;
import com.dsw.iot.dto.OutConfirmRequest;
import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.model.OutConfirmDoExample;
import com.dsw.iot.model.RemindDo;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.GoodsRegisterService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.OutConfirmService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.RemindService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class OutConfirmServiceImpl implements OutConfirmService {

	@Autowired
	OutConfirmDoMapperExt outConfirmDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	LogService logService;
	@Autowired
	RemindService remindService;
	@Autowired
	GoodsRegisterService goodsRegisterService;
	@Autowired
	PersonRegisterService personRegisterService;

	/**
	 * 分页查询出所审核
	 */
	@Override
	public PageResult<OutConfirmDo> queryPage(OutConfirmRequest outConfirmRequest) {
		PageResult<OutConfirmDo> pageResult = new PageResult<>();
		OutConfirmDoExample example = new OutConfirmDoExample();
		OutConfirmDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());

		//审核状态
		if(StringUtils.isNotBlank(outConfirmRequest.getStatus())){
			criteria.andStatusEqualTo(outConfirmRequest.getStatus());
		}
		//申请开始结束时间区间
		if (null != outConfirmRequest.getApplyStartTime()) {
			criteria.andApplyTimeGreaterThanOrEqualTo(outConfirmRequest.getApplyStartTime());
		}
		if (null != outConfirmRequest.getApplyEndTime()) {
			criteria.andApplyTimeLessThanOrEqualTo(outConfirmRequest.getApplyEndTime());
		}
		// 申请人姓名
		if (StringUtils.isNotBlank(outConfirmRequest.getApplyName())) {
			criteria.andApplyNameLike("%" + outConfirmRequest.getApplyName() + "%");
		}
		//申请原因
		if(StringUtils.isNotBlank(outConfirmRequest.getApplyReason())){
			criteria.andApplyReasonEqualTo(outConfirmRequest.getApplyReason());
		}
		//审批民警
		if(StringUtils.isNotBlank(outConfirmRequest.getAuditUser())){
			criteria.andAuditUserLike("%" + outConfirmRequest.getAuditUser() + "%");
		}
		// 排序字段
		if (StringUtils.isBlank(outConfirmRequest.getOrderByClause())) {
			example.setOrderByClause("create_time desc");
		} else {
			example.setOrderByClause(outConfirmRequest.getOrderByClause());
		}
		int count = outConfirmDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(outConfirmRequest.getPage(), outConfirmRequest.getLimit(), count);
		example.setPageDto(pageDto);
		List<OutConfirmDo> list = outConfirmDoMapperExt.selectByExample(example);
		pageResult.setCount(count);
		pageResult.setData(list);
		return pageResult;
	}

	/**
	 * 通过主键查询单条记录
	 */
	@Override
	public OutConfirmDo getOutConfirm(Long id) {
		return outConfirmDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 保存记录
	 *
	 * @throws BizException
	 */
	@Override
	@Transactional
	public OutConfirmDo saveOutConfirm(OutConfirmRequest outConfirmRequest) throws BizException {
		OutConfirmDo outConfirmDo = new OutConfirmDo();
		BeanUtils.copyProperties(outConfirmRequest, outConfirmDo);

		RemindDo remindDo = new RemindDo();

		if (null == outConfirmRequest.getId()) {
			outConfirmDo.setIsHistory("0");// 非历史

			// 填写申请，新增新数据
			DomainUtil.setCommonValueForCreate(outConfirmDo, currentUserService.getPvgInfo());
			outConfirmDo.setApplyTime(new Date()); // 申请时间

			outConfirmDo.setStatus(BusinessBizConfig.AuditStatus.WAIT); // 3-待审核；2-审核不通过；1-审核通过
			outConfirmDoMapperExt.insertSelective(outConfirmDo);

			//如果为新增，则新增一条待办数据
			remindDo.setRoleId(outConfirmRequest.getRoleId());

			//待办内容
			String remindContent = "";
			remindContent = outConfirmDo.getApplyName() + "的"
					+ BusinessBizConfig.OutConfirmType.getName(outConfirmDo.getOutType()) + "申请";

			remindDo.setContent(remindContent);
			remindDo.setTitle(remindContent);
			remindDo.setPath(BusinessBizConfig.RemindTaskBelong.getPath(outConfirmDo.getOutType()));
			remindDo.setTaskId(Long.valueOf(outConfirmDo.getId()));
			remindDo.setTaskBelong(BusinessBizConfig.RemindTaskBelong.OUT_CONFIRM);
			remindDo.setStatus(BusinessBizConfig.RemindStatus.NOTPROCESSED);//0-未读；1-已读
			remindDo.setCreateUser(outConfirmDo.getCreateUser());
			remindDo.setCreateTime(new Date());
			remindService.saveRemind(remindDo);

			//如果为新增，批量更新物品状态为扣押
			String goodIds = outConfirmRequest.getHoldGoods();
			goodsRegisterService.updateGoodsStatus(goodIds,BusinessBizConfig.GoodStatus.SEIZED,outConfirmDo.getId());

			// 更新人员状态为申请出办案区。1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
			personRegisterService.updatePersonStatus(outConfirmRequest.getRegisterId(), "2");

			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.OUT_CONFIRM.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
					currentUserService.getPvgInfo().getName() + "  新增了一条出所申请，申请人员：" + outConfirmDo.getApplyName());
		} else {
			String status = outConfirmDo.getStatus();
			if (!status.equals(BusinessBizConfig.AuditStatus.PASS)) {
				outConfirmDo.setIsHistory("1");// 不通过就置为历史
			}

			// 审核编辑，更新审核信息
			DomainUtil.setCommonValueForUpdate(outConfirmDo, currentUserService.getPvgInfo());
			outConfirmDo.setAuditTime(new Date());
			outConfirmDoMapperExt.updateByPrimaryKeySelective(outConfirmDo);

			//业务审核完后将待办数据更新为已处理
			List<RemindDo> resultList = remindService.getRemindByTaskId(outConfirmDo.getId(), BusinessBizConfig.RemindTaskBelong.OUT_CONFIRM);
			if (resultList.size() > 0) {
				for (int i = 0 ; i < resultList.size() ; i++) {
					remindDo = resultList.get(i);
					remindDo.setStatus(BusinessBizConfig.RemindStatus.PROCESSED);
					remindService.saveRemind(remindDo);
				}
			}

			if (status.equals(BusinessBizConfig.AuditStatus.PASS)) {
				// 审核通过
				// 更新人员状态为待出办案区。1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
				personRegisterService.updatePersonStatus(outConfirmRequest.getRegisterId(), "3");
			} else {
				// 审核不通过
				// 更新人员状态为在办案区。1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
				personRegisterService.updatePersonStatus(outConfirmRequest.getRegisterId(), "1");
				// 更新扣押物品为已登记
				// 0-待绑定（登记了物品后还没有绑定注册表）；1-已登记（入办案区登记所有物品）；2-已扣押（出办案区选择物品扣押）；3-已归还）
				String goodIds = outConfirmRequest.getHoldGoods();
				goodsRegisterService.updateGoodsStatus(goodIds, BusinessBizConfig.GoodStatus.REGISTERED,
						outConfirmDo.getId());
			}

			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.OUT_CONFIRM.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
					currentUserService.getPvgInfo().getName() + "  审核了一条出所申请");
		}
		return outConfirmDo;
	}

	/**
	 * 通过人员id查询记录
	 */
	@Override
	public List<OutConfirmDo> getOutConfirmByRid(Long id, String status) {
		OutConfirmDoExample example = new OutConfirmDoExample();
		OutConfirmDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());

		criteria.andRegisterIdEqualTo(id);
		if (StringUtils.isNotBlank(status)) {
			criteria.andStatusEqualTo(status);
		}

		example.setOrderByClause("create_time desc");
		return outConfirmDoMapperExt.selectByExample(example);
	}

	/**
	 * 通过人员id，审批状态，是否历史查找审核记录
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<OutConfirmDo> getOutConfirmByRid(Long registerId, String status, String isHistory) {
		OutConfirmDoExample example = new OutConfirmDoExample();
		OutConfirmDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());

		criteria.andRegisterIdEqualTo(registerId);
		criteria.andStatusEqualTo(status);
		criteria.andIsHistoryEqualTo(isHistory);

		example.setOrderByClause("create_time desc");
		return outConfirmDoMapperExt.selectByExample(example);
	}

	/**
	 * 更新审批单为历史记录
	 */
	@Override
	public OutConfirmDo updateConfirmToHistory(Long id) {
		if (null != id) {
			OutConfirmDo outConfirmDo = outConfirmDoMapperExt.selectByPrimaryKey(id);
			outConfirmDo.setIsHistory("1");
			outConfirmDo.setOutTime(new Date());
			outConfirmDoMapperExt.updateByPrimaryKey(outConfirmDo);
			return outConfirmDo;
		}
		return null;
	}

	/**
	 * 更新审批单返回时间为当前时间
	 */
	@Override
	public OutConfirmDo updateConfirmBackTime(Long id) {
		if (null != id) {
			OutConfirmDo outConfirmDo = outConfirmDoMapperExt.selectByPrimaryKey(id);
			outConfirmDo.setBackTime(new Date());
			outConfirmDoMapperExt.updateByPrimaryKey(outConfirmDo);
			return outConfirmDo;
		}
		return null;
	}

}
