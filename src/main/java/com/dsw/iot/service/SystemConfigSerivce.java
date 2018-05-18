package com.dsw.iot.service;

import com.dsw.iot.dto.SystemConfRequest;
import com.dsw.iot.model.SystemConfDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

public interface SystemConfigSerivce {
    
    /**
   	 * 分页查询配置信息
   	 *
   	 * @param request
   	 * @return
   	 */
       public PageResult<SystemConfDo> queryPage(SystemConfRequest request);

       /**
   	 * 新增或更新配置信息
   	 *
   	 * @param systemConfigDo
   	 * @return
   	 * @throws BizException
   	 */
   	public void saveSystemConfig(SystemConfDo systemConfigDo) throws BizException;

   	/**
   	 * 根据主键id查询配置
   	 *
   	 * @param id
   	 * @return
   	 */
   	public SystemConfDo getSystemConfig(Long id);

   	/**
   	 * 根据主键id删除配置
   	 */
   	public void invalidSystemConfig(String ids);


}
