package com.dsw.iot.handler;

import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.text.MessageFormat;

/**
 * 集中异常处理类
 */
@RestController
@ControllerAdvice
public class BizExceptionHandler {
    protected static final Logger logger = Logger.getLogger(BizExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Object baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error(MessageFormat.format("---BaseException Handler---Host {0} invokes url {1} ERROR: {2}", req.getRemoteHost(), req.getRequestURL(), e.getMessage()));
        ActionResult actionResult = new ActionResult();
        actionResult.setSuccess(false);
        actionResult.setErrorMsg(e.getMessage());
        return actionResult;
    }
}
