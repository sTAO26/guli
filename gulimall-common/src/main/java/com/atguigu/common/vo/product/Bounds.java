/**
  * Copyright 2020 bejson.com 
  */
package com.atguigu.common.vo.product;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Bounds {

    private BigDecimal buyBounds;
    private BigDecimal growBounds;

}