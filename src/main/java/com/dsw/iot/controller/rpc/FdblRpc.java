package com.dsw.iot.controller.rpc;

import com.dsw.iot.manager.FdblManager;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.BlContentVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 法度笔录RPC
 *
 * @author huangt
 * @create 2018-03-22 14:25
 **/
@RestController
@RequestMapping("/FdblRpc")
public class FdblRpc {
    protected static final Logger logger = Logger.getLogger(FdblRpc.class);
    @Autowired
    private FdblManager fdblManager;

    /**
     * 物联网同步法度笔录登录秘钥
     *
     * @param registerId
     * @param bllx
     * @return
     * @throws BizException
     */
    @RequestMapping("/syncFadu")
    public ActionResult syncFadu(Long registerId, String bllx) throws BizException {
        return fdblManager.syncFadu(registerId, bllx);
    }

    /**
     * 通过房间编号+在所状态 获取最新一条笔录内容
     * 获取笔录内容pdf
     * 有缺陷:后期通过物联网的光盘刻录可避免通过房间获取 TODO;
     *
     * @param roomNo
     * @return
     */
    @RequestMapping("/getBlContentToPdf")
    public List<BlContentVo> getBlContentToPdf(String roomNo) throws Exception {
        return fdblManager.getBlContentToPdfPath(roomNo);
    }
}
