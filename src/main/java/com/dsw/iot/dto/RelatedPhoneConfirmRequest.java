package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class RelatedPhoneConfirmRequest extends BaseDto{
    private Long id;

    /**
     * 人员注册id
     */
    private Long registerId;

    /**
     * 主办民警
     */
    private String policeName;

    /**
     * 联系人
     */
    private String callName;
    /**
     * 人员姓名（列表显示用）
     */
    private String applyName;
    /**
     * 申请原因
     */
    private String applyReason;

    /**
     * 通话时长
     */
    private String talkTime;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 审批人
     */
    private String auditUser;

    /**
     * 审批状态（1-通过；2-不通过；3-待审批）
     */
    private String Status;

    /**
     * 审批时间
     */
    private Date auditTime;

    /**
     * 审批备注
     */
    private String auditContent;

    private String isDeleted;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private String remark;
    /**
     * 开始时间
     */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 排序
	 */
	private String orderByClause;
}
