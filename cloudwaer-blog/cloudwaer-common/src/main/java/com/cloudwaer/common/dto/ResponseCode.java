package com.cloudwaer.common.dto;

/**
 * @ClassName ResponseDtoCode  返回枚举
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 10:44
 * @Version 1.0
 **/
public enum ResponseCode {
    SUCCESS("100000", "处理成功", true),
    ERROR("100001", "处理失败", false),
    PARAMS_ERROR("100003", "参数异常", false),
//    QUERY_HYSTRIX_ERROR("200203", "从基础服务获取用户权限异常熔断", false),
    QUERY_CATEGORY_EXIST("999701","此分类已存在,禁止重复分类名称",false);



    ResponseCode(String code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
    }


    private String code;
    private String msg;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
