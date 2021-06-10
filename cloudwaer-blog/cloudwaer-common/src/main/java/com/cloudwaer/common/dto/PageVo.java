package com.cloudwaer.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName PageVo
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 11:40
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PageVo {
    //当前页
    private int current;
    //每页数
    private int pageSize;
    private int start;
    private int end;
    private String sortOrder;
}
