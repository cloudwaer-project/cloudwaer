package com.cloudwaer.common.exception;

import com.cloudwaer.common.dto.ResponseCode;

import java.io.Serializable;

/**
 * @ClassName ParamsException
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 13:34
 * @Version 1.0
 **/
public class ParamsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private ResponseCode errorCode;
    private String msg;

    public ParamsException(ResponseCode errorCode, String msg, String ... args) {
        this.errorCode = errorCode;
        if (args == null || args.length == 0) {
            this.msg = msg;
        } else {
            StringBuilder sb = new StringBuilder(msg);
            for (String arg : args) {
                sb.replace(sb.indexOf("{"), sb.indexOf("}") + 1,
                        new StringBuilder("[").append(arg).append("]").toString());
            }
            this.msg = sb.toString();
        }
    }

    public ResponseCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ResponseCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
