package com.dsw.iot.vo;

import java.util.List;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.InjuryRegisterRequest;
import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.util.BaseModel;

import lombok.Data;

@Data
public class PersonPositionVo extends BaseModel{

	/**
	 * ip
	 */
	String  ip;

	/**
	 * port
	 */
	String port;
}
