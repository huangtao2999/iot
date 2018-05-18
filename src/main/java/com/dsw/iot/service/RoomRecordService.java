package com.dsw.iot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsw.iot.dto.RoomRecordRequest;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.model.RoomRecordDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.BlPersonRegisterVo;

/**
 * 人员房间关联记录
 * @author zc
 *
 */
public interface RoomRecordService {
	
	/**
	 * 查询关押人员 分页查询
	 * 
	 */
	public PageResult<PersonRegisterDo> queryPageRecord(RoomRecordRequest request);
	
    /**
     * 新增或跟新人员房间关联表
     */
    public void saveRoomRecord(RoomRecordDo roomRecordDo) throws BizException;

	/**
	 * 根据主键id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public RoomRecordDo getRoomRecord(Long id);

	/**
	 * 根据主键id删除数据
	 */
	public void deleteRoomRecord(String ids);
	
	/**
	 * 根据房间id查询关联人员
	 * roomid 房间id
	 * isHistory 是否历史记录,为空则查询所有的
	 * 
	 */
	public List<PersonRegisterDo> getPersonRegisterByrid(Long roomId,String isHistory);
	

	
	/**
	 * 根据房间id查询关联人员
	 * roomid 房间id
	 * registerId 人员id
	 * isHistory 是否历史记录,为空则查询所有的
	 * 
	 */
	public List<PersonRegisterDo> getPersonRegisterByrid(Long roomId,Long registerId,String isHistory);
	
	/**
	 * 查询关联人员是否存在询问室
	 * registerId 人员id
	 * isHistory 是否历史记录,为空则查询所有的
	 * 
	 */
	public List<PersonRegisterDo> getPersonRegisterByprid(Long registerId,String isHistory);
	
	/**
	 * 查询关联人员是否存在询问室
	 * registerId 人员id
	 * isHistory 是否历史记录,为空则查询所有的
	 * 
	 */
	public List<RoomPropertyDo> getRoomPropertyByprid(Long registerId,String isHistory);
	
	/**
	 * 查询该房间目前关押人员的数量
	 * roomId 房间id
	 */
	public int getPersonCount(Long roomId);
	
	/**
	 * 将该房间的关押人员设置为历史记录
	 * 视为该人员离开询/讯问室 主要是等候室使用
	 * roomId 房间id
	 * registerId 人员id
	 */
	public void updateRoomRecordHistory(Long roomId,Long registerId);
	
	/**
	 * 将关押人员设置为历史记录
	 * 视为该人员离开询/讯问室 主要是等候室使用
	 * registerId 人员id
	 */
	public void updateRoomRecordHistory(Long registerId);
	
	/**
	 * 通过房间id查询在这个房间的所有人
	 * 
	 * @param roomId  房间id
	 * @param isHistory 是否历史记录,为空则查询所有的
	 * @return
	 */
	public List<RoomRecordDo> getRoomRecordByrid(Long roomId, String isHistory);
	
	/**
	 * @return
	 */
	public BlPersonRegisterVo authRoom(HttpServletRequest request, HttpServletResponse response) throws BizException;

}
