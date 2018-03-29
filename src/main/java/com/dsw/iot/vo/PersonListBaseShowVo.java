package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

@Data
public class PersonListBaseShowVo extends BaseModel {

    /**
     * 人员查询页右边属性信息的name
     */
    private String name;
    /**
     * 人员查询页右边属性信息的值
     */
    private String value;
    /**
     * 区域名称
     */
    private String roomName;
    /**
     * 区域ID
     */
    private Long roomId;

    /**
     * 人员查询页右边流程里加是否可点击标志
     */
    private boolean canClick;
}
