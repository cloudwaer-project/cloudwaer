package com.cloudwaer.common.entity.category;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiushiboy
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogCartgory implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键自增
     */
    @TableId("ID")
    private int id;

    /**
     * 类别名称
     */
    @TableField("CATEGORY_NAME")
    private String categoryName;

    /**
     * 类别唯一编号
     */
    @TableField("CATEGORY_CODE")
    private String categoryCode;

    /**
     * 该类别热度
     */
    @TableField("CARTGORY_HOT")
    private Integer categoryHot;

    /**
     * 该类别背景图片
     */
    @TableField("CARTGORY_BGIMG")
    private String categoryBgimg;


}
