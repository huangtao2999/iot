package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

import java.util.Date;

@Data
public class IndexDelayVo extends BaseModel {
    //老版本，一起查出来，后台拼接的
    /**
     * 标题
     */
    private String title;
    /**
     * 姓名列表
     */
    private String names;
    /**
     * 类型
     */
    private String type;

    //新版本，可扩展点击看详情事件
    /**
     * 点击跳转路径
     */
    private String path;
    /**
     * 人员姓名
     */
    private String name;
    /**
     * 人员id
     */
    private Long id;
    /**
     * 性别
     */
    private String sex;
    /**
     * 入区时间
     */
    private Date inTime;
    /**
     * 入区原因
     */
    private String inReason;
    /**
     * 用户等级照片ID
     */
    private String personImgId;
}
