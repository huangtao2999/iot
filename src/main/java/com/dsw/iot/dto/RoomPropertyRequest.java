package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;
/**
 * RoomProperty查询请求头
 *
 * @author zc
 **/
@Data
public class RoomPropertyRequest extends BaseDto {
	 //房间类型
    private String roomType; //-1为查询除等候室所有的房间
    //房间状态
    private String roomStatus;
    
    //房间id
    private String roomId;
}
