package com.dsw.iot.vo;

import com.dsw.iot.util.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class UrineTestInfoVo extends BaseDto {
    /**
     * ID
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Long id;

    private String search;
    /**
     * 人员注册表id*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Long registerId;

    /**
     * 报告书类别*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String reportType;

    /**
     * 检字*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String jianzi;

    /**
     * 报告书编号*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String reportNo;

    /**
     * 检测时间*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date checkDate;

    /**
     * 检测方式*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String checkWay;

    /**
     * 检测地点*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String checkPlace;

    /**
     * 检测结果*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String checkResult;

    /**
     * 被检测人姓名*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String name;

    /**
     * 性别*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String sex;

    /**
     * 出生日期*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date birthDate;

    /**
     * 证件号码*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String cardNo;

    /**
     * 现住地*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String livingPlace;

    /**
     * 工作单位*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String workUnit;

    /**
     * 现查明*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String findOut;

    /**
     * 勾选证据*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String proof;

    /**
     * 办案区负责人*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String officer;

    /**
     * 检测人、认定人员*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String checkPerson;

    /**
     * 检测人2、认定人员2*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String checkPerson2;

    /**
     * b瓶编号*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String bottleb;

    /**
     * b瓶保管到期时间*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date bottleDeadtimeB;

    /**
     * a瓶编号*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String bottlea;

    /**
     * a瓶保管到期时间*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date bottleDeadtimeA;

    /**
     * 是否删除*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String isDeleted;

    /**
     * 创建时间*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date createTime;

    /**
     * 创建人 *
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String createUser;

    /**
     * 更新时间*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date updateTime;

    /**
     * 更新人*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String updateUser;

    /**
     * 描述*
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String remark;

    /**
     * 被认定人
     */
    private String identified;
    /**
     * 认定人1
     */
    private String cognizant1;
    /**
     * 认定人2
     */
    private String cognizant2;
    /**
     * 办案单位负责人
     */
    private String orgManager;


}
