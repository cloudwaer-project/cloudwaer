package com.cloudwaer.service;

import com.cloudwaer.quantity.CommentQT;
import com.cloudwaer.quantity.CommonUtil;

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
