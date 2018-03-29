package com.dsw.iot.dto;

import lombok.Data;

/**
 * 人脸基本信息
 *
 * @author huangt
 * @create 2018-03-14 13:01
 **/
@Data
public class FaceLineInfoDto {
    private String faceId;
    private String groupId;
    private Float score;
    private Float threshold;
    private String cardId;
    private String name;
    private String groupFaceBase64;

    private Integer ruleId;
    private Integer alertType;
}
