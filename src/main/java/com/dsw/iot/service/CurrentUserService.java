package com.dsw.iot.service;

import com.dsw.iot.util.PrivilegeInfo;

public interface CurrentUserService {

    public PrivilegeInfo getPvgInfo();

    public void setPvginfo(PrivilegeInfo privilegeInfo);

    public void clean();
}
