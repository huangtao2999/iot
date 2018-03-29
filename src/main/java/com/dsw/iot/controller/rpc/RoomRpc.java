package com.dsw.iot.controller.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.RoomPropertyRequest;
import com.dsw.iot.dto.RoomRecordRequest;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.service.RoomPropertyService;
import com.dsw.iot.service.RoomRecordService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.RoomInquiryVo;
import com.dsw.iot.vo.RoomPropertyVo;

/**
 * 询讯问室
 * @author zc
 *
 */
@RestController
@RequestMapping("/Room")
public class RoomRpc {
    @Autowired
    private RoomPropertyService roomPropertyService;
    @Autowired
    private RoomRecordService roomRecordService;

    @RequestMapping("/queryPage")
    public PageResult<RoomPropertyDo> queryPage(RoomPropertyRequest resquest) {
        PageResult<RoomPropertyDo> pageResult = roomPropertyService.queryPage(resquest);
        return pageResult;
    }

    //分页查询等候室列表
    @RequestMapping("/queryPageWait")
    public PageResult<RoomPropertyVo> queryPageWait(RoomPropertyRequest resquest) {
        PageResult<RoomPropertyVo> pageResult = roomPropertyService.queryPageWait(resquest);
        return pageResult;
    }

    //分页查询询问室列表
    @RequestMapping("/queryPageInquiry")
    public PageResult<RoomInquiryVo> queryPageInquiry(RoomPropertyRequest resquest) {
        PageResult<RoomInquiryVo> pageResult = roomPropertyService.queryPageInquiry(resquest);
        return pageResult;
    }
    
    /**
     * 查询关押人员记录
     * @param roomid
     * @return
     */
    @RequestMapping("/queryPageRecord")
    public PageResult<PersonRegisterDo> queryPageRecord(RoomRecordRequest resquest) {
        PageResult<PersonRegisterDo> pageResult = roomRecordService.queryPageRecord(resquest);
        return pageResult;
    }
    
    
    @RequestMapping("/saveRoomProperty")
    public void saveRoomProperty(RoomPropertyDo roomPropertyDo) throws BizException {
         roomPropertyService.saveRoomProperty(roomPropertyDo);
    }

	@RequestMapping("/deleteRoomProperty")
	public void deleteRoomProperty(String ids) throws BizException {
		 roomPropertyService.deleteRoomProperty(ids);
	}

    @RequestMapping("/getRoomPropertyDo")
    public RoomPropertyDo getRoomPropertyDo(Long id) {
    	return roomPropertyService.getRoomProperty(id);
    }

    
    /**
     * 根据讯询室id查询历史关押人员记录
     * @param roomid
     * @return
     */
    @RequestMapping("/getPersonRegisterlist")
    public List<PersonRegisterDo> getPersonRegisterlist(Long roomId,String isHistory) {
    	return roomRecordService.getPersonRegisterByrid(roomId,isHistory);
    }
    
 
    
    /**
	 * 将该房间的关押人员设置为历史记录
	 * 视为该人员离开询/讯问室 主要是等候室使用
	 * roomId 房间id
	 * registerId 人员id
	 */
    @RequestMapping("/updateRoomRecordHistory")
    public void updateRoomRecordHistory(Long roomId,Long registerId) {
    	 roomRecordService.updateRoomRecordHistory(roomId, registerId);
    }
    
    /**
	 * 修改房间状态 
	 * id 房间id
	 * roomStatus 房间状态
	 */
    @RequestMapping("/updateRoomStatus")
    public void updateRoomStatus(Long id,String roomStatus) {
    	roomPropertyService.updateRoomStatus(id, roomStatus);
    }
    
    
	/**
	 * 讯/询问室列表
	 * roomtype 房间类型 为空，则代表查询除等候室之外的所有房间
	 * roomstatus 房间状态 为空，则代表查询所有
	 */
      @RequestMapping("/getRoomPropertylist")
      public List<RoomPropertyDo> getRoomPropertylist(String roomType, String roomStatus) {
      	return roomPropertyService.getRoomPropertylist(roomType, roomStatus);
      }
      
      /**
  	    * 讯/询问室分配,手动分配
  	    * registerId 注册人员id
  	    * id 房间id
  	   */
      @RequestMapping("/getRoomDistribution")
      public ActionResult<String> getRoomDistribution(Long registerId,Long id){
    	  return roomPropertyService.getRoomDistribution(registerId, id);
      }
      
      /**
  	 * 等候室分配,自动分配
  	 * registerId 人员id
  	 */
     @RequestMapping("/getRoomWaitAutoDistribution")
     public ActionResult<String> getRoomWaitAutoDistribution(Long registerId){
   	  return roomPropertyService.getRoomWaitAutoDistribution(registerId);
     }
      
     /**
   	 * 等候室分派 ，直接分配，不考虑预警规则
   	 * registerId 人员id
   	 * 房间id id
   	 */
      @RequestMapping("/getRoomWaitDistribution")
      public void getRoomWaitDistribution(Long registerId,Long roomId){
    	   roomPropertyService.getRoomWaitDistribution(registerId,roomId);
      }

      /**
       * type=1 打开房间电源，type=2 打开换气扇 ，type=3打开只能墙体
       * @param id
       * @param roomId
       */
	  @RequestMapping("/openEvent")
	public ActionResult<String> openEvent(Long id, String type) {
		return roomPropertyService.openEvent(id, type);
	  }
	  
	  /**
	   * 清空房间，将该房间内所有人非历史状态人员置为历史
	   * @param roomId 房间id
	   */
	  @RequestMapping("/releaseRoom")
	  public void releaseRoom(Long roomId){
		roomPropertyService.releaseRoom(roomId);
	  }
}
