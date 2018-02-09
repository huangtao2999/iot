package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.RoleDoMapper;
import com.dsw.iot.dto.RoleRequest;
import com.dsw.iot.model.RoleDo;

public interface RoleDoMapperExt extends RoleDoMapper {
    List<RoleDo> selectRoleDoListByUserId(RoleRequest param);
}