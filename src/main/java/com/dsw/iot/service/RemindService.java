package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.RemindRequest;
import com.dsw.iot.model.RemindDo;
import com.dsw.iot.util.PageResult;
public interface RemindService {

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id
	 * @return
	 */
	RemindDo getRemind(Long id);

	/**
	 * 通过业务id和业务类型查询代办信息
	 *
	 * @param id
	 * @return
	 */
	List<RemindDo> getRemindByTaskId(Long taskId, String taskBelong);

	/**
	 * 保存记录
	 *
	 * @param AlarmManageDo
	 */
	void saveRemind(RemindDo remindDo);

	/**
	 * 查询所有待办列表
	 * @param userId
	 * @return
	 */
	List<RemindDo> listRemind(Long roleId);

	/**
	 * 多个roleid，查询待办
	 *
	 * @param roleId
	 * @return
	 */
	List<RemindDo> listRemindByIds(String roleIds);
	
	/**
	 * 分页查询所有代办任务
	 * @param roleIds
	 * @return
	 */
	PageResult<RemindDo> queryPage(RemindRequest remindRequest);
}
