package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class InjuryRegisterRequest extends BaseDto{
	private Long id;
    private Long registerId;
    private String part;
    private String remark;

    /**
     * 该部位伤情拍的照片集合，逗号分隔，要绑定的附件ids，类型：INJURY_REGISTER_IMGS
     */
    private String sourceIds;

    /**
     * 要解除绑定的伤情信息
     */
    private String removeIds;
}
