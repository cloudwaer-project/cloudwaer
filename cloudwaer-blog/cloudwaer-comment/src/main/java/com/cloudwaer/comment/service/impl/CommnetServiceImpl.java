package com.cloudwaer.comment.service.impl;

import com.cloudwaer.comment.mapper.CommentMapper;
import com.cloudwaer.comment.quantity.CommentQT;
import com.cloudwaer.comment.service.CommentService;
import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.common.utils.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
