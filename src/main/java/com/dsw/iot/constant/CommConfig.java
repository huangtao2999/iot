package com.dsw.iot.constant;

import sun.applet.Main;

import lombok.Data;

public class CommConfig {
    //系统目录分隔符
    public static final String SEPARATOR = System.getProperty("file.separator");
    //cookie存在客户端的key
    public static final String GUANKONG_USER = "GUANKONG_USER";
    //cookie 生命周期
    public static final int COOKIE_AGE = 8 * 60 * 60 * 1000;
    //guankong系统加密key
    public static final String GUANGKONG_KEY = "3c56602233254dd4be6db5d9b7f9a23a";
}
