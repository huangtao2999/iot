package com.dsw.iot.service;

import com.dsw.iot.dto.CatchInfoRequest;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.util.PageResult;

public interface CatchInfoService {
	/**
	 * 分页查询
	 *
	 * @param param
	 * @return
	 */
	public PageResult<CatchInfoDo> queryPage(CatchInfoRequest catchInfoRequest);

	/**
	 * 查单条记录
	 *
	 * @param id
	 * @return
	 */
	CatchInfoDo getCatchInfo(Long id);

	/**
	 * 新增/编辑抓获信息
	 *
	 * @param record
	 * @param loginUserDo
	 * @return
	 */
	CatchInfoDo saveCatchInfo(CatchInfoDo catchInfoDo);

	/**
	 * 删除角色
	 *
	 * @param param
	 * @return
	 */
	void removeCatchInfo(CatchInfoDo catchInfoDo);

	/**
	 * 通过人员id查询抓获信息
	 * 
	 * @param id
	 * @return
	 */
	CatchInfoDo getCatchInfoByRid(Long id);
}
