package com.dsw.iot.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.LogDoMapperExt;
import com.dsw.iot.dto.LogRequest;
import com.dsw.iot.model.LogDo;
import com.dsw.iot.model.LogDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogDoMapperExt logDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;

    /**
     * 分页查询
     *
     * @throws ParseException
     */
    @Override
    public PageResult<LogDo> queryPage(LogRequest param) throws ParseException {
        // 定义分页返回集合
        PageResult<LogDo> pageResult = new PageResult<>();
        LogDoExample logDoExample = new LogDoExample();
        LogDoExample.Criteria lCriteria = logDoExample.createCriteria();
        lCriteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 开始时间
        if (StringUtils.isNotBlank(param.getStartTime())) {
            Date startTime = DateUtils.parseDate(param.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            lCriteria.andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        // 结束时间
        if (StringUtils.isNotBlank(param.getEndTime())) {
            Date startTime = DateUtils.parseDate(param.getEndTime(), "yyyy-MM-dd HH:mm:ss");
            lCriteria.andCreateTimeLessThanOrEqualTo(startTime);
        }
        // 内容
        if (null != param.getContent()) {
            lCriteria.andContentLike("%" + param.getContent() + "%");
        }
        // 类型
        if (StringUtils.isNotBlank(param.getType())) {
            lCriteria.andTypeEqualTo(param.getType());
        }
        logDoExample.setOrderByClause("create_time desc");
        // 分页
        int count = logDoMapperExt.countByExample(logDoExample);
        PageDto pageDto = new PageDto(param.getPage(), param.getLimit(), count);
        logDoExample.setPageDto(pageDto);

        List<LogDo> logDos = logDoMapperExt.selectByExample(logDoExample);
        // 返回list和分页
        pageResult.setData(logDos);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 通过主键查找单条记录
     */
    @Override
    public LogDo selectByPrimaryKey(Long id) {
        return logDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 通用日志插入接口 module 常量中定义模块 type 操作类型（常量中定义） content 操作内容
     */
    @Override
    @Transactional
    public void insertLog(HttpServletRequest request, String module, String type, String content) {
        LogDo logDo = new LogDo();
        logDo.setModule(module);
        logDo.setType(type);
        logDo.setContent(content);
        // 获得本机ip
        String hostContent = request.getHeader("Host");
        String localIp = hostContent.substring(0, hostContent.indexOf(":"));
        logDo.setIp(localIp);
        // url请求地址
        logDo.setUrl(request.getRequestURI());
        DomainUtil.setCommonValueForCreate(logDo, currentUserService.getPvgInfo());
        logDoMapperExt.insertSelective(logDo);
    }

    /**
     * 通用日志插入接口 module 常量中定义模块 type 操作类型（常量中定义） content 操作内容
     */
    @Override
    @Transactional
    public void insertLog(String module, String type, String content) {
        LogDo logDo = new LogDo();
        logDo.setModule(module);
        logDo.setType(type);
        logDo.setContent(content);
        // 获得本机ip
        logDo.setIp(currentUserService.getPvgInfo().getIp());
        DomainUtil.setCommonValueForCreate(logDo, currentUserService.getPvgInfo());
        logDoMapperExt.insertSelective(logDo);
    }

}
