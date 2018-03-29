package com.dsw.iot.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.FaduConfig;
import com.dsw.iot.dal.TpBlrecordDoMapperExt;
import com.dsw.iot.manager.FdblManager;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.model.TpBlrecordDo;
import com.dsw.iot.model.TpBlrecordDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.RoomRecordService;
import com.dsw.iot.util.*;
import com.dsw.iot.vo.BlContentVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 法度笔录服务类
 *
 * @author huangt
 * @create 2018-03-22 14:28
 **/
@Service
public class FdblManagerImpl implements FdblManager {
    protected static final Logger logger = Logger.getLogger(FdblManagerImpl.class);
    @Autowired(required = false)
    private TpBlrecordDoMapperExt tpBlrecordDoMapperExt;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private RoomRecordService roomRecordService;
    @Autowired
    private PersonRegisterService personRegisterService;
    @Value("${fdbl.service.url}")
    private String serviceUrl;
    @Value("${fdbl.appKey}")
    public String appKey;
    @Value("${fdbl.appSecret}")
    public String appSecret;
    @Value("${fdbl.remote.file.url}")
    private String remoteFileUrl;
    @Value("${fdbl.remote.file.path}")
    private String remotePath;


    @Override
    public ActionResult syncFadu(Long registerId, String bllx) throws BizException {
        PrivilegeInfo pvgInfo = currentUserService.getPvgInfo();
        ActionResult actionResult = new ActionResult();
        TpBlrecordDo tpBlrecordDo = new TpBlrecordDo();
        tpBlrecordDo.setRyId(registerId);
        List<RoomPropertyDo> roomList = roomRecordService.getRoomPropertyByprid(registerId, "0");
        String roomNo = null;
        if (CollectionUtils.isNotEmpty(roomList)) {
            RoomPropertyDo roomPropertyDo = roomList.get(0);
            roomNo = roomPropertyDo.getRoomNo();
        }
        if (null == roomNo) {
            throw new BizException("请确认人员在询/讯问室内");
        }
        tpBlrecordDo.setWhdd(roomNo);
        DomainUtil.setCommonValueForCreate(tpBlrecordDo, pvgInfo);
        tpBlrecordDoMapperExt.insertSelective(tpBlrecordDo);
//        ==================生成笔录记录=====================

//        ==================生成笔录网络文件==================
        JSONObject jsonParam = new JSONObject();
        //账号类型，本系统  5:警号
        jsonParam.put("IdType", 5);
        jsonParam.put("IdValue", pvgInfo.getAccount());
        jsonParam.put("AppKey", appKey);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sf.format(new Date());
        jsonParam.put("Time", time);
        String sign = MD5Util.MD5(appKey + pvgInfo.getAccount() + appSecret + time);
        jsonParam.put("Sign", sign);
        //笔录基本附带信息
        JSONObject tagJson = new JSONObject();
        tagJson.put("outerId", tpBlrecordDo.getId().toString());
        tagJson.put("BLLX", bllx);
        JSONArray ryArray = new JSONArray();
        //组装需要被笔录的人员
        PersonRegisterDo personRegisterDo = personRegisterService.getPersonRegister(registerId);
        JSONObject ryJson = new JSONObject();
        ryJson.put("SFZ", personRegisterDo.getCardNo());
        ryJson.put("XM", personRegisterDo.getName());
        ryJson.put("ZY", personRegisterDo.getProfessionType());
        ryJson.put("GZDW", personRegisterDo.getWorkUnit());
        ryJson.put("LXDH", personRegisterDo.getPhone());
        ryJson.put("HJDXZ", personRegisterDo.getDomicilePlace());
        ryJson.put("XZD", personRegisterDo.getLivingPlace());
        ryArray.add(ryJson);
        tagJson.put("RYList", ryArray);
        //生成文件上传到网络地址
        if (null != tagJson) {
            String fileName = sign + ".txt";
            String remotePath = saveRemote(tagJson.toString(), fileName);
            jsonParam.put("Tag", remotePath);
        }
        actionResult.setContent(FaduConfig.prefix_order + FuncComm.getBase64(jsonParam.toString()));
        return actionResult;
    }

    @Override
    public List<BlContentVo> getBlContentToPdfPath(String roomNo) throws Exception {
        TpBlrecordDoExample example = new TpBlrecordDoExample();
        TpBlrecordDoExample.Criteria criteria = example.createCriteria();
        criteria.andWhddEqualTo(roomNo);
        example.setOrderByClause(" update_time desc");
        List<TpBlrecordDo> tpBlrecordDos = tpBlrecordDoMapperExt.selectByExample(example);
        TpBlrecordDo tpBlrecordDo = null;
        if (CollectionUtils.isNotEmpty(tpBlrecordDos)) {
            tpBlrecordDo = tpBlrecordDos.get(0);
        }
        if (null == tpBlrecordDo) {
            throw new BizException(MessageFormat.format("请确认{0}中有在所人员!", roomNo));
        }
        List<BlContentVo> blContentVos = getBlPdf(tpBlrecordDo.getId());
        Collections.sort(blContentVos, new Comparator<BlContentVo>() {
            @Override
            public int compare(BlContentVo o1, BlContentVo o2) {
                if (DateUtil.formatTime(o1.getKssj(), DateUtil.FORMAT_FULL) < DateUtil.formatTime(o2.getKssj(), DateUtil.FORMAT_FULL)) {
                    return 1;
                } else if (DateUtil.formatTime(o1.getKssj(), DateUtil.FORMAT_FULL) > DateUtil.formatTime(o2.getKssj(), DateUtil.FORMAT_FULL)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        List<BlContentVo> resultList = new ArrayList();
        if (null != blContentVos && blContentVos.size() > 0) {
            resultList.add(blContentVos.get(0));
        }
        return resultList;
    }

    /**
     * 通过人员笔录记录ID 获取笔内容
     */
    private List<BlContentVo> getBlPdf(Long blRecordId) throws Exception {
        List<BlContentVo> blContentVos = new ArrayList<>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", appKey);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", blRecordId);
        data.put("wtsj", "0");
        data.put("isOuterId", "1");
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String dataSecret = aes.encrypt(JSON.toJSONString(data), appSecret);
        params.put("data", dataSecret);
        String bgSig = MD5Util.MD5(appKey + appSecret + dataSecret);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String blHtml = HttpClientUtil.post(serviceUrl + FaduConfig.method_queryBiluByBiluID, params);
        if (null != blHtml) {
            JSONObject jsonObject = JSONObject.parseObject(blHtml);
            JSONObject blObj = jsonObject.getJSONObject("data");
            if (0 == jsonObject.getIntValue("resultCode") && null != blObj) {
                String kssj = blObj.getString("KSSJ");
                String jssj = blObj.getString("JSSJ");
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
            } else {
                throw new BizException(blRecordId + ":法度接口访问失败!");
            }
        }
        return blContentVos;
    }

    @Override
    public List<String> getSigntureFileContent(String blid) throws Exception {
        List<String> list = new ArrayList<>();
        //接口安全接入 秘钥
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", appKey);
        //设置请求参数  查询条件
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("blid", blid);
        //对查询条件aes加密
        AESTool aes = new AESTool();
        String d = aes.encrypt(JSON.toJSONString(data), appSecret);
        params.put("data", d);
        String bgSig = MD5Util.MD5(appKey + appSecret + d);
        params.put("bdSig", bgSig);
        //通过http请求发送查询条件，返回查询结果
        String json = HttpClientUtil.post(serviceUrl + FaduConfig.method_getLatestSigntureFileContent, params);
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
                    String fileName = MD5Util.MD5(content) + "." + "pdf";
                    String fileAddress = saveRemote(bytes, fileName);
                    list.add(fileAddress);
                }
            }
        }
        return list;
    }

    /**
     * 把内容content写的path文件中
     */
    private String saveRemote(String content, String fileName) {
        String today = DateUtil.getToday();
        String outDir = remotePath + "/" + today;
        String remotePath = remoteFileUrl + "/" + today + "/" + fileName;
        BufferedWriter bw = null;
        try {
            File dir = new File(outDir);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outDir + "/" + fileName, true), "GBK"));
            if (content != null) {
                bw.write(content);
            }
        } catch (IOException e) {
            logger.error("物联网同步法度笔录秘钥生成失败！", e);
        } finally {
            if (bw != null) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    logger.error("物联网同步法度笔录秘钥生成失败！", e);
                }
            }
        }
        return remotePath;
    }

    /**
     * 根据byte数组，生成文件
     */
    public String saveRemote(byte[] bfile, String fileName) {
        String today = DateUtil.getToday();
        String outDir = remotePath + "/" + today;
        String remotePath = remoteFileUrl + "/" + today + "/" + fileName;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(outDir);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(outDir + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            logger.error("物联网同步法度笔录PDF失败！", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("物联网同步法度笔录PDF失败！", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("物联网同步法度笔录PDF失败！", e);
                }
            }
        }
        return remotePath;
    }
}
