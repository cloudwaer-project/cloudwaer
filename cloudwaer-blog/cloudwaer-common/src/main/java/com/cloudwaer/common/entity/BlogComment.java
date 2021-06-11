package com.cloudwaer.common.entity;


public class BlogComment {

    private Integer id = null;
    private String userCode;
    private String userName;
    private String articleCode;
    private String parentCommentCode;
    private String parentCommentUserCode;
    private String replyCommentCode = null;
    private String replyCommentUserCode;
    private Integer commentLevel = 1;
    private String content;
    private Integer status = 1;
    private Integer praiseNum = 0;
    private Integer topStatus = 0;
    private String createTime;
    private Integer auditStatus = 0;
    private String parentCode;

    public BlogComment(String USER_CODE, String USER_NAME,
                       String ARTICLE_CODE, String PARENT_COMMENT_CODE,
                       String PARENT_COMMENT_USER_CODE,
                       String REPLY_COMMENT_CODE,
                       String REPLY_COMMENT_USER_CODE,
                       String CONTENT, String CREATE_TIME, String PARENT_CODE) {
        this.userCode = USER_CODE;
        this.userName = USER_NAME;
        this.articleCode = ARTICLE_CODE;
        this.parentCommentCode = PARENT_COMMENT_CODE;
        this.parentCommentUserCode = PARENT_COMMENT_USER_CODE;
        this.replyCommentCode = REPLY_COMMENT_CODE;
        this.replyCommentUserCode = REPLY_COMMENT_USER_CODE;
        this.content = CONTENT;
        this.createTime = CREATE_TIME;
        this.parentCode = PARENT_CODE;
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String toString() {
        return "BlogComment{" +
                "id=" + id +
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
                ", createTime=" + createTime +
                ", auditStatus=" + auditStatus +
                ", parentCode='" + parentCode + '\'' +
                '}';
    }
}
