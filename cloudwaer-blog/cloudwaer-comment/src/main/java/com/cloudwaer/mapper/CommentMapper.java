package com.cloudwaer.mapper;

import com.cloudwaer.common.entity.BlogComment;
import com.cloudwaer.quantity.UpdTopUtils;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author A_Nan
 * @Date 21/06/11 上午 11:35
 * @ClassName
 */

@Mapper
@Component
public interface CommentMapper {

    @Insert("INSERT INTO `cloudwear`.`blog_comment`(`ID`, `USER_CODE`, `USER_NAME`, `ARTICLE_CODE`, `PARENT_COMMENT_CODE`, `PARENT_COMMENT_USER_CODE`, `REPLY_COMMENT_CODE`, `REPLY_COMMENT_USER_CODE`, `COMMENT_LEVEL`, `CONTENT`, `STATUS`, `PRAISE_NUM`, `TOP_STATUS`, `CREATE_TIME`, `AUDIT_STATUS`, `PARENT_CODE`) VALUES (null,'${userCode}', '${userName}', '${articleCode}', '${parentCommentCode}', '${parentCommentUserCode}', '${replyCommentCode}', '${replyCommentUserCode}', ${commentLevel}, '${content}', ${status}, ${praiseNum}, ${topStatus}, '${createTime}', ${auditStatus}, '${parentCode}');")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addCommnet(BlogComment blogComment);

    @Select("select count(PARENT_COMMENT_CODE) from blog_comment where PARENT_COMMENT_CODE='${parent_comment_code}'")
    int findParentCommentCode(@Param("parent_comment_code") String parent_comment_code);

    @Select("select * from blog_comment where ARTICLE_CODE='${article_code}' and AUDIT_STATUS='2'")
    List<BlogComment> finmCommentByArticleCode(@Param("article_code") String article_code);

    @Update("update blog_comment set TOP_STATUS=${TOP_STATUS} where ARTICLE_CODE='${ARTICLE_CODE}' and PARENT_COMMENT_CODE='${PARENT_COMMENT_CODE}'")
    int updateTopStatus(UpdTopUtils updTopUtils);

    @Select("select * from blog_comment where AUDIT_STATUS='0'")
    List<BlogComment> findAllUnrevised();
}
