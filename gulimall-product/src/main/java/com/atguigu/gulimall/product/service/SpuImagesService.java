package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.entity.product.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu图片
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增商品图片
     */
    void saveSpuImages(Long spuId, List<String> images);
}

