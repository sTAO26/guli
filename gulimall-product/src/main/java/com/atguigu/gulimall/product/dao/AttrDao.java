package com.atguigu.gulimall.product.dao;

import com.atguigu.common.entity.product.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 

 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    /**
     * 查询允许被检索的属性ID集合
     */
    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
