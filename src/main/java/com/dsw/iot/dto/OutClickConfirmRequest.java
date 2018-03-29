package com.dsw.iot.dto;

import java.util.List;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class OutClickConfirmRequest extends BaseDto{
	/**
	 * 入区物品拍照
	 */
	List<GoodsRegisterRequest> inGoodsInfo;
	/**
	 * 出区物品拍照
	 */
	List<GoodsRegisterRequest> outGoodsInfo;
}
