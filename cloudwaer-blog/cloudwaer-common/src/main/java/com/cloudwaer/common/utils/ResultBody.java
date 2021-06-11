package com.cloudwaer.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultBody {
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 响应代码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应结果
     */
    private Object result;

    public ResultBody() {
    }

    public ResultBody(ErrorFacon errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(ErrorMe.SUCCESS.getResultCode());
        rb.setMessage(ErrorMe.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(ErrorFacon errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(Integer code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(-1);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return objectMapper.valueToTree(this).toString();
    }

}