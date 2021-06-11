package com.cloudwaer.comment.service.impl;

import com.cloudwaer.comment.mapper.CommentMapper;
import com.cloudwaer.comment.quantity.CommentQT;
import com.cloudwaer.comment.quantity.CommonUtil;
import com.cloudwaer.comment.service.CommentService;
import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.common.utils.ErrorException;
import com.cloudwaer.common.utils.ErrorMe;
import com.cloudwaer.common.utils.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author A_Nan
 * @Date 21/06/11 上午 11:09
 * @ClassName
 */
@Service
public class CommnetServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addCommnet(CommentQT commentQT) {
        //为了防止生成的CODE与数据库发生冲突需要先验证数据库是否有这个CODE
        String PARENT_COMMENT_CODE = null;
        int length = 0;
        do {
            PARENT_COMMENT_CODE = StringRandom.getString(10);
            length = commentMapper.findParentCommentCode(PARENT_COMMENT_CODE);
        } while (length >= 1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式 到时候设计为工具类
        //创建需要添加的数据的实体类对象并且设置数据
        BlogComment blogComment = new BlogComment(commentQT.getUSER_CODE(), commentQT.getUSER_NAME(),
                commentQT.getARTICLE_CODE(), PARENT_COMMENT_CODE,
                commentQT.getPARENT_COMMENT_USER_CODE(),
                commentQT.getREPLY_COMMENT_CODE(),
                commentQT.getREPLY_COMMENT_USER_CODE(),
                commentQT.getCONTENT(), df.format(new Date()), commentQT.getPARENT_CODE());
        return commentMapper.addCommnet(blogComment);
    }

    @Override
    public List<CommonUtil> finmCommentByArticleCode(String article_code) {
        CommonUtil commonUtil = new CommonUtil();//一级评论单条
        List<CommonUtil> commorResut = new ArrayList<>();//一级评论列表 一级评论包含二级
        List<BlogComment> comments = new ArrayList<>();//二级评论
        List<BlogComment> list = commentMapper.finmCommentByArticleCode(article_code);
        if (list.size() <= 0) {
            throw new ErrorException(ErrorMe.COMMENTNO_FOUND);
        }
        for (int i = 0; i < list.size(); i++) {
            //二级评论
            if (!(list.get(i).getReplyCommentCode() == "" || list.get(i).getReplyCommentCode() == null || list.get(i).getReplyCommentCode().equals(""))) {
                comments.add(list.get(i));//将当前的二级评论放到上面的二级评论
            }
            //一级评论需要一个个设置
            commonUtil.setId(list.get(i).getId());
            commonUtil.setUserCode(list.get(i).getUserCode());
            commonUtil.setUserName(list.get(i).getUserName());
            commonUtil.setArticleCode(list.get(i).getArticleCode());
            commonUtil.setParentCommentCode(list.get(i).getParentCommentCode());
            commonUtil.setParentCommentUserCode(list.get(i).getParentCommentUserCode());
            commonUtil.setReplyCommentCode(list.get(i).getReplyCommentCode());
            commonUtil.setReplyCommentUserCode(list.get(i).getReplyCommentUserCode());
            commonUtil.setCommentLevel(list.get(i).getCommentLevel());
            commonUtil.setContent(list.get(i).getContent());
            commonUtil.setStatus(list.get(i).getStatus());
            commonUtil.setPraiseNum(list.get(i).getPraiseNum());
            commonUtil.setTopStatus(list.get(i).getTopStatus());
            commonUtil.setCreateTime(list.get(i).getCreateTime());
            commonUtil.setAuditStatus(list.get(i).getAuditStatus());
            commonUtil.setParentCode(list.get(i).getParentCode());
            //设置完不能忘记判断二级ID和这个是否相等
            for (int j = 0; j < comments.size(); j++) {

            }
        }
        return commorResut;
    }
}
