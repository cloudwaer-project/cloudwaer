package com.cloudwaer.comment.controller;

import com.cloudwaer.comment.quantity.CommentQT;
import com.cloudwaer.comment.quantity.CommonUtil;
import com.cloudwaer.comment.service.CommentService;
import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.common.utils.ErrorException;
import com.cloudwaer.common.utils.ErrorMe;
import com.cloudwaer.common.utils.ResultBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author A_Nan
 * @Date 21/06/10 下午 3:42
 * @ClassName 评论的控制层
 */

@RestController
@RequestMapping("/Comment")
public class CommonController {

    @Autowired
    private CommentService commnetService;


    private ResultBody resultBody = new ResultBody();


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
        return commentQT.toString();
    }

    /**
     * 一篇文章下面有多条评论,查询评论不需要其他数据只需要文章CODE即可
     * 为什么要吧参数接在后面 首先 因为前端不会手动请求这个服务,只能是ajax请求该服务 所以ajax会设置url即可 不会发生404Not Found
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


/*    *//**
     * 修改评论置顶状态
     *
     * @param
     *//*

    @RequestMapping("/updateTopStatus")
    public String updateTopStatus(@RequestBody JsonNode PARENTCOMMENTCODE) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(PARENTCOMMENTCODE.toString());
        System.out.println(PARENTCOMMENTCODE.get("PARENTCOMMENTCODE"));
//        commnetService.updateTopStatus(PARENTCOMMENTCODE);
        resultBody.setResult(null);
        resultBody.setCode(200);
        resultBody.setMessage("OK");
        return resultBody.toString();
    }*/
}