package com.cloudwaer.common.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName CategoryRespDto
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/22 11:11
 * @Version 1.0
 **/
public class CategoryRespDto implements Serializable {
    private Integer id;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 类别唯一编号
     */
    private String categoryCode;

    /**
     * 该类别热度
     */
    private Integer categoryHot;

    /**
     * 该类别背景图片
     */
    private String categoryBgimg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getCategoryHot() {
        return categoryHot;
    }

    public void setCategoryHot(Integer categoryHot) {
        this.categoryHot = categoryHot;
    }

    public String getCategoryBgimg() {
        return categoryBgimg;
    }

    public void setCategoryBgimg(String categoryBgimg) {
        this.categoryBgimg = categoryBgimg;
    }
}
