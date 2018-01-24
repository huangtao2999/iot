package com.dsw.iot.util;

import java.io.Serializable;

import lombok.Data;

/**
 * 操作返回结果
 */
@Data
public class ActionResult<T> extends BaseModel implements Serializable {

    /**
     * 返回值
     */
    private T content;
    /**
     * 操作是否成功
     */
    private boolean success = true;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * info —300 提醒信息 warn —200 警告信息 error —100 业务错误信息 fault —0 系统级别错误信息
     */
    private Integer errorLevel;

    public ActionResult() {
    }

    public ActionResult(T content) {
        this.content = content;
    }

    public ActionResult(T content, boolean success) {
        this.content = content;
        this.success = success;
    }

    public ActionResult(T content, boolean success, String errorCode, String errorMsg) {
        this.content = content;
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ActionResult(T content, boolean success, String errorCode, String errorMsg, int errorLevel) {
        this.content = content;
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorLevel = errorLevel;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        this.success = false;
    }


}
