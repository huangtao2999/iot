package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.PersonTraceDoMapperExt;
import com.dsw.iot.dto.PersonTraceRequest;
import com.dsw.iot.model.PersonTraceDo;
import com.dsw.iot.model.PersonTraceDoExample;
import com.dsw.iot.service.PersonTraceService;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class PersonTraceServiceImpl implements PersonTraceService {

    @Autowired
    PersonTraceDoMapperExt personTraceDoMapperExt;

    /**
     * 分页查询物品信息主表
     */
    @Override
    public PageResult<PersonTraceDo> queryPage(PersonTraceRequest param) {
        // 分页返回集合
        PageResult<PersonTraceDo> pageResult = new PageResult<>();
        // 查询条件的容器
        PersonTraceDoExample example = new PersonTraceDoExample();
        // 新建查询条件
        PersonTraceDoExample.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 人员id
        if (param.getRegisterId() != null) {
            criteria.andRegisterIdEqualTo(param.getRegisterId());
        }
        // 姓名
        if (StringUtils.isNotBlank(param.getPersonName())) {
            criteria.andPersonNameEqualTo(param.getPersonName());
        }
        // 时间
        if (null != param.getStartTime()) {
            criteria.andCreateTimeGreaterThanOrEqualTo(param.getStartTime());
        }
        if (null != param.getEndTime()) {
            criteria.andCreateTimeLessThanOrEqualTo(param.getEndTime());
        }
        example.setOrderByClause(" create_time desc");
        // 分页
        int count = personTraceDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(param.getPage(), param.getLimit(), count);
        example.setPageDto(pageDto);
        List<PersonTraceDo> list = personTraceDoMapperExt.selectByExample(example);
        // 返回集合
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 查询list集合
     */
    @Override
    public List<PersonTraceDo> queryList(Long registerId) {
        List<PersonTraceDo> list = new ArrayList<>();
        // 查询条件的容器
        PersonTraceDoExample example = new PersonTraceDoExample();
        // 新建查询条件
        PersonTraceDoExample.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        criteria.andRegisterIdEqualTo(registerId);
        example.setOrderByClause("create_time desc");
        if (null != registerId) {
            list = personTraceDoMapperExt.selectByExample(example);
        }
        return list;
    }

}
