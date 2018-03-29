package com.dsw.iot.vo;

import java.util.Date;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

@Data
public class PersonTraceVo  extends BaseModel{
	/**
	 * 主键id
	 */
    private Long id;
    /**
     * 人员id
     */
    private Long registerId;
    /**
     * 人员姓名
     */
    private String personName;
    /**
     * 内容
     */
    private String content;
    private String isDeleted;
    /**
     * 开始时间
     */
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    /**
     * 备注
     */
    private String remark;
}
