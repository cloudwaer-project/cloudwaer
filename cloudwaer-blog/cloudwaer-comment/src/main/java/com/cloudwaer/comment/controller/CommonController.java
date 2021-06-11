package com.cloudwaer.comment.controller;

import com.cloudwaer.comment.quantity.CommentQT;
import com.cloudwaer.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    /**
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
}
