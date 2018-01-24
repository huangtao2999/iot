package com.dsw.iot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.FaduConfig;
import com.dsw.iot.dal.TpBaqryUserDoMapperExt;
import com.dsw.iot.model.TpBaqryUserDo;
import com.dsw.iot.model.TpBaqryUserDoExample;
import com.dsw.iot.service.FaduService;
import com.dsw.iot.util.*;
import com.dsw.iot.vo.BlContentVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class FaduServiceImpl implements FaduService {
    protected static final Logger logger = Logger.getLogger(FaduServiceImpl.class);
    @Value("${bl.service.url}")
    private String FADU_URL;
    @Autowired
    private FileUpload fileUpload;
    @Autowired(required = false)
    private TpBaqryUserDoMapperExt tpBaqryUserDoMapperExt;

    @Override
    public ActionResult queryBiluListByRyIdcard() throws Exception {
        ActionResult actionResult = new ActionResult();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idCard", "513435199706032115");
        data.put("kssj", "20171229000000");
        data.put("start", "1");
        data.put("end", "10");
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String json = HttpClientUtil.post(FADU_URL + FaduConfig.method_queryBiluListByRyIdcard, params);
        System.out.println(json);
        actionResult.setContent(json);
        return actionResult;
    }

    @Override
    public BlContentVo queryBiluByBiluID(String blId, String isOuterId) throws Exception {
        BlContentVo blContentVo = new BlContentVo();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", blId);
        data.put("wtsj", "0");
        data.put("isOuterId", isOuterId);
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String html = HttpClientUtil.post(FADU_URL + FaduConfig.method_queryBiluByBiluID, params);
        JSONObject jsonObject = JSONObject.parseObject(html);
        if (null != jsonObject && jsonObject.getIntValue("resultCode") == 0) {
            JSONObject jsonData = jsonObject.getJSONObject("data");
            String kssj = jsonData.getString("KSSJ");
            String jssj = jsonData.getString("JSSJ");
            String wdnr = jsonData.getString("WDNR");
            String blnr = aes.decrypt(wdnr, FaduConfig.appSecret);
            blContentVo.setKssj(kssj);
            blContentVo.setJssj(jssj);
            blContentVo.setBlnr(blnr);
        }
        return blContentVo;
    }

    @Override
    public List<String> getSigntureFileContent(String blid) throws Exception {
        List<String> list = new ArrayList<>();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", blid);
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String json = HttpClientUtil.post(FADU_URL + FaduConfig.method_getLatestSigntureFileContent, params);
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (null != jsonObject && 1 == jsonObject.getIntValue("resultCode")) {
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            String content = null;
            String name = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                content = jsonObject2.getString("content");
                name = jsonObject2.getString("fileName");
                if (null != content) {
                    byte[] bytes = FuncComm.base64ToByte(content);
                    String fileName = MD5Util.MD5(name) + "." + "pdf";
                    String fileAddress = fileUpload.saveRemote(bytes, fileName);
                    list.add(fileAddress);
                }
            }
        }
        return list;
    }

    /**
     * 通过人员登记ID 获取笔内容
     */
    private List<BlContentVo> getBlPdf(String baqryId) throws Exception {
        List<BlContentVo> blContentVos = new ArrayList<>();
        //通过身份证号和入办案区时间获取当前的一条笔录
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", FaduConfig.appKey);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("ajid", "");
        data.put("ajbh", baqryId);
        data.put("start", "1");
        data.put("end", "10");
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String dataSecret = aes.encrypt(JSON.toJSONString(data), FaduConfig.appSecret);
        params.put("data", dataSecret);
        String bgSig = MD5Util.MD5(FaduConfig.appKey + FaduConfig.appSecret + dataSecret);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String blHtml = HttpClientUtil.post(FADU_URL + FaduConfig.method_queryBiluListByAjid, params);
        if (null != blHtml) {
            JSONObject jsonObject = JSONObject.parseObject(blHtml);
            if (0 == jsonObject.getIntValue("resultCode")) {
                JSONArray blArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < blArray.size(); i++) {
                    JSONObject blObj = (JSONObject) blArray.get(i);
                    String kssj = blObj.getString("kssj");
                    String jssj = blObj.getString("jssj");
                    String blid = blObj.getString("id");
                    String bldxxm = blObj.getString("bldxxm");
                    BlContentVo blContentVo = new BlContentVo();
                    blContentVo.setKssj(DateUtil.formatFull(kssj));
                    blContentVo.setJssj(DateUtil.formatFull(jssj));
                    blContentVo.setBldxxm(bldxxm);
                    String pdfFile = blObj.getString("pdf_file");
                    if ("1".equals(pdfFile)) {
                        List<String> filePaths = getSigntureFileContent(blid);
                        blContentVo.setFilePath(filePaths);
                    } else {
                        logger.warn(blid + ":该笔录没有PDF文书!");
                    }
                    blContentVos.add(blContentVo);
                }
            }
        }
        return blContentVos;
    }

    @Override
    public List<BlContentVo> getBlContentToPdfPath(String braceletNumber) throws Exception {
        TpBaqryUserDo tpBaqryUserDo = tpBaqryUserDoMapperExt.selectNowUserByRoomNo(braceletNumber);
        if (null == tpBaqryUserDo) {
            throw new BizException(MessageFormat.format("请确认询问室{0}中有在所人员!", braceletNumber));
        }
        List<BlContentVo> blContentVos = getBlPdf(String.valueOf(tpBaqryUserDo.getId()));
        return blContentVos;
    }

    @Override
    public List<BlContentVo> getBlContentToJpgPath(String alertNumber) throws Exception {
        List<BlContentVo> blContentVosAll = new ArrayList<>();
        TpBaqryUserDoExample example = new TpBaqryUserDoExample();
        example.createCriteria().andAlertNumberEqualTo(alertNumber);
        List<TpBaqryUserDo> list = tpBaqryUserDoMapperExt.selectByExample(example);
        //同一个警情编号，对应多个嫌疑人
        for (TpBaqryUserDo tpBaqryUserDo : list) {
            List<BlContentVo> blContentVos = getBlPdf(String.valueOf(tpBaqryUserDo.getId()));
            List<String> imagePaths = null;
            for (BlContentVo blContentVo : blContentVos) {
                List<String> filePaths = blContentVo.getFilePath();
                imagePaths = new ArrayList<>();
                for (String filePath : filePaths) {
                    imagePaths.addAll(fileUpload.pdfToImage(filePath));
                }
                blContentVo.setFilePath(imagePaths);
            }
            blContentVosAll.addAll(blContentVos);
        }
        return blContentVosAll;
    }
}
