package com.atguigu.gulimall.product.service;

import com.atguigu.common.entity.product.BrandEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.entity.product.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新冗余数据
     */
    void updateBrand(Long brandId, String name);

    /**
     * 更新冗余数据
     */
    void updateCategory(Long catId, String name);

    /**
     * 获取分类关联的所有品牌
     */
    List<BrandEntity> getBrandsByCatId(Long catId);
}

