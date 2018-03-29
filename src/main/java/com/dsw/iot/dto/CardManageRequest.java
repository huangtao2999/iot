package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class CardManageRequest extends BaseDto{
	private Long id;

	/**
	 * 手环/胸牌MAC编码（芯片读取码）
	 */
	private String cardId;

	/**
	 * 手环/胸牌编号（标签号）
	 */
	private String cardNo;

	/**
	 * 定位标签id
	 */
	private Integer tagId;

	/**
	 * 定位标签euid
	 */
	private String tagEuid;

	/**
	 * 设备类型：1-胸牌-民警；2-手环-嫌疑人
	 */
	private String type;

	/**
	 * 是否被激活,激活则记录轨迹,默认0未激活,手环被回收时重置为0,1-激活
	 */
	private String isActivate;

	private String isDeleted;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;

	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 排序参数
	 */
	private String orderByClause;
}
