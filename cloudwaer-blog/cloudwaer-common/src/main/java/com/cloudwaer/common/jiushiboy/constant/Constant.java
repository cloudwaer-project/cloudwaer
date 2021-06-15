package com.cloudwaer.common.jiushiboy.constant;

/**
 * @ClassName Constant
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 15:57
 * @Version 1.0
 **/
public class Constant {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";


    /**
     * 验证码 redis key 前缀cloudwaer_codes:
     */
    public static final String CAPTCHA_CODE_KEY = "cloudwaer_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;
}
