package com.atguigu.gulimall.product.service;

import com.atguigu.common.entity.product.SkuInfoEntity;

import java.util.List;

/**
 * 推荐接口
 **/
public interface UserCFService {

    /**
     * 获取推荐列表
     * @return
     */
    List<SkuInfoEntity> getRecommendSkus();
}
