package com.cloudwaer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.entity.BlogArticle;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.DateTimeUtil;
import com.cloudwaer.common.utils.GenerateSystemCodeUtils;
import com.cloudwaer.common.utils.PageModel;
import com.cloudwaer.common.utils.ParamUtils;
import com.cloudwaer.dto.ArticleReqDto;
import com.cloudwaer.dto.ArticleRespDto;
import com.cloudwaer.mapper.BlogArticleMapper;
import com.cloudwaer.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-07
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {


    @Resource
    private BlogArticleMapper blogArticleMapper;

    /**
     * 查询文章列表
     *
     * @param articleReqDto
     * @return
     */
    @Override
    public PageModel queryArticleList(ArticleReqDto articleReqDto) {
        //校验是否传入当前页
        ParamUtils.isParamsNotNull(articleReqDto, "current", "pageSize");
        //计算出分页开始位置
        if (0 <= articleReqDto.getCurrent()) {
            articleReqDto.setCurrent(1);
        }
        //设置分页开始位置
        articleReqDto.setStart((articleReqDto.getCurrent() - 1) * articleReqDto.getPageSize());
        //设置分页结束位置
        articleReqDto.setEnd(articleReqDto.getPageSize());

        //查询总记录
        int total = blogArticleMapper.queryArticleCount(articleReqDto);

        //获取列表数据集合
        List<ArticleRespDto> rows = blogArticleMapper.queryArticleList(articleReqDto);

        PageModel pageModel = new PageModel();
        pageModel.setRows(rows);
        pageModel.setCount(rows.size());
        pageModel.setTotal(total);
        return pageModel;
    }

    /**
     * 添加文章
     *
     * @param articleReqDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveArticle(ArticleReqDto articleReqDto) {
        // 参数转换
        BlogArticle article = this.ArticleReqDtoToBlogArticle(articleReqDto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Date currDate = DateTimeUtil.getCurrDate();
        // 传入参数文章唯一编号为空则做增加操作
        if (StringUtils.isEmpty(articleReqDto.getArticleUniqueCode())) {
            // 1.参数校验
            ParamUtils.isParamsNotNull(articleReqDto, "articleTitle", "articleTags", "catrgoryCode", "catrgoryCode");
            article.setArticleCreatetime(currDate);
            article.setArticleCreatecode(username);
            article.setArticleUniqueCode(GenerateSystemCodeUtils.obtainKeyDateSeqYMD("WZMK"));
            // 3.文章新增
            save(article);
            //TODO 标签与分类新增待处理
        } else {
            // 更新操作
            article.setArticleUpdatecode(username);
            article.setArticleUpdatetim(currDate);
            QueryWrapper<BlogArticle> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("ARTICLE_UNIQUE_CODE",article.getArticleUniqueCode());
            update(article,queryWrapper);
            //TODO 标签与分类修改待处理
        }
    }

    @Override
    public void deleteArticle(ArticleReqDto articleReqDto) {
        blogArticleMapper.deleteArticle(articleReqDto);
    }

    /**
     * 用于初始化文章添加时需要的默认值
     *
     * @param BlogArticle
     * @return
     */
    private BlogArticle ArticleReqDtoToBlogArticle(ArticleReqDto articleReqDto) {
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setArticleContent(articleReqDto.getArticleContent());
        blogArticle.setArticleImage(articleReqDto.getArticleImage());
        blogArticle.setArticleTitle(articleReqDto.getArticleTitle());
        blogArticle.setArticleCreatetime(articleReqDto.getArticleCreatetime());
        blogArticle.setArticleDelflag(articleReqDto.getArticleDelflag());
        blogArticle.setArticleEnableComment(articleReqDto.getArticleEnableComment());
        blogArticle.setArticleHot(articleReqDto.getArticleHot());
        blogArticle.setArticleOrder(articleReqDto.getArticleOrder());
        blogArticle.setArticleUniqueCode(articleReqDto.getArticleUniqueCode());
        blogArticle.setArticleCreatecode(articleReqDto.getArticleCreatecode());
        blogArticle.setArticleUpdatecode(articleReqDto.getArticleUpdatecode());
        blogArticle.setArticleUpdatetim(articleReqDto.getArticleUpdatetim());
        blogArticle.setArticleViews(articleReqDto.getArticleViews());
        return blogArticle;
    }


}
