package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class GoodsRegisterRequest extends BaseDto {

	private Long id;
	private Long registerId;
	/**
	 * 出所审核表id
	 */
	private Long outId;
	private String status;

	/**
	 * 物品名
	 */
    private String goodsName;
    /**
     * 物品数量
     */
    private String goodsNum;
    /**
     * 物品描述
     */
    private String goodsDesc;

    /**
     * 物品照片对象集合，逗号分隔，要绑定的附件ids，类型：GOODS_REGISTER_IMGS
     */
    private String sourceIds;

    /**
     * 要解除绑定的伤情信息
     */
    private String removeIds;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 取物照片ids，类型：GOODS_REGISTER_IMGS
     */
    private String receiveIds;

}
