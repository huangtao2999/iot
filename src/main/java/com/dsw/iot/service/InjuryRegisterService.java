package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.InjuryRegisterRequest;

public interface InjuryRegisterService {

	/**
	 * 保存伤情信息
	 *
	 * @param injuryRegisterList
	 * @param registerId
	 * @return
	 */
	List<InjuryRegisterRequest> saveInjuryInfo(List<InjuryRegisterRequest> injuryRegisterList, Long registerId);

	/**
	 * 查询人员的伤情信息
	 */
	List<InjuryRegisterRequest> getInjuryInfoByRid(Long registerId);
}
