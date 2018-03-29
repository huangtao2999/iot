package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class DelayConfirmRequest extends BaseDto{
    private Long id;

    /**
     * 人员id
     */
    private Integer registerId;

    /**
     * 人员姓名（显示列表用）
     */
    private String applyName;

    /**
     * 申请原因
     */
    private String applyReason;

    /**
     * 申请时间
     */
    private Date applyTime;
    private Date applyStartTime;	//申请开始时间
    private Date applyEndTime;	//申请结束时间


    /**
     * 延期时间（字典：小时8、12、24、48）
     */
    private String delayHour;

    /**
     * 审核内容
     */
    private String auditContent;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核状态（字典：3-待审核；2-审核不通过；1-审核通过）
     */
    private String status;

    /**
     * 办案区
     */
    private String dept;
    private String isDeleted;

    /**
     * 描述
     */
    private String remark;

    /**
     * 排序
     */
    private String orderByClause;
}
