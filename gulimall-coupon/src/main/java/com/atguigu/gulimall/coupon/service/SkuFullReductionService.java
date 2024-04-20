package com.atguigu.gulimall.coupon.service;

import com.atguigu.common.to.product.SkuReductionTO;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.coupon.entity.SkuFullReductionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 商品满减信息
 *
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增满减信息（发布商品）
     */
    void saveSkuReduction(SkuReductionTO reductionTo);
}

