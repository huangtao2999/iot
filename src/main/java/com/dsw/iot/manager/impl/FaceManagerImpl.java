package com.dsw.iot.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.constant.FaceConfig;
import com.dsw.iot.dal.FaceBaseDoMapperExt;
import com.dsw.iot.dal.FaceCatchDoMapperExt;
import com.dsw.iot.dto.FaceBaseInfoRequest;
import com.dsw.iot.dto.FaceHeadInfoDto;
import com.dsw.iot.dto.FaceLineInfoDto;
import com.dsw.iot.manager.FaceManager;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.FaceBaseDo;
import com.dsw.iot.model.FaceCatchDo;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.util.HttpClientUtil;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.FaceBaseInfoVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 人脸识别对接
 *
 * @author huangt
 * @create 2018-03-15 16:27
 **/
@Service
public class FaceManagerImpl implements FaceManager {
    protected static final Logger logger = Logger.getLogger(FaceManagerImpl.class);
    @Autowired(required = false)
    private FaceBaseDoMapperExt faceBaseDoMapperExt;
    @Autowired(required = false)
    private FaceCatchDoMapperExt faceCatchDoMapperExt;
    @Autowired
    private FileUploadService fileUploadService;
    @Value("${face.service.url}")
    private String faceServiceUrl;

    @Override
    public void saveFaceInfo(FaceHeadInfoDto faceHeadInfoDto) {
        if (null != faceHeadInfoDto) {
            FaceCatchDo faceCatchDo = new FaceCatchDo();
            faceCatchDo.setTopic(faceHeadInfoDto.getTopic());
            faceCatchDo.setCode(faceHeadInfoDto.getCode());
            faceCatchDo.setCaptureId(String.valueOf(faceHeadInfoDto.getCaptureId()));
            faceCatchDo.setCameraId(String.valueOf(faceHeadInfoDto.getCameraId()));
            faceCatchDo.setTime(new Date());
            try {
                DateUtils.parseDate(faceHeadInfoDto.getTime(), "");
            } catch (ParseException e) {
                logger.error("时间格式化错误", e);
            }
            faceCatchDoMapperExt.insertSelective(faceCatchDo);
            //保存附件
            AttachDo attachDo = fileUploadService.uploadBase64(faceHeadInfoDto.getFaceImage(), "视频抓拍.jpg");
            fileUploadService.updateAttach(String.valueOf(attachDo.getId()), faceCatchDo.getId(), CommConfig.ATTACH_TYPE.FACE_CATCH.name(), "抓拍照片");
            List<FaceLineInfoDto> list = faceHeadInfoDto.getList();
            if (!CollectionUtils.isEmpty(list)) {
                //底库只用保存一个，需要在云从客户端设置一个比较高的阈值
                FaceLineInfoDto faceLineInfoDto = list.get(0);
                FaceBaseDo faceBaseDo = new FaceBaseDo();
                faceBaseDo.setGroupId(faceLineInfoDto.getGroupId());
//                BigDecimal score = new BigDecimal(Float.toString(faceLineInfoDto.getScore()));
                faceBaseDo.setScore(Double.valueOf(faceLineInfoDto.getScore()));
//                BigDecimal threshold = new BigDecimal(Float.toString(faceLineInfoDto.getThreshold()));
                faceBaseDo.setThreshold(Double.valueOf(faceLineInfoDto.getThreshold()));
                faceBaseDo.setCardId(faceLineInfoDto.getCardId());
                faceBaseDo.setName(faceLineInfoDto.getName());
                faceBaseDo.setFaceId(faceLineInfoDto.getFaceId());
                faceBaseDo.setAlertType(faceLineInfoDto.getAlertType());
                //faceId ,ruleId,alertType
                faceBaseDoMapperExt.insertSelective(faceBaseDo);
                attachDo = fileUploadService.uploadBase64(faceHeadInfoDto.getFaceImage(), "视频抓拍底库.jpg");
                fileUploadService.updateAttach(String.valueOf(attachDo.getId()), faceBaseDo.getId(), CommConfig.ATTACH_TYPE.FACE_BASE.name(), "抓拍底库照片");
            }

        }

    }

    @Override
    public PageResult<FaceBaseInfoVo> queryPage(FaceBaseInfoRequest request) {
        PageResult<FaceBaseInfoVo> result = new PageResult<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", FaceConfig.userId);
        jsonObject.put("rowsOfPage", request.getLimit());
        jsonObject.put("currentPage", request.getPage());
        jsonObject.put("date_star", request.getStartTime());
        jsonObject.put("date_end", request.getEndTime());
//        jsonObject.put("", "");
        String res = HttpClientUtil.postBody(faceServiceUrl + FaceConfig.method_warningRecord, jsonObject.toJSONString());
        jsonObject = JSONObject.parseObject(res);
        if ("200".equals(jsonObject.getString("respCode"))) {
            JSONObject data = jsonObject.getJSONObject("data");
            String datas = data.getString("datas");
            result.setCount(data.getIntValue("totalRows"));
            List<FaceBaseInfoVo> list = JSON.parseArray(datas, FaceBaseInfoVo.class);
            for (FaceBaseInfoVo faceBaseInfoVo : list) {
                if ("1".equals(faceBaseInfoVo.getSex())) {
                    faceBaseInfoVo.setSex("man");
                } else {
                    faceBaseInfoVo.setSex("woman");
                }
            }
            result.setData(list);
        }
        return result;
    }
}
