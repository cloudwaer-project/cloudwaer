package com.cloudwaer.service;

import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.quantity.CommentQT;
import com.cloudwaer.quantity.CommonUtil;
import com.cloudwaer.quantity.UpdTopUtils;

import java.util.List;

/**
 * @Author A_Nan
 * @Date 21/06/11 上午 10:57
 * @ClassName
 */
public interface CommentService {
    int addCommnet(CommentQT commentQT);

    List<CommonUtil> finmCommentByArticleCode(String article_code);

    int updateTopStatus(UpdTopUtils updTopUtils);

    List<BlogComment> findAllUnrevised();
}
