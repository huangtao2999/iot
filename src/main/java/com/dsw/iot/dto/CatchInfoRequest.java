package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class CatchInfoRequest extends BaseDto{
    private Long id;

    /**
     * 注册表id
     */
    private Long registerId;

    /**
     * 案发地
     */
    private String happenedPlace;

    /**
     * 保密级别
     */
    private String securityLevel;

    /**
     * 嫌疑属性
     */
    private String suspectProperty;

    /**
     * 抓获日期
     */
    private Date catchTime;

    /**
     * 抓获地点
     */
    private String catchPlace;

    /**
     * 抓获人
     */
    private String catchPerson;

    /**
     * 抓获方式
     */
    private String catchMethod;

    /**
     * 抓获单位
     */
    private String catchUnit;

    /**
     * 局外抓获单位
     */
    private String catchUnitOut;

    /**
     * 现实状况
     */
    private String reality;

    /**
     * 健康状况（字典：1-健康；2-良好；3-差，，，或根据警综数据定义）
     */
    private String healthy;

    /**
     * 是否吸毒检测（根据这个自动打勾要检测的项目）
     */
    private String isCheckDrug;

    /**
     * 可疑依据
     */
    private String doubtProof;

    /**
     * 作案原因
     */
    private String motivation;

    /**
     * 是否删除（逻辑删除，查询时系统自动拼sql）
     */
    private String isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;


    private String orderByClause;
}
