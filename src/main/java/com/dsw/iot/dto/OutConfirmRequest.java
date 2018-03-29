package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class OutConfirmRequest extends BaseDto{
    private Long id;

    /**
     * 人员id
     */
    private Long registerId;

    /**
     * 人员姓名（列表显示用）
     */
    private String applyName;

    /**
     * 申请类型
     */
    private String applyType;

    /**
     * 申请原因
     */
    private String applyReason;


    /**
     * 申请时间
     */
    private Date applyTime;
    private Date applyStartTime;//申请开始时间
    private Date applyEndTime;//申请结束时间

    /**
     * 物品是否全部归还（字典：0-否；1-是）
     */
    private String isReturn;

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
     * 是否押送（字典：0-否；1-是）
     */
    private String isEscort;

    /**
     * 押送民警
     */
    private String escortPolice;

    /**
     * 提醒id
     */
    private String remindId;

    /**
     * 出所类型（字典：1-正式出所；2-临时出所）
     */
    private String outType;

    /**
     * 是否是以往审核记录，（字典：0-已出所；1-未出所）
     */
    private String isHistory;

    /**
     * 出办案区时间
     */
    private Date outTime;

    /**
     * 归办案区时间
     */
    private Date backTime;

    /**
     * 是否删除（逻辑删除，查询时系统自动拼sql）
     */
    private String isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 描述
     */
    private String remark;


    /**
     * 排序
     */
    private String orderByClause;

	private Long roleId; //出所申请选择审核角色id

//	private String goodIds;//出所申请扣押的物品ids

	private String holdGoods;//扣押物品ids，供正式出办案区审批不通过的时候查看历史记录
}
