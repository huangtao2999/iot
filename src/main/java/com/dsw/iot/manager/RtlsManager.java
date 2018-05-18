package com.dsw.iot.manager;

/**
 * 智物达对接服务类
 *
 * @author huangt
 * @create 2018-04-02 16:08
 **/
public interface RtlsManager {
    /**
     * 绑定标签名称
     *
     * @param euid
     * @param name
     * @param gender
     * @return
     */
    public boolean bindTag(String euid, String name, Integer gender);

    /**
     * 解绑标签名称
     */
    public boolean unBindTag(String euid);

}
