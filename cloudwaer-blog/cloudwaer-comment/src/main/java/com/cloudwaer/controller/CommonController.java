package com.cloudwaer.controller;

import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.quantity.CommentQT;
import com.cloudwaer.quantity.CommonUtil;
import com.cloudwaer.quantity.UpdTopUtils;
import com.cloudwaer.service.CommentService;
import com.cloudwaer.common.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 评论的控制类
 *
 * @Author A_Nan
 * @Date 21/06/10 下午 3:42
 * @ClassName 评论模块的控制层
 */

@RestController
@RequestMapping("/Comment")
public class CommonController {

    private final ResultBody resultBody = new ResultBody();
    @Autowired
    private CommentService commnetService;

    /**
     * 发布一条消息,
     *
     * @Method addCommnet 发布一条评论
     * @USER_CODE 用户ID
     * @USER_NAME 用户显示名称
     * @ARTICLE_CODE 评论文章的CODE
     * @PARENT_COMMENT_CODE一级评论的CODE
     * @PARENT_COMMENT_USER_CODE 一级评论的用户CODE
     * @REPLY_COMMENT_CODE 二级评论的CODE
     * @REPLY_COMMENT_USER_CODE 二级评论的用户CODE
     * @CONTENT 评论的内容
     * @CREATE_TIME 发布时间 new Date
     * @PARENT_CODE 二级评论一级的id 如果没有这个参数即表示为一级评论
     * @CommentQT 前端传过来的参数 不需要非空判断 全局异常拦截了空指针 只需要调用toString就行
     */
    @PostMapping("/IssueComment")
    public String addCommnet(CommentQT commentQT) {
        commnetService.addCommnet(commentQT);
        resultBody.setCode(200);
        resultBody.setMessage("OK");
        resultBody.setResult(commentQT);
        return resultBody.toString();
    }

    /**
     * 一篇文章下面有多条评论,查询评论不需要其他数据只需要文章CODE即可
     * 为什么要吧参数接在后面 首先 因为前端不会手动请求这个服务,只能是ajax请求该服务 所以ajax会设置url即可 不会发生404Not Found
     * 前端只会传0和2的两种状态来到这个方法
     *
     * @ARTICLE_CODE 文章CODE
     */

    @RequestMapping("/finmCommentByArticleCode/{ARTICLE_CODE}")
    @ResponseBody
    public String finmCommentByArticleCode(@PathVariable("ARTICLE_CODE") String ARTICLE_CODE) {
        List<CommonUtil> list = commnetService.finmCommentByArticleCode(ARTICLE_CODE);
        resultBody.setCode(200);
        resultBody.setMessage("OK");
        resultBody.setResult(list);
        return resultBody.toString();
    }


    /**
     * 修改评论置顶状态
     *
     * @TOP_STATUS 是否置顶 默认false 修改为true 前端只需要传入当前状态(是否置顶即可)
     * @ARTICLE_CODE 文章COODE
     * @PARENT_COMMENT_CODE 一级评论CODE根据一级评论的CODE和文章的CODE来修改
     */

    @RequestMapping("/updateTopStatus")
    public String updateTopStatus(UpdTopUtils updTopUtils) {
        int i = commnetService.updateTopStatus(updTopUtils);
        if (i >= 1) {
            resultBody.setCode(200);
            resultBody.setMessage("OK");
            resultBody.setResult(null);
            return resultBody.toString();
        }
        resultBody.setCode(201);
        resultBody.setMessage("ERROR");
        resultBody.setResult("修改失败");
        return resultBody.toString();
    }

    /**
     * 获取所有未审核的的也就是 AUDIT_STATUS=0的
     */
    @RequestMapping("/findAllUnrevised")
    public String findAllUnrevised() {
        List<BlogComment> list = commnetService.findAllUnrevised();
        resultBody.setCode(201);
        resultBody.setMessage("OK");
        resultBody.setResult(list);
        return resultBody.toString();
    }

    /**
     * 审核评论是否通过
     * 需要评论id 后期需要权限认证
     */


/*
    @RequestMapping("/auditComment")
    public String auditComment() {
        resultBody.setCode(201);
        resultBody.setMessage("OK");
        resultBody.setResult(null);
        return resultBody.toString();
    }
*/

}