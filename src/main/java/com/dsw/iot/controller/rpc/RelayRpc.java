package com.dsw.iot.controller.rpc;

import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.vo.RelayCfgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 继电器控制类
 *
 * @author huangt
 * @create 2018-03-26 11:07
 **/
@RestController
@RequestMapping("/RelayRpc")
public class RelayRpc {

    @Autowired
    private RelayManager relayManager;


    @RequestMapping(value = "/open")
    public ActionResult<String> open(String type) {
        // type：phone  ,alarm
        RelayCfgVo relayCfgVo = relayManager.initCfg(type);
        return relayManager.openV2(relayCfgVo.getIp(), relayCfgVo.getPort(), relayCfgVo.getChannel());
    }

    @RequestMapping(value = "/close")
    public ActionResult<String> close(String type) {
        // type：phone  ,alarm
        RelayCfgVo relayCfgVo = relayManager.initCfg(type);
        return relayManager.closeV2(relayCfgVo.getIp(), relayCfgVo.getPort(), relayCfgVo.getChannel());
    }
}
