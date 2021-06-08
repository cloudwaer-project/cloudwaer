package com.cloudwaer.controller;

import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.PageModel;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.dto.ArticleReqDto;
import com.cloudwaer.dto.ArticleRespDto;
import com.cloudwaer.service.BlogArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ArticleController 控制层  --->  请求访问层
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 10:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private BlogArticleService blogArticleService;

    @RequestMapping("/queryArticleList")
    public ResponseDto queryArticleList(ArticleReqDto articleReqDto) {
        try {
            PageModel articleRespDtos = blogArticleService.queryArticleList(articleReqDto);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, articleRespDtos);
        } catch (ParamsException pe) {
            logger.info("查询文章列表接口参数异常:{}", pe);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (Exception e) {
            logger.info("查询文章列表接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

}
