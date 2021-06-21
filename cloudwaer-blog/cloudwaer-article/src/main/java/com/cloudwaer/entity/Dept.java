package com.cloudwaer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 部门表
 */
@Data
public class Dept implements Serializable {

    //部门id
    private Integer id;

    //父级id
    private Integer parentId;

    private List<Dept> children = new ArrayList<>();

}
