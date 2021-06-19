package com.cloudwaer.quantity;

import com.cloudwaer.common.utils.ErrorException;
import com.cloudwaer.common.utils.ErrorMe;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author A_Nan
 * @Date 21/06/16 上午 10:25
 * @ClassName 修改是否置顶的工具类
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdTopUtils {
    private Integer TOP_STATUS;//当前是否置顶状态,默认为0不置顶
    private String ARTICLE_CODE;//评论的文章CODE
    private String PARENT_COMMENT_CODE;//一级评论的CODE

    public Integer getTOP_STATUS() {
        if (TOP_STATUS == 1) {
            return 0;
        } else if (TOP_STATUS == 0) {
            return 1;
        } else {
            throw new ErrorException(ErrorMe.TOP_ERROR);
        }
    }

    public void setTOP_STATUS(Integer TOP_STATUS) {
        this.TOP_STATUS = TOP_STATUS;
    }

    public String getARTICLE_CODE() {
        return ARTICLE_CODE;
    }

    public void setARTICLE_CODE(String ARTICLE_CODE) {
        this.ARTICLE_CODE = ARTICLE_CODE;
    }

    public String getPARENT_COMMENT_CODE() {
        return PARENT_COMMENT_CODE;
    }

    public void setPARENT_COMMENT_CODE(String PARENT_COMMENT_CODE) {
        this.PARENT_COMMENT_CODE = PARENT_COMMENT_CODE;
    }


    /**
     * .toString如果为空即会触发空指针会被全局异常拦截相当于数据校验,然后前端做非空判断即可保证数据一致
     */

    @Override
    public String toString() {
        TOP_STATUS.toString();
        ARTICLE_CODE.toString();
        PARENT_COMMENT_CODE.toString();
        return "UpdTopUtils{" +
                "TOP_STATUS='" + TOP_STATUS + '\'' +
                ", ARTICLE_CODE='" + ARTICLE_CODE + '\'' +
                ", PARENT_COMMENT_CODE='" + PARENT_COMMENT_CODE + '\'' +
                '}';
    }
}
