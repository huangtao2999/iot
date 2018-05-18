package com.dsw.iot.manager.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.RtlsConfig;
import com.dsw.iot.manager.RtlsManager;
import com.dsw.iot.util.HttpClientUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 智物达对接服务类
 *
 * @author huangt
 * @create 2018-04-02 16:14
 **/
@Service
public class RtlsManagerImpl implements RtlsManager {
    protected static final Logger logger = Logger.getLogger(RtlsManagerImpl.class);

    @Value("${person.position.url}")
    private String url;

    @Override
    public boolean bindTag(String euid, String name, Integer gender) {
        Map<String, Object> params = new HashMap<>();
        params.put("action", "add.user");
        params.put("euid", euid);
        params.put("userGroupId", 1);
        params.put("name", name);
        params.put("gender", gender);
//        params.put("size", "");
        params.put("color", "#6395530");
        String res = HttpClientUtil.doPost(url + RtlsConfig.method_user, params, "UTF-8");
        JSONObject obj = JSONObject.parseObject(res);
        if (null != obj && "success".equals(obj.getString("result"))) {
            return true;
        } else {
            logger.error(euid + "  手环标签绑定失败!" + res);
        }
        return false;
    }

    @Override
    public boolean unBindTag(String euid) {
        Map<String, Object> params = new HashMap<>();
        //查询
        params.put("action", "get.users");
        params.put("startNum", 0);
        params.put("scaleNum", 40);
        params.put("userGroupId", 1);
        params.put("status", 2);
        params.put("searchType", "1");
        params.put("keyword", "");
        String res = HttpClientUtil.doGet(url + RtlsConfig.method_user, params, "UTF-8");
        JSONObject obj = JSONObject.parseObject(res);
        if (null != obj && "success".equals(obj.getString("result"))) {
            JSONArray users = obj.getJSONArray("users");
            if (null != users && users.size() > 0) {
                for (Object userObj : users) {
                    JSONObject user = (JSONObject) userObj;
                    Integer userId = user.getIntValue("userId");
                    String tagEuid = user.getString("euid");
                    if (euid.equals(tagEuid)) {
                        //删除
                        params = new HashMap<>();
                        params.put("action", "del.user");
                        params.put("userId", userId);
                        res = HttpClientUtil.doGet(url + RtlsConfig.method_user, params, "UTF-8");
                        obj = JSONObject.parseObject(res);
                        if (null != obj && "success".equals(obj.getString("result"))) {
                            return true;
                        } else {
                            logger.error(tagEuid + "  手环标签解除绑定失败!" + res);
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }
}
