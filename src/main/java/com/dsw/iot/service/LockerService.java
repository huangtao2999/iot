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
     */
    public PageResult<LockerDo> queryPage(LockerResquest request);

    /**
     * 新增或跟新储物柜信息
     */
    public ActionResult<String> addOrUpdate(LockerDo lockerDo) throws BizException;

	/**
	 * 根据主键id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public LockerDo selectByPrimaryKey(Long id);

	/**
	 * 根据主键id删除数据
	 */
	public ActionResult<String> deleteLocker(String ids);

	/**
	 * 查取最大编号
	 */
	public ActionResult<String> selectLockerNo(String type);
}
