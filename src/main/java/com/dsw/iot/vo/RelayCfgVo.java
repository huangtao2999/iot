package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;
import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author huangt
 * @create 2018-03-26 11:12
 **/
@Data
public class RelayCfgVo extends BaseModel {

    private String ip;
    private int port;
    private int channel;
}
