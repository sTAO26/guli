package com.atguigu.gulimall.product.feign;

import com.atguigu.common.utils.R;
import com.atguigu.gulimall.product.fallback.SeckillFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 **/
@FeignClient(value = "gulimall-seckill", fallback = SeckillFeignServiceFallBack.class,
        configuration = SeckillFeignServiceFallBack.class)
public interface SeckillFeignService {

    /**
     * 根据skuId查询商品是否参加秒杀活动
     */
    @GetMapping(value = "/sku/seckill/{skuId}")
    R getSkuSeckilInfo(@PathVariable("skuId") Long skuId);

}
