package com.dsw.iot.util;

import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.model.UserDo;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具
 *
 * @author huangt
 * @create 2018-01-16 22:07
 **/
public class CookieUtil {
    protected static final Logger logger = Logger.getLogger(CookieUtil.class);

    public static void addUserToCookie(HttpServletRequest request, HttpServletResponse response, UserDo userDo) {
        String userJson = JSONObject.toJSONString(userDo);
        Cookie cookie = null;
        try {
            cookie = new Cookie(CommConfig.GUANKONG_USER, AESTool.encrypt(userJson, CommConfig.GUANGKONG_KEY));
        } catch (Exception e) {
            logger.error("cookie添加登录用户失败:" + userJson, e);
        }
        cookie.setPath("/");
        cookie.setMaxAge(CommConfig.COOKIE_AGE);
        response.addCookie(cookie);
    }

    public static UserDo getUserFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (CommConfig.GUANKONG_USER.equals(cookie.getName())) {
                    try {
                        String userJson = AESTool.decrypt(cookie.getValue(), CommConfig.GUANGKONG_KEY);
                        UserDo userDo = JSONObject.parseObject(userJson, UserDo.class);
                        return userDo;
                    } catch (Exception e) {
                        logger.error("cookie用户解密失败:" + cookie.getValue());
                    }
                }
            }
        }
        return null;
    }

    public static void removeCookie(String key, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    try {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    } catch (Exception e) {
                        logger.error("cookie删除失败:" + cookie.getName());
                    }
                }
            }
        }
    }

}
