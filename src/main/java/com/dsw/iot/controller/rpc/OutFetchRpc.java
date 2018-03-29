package com.dsw.iot.controller.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.OutClickConfirmRequest;
import com.dsw.iot.model.GoodsRegisterDo;
import com.dsw.iot.service.GoodsRegisterService;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.service.OutFetchService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.PersonRegisterVo;

/**
 * 出所取物
 *
 * @author zc
 *
 */
@RestController
@RequestMapping("/OutFetch")
public class OutFetchRpc {

	@Autowired
	PersonRegisterService personRegisterService;
	@Autowired
	GoodsRegisterService goodsRegisterService;
	@Autowired
	LockerService lockerService;
	@Autowired
	OutFetchService outFetchService;

	/**
	 * 通过手环编号获取关押人员信息
	 *
	 * @param braceletNo
	 *            手环编号
	 * @return
	 */
	@RequestMapping("/getPersonRegister")
	public PersonRegisterVo getFetchDetail(String braceletNo) {
		return personRegisterService.getPersonRegister(braceletNo);
	}

	/**
	 * 犯人的出所取物信息
	 * registerId 犯人id
	 */
	@RequestMapping("/getGoodsRegisterlist")
	public List<GoodsRegisterDo> getGoodsRegisterlist(Long registerId) {
		return goodsRegisterService.getGoodsRegisterByrid(registerId);
	}

	/**
	 * 储物柜控制
	 * lockerId为储物罐id
	 */
	@RequestMapping("/getLockerOpen")
	public void getLockerOpen(Long lockerId) {
		 outFetchService.getLockerOpen(lockerId);
	}

	/**
	 * 临时出办案区 registerId 注册人员id cardId 卡芯片编码id
	 *
	 * @throws BizException
	 */
	@RequestMapping("/outTempArea")
	public ActionResult<String> outTempArea(Long registerId, String cardId, Long outId) throws BizException {
		ActionResult<String> result = outFetchService.outTempArea(registerId, cardId, outId);
		return result;
	}

	/**
	 * 确认出办案区 registerId 注册人员id cardId 卡芯片编码id
	 *
	 * @throws BizException
	 */
	@RequestMapping("/outArea")
	public ActionResult<String> outArea(Long registerId, String cardId, Long outId) throws BizException {
		ActionResult<String> result = outFetchService.outArea(registerId, cardId, outId);
		return result;
	}

	/**
	 * 更新物品状态
	 * ids 物品主键id
	 * status 物品状态
	 */
	@RequestMapping("/updateGoodsStatus")
	public void updateGoodsStatus(String ids,String status) {
		goodsRegisterService.updateGoodsStatus(ids, status);
	}

	/**
	 * 出办案区取物，点击签名确认按钮
	 */
	@RequestMapping("/clickConfirm")
	public ActionResult<String> clickConfirm(@RequestBody OutClickConfirmRequest clickConfirmRequest) {
		ActionResult<String> result = outFetchService.clickConfirm(clickConfirmRequest);
		return result;
	}

}
