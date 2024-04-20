package com.atguigu.common.to.product;


import lombok.Data;

import java.io.Serializable;

/**
 * 商品信息TO
 */
@Data
public class BuyTimeTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long skuId;
    private Integer time;

    public BuyTimeTO(Long skuid, Integer time) {
        this.skuId = skuid;
        this.time = time;
    }
    public BuyTimeTO(){}
}

