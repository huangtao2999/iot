package com.dsw.iot.vo;

import java.util.Date;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

@Data
public class PersonFormVo extends BaseModel{
    private Long id;
    private Long registerId;
    private String formNo;
    private String org;
    private String cardNo;
    private String cardType;
    private String name;
    private String sex;
    private String birthDate;
    private String phone;
    private String domicilePlace;
    private String livingPlace;
    private String inType;
    private String inTime;
    private String inReason;
    private String alarmNumber;
    private String wordNumber;
    private String medicalHistory;
    private String checkBodyResult;
    private String collectProject;
    private String doInfoCollect;
    private String doInfoInbase;
    private String doRadioSync;
    private String doCheck;
    private String diskNo;
    private String receiveCardNo;
    private String receiveDate;
    private String isDeleted;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private String remark;

    //手写板签名信息
    private String formHostPoliceSign;
    private String formAdminSign;
    private String formCheckPoliceSign;
    private String formWitnessSign;
    private String formSuspectSign;
    private String formGoodsHostPoliceSign;
    private String formGoodsAdminSign;
    private String formGoodsSuspectSign;
    private String formReceiveSign;
    private String formGoodsHoldAdminSign;
}
