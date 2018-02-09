package com.dsw.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.UserRoleDoMapperExt;
import com.dsw.iot.model.UserRoleDo;
import com.dsw.iot.model.UserRoleDoExample;
import com.dsw.iot.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired(required = false)
    UserRoleDoMapperExt userRoleDoMapperExt;

    /**
     * 查询该用户已经分配是角色
     */
    @Override
    public String selectRolesByUserId(Long userId) {
        //定义查询条件容器
        UserRoleDoExample example = new UserRoleDoExample();
        //新建查询标准
        UserRoleDoExample.Criteria criteria = example.createCriteria();
        //添加条件
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        if (null != userId) {
            criteria.andUserIdEqualTo(userId);
        }
        //查询该用户的所有角色
        List<UserRoleDo> list = userRoleDoMapperExt.selectByExample(example);
        UserRoleDo entity = new UserRoleDo();
        String reString = "";
        //把角色id逗号拼接传给页面
        if (list.size() > 0) {
            for (int m = 0; m < list.size(); m++) {
                entity = list.get(m);
                if (entity.getRoleId() != null) {
                    reString += entity.getRoleId() + ",";
                }
            }
        }
        return reString;
    }
}
