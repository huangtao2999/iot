package com.dsw.iot;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.service.RoomPropertyService;
import com.dsw.iot.service.RoomRecordService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;

public class TestRoom  extends BaseTest{
	
	    @Autowired
	    private RoomPropertyService roomPropertyService;
	    @Autowired
	    private RoomRecordService roomRecordService;
	    private RoomPropertyDo roomPropertyDo=null;

    @Test
    public void register() throws BizException {
    	saveRoomProperty();
    	// 查询历史关押记录
    	getPersonRegisterlist();
    	//关押人员退出等候室
//    	updateRoomRecordHistory();
//    	//更新房间状态
//    	updateRoomStatus();
    	//等候室分配
//    	getRoomWaitAutoDistribution();
    }
	 
    /*
     * 保存/更新/查询询问室管理模块
     */
    private void saveRoomProperty() {
    	System.out.println("开始保存");
        roomPropertyDo=new RoomPropertyDo();
    	roomPropertyDo.setOrg("武汉");
    	roomPropertyDo.setArea("汉川");
    	roomPropertyDo.setRoomName("江夏区");
    	roomPropertyDo.setRoomType("1");
    	roomPropertyDo.setRoomNo("23");
    	 try {
			roomPropertyService.saveRoomProperty(roomPropertyDo);
		} catch (BizException e) {
			e.printStackTrace();
         }
    		System.out.println("保存完成");
    	 
         //查询询问室
    	 System.out.println("开始查询房间");
    	 roomPropertyDo=roomPropertyService.getRoomProperty(Long.parseLong("4"));
    	 System.out.println("房间名称:"+roomPropertyDo.getRoomName());
    	 
    	 //更新房间编号
    	 System.out.println("更新房间编号为24");
    	 roomPropertyDo.setRoomNo("24");
    	 try {
			roomPropertyService.saveRoomProperty(roomPropertyDo);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 roomPropertyDo=roomPropertyService.getRoomProperty(Long.parseLong("4"));
    	 System.out.println("房间编号:"+roomPropertyDo.getRoomNo());
    }
    	
    /**
     * 查询历史关押记录
     * roomid 4
     */
    private void getPersonRegisterlist() {
    	List<PersonRegisterDo> list=roomRecordService.getPersonRegisterByrid(Long.parseLong("4"),"");
    	if(list!=null) {
    		for(PersonRegisterDo personRegisterDo:list) {
    			System.out.println("关押人员名称="+personRegisterDo.getName());
    		}
    		
    	}
    }
    
    private void updateRoomRecordHistory() {
    	roomRecordService.updateRoomRecordHistory(Long.parseLong("1"), Long.parseLong("24"));
    }

    private void updateRoomStatus() {
    	roomPropertyService.updateRoomStatus(Long.parseLong("1"), "0");
    }
    
    private void getRoomWaitAutoDistribution() {
    	ActionResult<String> as= roomPropertyService.getRoomWaitAutoDistribution(Long.parseLong("24"));
    	System.out.println("success="+as.isSuccess());
    	System.out.println("content="+as.getContent());
    }
}
