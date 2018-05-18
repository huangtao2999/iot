package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.SystemConfRequest;
import com.dsw.iot.model.SystemConfDo;
import com.dsw.iot.service.SystemConfigSerivce;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;


@RestController
@RequestMapping("/SystemConfig")
public class SystemConfigRpc {
    
        @Autowired
        SystemConfigSerivce systemConfigService;

    	/**
    	 * 分页查询配置信息
    	 *
    	 * @param resquest
    	 * @return
    	 */
        @RequestMapping("/queryPage")
        public PageResult<SystemConfDo> queryPage(SystemConfRequest resquest) {
            PageResult<SystemConfDo> pageResult = systemConfigService.queryPage(resquest);
            return pageResult;
        }

    	/**
    	 * 新增/编辑配置信息
    	 *
    	 * @param systemConfigDo
    	 * @return
    	 * @throws Exception
    	 */
    	@RequestMapping(value = "/saveSystemConfig", method = RequestMethod.POST)
    	public void saveSystemConfigr(SystemConfDo systemConfigDo) throws Exception {
    	    systemConfigService.saveSystemConfig(systemConfigDo);
        }

    	/**
    	 * 删除配置信息
    	 *
    	 * @param param
    	 * @return
    	 */
    	@RequestMapping("/invalidMessage")
    	public void invalidSystemConfig(String ids) throws BizException {
    	systemConfigService.invalidSystemConfig(ids);
        }

}
