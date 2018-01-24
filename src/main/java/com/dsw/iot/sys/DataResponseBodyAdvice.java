package com.dsw.iot.sys;

import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.PageResult;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对restful请求返回统一的结果格式ActionResult
 */
@ControllerAdvice
public class DataResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof ActionResult) {
            //按标准格式返回的不处理
            return body;
        } else if (body instanceof PageResult) {
            //分页对象不处理
            return body;
        } else {
            ActionResult actionResult = new ActionResult();
            actionResult.setContent(body);
            return actionResult;
        }
    }
}