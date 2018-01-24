package com.dsw.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.FaduConfig;
import com.dsw.iot.dal.TpUserDoMapperExt;
import com.dsw.iot.model.TpUserDo;
import com.dsw.iot.model.TpUserDoExample;
import com.dsw.iot.service.LoginService;
import com.dsw.iot.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private FileUpload fileUpload;
    @Autowired(required = false)
    private TpUserDoMapperExt tpUserDoMapperExt;

    @Override
    public ActionResult syncFadu(String param) {
        ActionResult actionResult = new ActionResult();
        JSONObject jsonObject = JSONObject.parseObject(param);
        String idType = jsonObject.get("idType").toString();
        String idValue = jsonObject.get("idValue").toString();
        String anInfo = jsonObject.get("ajInfo").toString();

        JSONObject json = new JSONObject();
        json.put("IdType", idType);
        json.put("IdValue", idValue);

        json.put("AppKey", FaduConfig.appKey);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = dateTimeFormatter.format(localDateTime);
        json.put("Time", time);
        String sign = MD5Util.MD5(FaduConfig.appKey + idValue + FaduConfig.appSecret + time);
        json.put("Sign", sign);
        if (StringUtils.isNotEmpty(anInfo)) {
            String fileName = sign + ".txt";
            String gxfileDir = fileUpload.saveRemote(anInfo, fileName);
            json.put("Tag", gxfileDir);
        }
        actionResult.setContent(FaduConfig.prefix_order + FuncComm.getBase64(json.toString()));
        return actionResult;
    }

    @Override
    public TpUserDo login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(userName)) {
            throw new BizException("用户名为空!");
        }
        if (StringUtils.isEmpty(passWord)) {
            throw new BizException("密码为空!");
        }

        passWord = MD5Util.MD5(passWord);
        TpUserDoExample example = new TpUserDoExample();
        example.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(passWord);
        List<TpUserDo> list = tpUserDoMapperExt.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("用户名或密码错误!");
        }
        TpUserDo tpUserDo = list.get(0);
        CookieUtil.addUserToCookie(request, response, tpUserDo);
        return tpUserDo;
    }
}
