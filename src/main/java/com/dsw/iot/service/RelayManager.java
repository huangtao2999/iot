package com.dsw.iot.service;

/**
 * 继电器控制服务类
 *
 * @author huangt
 * @create 2018-02-23 17:23
 * TODO;
 **/
public interface RelayManager {
    /**
     * 打开继电器
     *
     * @param ip
     * @param port
     * @return
     */
    public boolean open(String ip, String port);

    /**
     * 关闭继电器
     *
     * @param ip
     * @param port
     * @return
     */
    public boolean close(String ip, String port);

    /**
     * 获取继电器状态
     *
     * @param ip
     * @param port
     * @return
     */
    public String getStatus(String ip, String port);
}
