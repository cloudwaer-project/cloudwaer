package com.cloudwaer.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cloudwaer.common.dto.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @ClassName ArticleReqDto
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 10:57
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleReqDto extends PageVo {


    /**
     * 文章唯一编号,发布文章时自动生成
     */
    private String articleUniqueCode;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章封面
     */
    private String articleImage;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章标签的唯一编号 可以多个  以,分割
     */
    private String articleTags;

    /**
     * 是否开启评论 0关闭1开启
     */
    private Boolean articleEnableComment;

    /**
     * 文章访问量
     */
    private Integer articleViews;

    /**
     * 文章发布时间
     */
    private LocalDateTime articleCreatetime;

    /**
     * 文章是否删除0删除1未删除
     */
    private Integer articleDelflag;

    /**
     * 文章更新时间
     */
    private LocalDateTime articleUpdatetim;

    /**
     * 文章发布用户的唯一编号
     */
    private String articleCreatecode;

    /**
     * 文章最后编辑用户编号
     */
    private String articleUpdatecode;

    /**
     * 文章热度
     */
    private Integer articleHot;

    /**
     * 文章排序
     */
    private Integer articleOrder;

    /**
     * 文章分类
     */
    private String catrgoryCode;
}
