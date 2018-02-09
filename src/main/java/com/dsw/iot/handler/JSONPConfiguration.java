package com.dsw.iot.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * jsonp跨域请求支持
 */
@ControllerAdvice(basePackages = {"com.dsw.iot.controller"})
public class JSONPConfiguration extends AbstractJsonpResponseBodyAdvice {
    public JSONPConfiguration() {
        super("callback", "jsonp");
    }
}
