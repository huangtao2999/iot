package com.dsw.iot.util;

import lombok.Data;

/**
 * 登录用户
 *
 * @author huangt
 * @create 2018-02-01 17:48
 **/
@Data
public class PrivilegeInfo {
	private Long userId;
    private String account;

    private String name;//登录人姓名
    private String realName;//登录人姓名(拿coockie的时候放实体里用)

    private String ip;//登录人ip
    private String roleIds;//登录人的角色ids
    //身份证号码
    private String idCard;
}
