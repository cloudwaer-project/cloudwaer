package com.cloudwaer.common.dto.tags;

import lombok.Data;

/**
 * @ClassName LabelRespDto
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/23 9:13
 * @Version 1.0
 **/
@Data
public class LabelRespDto {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签唯一编号
     */
    private String labelCode;

    /**
     * 标签背景图片
     */
    private String labelBgimg;

    /**
     * 标签热度
     */
    private Integer labelHeat;

}
