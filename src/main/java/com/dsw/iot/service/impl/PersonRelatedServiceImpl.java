package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.PersonRelatedDoMapperExt;
import com.dsw.iot.dto.PersonRelatedRequest;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.model.PersonRelatedDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.PersonRelatedService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class PersonRelatedServiceImpl implements PersonRelatedService {

	@Autowired
	PersonRelatedDoMapperExt personRelatedDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	LogService logService;

	/**
	 * 分页查询
	 */
	@Override
	public PageResult<PersonRelatedDo> queryPage(PersonRelatedRequest personRelatedRequest) {
		// 定义分页返回集合
		PageResult<PersonRelatedDo> pageResult = new PageResult<>();
		// 定义查询条件容器
		PersonRelatedDoExample example = new PersonRelatedDoExample();
		// 添加条件
		PersonRelatedDoExample.Criteria criteria = example.createCriteria();
		// 默认条件添加is_deleted="N"
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 姓名
		if (StringUtils.isNotEmpty(personRelatedRequest.getName())) {
			criteria.andNameLike("%" + personRelatedRequest.getName() + "%");
		}
		// 排序字段
		if (StringUtils.isBlank(personRelatedRequest.getOrderByClause())) {
			example.setOrderByClause("create_time desc");
		} else {
			example.setOrderByClause(personRelatedRequest.getOrderByClause());
		}
		// 分页查询总数
		int count = personRelatedDoMapperExt.countByExample(example);
		// 创建分页
		PageDto pageDto = new PageDto(personRelatedRequest.getPage(), personRelatedRequest.getLimit(), count);
		example.setPageDto(pageDto);
		// 查询数据集合
		List<PersonRelatedDo> list = personRelatedDoMapperExt.selectByExample(example);
		// 返回list和分页
		pageResult.setData(list);
		pageResult.setCount(count);
		return pageResult;
	}

	/**
	 * 单条记录
	 */
	@Override
	public PersonRelatedDo getRelated(Long id) {
		return personRelatedDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 新增编辑陪同人信息
	 *
	 * @param request
	 * @param record
	 */
	@Override
	@Transactional
	public PersonRelatedDo saveRelated(PersonRelatedDo personRelatedDo) {
		if (personRelatedDo.getId() == null) {
			// 新增
			DomainUtil.setCommonValueForCreate(personRelatedDo, currentUserService.getPvgInfo());
			personRelatedDoMapperExt.insertSelective(personRelatedDo);
		} else {
			// 编辑
			DomainUtil.setCommonValueForUpdate(personRelatedDo, currentUserService.getPvgInfo());
			personRelatedDoMapperExt.updateByPrimaryKeySelective(personRelatedDo);
		}
		return personRelatedDoMapperExt.selectByPrimaryKey(personRelatedDo.getId());
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void removeRelated(PersonRelatedDo personRelatedDo) {
		// 先删除该角色下的菜单，再删除角色
		if (personRelatedDo.getId() != null) {
			DomainUtil.setCommonValueForDelete(personRelatedDo, currentUserService.getPvgInfo());
			personRelatedDoMapperExt.deleteByPrimaryKey(personRelatedDo);
		}
	}

	/**
	 * 通过人员id，查询所有陪同人信息
	 */
	@Override
	public List<PersonRelatedDo> getRelatedListByRid(Long registerId) {
		// 定义查询条件容器
		PersonRelatedDoExample example = new PersonRelatedDoExample();
		// 添加条件
		PersonRelatedDoExample.Criteria criteria = example.createCriteria();
		// 默认条件添加is_deleted="N"
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andRegisterIdEqualTo(registerId);
		return personRelatedDoMapperExt.selectByExample(example);
	}
}
