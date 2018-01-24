package com.dsw.iot.vo;

import java.util.List;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

/**
 * 笔录对象
 */

@Data
public class BlContentVo extends BaseModel {

    private String kssj;

    private String jssj;

    private List<String> filePath;

    private String fileName;

    private String blnr;
    //笔录对象姓名
    private String bldxxm;

}
