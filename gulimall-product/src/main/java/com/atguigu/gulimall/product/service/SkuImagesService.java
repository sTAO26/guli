package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.entity.product.SkuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询sku图片信息
     */
    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}

