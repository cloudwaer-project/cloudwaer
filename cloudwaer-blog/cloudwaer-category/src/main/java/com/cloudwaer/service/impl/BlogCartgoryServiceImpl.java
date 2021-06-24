package com.cloudwaer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.entity.category.BlogCartgory;
import com.cloudwaer.common.exception.BusinessException;
import com.cloudwaer.common.utils.GenerateSystemCodeUtils;
import com.cloudwaer.common.utils.ParamUtils;
import com.cloudwaer.common.dto.category.CategoryReqDto;
import com.cloudwaer.common.dto.category.CategoryRespDto;
import com.cloudwaer.mapper.BlogCartgoryMapper;
import com.cloudwaer.service.BlogCartgoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-22
 */
@Service
public class BlogCartgoryServiceImpl extends ServiceImpl<BlogCartgoryMapper, BlogCartgory> implements BlogCartgoryService {


    /**
     * 新增 or 更新 分类
     *
     * @param categoryReqDto
     * @return
     */
    @Override
    public CategoryRespDto saveOrUpdateCategory(CategoryReqDto categoryReqDto) {
        // 转换实体
        BlogCartgory blogCartgory = this.CategoryReqDtoToBlogCartgory(categoryReqDto);

        // 2. 校验分类是否已经存在
        QueryWrapper<BlogCartgory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CATEGORY_NAME", categoryReqDto.getCategoryName());
        BlogCartgory cartgory = getOne(queryWrapper);

        // 新增操作
        if (StringUtils.isEmpty(categoryReqDto.getCategoryCode())) {
            // 1. 参数校验
            String args[] = {"categoryName"};
            ParamUtils.isParamsNotNull(categoryReqDto, args);

            // 3. 返回分类生成编号
            CategoryRespDto categoryRespDto = new CategoryRespDto();
            if (null != cartgory) {
                categoryRespDto.setCategoryCode(cartgory.getCategoryCode());
            } else {
                // 4. 唯一编号生成
                String categoryCode = GenerateSystemCodeUtils.obtainKeyDateSeqYMD("FLMK");
                categoryRespDto.setCategoryCode(categoryCode);
                blogCartgory.setCategoryCode(categoryCode);
                // 5. 保存分类数据
                save(blogCartgory);
            }
            return categoryRespDto;
        }
        // 修改操作无需返回
        // 修改分类时,分类名不能与数据库中相同,否则抛出异常
        if (null != cartgory) {
            throw new BusinessException(ResponseCode.QUERY_CATEGORY_EXIST);
        }
        update(blogCartgory, new QueryWrapper<BlogCartgory>().eq("CATEGORY_CODE", blogCartgory.getCategoryCode()));
        return null;
    }

    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    public List<BlogCartgory> queryAllCategory() {
        return list();
    }

    /**
     * 转换实体
     *
     * @param categoryReqDto
     * @return
     */
    private BlogCartgory CategoryReqDtoToBlogCartgory(CategoryReqDto categoryReqDto) {
        BlogCartgory blogCartgory = new BlogCartgory();
        blogCartgory.setCategoryBgimg(categoryReqDto.getCategoryBgimg());
        blogCartgory.setCategoryHot(categoryReqDto.getCategoryHot());
        blogCartgory.setCategoryCode(categoryReqDto.getCategoryCode());
        blogCartgory.setCategoryName(categoryReqDto.getCategoryName());
        blogCartgory.setId(categoryReqDto.getId());
        return blogCartgory;
    }
}
