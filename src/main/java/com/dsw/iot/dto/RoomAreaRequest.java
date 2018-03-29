package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

/**
 * RoomArea查询请求头
 * @author zc
 *
 */

@Data
public class RoomAreaRequest extends BaseDto {
	
	 //房间类型
    private String status;

}
