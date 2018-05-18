package com.dsw.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctc.wstx.util.StringUtil;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.SystemConfDoMapperExt;
import com.dsw.iot.dto.SystemConfRequest;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.model.SystemConfDo;
import com.dsw.iot.model.SystemConfDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.SystemConfigSerivce;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;


/**
 * 配置文件单独管理服务
 * @author dearseewe
 *
 */

@Service
public class SystemConfigServiceImp implements SystemConfigSerivce {
    
    @Autowired(required= false)
    private SystemConfDoMapperExt systemConfDoMapperExt;
    
    @Autowired
    private CurrentUserService currentUserService;
    
    /**
     * 分页
     */
    @Override
    public PageResult<SystemConfDo> queryPage(SystemConfRequest request) {
	
	PageResult<SystemConfDo> pageResult=new PageResult<>();
	
	SystemConfDoExample example= new SystemConfDoExample();
	
	SystemConfDoExample.Criteria criteria=example.createCriteria();
	
	criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
	
	if (org.apache.commons.lang3.StringUtils.isNotEmpty(request.getKeyInfo())) {
	    
	    criteria.andKeyInfoLike("%"+request.getKeyInfo()+"%");
	    
	}
	
	example.setOrderByClause("update_time desc");
	
	int count =systemConfDoMapperExt.countByExample(example);
	
	PageDto page = new PageDto(request.getPage(), request.getLimit(), count);
        //获得分页参数
        example.setPageDto(page);
        
        List<SystemConfDo> list=systemConfDoMapperExt.selectByExample(example);
       
        pageResult.setData(list);
       
        pageResult.setCount(count);
	
        return pageResult;
    }

    /**
     * 保存
     */
    @Override
    @Transactional
    public void saveSystemConfig(SystemConfDo systemConfigDo) throws BizException {
	//新增或更新配置表
	if (null==systemConfigDo.getId()) {
	    
	    DomainUtil.setCommonValueForCreate(systemConfigDo, currentUserService.getPvgInfo());
	    
	    systemConfDoMapperExt.insertSelective(systemConfigDo);
	    
	}else {
	    
	    DomainUtil.setCommonValueForUpdate(systemConfigDo, currentUserService.getPvgInfo());
	    
	    systemConfDoMapperExt.updateByPrimaryKeySelective(systemConfigDo);
	    
	}
    }

    /**
     * 通过主键查询
     */
    @Override
    public SystemConfDo getSystemConfig(Long id) {
	
	 return systemConfDoMapperExt.selectByPrimaryKey(id);
	 
    }

    
    /**
     * 根据主键id删除配置信息,使失效
     * 
     */
    @Override
    @Transactional
    public void invalidSystemConfig(String ids) {
	if (!StringUtils.isEmpty(ids)) { // 删除多个id
            String[] idArr = ids.split(",");// 分解逗号拼接字符串
            for (String id : idArr) {
                if (!StringUtils.isEmpty(id)) {
                    SystemConfDo systemConfigDo = new SystemConfDo();
                    systemConfigDo.setId(Long.parseLong(id));
                    systemConfigDo.setStatus("0");
                    DomainUtil.setCommonValueForDelete(systemConfigDo, currentUserService.getPvgInfo());
                    systemConfDoMapperExt.updateByPrimaryKey(systemConfigDo);
                }
            }
        }
    }
}
