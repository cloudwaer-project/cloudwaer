package com.cloudwaer.comment.service;

import com.cloudwaer.comment.quantity.CommentQT;
import com.cloudwaer.comment.quantity.CommonUtil;
import com.cloudwaer.common.entity.BlogComment;

import java.util.List;

/**
 * @Author A_Nan
 * @Date 21/06/11 上午 10:57
 * @ClassName
 */
public interface CommentService {
    int addCommnet(CommentQT commentQT);

    List<CommonUtil> finmCommentByArticleCode(String article_code);
}
