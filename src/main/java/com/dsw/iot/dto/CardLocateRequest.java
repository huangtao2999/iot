package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class CardLocateRequest extends BaseDto{
	   private Long id;

	    /**
	     * 卡芯片编码id
	     */
	    private String cardId;

	    /**
	     * 人员注册id（register_id）
	     */
	    private Long registerId;

	    /**
	     * 定位标签id
	     */
	    private Integer tagId;

	    /**
	     * 定位标签euid
	     */
	    private String tagEuid;

	    /**
	     * 坐标X值(像素)
	     */
	    private Long localX;

	    /**
	     * 坐标Y值(像素)
	     */
	    private Long localY;

	    /**
	     * 坐标Z值(像素)
	     */
	    private Long localZ;

	    /**
	     * 位置生成时间(毫秒)
	     */
	    private Date addTime;

	    /**
	     * Tag绑定的用户名
	     */
	    private String tagalias;

	    /**
	     * 表示TAG绑定用户的性别
	     */
	    private String taggender;

	    /**
	     * 是否历史记录，默认否（字典：0-否；1-是）【否：实时记录，用于同步显示定位信息；是：历史记录，用于重现历史轨迹，人员出办案区后，将相关记录更新为1】
	     */
	    private String isHistory;

	    /**
	     * 是否删除（逻辑删除，查询时系统自动拼sql）
	     */
	    private String isDeleted;

	    /**
	     * 创建时间
	     */
	    private Date createTime;

	    /**
	     * 描述
	     */
	    private String remark;
}
