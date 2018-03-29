package com.dsw.iot.vo;

import lombok.Data;

import com.dsw.iot.util.BaseModel;
/*
 * 房间信息 
 */
@Data
public class RoomPropertyVo extends BaseModel{
	
	private Long id;//房间id
	
	private String roomNo;
	
	private String roomName;
	
	 private Integer roomMax;
	
	private String roomSex;//房间关押人员性别
	
	private Integer heldCount;//房间关押人数

	private String heldCountAndRoomMax;// 房间人数与最大关押人数逗号拼接

}
