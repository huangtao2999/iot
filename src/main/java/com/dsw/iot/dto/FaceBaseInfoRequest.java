package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;
import lombok.Data;

/**
 * 人脸查询请求参数
 *
 * @author huangt
 * @create 2018-03-15 17:23
 **/
@Data
public class FaceBaseInfoRequest extends BaseDto {
    private String startTime;
    private String endTime;
}
