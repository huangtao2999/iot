package com.dsw.iot.manager;

import com.dsw.iot.util.BizException;

/**
 * 警综对接服务类
 *
 * @author huangt
 * @create 2018-02-23 17:32
 * TODO;
 **/
public interface SwytManager {
    /**
     * 根据身份证号 获取常驻人员信息
     * @param cardNo
     * @param policeNo
     * @return
     */
    public Object getPeopleByCardNo(String cardNo,String policeNo) throws BizException;

    //获取警情编号

    //同步人员登记信息
}
