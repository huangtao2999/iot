package com.dsw.iot.util;


import com.dsw.iot.constant.CommConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 映射对象工具类
 *
 * @author huangt
 * @create 2018-02-01 14:27
 **/
public class DomainUtil {
    private static final Logger logger = LoggerFactory.getLogger(DomainUtil.class);

    public static void setCommonValueForCreate(Object pojo,
                                               PrivilegeInfo privilegeInfo) {
        try {
            Method setCreator = pojo.getClass().getMethod("setCreateUser",
                    String.class);
            setCreator.invoke(pojo, getOperator(privilegeInfo));

            Method setModifier = pojo.getClass().getMethod("setUpdateUser",
                    String.class);
            setModifier.invoke(pojo, getOperator(privilegeInfo));

            Method setGmtCreate = pojo.getClass().getMethod("setCreateTime",
                    Date.class);
            setGmtCreate.invoke(pojo, new Date());

            Method setGmtModified = pojo.getClass().getMethod("setUpdateTime",
                    Date.class);
            setGmtModified.invoke(pojo, new Date());

            Method setIsDeleted = pojo.getClass().getMethod("setIsDeleted",
                    String.class);
            setIsDeleted.invoke(pojo, CommConfig.DELETED.NO.getName());

        } catch (Exception e) {
            logger.error("插入设置默认参数失败!", e);
        }

    }

    public static void setCommonValueForDelete(Object pojo,
                                               PrivilegeInfo privilegeInfo) {
        setCommonValueForUpdate(pojo, privilegeInfo);
        try {
            Method setIsDeleted = pojo.getClass().getMethod("setIsDeleted",
                    String.class);
            setIsDeleted.invoke(pojo, CommConfig.DELETED.YES.getName());
            setCommonValueForUpdate(pojo, privilegeInfo);
        } catch (Exception e) {
            logger.error("删除设置默认参数失败!", e);
        }

    }

    public static void setCommonValueForUpdate(Object pojo,
                                               PrivilegeInfo privilegeInfo) {
        try {
            Method setGmtModified = pojo.getClass().getMethod("setUpdateTime",
                    Date.class);
            setGmtModified.invoke(pojo, new Date());
            Method setModifier = pojo.getClass().getMethod("setUpdateUser",
                    String.class);
            setModifier.invoke(pojo, getOperator(privilegeInfo));
        } catch (Exception e) {
            logger.error("更新设置默认参数失败!", e);
        }

    }

    public static String getOperator(PrivilegeInfo pvgInfo) {
        if (pvgInfo == null || StringUtils.isEmpty(pvgInfo.getAccount())) {
            return "SYSTEM";
        } else {
            return pvgInfo.getAccount();
        }
    }
}
