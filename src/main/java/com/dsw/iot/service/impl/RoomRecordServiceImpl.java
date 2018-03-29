package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.PersonRegisterDoMapperExt;
import com.dsw.iot.dal.RoomPropertyDoMapperExt;
import com.dsw.iot.dal.RoomRecordDoMapperExt;
import com.dsw.iot.dto.RoomRecordRequest;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.model.RoomRecordDo;
import com.dsw.iot.model.RoomRecordDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.RoomRecordService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

import dm.jdbc.util.StringUtil;

/**
 * 人员--房间关联服务
 *
 * @author zc
 *
 */

@Service
public class RoomRecordServiceImpl implements RoomRecordService {
	protected static final Logger logger = Logger.getLogger(RoomRecordServiceImpl.class);
	@Autowired(required = false)
	private RoomRecordDoMapperExt roomRecordDoMapperExt;
	@Autowired(required = false)
	private PersonRegisterDoMapperExt peRegisterDoMapperExt;
	@Autowired(required = false)
	private RoomPropertyDoMapperExt roomPropertyDoMapperExt;
	@Autowired
	private CurrentUserService currentUserService;

	/**
	 * 根据房间id查询关联人员 分页查询 roomid 房间id
	 *
	 */
	@Override
	public PageResult<PersonRegisterDo> queryPageRecord(RoomRecordRequest request){
		PageResult<PersonRegisterDo> pageResult = new PageResult<>();
		List<PersonRegisterDo> list = null;
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRoomIdEqualTo(request.getRoomId());
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andIsHistoryEqualTo("0");

		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			list = new ArrayList<PersonRegisterDo>();
			for (RoomRecordDo room_RecordDo : list_roomrecord) {
				PersonRegisterDo personRegisterDo = peRegisterDoMapperExt
						.selectByPrimaryKey(room_RecordDo.getRegisterId());
				if (personRegisterDo != null) {
					list.add(personRegisterDo);
				}
			}
		}
		int count = roomRecordDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
		example.setPageDto(pageDto);
		pageResult.setData(list);
		pageResult.setCount(count);
		return pageResult;
	}

	@Override
	public void saveRoomRecord(RoomRecordDo roomRecordDo) throws BizException {
		if (null == roomRecordDo.getId()) {
			validate(roomRecordDo, "add");
			// 新增
			DomainUtil.setCommonValueForCreate(roomRecordDo, currentUserService.getPvgInfo());
			roomRecordDoMapperExt.insertSelective(roomRecordDo);
		} else {
			validate(roomRecordDo, "update");
			// 编辑
			DomainUtil.setCommonValueForUpdate(roomRecordDo, currentUserService.getPvgInfo());
			roomRecordDoMapperExt.updateByPrimaryKeySelective(roomRecordDo);
		}
	}

	private void validate(RoomRecordDo roomRecordDo, String option) throws BizException {
		if ("add".equals(option)) {
			if (roomRecordDo.getRoomId() == null) {
				throw new BizException("讯询问室id不能为空");
			}
			if (roomRecordDo.getRegisterId() == null) {
				throw new BizException("关押人员id不能为空");
			}
		} else {
			// TODO
		}
	}

	@Override
	public RoomRecordDo getRoomRecord(Long id) {
		return roomRecordDoMapperExt.selectByPrimaryKey(id);
	}

	@Override
	public void deleteRoomRecord(String ids) {
		if (!StringUtils.isEmpty(ids)) { // 删除多个id
			String[] idArr = ids.split(",");// 分解逗号拼接字符串
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					RoomRecordDo roomRecordDo = new RoomRecordDo();
					roomRecordDo.setId(Long.parseLong(id));
					DomainUtil.setCommonValueForDelete(roomRecordDo, currentUserService.getPvgInfo());
					roomRecordDoMapperExt.deleteByPrimaryKey(roomRecordDo);
				}
			}
		}
	}


	/**
	 * 根据房间id查询关联人员 roomid 房间id isHistory 是否历史记录,为空则查询所有的
	 *
	 */
	@Override
	@Transactional
	public List<PersonRegisterDo> getPersonRegisterByrid(Long roomId,String isHistory) {
		List<PersonRegisterDo> list = null;
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRoomIdEqualTo(roomId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if(StringUtil.isNotEmpty(isHistory)) {
			criteria.andIsHistoryEqualTo(isHistory);
		}
		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			list = new ArrayList<PersonRegisterDo>();
			for (RoomRecordDo room_RecordDo : list_roomrecord) {
				PersonRegisterDo personRegisterDo = peRegisterDoMapperExt
						.selectByPrimaryKey(room_RecordDo.getRegisterId());
				if (personRegisterDo != null) {
					list.add(personRegisterDo);
				}
			}
		}

		return list;
	}



	/**
	 * 根据房间id查询关联人员 roomid 房间id registerId 人员id isHistory 是否历史记录,为空则查询所有的
	 *
	 */
	@Override
	@Transactional
	public List<PersonRegisterDo> getPersonRegisterByrid(Long roomId,Long registerId,String isHistory) {
		List<PersonRegisterDo> list = null;
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRoomIdEqualTo(roomId);
		criteria.andRegisterIdEqualTo(registerId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if(StringUtil.isNotEmpty(isHistory)) {
			criteria.andIsHistoryEqualTo(isHistory);
		}
		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			list = new ArrayList<PersonRegisterDo>();
			for (RoomRecordDo room_RecordDo : list_roomrecord) {
				PersonRegisterDo personRegisterDo = peRegisterDoMapperExt
						.selectByPrimaryKey(room_RecordDo.getRegisterId());
				if (personRegisterDo != null) {
					list.add(personRegisterDo);
				}
			}
		}

		return list;
	}

	/**
	 * 查询关联人员是否存在询问室 registerId 人员id isHistory 是否历史记录,为空则查询所有的
	 *
	 */
	@Override
	public List<PersonRegisterDo> getPersonRegisterByprid(Long registerId,String isHistory){
		List<PersonRegisterDo> list = null;
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRegisterIdEqualTo(registerId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if(StringUtil.isNotEmpty(isHistory)) {
			criteria.andIsHistoryEqualTo(isHistory);
		}
		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			list = new ArrayList<PersonRegisterDo>();
			for (RoomRecordDo room_RecordDo : list_roomrecord) {
				PersonRegisterDo personRegisterDo = peRegisterDoMapperExt
						.selectByPrimaryKey(room_RecordDo.getRegisterId());
				if (personRegisterDo != null) {
					list.add(personRegisterDo);
				}
			}
		}

		return list;

	}

	/**
	 * 查询关联人员是否存在询问室 registerId 人员id isHistory 是否历史记录,为空则查询所有的
	 *
	 */
	@Override
	@Transactional
	public List<RoomPropertyDo> getRoomPropertyByprid(Long registerId,String isHistory){
		List<RoomPropertyDo> list = null;
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRegisterIdEqualTo(registerId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if(StringUtil.isNotEmpty(isHistory)) {
			criteria.andIsHistoryEqualTo(isHistory);
		}
		example.setOrderByClause("create_time desc");// 加日期排序
		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			list = new ArrayList<RoomPropertyDo>();
			for (RoomRecordDo room_RecordDo : list_roomrecord) {
				RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt
						.selectByPrimaryKey(room_RecordDo.getRoomId());
				if (roomPropertyDo != null) {
					list.add(roomPropertyDo);
				}
			}
		}

		return list;
	}

	/**
	 * 查询该房间目前关押人员的数量
	 * roomId 房间id
	 */
	@Override
	public int getPersonCount(Long roomId) {
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRoomIdEqualTo(roomId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 目前关押人员
		criteria.andIsHistoryEqualTo("0");
		return roomRecordDoMapperExt.countByExample(example);
	}

	/**
	 * 将该房间的关押人员设置为历史记录 视为该人员离开询/讯问室 主要是等候室使用 roomId 房间id registerId 人员id
	 */
	@Override
	public void updateRoomRecordHistory(Long roomId,Long registerId) {
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 目前关押人员
		criteria.andIsHistoryEqualTo("0");
		criteria.andRegisterIdEqualTo(registerId);
		criteria.andRoomIdEqualTo(roomId);
		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			for(RoomRecordDo roomRecordDo:list_roomrecord) {
				roomRecordDo.setIsHistory("1");//设为历史记录
				try {
					saveRoomRecord(roomRecordDo);
				} catch (BizException e) {
					// TODO Auto-generated catch block
					logger.error("保存异常", e);
				}
			}
		}
	}

	/**
	 * 将关押人员设置为历史记录
	 * 视为该人员离开询/讯问室 主要是等候室使用
	 * registerId 人员id
	 */
	@Override
	public void updateRoomRecordHistory(Long registerId) {
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 目前关押人员
		criteria.andIsHistoryEqualTo("0");
		criteria.andRegisterIdEqualTo(registerId);
		System.out.println("registerId="+registerId);
		List<RoomRecordDo> list_roomrecord = roomRecordDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list_roomrecord)) {
			System.out.println("size="+list_roomrecord.size());
			for(RoomRecordDo roomRecordDo:list_roomrecord) {
				roomRecordDo.setIsHistory("1");//设为历史记录
				try {
					saveRoomRecord(roomRecordDo);
				} catch (BizException e) {
					// TODO Auto-generated catch block
					logger.error("保存异常", e);
				}
			}
		}
	}

	/**
	 * 通过房间id查询在这个房间的所有人
	 * roomId 房间id
	 * isHistory 是否历史记录,为空则查询所有的
	 */
	@Override
	public List<RoomRecordDo> getRoomRecordByrid(Long roomId, String isHistory) {
		List<RoomRecordDo> list = null;
		RoomRecordDoExample example = new RoomRecordDoExample();
		RoomRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andRoomIdEqualTo(roomId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if(StringUtil.isNotEmpty(isHistory)) {
			criteria.andIsHistoryEqualTo(isHistory);
		}
		list = roomRecordDoMapperExt.selectByExample(example);
		return list;
	}

}
