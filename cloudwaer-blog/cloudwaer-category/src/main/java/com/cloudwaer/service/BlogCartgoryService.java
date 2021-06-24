package com.cloudwaer.service;

import com.cloudwaer.common.entity.category.BlogCartgory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudwaer.common.dto.category.CategoryReqDto;
import com.cloudwaer.common.dto.category.CategoryRespDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-22
 */
public interface BlogCartgoryService extends IService<BlogCartgory> {

    /**
     * 新增 or 更新分类
     * @param categoryReqDto
     * @return
     */
    CategoryRespDto saveOrUpdateCategory(CategoryReqDto categoryReqDto);

    /**
     * 查询所有分类
     * @return
     */
    List<BlogCartgory> queryAllCategory();

}
