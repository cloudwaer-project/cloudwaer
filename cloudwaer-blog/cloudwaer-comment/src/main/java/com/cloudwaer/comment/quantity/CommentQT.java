package com.cloudwaer.comment.quantity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * String Author A_Nan
 * String Date 21/06/11 上午 9:31
 * String ClassName
 */


@Data
@EqualsAndHashCode(callSuper = false)
public class CommentQT {

    private String USER_CODE;// 用户ID
    private String USER_NAME;// 用户显示名称
    private String ARTICLE_CODE;// 评论文章的CODE
    private String PARENT_COMMENT_CODE;//一级评论的CODE
    private String PARENT_COMMENT_USER_CODE;// 一级评论的用户CODE
    private String REPLY_COMMENT_CODE;// 二级评论的CODE
    private String REPLY_COMMENT_USER_CODE;// 二级评论的用户CODE
    private String CONTENT;// 评论的内容
    private String CREATE_TIME;// 发布时间 new Date
    private String PARENT_CODE;// 二级评论一级的id 如果没有这个参数即表示为一级评论

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getARTICLE_CODE() {
        return ARTICLE_CODE;
    }

    public String getPARENT_COMMENT_CODE() {
        return PARENT_COMMENT_CODE;
    }

    public String getPARENT_COMMENT_USER_CODE() {
        return PARENT_COMMENT_USER_CODE;
    }

    public String getREPLY_COMMENT_CODE() {
        return REPLY_COMMENT_CODE;
    }

    public String getREPLY_COMMENT_USER_CODE() {
        return REPLY_COMMENT_USER_CODE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public String getPARENT_CODE() {
        return PARENT_CODE;
    }


    /**
     * .toString如果为空即会触发空指针会被全局异常拦截相当于数据校验,然后前端做非空判断即可保证数据一致
     */

    @Override
    public String toString() {
        USER_CODE.toString();
        USER_NAME.toString();
        ARTICLE_CODE.toString();
        PARENT_COMMENT_CODE.toString();
        PARENT_COMMENT_USER_CODE.toString();
//        REPLY_COMMENT_CODE.toString();
//        REPLY_COMMENT_USER_CODE.toString();
        CONTENT.toString();
//        CREATE_TIME.toString();
//        PARENT_CODE.toString();
        return "CommentQT{" +
                "USER_CODE='" + USER_CODE + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", ARTICLE_CODE='" + ARTICLE_CODE + '\'' +
                ", PARENT_COMMENT_CODE='" + PARENT_COMMENT_CODE + '\'' +
                ", PARENT_COMMENT_USER_CODE='" + PARENT_COMMENT_USER_CODE + '\'' +
                ", REPLY_COMMENT_CODE='" + REPLY_COMMENT_CODE + '\'' +
                ", REPLY_COMMENT_USER_CODE='" + REPLY_COMMENT_USER_CODE + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", PARENT_CODE='" + PARENT_CODE + '\'' +
                '}';
    }
}
