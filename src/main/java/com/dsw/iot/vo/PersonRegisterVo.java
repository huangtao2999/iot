package com.dsw.iot.vo;

import java.util.List;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.InjuryRegisterRequest;
import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.model.PersonTraceDo;
import com.dsw.iot.util.BaseModel;

import lombok.Data;

@Data
public class PersonRegisterVo extends BaseModel{

	/**
	 * 人员信息
	 */
	PersonRegisterRequest personInfo;

	/**
	 * 抓获信息
	 */
	CatchInfoDo catchInfo;

	/**
	 * 陪同人信息
	 */
	List<PersonRelatedDo> relatedInfo;

	/**
	 * 伤情信息
	 */
	List<InjuryRegisterRequest> injuryInfo;

	/**
	 * 物品信息
	 */
	List<GoodsRegisterRequest> goodsInfo;

	/**
	 * 出办案区审核信息
	 */
	List<OutConfirmDo> outInfo;

	/**
	 * 人员轨迹信息
	 */
	List<PersonTraceDo> traceInfo;

}
