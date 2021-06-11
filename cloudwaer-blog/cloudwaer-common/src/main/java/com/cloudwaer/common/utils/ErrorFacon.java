package com.cloudwaer.common.utils;

public interface ErrorFacon {
    /**
     * 错误码
     */
    Integer getResultCode();

    /**
     * 错误描述
     */
    String getResultMsg();
}