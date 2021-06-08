package com.cloudwaer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.utils.PageModel;
import com.cloudwaer.common.utils.ParamUtils;
import com.cloudwaer.dto.ArticleReqDto;
import com.cloudwaer.dto.ArticleRespDto;
import com.cloudwaer.entity.BlogArticle;
import com.cloudwaer.mapper.BlogArticleMapper;
import com.cloudwaer.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        String params[] = {"current", "pageSize"};
        ParamUtils.isParamsNotNull(articleReqDto, params);
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
}
