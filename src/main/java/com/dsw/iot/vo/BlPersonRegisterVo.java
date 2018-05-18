package com.dsw.iot.vo;

import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomRecordDo;
import com.dsw.iot.model.UserDo;

import lombok.Data;

@Data
public class BlPersonRegisterVo {
	private PersonRegisterDo personRegisterDo;
	private String personRegisterImg;
	private String registerSexName;
	private String peopleType;
	private String registerInReason;
	private String nationality;
	private RoomRecordDo roomRecordDo;
	private UserDo userDo;
	private String cardNo;
	private String cardType;
}
