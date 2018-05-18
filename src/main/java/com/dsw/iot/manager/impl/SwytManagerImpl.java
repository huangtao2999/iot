package com.dsw.iot.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.dto.DmDataDockDto;
import com.dsw.iot.dto.DmDataPopulationDto;
import com.dsw.iot.manager.SwytManager;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.HttpClientUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 警综对接服务类
 *
 * @author huangt
 * @create 2018/4/26 9:06
 **/
@Service
public class SwytManagerImpl implements SwytManager {

    private static final Logger logger = Logger.getLogger(SwytManagerImpl.class);

    //常驻人口的url
    @Value("${swyt.server.url}")
    private String swytServiceUrl;
    //常驻人口约定的参数
    @Value("${swyt.security.code}")
    private String swytSecurityCode;
    //常驻人口约定的参数
    @Value("${swyt.request.source}")
    private String requestSource;
    //授权身份证号码
    @Value("${swyt.id.card}")
    private String idcard;
    
    @Autowired
    private CurrentUserService currentUserService;


    @Override
    public Object getPeopleByCardNo(String cardNo, String policeNo) throws BizException {
        String result = "";
        try {
            Map<String, Object> data = new HashMap<>();
            //当前登陆人身份证号码
            policeNo = currentUserService.getPvgInfo().getIdCard();
            if (StringUtils.isEmpty(policeNo)) {
                policeNo = "420624197707190017";
            }
            data.put("IDCard", idcard);
            data.put("securityCode", swytSecurityCode);
            data.put("requestSource", requestSource);
            Map<String, String> params = new HashMap<>();
            params.put("GMSFHM", cardNo);
            data.put("params", params);
            StringBuilder sb = new StringBuilder(swytServiceUrl);
            sb.append("?data=").append(URLEncoder.encode(JSON.toJSONString(data), "UTF-8"));
            String url = sb.toString();
            result = HttpClientUtil.doGet(url);
            logger.info(url);
        } catch (Exception e) {
            logger.warn("警综对接服务获取人员信息失败", e);
            throw new BizException("常驻人口库信息获取失败!");
//    		return null;
        }
       
        logger.info("警综对接服务获取人员信息成功"+result);
        
        if (null != result) {
    		JSONObject obj = JSONObject.parseObject(result);
            if (null != obj) {
        		JSONArray ja=JSONArray.parseArray(obj.get("entity").toString());
                if (null != ja) {
            		DmDataPopulationDto dto  = JSONObject.parseObject(ja.get(0).toString(), DmDataPopulationDto.class);
                    if (null != dto) {
                        if ("1".equals(dto.getXB())) {
                            dto.setXB("man");
                        } else if ("2".equals(dto.getXB())) {
                            dto.setXB("woman");
                        }
                        return dto;
                    }
                }
            }
        }
        return null;
    }
}
