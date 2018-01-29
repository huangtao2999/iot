package com.dsw.iot.service.impl;

import com.dsw.iot.dal.TpLockerDoMapperExt;
import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.model.TpLockerDo;
import com.dsw.iot.model.TpLockerDoExample;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.util.Page;
import com.dsw.iot.util.PageResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 储物柜服务
 *
 * @author huangt
 * @create 2018-01-17 22:24
 **/
@Service
public class LockerServiceImpl implements LockerService {
    @Autowired(required = false)
    private TpLockerDoMapperExt tpLockerDoMapperExt;

    @Override
    public PageResult<TpLockerDo> queryPage(LockerResquest request) {
        PageResult<TpLockerDo> pageResult = new PageResult<>();
        TpLockerDoExample example = new TpLockerDoExample();
        TpLockerDoExample.Criteria criteria = example.createCriteria();
        //添加条件
        if (StringUtils.isNotEmpty(request.getLockerNo())) {
            criteria.andLockerNoLike("%" + request.getLockerNo() + "%");
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            criteria.andTypeEqualTo(request.getType());
        }
        int count = tpLockerDoMapperExt.countByExample(example);
        Page page = new Page(request.getPage(), request.getLimit(), count);
        example.setPage(page);
        List<TpLockerDo> list = tpLockerDoMapperExt.selectByExample(example);
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }
}
