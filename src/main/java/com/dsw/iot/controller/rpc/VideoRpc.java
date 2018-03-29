package com.dsw.iot.controller.rpc;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dsw.iot.dto.FaceBaseInfoRequest;
import com.dsw.iot.dto.FaceHeadInfoDto;
import com.dsw.iot.manager.FaceManager;
import com.dsw.iot.manager.VideoManager;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.FaceBaseInfoVo;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 视频抓拍rpc
 *
 * @author huangt
 * @create 2018-01-18 8:40
 **/
@RestController
@RequestMapping("/Video")
public class VideoRpc {
    protected static final Logger logger = Logger.getLogger(VideoRpc.class);

    @Autowired
    private VideoManager videoManager;
    @Autowired
    private FaceManager faceManager;

    /**
     * 根据通道号进行抓拍
     * 实验室 47通道号
     *
     * @param type
     * @return
     */
    @RequestMapping("/captureByChannel")
    public AttachDo captureByChannel(String type) throws BizException {
        return videoManager.capture(type);
    }

    /**
     * 消息订阅-接收云从报警信息
     */
    @RequestMapping("/subscribeFaceMsg")
    public void subscribeFaceMsg(HttpServletRequest request) {
        try {
            String body = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            FaceHeadInfoDto faceHeadInfoDto = JSONObject.parseObject(body, new TypeReference<FaceHeadInfoDto>() {
            });
            faceManager.saveFaceInfo(faceHeadInfoDto);
        } catch (Exception e) {
            logger.error("云从报警信息接口异常", e);
        }
    }

    @RequestMapping("/queryPage")
    public PageResult<FaceBaseInfoVo> queryPage(FaceBaseInfoRequest request) {
        return faceManager.queryPage(request);
    }

}
