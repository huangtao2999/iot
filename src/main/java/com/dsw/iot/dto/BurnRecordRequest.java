package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class BurnRecordRequest extends BaseDto{
	
	private String policeNo;
	private String policeName;
	private String registerName;
	private int burnState;
}
