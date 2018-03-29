package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

/**
 * RoomArea查询请求头
 * @author zc
 *
 */
@Data
public class RoomRecordRequest extends BaseDto {
	//房间id
	private Long roomId;

}
