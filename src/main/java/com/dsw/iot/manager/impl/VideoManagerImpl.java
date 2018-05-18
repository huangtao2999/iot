package com.dsw.iot.manager.impl;

import com.dsw.iot.manager.HCNetSDK;
import com.dsw.iot.manager.VideoManager;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.util.BASE64Util;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.FileUtils;
import com.dsw.iot.vo.CaptureCfgVo;
import com.sun.jna.NativeLong;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 海康抓拍服务类
 *
 * @author huangt
 * @create 2018-03-07 18:35
 **/
@Service
public class VideoManagerImpl implements VideoManager {
    protected static final Logger logger = Logger.getLogger(VideoManagerImpl.class);
    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();//设备信息
    NativeLong lUserID = new NativeLong(-1);//用户句柄
    @Value("${capture.file.path}")
    private String tempPath;
    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public AttachDo capture(String type) throws BizException {
        AttachDo attachDo = null;
//        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpg";
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String base64 = capturePicture(type, fileName);
        if (!StringUtils.isEmpty(base64)) {
            attachDo = fileUploadService.uploadBase64(base64, fileName);
        }
        return attachDo;
    }

    /**
     * 抓取图片,返回BASE64码
     *
     * @return
     */
    private String capturePicture(String type, String fileName) throws BizException {
        CaptureCfgVo captureCfgVo = initCaptureCfg(type);
        String base64 = null;
        NativeLong hkChannel = new NativeLong(captureCfgVo.getChannel());///用户通道
        boolean binit = hCNetSDK.NET_DVR_Init();//初始化
        if (binit) {
            //先注销
            if (lUserID.longValue() > -1) {
                hCNetSDK.NET_DVR_Logout_V30(lUserID);
                lUserID = new NativeLong(-1);
            }
            //再注册
            lUserID = hCNetSDK.NET_DVR_Login_V30(captureCfgVo.getIp(), (short) captureCfgVo.getPort(), captureCfgVo.getUserName(), captureCfgVo.getPassword(), m_strDeviceInfo);
            long userID = lUserID.longValue();
            try {
                if (userID == -1) {
                    throw new BizException("NVR抓拍,注册失败");
                } else {
                    //抓拍
                    HCNetSDK.NET_DVR_JPEGPARA lpJpegPara = new HCNetSDK.NET_DVR_JPEGPARA();
                    lpJpegPara.wPicSize = 2;
                    lpJpegPara.wPicQuality = 0;
                    FileUtils.createDir(tempPath);
                    String filePath = tempPath + "/" + fileName;
                    boolean result = hCNetSDK.NET_DVR_CaptureJPEGPicture(lUserID, hkChannel, lpJpegPara, filePath);
                    if (result) {
                        base64 = BASE64Util.getBase64ByPath(filePath);
                        logger.info("NVR抓拍成功:" + filePath);
                    } else {
                        throw new BizException("NVR抓拍,抓拍失败");
                    }
                }
            } finally {
                //注销
                if (lUserID.longValue() > -1) {
                    hCNetSDK.NET_DVR_Logout_V30(lUserID);
                    lUserID = new NativeLong(-1);
                }
            }
        }
        return base64;
    }

    //尿检抓拍配置
    @Value("${urine.capture.ip}")
    private String urine_ip;
    @Value("${urine.capture.port}")
    private int urine_port;
    @Value("${urine.capture.channel}")
    private int urine_channel;
    @Value("${urine.capture.web.port}")
    private int urine_webPort;
    @Value("${urine.capture.web.channel}")
    private int urine_webChannel;
    @Value("${urine.capture.username}")
    private String urine_userName;
    @Value("${urine.capture.password}")
    private String urine_passWord;
    //伤情抓拍配置
    @Value("${injury.capture.ip}")
    private String injury_ip;
    @Value("${injury.capture.port}")
    private int injury_port;
    @Value("${injury.capture.channel}")
    private int injury_channel;
    @Value("${injury.capture.web.port}")
    private int injury_webPort;
    @Value("${injury.capture.web.channel}")
    private int injury_webChannel;
    @Value("${injury.capture.username}")
    private String injury_userName;
    @Value("${injury.capture.password}")
    private String injury_passWord;
    //存物抓拍配置
    @Value("${in.goods.capture.ip}")
    private String in_goods_ip;
    @Value("${in.goods.capture.port}")
    private int in_goods_port;
    @Value("${in.goods.capture.channel}")
    private int in_goods_channel;
    @Value("${in.goods.capture.web.port}")
    private int in_goods_webPort;
    @Value("${in.goods.capture.web.channel}")
    private int in_goods_webChannel;
    @Value("${in.goods.capture.username}")
    private String in_goods_userName;
    @Value("${in.goods.capture.password}")
    private String in_goods_passWord;
    //取物抓拍配置
    @Value("${out.goods.capture.ip}")
    private String out_goods_ip;
    @Value("${out.goods.capture.port}")
    private int out_goods_port;
    @Value("${out.goods.capture.channel}")
    private int out_goods_channel;
    @Value("${out.goods.capture.web.port}")
    private int out_goods_webPort;
    @Value("${out.goods.capture.web.channel}")
    private int out_goods_webChannel;
    @Value("${out.goods.capture.username}")
    private String out_goods_userName;
    @Value("${out.goods.capture.password}")
    private String out_goods_passWord;

    @Override
    public CaptureCfgVo initCaptureCfg(String type) {
        CaptureCfgVo captureCfgVo = new CaptureCfgVo();
        if ("urine".equals(type)) {
            captureCfgVo.setIp(urine_ip);
            captureCfgVo.setPort(urine_port);
            captureCfgVo.setChannel(urine_channel);
            captureCfgVo.setWebPort(urine_webPort);
            captureCfgVo.setWebChannel(urine_webChannel);
            captureCfgVo.setUserName(urine_userName);
            captureCfgVo.setPassword(urine_passWord);
            logger.info("尿检抓拍配置获取成功！");
        } else if ("injury".equals(type)) {
            captureCfgVo.setIp(injury_ip);
            captureCfgVo.setPort(injury_port);
            captureCfgVo.setChannel(injury_channel);
            captureCfgVo.setWebPort(injury_webPort);
            captureCfgVo.setWebChannel(injury_webChannel);
            captureCfgVo.setUserName(injury_userName);
            captureCfgVo.setPassword(injury_passWord);
            logger.info("伤情抓拍配置获取成功！");
        } else if ("inGoods".equals(type)) {
            captureCfgVo.setIp(in_goods_ip);
            captureCfgVo.setPort(in_goods_port);
            captureCfgVo.setChannel(in_goods_channel);
            captureCfgVo.setWebPort(in_goods_webPort);
            captureCfgVo.setWebChannel(in_goods_webChannel);
            captureCfgVo.setUserName(in_goods_userName);
            captureCfgVo.setPassword(in_goods_passWord);
            logger.info("存物抓拍配置获取成功！");
        } else {
            captureCfgVo.setIp(out_goods_ip);
            captureCfgVo.setPort(out_goods_port);
            captureCfgVo.setChannel(out_goods_channel);
            captureCfgVo.setWebPort(out_goods_webPort);
            captureCfgVo.setWebChannel(out_goods_webChannel);
            captureCfgVo.setUserName(out_goods_userName);
            captureCfgVo.setPassword(out_goods_passWord);
            logger.info("取物抓拍配置获取成功！");
        }
        return captureCfgVo;
    }
}
