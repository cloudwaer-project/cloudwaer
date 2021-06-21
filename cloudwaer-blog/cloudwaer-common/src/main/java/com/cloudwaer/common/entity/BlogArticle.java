package com.cloudwaer.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    @TableId("ID")
    private int id;

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




}
