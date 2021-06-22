package com.cloudwaer.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-07
 */
public class BlogArticle implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键自增
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章唯一编号,发布文章时自动生成
     */
    @TableField("ARTICLE_UNIQUE_CODE")
    private String articleUniqueCode;

    /**
     * 文章标题
     */
    @TableField("ARTICLE_TITLE")
    private String articleTitle;

    /**
     * 文章封面
     */
    @TableField("ARTICLE_IMAGE")
    private String articleImage;

    /**
     * 文章内容
     */
    @TableField("ARTICLE_CONTENT")
    private String articleContent;

    /**
     * 文章标签的唯一编号 可以多个  以,分割
     */
    @TableField("ARTICLE_TAGS")
    private String articleTags;

    /**
     * 是否开启评论 0关闭1开启
     */
    @TableField("ARTICLE_ENABLE_COMMENT")
    private Boolean articleEnableComment;

    /**
     * 文章访问量
     */
    @TableField("ARTICLE_VIEWS")
    private Integer articleViews;

    /**
     * 文章发布时间
     */
    @TableField("ARTICLE_CREATETIME")
    private Date articleCreatetime;

    /**
     * 文章是否删除0删除1未删除
     */
    @TableField("ARTICLE_DELFLAG")
    private Integer articleDelflag;

    /**
     * 文章更新时间
     */
    @TableField("ARTICLE_UPDATETIM")
    private Date articleUpdatetim;

    /**
     * 文章发布用户的唯一编号
     */
    @TableField("ARTICLE_CREATECODE")
    private String articleCreatecode;

    /**
     * 文章最后编辑用户编号
     */
    @TableField("ARTICLE_UPDATECODE")
    private String articleUpdatecode;

    /**
     * 文章热度
     */
    @TableField("ARTICLE_HOT")
    private Integer articleHot;

    /**
     * 文章排序
     */
    @TableField("ARTICLE_ORDER")
    private Integer articleOrder;

    /**
     * 文章分类
     */
    @TableField("CATRGORY_CODE")
    private String catrgoryCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleUniqueCode() {
        return articleUniqueCode;
    }

    public void setArticleUniqueCode(String articleUniqueCode) {
        this.articleUniqueCode = articleUniqueCode;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public Boolean getArticleEnableComment() {
        return articleEnableComment;
    }

    public void setArticleEnableComment(Boolean articleEnableComment) {
        this.articleEnableComment = articleEnableComment;
    }

    public Integer getArticleViews() {
        return articleViews;
    }

    public void setArticleViews(Integer articleViews) {
        this.articleViews = articleViews;
    }

    public Date getArticleCreatetime() {
        return articleCreatetime;
    }

    public void setArticleCreatetime(Date articleCreatetime) {
        this.articleCreatetime = articleCreatetime;
    }

    public Integer getArticleDelflag() {
        return articleDelflag;
    }

    public void setArticleDelflag(Integer articleDelflag) {
        this.articleDelflag = articleDelflag;
    }

    public Date getArticleUpdatetim() {
        return articleUpdatetim;
    }

    public void setArticleUpdatetim(Date articleUpdatetim) {
        this.articleUpdatetim = articleUpdatetim;
    }

    public String getArticleCreatecode() {
        return articleCreatecode;
    }

    public void setArticleCreatecode(String articleCreatecode) {
        this.articleCreatecode = articleCreatecode;
    }

    public String getArticleUpdatecode() {
        return articleUpdatecode;
    }

    public void setArticleUpdatecode(String articleUpdatecode) {
        this.articleUpdatecode = articleUpdatecode;
    }

    public Integer getArticleHot() {
        return articleHot;
    }

    public void setArticleHot(Integer articleHot) {
        this.articleHot = articleHot;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public String getCatrgoryCode() {
        return catrgoryCode;
    }

    public void setCatrgoryCode(String catrgoryCode) {
        this.catrgoryCode = catrgoryCode;
    }
}
