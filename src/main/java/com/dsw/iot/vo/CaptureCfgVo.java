package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;
import lombok.Data;

/**
 * 摄像头配置
 *
 * @author huangt
 * @create 2018-03-24 14:44
 **/
@Data
public class CaptureCfgVo extends BaseModel {
    private String ip;
    private int port;
    private int channel;
    private int webPort;
    private int webChannel;
    private String userName;
    private String password;
}
