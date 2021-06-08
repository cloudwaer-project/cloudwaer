package com.cloudwaer.common.dto;

/**
 * @ClassName ResponseDto
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 10:42
 * @Version 1.0
 **/
public class ResponseDto<T> {
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(ResponseCode responseCode) {
        code = responseCode.getCode();
        msg = responseCode.getMsg();
        success = responseCode.getCode().equals(ResponseCode.SUCCESS.getCode());
        //会使得success 值为false
        success = responseCode.isSuccess();
    }

    public ResponseDto(ResponseCode responseCode, String msg) {
        code = responseCode.getCode();
        this.msg = msg;
        //会判断你传入的状态码是否是成功的枚举状态码
        success = responseCode.getCode().equals(ResponseCode.SUCCESS.getCode());
    }

    public ResponseDto(ResponseCode responseCode, T data) {
        code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        //会判断你传入的状态码是否是成功的枚举状态码
        success = responseCode.getCode().equals(ResponseCode.SUCCESS.getCode());
        success = responseCode.isSuccess();
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
