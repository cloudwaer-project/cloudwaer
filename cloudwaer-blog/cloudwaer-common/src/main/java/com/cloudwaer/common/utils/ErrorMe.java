package com.cloudwaer.common.utils;

public enum ErrorMe implements ErrorFacon {
    // 数据操作错误定义
    SUCCESS(200, "OK!"),
    BODY_NOT_MATCH(400, "请求参数有非空的为空,请检查"),
    SIGNATURE_NOT_MATCH(402, "请求资源与Token不匹配"),
    NOT_FOUND(404, "错啦,请返回首页"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    COMMENTNO_FOUND(444, "没有评论奥~"),
    INTERNAL_SERVER_ERROR(500, "嗷嗷请求输错,请联系管理员");

    /**
     * 错误码
     */
    private Integer resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;


    ErrorMe(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}