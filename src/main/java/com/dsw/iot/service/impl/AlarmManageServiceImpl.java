package com.dsw.iot.service.impl;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.AlarmManageDoMapperExt;
import com.dsw.iot.dto.AlarmManageRequest;
import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.model.AlarmManageDo;
import com.dsw.iot.model.AlarmManageDoExample;
import com.dsw.iot.service.AlarmManageService;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AlarmManageServiceImpl implements AlarmManageService {
    @Autowired(required = false)
    AlarmManageDoMapperExt alarmManageDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    LogService logService;
    @Autowired
    private RelayManager relayManager;

    @Value("${alarm.relay.ip}")
    private String ip;
    @Value("${alarm.relay.port}")
    private int port;
    @Value("${alarm.relay.index}")
    private int index;

    /**
     * 根据筛选条件查询信息分页列表
     */
    @Override
    public PageResult<AlarmManageDo> queryPage(AlarmManageRequest alarmManageRequest) {
        PageResult<AlarmManageDo> pageResult = new PageResult<>();
        AlarmManageDoExample example = new AlarmManageDoExample();
        AlarmManageDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());

        //预警开始结束时间区间
        if (null != alarmManageRequest.getAlarmStartTime()) {
            criteria.andAlarmTimeGreaterThanOrEqualTo(alarmManageRequest.getAlarmStartTime());
        }
        if (null != alarmManageRequest.getAlarmEndTime()) {
            criteria.andAlarmTimeLessThanOrEqualTo(alarmManageRequest.getAlarmEndTime());
        }
        // 触发人姓名
        if (StringUtils.isNotBlank(alarmManageRequest.getActivePerson())) {
            criteria.andActivePersonLike("%" + alarmManageRequest.getActivePerson() + "%");
        }
        // 处理方式
        if (StringUtils.isNotBlank(alarmManageRequest.getHandleMethod())) {
            criteria.andHandleMethodEqualTo(alarmManageRequest.getHandleMethod());
        }
        // 排序字段
        if (StringUtils.isBlank(alarmManageRequest.getOrderByClause())) {
            example.setOrderByClause("create_time desc");
        } else {
            example.setOrderByClause(alarmManageRequest.getOrderByClause());
        }
        int count = alarmManageDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(alarmManageRequest.getPage(), alarmManageRequest.getLimit(), count);
        example.setPageDto(pageDto);
        List<AlarmManageDo> list = alarmManageDoMapperExt.selectByExample(example);
        pageResult.setCount(count);
        pageResult.setData(list);
        return pageResult;
    }

    /**
     * 根据主键id查询预警信息
     */
    @Override
    public AlarmManageDo getAlarmManage(Long id) {
        return alarmManageDoMapperExt.selectByPrimaryKey(id);
    }

    /*
     * 保存预警信息
     */
    @Override
    public void saveAlarmManage(AlarmManageDo alarmManageDo) {
        if (null == alarmManageDo.getId()) {
            // add
            alarmManageDo.setAlarmTime(new Date());
            DomainUtil.setCommonValueForCreate(alarmManageDo, currentUserService.getPvgInfo());
            alarmManageDoMapperExt.insertSelective(alarmManageDo);
        } else {
            // edit
            DomainUtil.setCommonValueForUpdate(alarmManageDo, currentUserService.getPvgInfo());
            alarmManageDoMapperExt.updateByPrimaryKeySelective(alarmManageDo);
        }
    }

    @Override
    @Transactional
    public void deleteAlarmManage(String ids) {
        if (!StringUtils.isEmpty(ids)) { // 删除多个id
            String[] idArr = ids.split(",");// 分解逗号拼接字符串
            for (String id : idArr) {
                if (!StringUtils.isEmpty(id)) {
                    AlarmManageDo alarmManageDo = new AlarmManageDo();
                    alarmManageDo.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForDelete(alarmManageDo, currentUserService.getPvgInfo());
                    alarmManageDoMapperExt.deleteByPrimaryKey(alarmManageDo);
                }
            }
        }
    }

    /**
     * 关闭预警
     */
    @Override
    public ActionResult<String> closeAlarmManage() {
        ActionResult<String> result = relayManager.closeV2(ip, port, index);
        return result;
    }

    @Override
    public ActionResult<String> openAlarmManage() {
        ActionResult<String> result = relayManager.openV2(ip, port, index);
        return result;
    }

    @Override
    public ActionResult<String> checkAlarmManage() {
        // TODO Auto-generated method stub
        ActionResult<String> result = relayManager.getStatus(ip, port, index);
        return result;
    }


}
