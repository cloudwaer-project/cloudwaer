package com.cloudwaer.common.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @Author A_Nan
 * @Date 21/06/08 下午 4:44
 * @ClassName
 */

public class ErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public ErrorException() {
        super();
    }

    public ErrorException(ErrorFacon errorInfoInterface) {
        super(errorInfoInterface.getResultCode() + "");
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public ErrorException(ErrorFacon errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode() + "", cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public ErrorException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ErrorException(Integer errorCode, String errorMsg) {
        super(errorCode + "");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorCode + "", cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
