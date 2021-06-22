package com.cloudwaer.mapper;

import com.cloudwaer.common.dto.article.ArticleReqDto;
import com.cloudwaer.common.dto.article.ArticleRespDto;
import com.cloudwaer.common.dto.category.CategoryRespDto;
import com.cloudwaer.common.entity.article.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudwaer.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-07
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    /**
     * 查询文章列表
     * @param articleReqDto
     * @return
     */
    List<ArticleRespDto> queryArticleList(@Param("articleReqDto") ArticleReqDto articleReqDto);

    /**
     * 查询文章数量
     * @param articleReqDto
     * @return
     */
    Integer queryArticleCount(@Param("articleReqDto") ArticleReqDto articleReqDto);

    /**
     * 测试 N节点数据使用的方法
     * @return
     */
    List<Dept> queryAll();

    /**
     * 添加文章
     * @param articleReqDto
     */
    void saveArticle(@Param("articleReqDto") ArticleReqDto articleReqDto);

    /**
     * 删除文章
     * @param articleReqDto
     */
    void deleteArticle(@Param("articleReqDto") ArticleReqDto articleReqDto);
}
