package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.RoomPropertyRequest;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.RoomInquiryVo;
import com.dsw.iot.vo.RoomPropertyVo;

/**
 * 讯询问室配置服务
 * @author zc
 *
 */
public interface RoomPropertyService {

	 /**
     * 分页查询讯询问室
     */
    public PageResult<RoomPropertyDo> queryPage(RoomPropertyRequest request);

    /**
     * 分页查询等候室列表
     */
    public PageResult<RoomPropertyVo> queryPageWait(RoomPropertyRequest request);

    /**
     * 分页查询询问室列表
     */
    public PageResult<RoomInquiryVo> queryPageInquiry(RoomPropertyRequest request);


    /**
     * 新增或跟新讯询问室
     */
    public void saveRoomProperty(RoomPropertyDo roomPropertyDo) throws BizException;


	/**
	 * 根据主键id查询数据
	 *
	 * @param id
	 * @return
	 */
	public RoomPropertyDo getRoomProperty(Long id);

	/**
	 * 根据主键id删除数据
	 */
	public void deleteRoomProperty(String ids);

	/**
	 * 讯/询问室列表
	 * roomtype 房间类型 为空，则代表查询处等候室之外的所有房间
	 * roomstatus 房间状态 为空，则代表查询所有
	 */
	public List<RoomPropertyDo> getRoomPropertylist(String roomType,String roomStatus);

	/**
	 * 讯/询问室分配
	 * personRegisterDo 需要分配人员的心
	 * roomId 房间id
	 */
	public ActionResult<String> getRoomDistribution(Long registerId,Long roomId);


	/**
	 * 等候室分配,自动分配
	 * registerId 人员id
	 */
	public  ActionResult<String> getRoomWaitAutoDistribution(Long registerId);

	/**
	 * 等候室分配
	 * 人缘直接分配
	 * registerId 人员id
	 * roomId 房间id
	 */
	public void getRoomWaitDistribution(Long registerId,Long roomId);

	/**
	 * 修改房间状态 roomId 房间id roomStatus 房间状态
	 */
	public void updateRoomStatus(Long roomId,String roomStatus);

	/**
	 * type=1 打开房间电源，type=2 打开换气扇 ，type=3打开只能墙体
	 * @param id
	 * @param type
	 */
	public ActionResult<String> openEvent(Long id, String type);

	/**
	 * 更新等候室led
	 *
	 * @param roomId
	 */
	public void distributeWaitRoomShow(Long roomId);

	/**
	 * 走廊led
	 * 
	 * @param registerId
	 * @param roomId
	 * @return
	 */
	public String corridorLedShow(Long registerId, Long roomId);

	/**
	 * 清空房间（清除嫌疑人，作历史状态）
	 * 
	 * @param id
	 */
	public void releaseRoom(Long roomId);
}
