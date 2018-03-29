package com.dsw.iot.service;

import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

/**
 * 储物柜服务
 */
public interface LockerService {
    /**
	 * 分页查询储物柜
	 *
	 * @param request
	 * @return
	 */
    public PageResult<LockerDo> queryPage(LockerResquest request);

    /**
	 * 新增或更新储物柜信息
	 *
	 * @param lockerDo
	 * @return
	 * @throws BizException
	 */
	public void saveLocker(LockerDo lockerDo) throws BizException;

	/**
	 * 根据主键id查询数据
	 *
	 * @param id
	 * @return
	 */
	public LockerDo getLocker(Long id);

	/**
	 * 根据主键id删除数据
	 */
	public void deleteLocker(String ids);

	/**
	 * 查取最大编号
	 */
	public ActionResult<String> getLockerNo(String type);

	/**
	 * 根据柜子类型，获得空闲柜子
	 *
	 * @throws BizException
	 */
	public LockerDo getFreeLockerByType(String type) throws BizException;

	/**
	 * 出入所柜门控制(type="in"入所;type="out"出所 )
	 */
	public ActionResult<String> openLocker(Long id, String type);

	/**
	 * 开柜关柜操作
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	ActionResult<String> openLockerByid(Long id) throws BizException;
}
