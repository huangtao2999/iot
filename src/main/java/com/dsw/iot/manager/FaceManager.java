package com.dsw.iot.manager;

import com.dsw.iot.dto.FaceBaseInfoRequest;
import com.dsw.iot.dto.FaceHeadInfoDto;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.FaceBaseInfoVo;

/**
 * 人脸识别对接
 *
 * @author huangt
 * @create 2018-03-15 16:26
 **/
public interface FaceManager {
    /**
     * 保存云从人脸对比-报警信息
     *
     * @param faceHeadInfoDto
     */
    public void saveFaceInfo(FaceHeadInfoDto faceHeadInfoDto);

    public PageResult<FaceBaseInfoVo> queryPage(FaceBaseInfoRequest request);
}
