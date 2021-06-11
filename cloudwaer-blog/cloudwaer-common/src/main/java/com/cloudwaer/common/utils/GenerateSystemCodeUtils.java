package com.cloudwaer.common.utils;

import java.util.Date;

/**
 * @ClassName GenerateSystemCodeUtils
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 13:35
 * @Version 1.0
 **/
public class GenerateSystemCodeUtils {
    /**
     * 返回带key和 年月日 的序列号
     * @param key
     * @return
     */
    public static String obtainKeyDateSeqYMD(String key) {
        if(org.apache.commons.lang.StringUtils.isEmpty(key)){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(key);
        sb.append(DateTimeUtil.getFormatDateTime_MS(new Date()));
        int i=(int)(Math.random()*900)+100;
        sb.append(i);
        return sb.toString();
    }
}
