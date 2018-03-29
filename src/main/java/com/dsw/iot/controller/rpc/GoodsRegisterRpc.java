package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.model.GoodsRegisterDo;
import com.dsw.iot.service.GoodsRegisterService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/GoodsRegister")
public class GoodsRegisterRpc {

	@Autowired
	GoodsRegisterService goodsRegisterService;

	/**
	 * 分页查询
	 *
	 * @param AlarmManageRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<GoodsRegisterDo> queryPage(GoodsRegisterRequest goodsRegisterRequest) {
		PageResult<GoodsRegisterDo> pageResult = goodsRegisterService.queryPage(goodsRegisterRequest);
		return pageResult;
	}

	/**
	 * 分页查询带图片的物品信息
	 *
	 * @param AlarmManageRequest
	 * @return
	 */
	@RequestMapping("/queryPhotoPage")
	public PageResult<GoodsRegisterRequest> queryPhotoPage(GoodsRegisterRequest param) {
		return goodsRegisterService.queryPhotoPage(param);
	}
}
