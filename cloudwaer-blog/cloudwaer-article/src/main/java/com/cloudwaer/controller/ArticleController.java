package com.cloudwaer.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudwaer.channel.category.CategoryFeignClient;
import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.ParamsException;
import com.cloudwaer.common.utils.PageModel;
import com.cloudwaer.common.utils.ResponseUtils;
import com.cloudwaer.common.dto.article.ArticleReqDto;
import com.cloudwaer.service.BlogArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/queryArticleList", method = RequestMethod.POST)
    public ResponseDto queryArticleList(@RequestBody ArticleReqDto articleReqDto) {
        try {
            logger.info("查询文章列表接口入参:{}", JSONObject.toJSONString(articleReqDto));
            PageModel articleRespDtos = blogArticleService.queryArticleList(articleReqDto);
            logger.info("查询文章列表接口反参:{}", JSONObject.toJSONString(articleRespDtos));
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS, articleRespDtos);
        } catch (ParamsException pe) {
            logger.info("查询文章列表接口参数异常:{}", pe);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, pe.getMsg());
        } catch (Exception e) {
            logger.info("查询文章列表接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

    /**
     * 新增和更新文章
     *
     * @param articleReqDto
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateArticle", method = RequestMethod.POST)
    public ResponseDto saveOrUpdateArticle(@RequestBody ArticleReqDto articleReqDto) {
        try {
            logger.info("添加文章列表接口入参:{}", JSONObject.toJSONString(articleReqDto));
            blogArticleService.saveArticle(articleReqDto);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS);
        } catch (ParamsException e) {
            logger.info("添加文章接口参数异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, e.getMsg());
        } catch (RuntimeException re) {
            logger.info("添加文章接口运行时异常:{}", re);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR, re.getMessage());
        } catch (Exception e) {
            logger.info("添加文章接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    public ResponseDto deleteArticle(@RequestBody ArticleReqDto articleReqDto) {
        try {
            logger.info("删除文章列表接口入参:{}", JSONObject.toJSONString(articleReqDto));
            blogArticleService.deleteArticle(articleReqDto);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS);
        } catch (ParamsException e) {
            logger.info("删除文章接口参数异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR, e.getMsg());
        } catch (Exception e) {
            logger.info("删除文章接口异常:{}", e);
            return ResponseUtils.buildVoByResponseCode(ResponseCode.ERROR);
        }
    }

    @Resource
    private CategoryFeignClient categoryFeignClient;

    /*@Autowired
    private BlogArticleMapper blogArticleMapper;

    @GetMapping("/test")
    public String test() {

        List<Dept> depts = blogArticleMapper.queryAll();
        List<Dept> result = Lists.newArrayList();
        result = depts.stream().filter(dept -> 0 == dept.getParentId()).collect(Collectors.toList());
        // 1. 首先获取所有的根节点
        // 2. 遍历获取的根节点集合,根据根节点的ID查询子节点数据,拿父节点ID跟子节点parentId比较
        // 3. 如果在不知道有几级节点的情况下,使用递归
        for (Dept dept : result) {
            List<Dept> childNode = this.queryChildNode(dept, depts);
            dept.setChildren(childNode);
        }

        return JSONObject.toJSONString(result, true);
    }

    private List<Dept> queryChildNode(Dept dept, List<Dept> depts) {
        List<Dept> childNodeList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(depts)) {
            childNodeList = depts.stream().filter(childDept -> dept.getId().equals(childDept.getParentId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(childNodeList)) {
                for (Dept deptBean : childNodeList) {
                    List<Dept> childNode = this.queryChildNode(deptBean, depts);
                    deptBean.setChildren(childNode);
                }
            }
        }
        return childNodeList;
    }*/

}
