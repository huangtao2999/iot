package com.dsw.iot.service.impl;

import com.dsw.iot.dmdal.SwytJjDoMapper;
import com.dsw.iot.dto.SwytJjRequest;
import com.dsw.iot.model.SwytJjDo;
import com.dsw.iot.model.SwytJjDoExample;
import com.dsw.iot.service.SwytJjService;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SwytJjServiceImpl implements SwytJjService {

    private final Logger logger = LogManager.getLogger(SwytJjServiceImpl.class);

    @Autowired
    private SwytJjDoMapper swytJjDoMapper;


    @Override
    public PageResult<SwytJjDo> queryPage(SwytJjRequest swytJjRequest) {
        PageResult<SwytJjDo> pageResult = new PageResult<>();

        SwytJjDoExample example = new SwytJjDoExample();
        SwytJjDoExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNoneBlank(swytJjRequest.getJjr())) {
            criteria.andJjrLike("%" + swytJjRequest.getJjr() + "%");
        }
        if (StringUtils.isNoneBlank(swytJjRequest.getBjrxm())) {
            criteria.andBjrxmLike("%" + swytJjRequest.getBjrxm() + "%");
        }
        criteria.andJjsjIsNotNull();
        if (StringUtils.isNoneBlank(swytJjRequest.getJjsjStart())) {
            Date date1;
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(swytJjRequest.getJjsjStart());
                criteria.andJjsjGreaterThan(new SimpleDateFormat("yyyyMMddHHmmss").format(date1));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }

        if (StringUtils.isNoneBlank(swytJjRequest.getJjsjEnd())) {
            Date date2;
            try {
                date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(swytJjRequest.getJjsjEnd());
                criteria.andJjsjLessThanOrEqualTo(new SimpleDateFormat("yyyyMMddHHmmss").format(date2));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }

        int count = swytJjDoMapper.countByExample(example);
        PageDto pageDto = new PageDto(swytJjRequest.getPage(), swytJjRequest.getLimit(), count);
        example.setPageDto(pageDto);
        example.setOrderByClause("JJSJ DESC");

        List<SwytJjDo> list = swytJjDoMapper.selectByExample(example);
        if (list.size() > 0 && list != null) {
            for (SwytJjDo jjdo : list) {
                try {
                    if (StringUtils.isNoneBlank(jjdo.getJjsj())) {
                        Date date1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(jjdo.getJjsj());
                        jjdo.setJjsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
                    }
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
            }
        }

        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }


}
