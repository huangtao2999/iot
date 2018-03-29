package com.dsw.iot.constant;

/**
 * 云从对接配置
 */
public class FaceConfig {
    public static final String userId = "c11aa5249fb64ba5bfc10f93e123320a";

    /**
     * 人脸底库检索
     */
    public static final String method_faceSelect = "/face/faceSelect";
    /**
     * 检测图片中人脸数量
     */
    public static final String method_detect = "/faceCompare/detect";

    /**
     * 获取报警记录
     */
    public static final String method_warningRecord = "/warningRecord/select";

}
