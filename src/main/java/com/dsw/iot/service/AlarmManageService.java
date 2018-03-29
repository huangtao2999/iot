package com.dsw.iot.service;

import com.dsw.iot.dto.AlarmManageRequest;
import com.dsw.iot.model.AlarmManageDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.PageResult;

public interface AlarmManageService {
    /**
     * 分页查询预警信息
     *
     * @param alarmManageRequest
     * @return
     */
    PageResult<AlarmManageDo> queryPage(AlarmManageRequest alarmManageRequest);

    /**
     * 通过主键查询单条记录
     *
     * @param id
     * @return
     */
    AlarmManageDo getAlarmManage(Long id);

    /**
     * 保存记录
     *
     * @param alarmManageDo
     */
    void saveAlarmManage(AlarmManageDo alarmManageDo);

    /**
     * 删除记录
     *
     * @param ids
     */
    void deleteAlarmManage(String ids);

    /**
     * 关闭预警
     */
    ActionResult<String> closeAlarmManage();

    /**
     * 打开预警警报
     */
    ActionResult<String> openAlarmManage();
    
    /**
     * 查询报警器的状态
     * @return int
     */
    ActionResult<String> checkAlarmManage();
}
