package com.dsw.iot.vo;

import java.util.List;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.InjuryRegisterRequest;
import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.util.BaseModel;

import lombok.Data;
/*
 * 房间信息 
 */
@Data
public class RoomInquiryVo extends BaseModel{
	
	private Long id;//房间id
	
	private String roomNo;
	
	private String roomName;
	
	private String roomStatus;
	
	private Long lockPersonId;//关押人员id
	
	private String lockPersonName;//房间关押姓名

}
