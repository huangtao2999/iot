package com.dsw.iot.manager;

import com.dsw.iot.model.AttachDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.CaptureCfgVo;

/**
 * 视频抓拍服务类
 *
 * @author huangt
 * @create 2018-03-07 18:34
 **/
public interface VideoManager {
    /**
     * 通过类型抓拍
     *
     * @param type
     * @return
     */
    public AttachDo capture(String type) throws BizException;

    /**
     * 通过摄像头类型  初始化配置
     *
     * @param type
     */
    public CaptureCfgVo initCaptureCfg(String type);
}
