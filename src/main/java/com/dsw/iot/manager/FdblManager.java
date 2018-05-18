package com.dsw.iot.manager;

import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.BlContentVo;

import java.util.List;

/**
 * 法度笔录服务类
 *
 * @author huangt
 * @create 2018-03-22 14:27
 **/
public interface FdblManager {
    /**
     * 单点登录 同步笔录基础信息
     *
     * @param registerId
     * @return
     */
    public ActionResult syncFadu(Long registerId, String bllx) throws BizException;

    /**
     * 根据房间编号+在所状态获取笔录PDF 文件地址
     *
     * @param roomNo
     * @return
     * @throws Exception
     */
    public List<BlContentVo> getBlContentToPdfPath(String roomNo) throws Exception;

    /**
     *  根据personRegisterId 获取pdf 文件地址
     */
    public BlContentVo getBlContentToPdfPath(long registerId) throws BizException;
    
    /**
     * 获取笔录pdf 返回pdf路径
     *
     * @param blid
     * @return
     * @throws Exception
     */
    public List<String> getSigntureFileContent(String blid) throws Exception;

}
