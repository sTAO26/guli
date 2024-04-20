/**
  * Copyright 2020 bejson.com 
  */
package com.atguigu.common.vo.product;

import lombok.Data;


@Data
public class BaseAttrs {

    private Long attrId;
    private String attrValues;
    private int showDesc;

}