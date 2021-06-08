package com.cloudwaer.mapper;

import com.cloudwaer.dto.ArticleReqDto;
import com.cloudwaer.dto.ArticleRespDto;
import com.cloudwaer.entity.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-07
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    List<ArticleRespDto> queryArticleList(ArticleReqDto articleReqDto);

    int queryArticleCount(ArticleReqDto articleReqDto);
}
