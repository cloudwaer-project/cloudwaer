package com.cloudwaer.comment.service.impl;

import com.cloudwaer.comment.mapper.CommentMapper;
import com.cloudwaer.comment.quantity.CommentQT;
import com.cloudwaer.comment.quantity.CommonUtil;
import com.cloudwaer.comment.service.CommentService;
import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.common.utils.ErrorException;
import com.cloudwaer.common.utils.ErrorMe;
import com.cloudwaer.common.utils.StringRandom;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper objectMapper = new ObjectMapper();
        List<BlogComment> list = null;
        try {
            list = commentMapper.finmCommentByArticleCode(article_code);
        } catch (Exception e) {
            throw new ErrorException(7865, "长时间未操作,状态失效请等待刷新页面,然后重试");
        }
        CommonUtil commonUtil = null;//单条评论
        List<CommonUtil> commonUtils = new ArrayList<>();//全部的评论包含了一级的
        if (list.size() <= 0) {
            throw new ErrorException(ErrorMe.COMMENTNO_FOUND);
        }
        //赛选出一级评论
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentCode() == null || "".equals(list.get(i).getParentCode()) || list.get(i).getParentCode() == "") {
                //空的说明没有父评论code也就是标识这是一级评论
                commonUtil = new CommonUtil();
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
                //需要查询当前评论下面的子评论放到common_tow里面根据当前的parentCommentCode是否等于parentCode
                for (int j = 0; j < list.size(); j++) {
                    //空的表示是一级评论 直接跳过
                    if (list.get(j).getParentCode() == null || "".equals(list.get(j).getParentCode()) || list.get(j).getParentCode() == "") {
                        continue;
                    }
                    //走到这里就是二级评论了需要判断一级评论的parentCommentCode是否等于当前parentCode
                    if (commonUtil.getParentCommentCode() == list.get(j).getParentCode() || commonUtil.getParentCommentCode().equals(list.get(j).getParentCode())) {
                        //相等即是该一级评论下面的二级评论
                        commonUtil.common_tow.add(list.get(j));
                    }
                }
                commonUtils.add(commonUtil);
                commonUtil = null;//添加一级评论和下面的二级评论后将commonUtil清空
            }
        }
       /* //将二级评论放到commonUtils里面的commonUtil里面的common_tow list集合里面
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentCode() == null || "".equals(list.get(i).getParentCode()) || list.get(i).getParentCode() == "") {
                continue;
            }
            //这里需要判断放到的是哪个commonUtil里面
            for (int j = 0; j < commonUtils.size(); j++) {
                //ParentCode都相等的情况下说明该二级评论是当前一级评论的子评论
                if (commonUtils.get(j).getParentCommentCode().equals(list.get(i).getParentCode()) || list.get(i).getParentCode() == commonUtils.get(j).getParentCommentCode()) {
                    //添加到当前的commonUtils里面的common_tow
                    commonUtils.get(j).common_tow.add(list.get(i));
                    System.out.println(commonUtils.get(j));
                    //添加之后跳过;
                }
            }
        }*/
        //return之前调用一次gc
        System.gc();
        return commonUtils;
    }
}
