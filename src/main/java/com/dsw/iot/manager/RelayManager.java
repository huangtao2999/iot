package com.dsw.iot.manager;

import com.dsw.iot.util.ActionResult;
import com.dsw.iot.vo.RelayCfgVo;

/**
 * 继电器控制服务类
 *
 * @author huangt
 * @create 2018-02-23 17:23
 **/
public interface RelayManager {
    /**
     * 打开继电器 重载涛哥的方法，免得被他发现
     *
     * @param ip
     * @param port
     * @param index
     * @return
     */
    public boolean open(String ip, int port, int index);

    /**
     * 关闭继电器 重载涛哥的方法，免得被他发现
     *
     * @param ip
     * @param port
     * @param index
     * @return
     */
    public boolean close(String ip, int port, int index);

    /**
     * 打开继电器
     *
     * @param ip
     * @param port
     * @param index
     * @return
     */
    public ActionResult<String> openV2(String ip, int port, int index);

    /**
     * 关闭继电器
     *
     * @param ip
     * @param port
     * @param index
     * @return
     */
    public ActionResult<String> closeV2(String ip, int port, int index);

    /**
     * 获取继电器状态
     *
     * @param ip
     * @param port
     * @param index
     * @return
     */
    public ActionResult<String> getStatus(String ip, int port, int index);


    /**
     * 根据类型 获取继电器配置
     *
     * @param type
     * @return
     */
    public RelayCfgVo initCfg(String type);

}
