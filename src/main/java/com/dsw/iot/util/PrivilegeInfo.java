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

    private String ip;//登录人ip
    private String roleIds;//登录人的角色ids
}
