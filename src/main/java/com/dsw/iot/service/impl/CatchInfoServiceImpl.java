package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.CatchInfoDoMapperExt;
import com.dsw.iot.dto.CatchInfoRequest;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.model.CatchInfoDoExample;
import com.dsw.iot.service.CatchInfoService;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class CatchInfoServiceImpl implements CatchInfoService {
	@Autowired
	CatchInfoDoMapperExt catchInfoDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	LogService logService;

	/**
	 * 分页查询
	 */
	@Override
	public PageResult<CatchInfoDo> queryPage(CatchInfoRequest catchInfoRequest) {
		// 定义分页返回集合
		PageResult<CatchInfoDo> pageResult = new PageResult<>();
		// 定义查询条件容器
		CatchInfoDoExample example = new CatchInfoDoExample();
		// 添加条件
		CatchInfoDoExample.Criteria criteria = example.createCriteria();
		// 默认条件添加is_deleted="N"
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 排序字段
		if (StringUtils.isBlank(catchInfoRequest.getOrderByClause())) {
			example.setOrderByClause("create_time desc");
		} else {
			example.setOrderByClause(catchInfoRequest.getOrderByClause());
		}
		// 分页查询总数
		int count = catchInfoDoMapperExt.countByExample(example);
		// 创建分页
		PageDto pageDto = new PageDto(catchInfoRequest.getPage(), catchInfoRequest.getLimit(), count);
		example.setPageDto(pageDto);
		// 查询数据集合
		List<CatchInfoDo> list = catchInfoDoMapperExt.selectByExample(example);
		// 返回list和分页
		pageResult.setData(list);
		pageResult.setCount(count);
		return pageResult;
	}

	/**
	 * 单条记录
	 */
	@Override
	public CatchInfoDo getCatchInfo(Long id) {
		return catchInfoDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 通过人员id查询抓获信息
	 */
	@Override
	public CatchInfoDo getCatchInfoByRid(Long id) {
		// 定义查询条件容器
		CatchInfoDoExample example = new CatchInfoDoExample();
		// 添加条件
		CatchInfoDoExample.Criteria criteria = example.createCriteria();
		// 默认条件添加is_deleted="N"
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andRegisterIdEqualTo(id);
		List<CatchInfoDo> list = catchInfoDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 新增编辑抓获信息
	 *
	 * @param request
	 * @param record
	 */
	@Override
	@Transactional
	public CatchInfoDo saveCatchInfo(CatchInfoDo catchInfoDo) {
		if (catchInfoDo.getId() == null) {
			// 新增
			DomainUtil.setCommonValueForCreate(catchInfoDo, currentUserService.getPvgInfo());
			catchInfoDoMapperExt.insertSelective(catchInfoDo);
		} else {
			// 编辑
			DomainUtil.setCommonValueForUpdate(catchInfoDo, currentUserService.getPvgInfo());
			catchInfoDoMapperExt.updateByPrimaryKeySelective(catchInfoDo);
		}
		return catchInfoDoMapperExt.selectByPrimaryKey(catchInfoDo.getId());
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void removeCatchInfo(CatchInfoDo catchInfoDo) {
		// 先删除该角色下的菜单，再删除角色
		if (catchInfoDo.getId() != null) {
			DomainUtil.setCommonValueForDelete(catchInfoDo, currentUserService.getPvgInfo());
			catchInfoDoMapperExt.deleteByPrimaryKey(catchInfoDo);
		}
	}
}
