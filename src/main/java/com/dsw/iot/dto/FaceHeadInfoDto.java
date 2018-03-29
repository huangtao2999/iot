package com.dsw.iot.dto;

import lombok.Data;

import java.util.List;

/**
 * 人脸基本信息
 *
 * @author huangt
 * @create 2018-03-14 13:01
 **/
@Data
public class FaceHeadInfoDto {
    private String topic;
    private String code;
    private Float captureId;
    private Float cameraId;
    private String time;
    private String faceImage;
    private List<FaceLineInfoDto> list;
}
