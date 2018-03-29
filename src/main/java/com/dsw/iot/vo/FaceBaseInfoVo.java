package com.dsw.iot.vo;

import lombok.Data;

/**
 * 人脸识别展示对象
 *
 * @author huangt
 * @create 2018-03-15 17:22
 **/

@Data
public class FaceBaseInfoVo {

    /**
     * 报警编号
     */
    private String id;
    /**
     * 对比后的相识度
     */
    private float simScore;

    private String name;

    private String sex;

    private String cardId;

    private String birthDate;

    private String natives;

    private String groupFaceFullUrl;

    private String faceImageFullUrl;

    private String createTime;

    private String faceGroup;

}
