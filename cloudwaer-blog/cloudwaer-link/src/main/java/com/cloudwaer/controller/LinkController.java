package com.cloudwaer.controller;

import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.entity.BlogLink;
import com.cloudwaer.service.BlogLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author wenchang
 * @Date 2021/7/13 9:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BlogLinkService blogLinkService;

    @PostMapping("queryLinkList")
    public ResponseDto queryLinkList(@RequestBody BlogLink blogLink) {
        try {
            List<BlogLink> result = blogLinkService.queryLinkList(blogLink);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, result);
        } catch (ParamsException pe) {
            logger.info("友链查询接口参数异常:{}", pe.getMsg());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (Exception e) {
            logger.info("友链查询接口异常:{}", e.getMessage());
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR, e.getMessage());
        }
    }
}
