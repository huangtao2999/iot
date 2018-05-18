package com.dsw.iot.service;

import javax.servlet.http.HttpServletRequest;

public interface ClientIpService {
	
	public String getRemoteHost(HttpServletRequest request);
}
