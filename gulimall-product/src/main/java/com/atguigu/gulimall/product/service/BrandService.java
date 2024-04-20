package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.entity.product.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 修改品牌表，同时修改冗余表数据
     */
    void updateDetail(BrandEntity brand);
}

