package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.iot.constant.BusinessBizConfig;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.RemindDoMapperExt;
import com.dsw.iot.dto.RemindRequest;
import com.dsw.iot.model.RemindDo;
import com.dsw.iot.model.RemindDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.RemindService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class RemindServiceImpl implements RemindService {

	@Autowired
	private RemindDoMapperExt remindDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	private LogService logService;

	/**
	 * 根据主键id查询待办信息
	 */
	@Override
	public RemindDo getRemind(Long id) {
		return remindDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 保存记录
	 */
	@Override
	public void saveRemind(RemindDo remindDo) {
		if (null == remindDo.getId()) {
			// add
			DomainUtil.setCommonValueForCreate(remindDo, currentUserService.getPvgInfo());
			remindDoMapperExt.insertSelective(remindDo);
		} else {
			// edit
			DomainUtil.setCommonValueForUpdate(remindDo, currentUserService.getPvgInfo());
			remindDoMapperExt.updateByPrimaryKeySelective(remindDo);
		}

	}

	/**
	 * 查询列表
	 */
	@Override
	public List<RemindDo> listRemind(Long roleId) {
		List<RemindDo> resultList = new ArrayList<RemindDo>();
		RemindDoExample example = new RemindDoExample();
		RemindDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		//查询未处理的代办业务
		criteria.andStatusEqualTo(BusinessBizConfig.RemindStatus.NOTPROCESSED);
		if (null != roleId){
			criteria.andRoleIdEqualTo(roleId);
		}
		//按时间倒序排列
		example.setOrderByClause("create_time desc");

		resultList = remindDoMapperExt.selectByExample(example);

		// 写日志
		logService.insertLog(CommConfig.LOG_MODULE.PERSON_REGISTER.getModule(), CommConfig.LOG_TYPE.QUERY.getType(),
				currentUserService.getPvgInfo().getName() + "  查询了自己的待办任务");
		return resultList;
	}

	/**
	 * 通过业务id和业务类型查询代办信息
	 */
	@Override
	public List<RemindDo> getRemindByTaskId(Long taskId, String taskBelong) {
		List<RemindDo> resultList = new ArrayList<RemindDo>();
		RemindDoExample example = new RemindDoExample();
		RemindDoExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(BusinessBizConfig.RemindStatus.NOTPROCESSED);
		if (null != taskId) {
			criteria.andTaskIdEqualTo(taskId);
		}
		criteria.andTaskBelongEqualTo(taskBelong);
		resultList = remindDoMapperExt.selectByExample(example);
		return resultList;
	}

	/**
	 * 多个roleid，查询待办
	 *
	 * @param roleIds
	 * @return
	 */
	@Override
	public List<RemindDo> listRemindByIds(String roleIds) {
		List<RemindDo> resList = new ArrayList<>();
		if (StringUtils.isNotBlank(roleIds)) {
			String[] ids = roleIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				resList.addAll(listRemind(Long.parseLong(ids[i])));
			}
			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.PERSON_REGISTER.getModule(), CommConfig.LOG_TYPE.QUERY.getType(),
					currentUserService.getPvgInfo().getName() + "  查询了自己的待办任务");
		}
		return resList;
	}

	/**
	 * 根据筛选条件查询信息分页列表
	 */
	@Override
	public PageResult<RemindDo> queryPage(RemindRequest remindRequest) {
		PageResult<RemindDo> pageResult = new PageResult<>();
		RemindDoExample example = new RemindDoExample();
		RemindDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());

		if (StringUtils.isNotBlank(remindRequest.getRoleIds())) {
			String[] ids = remindRequest.getRoleIds().split(",");
			ArrayList  idList = new ArrayList();
			for(int i = 0;i < ids.length ; i++){
				idList.add(ids[i]);
			}
			criteria.andRoleIdIn(idList);
		}
		//查询未处理的代办业务
		criteria.andStatusEqualTo(BusinessBizConfig.RemindStatus.NOTPROCESSED);
		//标题模糊查询
		if (StringUtils.isNotBlank(remindRequest.getTitle())) {
			criteria.andTitleLike("%" + remindRequest.getTitle() + "%");
		}
		//内容模糊查询
		if (StringUtils.isNotBlank(remindRequest.getContent())) {
			criteria.andContentLike("%" + remindRequest.getContent() + "%");
		}
		//创建人
		if (StringUtils.isNotBlank(remindRequest.getCreateUser())) {
			criteria.andCreateUserLike("%" + remindRequest.getCreateUser() + "%");
		}
		//时间
		if (null != remindRequest.getCreateStartTime()) {
			criteria.andCreateTimeGreaterThanOrEqualTo(remindRequest.getCreateStartTime());
		}
		if (null != remindRequest.getCreateEndTime()) {
			criteria.andCreateTimeLessThanOrEqualTo(remindRequest.getCreateEndTime());
		}
		//按时间倒序排列
		example.setOrderByClause("create_time desc");

		int count = remindDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(remindRequest.getPage(), remindRequest.getLimit(), count);
		example.setPageDto(pageDto);
		List<RemindDo> list = remindDoMapperExt.selectByExample(example);
		pageResult.setCount(count);
		pageResult.setData(list);
		return pageResult;
	}
}
