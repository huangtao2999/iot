package com.dsw.iot.controller.comm.rpc;

import com.dsw.iot.vo.BlContentVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.service.FaduService;

import java.util.List;

@RestController
@RequestMapping("/FaduController")
public class FaduController {
    protected static final Logger logger = Logger.getLogger(FaduController.class);
    @Autowired
    private FaduService faduService;

    /**
     * 根据笔录ID获取笔录内容
     */
    @RequestMapping(value = "/queryBiluByBiluID")
    public BlContentVo queryBiluByBiluID(String blId, String isOuterId) {
        BlContentVo blContentVo = null;
        try {
            blContentVo = faduService.queryBiluByBiluID(blId, isOuterId);
        } catch (Exception e) {
            logger.error("FaduController.queryBiluByBiluID()调用异常", e);
        }
        return blContentVo;
    }

    /**
     * 获取笔录内容pdf
     */
    @RequestMapping("/getBlContentToPdf")
    public List<BlContentVo> getBlContentToPdf(String braceletNumber) throws Exception {
        List<BlContentVo> list = null;
//        try {
        list = faduService.getBlContentToPdfPath(braceletNumber);
//        } catch (Exception e) {
//            logger.error("FaduController.getBlContentToPdf()调用异常",e);
//        }
        return list;
    }

    /**
     * 获取笔录内容pdf
     */
    @RequestMapping("/getBlContentToJpg")
    public List<BlContentVo> getBlContentToJpg(String alertNumber) {
        List<BlContentVo> list = null;
        try {
            list = faduService.getBlContentToJpgPath(alertNumber);
        } catch (Exception e) {
            logger.error("FaduController.getBlContentToJpg()调用异常", e);
        }
        return list;
    }
}
