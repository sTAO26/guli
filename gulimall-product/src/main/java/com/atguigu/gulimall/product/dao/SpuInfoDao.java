package com.atguigu.gulimall.product.dao;

import com.atguigu.common.entity.product.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    /**
     * 修改商品状态
     */
    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}
