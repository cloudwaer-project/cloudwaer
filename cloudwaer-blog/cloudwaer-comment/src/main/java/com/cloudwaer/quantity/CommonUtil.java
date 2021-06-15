package com.cloudwaer.quantity;

import com.cloudwaer.common.entity.BlogComment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author A_Nan
 * @Date 21/06/11 下午 3:57
 * @ClassName 单条评论信息类
 * @common_tow 二级评论包含在一级评论的里面
 */
public class CommonUtil {
    private Integer id;
    private String userCode;
    private String userName;
    private String articleCode;
    private String parentCommentCode;
    private String parentCommentUserCode;
    private String replyCommentCode;
    private String replyCommentUserCode;
    private Integer commentLevel;
    private String content;
    private Integer status;
    private Integer praiseNum;
    private Integer topStatus;
    private String createTime;
    private Integer auditStatus;
    private String parentCode;
    public List<BlogComment> common_tow = null;
    public CommonUtil() {
        if (this.common_tow == null) {
            this.common_tow = new ArrayList<BlogComment>();
        }
    }

    @Override
    public String toString() {
        return "CommonUtil{" +
                ", id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", articleCode='" + articleCode + '\'' +
                ", parentCommentCode='" + parentCommentCode + '\'' +
                ", parentCommentUserCode='" + parentCommentUserCode + '\'' +
                ", replyCommentCode='" + replyCommentCode + '\'' +
                ", replyCommentUserCode='" + replyCommentUserCode + '\'' +
                ", commentLevel=" + commentLevel +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", praiseNum=" + praiseNum +
                ", topStatus=" + topStatus +
                ", createTime='" + createTime + '\'' +
                ", auditStatus=" + auditStatus +
                ", parentCode='" + parentCode + '\'' +
                "common_tow=" + common_tow +
                '}';
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<BlogComment> getCommon_tow() {
        return common_tow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getParentCommentCode() {
        return parentCommentCode;
    }

    public void setParentCommentCode(String parentCommentCode) {
        this.parentCommentCode = parentCommentCode;
    }

    public String getParentCommentUserCode() {
        return parentCommentUserCode;
    }

    public void setParentCommentUserCode(String parentCommentUserCode) {
        this.parentCommentUserCode = parentCommentUserCode;
    }

    public String getReplyCommentCode() {
        return replyCommentCode;
    }

    public void setReplyCommentCode(String replyCommentCode) {
        this.replyCommentCode = replyCommentCode;
    }

    public String getReplyCommentUserCode() {
        return replyCommentUserCode;
    }

    public void setReplyCommentUserCode(String replyCommentUserCode) {
        this.replyCommentUserCode = replyCommentUserCode;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
