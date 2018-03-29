package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.RoomAreaDoMapperExt;
import com.dsw.iot.dto.RoomAreaRequest;
import com.dsw.iot.model.RoomAreaDo;
import com.dsw.iot.model.RoomAreaDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.RoomAreaService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

public class RoomAreaServiceImpl implements RoomAreaService {

	@Autowired(required = false)
	private RoomAreaDoMapperExt roomAreaDoMapperExt;
	@Autowired
	private CurrentUserService currentUserService;

	@Override
	public PageResult<RoomAreaDo> queryPage(RoomAreaRequest request) {
		PageResult<RoomAreaDo> pageResult = new PageResult<>();
		RoomAreaDoExample example = new RoomAreaDoExample();
		RoomAreaDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 添加条件
		if (StringUtils.isNotEmpty(request.getStatus())) {
			criteria.andStatusEqualTo(request.getStatus());
		}
		int count = roomAreaDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
		example.setPageDto(pageDto);
		List<RoomAreaDo> list = roomAreaDoMapperExt.selectByExample(example);
		pageResult.setData(list);
		pageResult.setCount(count);
		return pageResult;
	}

	@Override
	public void saveRoomArea(RoomAreaDo roomAreaDo) throws BizException {
		if (null == roomAreaDo.getId()) {
			validate(roomAreaDo, "add");
			// 新增
			DomainUtil.setCommonValueForCreate(roomAreaDo, currentUserService.getPvgInfo());
			roomAreaDoMapperExt.insertSelective(roomAreaDo);
		} else {
			validate(roomAreaDo, "update");
			// 编辑
			DomainUtil.setCommonValueForUpdate(roomAreaDo, currentUserService.getPvgInfo());
			roomAreaDoMapperExt.updateByPrimaryKeySelective(roomAreaDo);
		}
	}

	private void validate(RoomAreaDo roomAreaDo, String option) throws BizException {
		if ("add".equals(option)) {

		} else {
			// TODO
		}
	}

	@Override
	public RoomAreaDo getRoomArea(Long id) {
		return roomAreaDoMapperExt.selectByPrimaryKey(id);
	}

	@Override
	public void deleteRoomArea(String ids) {
		if (!StringUtils.isEmpty(ids)) { // 删除多个id
			String[] idArr = ids.split(",");// 分解逗号拼接字符串
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					RoomAreaDo roomAreaDo = new RoomAreaDo();
					roomAreaDo.setId(Long.parseLong(id));
					roomAreaDoMapperExt.deleteByPrimaryKey(roomAreaDo);
				}
			}
		}
	}

}
