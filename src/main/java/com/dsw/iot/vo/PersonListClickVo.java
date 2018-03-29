package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class PersonListClickVo extends BaseModel {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 人员状态
     */
    private String personStatus;

    /**
     * 要展示到图片旁边的信息列表
     */
    List<PersonListBaseShowVo> showInfo;

    /**
     * 人员照片
     */
    private String photoUrl;
    /**
     * 身份证照片
     */
    private String cardImgIds;


    /**
     * 可点击的按钮名
     */
    private String[] btns;

    /**
     * 已经走过的流程
     */
    List<PersonListBaseShowVo> doTask;
}
