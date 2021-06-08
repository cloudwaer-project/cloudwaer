package com.cloudwaer.common.exception;

import com.cloudwaer.common.dto.DiyRespState;
import com.cloudwaer.common.dto.ResponseCode;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 12:21
 * @Version 1.0
 **/
public class BusinessException extends RuntimeException{
    /**
     * 序列化使用
     */
    private static final long serialVersionUID = 1L;

    private ResponseCode responseCode;

    private DiyRespState respState;

    public BusinessException(ResponseCode errorCode) {
        super(errorCode.toString());
        this.responseCode = errorCode;
    }

    public boolean isState(){
        return null == respState ? false : true;
    }

    public ResponseCode getErrorCode() {
        return responseCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public DiyRespState getRespState() {
        return respState;
    }

    public void setRespState(DiyRespState respState) {
        this.respState = respState;
    }
}
