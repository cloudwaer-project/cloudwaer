package com.cloudwaer.channel.category;

import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.dto.category.CategoryReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName CategoryFeignClient
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/22 13:51
 * @Version 1.0
 **/
@FeignClient(value = "cloudwaer-category")
public interface CategoryFeignClient {
    /**
     * @description: 查询所有分类
     * @author wenchang
     * @date 2021/7/12 16:54
     * @version 1.0
     */
    @RequestMapping(value = "/blogCartgory/queryAllCategory", method = RequestMethod.POST)
    public ResponseDto queryAllCategory();
    /**
     * @description: 保存与修改分类接口
     * @author wenchang
     * @date 2021/7/12 16:54
     * @version 1.0
     */
    @RequestMapping(value = "/blogCartgory/saveOrUpdateCategory", method = RequestMethod.POST)
    public ResponseDto saveOrUpdateCategory(@RequestBody CategoryReqDto categoryReqDto);
}
