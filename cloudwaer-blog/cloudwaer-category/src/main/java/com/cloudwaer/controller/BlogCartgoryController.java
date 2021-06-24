package com.cloudwaer.controller;


import com.alibaba.fastjson.JSONObject;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.entity.category.BlogCartgory;
import com.cloudwaer.common.exception.BusinessException;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.common.dto.category.CategoryReqDto;
import com.cloudwaer.common.dto.category.CategoryRespDto;
import com.cloudwaer.service.BlogCartgoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/blogCartgory")
public class BlogCartgoryController {

    private Logger logger = LoggerFactory.getLogger(BlogCartgoryController.class);

    @Resource
    private BlogCartgoryService cartgoryService;

    /**
     * 新增 or 修改 分类接口
     *
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateCategory", method = RequestMethod.POST)
    public ResponseDto saveOrUpdateCategory(@RequestBody CategoryReqDto categoryReqDto) {
        try {
            logger.info("新增 or 修改接口入参:{}", JSONObject.toJSONString(categoryReqDto));
            CategoryRespDto categoryRespDto = cartgoryService.saveOrUpdateCategory(categoryReqDto);
            logger.info("新增 or 修改接口返参:{}", JSONObject.toJSONString(categoryRespDto));
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, categoryRespDto);
        } catch (ParamsException pe) {
            logger.info("新增 or 修改接口参数异常:{}", pe);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (BusinessException be) {
            logger.info("新增 or 修改接口业务异常:{}",be.getResponseCode());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.QUERY_CATEGORY_EXIST);
        } catch (Exception e) {
            logger.info("新增 or 修改接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

    @RequestMapping(value = "/queryAllCategory", method = RequestMethod.POST)
    public ResponseDto queryAllCategory() {
        try {
            List<BlogCartgory> blogCartgoryList = cartgoryService.queryAllCategory();
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, blogCartgoryList);
        } catch (Exception e) {
            logger.info("查询分类列表接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }


}

