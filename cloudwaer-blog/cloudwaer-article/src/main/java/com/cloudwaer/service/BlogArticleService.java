package com.cloudwaer.service;

import com.cloudwaer.common.utils.PageModel;
import com.cloudwaer.common.dto.article.ArticleReqDto;
import com.cloudwaer.common.entity.article.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-07
 */
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 查询文章列表
     *
     * @param articleReqDto
     * @return
     */
    PageModel queryArticleList(ArticleReqDto articleReqDto);

    /**
     * 添加文章
     * @param articleReqDto
     */
    void saveArticle(ArticleReqDto articleReqDto);

    /**
     * 删除文章
     * @param articleReqDto
     */
    void deleteArticle(ArticleReqDto articleReqDto);
}
